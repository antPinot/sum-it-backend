/**
 * 
 */
package com.pinot.sumitbackend.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pinot.sumitbackend.document.User;

/**
 * 
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findByUsername(String username);
	
	public User findByMail(String mail);
	
	
	
}
