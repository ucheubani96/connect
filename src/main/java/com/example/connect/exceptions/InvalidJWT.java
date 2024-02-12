package com.example.connect.exceptions;

public class InvalidJWT extends RuntimeException{
    private static final long serialVersionUID = 1;

    public InvalidJWT(String message) {
        super(message);
    }
    public InvalidJWT() {
        super("Invalid token");
    }
}
