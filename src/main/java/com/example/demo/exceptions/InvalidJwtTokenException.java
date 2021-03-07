package com.example.demo.exceptions;

public class InvalidJwtTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidJwtTokenException() {
        super("Invalid jwt token provided.");
    }
}
