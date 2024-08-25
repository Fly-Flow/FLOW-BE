package com.server.flow.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.flow.auth.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
