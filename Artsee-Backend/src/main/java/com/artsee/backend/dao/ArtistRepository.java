package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Artist;
import com.artsee.backend.model.EndUser;


public interface ArtistRepository extends CrudRepository<Artist, String> {

	Artist findByEmail(String email);
	
}
