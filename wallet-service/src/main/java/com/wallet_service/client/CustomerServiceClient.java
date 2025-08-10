package com.wallet_service.client;

import com.wallet_service.config.WalletServiceClientConfig;
import com.wallet_service.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service", url = "${customer.service.url}", configuration = WalletServiceClientConfig.class)
public interface CustomerServiceClient {
    @GetMapping("/api/customers/id/{id}")
    Optional<CustomerDto> getCustomerById(@PathVariable("id") Long id);
}
