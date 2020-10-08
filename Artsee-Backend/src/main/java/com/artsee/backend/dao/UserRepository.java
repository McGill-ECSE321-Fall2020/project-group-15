package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	User findUserByUser_id(String user_id);

}
