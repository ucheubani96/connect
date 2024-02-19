package com.example.connect.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorObject> handleUserAlreadyExistException (UserAlreadyExistException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VerificationTokenNotCreated.class)
    public ResponseEntity<ErrorObject> handleUserVerificationTokenNotCreated (VerificationTokenNotCreated e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ErrorObject> handleEntityNotFound (EntityNotFound e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotEqual.class)
    public ResponseEntity<ErrorObject> handleEntityNotEqual (EntityNotFound e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidVerificationToken.class, InvalidToken.class, ExpiredToken.class})
    public ResponseEntity<ErrorObject> handleInvalidOrExpiredToken (RuntimeException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEntity.class)
    public ResponseEntity<ErrorObject> handleInvalidEntity (RuntimeException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JWTVerificationException.class, ExpiredJWT.class})
    public ResponseEntity<ErrorObject> handleInvalidJWT (RuntimeException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        if (e.getClass() == ExpiredJWT.class) errorObject.setMessage(e.getMessage());
        else errorObject.setMessage("Invalid token");

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidCredentials.class, UserNotVerified.class})
    public ResponseEntity<ErrorObject> handleInvalidCredentials (RuntimeException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorObject> handleInvalidPathVariableType (MethodArgumentTypeMismatchException e, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(e.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleRequestDataValidationException (MethodArgumentNotValidException e, WebRequest request) {

        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

}
