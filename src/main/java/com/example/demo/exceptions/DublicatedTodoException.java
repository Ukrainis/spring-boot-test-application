package com.example.demo.exceptions;

public class DublicatedTodoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DublicatedTodoException(String todoTitle) {
        super("This todo already exists: " + todoTitle);
    }    
}