package com.example.concessionaria_campos.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.concessionaria_campos.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("concessionaria-auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("concessionaria-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Erro ao verificar token", exception);
        }
    }

    public void addTokenToCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
            .httpOnly(true)
            .path("/")
            .maxAge(60 * 60 * 8)// expira imediatamente
            .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    public void removeTokenCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("token", null)
            .httpOnly(true)
            .path("/")
            .maxAge(0)// expira imediatamente
            .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    private Instant getExpiration() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
