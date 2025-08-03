package com.debika.Employee.Attendance.Leave.Management.System.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import static java.security.KeyRep.Type.SECRET;

@Slf4j
@Service
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private String jwtExpirationMs;

    @PostConstruct
    public void init() {
        log.info("JWT Secret: {}", jwtSecret != null && !jwtSecret.isEmpty() ? "Loaded successfully" : "Missing!");
        log.info("JWT Expiration (ms): {}", jwtExpirationMs);
    }

    public String createToken(Map<String, Object> claims, String email) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationMs + 1000L);


        LocalDate expiryLocalDate = LocalDate.now().plusDays(Long.parseLong(jwtExpirationMs) / (1000 * 60 * 60 * 24));
        Date expiryDate = new Date(expiryLocalDate.toEpochDay() * 24 * 60 * 60 * 1000);
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(email)
                .signWith(getSignKey())
                .compact();
    }
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
