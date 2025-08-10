package com.transaction_service.config;

import com.transaction_service.security.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Long userId = UserContext.getUserId();
        String role = UserContext.getUserRole();

        if (userId != null) {
            template.header("X-User-Id", String.valueOf(userId));
        }
        if (role != null) {
            template.header("X-User-Role", role);
        }
    }
}