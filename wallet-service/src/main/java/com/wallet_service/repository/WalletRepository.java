package com.wallet_service.repository;

import com.wallet_service.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT w FROM Wallet w WHERE " +
            "(:customerId IS NULL OR w.customerId = :customerId) AND " +
            "(:currency IS NULL OR w.currency = :currency) AND " +
            "(:minAmount IS NULL OR w.balance >= :minAmount) AND " +
            "(:maxAmount IS NULL OR w.balance <= :maxAmount)")
    List<Wallet> findByFilters(@Param("customerId") Long customerId,
                               @Param("currency") Wallet.Currency currency,
                               @Param("minAmount") BigDecimal minAmount,
                               @Param("maxAmount") BigDecimal maxAmount);

    Optional<Wallet> findById(Long id);
}
