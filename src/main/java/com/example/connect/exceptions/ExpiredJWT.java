package com.example.connect.exceptions;

public class ExpiredJWT extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ExpiredJWT(String message) {
        super(message);
    }
    public ExpiredJWT() {
        super("Token already expired");
    }
}
