package com.BookHive.backend.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Δημιουργία μυστικού κλειδιού

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Το username του χρήστη
                .setIssuedAt(new Date()) // Ημερομηνία δημιουργίας
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Λήξη μετά από 10 ώρες
                .signWith(secretKey) // Υπογραφή με το μυστικό κλειδί
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true; // Το token είναι έγκυρο
        } catch (Exception e) {
            return false; // Το token είναι άκυρο
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Εξαγωγή του username
    }

    public boolean isTokenValid(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
    
}
