package br.com.bookly.services.impl;

import br.com.bookly.entities.Users;
import br.com.bookly.exceptions.InvalidTokenException;
import br.com.bookly.exceptions.TokenGenerationException;
import br.com.bookly.services.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${api.token.secret}")
    private String tokenSecret;

    @Override
    public String generateToken(Users user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.create()
                    .withIssuer("bookly") // dono da aplicação
                    .withSubject(user.getEmail()) // identificador do user
                    .withExpiresAt( // configs de expiração do token
                            LocalDateTime.now().plusHours(2).toInstant(
                                    ZoneOffset.of("-03:00")
                            )
                    )
                    .sign(algorithm);
        } catch (Exception e){
            throw new TokenGenerationException("Error generating authentication token", e);
        }
    }

    @Override
    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.require(algorithm)
                    .withIssuer("bookly")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new InvalidTokenException("Invalid or expired token");
        }
    }
}
