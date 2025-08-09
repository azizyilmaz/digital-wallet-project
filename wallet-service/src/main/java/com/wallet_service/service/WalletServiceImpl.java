package com.wallet_service.service;

import com.wallet_service.mapper.WalletMapper;
import com.wallet_service.model.Wallet;
import com.wallet_service.dto.WalletDto;
import com.wallet_service.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImpl(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public WalletDto createWallet(WalletDto dto) {
        Wallet wallet = walletMapper.toEntity(dto);
        return walletMapper.toDto(walletRepository.save(wallet));
    }

    @Override
    public List<WalletDto> getAllWallets() {
        return walletRepository.findAll()
                .stream()
                .map(walletMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WalletDto> getWalletsByCustomerAndFilter(Long customerId, String currency, BigDecimal minAmount, BigDecimal maxAmount) {
        Wallet.Currency currencyEnum = null;
        if (currency != null) {
            try {
                currencyEnum = Wallet.Currency.valueOf(currency);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid currency: " + currency);
            }
        }
        List<Wallet> wallets = walletRepository.findByFilters(customerId, currencyEnum, minAmount, maxAmount);
        return wallets.stream()
                .map(walletMapper::toDto)
                .collect(Collectors.toList());
    }
}
