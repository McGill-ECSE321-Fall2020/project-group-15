package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Customer extends User{
private Address address;

@ManyToOne
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

private String customer_email;

public void setCustomer_email(String value) {
this.customer_email = value;
    }
@Id
public String getCustomer_email() {
return this.customer_email;
       }
   }
