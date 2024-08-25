package com.server.flow.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

			.httpBasic(AbstractHttpConfigurer::disable)
			//.formLogin(AbstractHttpConfigurer::disable)

			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/api/login", "/home", "/").permitAll()
				.anyRequest().authenticated())

			.formLogin(formLoginConfigurer -> formLoginConfigurer
				.loginProcessingUrl("/api/login")
				.usernameParameter("employeeNumber")
				.defaultSuccessUrl("/home", true));

		// .logout(logoutConfigurer -> logoutConfigurer
		// 	.logoutSuccessUrl("/login")
		// 	.invalidateHttpSession(true)
		//);

		return http.build();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/api/**", config);
		return new CorsFilter(source);
	}
}
