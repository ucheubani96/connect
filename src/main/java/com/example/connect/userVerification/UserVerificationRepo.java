package com.example.connect.userVerification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserVerificationRepo extends JpaRepository<UserVerification, Long> {
    boolean existsByToken(@NonNull String token);


    @Query("select uv from UserVerification uv where uv.user_id = ?1 ")
    Optional<UserVerification> findByUser_id(Long userId);

    @Query("select uv from UserVerification uv where uv.token = ?1 ")
    Optional<UserVerification> findByToken(String token);
}
