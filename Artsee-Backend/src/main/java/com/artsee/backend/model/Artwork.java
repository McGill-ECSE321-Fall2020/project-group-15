package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;

@Entity
public class Artwork{
	private Set<ArtworkOrder> artworkOrders;
	//Create a many to many relationship with the class ArtworkOrder
	@ManyToMany
	public Set<ArtworkOrder> getArtworkOrders() {
	   return this.artworkOrders;
	}
	
	public void setArtworkOrders(Set<ArtworkOrder> artworkOrderss) {
	   this.artworkOrders = artworkOrderss;
	}
	
	private Integer artworkID;

	 //Create primary key called artworkID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getArtworkID() {
		return this.artworkID;
    }
	
	public void setArtworkID(Integer artworkID) {
		this.artworkID = artworkID;
    }
	
	private String name;
	
	public void setName(String value) {
		this.name = value;
    }
	public String getName() {
		return this.name;
    }
	private String description;

	public void setDescription(String value) {
		this.description = value;
    }
	public String getDescription() {
		return this.description;
    }
	private Integer price;

	public void setPrice(int value) {
		this.price = value;
    }
	public Integer getPrice() {
		return this.price;
    }
	private Date dateOfCreation;

	public void setDateOfCreation(Date value) {
		this.dateOfCreation = value;
    }
	public Date getDateOfCreation() {
		return this.dateOfCreation;
    }
	private Integer numInStock;

	public void setNumInStock(Integer value) {
		this.numInStock = value;
    }
	public Integer getNumInStock() {
		return this.numInStock;
    }
	
	private Artist artist;
	//Create a many to one relationship with the class Artist
	@ManyToOne(optional=false)
	public Artist getArtist() {
	   return this.artist;
	}
	
	public void setArtist(Artist artist) {
	   this.artist = artist;
	}
}

