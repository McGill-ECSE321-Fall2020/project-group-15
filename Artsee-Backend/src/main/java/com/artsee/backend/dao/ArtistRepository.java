package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, String> {
	
	Artist findArtistByUser_id(String user_id);

}
