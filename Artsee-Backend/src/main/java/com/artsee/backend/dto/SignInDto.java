package com.artsee.backend.dto;

public class SignInDto {
	private String userID;
	private String password;
	
	public SignInDto() {}
	
	public SignInDto(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}
	
	public String getUserID() {
		return this.userID;
	}

	public String getPassword() {
		return this.password;
	}
}
