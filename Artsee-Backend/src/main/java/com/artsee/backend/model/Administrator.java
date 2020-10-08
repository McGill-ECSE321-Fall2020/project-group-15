package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Administrator extends User{
private String admin_email;
   
   public void setAdmin_email(String value) {
this.admin_email = value;
    }
@Id
public String getAdmin_email() {
return this.admin_email;
       }
   }
