package com.customer_service.controller;

import com.customer_service.dto.CustomerDto;
import com.customer_service.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerDto dto) {
        return customerService.createCustomer(dto);
    }

    // Internal API
    @GetMapping("id/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}
