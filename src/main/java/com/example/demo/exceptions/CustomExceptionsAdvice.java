package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomExceptionsAdvice {

    @ExceptionHandler(InvalidUserDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidUserHandler(InvalidUserDataException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomExceptionResponse userNotFoundHandler(UserNotFoundException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(error);
    }

    @ExceptionHandler(InvalidCompanyDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidCompanyHandler(InvalidCompanyDataException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(error);
    }

    @ExceptionHandler(InvalidAddressDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidAddressHandler(InvalidAddressDataException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(error);
    }
}