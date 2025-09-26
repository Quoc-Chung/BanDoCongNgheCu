package com.quocchung.webbando.bandocongnghe.config;

import com.quocchung.webbando.bandocongnghe.entity.Role;
import com.quocchung.webbando.bandocongnghe.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final JwtConfig jwtConfig;

  public String generateToken(User user) {
    Instant now = Instant.now();
    Instant expiry = now.plusMillis(jwtConfig.getExpirationTime());

    String roles = user.getRoles().stream()
        .map(role -> role.getCode().name())
        .collect(Collectors.joining(","));

    return Jwts.builder()
        .setSubject(user.getId().toString())
        .claim("username", user.getUsername())
        .claim("email", user.getEmail())
        .claim("roles", roles)
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(expiry))
        .signWith(jwtConfig.getPrivateKey(), SignatureAlgorithm.RS256)
        .compact();
  }

  public Long getUserIdFromToken(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(jwtConfig.getPublicKey())
        .build()
        .parseClaimsJws(token)
        .getBody();

    return Long.parseLong(claims.getSubject());
  }

  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(jwtConfig.getPublicKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    return claims.get("email", String.class);
  }


  /**
   * Kiểm tra token có hợp lệ không
   * @param token
   * @return
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(jwtConfig.getPublicKey())
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}
