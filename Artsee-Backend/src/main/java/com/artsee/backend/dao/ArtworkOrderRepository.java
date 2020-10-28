package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.ArtworkOrder;

public interface ArtworkOrderRepository extends CrudRepository<ArtworkOrder, Integer>{
	
//	//Perform CRUD operations by interacting with the ArtworkOrder class
//	ArtworkOrder findArtworkOrderByOrderID(Integer orderID);

}
