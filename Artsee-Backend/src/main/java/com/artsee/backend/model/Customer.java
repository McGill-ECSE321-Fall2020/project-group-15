package com.artsee.backend.model;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.ManyToOne;

//Setting the primary key name in the customerrID (this is inherited from endUser as a joined table structure was used)
@Entity
@PrimaryKeyJoinColumn(name = "customerID")
public class Customer extends EndUser{
private Set<ArtworkOrder> artworkOrders;

@OneToMany(mappedBy="customer")
public Set<ArtworkOrder> getArtworkOrders() {
   return this.artworkOrders;
}

public void setArtworkOrders(Set<ArtworkOrder> artworkOrderss) {
   this.artworkOrders = artworkOrderss;
}

private Address address;

@ManyToOne
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

}
