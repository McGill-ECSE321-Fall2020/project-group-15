package com.artsee.backend.dao;

import com.artsee.backend.model.Artist;
import com.artsee.backend.model.Artwork;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ArtworkRepository extends CrudRepository<Artwork,Integer> {
	
	boolean existsByNameAndArtist(String name, Artist artist);
	
	List<Artwork> findByArtist(Artist artist);

}

