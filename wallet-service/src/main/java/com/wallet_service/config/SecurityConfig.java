package com.wallet_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF closed (REST API)
                .csrf(csrf -> csrf.disable())

                // Authorization Resuest
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )

                // Basic Auth
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
