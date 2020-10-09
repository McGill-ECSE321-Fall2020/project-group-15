package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.User;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.data.repository.query.Param;

//@CrossOrigin(origins = "*")
public interface UserRepository extends CrudRepository<User, String> {
	
//	User findUserByEmail(@Param(value = "email") String email);

	User findUserByEmail(String email);

}
