package kr.co.fastcampus.eatgore.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        this.jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void JWT_토큰_생성_테스트() {
        String token = jwtUtil.createToken(1004L, "tester");
        assertThat(token).contains(".");
    }

    @Test
    void JWT_getClaimsTest() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsInVzZXJOYW1lIjoidGVzdGVyIn0.Q-dpPeV_d4nIHh_kyKkF6X8AnGyXHBsZ2fKo_4cDwNc";

        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("userId", Long.class)).isEqualTo(1004L);
        assertThat(claims.get("userName", String.class)).isEqualTo("tester");
    }
}