package com.example.connect.authentication;

import com.example.connect.shared.ResponseHandler;
import com.example.connect.shared.ResponseObject;
import com.example.connect.user.User;
import com.example.connect.user.UserService;
import com.example.connect.user.dto.CreateUserDto;
import com.example.connect.user.dto.VerifyUserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody CreateUserDto userData) {
        User user = userService.createUser(userData);

        return responseHandler.respond(HttpStatus.CREATED, "User registered successfully", user);
    }

    @GetMapping("/verify-user/{token}")
    public ResponseEntity<ResponseObject> verifyUser(@PathVariable("token") @NotBlank String token) {
        userService.verifyUser(token);

        return responseHandler.respond(HttpStatus.CREATED, "User verified successfully, login to continue", new HashMap<>());
    }
}
