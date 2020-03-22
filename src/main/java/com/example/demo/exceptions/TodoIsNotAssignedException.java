package com.example.demo.exceptions;

public class TodoIsNotAssignedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TodoIsNotAssignedException() {
        super("Todo is not assigned to any user. Please assign it first.");
    }    
}