package com.wallet_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String walletName;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private boolean activeForShopping;
    private boolean activeForWithdraw;

    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal usableBalance = BigDecimal.ZERO;

    @Column(name = "customer_id")
    private Long customerId;

    public enum Currency {
        TRY, USD, EUR
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
        if (balance == null) {
            return BigDecimal.ZERO;
        }
        return balance;
    }

    public BigDecimal getUsableBalance() {
        if (usableBalance == null) {
            return BigDecimal.ZERO;
        }
        return usableBalance;
    }

    public Long getCustomerId() {
        return customerId;
    }

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
}
