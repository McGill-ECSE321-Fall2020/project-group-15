package com.artsee.backend.model;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;

@Entity
public class Artwork{
private Set<ArtworkOrder> artworkOrders;

@ManyToMany
public Set<ArtworkOrder> getArtworkOrders() {
   return this.artworkOrders;
}

public void setArtworkOrders(Set<ArtworkOrder> artworkOrderss) {
   this.artworkOrders = artworkOrderss;
}

private Integer artworkID;

public void setArtworkID(Integer value) {
this.artworkID = value;
    }
@Id
public Integer getArtworkID() {
return this.artworkID;
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
private float price;

public void setPrice(float value) {
this.price = value;
    }
public float getPrice() {
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

@ManyToOne(optional=false)
public Artist getArtist() {
   return this.artist;
}

public void setArtist(Artist artist) {
   this.artist = artist;
}

}

