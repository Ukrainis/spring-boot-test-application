package com.example.demo.exceptions;

public class InvalidTodoStatusException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTodoStatusException(String todoStatus) {
        super("Invalid status for TODO: " + todoStatus + ". Should be In progress or Done");
    }
}