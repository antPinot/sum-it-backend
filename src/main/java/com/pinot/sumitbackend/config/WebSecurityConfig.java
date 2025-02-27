/**
 * 
 */
package com.pinot.sumitbackend.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 */
@Configuration
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, JWTAuthorizationfilter jwtFilter) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.requestMatchers(HttpMethod.POST, "/session").permitAll()
				.requestMatchers(HttpMethod.GET, "/rest/peak/all").permitAll()
				.requestMatchers(HttpMethod.POST, "/rest/user").permitAll()
				.anyRequest().authenticated())
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.csrf(csrf -> csrf.disable())
//		.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//						  .csrfTokenRequestHandler(new XorCsrfTokenRequestAttributeHandler() :: handle))
		;

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new DelegatingPasswordEncoder("bcrypt", Map.of("bcrypt", new BCryptPasswordEncoder()));
	}
}
