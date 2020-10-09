package com.artsee.backend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
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
import com.artsee.backend.model.ArtworkOrder;
import com.artsee.backend.model.Review;
import com.artsee.backend.model.DeliveryMethod;
import com.artsee.backend.model.OrderStatus;


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
	private ArtworkOrderRepository artworkOrderRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private EndUserRepository endUserRepository;

	// Clear the database after each test
	@AfterEach
	public void clearDatabase() {
		
		// Clear Artwork before Artist before Review to avoid inconsistency
		artworkRepository.deleteAll();
		artistRepository.deleteAll();
		reviewRepository.deleteAll();

		//Clear ArtworkOrder, Review Before Customer
		artworkOrderRepository.deleteAll();
		reviewRepository.deleteAll();

		//Clear Review before Customer before Address to avoid inconsistency
		customerRepository.deleteAll();
		addressRepository.deleteAll();
		
		// Has no references, can delete in any order
		endUserRepository.deleteAll();
		administratorRepository.deleteAll();
	}
	
	// Test for Address persistence 
	@Test
	public void testPersistAndLoadAddress() {
		
		// Creating Address object with test data
		Integer addressID = 1;
		String addressLine1 = "123 Test st.";
		String addressLine2 = "Apt 102";
		String city = "testCity";
		String province = "testProvince";
		String postalCode = "A1B2C3";
		String country = "Canada";
		Address address = createTestAddress(addressID, addressLine1, addressLine2, city, province, postalCode, country);
		
		// Saving object to DB
		addressRepository.save(address);
		
		address = null;
		
		// Pulling object from DB
		address = addressRepository.findAddressByAddressID(addressID);
		
		// Testing object pulls correctly
		assertNotNull(address);
		assertEquals(addressID, address.getAddressID());
		assertEquals(addressLine1, address.getAddressLine1());
		assertEquals(addressLine2, address.getAddressLine2());
		assertEquals(city, address.getCity());
		assertEquals(province, address.getProvince());
		assertEquals(postalCode, address.getPostalCode());
		assertEquals(country, address.getCountry());
	}
	
	// Test for Administrator persistence 
	@Test
	public void testPersistAndLoadAdministrator() {
		
		// Creating Administrator object with test data
		String email = "admin@mail.ca";
		String password = "adminPassword";
		String firstName = "adminFirst";
		String lastName = "adminLast";
		String phoneNumber = "123456";
		Administrator administrator = createTestAdministrator(email, password, firstName, lastName, phoneNumber);
		
		// Saving object to DB
		administratorRepository.save(administrator);
		
		administrator = null;
		
		// Pulling object from DB
		administrator = administratorRepository.findAdministratorByEmail(email);
		
		// Testing object pulls correctly
		assertNotNull(administrator);
		assertEquals(email, administrator.getEmail());
		assertEquals(password, administrator.getPassword());
		assertEquals(firstName, administrator.getFirstName());
		assertEquals(lastName, administrator.getLastName());
		assertEquals(phoneNumber, administrator.getPhoneNumber());
	}
	
	// Test for Artist persistence 
	@Test
	public void testPersistAndLoadArtist() {
				
		// Creating Administrator object with test data
		String email = "artist@mail.ca";
		String password = "artistPassword";
		String firstName = "artistfirst";
		String lastName = "artistlast";
		String phoneNumber = "123456";
		String artistDescription = "artistTestDescription";
		Float rating = 4.2f;

		Artist artist = createTestArtistWithoutReviewOrArtworks(email, password, firstName, lastName, phoneNumber, artistDescription, rating);

		// Saving object to DB
		artistRepository.save(artist);
		
		artist = null;
		
		// Pulling object from DB
		artist = artistRepository.findArtistByEmail(email);
		
		// Testing object pulls correctly
		assertNotNull(artist);
		assertEquals(email, artist.getEmail());
		assertEquals(password, artist.getPassword());
		assertEquals(firstName, artist.getFirstName());
		assertEquals(lastName, artist.getLastName());
		assertEquals(phoneNumber, artist.getPhoneNumber());
		assertEquals(rating, artist.getRating());

	}
	
	// Test for Artist persistence 
	@Test
	public void testPersistAndLoadCustomer() {
				
		// Creating Address object with test data and saving to DB
		Integer addressID = 1;
		String addressLine1 = "123 Test st.";
		String addressLine2 = "Apt 102";
		String city = "testCity";
		String province = "testProvince";
		String postalCode = "A1B2C3";
		String country = "Canada";
		Address address = createTestAddress(addressID, addressLine1, addressLine2, city, province, postalCode, country);
		addressRepository.save(address);
		
		// Creating Customer object with test data
		String email = "customer@mail.ca";
		String password = "customerPassword";
		String firstName = "customerFirst";
		String lastName = "customerLast";
		String phoneNumber = "123456";
		Customer customer = createTestCustomerWithoutAddress(email ,password , firstName, lastName, phoneNumber);
		customer.setAddress(address);
		
		// Saving object to DB
		customerRepository.save(customer);
		
		customer = null;
		
		// Pulling object from DB
		customer = customerRepository.findCustomerByEmail(email);
		
		// Testing object pulls correctly
		assertNotNull(customer);
		assertEquals(email, customer.getEmail());
		assertEquals(password, customer.getPassword());
		assertEquals(firstName, customer.getFirstName());
		assertEquals(lastName, customer.getLastName());
		assertEquals(phoneNumber, customer.getPhoneNumber());
		
		// Comparing by keys
		assertEquals(address.getAddressID(), customer.getAddress().getAddressID());

	}
	
	// Test for Artwork persistence 
	@Test
	public void testPersistAndLoadArtwork() {
		
		// Creating Artist object with test data and saving to DB
		String email = "artist@mail.ca";
		String password = "artistPassword";
		String firstName = "artistfirst";
		String lastName = "artistlast";
		String phoneNumber = "123456";
		String artistDescription = "artistTestDescription";
		Float rating = 4.2f;
		Artist artist = createTestArtistWithoutReviewOrArtworks(email, password, firstName, lastName, phoneNumber, artistDescription, rating);
		artistRepository.save(artist);
		
		// Creating Artwork object with test data
		Integer artworkID = 122;
		String name = "ArtworkTestName";
		String description = "Artwork description test";
		Float price = 1500.f;
		Date dateOfCreation = java.sql.Date.valueOf(LocalDate.of(2020, Month.SEPTEMBER, 15));
		Integer numInStock = 3;
		Artwork artwork = createTestArtworkWithoutArtist(artworkID , name, description, price, dateOfCreation, numInStock);
		artwork.setArtist(artist);
		
		// Saving object to DB
		artworkRepository.save(artwork);
		
		artwork = null;
		
		// Pulling object from DB
		artwork = artworkRepository.findArtworkByArtworkID(artworkID);
		
		// Testing object pulls correctly
		assertNotNull(artwork);
		assertEquals(artworkID, artwork.getArtworkID());
		assertEquals(name, artwork.getName());
		assertEquals(description, artwork.getDescription());
		assertEquals(price, artwork.getPrice());
		assertEquals(dateOfCreation, artwork.getDateOfCreation());
		assertEquals(numInStock, artwork.getNumInStock());
		
		// Comparing by keys
		assertEquals(artist.getEmail(), artwork.getArtist().getEmail());
	}
	
	// Test for Review persistence 

	@Test
	public void testPersistAndLoadReview() {
		
		// Creating Customer object with test data and saving to DB
		String customerEmail = "customer@mail.ca";
		String customerPassword = "customerPassword";
		String customerFirstName = "customerFirst";
		String customerLastName = "customerLast";
		String customerPhoneNumber = "123456";
		Customer customer = createTestCustomerWithoutAddress(customerEmail, customerPassword, customerFirstName, customerLastName, customerPhoneNumber);
		customerRepository.save(customer);
		
		// Creating Artist object with test data and saving to DB
		String artistEmail = "artist@mail.ca";
		String artistPassword = "artistPassword";
		String artistFirstName = "artistfirst";
		String artistLastName = "artistlast";
		String artistPhoneNumber = "123456";
		String artistDescription = "artistTestDescription";
		Float artistRating = 4.2f;
		Artist artist = createTestArtistWithoutReviewOrArtworks(artistEmail, artistPassword, artistFirstName, artistLastName, artistPhoneNumber, artistDescription, artistRating);
		artistRepository.save(artist);
		
		// Creating Review object with test data
		Integer reviewID = 39;
		Integer rating = 4;
		String comment = "Test Comment 123";
		Boolean wouldRecommend = true;
		Review review = new Review();
		review.setReviewID(reviewID);
		review.setRating(rating);
		review.setComment(comment);
		review.setWouldRecommend(wouldRecommend);
		review.setCustomer(customer);
		review.setArtist(artist);

		// Saving object to DB
		reviewRepository.save(review);
		
		review = null;
		
		// Pulling object from DB
		review = reviewRepository.findReviewByReviewID(reviewID);
		
		// Testing object pulls correctly
		assertNotNull(review);
		assertEquals(reviewID, review.getReviewID());
		assertEquals(rating, review.getRating());
		assertEquals(comment, review.getComment());
		assertEquals(wouldRecommend, review.getWouldRecommend());
		
		// Comparing by keys
		assertEquals(customer.getEmail(), review.getCustomer().getEmail());
		assertEquals(artist.getEmail(), review.getArtist().getEmail());
	}
		
	// Test for ArtworkOrder persistence 
	@Test
	public void testPersistAndLoadArtworkOrder() {
		
		// Creating Customer object with test data and saving to DB
		String customerEmail = "customer@mail.ca";
		String customerPassword = "customerPassword";
		String customerFirstName = "customerFirst";
		String customerLastName = "customerLast";
		String customerPhoneNumber = "123456";
		Customer customer = createTestCustomerWithoutAddress(customerEmail, customerPassword, customerFirstName, customerLastName, customerPhoneNumber);
		customerRepository.save(customer);
		
		// Creating an ArtworkOrder object with test data 
		Integer orderID = 32;
		Float totalPrice = 12345.f;
		Date datePlaced = java.sql.Date.valueOf(LocalDate.of(2020, Month.OCTOBER, 15));;
		Date dateCompleted = java.sql.Date.valueOf(LocalDate.of(2020, Month.OCTOBER, 19));;
		DeliveryMethod deliveryMethod = DeliveryMethod.SHIP;
		OrderStatus orderStatus = OrderStatus.PROCESSING;
		
		ArtworkOrder order = new ArtworkOrder();
		
		order.setOrderID(orderID);
		order.setTotalPrice(totalPrice);
		order.setDatePlaced(datePlaced);
		order.setDateCompleted(dateCompleted);
		order.setDeliveryMethod(deliveryMethod);
		order.setOrderStatus(orderStatus);
		order.setCustomer(customer);
		
		// Saving object to DB
		artworkOrderRepository.save(order);
		
		order = null;
		
		// Pulling object from DB
		order = artworkOrderRepository.findArtworkOrderByOrderID(orderID);
		
		// Testing object pulls correctly
		assertNotNull(order);
		assertEquals(orderID, order.getOrderID());
		assertEquals(totalPrice, order.getTotalPrice());
		assertEquals(datePlaced, order.getDatePlaced());
		assertEquals(dateCompleted, order.getDateCompleted());
		assertEquals(deliveryMethod, order.getDeliveryMethod());
		
		// Comparing by keys
		assertEquals(orderStatus, order.getOrderStatus());
		assertEquals(customer.getEmail(), order.getCustomer().getEmail());

	}


	// Test constructors to simplify object creation for testing
	
	public Address createTestAddress(Integer addressID, String addressLine1, String addressLine2, String city, String province, String postalCode, String country) {
		Address address = new Address();
		address.setAddressID(addressID);
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setProvince(province);
		address.setPostalCode(postalCode);
		address.setCountry(country);
		return address;
	}
	
	public Administrator createTestAdministrator(String email, String password, String firstName, String lastName, String phoneNumber){
		Administrator administrator = new Administrator();
		administrator.setEmail(email);
		administrator.setPassword(password);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPhoneNumber(phoneNumber);
		return administrator;
	}
	
	public Customer createTestCustomerWithoutAddress(String email, String password, String firstName, String lastName, String phoneNumber){
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhoneNumber(phoneNumber);
		return customer;
	}
	
	public Artist createTestArtistWithoutReviewOrArtworks(String email, String password, String firstName, String lastName, String phoneNumber, String artistDescription, Float rating){
		Artist artist = new Artist();
		artist.setEmail(email);
		artist.setPassword(password);
		artist.setFirstName(firstName);
		artist.setLastName(lastName);
		artist.setPhoneNumber(phoneNumber);
		artist.setArtistDescription(artistDescription);
		artist.setRating(rating);
		return artist;
	}
	
	public Artwork createTestArtworkWithoutArtist(Integer artworkID , String name, String description, Float price, Date dateOfCreation , Integer numInStock){		
		Artwork artwork = new Artwork();
		artwork.setArtworkID(artworkID);
		artwork.setName(name);
		artwork.setDescription(description);
		artwork.setPrice(price);
		artwork.setDateOfCreation(dateOfCreation);
		artwork.setNumInStock(numInStock);
		return artwork;
	}
	
}
