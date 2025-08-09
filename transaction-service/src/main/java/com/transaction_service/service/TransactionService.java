package com.transaction_service.service;

import com.transaction_service.dto.TransactionRequest;
import com.transaction_service.model.Transaction;

import java.util.List;

public interface TransactionService {
    
    Transaction deposit(TransactionRequest request);

    Transaction withdraw(TransactionRequest request);

    Transaction approve(Long transactionId);

    Transaction reject(Long transactionId);

    List<Transaction> listByIban(String iban);
}
