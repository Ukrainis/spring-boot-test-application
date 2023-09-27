package com.example.demo.exceptions;

import com.example.demo.enums.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomExceptionsAdvice {

    private final String DEFAULT_MESSAGE = "Please see errors below.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidUserHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            FieldError fError = (FieldError) error;
            String fieldName = fError.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new CustomExceptionResponse(Exceptions.InvalidUserDataException, DEFAULT_MESSAGE, errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomExceptionResponse userNotFoundHandler(UserNotFoundException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.UserNotFoundException, error, null);
    }

    /*
     * @ExceptionHandler(InvalidCompanyDataException.class)
     * 
     * @ResponseStatus(HttpStatus.BAD_REQUEST)
     * 
     * @ResponseBody
     * public CustomExceptionResponse
     * invalidCompanyHandler(InvalidCompanyDataException ex) {
     * String error = ex.getMessage();
     * return new CustomExceptionResponse(Exceptions.InvalidCompanyDataException,
     * error, null);
     * }
     * 
     * @ExceptionHandler(InvalidAddressDataException.class)
     * 
     * @ResponseStatus(HttpStatus.BAD_REQUEST)
     * 
     * @ResponseBody
     * public CustomExceptionResponse
     * invalidAddressHandler(InvalidAddressDataException ex) {
     * String error = ex.getMessage();
     * return new CustomExceptionResponse(Exceptions.InvalidAddressDataException,
     * error, null);
     * }
     */

    @ExceptionHandler(MissingAddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse missingAddressHandler(MissingAddressException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.MissingAddressException, error, null);
    }

    /*
     * @ExceptionHandler(InvaliGeoException.class)
     * 
     * @ResponseStatus(HttpStatus.BAD_REQUEST)
     * 
     * @ResponseBody
     * public CustomExceptionResponse invalidGeoHandler(InvaliGeoException ex) {
     * String error = ex.getMessage();
     * return new CustomExceptionResponse(Exceptions.InvalidGeoDataException, error,
     * null);
     * }
     */

    @ExceptionHandler(DuplicatedUserNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse dublicatedUserNameHandler(DuplicatedUserNameException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.DublicateUserNameException, error, null);
    }

    /*
     * @ExceptionHandler(InvalidTodoException.class)
     * 
     * @ResponseStatus(HttpStatus.BAD_REQUEST)
     * 
     * @ResponseBody
     * public CustomExceptionResponse invalidTodoHandler(InvalidTodoException ex) {
     * String error = ex.getMessage();
     * return new CustomExceptionResponse(Exceptions.InvalidTodoException, error,
     * null);
     * }
     */

    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse missingTodoEntityHandler(TodoNotFoundException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.TodoNotFoundException, error, null);
    }

    @ExceptionHandler(InvalidTodoStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidTodoStatusHandler(InvalidTodoStatusException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidTodoStatusException, error, null);
    }

    @ExceptionHandler(TodoIsNotAssignedException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ResponseBody
    public CustomExceptionResponse invalidTodoStatusHandler(TodoIsNotAssignedException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.TodoIsNotAssignedException, error, null);
    }

    @ExceptionHandler(WrongTodoStatusException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ResponseBody
    public CustomExceptionResponse wrongTodoStatusHandler(WrongTodoStatusException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.WrongTodoStatusException, error, null);
    }

    @ExceptionHandler(NotCompletedUserDataException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ResponseBody
    public CustomExceptionResponse notCompletedUserDataHandler(NotCompletedUserDataException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.NotCompletedUserDataException, error, null);
    }

    @ExceptionHandler(DuplicatedTodoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse duplicatedTodoHandler(DuplicatedTodoException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.DuplicatedTodoException, error, null);
    }

    @ExceptionHandler(EmptyFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse emptyFileHandler(EmptyFileException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.EmptyFileException, error, null);
    }

    @ExceptionHandler(WrongFileExtensionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse wrongFileExtensionHandler(WrongFileExtensionException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.WrongFileExtensionException, error, null);
    }

    @ExceptionHandler(TooBigFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse tooBigFileExceptionHandler(TooBigFileException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.TooBifFileException, error, null);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public CustomExceptionResponse tokenNotFoundExceptionHandler(TokenNotFoundException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.TokenNotFoundException, error, null);
    }

    @ExceptionHandler(InvalidUserTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public CustomExceptionResponse invalidUserTokenExceptionHandler(InvalidUserTokenException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidUserTokenException, error, null);
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomExceptionResponse invalidJwtTokenExceptionHandler(InvalidJwtTokenException ex) {
        String error = ex.getMessage();
        return new CustomExceptionResponse(Exceptions.InvalidJwtTokenException, error, null);
    }
}
