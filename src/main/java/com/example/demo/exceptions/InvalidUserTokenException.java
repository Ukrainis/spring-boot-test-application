package com.example.demo.exceptions;

public class InvalidUserTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUserTokenException() {
        super("Wrong jwt token for current user.");
    }
}
