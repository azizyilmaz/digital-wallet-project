package com.wallet_service.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String userIdHeader = httpRequest.getHeader("X-User-Id");
            String userRoleHeader = httpRequest.getHeader("X-User-Role");

            if (userIdHeader != null) {
                UserContext.setUserId(Long.parseLong(userIdHeader));
            }
            if (userRoleHeader != null) {
                UserContext.setUserRole(userRoleHeader);
            }

            chain.doFilter(request, response);
        } finally {
            UserContext.clear(); // ThreadLocal temizle
        }
    }

    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws jakarta.servlet.ServletException {
        jakarta.servlet.Filter.super.init(filterConfig);
    }
}