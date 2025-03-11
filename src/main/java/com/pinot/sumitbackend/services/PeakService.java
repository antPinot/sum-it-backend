/**
 * 
 */
package com.pinot.sumitbackend.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.repositories.PeakRepository;

/**
 * 
 */
@Service
public class PeakService {
	
	@Autowired
	private PeakRepository peakRepository;
	
	/**
	 * 
	 * @return
	 */
	public List<Peak> getAllPeaks(){
		List<Peak> peaksToShuffle = peakRepository.findAll();
		Collections.shuffle(peaksToShuffle);
		return peaksToShuffle;
	}
	
	public List<Peak> getAllPeaksById(List<String> peaksIds){
		List<ObjectId> peaksObjectIds = peaksIds.stream().map(ObjectId :: new).toList();
		return peakRepository.findAllById(peaksObjectIds);
	}
	
	

}
