package com.server.flow.auth.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.flow.auth.service.TokenService;
import com.server.flow.auth.service.dto.LoginRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {
		ObjectMapper objectMapper = new ObjectMapper();
		LoginRequest loginRequest = null;

		try {
			loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginRequest.employeeNumber(), loginRequest.password());

			return authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		} catch (Exception e) {
			log.error("==> Error during authentication : {}", e.getMessage());
			throw new AuthenticationServiceException(e.getMessage());
		}
	}

	// todo
	// @Override
	// protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	// 	Authentication authResult) throws IOException, ServletException {
	// 	Object principal = authResult.getPrincipal();
	// 	tokenService.generateToken()
	// }
}
