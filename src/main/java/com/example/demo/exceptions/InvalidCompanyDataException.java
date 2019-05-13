package com.example.demo.exceptions;

public class InvalidCompanyDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidCompanyDataException() {
        super("Incorrect company. Please validate if all mandatory data is filled.");
    }

}