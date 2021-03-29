package kr.co.fastcampus.eatgore.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private Key key;

    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String userName) {
        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("userName", userName);

        return builder.signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}

//createToken : key (secretKey) for algorithm -> claims -> signatureAlgorithm
//getClaims : key setting -> token 이용해서 claims parsing -> getBody
