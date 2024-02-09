package com.example.connect.user.factory;

import com.example.connect.user.User;
import com.example.connect.user.dto.CreateUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUsingRegisterDTO (CreateUserDto userData) {
        User user = new User();

        user.setUsername(userData.username);
        user.setEmail(userData.email);
        user.setPassword(userData.password);
        user.setDp(userData.dp);

        return user;
    }
}
