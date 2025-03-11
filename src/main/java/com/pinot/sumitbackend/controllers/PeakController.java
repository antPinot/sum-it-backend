/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.services.PeakService;


/**
 * REST controller exposing endpoints related to peaks.
 * This class provides APIs to retrieve peak data.
 * <p>
 * It uses {@link PeakService} to fetch and return information to clients.
 * </p>
 * 
 * <p><strong>Annotations used:</strong></p>
 * <ul>
 *   <li>{@code @RestController} : Marks this class as a REST controller.</li>
 *   <li>{@code @RequestMapping("/rest/peak")} : Defines the base path for endpoints.</li>
 *   <li>{@code @CrossOrigin(origins = "*")} : Allows CORS requests from any origin.</li>
 * </ul>
 * 
 * <p><strong>Exposed endpoints:</strong></p>
 * <ul>
 *   <li>{@code GET /rest/peak/all} : Returns the full list of peaks.</li>
 * </ul>
 * 
 * @author Anthony PINOT
 * @version 1.0
 */
@RestController
@RequestMapping("/rest/peak")
@CrossOrigin(origins = "*")
public class PeakController {
	
	
	/**
	 * {@code @Autowired} : Injects an instance of {@link PeakService} to interact with data.
	 */
	@Autowired
	private PeakService peakService;
	
	/**
     * Retrieves the list of all available peaks.
     * 
     * @return a list of {@link Peak} objects representing the peaks.
     */
	@GetMapping("/all")
	public List<Peak> getAllPeaks(){
		return peakService.getAllPeaks();
	}
	
	@GetMapping("/favorites")
	public List<Peak>getAllPeaksByIds(@RequestBody List<String> peaksIds){
		return peakService.getAllPeaksById(peaksIds);
	}
	
}
