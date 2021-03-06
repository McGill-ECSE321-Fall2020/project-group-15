package com.artsee.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review{
	
	public void setReviewID(Integer reviewID) {
		this.reviewID = reviewID;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewID;

	public Integer getReviewID() {
		return this.reviewID;
	}
   
	private Integer rating;

	public void setRating(Integer value) {
		this.rating = value;
    }
	public Integer getRating() {
		return this.rating;
    }
	private String comment;

	public void setComment(String value) {
		this.comment = value;
    }
	public String getComment() {
		return this.comment;
    }
	private Boolean wouldRecommend;

	public void setWouldRecommend(Boolean value) {
		this.wouldRecommend = value;
    }
	public Boolean getWouldRecommend() {
		return this.wouldRecommend;
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
	
	//Create a many to one relationship with the class Customer
	@ManyToOne(optional=false)
	private Customer customer;

	public Customer getCustomer() {
	   return this.customer;
	}
	
	public void setCustomer(Customer customer) {
	   this.customer = customer;
	}

}

