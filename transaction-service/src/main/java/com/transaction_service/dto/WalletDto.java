package com.transaction_service.dto;

import com.transaction_service.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletDto {

    private Long id;
    private String walletName;
    private Currency currency;
    private boolean activeForShopping;
    private boolean activeForWithdraw;
    private BigDecimal balance;
    private BigDecimal usableBalance;
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isActiveForShopping() {
        return activeForShopping;
    }

    public void setActiveForShopping(boolean activeForShopping) {
        this.activeForShopping = activeForShopping;
    }

    public boolean isActiveForWithdraw() {
        return activeForWithdraw;
    }

    public void setActiveForWithdraw(boolean activeForWithdraw) {
        this.activeForWithdraw = activeForWithdraw;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUsableBalance() {
        return usableBalance;
    }

    public void setUsableBalance(BigDecimal usableBalance) {
        this.usableBalance = usableBalance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
