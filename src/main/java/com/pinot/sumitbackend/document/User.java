/**
 * 
 */
package com.pinot.sumitbackend.document;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Document(collection = "user")
public class User {
	
	@Id
	@Field(name = "id")
	@Getter
	private String id;
	
	@NotBlank
	@Field(name = "username")
	@Getter
	@Setter
	@NotNull(message = "Please enter username")
	private String username;
	
	@NotBlank
	@Field(name = "mail")
	@Getter
	@Setter
	@NotNull(message = "Please enter email adress")
	private String mail;
	
	@NotBlank
	@Field(name = "password")
	@Getter
	@Setter
	@NotNull(message = "Please enter email password")
	private String password;
	
	@Field(name = "passwordToken")
	@Getter
	@Setter
	private String refreshToken;
	
	@Field(name = "creationDate")
	@NotBlank
	@Getter
	@Setter
	@NotNull(message = "Creation Date is not provided")
	private LocalDate creationDate;
	
	@Field(name = "favorites")
	@Getter
	@Setter
	private List<String> favorites;

	public User(@NotBlank String username, @NotBlank String mail, @NotBlank String password) {
		super();
		this.username = username;
		this.mail = mail;
		this.password = password;
	}
	
	
	
}
