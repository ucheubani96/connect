package com.example.connect.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public UserAlreadyExistException (String message) {
        super(message);
    }
}
