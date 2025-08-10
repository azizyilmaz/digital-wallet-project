package com.transaction_service.service;

import com.transaction_service.security.UserContext;
import org.springframework.stereotype.Service;

@Service
public class AuthCheckService {

    public void checkAuthorization(Long targetCustomerId) {
        String role = UserContext.getUserRole();
        Long userId = UserContext.getUserId();

        if (role == null || userId == null) {
            throw new RuntimeException("Authentication information missing");
        }

        if ("employee".equalsIgnoreCase(role)) {
            return;
        }

        if ("customer".equalsIgnoreCase(role)) {
            if (!userId.equals(targetCustomerId)) {
                throw new RuntimeException("Your authority only applies to your own account.");
            }
            return;
        }

        throw new RuntimeException("Invalid role.");
    }
}