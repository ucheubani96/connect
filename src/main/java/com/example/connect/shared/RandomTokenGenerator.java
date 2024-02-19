package com.example.connect.shared;


import org.springframework.stereotype.Component;

@Component
public class RandomTokenGenerator {

    private static final int min = 10000;
    private static final int max = 99999;
    public static String generateAlphaNumericToken (int length) {
        return "";
    }

    public static int getIntToken () {
        return (int) (Math.random()*((max-min)+1))+min;
    }

}
