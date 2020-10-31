package com.artsee.backend.dto;

public class AdministratorDto {
	
	private String userID;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public AdministratorDto() {}
	
	public AdministratorDto(String userID, String email, String password, String firstName, String lastName, String phoneNumber) {
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
}
