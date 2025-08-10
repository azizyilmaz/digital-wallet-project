package com.customer_service.service.impl;

import com.customer_service.dto.CustomerDto;
import com.customer_service.exception.NotFoundException;
import com.customer_service.mapper.CustomerMapper;
import com.customer_service.model.Customer;
import com.customer_service.repository.CustomerRepository;
import com.customer_service.service.AuthCheckService;
import com.customer_service.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuthCheckService authCheckService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, AuthCheckService authCheckService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.authCheckService = authCheckService;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        authCheckService.checkAuthorization(dto.getId());
        Customer entity = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(entity);
        return customerMapper.toDto(saved);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found for ID: " + id));
        return customerMapper.toDto(customer);
    }
}
