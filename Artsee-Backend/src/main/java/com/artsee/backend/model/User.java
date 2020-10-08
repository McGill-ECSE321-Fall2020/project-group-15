package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class User{
private String user_email;
   
   public void setUser_email(String value) {
this.user_email = value;
    }
@Id
public String getUser_email() {
return this.user_email;
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
