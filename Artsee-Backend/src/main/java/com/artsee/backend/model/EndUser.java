package com.artsee.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


// Implemented a joined inheritance for persistence. End user will hold all common info of subclasses
// Subclass tables will hold properties specific to subclasses
// Subclasses will inherit the EndUser's Primary key (although the columns are renamed to suit the subclass)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EndUser{
	private String email;
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	@Id
	private String userID;

	public String getUserID() {
		return this.userID;
	}
	
	public void setEmail(String value) {
		   this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	private String password;
	
	public void setPassword(String value) {
		this.password = value;
	}
	public String getPassword() {
		return this.password;
	}
	private String firstName;
	
	public void setFirstName(String value) {
		this.firstName = value;
	}
	public String getFirstName() {
		return this.firstName;
	}
	private String lastName;
	
	public void setLastName(String value) {
		this.lastName = value;
	}
	public String getLastName() {
		return this.lastName;
	}
	private String phoneNumber;
	
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
}
