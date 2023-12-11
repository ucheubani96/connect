package com.example.connect.user;

import com.example.connect.encryption.EncryptionService;
import com.example.connect.exceptions.UserAlreadyExistException;
import com.example.connect.user.dto.CreateUserDto;
import com.example.connect.user.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserFactory userFactory;

    public User createUser(CreateUserDto userData) throws RuntimeException {

        if (userRepo.existsByUsernameIgnoreCase(userData.username)) { throw new UserAlreadyExistException("Username already Exist");}
        if (userRepo.existsByEmailIgnoreCase(userData.email)) { throw new UserAlreadyExistException("Email already Exist");}

        userData.password = encryptionService.encryptPassword(userData.password);

        return userRepo.save(userFactory.createUsingRegisterDTO(userData));
    }
}
