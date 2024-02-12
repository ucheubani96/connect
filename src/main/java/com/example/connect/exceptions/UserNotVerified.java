package com.example.connect.exceptions;

public class UserNotVerified extends RuntimeException{
    private static final long serialVersionUID = 1;

    public UserNotVerified(String message) {
        super(message);
    }
    public UserNotVerified() {
        super("User not verified.");
    }
}
