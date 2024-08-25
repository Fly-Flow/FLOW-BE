package com.server.flow.auth.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.server.flow.employee.entity.Employee;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProcessor {
	private final JwtProperties jwtProperties;

	public String generateAccessToken(Employee employee) {
		Date now = new Date();
		SecretKeySpec secretKeySpec = getSecretKeySpec(TokenType.ACCESS);

		String jwt = Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiresIn()))
			.claim("employeeId", employee.getId())
			.signWith(secretKeySpec, SignatureAlgorithm.HS256)
			.compact();

		log.info("==> generated access token: {}", jwt);

		return jwt;
	}

	public String generateRefreshToken(Employee employee) {
		Date now = new Date();
		SecretKeySpec secretKeySpec = getSecretKeySpec(TokenType.REFRESH);

		String jwt = Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + jwtProperties.getRefreshExpiresIn()))
			.claim("employeeId", employee.getId())
			.signWith(secretKeySpec, SignatureAlgorithm.HS256)
			.compact();

		log.info("==> generated refresh token: {}", jwt);

		return jwt;
	}

	private SecretKeySpec getSecretKeySpec(TokenType tokenType) {
		String secretKey =
			tokenType == TokenType.ACCESS ? jwtProperties.getAccessSecretKey() : jwtProperties.getRefreshSecretKey();
		byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
	}

	public enum TokenType {
		ACCESS, REFRESH
	}
}
