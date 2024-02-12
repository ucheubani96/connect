package com.example.connect.userVerification;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.connect.encryption.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserVerificationService {
    @Autowired
    private JWTService jwtService;

    public String createVerificationToken (Long userId) throws RuntimeException {
        String token = jwtService.generateUserVerificationJWT(userId);

        System.out.println(token);

        return token;
    }

    public Long decodeVerificationToken (String token) throws RuntimeException {

        DecodedJWT decodedJWT = jwtService.decodeJwt(token);

        return decodedJWT.getClaim("userId").asLong();
    }
}
