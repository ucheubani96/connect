package com.example.connect.user;

import com.example.connect.encryption.EncryptionService;
import com.example.connect.exceptions.EntityNotFound;
import com.example.connect.exceptions.UserAlreadyExistException;
import com.example.connect.user.dto.CreateUserDto;
import com.example.connect.user.factory.UserFactory;
import com.example.connect.userVerification.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        String userVerification = userVerificationService.createVerificationToken(user.getId());

//        SEND EMAIL TO USER

        return user;
    }

    public User verifyUser(String token) throws RuntimeException {

        Long userId = userVerificationService.decodeVerificationToken(token);

        Optional<User> user = userRepo.findById(userId);

        if (user.isEmpty()) throw new EntityNotFound("User not found");

        user.get().setVerified(true);

        return userRepo.save(user.get());
    }

}
