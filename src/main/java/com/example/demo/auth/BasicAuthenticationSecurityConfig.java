package com.example.demo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class BasicAuthenticationSecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/todo*", "/api/fileUpload", "/api/user/*",
                                "/api/token/*", "/api/auth/apiKeyAuthenticatedResource", "/h2-console")
                        .permitAll().anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService configureGlobal() throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .passwordEncoder(passwordEncoder::encode)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
