package com.artsee.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.ArtworkOrder;
import com.artsee.backend.model.Customer;
import com.artsee.backend.model.Review;

public interface ArtworkOrderRepository extends CrudRepository<ArtworkOrder, Integer>{

	List<ArtworkOrder> findByCustomer(Customer customer);
	
}
