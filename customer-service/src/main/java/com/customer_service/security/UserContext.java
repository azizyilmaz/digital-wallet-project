package com.customer_service.security;

public class UserContext {
    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();
    private static final ThreadLocal<String> currentUserRole = new ThreadLocal<>();

    public static Long getUserId() {
        return currentUserId.get();
    }

    public static void setUserId(Long userId) {
        currentUserId.set(userId);
    }

    public static String getUserRole() {
        return currentUserRole.get();
    }

    public static void setUserRole(String role) {
        currentUserRole.set(role);
    }

    public static void clear() {
        currentUserId.remove();
        currentUserRole.remove();
    }
}