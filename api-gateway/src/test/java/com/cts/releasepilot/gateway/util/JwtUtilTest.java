package com.cts.releasepilot.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String SECRET = "ReleasePilotSecretKey2024@CTS@JFS@Secure";
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", SECRET);
    }

    private String token(String subject, String role, long expiryMillisFromNow) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(subject)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiryMillisFromNow))
                .signWith(key)
                .compact();
    }

    @Test
    void validTokenIsAccepted() {
        String t = token("alice@cts.com", "Admin", 60_000);
        assertTrue(jwtUtil.isValid(t));
        assertEquals("alice@cts.com", jwtUtil.extractUsername(t));
        assertEquals("Admin", jwtUtil.extractRole(t));
    }

    @Test
    void expiredTokenIsRejected() {
        String t = token("bob@cts.com", "DevLead", -1_000);
        assertFalse(jwtUtil.isValid(t));
    }

    @Test
    void garbageTokenIsRejected() {
        assertFalse(jwtUtil.isValid("not-a-real-token"));
    }
}
