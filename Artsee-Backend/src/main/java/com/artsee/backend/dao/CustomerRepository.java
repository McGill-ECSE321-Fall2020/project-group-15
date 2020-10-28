package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Customer;
import com.artsee.backend.model.EndUser;

public interface CustomerRepository extends CrudRepository<Customer, String> {
	
//	//Perform CRUD operations by interacting with the Customer class
//	Customer findCustomerByUserID(String userID);
	Customer findByEmail(String email);
}