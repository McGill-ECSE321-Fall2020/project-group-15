package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name = "customerID")
public class Customer extends User{
	private Address address;
	
	@ManyToOne
	public Address getAddress() {
	   return this.address;
	}
	
	public void setAddress(Address address) {
	   this.address = address;
	}
}
