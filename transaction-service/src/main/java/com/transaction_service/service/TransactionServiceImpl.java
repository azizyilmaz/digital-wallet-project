package com.transaction_service.service;

import com.transaction_service.client.WalletServiceClient;
import com.transaction_service.dto.TransactionDto;
import com.transaction_service.dto.WalletDto;
import com.transaction_service.enums.TransactionStatus;
import com.transaction_service.enums.TransactionType;
import com.transaction_service.exception.BadRequestException;
import com.transaction_service.exception.ConflictException;
import com.transaction_service.exception.NotFoundException;
import com.transaction_service.mapper.TransactionMapper;
import com.transaction_service.model.Transaction;
import com.transaction_service.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final BigDecimal THRESHOLD = new BigDecimal("1000");
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final WalletServiceClient walletServiceClient;
    private final AuthCheckService authCheckService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, WalletServiceClient walletServiceClient, AuthCheckService authCheckService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.walletServiceClient = walletServiceClient;
        this.authCheckService = authCheckService;
    }

    @Override
    @Transactional
    public TransactionDto deposit(TransactionDto dto) {

        validateAmount(dto.getAmount());

        WalletDto wallet = walletServiceClient.getWalletById(dto.getWalletId());
        if (wallet == null) {
            throw new NotFoundException("Wallet not found for wallet id: " + dto.getWalletId());
        }

        authCheckService.checkAuthorization(wallet.getCustomerId());

        TransactionStatus status = dto.getAmount().compareTo(THRESHOLD) > 0 ? TransactionStatus.PENDING : TransactionStatus.APPROVED;

        Transaction tx = Transaction.builder().id(dto.getId()).iban(dto.getIban()).walletId(dto.getWalletId()).type(TransactionType.DEPOSIT).amount(dto.getAmount()).status(status).createdAt(LocalDateTime.now()).description(dto.getDescription()).build();

        tx = transactionRepository.save(tx);

        // Wallet update
        if (status == TransactionStatus.APPROVED) {
            // increase balance and usableBalance
            walletServiceClient.updateBalance(wallet.getId(), dto.getAmount(), dto.getAmount());
        } else {
            // PENDING -> increase just balance, not usableBalance
            walletServiceClient.updateBalance(wallet.getId(), dto.getAmount(), BigDecimal.ZERO);
        }

        tx.setUpdatedAt(LocalDateTime.now());
        return transactionMapper.toDto(transactionRepository.save(tx));
    }

    @Override
    @Transactional
    public TransactionDto withdraw(TransactionDto dto) {
        validateAmount(dto.getAmount());

        WalletDto wallet = walletServiceClient.getWalletById(dto.getWalletId());
        if (wallet == null) {
            throw new NotFoundException("Wallet not found for wallet id: " + dto.getWalletId());
        }

        authCheckService.checkAuthorization(wallet.getCustomerId());

        // Active control
        if (!wallet.isActiveForWithdraw()) {
            throw new ConflictException("Withdraw operations are not allowed for this wallet.");
        }

        // UsableBalance balance control
        if (wallet.getUsableBalance().compareTo(dto.getAmount()) < 0) {
            throw new BadRequestException("Insufficient usable balance.");
        }

        TransactionStatus status = dto.getAmount().compareTo(THRESHOLD) > 0 ? TransactionStatus.PENDING : TransactionStatus.APPROVED;

        Transaction tx = Transaction.builder().iban(dto.getIban()).walletId(dto.getWalletId()).type(TransactionType.WITHDRAW).amount(dto.getAmount()).status(status).createdAt(LocalDateTime.now()).description(dto.getDescription()).build();

        tx = transactionRepository.save(tx);

        if (status == TransactionStatus.APPROVED) {
            // Descrease balance and usableBalance
            walletServiceClient.updateBalance(wallet.getId(), dto.getAmount().negate(), dto.getAmount().negate());
        } else {
            // PENDING -> Decrease just usableBalance, not normal balance
            walletServiceClient.updateBalance(wallet.getId(), BigDecimal.ZERO, dto.getAmount().negate());
        }

        tx.setUpdatedAt(LocalDateTime.now());
        return transactionMapper.toDto(transactionRepository.save(tx));
    }

    @Override
    @Transactional
    public TransactionDto approve(Long transactionId) {
        Transaction tx = transactionRepository.findById(transactionId).orElseThrow(() -> new NotFoundException("Transaction not found: " + transactionId));

        if (tx.getStatus() == TransactionStatus.APPROVED) {
            throw new ConflictException("Transaction already approved.");
        }
        if (tx.getStatus() == TransactionStatus.REJECTED) {
            throw new ConflictException("Transaction already rejected.");
        }

        WalletDto wallet = walletServiceClient.getWalletById(tx.getWalletId());
        if (wallet == null) throw new NotFoundException("Wallet not found for wallet id: " + tx.getIban());

        authCheckService.checkAuthorization(wallet.getCustomerId());

        if (tx.getType() == TransactionType.DEPOSIT) {
            walletServiceClient.updateBalance(wallet.getId(), BigDecimal.ZERO, tx.getAmount());
        } else { // WITHDRAW
            walletServiceClient.updateBalance(wallet.getId(), tx.getAmount().negate(), BigDecimal.ZERO);
        }

        tx.setStatus(TransactionStatus.APPROVED);
        tx.setUpdatedAt(LocalDateTime.now());
        return transactionMapper.toDto(transactionRepository.save(tx));
    }

    @Override
    @Transactional
    public TransactionDto reject(Long transactionId) {
        Transaction tx = transactionRepository.findById(transactionId).orElseThrow(() -> new NotFoundException("Transaction not found: " + transactionId));

        if (tx.getStatus() == TransactionStatus.REJECTED) {
            throw new ConflictException("Transaction already rejected.");
        }
        if (tx.getStatus() == TransactionStatus.APPROVED) {
            throw new ConflictException("Cannot reject already approved transaction.");
        }

        // tx is PENDING (only PENDING can be rejected in our flow)
        WalletDto wallet = walletServiceClient.getWalletById(tx.getWalletId());
        if (wallet == null) throw new NotFoundException("Wallet not found for wallet id: " + tx.getWalletId());

        authCheckService.checkAuthorization(wallet.getCustomerId());

        if (tx.getType() == TransactionType.DEPOSIT) {
            // PENDING deposit: at creation we increased balance. On reject, reduce balance.
            walletServiceClient.updateBalance(wallet.getId(), tx.getAmount().negate(), BigDecimal.ZERO);
        } else { // WITHDRAW
            // PENDING withdraw: at creation we decreased usableBalance. On reject, restore usableBalance.
            walletServiceClient.updateBalance(wallet.getId(), BigDecimal.ZERO, tx.getAmount());
        }

        tx.setStatus(TransactionStatus.REJECTED);
        tx.setUpdatedAt(LocalDateTime.now());
        return transactionMapper.toDto(transactionRepository.save(tx));
    }

    // This method is used for my check, not for employee or customer.
    @Override
    public List<TransactionDto> listByIban(String iban) {
        List<Transaction> transactions = transactionRepository.findByIbanOrderByCreatedAtDesc(iban);
        return transactions.stream().map(transactionMapper::toDto).collect(Collectors.toList());
    }

    private void validateAmount(java.math.BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be greater than zero.");
        }
    }
}