package com.artsee.artsee_android;

import java.sql.Date;
import java.util.List;

public class ArtworkOrder {

    private int orderID;
    private int totalPrice;
    private Date datePlaced;
    private Date dateCompleted;
    private DeliveryMethodDto deliveryMethodDto;
    private OrderStatusDto orderStatusDto;
    private CustomerDto customer;
    private List<Artwork> artworks;

    public enum OrderStatusDto {
        PROCESSING,
        DELIVERED
    }

    public enum DeliveryMethodDto {
        SHIP,
        PICKUP
    }

    public ArtworkOrder() {}

    public ArtworkOrder(Integer orderID, Integer totalPrice, Date datePlaced, Date dateCompleted, DeliveryMethodDto deliveryMethodDto, OrderStatusDto orderStatusDto, CustomerDto customer, List<Artwork> artworks) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.datePlaced = datePlaced;
        this.dateCompleted = dateCompleted;
        this.deliveryMethodDto = deliveryMethodDto;
        this.orderStatusDto = orderStatusDto;
        this.customer = customer;
        this.artworks = artworks;
    }

    public ArtworkOrder(DeliveryMethodDto deliveryMethodDto, CustomerDto customer, List<Artwork> artworks) {
        this.deliveryMethodDto = deliveryMethodDto;
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

    public List<Artwork> getArtworks(){
        return this.artworks;
    }

    public void setArtworks(List<Artwork> list){
        this.artworks = list;
    }
}
