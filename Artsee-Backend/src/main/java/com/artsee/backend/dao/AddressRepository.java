package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

	Address findByAddress_id(Integer address_id);
	
}
