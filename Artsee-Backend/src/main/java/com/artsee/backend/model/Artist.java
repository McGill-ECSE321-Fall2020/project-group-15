package com.artsee.backend.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import java.util.Set;

//Setting the primary key name in the artistID (this is inherited from endUser as a joined table structure was used)
@Entity
@PrimaryKeyJoinColumn(name = "artistID")
public class Artist extends EndUser{
	private String artistDescription;
   
   public void setArtistDescription(String value) {
	   this.artistDescription = value;
   }
   public String getArtistDescription() {
	   return this.artistDescription;
   }

   private float rating;
   public float getRating() {
	   return this.rating;
   }
   public void setRating(float rating) {
	   this.rating = rating;
   }

   private Set<Review> reviews;
   //Create a one to many relationship with the class Review
   @OneToMany(mappedBy="artist", cascade={CascadeType.ALL})
   public Set<Review> getReviews() {
	   return this.reviews;
   }

	public void setReviews(Set<Review> reviews) {
	   this.reviews = reviews;
	}

	private Set<Artwork> artworks;
	//Create a one to many relationship with the class Artwork
	@OneToMany(mappedBy="artist")
	public Set<Artwork> getArtworks() {
	   return this.artworks;
	}
	
	public void setArtworks(Set<Artwork> artworkss) {
	   this.artworks = artworkss;
	}

}
