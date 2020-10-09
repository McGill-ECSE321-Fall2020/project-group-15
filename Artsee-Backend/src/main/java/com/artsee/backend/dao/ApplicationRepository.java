package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, String> {
	
	Application findApplicationByName(String name);

}