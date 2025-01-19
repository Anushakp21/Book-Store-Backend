package com.example.Book.Store.Application.security;

import com.example.Book.Store.Application.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class Util {
    public static final String SECRET_KEY ;
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;//1 day

    static
    {
        byte[] keyBytes = new byte[64];  // 64 bytes = 512 bits
        new SecureRandom().nextBytes(keyBytes);  // Generate secure random key
        SECRET_KEY = Base64.getEncoder().encodeToString(keyBytes); // Base64 encode the key
    }

    // Generate a symmetric key from the secret
    private SecretKey getSigningKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey); // Return the key for HS512
    }
   public String createToken(User user)
   {
     String username=user.getEmail();
     Long userId=user.getUserId();
     return Jwts
             .builder()
             .subject(username)
             .claim("userId", userId)
             .issuedAt(new Date(System.currentTimeMillis()))
             .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
             .signWith(getSigningKey())
             .compact();
   }

    public String extractUsernameFromToken(String token) {
        return extractClaim(token,claim->claim.getSubject());
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateJwtToken(String token, UserDetails userDetails) {
        String username = extractUsernameFromToken(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, claim->claim.getExpiration());
    }

    public long extractUserIdFromToken(String token) {
        return extractClaim(token, claim -> claim.get("userId",Long.class));
    }
}
