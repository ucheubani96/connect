package com.example.connect.userVerification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVerificationService {
    @Autowired
    private UserVerificationRepo userVerificationRepo;
}
