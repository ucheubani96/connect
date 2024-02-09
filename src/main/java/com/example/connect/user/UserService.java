package com.example.connect.user;

import com.example.connect.encryption.EncryptionService;
import com.example.connect.exceptions.UserAlreadyExistException;
import com.example.connect.user.dto.CreateUserDto;
import com.example.connect.user.factory.UserFactory;
import com.example.connect.userVerification.UserVerification;
import com.example.connect.userVerification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserVerificationService userVerificationService;

    @Autowired
    private UserFactory userFactory;

    public User createUser(CreateUserDto userData) throws RuntimeException {
        if (userRepo.existsByUsernameIgnoreCase(userData.username)) throw new UserAlreadyExistException("Username already Exist");
        if (userRepo.existsByEmailIgnoreCase(userData.email)) throw new UserAlreadyExistException("Email already Exist");

        userData.password = encryptionService.encryptPassword(userData.password);
        User user = userRepo.save(userFactory.createUsingRegisterDTO(userData));

        UserVerification userVerification = userVerificationService.createVerificationToken(user);

//        SEND EMAIL TO USER

        return user;
    }

}
