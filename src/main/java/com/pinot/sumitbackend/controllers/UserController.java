/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinot.sumitbackend.document.User;
import com.pinot.sumitbackend.dto.UserCreationDto;
import com.pinot.sumitbackend.dto.UserFavoritesDto;
import com.pinot.sumitbackend.dto.UserInfoDto;
import com.pinot.sumitbackend.services.UserService;

/**
 * 
 */
@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/info")
	private ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails == null) {
			System.out.println("no userDetails provided");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		return userService.findByUsername(userDetails.getUsername())
				.map(user -> UserInfoDto.builder()
		                .username(user.getUsername())
		                .favorites(user.getFavorites())
		                .creationDate(user.getCreationDate())
		                .build())
		        .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping()
	private void saveUser(@RequestBody UserCreationDto userToCreate) {
		userService.createUser(userToCreate.getUsername(), userToCreate.getMail(), userToCreate.getPassword());
	}
	
	@PostMapping("/favorites/add")
	private void updateFavorites(@RequestBody UserFavoritesDto userFavoritesDto) {
		userService.updateFavorites(userFavoritesDto.getUsername(), userFavoritesDto.getFavorite());
	}
	
	@DeleteMapping("{username}/favorites/delete/{favoriteId}")
	private void deleteFavorites(@PathVariable("username") String username, @PathVariable("favoriteId") String favoriteId ) {
		userService.deleteFavorites(username, favoriteId);
	}
	
	
	

}
