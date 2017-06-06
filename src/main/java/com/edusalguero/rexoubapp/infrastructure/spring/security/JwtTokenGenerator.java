package com.edusalguero.rexoubapp.infrastructure.spring.security;

import com.edusalguero.rexoubapp.application.auth.TokenGenerator;
import com.edusalguero.rexoubapp.domain.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGenerator implements TokenGenerator {
    @Override
    public String generateToken(User user, String secret) {
        Claims claims = Jwts.claims().setSubject(user.username());
        claims.put("userId", user.id() + "");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.ES256.HS512, secret)
                .compact();
    }
}
