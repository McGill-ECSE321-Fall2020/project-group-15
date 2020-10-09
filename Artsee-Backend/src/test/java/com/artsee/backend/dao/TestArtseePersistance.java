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
import com.artsee.backend.model.ArtworkOrder;
import com.artsee.backend.model.Review;
import com.artsee.backend.model.EndUser;

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

	@AfterEach
	public void clearDatabase() {
		
		
		
		// Clear Artist before Artwork and Review to avoid inconsistency
		artistRepository.deleteAll();
		artworkRepository.deleteAll();
		reviewRepository.deleteAll();

		//Clear Review before Customer before Address to avoid inconsistency
		customerRepository.deleteAll();
		addressRepository.deleteAll();

		
		// Has no references, can delete in any order
		administratorRepository.deleteAll();
				
		
		// Then we can clear the other tables
		artworkOrderRepository.deleteAll();
		reviewRepository.deleteAll();
		endUserRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadAddress() {
		Integer addressID = 1;
		String addressLine1 = "123 Test st.";
		String addressLine2 = "Apt 102";
		String city = "testCity";
		String province = "testProvince";
		String postalCode = "A1B2C3";
		String country = "Canada";
				
		Address address = new Address();
		address.setAddressID(addressID);
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setProvince(province);
		address.setPostalCode(postalCode);
		address.setCountry(country);

		addressRepository.save(address);
		
		address = null;
		
		address = addressRepository.findAddressByAddressID(addressID);
		
		assertNotNull(address);
		assertEquals(addressID, address.getAddressID());
		assertEquals(addressLine1, address.getAddressLine1());
		assertEquals(addressLine2, address.getAddressLine2());
		assertEquals(city, address.getCity());
		assertEquals(province, address.getProvince());
		assertEquals(postalCode, address.getPostalCode());
		assertEquals(country, address.getCountry());

	}
	
	@Test
	public void testPersistAndLoadAdministrator() {
		String email = "admin@mail.ca";
		String password = "adminPassword";
		String firstName = "adminFirst";
		String lastName = "adminLast";
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
		assertEquals(password, administrator.getPassword());
		assertEquals(firstName, administrator.getFirstName());
		assertEquals(lastName, administrator.getLastName());
		assertEquals(phoneNumber, administrator.getPhoneNumber());
	}
	
	@Test
	public void testPersistAndLoadArtist() {
		
		//TODO ADD IN REVIEWS **************
		
		String email = "artist@mail.ca";
		String password = "artistPassword";
		String firstName = "artistfirst";
		String lastName = "artistlast";
		String phoneNumber = "123456";
		String artistDescription = "artistTestDescription";
		Float rating = 4.2f;

		
		Artist artist = new Artist();
		artist.setEmail(email);
		artist.setPassword(password);
		artist.setFirstName(firstName);
		artist.setLastName(lastName);
		artist.setPhoneNumber(phoneNumber);
		artist.setArtistDescription(artistDescription);
		artist.setRating(rating);

		
		artistRepository.save(artist);
		
		artist = null;
		
		artist = artistRepository.findArtistByEmail(email);
		
		assertNotNull(artist);
		assertEquals(email, artist.getEmail());
		assertEquals(password, artist.getPassword());
		assertEquals(firstName, artist.getFirstName());
		assertEquals(lastName, artist.getLastName());
		assertEquals(phoneNumber, artist.getPhoneNumber());
		assertEquals(rating, artist.getRating());

	}
	
	@Test
	public void testPersistAndLoadArtwork() {
		
		//TODO ADD IN ARTIST  **************
//		String email = "artist@mail.ca";
//		String password = "artistPassword";
//		String firstName = "artistfirst";
//		String lastName = "artistlast";
//		String phoneNumber = "123456";
//		String artistDescription = "artistTestDescription";
//		Float rating = 4.2f;
//
//		
//		Artist artist = new Artist();
//		artist.setEmail(email);
//		artist.setPassword(password);
//		artist.setFirstName(firstName);
//		artist.setLastName(lastName);
//		artist.setPhoneNumber(phoneNumber);
//		artist.setArtistDescription(artistDescription);
//		artist.setRating(rating);
//		artistRepository.save(artist);
		
		Integer artworkID = 122;
		String name = "ArtworkTestName";
		String description = "Artwork description test";
		Float price = 1500.f;
		Date dateOfCreation = java.sql.Date.valueOf(LocalDate.of(2020, Month.SEPTEMBER, 15));
		Integer numInStock = 3;

		
		Artwork artwork = new Artwork();
		artwork.setArtworkID(artworkID);
		artwork.setName(name);
		artwork.setDescription(description);
		artwork.setPrice(price);
		artwork.setDateOfCreation(dateOfCreation);
		artwork.setNumInStock(numInStock);
//		artwork.setArtist(artist);

		
		artworkRepository.save(artwork);
		
		artwork = null;
		
		artwork = artworkRepository.findArtworkByArtworkID(artworkID);
		
		assertNotNull(artwork);
		assertEquals(artworkID, artwork.getArtworkID());
		assertEquals(name, artwork.getName());
		assertEquals(description, artwork.getDescription());
		assertEquals(price, artwork.getPrice());
		assertEquals(dateOfCreation, artwork.getDateOfCreation());
		assertEquals(numInStock, artwork.getNumInStock());
//		assertEquals(artist, artwork.getArtist());

	}
	
	@Test
	public void testPersistAndLoadCustomer() {
		
		//TODO ADD IN ADDRESS  **************
		
		Integer addressID = 1;
		String addressLine1 = "123 Test st.";
		String addressLine2 = "Apt 102";
		String city = "testCity";
		String province = "testProvince";
		String postalCode = "A1B2C3";
		String country = "Canada";
				
		Address address = new Address();
		address.setAddressID(addressID);
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setProvince(province);
		address.setPostalCode(postalCode);
		address.setCountry(country);

		addressRepository.save(address);
		
		String email = "customer@mail.ca";
		String password = "customerPassword";
		String firstName = "customerFirst";
		String lastName = "customerLast";
		String phoneNumber = "123456";
		
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhoneNumber(phoneNumber);
		customer.setAddress(address);
				
		customerRepository.save(customer);
		
		customer = null;
		
		customer = customerRepository.findCustomerByEmail(email);
		
//		assertNotNull(customer);
//		assertEquals(email, customer.getEmail());
//		assertEquals(password, customer.getPassword());
//		assertEquals(firstName, customer.getFirstName());
//		assertEquals(lastName, customer.getLastName());
//		assertEquals(phoneNumber, customer.getPhoneNumber());
//		assertEquals(address.getAddressID(), customer.getAddress().getAddressID());

	}

	@Test
	public void testPersistAndLoadOrder() {
		
		//TODO ADD IN ORDERSTATUS, DELIVERY METHOD, ARTWORKS, CUSTOMER  **************
		
		Integer orderID = 32;
		Float totalPrice = 12345.f;
		Date datePlaced = java.sql.Date.valueOf(LocalDate.of(2020, Month.OCTOBER, 15));;
		Date dateCompleted = java.sql.Date.valueOf(LocalDate.of(2020, Month.OCTOBER, 19));;
//		DeliveryMethod deliveryMethod = DeliveryMethod.SHIP;
//		OrderStatus orderStatus = 
		
		ArtworkOrder order = new ArtworkOrder();
		order.setOrderID(orderID);
		order.setTotalPrice(totalPrice);
		order.setDatePlaced(datePlaced);
		order.setDateCompleted(dateCompleted);
//		order.setDeliveryMethod(deliveryMethod);
//		order.setOrderStatus(orderStatus);
//		order.setArtworks(artworks);
//		order.setCustomer(customer);
		
		artworkOrderRepository.save(order);
		
		order = null;
		
		order = artworkOrderRepository.findArtworkOrderByOrderID(orderID);
		
		assertNotNull(order);
		assertEquals(orderID, order.getOrderID());
		assertEquals(totalPrice, order.getTotalPrice());
		assertEquals(datePlaced, order.getDatePlaced());
		assertEquals(dateCompleted, order.getDateCompleted());
//		assertEquals(deliveryMethod, order.getDeliveryMethod());
//		assertEquals(orderStatus, order.getOrderStatus());
//		assertEquals(artworks, order.getArtworks());
//		assertEquals(customer, order.getCustomer());

	}
	
	@Test
	public void testPersistAndLoadReview() {
		
		//TODO ADD IN CUSTOMER, Artist??  **************
		
		Integer reviewID = 39;
		Integer rating = 4;
		String comment = "Test Comment 123";
		Boolean wouldRecommend = true;
		
		Review review = new Review();
		review.setReviewID(reviewID);
		review.setRating(rating);
		review.setComment(comment);
		review.setWouldRecommend(wouldRecommend);
		
		reviewRepository.save(review);
		
		review = null;
		
		review = reviewRepository.findReviewByReviewID(reviewID);
		
		assertNotNull(review);
		assertEquals(reviewID, review.getReviewID());
		assertEquals(rating, review.getRating());
		assertEquals(comment, review.getComment());
		assertEquals(wouldRecommend, review.getWouldRecommend());
	}

}
