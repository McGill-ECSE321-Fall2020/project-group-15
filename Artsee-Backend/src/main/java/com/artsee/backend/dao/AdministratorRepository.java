package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Administrator;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {
	
//	//Perform CRUD operations by interacting with the Administrator class
//	Administrator findAdministratorByUserID(String userID);

}
