package com.artsee.backend.dto;

import java.sql.Date;

public class ArtworkDto {
	
	private String name;
	private String description;
	private int price;
	private Date dateOfCreation;
	private Integer numInStock;
	private ArtistDto artist;
	
	public void setName(String value) {
		this.name = value;
    }
	public String getName() {
		return this.name;
    }
	
	public void setDescription(String value) {
		this.description = value;
    }
	public String getDescription() {
		return this.description;
    }
	
	public void setPrice(int value) {
		this.price = value;
    }
	public int getPrice() {
		return this.price;
    }
	
	public void setDateOfCreation(Date value) {
		this.dateOfCreation = value;
    }
	public Date getDateOfCreation() {
		return this.dateOfCreation;
    }
	
	public void setNumInStock(Integer value) {
		this.numInStock = value;
    }
	public Integer getNumInStock() {
		return this.numInStock;
    }
	
	public ArtistDto getArtist() {
		return this.artist;
	}
		
	public void setArtist(ArtistDto artist) {
	   this.artist = artist;
	}
}
