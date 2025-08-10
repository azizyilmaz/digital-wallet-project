package com.customer_service.service;

import com.customer_service.dto.CustomerDto;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto dto);

    CustomerDto getCustomerById(Long id);
}
