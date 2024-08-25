package com.server.flow.auth.service.dto;

public record AuthResponse(
	Long employeeId,
	String accessToken,
	String refreshToken
) {
	public static AuthResponse of(
		Long employeeId,
		String accessToken,
		String refreshToken
	) {
		return new AuthResponse(employeeId, accessToken, refreshToken);
	}
}
