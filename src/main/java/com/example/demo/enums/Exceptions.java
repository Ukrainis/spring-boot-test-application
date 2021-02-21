package com.example.demo.enums;

import com.example.demo.exceptions.EmptyFileException;

public enum Exceptions {
    DublicateUserNameException, 
    InvalidAddressDataException, 
    MissingAddressException, 
    InvalidGeoDataException, 
    InvalidCompanyDataException, 
    InvalidUserDataException,
    UserNotFoundException,
    InvalidTodoException,
    TodoNotFoundException,
    InvalidTodoStatusException,
    TodoIsNotAssignedException,
    WrongTodoStatusException,
    NotCompletedUserDataException,
    DuplicatedTodoException,
    EmptyFileException,
    WrongFileExtensionException,
    TooBifFileException;
}