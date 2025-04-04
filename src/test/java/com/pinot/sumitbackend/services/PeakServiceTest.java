/**
 * 
 */
package com.pinot.sumitbackend.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.repositories.PeakRepository;
import com.pinot.sumitbackend.services.PeakService;

/**
 * 
 */
@SpringBootTest
class PeakServiceTest {
	
	@InjectMocks
	private PeakService mockPeakService;
	
	@MockitoBean
	private PeakRepository mockPeakRepository;
	
	private List<Peak> testPeaks = new ArrayList<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		
		ObjectId peak1Id = new ObjectId("65fdf5d1543b7f25e0e3c1a2");
		ObjectId peak2Id = new ObjectId("65f4b02f522b7a05b0b3f4d3");
		List<ObjectId> peaksIds = new ArrayList<>(Arrays.asList(peak1Id, peak2Id));
		
		Peak peak1 = new Peak(2581, "Peak 1", null, null, null, null);
		peak1.setId(peak1Id);
		Peak peak2 = new Peak(4863, "Peak 2", null, null, null, null);
		peak2.setId(peak2Id);
		
		testPeaks.addAll(Arrays.asList(peak1, peak2));

			
		
		when(mockPeakRepository.findAll()).thenReturn(testPeaks);
		when(mockPeakRepository.findAllById(peaksIds)).thenReturn(testPeaks);
		
	}

	/**
	 * Test method for {@link com.pinot.sumitbackend.services.PeakService#getAllPeaks()}.
	 */
	@Test
	final void should_get_all_peaks_and_return_not_null_results() {
		List<Peak> results = mockPeakService.getAllPeaks();
		
		assertNotNull(results, "The list of peaks shouldn't be null");
		assertTrue(results.containsAll(testPeaks), "The results do not contain all peaks");
		
	}
	
	@Test
	final void should_get_all_peaks_and_return_not_null_results_when_empty() {
		testPeaks.clear();
		
		List<Peak> results = mockPeakService.getAllPeaks();
		
		assertNotNull(results, "The list of peaks shouldn't be null");
		assertTrue(results.isEmpty(), "The results should be empty");
	}
	
	@Test
	final void should_get_peaks_with_corresponding_id() {
		List<String> peaksId = new ArrayList<>(Arrays.asList("65fdf5d1543b7f25e0e3c1a2","65f4b02f522b7a05b0b3f4d3"));
		List<Peak> results = mockPeakService.getAllPeaksById(peaksId);
		assertEquals(testPeaks, results);
	}
	
	

}
