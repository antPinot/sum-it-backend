/**
 * 
 */
package com.pinot.sumitbackend.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Peak> getAllPeaks(){
		List<Peak> peaksToShuffle = peakRepository.findAll();
		Collections.shuffle(peaksToShuffle);
		return peaksToShuffle;
	}

}
