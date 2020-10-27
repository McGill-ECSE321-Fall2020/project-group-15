package com.artsee.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address{
	private Integer addressID;

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
    }
	
   //Create primary key called addressID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAddressID() {
		return this.addressID;
    }
   
	private String addressLine1;

	public void setAddressLine1(String value) {
		this.addressLine1 = value;
    }
	public String getAddressLine1() {
		return this.addressLine1;
    }
	private String addressLine2;

	public void setAddressLine2(String value) {
		this.addressLine2 = value;
    }
	public String getAddressLine2() {
		return this.addressLine2;
    }
	private String city;

	public void setCity(String value) {
		this.city = value;
    }
	public String getCity() {
		return this.city;
    }
	private String province;

	public void setProvince(String value) {
		this.province = value;
    }
	public String getProvince() {
		return this.province;
    }
	private String postalCode;

	public void setPostalCode(String value) {
		this.postalCode = value;
    }
	public String getPostalCode() {
		return this.postalCode;
    }
	private String country;

	public void setCountry(String value) {
		this.country = value;
    }
	public String getCountry() {
		return this.country;
    }
}

