package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

// Setting the primary key name in the administratorID (this is inherited from endUser as a joined table structure was used)
@Entity
@PrimaryKeyJoinColumn(name = "administratorID")
public class Administrator extends EndUser{

}
