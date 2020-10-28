package com.artsee.backend.dto;

import java.sql.Date;

import com.artsee.backend.model.DeliveryMethod;
import com.artsee.backend.model.OrderStatus;

public class ArtworkOrderDto {

	private int orderID;
	private int totalPrice;
	private Date datePlaced;
	private Date dateCompleted;
	private DeliveryMethod deliveryMethod;
	private OrderStatus orderStatus;
	private CustomerDto customer;
	
	
	public ArtworkOrderDto() {
		
	}
	
	public ArtworkOrderDto(Integer orderID, Integer totalPrice, Date datePlaced, Date dateCompleted, DeliveryMethod deliveryMethod, OrderStatus orderStatus, CustomerDto customer) {
		this.orderID = orderID;
		this.totalPrice = totalPrice;
		this.datePlaced = datePlaced;
		this.dateCompleted = dateCompleted;
		this.deliveryMethod = deliveryMethod;
		this.orderStatus = orderStatus;
		this.customer = customer;
	}
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public int getTotalPrice() {
		return this.totalPrice;
	}
	
	public Date getDatePlaced() {
		return this.datePlaced;
	}
	
	public Date getDateCompleted() {
		return this.dateCompleted;
	}
	
	public DeliveryMethod getDeliveryMethod() {
		return this.deliveryMethod;
	}
	
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
	
	public CustomerDto getCustomer() {
		return this.customer;
	}
	
}
