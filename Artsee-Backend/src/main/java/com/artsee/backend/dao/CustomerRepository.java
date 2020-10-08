package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	
	Customer findCustomerByUser_id(String user_id);

}