package com.artsee.backend.dto;

import java.sql.Date;

import java.util.List;

public class ArtworkOrderDto {

	private int orderID;
	private int totalPrice;
	private Date datePlaced;
	private Date dateCompleted;
	private DeliveryMethodDto deliveryMethodDto;
	private OrderStatusDto orderStatusDto;
	private CustomerDto customer;
	private List<ArtworkDto> artworks;
	
	public ArtworkOrderDto(Integer orderID, Integer totalPrice, Date datePlaced, Date dateCompleted, DeliveryMethodDto deliveryMethodDto, OrderStatusDto orderStatusDto, CustomerDto customer, List<ArtworkDto> artworks) {
		this.orderID = orderID;
		this.totalPrice = totalPrice;
		this.datePlaced = datePlaced;
		this.dateCompleted = dateCompleted;
		this.deliveryMethodDto = deliveryMethodDto;
		this.orderStatusDto = orderStatusDto;
		this.customer = customer;
		this.artworks = artworks;
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
	
	public DeliveryMethodDto getDeliveryMethodStr() {
		return this.deliveryMethodDto;
	}
	
	public OrderStatusDto getOrderStatusStr() {
		return this.orderStatusDto;
	}
	
	public CustomerDto getCustomer() {
		return this.customer;
	}
	
	public List<ArtworkDto> getArtworks(){
		return this.artworks;
	}
}
