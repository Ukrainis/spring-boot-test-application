package com.example.demo.exceptions;

public class MissingAddressException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MissingAddressException(String userName){
        super("Address for current user: " + userName + " is not defined.");
    }
}