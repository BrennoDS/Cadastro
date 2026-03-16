package com.BrennoDS.cadastro.security;

import com.BrennoDS.cadastro.Application;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.BrennoDS.cadastro.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

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
}
