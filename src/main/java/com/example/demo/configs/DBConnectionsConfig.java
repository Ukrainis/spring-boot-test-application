package com.example.demo.configs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties("spring.datasource")
public class DBConnectionsConfig {

    private String url;
    private String driverClassName;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public DataSource devDbConnection() {
        System.out.println("DB connection for DEV - H2");
        System.out.println("Using url: " + url);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        return new HikariDataSource(config);
    }

    @Profile("prod")
    @Bean
    public DataSource prodDbConnection() throws MalformedURLException, URISyntaxException {
        System.out.println("DB connection for PROD - PostgreSql");
        System.out.println("Using url: " + url);
        System.out.println("Using userName: " + username);
        System.out.println("Using password: " + password);

        HikariConfig config = new HikariConfig();

        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }
}