package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Artist;


public interface ArtistRepository extends CrudRepository<Artist, String> {
	//Perform CRUD operations by interacting with the Artist class
	Artist findArtistByUserID(String userID);

}
