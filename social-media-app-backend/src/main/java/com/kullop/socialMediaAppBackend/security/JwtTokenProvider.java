package com.kullop.socialMediaAppBackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${socialMediaApp.app.secret}")
    private String APP_SECRET;
    @Value("${socialMediaApp.expires.in}")
    private Long EXPIRES_IN;

    // generates a new token
    public String generateJwtToken(Authentication authentication){
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + this.EXPIRES_IN);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, this.APP_SECRET)
                .compact();
    }

    // gets user id from the token
    public Long getUserIdFromJwtToken(String token){
        Claims claims = parseToken(token);
        return Long.parseLong(claims.getSubject());
    }

    // validates the token if its expired or malformed
    public Boolean validateToken(String token){
        try {
            parseToken(token);
            return !isTokenExpired(token);
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e){
            return false;
        }
    }

    // checks if the token expired
    public Boolean isTokenExpired(String token){
        Claims claims = parseToken(token);
        return claims.getExpiration().before(new Date());
    }

    // parses the token and returns the claims
    public Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(this.APP_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
