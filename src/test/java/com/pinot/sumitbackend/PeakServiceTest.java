/**
 * 
 */
package com.pinot.sumitbackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
		
		testPeaks.add(new Peak(2581, "Peak 1", null, null, null));
		testPeaks.add(new Peak(4863, "Peak 2", null, null, null));
		
		when(mockPeakRepository.findAll()).thenReturn(testPeaks);
		
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

}
