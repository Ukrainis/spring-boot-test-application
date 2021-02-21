package com.example.demo.exceptions;

public class EmptyFileException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmptyFileException() {
        super("Empty file provided. Please be sure you are pushing not empty file with .txt extension");
    }
}
