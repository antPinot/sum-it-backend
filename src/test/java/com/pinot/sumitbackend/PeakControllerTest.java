/**
 * 
 */
package com.pinot.sumitbackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pinot.sumitbackend.controllers.PeakController;
import com.pinot.sumitbackend.document.Peak;

/**
 * Unit tests for PeakController
 */
@AutoConfigureMockMvc
@SpringBootTest
class PeakControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PeakController mockPeakController;

	@BeforeEach
	public void setup() {
		
		List<Peak> testPeaks = new ArrayList<>();

		testPeaks.add(new Peak(2581, "Peak 1", null, null, null));
		testPeaks.add(new Peak(4863, "Peak 2", null, null, null));
		
		when(mockPeakController.getAllPeaks()).thenReturn(testPeaks);
	}

	/**
	 * Test method for
	 * {@link com.pinot.sumitbackend.controllers.PeakController#getAllPeaks()}.
	 * @throws Exception 
	 * 
	 */
	@Test
	void should_get_all_peaks_and_return_them_as_json_format() throws Exception {
		mockMvc.perform(get("/rest/peak/all"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()").value(2))
		.andExpect(jsonPath("$[1].name").value("Peak 2"))
		.andExpect(jsonPath("$[0].elevation").value(2581));
	}

}
