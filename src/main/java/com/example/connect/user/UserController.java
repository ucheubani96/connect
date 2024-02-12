package com.example.connect.user;

import com.example.connect.shared.ResponseHandler;
import com.example.connect.shared.ResponseObject;
import com.example.connect.user.dto.CreateUserDto;
import com.example.connect.user.dto.VerifyUserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResponseHandler responseHandler;

}
