package com.example.demo.exceptions;

public class InvalidTodoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTodoException() {
        super("Invalid Todo entity. Please validate if title is filled.");
    }
}