package com.app.control.api.configuration.security.auth.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.control.api.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiration}")
	private String expiration;
	@Value("${security.jwt.subscription-key}")
	private String signKey;

	public String generateToken(User user) {
		long expiration = Long.valueOf(this.expiration);
		LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expiration);
		Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		return Jwts.builder().setSubject(user.getEmail()).setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, this.signKey).compact();
	}

	private Claims obtainClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(this.signKey).parseClaimsJws(token).getBody();
	}

	public String obtainLoginUser(String token) throws ExpiredJwtException {
		return (String) obtainClaims(token).getSubject();
	}

	public boolean tokenValid(String token) {
		try {
			Claims claims = obtainClaims(token);
			Date dateExpiraton = claims.getExpiration();
			LocalDateTime localDateTime = dateExpiraton.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public String obtainTokenValid(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];

			boolean isTokenValid = tokenValid(token);

			if (isTokenValid) {
				return token;
			}

		}
		return "";
	}

}
