package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.EndUser;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.data.repository.query.Param;

//@CrossOrigin(origins = "*")
public interface EndUserRepository extends CrudRepository<EndUser, String> {
	
//	User findUserByEmail(@Param(value = "email") String email);

	EndUser findEndUserByEmail(String email);

}
