package com.netflix.clone;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private String secret = "mysecretkey";

    public String generateToken(String email) {
        
        return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 1))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact(); 
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    

}
