package com.wallet_service.dto;

import com.wallet_service.model.Wallet.Currency;

import java.math.BigDecimal;

public class WalletDto {

    private Long id;
    private String walletName;
    private Currency currency;
    private boolean activeForShopping;
    private boolean activeForWithdraw;
    private BigDecimal balance;
    private BigDecimal usableBalance;
    private Long customerId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setActiveForShopping(boolean activeForShopping) {
        this.activeForShopping = activeForShopping;
    }

    public void setActiveForWithdraw(boolean activeForWithdraw) {
        this.activeForWithdraw = activeForWithdraw;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setUsableBalance(BigDecimal usableBalance) {
        this.usableBalance = usableBalance;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public String getWalletName() {
        return walletName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isActiveForShopping() {
        return activeForShopping;
    }

    public boolean isActiveForWithdraw() {
        return activeForWithdraw;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getUsableBalance() {
        return usableBalance;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
