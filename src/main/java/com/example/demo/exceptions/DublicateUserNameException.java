package com.example.demo.exceptions;

public class DublicateUserNameException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public DublicateUserNameException (String userName) {
        super("User with current username allready exists. Username: " + userName);
    }
}