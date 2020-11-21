package com.artsee.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.artsee.backend.model.Artist;
import com.artsee.backend.model.Customer;
import com.artsee.backend.model.Review;


//@RepositoryRestResource(collectionResourceRel = "review_data", path = "review_data")
public interface ReviewRepository extends CrudRepository<Review, Integer>{
	
	List<Review> findByArtist(Artist artist);
	
	List<Review> findByCustomer(Customer customer);
	
}
