package com.korit.thememorialday.provider;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.util.StandardCharset;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//# JWT 생성 및 검증 기능

@Component
public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String secretKey;

	// JWT 생성 메서드
	public String create(String userId) {
		// 만료 시간 = 현재 시간 + 10시간
		Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

		String jwt = null;

		try {
			Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharset.UTF_8));

			jwt = Jwts.builder()
				.signWith(key, SignatureAlgorithm.HS256)
				.setSubject(userId)
				.setIssuedAt(new Date())
				.setExpiration(expiredDate)
				.compact();
		} catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}

		return jwt;
	}

	// 검증 메서드
	public String validate(String jwt) {
		String userId = null;

		try {
			Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharset.UTF_8));

			userId = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt)
				.getBody()
				.getSubject();
		} catch(Exception exception) {
			exception.printStackTrace();
			return null;
		}

		return userId;
	}

}
