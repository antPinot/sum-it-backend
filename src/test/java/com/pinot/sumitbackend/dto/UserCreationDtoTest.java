/**
 * 
 */
package com.pinot.sumitbackend.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class UserCreationDtoTest {

	@Test
	@DisplayName("Should set and get fields correctly in UserCreationDto")
	void should_set_and_get_fields_correctly() {
		
		UserCreationDto dto = UserCreationDto.builder().username("testuser")
														.mail("test@mail.com")
														.password("secret")
														.build();

		assertEquals("testuser", dto.getUsername());
		assertEquals("test@mail.com", dto.getMail());
		assertEquals("secret", dto.getPassword());
	}

}
