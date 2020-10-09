package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "administratorID")
public class Administrator extends User{

}
