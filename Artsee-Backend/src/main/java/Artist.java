import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Artist extends User{
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

@OneToMany(cascade={CascadeType.ALL})
public Set<Review> getReviews() {
   return this.reviews;
}

public void setReviews(Set<Review> reviewss) {
   this.reviews = reviewss;
}

}
