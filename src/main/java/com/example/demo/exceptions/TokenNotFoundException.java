package com.example.demo.exceptions;

public class TokenNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenNotFoundException() {
        super("No jwt token found in headers.");
    }
}
