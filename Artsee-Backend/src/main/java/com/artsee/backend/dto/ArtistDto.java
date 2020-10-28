package com.artsee.backend.dto;

public class ArtistDto {

	private String artistID;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String artistDescription;
	private Float rating;
	
	public ArtistDto() {
		
	}
	
	public ArtistDto(String artistID, String email, String password, String firstName, String lastName, String phoneNumber, String artistDescription, Float rating) {
		this.artistID = artistID;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.artistDescription = artistDescription;
		this.rating = rating;
	}
	
	public String getArtistID() {
		return artistID;
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
	
	public Float getRating() {
		return rating;
	}
	
	public String getArtistDescription() {
		return artistDescription;
	}
	
}
