package com.artsee.backend.dto;

public class EndUserDto {
	private String userID;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;

	public EndUserDto(String userID,  String email, String password, String firstName, String lastName, String phoneNumber) {
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
}
