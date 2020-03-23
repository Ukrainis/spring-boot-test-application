package com.example.demo.exceptions;

public class WrongTodoStatusException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WrongTodoStatusException(String message) {
        super(message);
    }
    
}