package com.artsee.backend.model;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.JoinTable;

import java.sql.Date;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class ArtworkOrder{
	//Create a many to many relationship with the class Artwork
	@ManyToMany(mappedBy="artworks")
	@ElementCollection(targetClass=Artwork.class)
//	@Access(AccessType.PROPERTY)
	private Set<Artwork> artworks;
	public Set<Artwork> getArtworks() {
	   return this.artworks;
	}
	
	public void setArtworks(Set<Artwork> artworkss) {
	   this.artworks = artworkss;
	}
	
	//Create primary key called orderID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderID;
	public Integer getOrderID() {
		return this.orderID;
    }
	
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
    }
	
	private Integer totalPrice;

	public void setTotalPrice(Integer value) {
		this.totalPrice = value;
    }
	public Integer getTotalPrice() {
		return this.totalPrice;
    }
	private Date datePlaced;

	public void setDatePlaced(Date value) {
		this.datePlaced = value;
    }
	public Date getDatePlaced() {
		return this.datePlaced;
    }
	private Date dateCompleted;

	public void setDateCompleted(Date value) {
		this.dateCompleted = value;
    }
	public Date getDateCompleted() {
		return this.dateCompleted;
    }

	//Create a many to one relationship with the class Customer
	@ManyToOne(optional=false)
	private Customer customer;
	public Customer getCustomer() {
	   return this.customer;
	}
	
	public void setCustomer(Customer customer) {
	   this.customer = customer;
	}

	private DeliveryMethod deliveryMethod;

	public void setDeliveryMethod(DeliveryMethod value) {
		this.deliveryMethod = value;
    }
	public DeliveryMethod getDeliveryMethod() {
		return this.deliveryMethod;
    }

	private OrderStatus orderStatus;

	public void setOrderStatus(OrderStatus value) {
		this.orderStatus = value;
    }
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
   }
}

