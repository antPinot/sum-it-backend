/**
 * 
 */
package com.pinot.sumitbackend.config;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@Component
public class JWTAuthorizationfilter extends OncePerRequestFilter {

	@Autowired
	private JWTConfig jwtConfig;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getCookies() != null) {
			Stream.of(request.getCookies()).filter(cookie -> cookie.getName().equals(jwtConfig.getCookie()))
					.map(cookie -> cookie.getValue()).forEach(token -> {
						Claims body = Jwts.parser()
								.verifyWith(jwtConfig.getSecretKey())
								.build()
								.parseSignedClaims(token)
								.getPayload();
						
						String username = body.getSubject();
					});
		}
		
		filterChain.doFilter(request, response);

	}

}
