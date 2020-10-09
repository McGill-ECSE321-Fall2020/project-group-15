//package com.artsee.backend.model;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.sql.Date;
//import java.util.Set;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class Order{
//private Integer order_id;
//   
//
//   public void setOrder_id(Integer value) {
//this.order_id = value;
//    }
//@Id
//public Integer getOrder_id() {
//return this.order_id;
//    }
//private float totalPrice;
//
//public void setTotalPrice(float value) {
//this.totalPrice = value;
//    }
//public float getTotalPrice() {
//return this.totalPrice;
//    }
//private Date datePlaced;
//
//public void setDatePlaced(Date value) {
//this.datePlaced = value;
//    }
//public Date getDatePlaced() {
//return this.datePlaced;
//    }
//private Date dateCompleted;
//
//public void setDateCompleted(Date value) {
//this.dateCompleted = value;
//    }
//public Date getDateCompleted() {
//return this.dateCompleted;
//    }
//
//public enum DeliveryMethod { 
//	SHIP,
//	PICKUP
//}
//
//private DeliveryMethod deliveryMethod;
//
//public void setDeliveryMethod(DeliveryMethod value) {
//this.deliveryMethod = value;
//    }
//public DeliveryMethod getDeliveryMethod() {
//return this.deliveryMethod;
//    }
//
//
//public enum OrderStatus { 
//	PROCESSING,
//	DELIVERED
//}
//
//private OrderStatus orderStatus;
//
//public void setOrderStatus(OrderStatus value) {
//this.orderStatus = value;
//    }
//public OrderStatus getOrderStatus() {
//return this.orderStatus;
//    }
//private Set<Artwork> artworks;
//
//@ManyToMany
//public Set<Artwork> getArtworks() {
//   return this.artworks;
//}
//
//public void setArtworks(Set<Artwork> artworkss) {
//   this.artworks = artworkss;
//}
//
//private Customer customer;
//
//@ManyToOne(optional=false)
//public Customer getCustomer() {
//   return this.customer;
//}
//
//public void setCustomer(Customer customer) {
//   this.customer = customer;
//}
//
//
//}
