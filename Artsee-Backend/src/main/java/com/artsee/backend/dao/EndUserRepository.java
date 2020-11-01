package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.EndUser;

public interface EndUserRepository extends CrudRepository<EndUser, String> {
	
	EndUser findByEmail(String email);
	
}
