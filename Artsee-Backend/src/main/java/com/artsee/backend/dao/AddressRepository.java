package com.artsee.backend.dao;
//
//import org.springframework.data.repository.CrudRepository;
//
//import com.artsee.backend.model.Address;
//
//public interface AddressRepository extends CrudRepository<Address, Integer>{
//
//	Address findAddressByAddress_id(Integer address_id);
//	
//}



import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

	Address findAddressByAddressID(Integer addressID);

}