package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Administrator;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {
	
	Administrator findAdministratorByUser_id(String user_id);

}
