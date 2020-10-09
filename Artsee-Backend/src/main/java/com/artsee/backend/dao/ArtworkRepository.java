package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Artwork;

public interface ArtworkRepository extends CrudRepository<Artwork, Integer> {
	
	Artwork findArtworkByArtworkID(Integer artworkID);

}

