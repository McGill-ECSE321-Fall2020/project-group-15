package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.EndUser;

public interface EndUserRepository extends CrudRepository<EndUser, Integer> {
	
	//Perform CRUD operations by interacting with the EndUser class
	EndUser findEndUserByUserID(Integer userID);

}
