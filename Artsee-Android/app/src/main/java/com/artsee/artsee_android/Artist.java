package com.artsee.artsee_android;

public class Artist {

    private String name;
    private String userID;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String artistDescription;
    private Double rating;
    private String profilePictureURL;

    public Artist(String name) {
        this.name = name;
    }

    public Artist(){}

    public Artist(String userID, String email, String firstName, String lastName, String phoneNumber, String artistDescription, Double rating, String profilePictureURL) {
        this.userID = userID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.artistDescription = artistDescription;
        this.rating = rating;
        this.profilePictureURL = profilePictureURL;
    }

    public String getName(){
        return this.firstName + " " +this.lastName;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getRating() {
        return rating;
    }

    public String getArtistDescription() {
        return artistDescription;
    }

    public String getProfilePictureURL() {
        return this.profilePictureURL;
    }

    public void serProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL=profilePictureURL;
    }
}
