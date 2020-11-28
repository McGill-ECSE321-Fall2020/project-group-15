package com.artsee.artsee_android;

/**
 * Artist class
 */
public class Artist {

    private String userID;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String artistDescription;
    private Double rating;
    private String profilePictureURL;

    /**
     *
     * @param userID
     * @param email
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param artistDescription
     * @param rating
     * @param profilePictureURL
     */
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

    /**
     * get artist name
     * @return
     */
    public String getName(){
        return this.firstName + " " +this.lastName;
    }

    /**
     * get artist description
     * @return
     */
    public String getArtistDescription() {
        return artistDescription;
    }
}
