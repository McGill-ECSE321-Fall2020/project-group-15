package com.artsee.backend.utility;

import com.artsee.backend.model.*;


public class TestUtility {

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
    
}
