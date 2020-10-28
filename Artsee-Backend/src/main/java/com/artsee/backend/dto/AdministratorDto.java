package com.artsee.backend.dto;

public class AdministratorDto {
	
	private String administratorID;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private AddressDto address;
	
	public AdministratorDto() {
		
	}
	
	public AdministratorDto(String administratorID, String email, String password, String firstName, String lastName, String phoneNumber, AddressDto address) {
		this.administratorID = administratorID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public String getAdministratorID() {
		return administratorID;
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
