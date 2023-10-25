package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.vo.TokenData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "f5464993e8c348133d255ff25dd9fdc2843decae309c9321dfc6cec0cb375fe3";

    public TokenData generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public TokenData generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
        final Date expirationDate = new Date(System.currentTimeMillis() + (1000 * 60 * 60));
        final String accessToken = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setClaims(extraClaims)
                .setExpiration(expirationDate)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        return TokenData.builder()
                .accessToken(accessToken)
                .expiration(expirationDate)
                .build();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }
}
