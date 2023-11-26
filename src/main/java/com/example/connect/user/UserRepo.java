package com.example.connect.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByEmailIgnoreCase(String user);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String username);


}
