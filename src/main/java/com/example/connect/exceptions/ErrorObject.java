package com.example.connect.exceptions;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timeStamp;
}
