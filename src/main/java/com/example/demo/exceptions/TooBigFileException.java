package com.example.demo.exceptions;

public class TooBigFileException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public TooBigFileException() {
        super("File should have size less or equal to 5000 bytes.");
    }
}
