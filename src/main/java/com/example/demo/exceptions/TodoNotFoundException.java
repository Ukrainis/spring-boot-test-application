package com.example.demo.exceptions;

public class TodoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TodoNotFoundException(Long todoId) {
        super("Todo with this id: " + todoId + " not found.");
    }
}