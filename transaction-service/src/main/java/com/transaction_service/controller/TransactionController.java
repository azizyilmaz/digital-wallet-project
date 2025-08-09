package com.transaction_service.controller;

import com.transaction_service.dto.TransactionDto;
import com.transaction_service.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto deposit(@RequestBody TransactionDto dto) {
        return transactionService.deposit(dto);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto withdraw(@RequestBody TransactionDto dto) {
        return transactionService.withdraw(dto);
    }

    @PostMapping("/{id}/approve")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransactionDto> approve(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.approve(id));
    }

    @PostMapping("/{id}/reject")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransactionDto> reject(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.reject(id));
    }

    @GetMapping("/filter")
    public List<TransactionDto> listByIban(@RequestParam(name = "iban") String iban) {
        return transactionService.listByIban(iban);
    }

}