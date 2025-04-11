/**
 * 
 */
package com.pinot.sumitbackend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 */
@Builder
public class UserFavoritesDto {
	
	@Getter
	private String username;
	
	@Getter
	@JsonProperty("favoriteSummitId")
	private String favorite;

}
