package com.example.connect.auth;

import com.example.connect.auth.dto.LoginUserDTO;
import com.example.connect.encryption.EncryptionService;
import com.example.connect.encryption.JWTService;
import com.example.connect.exceptions.EntityNotFound;
import com.example.connect.exceptions.InvalidCredentials;
import com.example.connect.exceptions.UserAlreadyExistException;
import com.example.connect.user.User;
import com.example.connect.user.UserRepo;
import com.example.connect.user.UserService;
import com.example.connect.user.dto.CreateUserDto;
import com.example.connect.user.factory.UserFactory;
import com.example.connect.userVerification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserVerificationService userVerificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;



    public String login (LoginUserDTO userData) {
        User user = userService.findUserByEmail(userData.email);

        userService.checkUserVerified(user);

        if (!encryptionService.verifyPassword(userData.password, user.getPassword())) throw new InvalidCredentials();

        return jwtService.generateLoginJWT(user);
    }

}
