package com.wallet_service.service;

import com.wallet_service.dto.WalletDto;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {

    WalletDto createWallet(WalletDto dto);

    List<WalletDto> getAllWallets();

    List<WalletDto> getWalletsByCustomerAndFilter(Long customerId, String currency, BigDecimal minAmount, BigDecimal maxAmount);

    WalletDto getWalletById(Long id);

    void updateBalance(Long walletId, BigDecimal balanceChange, BigDecimal usableBalanceChange);

}
