/**
 * 
 */
package com.pinot.sumitbackend.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
		return userRepository.findById(new ObjectId(id)).get();
	}
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void createUser(String username, String mail, String password) {
		if (userRepository.findByUsername(username).isEmpty() || userRepository.findByMail(mail).isEmpty()) {
			userRepository.save(new User(username, mail, passwordEncoder.encode(password), LocalDate.now()));
		}
		
	}
	
	public void updateFavorites(String username, String favorite) {
		System.out.println("Favori ajouté " + favorite);
		User userToUpdate = findByUsername(username).map(user -> user).orElseThrow();
		if (userToUpdate.getFavorites() != null) {
			userToUpdate.getFavorites().add(favorite);
		} else {
			userToUpdate.setFavorites(Arrays.asList(favorite));
		}
		userRepository.save(userToUpdate);
	}

}
