/**
 * 
 */
package com.pinot.sumitbackend.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pinot.sumitbackend.document.User;

/**
 * 
 */
@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByMail(String mail);
	
	
	
}
