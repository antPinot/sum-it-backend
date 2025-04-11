/**
 * 
 */
package com.pinot.sumitbackend.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinot.sumitbackend.components.TransactionListener;
import com.pinot.sumitbackend.controllers.UserController;
import com.pinot.sumitbackend.document.User;
import com.pinot.sumitbackend.exceptions.UserNotFoundException;
import com.pinot.sumitbackend.repositories.UserRepository;

/**
 * 
 */
@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final TransactionListener transactionListener;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TransactionListener transactionListener) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.transactionListener = transactionListener;
	}
	
	@Transactional(readOnly = true)
	public User findById(String id) {
		return userRepository.findById(new ObjectId(id)).orElseThrow();
	}
	
	@Transactional(readOnly = true)
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Transactional
	public void createUser(String username, String mail, String password) {
		if (userRepository.findByUsername(username).isEmpty() || userRepository.findByMail(mail).isEmpty()) {
			userRepository.save(new User(username, mail, passwordEncoder.encode(password), LocalDate.now()));
		}
		//Log creation only if transaction is committed
		transactionListener.runAfterCommit(() -> LOGGER.info("L'utilisateur " + username + " a été créé avec succès"));
	}
	
	
	private void modifyFavorites(String username, Consumer<List<String>> favoriteModifier) throws NoSuchElementException{
		User userToUpdate = findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		List<String> favorites = userToUpdate.getFavorites();
	    if (favorites == null) {
	    	favorites = new ArrayList<>();
	        userToUpdate.setFavorites(favorites);
	    }
	    favoriteModifier.accept(favorites);
	    userRepository.save(userToUpdate);
	}
	
	@Transactional
	public void updateFavorites(String username, String favorite) {
	    modifyFavorites(username, favorites -> favorites.add(favorite));
	    transactionListener.runAfterCommit(() -> LOGGER.info("Favorite " + favorite + " has been added by " + username));
	}
	
	@Transactional
	public void deleteFavorites(String username, String favorite) {
	    modifyFavorites(username, favorites -> favorites.remove(favorite));
	    transactionListener.runAfterCommit(() -> LOGGER.info("Favorite " + favorite + " has been removed by " + username));
	}



}
