package com.transaction_service.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class WalletServiceClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString(("admin:admin123").getBytes(StandardCharsets.UTF_8));
            requestTemplate.header("Authorization", basicAuthHeader);
        };
    }
}