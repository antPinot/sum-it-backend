/**
 * 
 */
package com.pinot.sumitbackend.config;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
						
						UserDetails userDetails = User.builder()
								.username(username)
								.password("")
								.roles("USER")
								.build();
						
						Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(authentication);
					});
		}
		
		filterChain.doFilter(request, response);

	}

}
