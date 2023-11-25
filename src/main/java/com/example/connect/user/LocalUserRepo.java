package com.example.connect.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalUserRepo extends JpaRepository<LocalUser, Long> {
    Optional<LocalUser> findByUsernameIgnoreCase(String username);
    Optional<LocalUser> findByEmailIgnoreCase(String user);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String username);


}
