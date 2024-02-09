package com.example.connect.exceptions;

public class VerificationTokenNotCreated extends RuntimeException{
    private static final long serialVersionUID = 1;

    public VerificationTokenNotCreated(String message) {
        super(message);
    }
}
