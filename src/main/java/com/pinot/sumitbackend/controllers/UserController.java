/**
 * 
 */
package com.pinot.sumitbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinot.sumitbackend.document.User;
import com.pinot.sumitbackend.dto.UserCreationDto;
import com.pinot.sumitbackend.dto.UserFavoritesDto;
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
	
	@PostMapping()
	private void saveUser(@RequestBody UserCreationDto userToCreate) {
		userService.createUser(userToCreate.getUsername(), userToCreate.getMail(), userToCreate.getPassword());
	}
	
	@PostMapping("/favorites")
	private void updateFavorites(@RequestBody UserFavoritesDto userFavoritesDto) { 
		userService.updateFavorites(userFavoritesDto.getUsername(), userFavoritesDto.getFavorite());
	}

}
