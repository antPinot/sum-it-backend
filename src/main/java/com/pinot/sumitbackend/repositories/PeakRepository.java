/**
 * 
 */
package com.pinot.sumitbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pinot.sumitbackend.document.Peak;

/**
 * 
 */
@Repository
public interface PeakRepository extends MongoRepository<Peak, String> {

	public Peak findByElevation(String elevation);
	
	public Peak findByName(String name);
}
