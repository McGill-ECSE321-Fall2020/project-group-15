//package com.artsee.backend.model;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.sql.Date;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class Artwork{
//private Integer artwork_id;
//   
//   public void setArtwork_id(Integer value) {
//this.artwork_id = value;
//    }
//@Id
//public Integer getArtwork_id() {
//return this.artwork_id;
//    }
//private String name;
//
//public void setName(String value) {
//this.name = value;
//    }
//public String getName() {
//return this.name;
//    }
//private String description;
//
//public void setDescription(String value) {
//this.description = value;
//    }
//public String getDescription() {
//return this.description;
//    }
//private float price;
//
//public void setPrice(float value) {
//this.price = value;
//    }
//public float getPrice() {
//return this.price;
//    }
//private Date dateOfCreation;
//
//public void setDateOfCreation(Date value) {
//this.dateOfCreation = value;
//    }
//public Date getDateOfCreation() {
//return this.dateOfCreation;
//    }
//private Integer numInStock;
//
//public void setNumInStock(Integer value) {
//this.numInStock = value;
//    }
//public Integer getNumInStock() {
//return this.numInStock;
//    }
//private Artist artist;
//
//@ManyToOne(optional=false)
//public Artist getArtist() {
//   return this.artist;
//}
//
//public void setArtist(Artist artist) {
//   this.artist = artist;
//}
//
//}
