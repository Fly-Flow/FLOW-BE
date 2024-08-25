package com.server.flow.auth.encryptor;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class PasswordEncryptor {
	private static final int BCRYPT_SALT_LENGTH = 10;

	public String encryptPassword(String password) {
		BCrypt.Hasher hasher = BCrypt.withDefaults();
		byte[] hashedPasswordBytes = hasher.hash(
			BCRYPT_SALT_LENGTH,
			password.getBytes(StandardCharsets.UTF_8)
		);

		return new String(hashedPasswordBytes, StandardCharsets.UTF_8);
	}

	public boolean matchPassword(String password, String encryptedPassword) {
		BCrypt.Verifyer verifyer = BCrypt.verifyer();
		BCrypt.Result result = verifyer.verify(
			password.getBytes(StandardCharsets.UTF_8),
			encryptedPassword.getBytes(StandardCharsets.UTF_8)
		);

		return result.verified;
	}
}
