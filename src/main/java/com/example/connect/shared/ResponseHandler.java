package com.example.connect.shared;

import com.example.connect.user.User;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHandler {
    public <T> ResponseEntity<ResponseObject> respond(HttpStatus statusCode, String message, T data) {
        ResponseObject responseObject = new ResponseObject();

        responseObject.setStatusCode(statusCode.value());
        responseObject.setMessage(message);
        responseObject.setData(data);

        return new ResponseEntity<>(responseObject, statusCode);
    }
}
