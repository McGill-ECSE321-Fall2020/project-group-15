package com.artsee.backend.dto;

public class CustomerDto {
	
	private String userID;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private AddressDto address;
	
	public CustomerDto() {
		
	}
	
	public CustomerDto(String userID, String email, String password, String firstName, String lastName, String phoneNumber, AddressDto address) {
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
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
	
	public AddressDto getAddress() {
		return address;
	}
	
}