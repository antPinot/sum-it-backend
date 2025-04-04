/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinot.sumitbackend.dto.UserCreationDto;
import com.pinot.sumitbackend.dto.UserFavoritesDto;
import com.pinot.sumitbackend.services.UserService;

/**
 * 
 */
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockitoBean
    private UserService userService;
	
	private UserCreationDto mockUserCreationDto;
	
	private UserFavoritesDto mockUserFavoritesDto;

	@Test
	final void should_create_user_from_json_request_body() throws Exception {
		mockUserCreationDto = UserCreationDto.builder()
							.username("test")
							.mail("test@test.fr")
							.password("test")
							.build();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(mockUserCreationDto);
        
        mockMvc.perform(post("/rest/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
        
        verify(userService).createUser("test", "test@test.fr", "test");
        
	}
	
	@Test
	final void should_update_user_favorites_from_json_request_body() throws Exception {
		mockUserFavoritesDto = UserFavoritesDto.builder()
				.username("test")
				.favorite("65fdf5d1543b7f25e0e3c1a2")
				.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(mockUserFavoritesDto);
        
        mockMvc.perform(post("/rest/user/favorites/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
        
        verify(userService).updateFavorites("test", "65fdf5d1543b7f25e0e3c1a2");
        
        
	}

}
