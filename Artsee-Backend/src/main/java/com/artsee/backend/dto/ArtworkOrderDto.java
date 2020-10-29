package com.artsee.backend.dto;

import java.sql.Date;

public class ArtworkOrderDto {

	private int orderID;
	private int totalPrice;
	private Date datePlaced;
	private Date dateCompleted;
	private String deliveryMethodStr;
	private String orderStatusStr;
	private CustomerDto customer;
//	private List<ArtworkDto> artworks;
	
	public ArtworkOrderDto(Integer orderID, Integer totalPrice, Date datePlaced, Date dateCompleted, String deliveryMethodStr, String orderStatusStr, CustomerDto customer) {
		this.orderID = orderID;
		this.totalPrice = totalPrice;
		this.datePlaced = datePlaced;
		this.dateCompleted = dateCompleted;
		this.deliveryMethodStr = deliveryMethodStr;
		this.orderStatusStr = orderStatusStr;
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
	
	public String getDeliveryMethodStr() {
		return this.deliveryMethodStr;
	}
	
	public String getOrderStatusStr() {
		return this.orderStatusStr;
	}
	
	public CustomerDto getCustomer() {
		return this.customer;
	}
	
}
