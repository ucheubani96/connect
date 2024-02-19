package com.example.connect.auth;

import com.example.connect.auth.dto.CompleteResetPasswordDTO;
import com.example.connect.auth.dto.LoginUserDTO;
import com.example.connect.auth.dto.ResetPasswordDTO;
import com.example.connect.encryption.EncryptionService;
import com.example.connect.encryption.JWTService;
import com.example.connect.exceptions.*;
import com.example.connect.resetPassword.ResetPassword;
import com.example.connect.resetPassword.ResetPasswordRepo;
import com.example.connect.resetPassword.ResetPasswordService;
import com.example.connect.shared.DataValidation;
import com.example.connect.shared.RandomTokenGenerator;
import com.example.connect.user.User;
import com.example.connect.user.UserRepo;
import com.example.connect.user.UserService;
import com.example.connect.user.dto.CreateUserDto;
import com.example.connect.userVerification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private ResetPasswordRepo resetPasswordRepo;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private UserRepo userRepo;


    public void register (CreateUserDto userDto) throws RuntimeException {

        userService.checkEmailUnique(userDto.email);

        userService.checkUsernameUnique(userDto.username);

        User user = userService.create(userDto);

        String userVerification = userVerificationService.createVerificationToken(user.getId());

//        SEND EMAIL
    }

    public String login (LoginUserDTO userData) throws RuntimeException {
        User user = userService.findByEmail(userData.email);

        userService.checkUserVerified(user);

        if (!encryptionService.verifyPassword(userData.password, user.getPassword())) throw new InvalidCredentials();

        return jwtService.generateLoginJWT(user);
    }

    public void resetPassword (ResetPasswordDTO resetData) throws RuntimeException {

        User user = userService.findByEmail(resetData.email);

        userService.checkUserVerified(user);

        resetPasswordService.create(user, RandomTokenGenerator.getIntToken());
    }

    public void completeResetPassword (CompleteResetPasswordDTO passwordResetData, String token) throws RuntimeException {

        this.comparePasswords(passwordResetData.password, passwordResetData.retypePassword);

        ResetPassword resetPassword = resetPasswordService.findOneByToken(this.validateAndTransformStringToken(token));

        this.checkTokenExpiry(resetPassword.getLastModifiedDate());

        resetPassword.getUser().setPassword(encryptionService.encryptPassword(passwordResetData.password));

        userService.save(resetPassword.getUser());
    }

    private void comparePasswords (String firstPassword, String secondPassword) throws RuntimeException {
        if (!firstPassword.equals(secondPassword)) throw new EntityNotEqual("Passwords do not match");
    }

    private int validateAndTransformStringToken (String token) throws RuntimeException {
        if (!DataValidation.validateStringContainsOnlyNumbers(token)) throw new InvalidEntity("Token must be a non-negative number");

        return Integer.parseInt(token);
    }

    private void checkTokenExpiry (Date lastModifiedAt) throws RuntimeException {

        Date now = new Date(System.currentTimeMillis());

        if (!now.before(new Date(lastModifiedAt.getTime() + (1000 * 300))))
            throw new ExpiredToken("Password reset link's expired. Request a new password reset link");
    }

}
