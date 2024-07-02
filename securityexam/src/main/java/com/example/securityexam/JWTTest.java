package com.example.securityexam;

import com.example.securityexam.security.jwt.utill.JwtTokenizer;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class JWTTest {
    public static void main(String[] args) {
        JwtTokenizer jwtTokenizer = new JwtTokenizer(
                "12345678901234567890123456789012",
                "12345678901234567890123456789012");
        // AccessToken 생성 예시
        String accessToken = jwtTokenizer.createAccessToken(
                1L,
                "test@example.com",
                "Test User",
                "testuser",
                Arrays.asList("ROLE_USER", "ROLE_ADMIN")
        );
        log.info("Generated Access Token: {}", accessToken);
        String refreshToken = jwtTokenizer.createRefreshToken(
                1L,
                "test@example.com",
                "Test User",
                "testuser",
                Arrays.asList("ROLE_USER", "ROLE_ADMIN")
        );
        log.info("Generated Refresh Token: {}", refreshToken);
        // AccessToken 파싱 예시
        Claims accessTokenClaims = jwtTokenizer.parseAccessToken(accessToken);
        log.info("Access Token Claims: {}", accessTokenClaims);
        Long userIdFromAccessToken = jwtTokenizer.getUserIdFromToken("Baerer: " + accessToken);
        log.info("User ID from Access Token: {}", userIdFromAccessToken);
        // RefreshToken 파싱 예시
        Claims refreshTokenClaims = jwtTokenizer.parseRefreshToken(refreshToken);
        log.info("Refresh Token Claims: {}", refreshTokenClaims);
    }
}
