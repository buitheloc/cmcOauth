package com.cmc.demo.oauth.security.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
  @Value("${jwtSecret}")
  private String jwtSecret;
  Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

  public String createTokenByAuthentication(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return createTokenByUserName(userDetails.getUsername());
  }

  public String createTokenByUserName(String userName) {
    return Jwts.builder()
            .setSubject(userName)
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + jwtSecret))
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }


  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(key).parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty");
    } catch (SignatureException e) {
      log.error("there is an error with the signature of you token ");
    }
    return false;
  }

  public String getUsername(String token) {
    // Extract the username from the JWT token
    return Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}