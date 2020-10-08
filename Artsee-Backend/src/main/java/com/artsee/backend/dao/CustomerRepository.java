package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	Customer findCustomerByUser_id(Integer user_id);

}