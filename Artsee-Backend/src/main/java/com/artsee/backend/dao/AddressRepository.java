package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{
	
	//Perform CRUD operations by interacting with the Address class
	Address findAddressByAddressID(Integer addressID);

}