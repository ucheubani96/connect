package com.example.connect.userVerification;

import com.example.connect.exceptions.VerificationTokenNotCreated;
import com.example.connect.shared.RandomTokenGenerator;
import com.example.connect.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.UUID;

@Service
@Transactional
public class UserVerificationService {
    @Autowired
    private UserVerificationRepo userVerificationRepo;

    @Autowired
    private RandomTokenGenerator randomTokenGenerator;
    public UserVerification createVerificationToken (User user) throws RuntimeException {

        String token = UUID.randomUUID().toString();
        if (userVerificationRepo.existsByToken(token)) throw new VerificationTokenNotCreated("Token already exist");

        UserVerification userVerification = new UserVerification();
        userVerification.setUser_id(user.getId());
        userVerification.setToken(token);

        return userVerificationRepo.save(userVerification);
    }
}
