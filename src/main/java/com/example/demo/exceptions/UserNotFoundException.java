package com.example.demo.exceptions;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("User not found. Id: " + id);
    }

    public UserNotFoundException(String userName) {
        super("User not found. User name: " + userName);
    }
}