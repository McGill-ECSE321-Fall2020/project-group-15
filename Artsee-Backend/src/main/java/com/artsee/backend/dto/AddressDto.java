package com.artsee.backend.dto;

public class AddressDto {
	
	private int addressID;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String province;
	private String postalCode;
	private String country;
	
	public AddressDto() {
		
	}
	
	public AddressDto(Integer addressID, String addressLine1, String addressLine2, String city, String province, String postalCode, String country) {
		this.addressID = addressID;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	public int getAddressID() {
		return this.addressID;
	}
	
	public String getAddressLine1() {
		return this.addressLine1;
	}
	
	public String getAddressLine2() {
		return this.addressLine2;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getProvince() {
		return this.province;
	}
	
	public String getPostalCode() {
		return this.postalCode;
	}
	
	public String getCountry() {
		return this.country;
	}
	
}