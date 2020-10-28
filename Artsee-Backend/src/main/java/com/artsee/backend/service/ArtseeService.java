package com.artsee.backend.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsee.backend.model.*;
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
	
	// End User Service Layer ___________________________________________________________________________________
	
	public EndUser getUserByID(String userID) {
		EndUser user = endUserRepository.findById(userID).orElse(null);
		return user;
	}
	
	public EndUser getUserByEmail(String email) {
		EndUser user = endUserRepository.findByEmail(email);
		return user;
	}
	
	public List<EndUser> getAllUsers() {
		return toList(endUserRepository.findAll());
	}
	
	public String deleteUser(String userID) {
		endUserRepository.deleteById(userID);
		return userID;
	}
	
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
	
	public Customer getCustomerByID(String customerID) {
		Customer customer = customerRepository.findById(customerID).orElse(null);
		return customer;
	}
	
	public Customer getCustomerByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		return customer;
	}
	
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}
	
	public String deleteCustomer(String customerID) {
		endUserRepository.deleteById(customerID);
		return customerID;
	}
	
	public Customer updateCustomer(String customerID, String email, String password, String firstName, String lastName, String phoneNumber, Address address){
		Customer customer = customerRepository.findById(customerID).orElse(null);
		
		if (customer!=null) {
			customer.setEmail(email);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setPassword(password);
			customer.setPhoneNumber(phoneNumber);
			customer.setAddress(address);
			endUserRepository.save(customer);
		}
		
		return customer;
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
	public Review getReview(Integer reviewID) {
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
		review.setRating(rating);
		review.setComment(comment);
		review.setWouldRecommend(wouldRecommend);
		review.setCustomer(customer);
		review.setArtist(artist);
		reviewRepository.save(review);
		return review;
	}
	
	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
