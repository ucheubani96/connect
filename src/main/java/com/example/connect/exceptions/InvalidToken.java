package com.example.connect.exceptions;

public class InvalidToken extends RuntimeException{
    private static final long serialVersionUID = 1;

    public InvalidToken(String message) {
        super(message);
    }
    public InvalidToken() {
        super("Invalid token");
    }
}
