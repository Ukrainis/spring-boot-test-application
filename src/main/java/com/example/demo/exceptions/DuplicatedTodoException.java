package com.example.demo.exceptions;

public class DuplicatedTodoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DuplicatedTodoException(String todoTitle) {
        super("This todo already exists: " + todoTitle);
    }    
}