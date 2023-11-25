package com.example.connect.userVerification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserVerificationRepo extends JpaRepository<UserVerification, Long> {
    Optional<UserVerification> findByUser_id(Long userId);
    Optional<UserVerification> findByToken(String token);
}
