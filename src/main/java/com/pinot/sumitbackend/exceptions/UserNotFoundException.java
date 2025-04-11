/**
 * 
 */
package com.pinot.sumitbackend.exceptions;

/**
 * 
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9047390844888498929L;
	
	private final String username;

	public UserNotFoundException(String username) {
		super("Utilisateur introuvable : " + username);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
