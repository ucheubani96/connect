package com.example.connect.auth;

import com.example.connect.auth.dto.LoginUserDTO;
import com.example.connect.shared.ResponseHandler;
import com.example.connect.shared.ResponseObject;
import com.example.connect.user.User;
import com.example.connect.user.UserService;
import com.example.connect.user.dto.CreateUserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody CreateUserDto userData) {
        User user = userService.createUser(userData);

        return responseHandler.respond(HttpStatus.CREATED, "User registered successfully", user);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody LoginUserDTO userData) {
        HashMap<String, String> token = new HashMap<>();
        token.put("token", authService.login(userData));

        return responseHandler.respond(HttpStatus.OK, "User logged in successfully", token);
    }

    @GetMapping("/verify-user/{token}")
    public ResponseEntity<ResponseObject> verifyUser(@PathVariable("token") @NotBlank String token) {
        userService.verifyUser(token);

        return responseHandler.respond(HttpStatus.OK, "User verified successfully, login to continue", new HashMap<>());
    }
}
