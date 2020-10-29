package com.artsee.backend.dto;

public class ReviewDto {

	private int reviewID;
	private int rating;
	private String comment;
	private Boolean wouldRecommend;
	private CustomerDto customer;
	private ArtistDto artist;
	
	public ReviewDto() {
		
	}
	
	public ReviewDto(int reviewID, int rating, String comment, Boolean wouldRecommend, CustomerDto customer, ArtistDto artist) {
		this.reviewID = reviewID;
		this.rating = rating;
		this.comment = comment;
		this.wouldRecommend = wouldRecommend;
		this.customer = customer;
		this.artist = artist;
	}

	public int getReviewID() {
		return reviewID;
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getComment() {
		return comment;
	}
	
	public Boolean getRecomendation() {
		return wouldRecommend;
	}
	
	public CustomerDto getCustomer() {
		return customer;
	}
	
	public ArtistDto getArtist() {
		return artist;
	}
	
}
