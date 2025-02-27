/**
 * 
 */
package com.pinot.sumitbackend.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
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
