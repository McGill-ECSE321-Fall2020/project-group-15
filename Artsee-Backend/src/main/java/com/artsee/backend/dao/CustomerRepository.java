package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Customer;
import com.artsee.backend.model.EndUser;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	
	Customer findByEmail(String email);
	
}