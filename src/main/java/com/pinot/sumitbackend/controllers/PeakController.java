/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.repositories.PeakRepository;
import com.pinot.sumitbackend.services.PeakService;


/**
 * 
 */
@RestController
@RequestMapping("/rest/peak")
@CrossOrigin(origins = "*")
public class PeakController {
	
	@Autowired
	private PeakService peakService;
	
	@GetMapping("/all")
	public List<Peak> getAllPeaks(){
		return peakService.getAllPeaks();
	}
	
}
