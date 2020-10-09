package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

// Setting changing the primary key name in the 
@Entity
@PrimaryKeyJoinColumn(name = "administratorID")
public class Administrator extends EndUser{

}
