package com.example.connect.exceptions;

public class EntityNotFound extends RuntimeException{
    private static final long serialVersionUID = 1;

    public EntityNotFound(String message) {
        super(message);
    }
}
