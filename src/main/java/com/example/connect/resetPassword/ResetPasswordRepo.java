package com.example.connect.resetPassword;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordRepo extends JpaRepository<ResetPassword, Long> {
    Optional<ResetPassword> findByToken(Integer token);

    Optional<ResetPassword> findByUser_Id(Long id);



}
