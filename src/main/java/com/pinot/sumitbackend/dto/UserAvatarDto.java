/**
 * 
 */
package com.pinot.sumitbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 
 */
public class UserAvatarDto {
	
	@Getter
	String username;
	
	@Getter
	@JsonProperty("avatar")
	@NotNull (message = "Le dataUrl de l'avatar est null")
	String Avatar;

}
