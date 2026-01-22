package com.example.demo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        /*
         * http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
         * //.headers(headers ->
         * headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
         * .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
         * .formLogin(Customizer.withDefaults());
         * return http.build();
         */
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/test/**").authenticated()
                .requestMatchers("/h2-console/**").permitAll());
        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return (SecurityFilterChain) http.build();
    }
}
