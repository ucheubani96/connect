package com.example.connect.exceptions;

public class EntityNotEqual extends RuntimeException{
    private static final long serialVersionUID = 1;
    public EntityNotEqual(String message) {
        super(message);
    }
}
