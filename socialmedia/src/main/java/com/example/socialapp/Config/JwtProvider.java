package com.example.socialapp.Config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	
	private static SecretKey key = Keys.hmacShaKeyFor(jwtConstant.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		String jwt = Jwts.builder()
				.setIssuer("NguyenPhuocLoc")
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 86400000))
				.claim("email", auth.getName())
				.signWith(key)
				.compact();

		return jwt;
	}
	
	public static String getEmailFromJwtToken(String jwt) {
		
		// Barrer token
		jwt = jwt.substring(7);
		
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt) // kiem tra xem jwt co hop le khong
				.getBody();
		
		String email = String.valueOf(claims.get("email"));
		
		
		return email;
	}

}
