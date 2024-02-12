package com.example.connect.encryption;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.connect.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    private static final String USERNAME_KEY = "USERNAME";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateLoginJWT(User user) {
        return JWT.create()
                .withClaim(USERNAME_KEY, user.getUsername())
                .withClaim("s", "s")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateUserVerificationJWT(Long userId) {
        return JWT.create()
                .withClaim("userId", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public DecodedJWT decodeJwt (String token) throws RuntimeException {
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        if (decodedJWT.getExpiresAt().before(new Date())) throw new JWTDecodeException("Token already expired");//InvalidJWT("Token already expired");

        return JWT.decode(token);
    }

//    public verifyJwt ()

    public String getUsername(String token) {
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
    }
}
