package com.artsee.artsee_android;

/**
 * Added this class to present data in list of recyclerViewAdapter
 */
public class Artwork {

    private String name;
    private int price;
    private String url;
    private Artist artist;

    public Artwork() {}

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
