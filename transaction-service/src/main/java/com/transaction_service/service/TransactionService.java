package com.transaction_service.service;

import com.transaction_service.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto deposit(TransactionDto request);

    TransactionDto withdraw(TransactionDto request);

    TransactionDto approve(Long transactionId);

    TransactionDto reject(Long transactionId);

    List<TransactionDto> listByIban(String iban);
}
