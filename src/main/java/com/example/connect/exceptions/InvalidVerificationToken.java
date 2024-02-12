package com.example.connect.exceptions;

public class InvalidVerificationToken extends RuntimeException{
    private static final long serialVersionUID = 1;

    public InvalidVerificationToken(String message) {
        super(message);
    }
    public InvalidVerificationToken() {
        super("Invalid verification token");
    }
}
