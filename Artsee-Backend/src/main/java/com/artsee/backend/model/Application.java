package com.artsee.backend.model;

import javax.persistence.Entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;


@Entity
public class Application{


	private String name; 
	//Create primary key called name
	@Id 
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private Set<EndUser> users;
	//Create a one to many relationship with the class Users
	@OneToMany(cascade={CascadeType.ALL})
	public Set<EndUser> getUsers() {
		return this.users;
	}

	public void setUsers(Set<EndUser> userss) {
	  this.users = userss;
	}
	
	private Set<ArtworkOrder> orders;
	//Create a one to many relationship with the class ArtworkOrder
	@OneToMany(cascade={CascadeType.ALL})
	public Set<ArtworkOrder> getOrders() {
	   return this.orders;
	}
	
	public void setOrders(Set<ArtworkOrder> orderss) {
	   this.orders = orderss;
	}
	
	private Set<Artwork> artworks;
	//Create a one to many relationship with the class Artwork
	@OneToMany(cascade={CascadeType.ALL})
	public Set<Artwork> getArtworks() {
	   return this.artworks;
	}
	
	public void setArtworks(Set<Artwork> artworkss) {
	   this.artworks = artworkss;
	}

}
