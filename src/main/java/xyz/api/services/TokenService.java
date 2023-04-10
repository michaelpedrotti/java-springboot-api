package xyz.api.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import xyz.api.entities.AuthEntity;

@Service
public class TokenService {

    @Value("${app.security.token.secret}")
    private String secret;

    public String generate(AuthEntity entity){

        var alg = Algorithm.HMAC256(this.secret);

        return JWT.create()
            .withIssuer("auth0")
            .withSubject(Long.toString(entity.getId()))
            .withClaim("id", entity.getId())
            .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
            .sign(alg);

    }

    public String verify(String token){

        var alg = Algorithm.HMAC256(this.secret);

        return JWT.require(alg)
            .withIssuer("auth0")
            .build()
            .verify(token)
            .getSubject();
            // .getClaim("id")
            //     .toString();

    }
    
}
