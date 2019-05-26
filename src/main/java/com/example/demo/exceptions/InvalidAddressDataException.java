package com.example.demo.exceptions;

public class InvalidAddressDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidAddressDataException() {
        super("Incorrect address. Please validate if all mandatory data is filled.");
    }

}