/**
 * 
 */
package com.pinot.sumitbackend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 */
@Builder
public class UserInfoDto {
	
	@Getter
	private String username;
	
	@Getter
	private List<String> favorites;
	
	@Getter
	private LocalDate creationDate;

}
