/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinot.sumitbackend.config.JWTConfig;
import com.pinot.sumitbackend.document.User;
import com.pinot.sumitbackend.dto.UserCreationDto;
import com.pinot.sumitbackend.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * 
 */
@RestController
@RequestMapping("session")
public class SessionController {

	@Autowired
	private JWTConfig jwtConfig;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<?> createAuthToken(@RequestBody UserCreationDto userCreationDto) {
		return userRepository.findByUsername(userCreationDto.getUsername())
				.filter(user -> passwordEncoder.matches(userCreationDto.getPassword(), user.getPassword()))
				.map(user -> ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, buildJWTCookie(user)).build())
				.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}
	
	public String buildJWTCookie(User user) {
		
		String jwtToken = Jwts.builder()
				.subject(user.getUsername())
				.expiration(Date.from(LocalDateTime.now().plusMonths(1).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(jwtConfig.getSecretKey())
				.compact();
		
		ResponseCookie tokenCookie = ResponseCookie.from(jwtConfig.getCookie(), jwtToken)
				.httpOnly(true)
				.maxAge(jwtConfig.getExpireIn() * 1000)
				.path("/")
				.build();
		
		return tokenCookie.toString();
		
	}

}
