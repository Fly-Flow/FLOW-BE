package com.server.flow.auth.entity;

import com.server.flow.common.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class RefreshToken extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long employeeId;

	@Column(nullable = false)
	private String refreshToken;

	private RefreshToken(Long employeeId, String refreshToken) {
		this.employeeId = employeeId;
		this.refreshToken = refreshToken;
	}

	public static RefreshToken of(Long employeeId, String refreshToken) {
		return new RefreshToken(employeeId, refreshToken);
	}
}
