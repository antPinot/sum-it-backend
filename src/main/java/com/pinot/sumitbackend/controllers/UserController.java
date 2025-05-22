/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinot.sumitbackend.dto.UserAvatarDto;
import com.pinot.sumitbackend.dto.UserCreationDto;
import com.pinot.sumitbackend.dto.UserFavoritesDto;
import com.pinot.sumitbackend.dto.UserInfoDto;
import com.pinot.sumitbackend.services.UserService;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	private UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/info")
	public ResponseEntity<UserInfoDto> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails == null) {
			LOGGER.info("No User Details provided in getUserInfo request");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		return userService.findByUsername(userDetails.getUsername())
				.map(user -> UserInfoDto.builder()
		                .username(user.getUsername())
		                .favorites(user.getFavorites())
		                .creationDate(user.getCreationDate())
		                .avatar(user.getAvatar())
		                .build())
		        .map(ResponseEntity::ok).orElseGet(() -> {
		        	LOGGER.error("User " + userDetails.getUsername() + " does not exist");
		        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		        });
	}
	
	@PostMapping()
	public void saveUser(@RequestBody UserCreationDto userToCreate) {
		userService.createUser(userToCreate.getUsername(), userToCreate.getMail(), userToCreate.getPassword());
	}
	
	@PostMapping("/avatar")
	public void updateAvatar(@RequestBody @Valid UserAvatarDto userAvatarDto) {
		userService.updateAvatar(userAvatarDto.getUsername(), userAvatarDto.getAvatar());
	}
	
	@PostMapping("/favorites/add")
	public void updateFavorites(@RequestBody UserFavoritesDto userFavoritesDto) {
		userService.updateFavorites(userFavoritesDto.getUsername(), userFavoritesDto.getFavorite());
	}
	
	@DeleteMapping("{username}/favorites/delete/{favoriteId}")
	public void deleteFavorites(@PathVariable("username") String username, @PathVariable("favoriteId") String favoriteId ) {
		userService.deleteFavorites(username, favoriteId);
	}
	
	
	

}
