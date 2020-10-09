package com.artsee.backend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.artsee.backend.model.Address;
import com.artsee.backend.model.Administrator;
import com.artsee.backend.model.Artist;
import com.artsee.backend.model.Artwork;
import com.artsee.backend.model.Customer;
import com.artsee.backend.model.Order;
import com.artsee.backend.model.Review;
import com.artsee.backend.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestArtseePersistance {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private ArtworkRepository artworkRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private UserRepository userRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		addressRepository.deleteAll();
		// Then we can clear the other tables
		administratorRepository.deleteAll();
		artistRepository.deleteAll();
		artworkRepository.deleteAll();
		customerRepository.deleteAll();
		orderRepository.deleteAll();
		reviewRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadAdministrator() {
		String email = "123@mail.ca";
		String password = "password";
		String firstName = "testfirst";
		String lastName = "testlast";
		String phoneNumber = "123456";
		
		Administrator administrator = new Administrator();
		administrator.setEmail(email);
		administrator.setPassword(password);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPhoneNumber(phoneNumber);
		
		administratorRepository.save(administrator);
		
		administrator = null;
		
		administrator = administratorRepository.findAdministratorByEmail(email);
		
		assertNotNull(administrator);
		assertEquals(email, administrator.getEmail());
	}

//	@Test
//	public void testPersistAndLoadEvent() {
//		String name = "ECSE321 Tutorial";
//		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
//		Event event = new Event();
//		event.setName(name);
//		event.setDate(date);
//		event.setStartTime(startTime);
//		event.setEndTime(endTime);
//		eventRepository.save(event);
//
//		event = null;
//
//		event = eventRepository.findEventByName(name);
//
//		assertNotNull(event);
//		assertEquals(name, event.getName());
//		assertEquals(date, event.getDate());
//		assertEquals(startTime, event.getStartTime());
//		assertEquals(endTime, event.getEndTime());
//	}
//
//	@Test
//	public void testPersistAndLoadRegistration() {
//		String personName = "TestPerson";
//		Person person = new Person();
//		person.setName(personName);
//		personRepository.save(person);
//
//		String eventName = "ECSE321 Tutorial";
//		Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
//		Event event = new Event();
//		event.setName(eventName);
//		event.setDate(date);
//		event.setStartTime(startTime);
//		event.setEndTime(endTime);
//		eventRepository.save(event);
//
//		Registration reg = new Registration();
//		int regId = 1;
//		// First example for reference save/load
//		reg.setId(regId);
//		reg.setPerson(person);
//		reg.setEvent(event);
//		registrationRepository.save(reg);
//
//		reg = null;
//
//		reg = registrationRepository.findByPersonAndEvent(person, event);
//		assertNotNull(reg);
//		assertEquals(regId, reg.getId());
//		// Comparing by keys
//		assertEquals(person.getName(), reg.getPerson().getName());
//		assertEquals(event.getName(), reg.getEvent().getName());
//	}

}
