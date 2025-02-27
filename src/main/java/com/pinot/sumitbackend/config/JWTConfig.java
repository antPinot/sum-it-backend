/**
 * 
 */
package com.pinot.sumitbackend.config;

import java.security.Key;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.Getter;

/**
 * 
 */
@Configuration
public class JWTConfig {
	
	@Value("${jwt.expires_in}")
	@Getter
	private long expireIn;
	
	@Value("${jwt.cookie}")
	@Getter
	private String cookie;
	
	@Value("${jwt.secret}")
	@Getter
	private String secret;
	
	@Getter
	private SecretKey secretKey;
	
	@PostConstruct
	public void buildKey() {
		secretKey = new SecretKeySpec(Base64.getDecoder().decode(getSecret()), SignatureAlgorithm.HS256.getJcaName());
	}

}
