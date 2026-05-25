package com.carrentalsystem.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;

    private Key key;

    @PostConstruct
    public void init() {
        // Base64-encode the secret so it always meets the 256-bit minimum requirement
        String base64Secret = Base64.getEncoder().encodeToString(SECRET.getBytes());
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));
    }

    // Generate Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Email
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate Token
    public boolean validateToken(String token, String email) {
    	 try {
             String extracted = extractEmail(token);
             return extracted.equals(email);
         } 
    	 catch (Exception e) {
             System.out.println("JWT ERROR: " + e.getMessage()); // 👈 debug
             return false;
         }
       
    }
}