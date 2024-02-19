package com.example.connect.exceptions;

public class InvalidEntity extends RuntimeException{
    private static final long serialVersionUID = 1;

    public InvalidEntity(String message) {
        super(message);
    }
}
