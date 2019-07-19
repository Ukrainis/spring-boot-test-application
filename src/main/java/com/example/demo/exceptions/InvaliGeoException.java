package com.example.demo.exceptions;

public class InvaliGeoException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public InvaliGeoException() {
        super("Incorrect Geo coordinates. Please validate if all mandatory data is filled.");
    }
}