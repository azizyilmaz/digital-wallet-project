package com.transaction_service.controller;

import com.transaction_service.dto.TransactionRequest;
import com.transaction_service.dto.TransactionResponse;
import com.transaction_service.model.Transaction;
import com.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody TransactionRequest request) {
        Transaction tx = transactionService.deposit(request);
        return ResponseEntity.ok(map(tx));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody TransactionRequest request) {
        Transaction tx = transactionService.withdraw(request);
        return ResponseEntity.ok(map(tx));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<TransactionResponse> approve(@PathVariable Long id) {
        Transaction tx = transactionService.approve(id);
        return ResponseEntity.ok(map(tx));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<TransactionResponse> reject(@PathVariable Long id) {
        Transaction tx = transactionService.reject(id);
        return ResponseEntity.ok(map(tx));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> listByIban(@RequestParam("iban") String iban) {
        List<Transaction> list = transactionService.listByIban(iban);
        List<TransactionResponse> resp = list.stream().map(this::map).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    private TransactionResponse map(Transaction tx) {
        return TransactionResponse.builder().id(tx.getId()).iban(tx.getIban()).type(tx.getType()).amount(tx.getAmount()).status(tx.getStatus()).createdAt(tx.getCreatedAt()).updatedAt(tx.getUpdatedAt()).description(tx.getDescription()).build();
    }
}