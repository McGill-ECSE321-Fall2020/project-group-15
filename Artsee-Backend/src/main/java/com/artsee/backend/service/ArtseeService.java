package com.artsee.backend.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsee.backend.model.Address;
import com.artsee.backend.model.Administrator;
import com.artsee.backend.model.Artist;
import com.artsee.backend.model.Artwork;
import com.artsee.backend.model.ArtworkOrder;
import com.artsee.backend.model.Customer;
import com.artsee.backend.model.EndUser;
import com.artsee.backend.model.Review;
import com.artsee.backend.model.DeliveryMethod;
import com.artsee.backend.model.OrderStatus;

import com.artsee.backend.dao.*;

@Service
public class ArtseeService {
    
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
	
	@Transactional
	public EndUser signIn(String userID, String password) throws IllegalArgumentException {
		EndUser user = endUserRepository.findById(userID).orElse(null);
		if(user == null) {
			throw new IllegalArgumentException("Username does not exits");
		} else if(!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Password is incorrect");
		}
		return user;
	}
	
	// End User Service Layer ___________________________________________________________________________________
	
	@Transactional
	public EndUser getUser(String userID) {
		EndUser user = endUserRepository.findById(userID).orElse(null);
		
		return user;
	}
	
	@Transactional
	public List<EndUser> getAllUsers() {
		return toList(endUserRepository.findAll());
	}
	
	@Transactional
	public String deleteUser(String userID) {
		endUserRepository.deleteById(userID);
		return userID;
	}
	
	@Transactional
	public EndUser updateUser(String userID, String email, String password, String firstName, String lastName, String phoneNumber){
		EndUser user = endUserRepository.findById(userID).orElse(null);
		
		if (user!=null) {
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setPhoneNumber(phoneNumber);
			endUserRepository.save(user);
		}
		
		return user;
	}
	
	// Customer Service Layer ___________________________________________________________________________________
	
	@Transactional
	public Customer createCustomer(String customerID, String email, String password, String firstName, String lastName, String phoneNumber, Address address) {
		Customer customer= new Customer();
		customer.setUserID(customerID);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhoneNumber(phoneNumber);
		customer.setAddress(address);
		customerRepository.save(customer);
		return customer;
	}
	
	@Transactional
	public Customer getCustomerByID(String customerID) {
		Customer customer = customerRepository.findById(customerID).orElse(null);
		return customer;
	}
	
	@Transactional
	public Customer getCustomerByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		return customer;
	}
	
	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}
	
	@Transactional
	public String deleteCustomer(String customerID) {
		customerRepository.deleteById(customerID);
		return customerID;
	}
	
	@Transactional
	public Customer updateCustomer(String customerID, String email, String password, String firstName, String lastName, String phoneNumber, Address address){
		Customer customer = customerRepository.findById(customerID).orElse(null);
		
		if (customer!=null) {
			customer.setEmail(email);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setPassword(password);
			customer.setPhoneNumber(phoneNumber);
			customer.setAddress(address);
			customerRepository.save(customer);
		}
		
		return customer;
	}

	// Artist Service Layer ___________________________________________________________________________________

	@Transactional
	public Artist createArtist(String artistID, String email, String password, String firstName, String lastName, String phoneNumber,  String artistDescription){
		Artist artist= new Artist();
		artist.setUserID(artistID);
		artist.setEmail(email);
		artist.setPassword(password);
		artist.setFirstName(firstName);
		artist.setLastName(lastName);
		artist.setArtistDescription(artistDescription);
		artist.setPhoneNumber(phoneNumber);
		artistRepository.save(artist);
		return artist;
	}
	
	@Transactional
	public Artist getArtistByID(String artistID) {
		Artist artist = artistRepository.findById(artistID).orElse(null);
		return artist;
	}
	
	@Transactional
	public Artist getArtistByEmail(String email) {
		Artist artist = artistRepository.findByEmail(email);
		return artist;
	}
	
	@Transactional
	public List<Artist> getAllArtists() {
		return toList(artistRepository.findAll());
	}
	
	@Transactional
	public String deleteArtist(String artistID) {
		artistRepository.deleteById(artistID);
		return artistID;
	}
	
	@Transactional
	public Float getArtistRating(String artistID) {
		Artist artist = artistRepository.findById(artistID).orElse(null);
		Float totalRatings = 0f;
		if(artist != null) {
			for (Review r : reviewRepository.findByArtist(artist)) {
				totalRatings += (float)r.getRating();
			}
			totalRatings = totalRatings/((float)reviewRepository.findByArtist(artist).size());
		}
		
		return totalRatings;
	}
	
	@Transactional
	public Artist updateArtist(String artistID, String email, String password, String firstName, String lastName, String phoneNumber, String artistDescription){
		Artist artist = artistRepository.findById(artistID).orElse(null);
		
		if (artist!=null) {
			artist.setEmail(email);
			artist.setFirstName(firstName);
			artist.setLastName(lastName);
			artist.setPassword(password);
			artist.setPhoneNumber(phoneNumber);
			artist.setArtistDescription(artistDescription);
			artistRepository.save(artist);
		}
		
		return artist;
	}

	
	// Admin Service Layer ___________________________________________________________________________________

	@Transactional
	public Administrator createAdministrator(String administratorID, String email, String password, String firstName, String lastName, String phoneNumber, Address address) {
		Administrator administrator = new Administrator();
		administrator.setUserID(administratorID);
		administrator.setEmail(email);
		administrator.setPassword(password);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPhoneNumber(phoneNumber);
		administratorRepository.save(administrator);
		return administrator;
	}
	
	@Transactional
	public Administrator getAdministratorByID(String administratorID) {
		Administrator administrator = administratorRepository.findById(administratorID).orElse(null);
		return administrator;
	}
	
	@Transactional
	public Administrator getAdministratorByEmail(String email) {
		Administrator administrator = administratorRepository.findByEmail(email);
		return administrator;
	}
	
	@Transactional
	public List<Administrator> getAllAdministrators() {
		return toList(administratorRepository.findAll());
	}
	
	@Transactional
	public String deleteAdministrator(String administratorID) {
		administratorRepository.deleteById(administratorID);
		return administratorID;
	}
	
	@Transactional
	public Administrator updateAdministrator(String administratorID, String email, String password, String firstName, String lastName, String phoneNumber){
		Administrator administrator = administratorRepository.findById(administratorID).orElse(null);
		
		if (administrator!=null) {
			administrator.setEmail(email);
			administrator.setFirstName(firstName);
			administrator.setLastName(lastName);
			administrator.setPassword(password);
			administrator.setPhoneNumber(phoneNumber);
			administratorRepository.save(administrator);
		}
		
		return administrator;
	}
	
	// Review Service Layer ___________________________________________________________________________________
	
	@Transactional
	public Review createReview(Integer rating, String comment, Boolean wouldRecommend, Customer customer, Artist artist) {
		Review review = new Review();
		review.setRating(rating);
		review.setComment(comment);
		review.setWouldRecommend(wouldRecommend);
		review.setCustomer(customer);
		review.setArtist(artist);
		reviewRepository.save(review);
		return review;
	}

	@Transactional
	public Review getReviewbyID(Integer reviewID) {
		Review review = reviewRepository.findById(reviewID).orElse(null);
		return review;
	}
	
	@Transactional
	public Integer deleteReview(Integer reviewID) {
		reviewRepository.deleteById(reviewID);
		return reviewID;
	}

	@Transactional
	public Review updateReview(Integer reviewID, Integer rating, String comment, Boolean wouldRecommend, Customer customer, Artist artist) {
		Review review = reviewRepository.findById(reviewID).orElse(null);
		if(review != null) {
			review.setRating(rating);
			review.setComment(comment);
			review.setWouldRecommend(wouldRecommend);
			review.setCustomer(customer);
			review.setArtist(artist);
			reviewRepository.save(review);
		}
		return review;
	}
	
	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	@Transactional
	public List<Review> getAllReviewsOnArtist(Artist artist) {
		List<Review> reviewsOnArtist = new ArrayList<>();
		for (Review r : reviewRepository.findByArtist(artist)) {
			reviewsOnArtist.add(r);
		}
		return reviewsOnArtist;
	}
	
	@Transactional
	public List<Review> getAllReviewsByCustomer(Customer customer) {
		List<Review> reviewsByCustomer = new ArrayList<>();
		for (Review r : reviewRepository.findByCustomer(customer)) {
			reviewsByCustomer.add(r);
		}
		return reviewsByCustomer;
	}
	
	// Artwork Order Service Layer ___________________________________________________________________________________
	
	@Transactional
	public ArtworkOrder createArtworkOrder(Integer totalPrice, Date datePlaced, Date dateCompleted, DeliveryMethod deliveryMethod, OrderStatus orderStatus, Customer customer) {
		ArtworkOrder order = new ArtworkOrder();
		order.setTotalPrice(totalPrice);
		order.setDatePlaced(datePlaced);
		order.setDateCompleted(dateCompleted);
		order.setDeliveryMethod(deliveryMethod);
		order.setOrderStatus(orderStatus);
		order.setCustomer(customer);
		artworkOrderRepository.save(order);
		return order;
	}

	@Transactional
	public ArtworkOrder getArtworkOrderByID(Integer orderID) {
		ArtworkOrder order = artworkOrderRepository.findById(orderID).orElse(null);
		return order;
	}
	
	@Transactional
	public Integer deleteArtworkOrder(Integer orderID) {
		artworkOrderRepository.deleteById(orderID);
		return orderID;
	}

	@Transactional
	public ArtworkOrder updateArtworkOrder(Integer orderID, Integer totalPrice, Date datePlaced, Date dateCompleted, DeliveryMethod deliveryMethod, OrderStatus orderStatus, Customer customer) {
		ArtworkOrder order = artworkOrderRepository.findById(orderID).orElse(null);
		if (order != null) {
			order.setTotalPrice(totalPrice);
			order.setDatePlaced(datePlaced);
			order.setDateCompleted(dateCompleted);
			order.setDeliveryMethod(deliveryMethod);
			order.setOrderStatus(orderStatus);
			order.setCustomer(customer);
			artworkOrderRepository.save(order);
		}
		return order;
	}
	
	@Transactional
	public List<ArtworkOrder> getAllArtworkOrders() {
		return toList(artworkOrderRepository.findAll());
	}
	
	@Transactional
	public List<ArtworkOrder> getAllArtworkOrdersByCustomer(Customer customer) {
		List<ArtworkOrder> artworkOrdersByCustomer = new ArrayList<>();
		for (ArtworkOrder r : artworkOrderRepository.findByCustomer(customer)) {
			artworkOrdersByCustomer.add(r);
		}
		return artworkOrdersByCustomer;
	}
	
	// Address Service Layer ___________________________________________________________________________________
	
	@Transactional
	public Address createAddress(String addressLine1, String addressLine2, String city, String province, String postalCode, String country) {
		Address address = new Address();
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setProvince(province);
		address.setPostalCode(postalCode);
		address.setCountry(country);
		addressRepository.save(address);
		return address;
	}

	@Transactional
	public Address getAddressById(Integer addressID) {
		Address address = addressRepository.findById(addressID).orElse(null);
		return address;
	}
	
	@Transactional
	public Integer deleteAddress(Integer addressID) {
		addressRepository.deleteById(addressID);
		return addressID;
	}

	@Transactional
	public Address updateAddress(Integer addressID, String addressLine1, String addressLine2, String city, String province, String postalCode, String country) {
		Address address = addressRepository.findById(addressID).orElse(null);
		if(address != null) {
			address.setAddressLine1(addressLine1);
			address.setAddressLine2(addressLine2);
			address.setCity(city);
			address.setProvince(province);
			address.setPostalCode(postalCode);
			address.setCountry(country);
			addressRepository.save(address);
		}
		return address;
	}
	
	@Transactional
	public List<Address> getAllAddresses() {
		return toList(addressRepository.findAll());
	}
	
	// Helper Method ___________________________________________________________________________________
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
