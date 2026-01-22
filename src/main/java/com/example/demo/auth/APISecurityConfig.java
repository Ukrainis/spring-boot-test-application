package com.example.demo.auth;

import com.example.demo.services.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(2)
public class APISecurityConfig {

    @Value("${sping-boot-test-aplication.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${sping-boot-test-aplication.http.auth-token}")
    private String principalRequestValue;

    public APISecurityConfig(JWTTokenService jwtTokenService) {
    }

    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!principalRequestValue.equals(principal)) {
                throw new BadCredentialsException("The API key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });
        return http
                // Matcher: Only apply this filter chain to requests matching this pattern
                .securityMatcher("/api/auth/apiKeyAuthenticatedResource")

                // Disable CSRF for stateless APIs
                .csrf(csrf -> csrf.disable())

                // Set session policy to STATELESS (typical for REST APIs)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Add the custom filter to the chain
                // .addFilter() is deprecated. We use .addFilterBefore() or .addFilterAt()
                // Here, we use .addFilter(filter) in the modern way, which adds it
                // at the position of the filter base class it extends.
                .addFilter(filter)

                // Authorization: All requests matching the matcher must be authenticated
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .build();
    }
}