package com.artsee.backend.model;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

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
