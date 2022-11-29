package com.example.demo.exceptions;

import java.util.Map;

public class InvalidUserDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUserDataException(Map<String, String> errorData) {
        super("Incorrect user request. One or more of the following fields is empty, missing incorrectly filled: \n"
                + errorData.toString());
    }
}