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
	
	public ArtworkOrderDto() {}
	
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
	
	public ArtworkOrderDto(Integer orderID, DeliveryMethodDto deliveryMethodDto, CustomerDto customer, List<ArtworkDto> artworksDto) {
		this.orderID = orderID;
		this.deliveryMethodDto = deliveryMethodDto;
		this.customer = customer;
		this.artworks = artworksDto;
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
	
	public DeliveryMethodDto getDeliveryMethodDto() {
		return this.deliveryMethodDto;
	}
	
	public void setDeliveryMethodDto(DeliveryMethodDto deliveryMethodDto) {
		this.deliveryMethodDto = deliveryMethodDto;
	}
	
	public OrderStatusDto getOrderStatusDto() {
		return this.orderStatusDto;
	}
	
	public CustomerDto getCustomer() {
		return this.customer;
	}
	
	public List<ArtworkDto> getArtworks(){
		return this.artworks;
	}
	
	public void setArtworks(List<ArtworkDto> list){
		this.artworks = list;
	}
}
