package com.artsee.artsee_android;

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

    /**
     *
     * @param id
     */
    public Artwork(Integer id){
        this.id = id;
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param price
     * @param date
     * @param numInStock
     * @param artist
     * @param url
     */
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

    /**
     * get artwork name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get artwork price
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * get artwork image url
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * get artist
     * @return
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get ID
     * @return
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * get description
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * get date
     * @return
     */
    public String getDate() {
        return this.date;
    }

    /**
     * get num in stock
     * @return
     */
    public Integer getNumInStock() {
        return this.numInStock;
    }
}
