package com.artsee.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.artsee.backend.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

	Order findOrderByOrderID(Integer orderID);

}
