package com.artsee.backend.model;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "artistID")
public class Artist extends EndUser{
private String artistDeccription;
   
   public void setArtistDeccription(String value) {
this.artistDeccription = value;
    }
public String getArtistDeccription() {
return this.artistDeccription;
    }
private float rating;

public void setRating(float value) {
this.rating = value;
    }
public float getRating() {
return this.rating;
    }
private Set<Review> reviews;

@OneToMany(mappedBy="artist", cascade={CascadeType.ALL})
public Set<Review> getReviews() {
   return this.reviews;
}

public void setReviews(Set<Review> reviewss) {
   this.reviews = reviewss;
}

private Set<Artwork> artworks;

@OneToMany(mappedBy="artist")
public Set<Artwork> getArtworks() {
   return this.artworks;
}

public void setArtworks(Set<Artwork> artworkss) {
   this.artworks = artworkss;
}

}
