package com.example.server.common.jwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final SecretKey key;
    private final Long expiredMs;

    public JwtProvider(@Value("${jwt.secret}") String secret,
        @Value("${jwt.expiredMs}") Long expiredMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiredMs = expiredMs;
    }

    public String generateToken(Long memberId) {
        long now = System.currentTimeMillis();
        Date expired = new Date(now + expiredMs);
        return Jwts.builder()
            .issuedAt(new Date(now))
            .expiration(expired)
            .claim("member_id", memberId)
            .signWith(key)
            .compact();
    }

    public Long getMemberIdFromToken(String token) {
        Claims claims = Jwts.parser().verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        return Long.parseLong(claims.get("member_id").toString());
    }

}
