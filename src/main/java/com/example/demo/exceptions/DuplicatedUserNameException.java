package com.example.demo.exceptions;

public class DuplicatedUserNameException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public DuplicatedUserNameException(String userName) {
        super("User with current username allready exists. Username: " + userName);
    }
}