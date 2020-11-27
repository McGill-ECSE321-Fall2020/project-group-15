package com.artsee.artsee_android;

import java.sql.Date;

/**
 * Added this class to present data in list of recyclerViewAdapter
 */
public class Artwork {

    private Integer id;
    private String name;
    private String description;
    private int price;
    private String date;
    private int numInStock;
    private String url;
    private Artist artist;
    private String artistID;

    public Artwork() {}

    public Artwork(Integer id, String name, String description, int price, String date, int numInStock, Artist artist, String url){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.date = date;
        this.numInStock = numInStock;
        this.artist = artist;
        this.url = url;
    }

    public Artwork(String name, int price, String url, Artist artist) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
