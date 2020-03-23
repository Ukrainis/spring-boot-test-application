package com.example.demo.exceptions;

import com.example.demo.enums.Exceptions;

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
        return new CustomExceptionResponse(Exceptions.InvalidUserDataException, error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomExceptionResponse userNotFoundHandler(UserNotFoundException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.UserNotFoundException, error);
    }

    @ExceptionHandler(InvalidCompanyDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidCompanyHandler(InvalidCompanyDataException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidCompanyDataException, error);
    }

    @ExceptionHandler(InvalidAddressDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidAddressHandler(InvalidAddressDataException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidAddressDataException, error);
    }

    @ExceptionHandler(MissingAddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse missingAddressHandler(MissingAddressException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.MissingAddressException, error);
    }

    @ExceptionHandler(InvaliGeoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidGeoHandler(InvaliGeoException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidGeoDataException, error);
    }

    @ExceptionHandler(DublicateUserNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse dublicatedUserNameHandler(DublicateUserNameException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.DublicateUserNameException, error);
    }

    @ExceptionHandler(InvalidTodoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidTodoHandler(InvalidTodoException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidTodoException, error);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse missingTodoEntityHandler(TodoNotFoundException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.TodoNotFoundException, error);
    }

    @ExceptionHandler(InvalidTodoStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidTodoStatusHandler(InvalidTodoStatusException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidTodoStatusException, error);
    }

    @ExceptionHandler(TodoIsNotAssignedException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ResponseBody
    public CustomExceptionResponse invalidTodoStatusHandler(TodoIsNotAssignedException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidTodoStatusException, error);
    }

    @ExceptionHandler(WrongTodoStatusException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ResponseBody
    public CustomExceptionResponse wrongTodoStatusHandler(WrongTodoStatusException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.WrongTodoStatusException, error);
    }
}