package com.server.flow.auth.jwt.service;

import org.springframework.stereotype.Service;

import com.server.flow.auth.jwt.JwtTokenProcessor;
import com.server.flow.auth.jwt.entity.RefreshToken;
import com.server.flow.auth.jwt.repository.RefreshTokenRepository;
import com.server.flow.auth.jwt.service.dto.AuthResponse;
import com.server.flow.employee.entity.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final JwtTokenProcessor jwtTokenProcessor;
	private final RefreshTokenRepository refreshTokenRepository;

	public AuthResponse generateToken(Employee employee) {
		String accessToken = jwtTokenProcessor.generateAccessToken(employee);
		String refreshToken = jwtTokenProcessor.generateRefreshToken(employee);

		RefreshToken createdRefreshToken = RefreshToken.of(employee.getId(), refreshToken);
		refreshTokenRepository.save(createdRefreshToken);

		return AuthResponse.of(
			employee.getId(),
			employee.getEmployeeNumber(),
			employee.getRoleTypes(),
			accessToken,
			refreshToken
		);
	}
}
