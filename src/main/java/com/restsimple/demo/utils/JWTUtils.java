package com.restsimple.demo.utils;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTUtils {

    @Value("${validation.jwtKey}")
    private String SECRET_KEY;

    public static final long EXP_DURATION = 24 * 60 * 60 * 1000;

    public String generateStudentToken(String email){

        return Jwts.builder()
                .setSubject(String.format("%s", email))
                .setIssuedAt(new Date())
                .setExpiration(new Date())
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
