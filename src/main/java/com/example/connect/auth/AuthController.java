package com.example.connect.auth;

import com.example.connect.auth.dto.CompleteResetPasswordDTO;
import com.example.connect.auth.dto.LoginUserDTO;
import com.example.connect.auth.dto.ResetPasswordDTO;
import com.example.connect.shared.ResponseHandler;
import com.example.connect.shared.ResponseObject;
import com.example.connect.user.User;
import com.example.connect.user.UserService;
import com.example.connect.user.dto.CreateUserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody CreateUserDto userData) {
        User user = userService.create(userData);

        return responseHandler.respond(HttpStatus.CREATED, "User registered successfully", user);
    }

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@Valid @RequestBody LoginUserDTO userData) {
        HashMap<String, String> token = new HashMap<>();
        token.put("token", authService.login(userData));

        return responseHandler.respond(HttpStatus.OK, "User logged in successfully", token);
    }

    @GetMapping("/verify-user/{token}")
    public ResponseEntity<ResponseObject> verifyUser(@PathVariable("token") @NotBlank String token) {
        userService.verify(token);

        return responseHandler.respond(HttpStatus.OK, "User verified successfully, login to continue", new HashMap<>());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseObject> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordData) {
        authService.resetPassword(resetPasswordData);

        return responseHandler.respond(HttpStatus.OK, "Password reset successfully. Check email for link.", new HashMap<>());
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<ResponseObject> completeResetPassword(@PathVariable("token") @NotBlank String token, @Valid @RequestBody CompleteResetPasswordDTO resetPasswordData) {
        authService.completeResetPassword(resetPasswordData, token);

        return responseHandler.respond(HttpStatus.OK, "Password reset successfully. Check email for link.", new HashMap<>());
    }
}
