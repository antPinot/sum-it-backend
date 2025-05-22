/**
 * 
 */
package com.pinot.sumitbackend.document;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
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
	@Setter
	private ObjectId id;
	
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
	
	@Field(name = "refreshToken")
	@Getter
	@Setter
	private String refreshToken;
	
	@Field(name = "creationDate")
	@Getter
	@Setter
	@CreatedDate
	@NotNull(message = "Creation Date is not provided")
	private LocalDate creationDate;
	
	@Field(name = "favorites")
	@Getter
	@Setter
	private List<String> favorites;
	
	@Field(name = "avatar")
	@Getter
	@Setter
	private String avatar;

	public User(String username, String mail, String password, LocalDate creationDate) {
		super();
		this.username = username;
		this.mail = mail;
		this.password = password;
		this.creationDate = creationDate;
	}
	
	
	
}
