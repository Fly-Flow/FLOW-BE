package com.server.flow.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
	private String accessSecretKey;
	private long accessExpiresIn;
	private String refreshSecretKey;
	private long refreshExpiresIn;
}
