package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.artsee.backend.model.Review;


//@RepositoryRestResource(collectionResourceRel = "review_data", path = "review_data")
public interface ReviewRepository extends CrudRepository<Review, Integer>{
	
//	//Perform CRUD operations by interacting with the Review class
//	Review findReviewByReviewID(Integer reviewID);

}
