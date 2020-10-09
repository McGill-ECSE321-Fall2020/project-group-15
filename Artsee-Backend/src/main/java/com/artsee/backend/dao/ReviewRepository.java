package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer>{

	Review findReviewByReviewID(Integer reviewID);

}
