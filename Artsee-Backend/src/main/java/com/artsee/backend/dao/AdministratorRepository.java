package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Administrator;
import com.artsee.backend.model.Artist;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {
	
	Administrator findByEmail(String email);
	
}
