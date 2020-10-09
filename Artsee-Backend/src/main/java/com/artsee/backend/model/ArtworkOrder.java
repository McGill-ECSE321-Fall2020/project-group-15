package com.artsee.backend.model;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;

@Entity
public class ArtworkOrder{
	private Set<Artwork> artworks;
	//Create a many to many relationship with the class Artwork
	@ManyToMany(mappedBy="artworkOrders")
	public Set<Artwork> getArtworks() {
	   return this.artworks;
	}
	
	public void setArtworks(Set<Artwork> artworkss) {
	   this.artworks = artworkss;
	}
	
	private Integer orderID;
	
	public void setOrderID(Integer value) {
		this.orderID = value;
    }
	//Create primary key called orderID
	@Id
	public Integer getOrderID() {
		return this.orderID;
    }
	private float totalPrice;

	public void setTotalPrice(float value) {
		this.totalPrice = value;
    }
	public float getTotalPrice() {
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

	private Customer customer;
	//Create a many to one relationship with the class Customer
	@ManyToOne(optional=false)
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

