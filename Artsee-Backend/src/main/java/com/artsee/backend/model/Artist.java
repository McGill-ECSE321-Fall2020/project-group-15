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

   public void setRating(float value) {
	   this.rating = value;
   }
   public float getRating() {
	   return this.rating;
   }
   
   @OneToMany(mappedBy="artist", cascade={CascadeType.ALL})
   private Set<Review> reviews;
   //Create a one to many relationship with the class Review
   public Set<Review> getReviews() {
	   return this.reviews;
   }

	public void setReviews(Set<Review> reviewss) {
	   this.reviews = reviewss;
	}

	@OneToMany(mappedBy="artist")
	private Set<Artwork> artworks;
	//Create a one to many relationship with the class Artwork
	public Set<Artwork> getArtworks() {
	   return this.artworks;
	}
	
	public void setArtworks(Set<Artwork> artworkss) {
	   this.artworks = artworkss;
	}

	private String profilePictureURL;
	
	public String getProfilePictureURL() {
		return this.profilePictureURL;
	}
	
	public void setProfilePictureURL(String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}
	
}
