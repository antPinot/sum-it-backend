/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.services.PeakService;

/**
 * Unit tests for PeakController
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class PeakControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PeakService mockPeakService;
	
	@BeforeEach
	public void setup() {
		
		List<Peak> testPeaks = new ArrayList<>();
		
		ObjectId peak1Id = new ObjectId("65fdf5d1543b7f25e0e3c1a2");
		ObjectId peak2Id = new ObjectId("65f4b02f522b7a05b0b3f4d3");
		
		Peak peak1 = new Peak(2581, "Peak 1", null, null, null, null);
		peak1.setId(peak1Id);
		Peak peak2 = new Peak(4863, "Peak 2", null, null, null, null);
		peak2.setId(peak2Id);
		
		testPeaks.addAll(Arrays.asList(peak1, peak2));
		
		when(mockPeakService.getAllPeaks()).thenReturn(testPeaks);
		when(mockPeakService.getAllPeaksById(Arrays.asList("65fdf5d1543b7f25e0e3c1a2"))).thenReturn(Arrays.asList(peak1));
		
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
	
	@Test
	void should_get_all_peaks_by_id_and_return_them_as_json_format() throws Exception {
		String body = (new ObjectMapper().valueToTree(Arrays.asList("65fdf5d1543b7f25e0e3c1a2"))).toString();
		mockMvc.perform(post("/rest/peak/favorites")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(1))
				.andExpect(jsonPath("$[0].name").value("Peak 1"))
				.andExpect(jsonPath("$[0].elevation").value(2581));
	}
	

}
