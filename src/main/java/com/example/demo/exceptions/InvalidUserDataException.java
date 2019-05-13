package com.example.demo.exceptions;

public class InvalidUserDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUserDataException() {
        super("Incorrect user. Please validate if all mandatory data is filled.");
    }
}