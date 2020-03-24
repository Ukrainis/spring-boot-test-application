package com.example.demo.exceptions;

public class NotCompletedUserDataException extends RuntimeException {

   private static final long serialVersionUID = 1L;
   
   public NotCompletedUserDataException(String userName) {
       super("Not all data for User: " + userName + " is complete.");
   }
}