package com.example.connect.exceptions;

public class InvalidCredentials extends RuntimeException{
    private static final long serialVersionUID = 1;

    public InvalidCredentials(String message) {
        super(message);
    }
    public InvalidCredentials() {
        super("Invalid credentials");
    }
}
