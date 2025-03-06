package com.example.tree.User.Dto;

import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureException;
import javax.crypto.SecretKey;
import javax.xml.crypto.dsig.Transform;

import java.util.Date;

@Component
public class JwtProvider {
    // 에러 로깅을 위해 로거 준비
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    // JWT secret을 저장할 변수
    private final SecretKey secretKey;

    // 토큰 만료 시간을 저장할 변수
    private final Long expirationInMilliseconds;

    // 생성자 함수
    public JwtProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration-time}") Long expirationInMilliseconds) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationInMilliseconds = expirationInMilliseconds;
    }

    // 토큰을 만들어 내는 함수
    public String createToken(String payload) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + this.expirationInMilliseconds);
        Claims claims = Jwts.claims()
                .setSubject(payload)       // "sub": "abc@gmail.com"
                .setIssuedAt(now)          // "iat": 1516239022
                .setExpiration(expiration);// "exp": 1516249022
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    // 유효한 토큰인지 검증하는 함수
    public Boolean isValidToken(String token) {
        try {
            Claims claims = parseToken(token);
            return claims != null; // null이 아니면 유효한 토큰
        } catch (JwtException e) {
            logger.error("Invalid JWT token: " + e.getMessage(), e);
            return false;
        }
    }

    // 토큰에서 로그인한 사용자의 userId를 추출하는 함수
    public String getSubject(String token) {
        Claims claims = parseToken(token);
        return (claims != null) ? claims.getSubject() : null;  // null 처리
    }

    // 유효한 토큰의 데이터를 읽는 함수
    private Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.error("Token expired", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token", e);
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty", e);
        } catch (JwtException e) {
            logger.error("Failed to parse JWT token", e); // 추가적인 예외 처리
        }
        return null;  // 예외 발생 시 null 반환
    }
}