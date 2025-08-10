package com.transaction_service.client;

import com.transaction_service.config.TransactionServiceClientConfig;
import com.transaction_service.dto.WalletDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "wallet-service", url = "${wallet.service.url}", configuration = TransactionServiceClientConfig.class)
public interface WalletServiceClient {

    @PutMapping("/api/wallets/{walletId}/balance")
    void updateBalance(@PathVariable("walletId") Long walletId, @RequestParam("balanceChange") BigDecimal balanceChange, @RequestParam("usableBalanceChange") BigDecimal usableBalanceChange);

    @GetMapping("/api/wallets/id/{id}")
    WalletDto getWalletById(@PathVariable("id") Long id);
}
