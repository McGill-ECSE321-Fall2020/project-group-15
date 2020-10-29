package com.artsee.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Artwork;
import com.artsee.backend.model.Artist;


public interface ArtworkRepository extends CrudRepository<Artwork,Integer> {
	
	boolean existsByNameAndArtist(String name, Artist artist);
	
	List<Artwork> findByArtist(Artist artist);

}

