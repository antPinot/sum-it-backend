/**
 * 
 */
package com.pinot.sumitbackend.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pinot.sumitbackend.document.Peak;

/**
 * 
 */
@Repository
public interface PeakRepository extends MongoRepository<Peak, ObjectId> {
	
	

	/**
	 * @param elevation
	 * @return Peak
	 */
	public Peak findByElevation(String elevation);
	
	/**
	 * @param name
	 * @return Peak
	 */
	public Peak findByName(String name);
	
}
