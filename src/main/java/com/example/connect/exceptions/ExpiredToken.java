package com.example.connect.exceptions;

public class ExpiredToken extends RuntimeException{
    private static final long serialVersionUID = 1;
    public ExpiredToken(String message) {
        super(message);
    }
    public ExpiredToken() {
        super("Token already expired");
    }
}
