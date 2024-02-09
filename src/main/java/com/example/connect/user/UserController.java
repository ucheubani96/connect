package com.example.connect.user;

import com.example.connect.shared.ResponseHandler;
import com.example.connect.shared.ResponseObject;
import com.example.connect.user.dto.CreateUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody CreateUserDto userData) {
            User user = userService.createUser(userData);

            return responseHandler.respond(HttpStatus.CREATED, "User registered successfully", user);
    }
}
