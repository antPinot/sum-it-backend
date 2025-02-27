/**
 * 
 */
package com.pinot.sumitbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pinot.sumitbackend.document.User;
import com.pinot.sumitbackend.repositories.UserRepository;

/**
 * 
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findById(String id) {
		return userRepository.findById(id).get();
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}
	
	public void createUser(String username, String mail, String password) {
		userRepository.save(new User(username, mail, passwordEncoder.encode(password)));
	}

}
