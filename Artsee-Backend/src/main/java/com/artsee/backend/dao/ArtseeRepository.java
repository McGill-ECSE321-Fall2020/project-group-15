//package com.artsee.backend.dao;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.artsee.backend.model.Address;
//
//
//@Repository
//public class ArtseeRepository {
//
//	@Autowired
//	EntityManager entityManager;
//
////	@Transactional
////	public Address createAddress(String name) {
////		Address a  = new Address();
////		p.setName(name);
////		entityManager.persist(p);
////		return p;
////	}
//
//	@Transactional
//	public Address getAddress(Integer address_id) {
//		Address a = entityManager.find(Address.class, address_id);
//		return a;
//	}
////
////	@Transactional
////	public Event createEvent(String name, Date date, Time startTime, Time endTime) {
////		Event e = new Event();
////		e.setName(name);
////		e.setDate(date);
////		e.setStartTime(startTime);
////		e.setEndTime(endTime);
////		entityManager.persist(e);
////		return e;
////	}
////
////	@Transactional
////	public Event getEvent(String name) {
////		Event e = entityManager.find(Event.class, name);
////		return e;
////	}
//
//}