package com.artsee.backend.model;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import java.sql.Date;
import javax.persistence.ManyToOne;

@Entity
public class Artwork{
	//Create a many to many relationship with the class ArtworkOrder
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "artwork_artwork_orders",
		joinColumns = @JoinColumn (name = "artwork_id", referencedColumnName = "artworkid"),
		inverseJoinColumns = @JoinColumn (name = "order_id", referencedColumnName = "orderid"),
		schema = "public"
		)	
//	@ElementCollection(targetClass=ArtworkOrder.class)
//	@Access(AccessType.PROPERTY)
	private Set<ArtworkOrder> artworkOrders;

	public Set<ArtworkOrder> getArtworkOrders() {
	   return this.artworkOrders;
	}
	
	public void setArtworkOrders(Set<ArtworkOrder> artworkOrderss) {
	   this.artworkOrders = artworkOrderss;
	}

	 //Create primary key called artworkID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artworkID;
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
	
	//Create a many to one relationship with the class Artist
	@ManyToOne(optional=false)
	private Artist artist;

	public Artist getArtist() {
	   return this.artist;
	}
	
	public void setArtist(Artist artist) {
	   this.artist = artist;
	}
	
	private String imageURL;
	
	public void setImageURL(String imageURL) {
		   this.imageURL = imageURL;
	}
	
	public String getImageURL() {
		   return this.imageURL;
	}
	
}

