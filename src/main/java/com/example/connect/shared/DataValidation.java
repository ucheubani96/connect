package com.example.connect.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static boolean validateStringContainsOnlyNumbers (String data) {
        String regex = "[0-9]+";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }
}
