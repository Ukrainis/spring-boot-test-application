package com.example.demo.exceptions;

public class WrongFileExtensionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WrongFileExtensionException() {
        super("File should have .txt extension.");
    }
}
