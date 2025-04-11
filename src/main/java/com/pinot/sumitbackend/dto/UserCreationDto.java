/**
 * 
 */
package com.pinot.sumitbackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Builder
public class UserCreationDto {
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String mail;
	
	@Getter
	@Setter
	private String password;

}
