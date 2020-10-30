package com.artsee.backend.utility;

import java.sql.Date;

import com.artsee.backend.model.*;


public class TestUtility {


    // ---------- Artist Methods ----------------------------------------------------------------------------------------------------------------------------------
    public static Artist createArtist(String artistID, String email, String password, String firstName, String lastName, String phoneNumber,  String artistDescription) {
        Artist artist = new Artist(); 
        artist.setUserID(artistID);
		artist.setEmail(email);
		artist.setPassword(password);
		artist.setFirstName(firstName);
		artist.setLastName(lastName);
		artist.setArtistDescription(artistDescription);
		artist.setPhoneNumber(phoneNumber);
		return artist;
    }
    
    public static Customer createCustomer(String customerID, String email, String password, String firstName, String lastName, String phoneNumber,  Address customerAddress) {
    	Customer customer = new Customer();
    	customer.setUserID(customerID);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setAddress(customerAddress);
		customer.setPhoneNumber(phoneNumber);
		return customer;
    }

    public static Administrator createAdmin(String administratorID, String email, String password, String firstName, String lastName, String phoneNumber) { 
        Administrator administrator = new Administrator();

        administrator.setUserID(administratorID);
		administrator.setEmail(email);
		administrator.setPassword(password);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPhoneNumber(phoneNumber);
		return administrator;
    }

    public static Address createAddress(int id, String addressLine1, String addressLine2, String city, String province, String postalCode, String country) {
        Address address = new Address();
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setProvince(province);
		address.setPostalCode(postalCode);
        address.setCountry(country);
        address.setAddressID(id);
		return address;
    }

    public static Artwork createArtwork(int id, String name, int price, String description, Date dateCreated, int numInStock, Artist artist) {
        Artwork artwork = new Artwork();
        artwork.setName(name);
        artwork.setDescription(description);
        artwork.setPrice(price);
        artwork.setDateOfCreation(dateCreated);
        artwork.setNumInStock(numInStock);
        artwork.setArtist(artist);
        artwork.setArtworkID(id);
        
        return artwork;
    }

    
}
