package com.jwt.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.jwt.model.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	private final String JWT_SECRET = "duong@123";

	private final long JWT_EXPIRATION =  60 * 60 * 1000;

	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();

		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException ex) {
//	            log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
//	            log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
//	            log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
//	            log.error("JWT claims string is empty.");
		}
		return false;
	}

}
