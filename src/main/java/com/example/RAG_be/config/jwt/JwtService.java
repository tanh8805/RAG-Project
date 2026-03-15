package com.example.RAG_be.config.jwt;

import com.example.RAG_be.config.security.CustomerUserDetails;
import com.example.RAG_be.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class JwtService {
  @Value("${jwt.secret}")
  private String JWT_SECRET;
  @Value("${jwt.expiration}")
  private long JWT_EXPIRATION;
  public String generateJwt(CustomerUserDetails user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getUser().getId());
    claims.put("role", user.getUser().getRole());
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
            .signWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .compact();
  }
  public String extractUsername(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(token).getBody().getSubject();
  }
  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
  private Date extractExpiration(String token) {
    return extractAllClaims(token).getExpiration();
  }
  public boolean isTokenExpired(String token) {
    try {
      return extractExpiration(token).before(new Date());
    } catch (Exception e) {
      return true;
    }
  }
  public String extractRole(String token) {
    return extractAllClaims(token).get("role").toString();
  }
  public String extractUserId(String token) {
    return extractAllClaims(token).get("userId").toString();
  }
}
