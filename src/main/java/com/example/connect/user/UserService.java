package com.example.connect.user;

import com.example.connect.encryption.EncryptionService;
import com.example.connect.exceptions.EntityNotFound;
import com.example.connect.exceptions.UserAlreadyExistException;
import com.example.connect.exceptions.UserNotVerified;
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

    public User create (CreateUserDto userData) throws RuntimeException {

        userData.password = encryptionService.encryptPassword(userData.password);

        return userRepo.save(userFactory.createUsingRegisterDTO(userData));
    }

    public User verify (String token) throws RuntimeException {

        Long userId = userVerificationService.decodeVerificationToken(token);

        Optional<User> user = userRepo.findById(userId);

        if (user.isEmpty()) throw new EntityNotFound("User not found");

        user.get().setVerified(true);

        return userRepo.save(user.get());
    }

    public User findByEmail (String email) throws RuntimeException {

        Optional<User> user = userRepo.findByEmailIgnoreCase(email);

        if (user.isEmpty()) throw new EntityNotFound("User not found");

        return user.get();
    }

    public User findUserById(Long id) throws RuntimeException {

        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty()) throw new EntityNotFound("User not found");

        return user.get();
    }

    public User save (User user) {
        return userRepo.save(user);
    }

    public void checkUserVerified (User user) throws RuntimeException {
        if (!user.isVerified()) throw new UserNotVerified();
    }

    public void checkUsernameUnique (String username) throws RuntimeException {
        if (userRepo.existsByUsernameIgnoreCase(username)) throw new UserAlreadyExistException("Username already Exist");
    }

    public void checkEmailUnique (String email) throws RuntimeException {
        if (userRepo.existsByEmailIgnoreCase(email)) throw new UserAlreadyExistException("Email already Exist");
    }

}
