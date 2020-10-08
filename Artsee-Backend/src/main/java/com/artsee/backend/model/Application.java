package com.artsee.backend.model;

import javax.persistence.Entity;
import User;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import Order;
import Artwork;

@Entity
public class Application{
private Set<User> users;

@OneToMany(cascade={CascadeType.ALL})
public Set<User> getUsers() {
   return this.users;
}

public void setUsers(Set<User> userss) {
   this.users = userss;
}

private Set<Order> orders;

@OneToMany(cascade={CascadeType.ALL})
public Set<Order> getOrders() {
   return this.orders;
}

public void setOrders(Set<Order> orderss) {
   this.orders = orderss;
}

private Set<Artwork> artworks;

@OneToMany(cascade={CascadeType.ALL})
public Set<Artwork> getArtworks() {
   return this.artworks;
}

public void setArtworks(Set<Artwork> artworkss) {
   this.artworks = artworkss;
}

}
