package com.wallet_service.controller;

import com.wallet_service.dto.WalletDto;
import com.wallet_service.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletDto create(@RequestBody WalletDto dto) {
        return walletService.createWallet(dto);
    }

    @GetMapping
    public List<WalletDto> list() {
        return walletService.getAllWallets();
    }

    @GetMapping("/filter")
    public List<WalletDto> getWalletsByFilter(
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "currency", required = false) String currency,
            @RequestParam(name = "minAmount", required = false) BigDecimal minAmount,
            @RequestParam(name = "maxAmount", required = false) BigDecimal maxAmount) {
        return walletService.getWalletsByCustomerAndFilter(customerId, currency, minAmount, maxAmount);
    }

    @GetMapping("/iban/{iban}")
    public ResponseEntity<WalletDto> getWalletById(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }

    @PutMapping("/{walletId}/balance")
    public ResponseEntity<Void> updateBalance(
            @PathVariable Long walletId,
            @RequestParam BigDecimal balanceChange,
            @RequestParam BigDecimal availableBalanceChange) {
        walletService.updateBalance(walletId, balanceChange, availableBalanceChange);
        return ResponseEntity.ok().build();
    }
}
