package com.BrennoDS.cadastro.security;

import com.BrennoDS.cadastro.Application;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.BrennoDS.cadastro.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class TokenConfig {
    private final Application application;
    private String secret = "secret";

    TokenConfig(Application application) {
        this.application = application;
    }

    public String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
               .withClaim("userId", user.getId())
               .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withIssuedAt(Instant.now())
                .sign(algorithm);

    }

    public Optional<JWTUserData> validateToken(String token) {

        try{

            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decoder = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decoder.getClaim("userId").asLong())
                    .email(decoder.getSubject())
                    .build());

        }catch(JWTVerificationException e){
            return Optional.empty();
        }

    }
}
