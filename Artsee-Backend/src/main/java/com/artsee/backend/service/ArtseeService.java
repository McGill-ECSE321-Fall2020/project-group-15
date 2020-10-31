package com.artsee.backend.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
			throw new IllegalArgumentException("Username cannot be found.");
		} else if(!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Password is incorrect.");
		}
		return user;
	}
	
	// End User Service Layer ___________________________________________________________________________________
	
	@Transactional
	public EndUser getUser(String userID) throws IllegalArgumentException {
		EndUser user = endUserRepository.findById(userID).orElse(null);
		
		if (user == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		return user;
	}
	
	@Transactional
	public List<EndUser> getAllUsers(){
		return toList(endUserRepository.findAll());
	}
	
	@Transactional
	public EndUser deleteUser(String userID) throws IllegalArgumentException{
		EndUser user = endUserRepository.findById(userID).orElse(null);
		
		if (user == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		endUserRepository.delete(user);
		
		return user;
	}
	
	@Transactional
	public EndUser updateUser(String userID, String email, String password, String firstName, String lastName, String phoneNumber) throws IllegalArgumentException {
		
		String e = "";
		
		if (nonValidString(userID)) {
			throw new IllegalArgumentException("Must enter a username.");
		}
	
		EndUser user = endUserRepository.findById(userID).orElse(null);
		
		
		if (nonValidString(email)) {
			e += "Must enter an email. ";
		}
		
		if (user == null) {
			e += "Username cannot be found.";
		}
		
		if ((!email.equals(user.getEmail())&&(endUserRepository.findByEmail(email) != null))) {
			e += "Email already exists. ";
		}
		
		if (nonValidString(password)) {
			e += "Must enter a password. ";
		}
		
		if (nonValidString(firstName)) {
			e+="Must enter a first name. ";
		}
		
		if (nonValidString(lastName)) {
			e+= "Must enter a last name. ";
		}
		
		if (!nonValidString(e)) {
			throw new IllegalArgumentException(e.trim());
		}
		
		
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		endUserRepository.save(user);
		
		
		return user;
	}
	
	// Customer Service Layer ___________________________________________________________________________________
	
	@Transactional
	public Customer createCustomer(String userID, String email, String password, String firstName, String lastName, String phoneNumber, Address address) throws IllegalArgumentException{
		Customer customer= new Customer();
		String e = "";
		
		if (nonValidString(userID)) {
			e += "Must enter a username. ";
		}
		
		if (nonValidString(email)) {
			e += "Must enter an email. ";
		}
		
		if (endUserRepository.findById(userID).orElse(null) != null) {
			e+="Username already exists. ";
		}
		
		if (endUserRepository.findByEmail(email) != null) {
			e+="Email already exists. ";
		}

		if (nonValidString(password)) {
			e += "Must enter a password. ";
		}
		
		if (nonValidString(firstName)) {
			e+="Must enter a first name. ";
		}
		
		if (nonValidString(lastName)) {
			e+= "Must enter a last name. ";
		}
		
		if (!nonValidString(e)) {
			throw new IllegalArgumentException(e.trim());
		}
		
		customer.setUserID(userID);
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
	public Customer getCustomerByID(String userID) throws IllegalArgumentException{
		Customer customer = customerRepository.findById(userID).orElse(null);
		
		if (customer == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		return customer;
	}
	
	@Transactional
	public Customer getCustomerByEmail(String email) throws IllegalArgumentException{
		Customer customer = customerRepository.findByEmail(email);
		
		if (customer == null) {
			throw new IllegalArgumentException("Email cannot be found.");
		}
		
		return customer;
	}
	
	@Transactional
	public List<Customer> getAllCustomers(){
		return toList(customerRepository.findAll());
	}
	
	@Transactional
	public Customer deleteCustomer(String userID) throws IllegalArgumentException{
		Customer customer = customerRepository.findById(userID).orElse(null);

		if (customer == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		customerRepository.delete(customer);
		return customer;
	}
	
	@Transactional
	public Customer updateCustomer(String userID, String email, String password, String firstName, String lastName, String phoneNumber, Address address) throws IllegalArgumentException{
		String e="";
		
		if (nonValidString(userID)) {
			throw new IllegalArgumentException("Must enter a username.");
		}
	
		Customer customer = customerRepository.findById(userID).orElse(null);
		
		if (nonValidString(email)) {
			e += "Must enter an email. ";
		}
		
		if (customer == null) {
			e += "Username cannot be found.";
		}
		
		if (!email.equals(customer.getEmail())&&(endUserRepository.findByEmail(email) != null)) {
			e+="Email already exists.";
		}
		
		if (nonValidString(password)) {
			e += "Must enter a password. ";
		}
		
		if (nonValidString(firstName)) {
			e+="Must enter a first name. ";
		}
		
		if (nonValidString(lastName)) {
			e+= "Must enter a last name. ";
		}
		
		if (!nonValidString(e)) {
			throw new IllegalArgumentException(e.trim());
		}
		
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPassword(password);
		customer.setPhoneNumber(phoneNumber);
		customer.setAddress(address);
		customerRepository.save(customer);
		
		
		return customer;
	}

	// Artist Service Layer ___________________________________________________________________________________

	@Transactional
	public Artist createArtist(String userID, String email, String password, String firstName, String lastName, String phoneNumber,  String artistDescription) throws IllegalArgumentException{
		Artist artist= new Artist();
		String e = "";
		
		if (nonValidString(userID)) {
			throw new IllegalArgumentException("Must enter a username.");
		} else {
			artist = artistRepository.findById(userID).orElse(null);
		}
		
		if (nonValidString(email)) {
			e += "Must enter an email. ";
		}
		
		if (endUserRepository.findById(userID).orElse(null) != null) {
			e += "Username already exists.";
		}
		
		if (endUserRepository.findByEmail(email) != null) {
			e += "Email already exists.";
		}
		
		if (nonValidString(password)) {
			e += "Must enter a password. ";
		}
		
		if (nonValidString(firstName)) {
			e += "Must enter a first name. ";
		}
		
		if (nonValidString(lastName)) {
			e += "Must enter a last name. ";
		}
		
		if (!nonValidString(e)) {
			throw new IllegalArgumentException(e.trim());
		}
		
		artist.setUserID(userID);
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
	public Artist getArtistByID(String userID) throws IllegalArgumentException{
		Artist artist = artistRepository.findById(userID).orElse(null);
		
		if (artist == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		return artist;
	}
	
	@Transactional
	public Artist getArtistByEmail(String email) throws IllegalArgumentException{
		Artist artist = artistRepository.findByEmail(email);
		
		if (artist == null) {
			throw new IllegalArgumentException("Email cannot be found.");
		}
		
		return artist;
	}
	
	@Transactional
	public List<Artist> getAllArtists(){
		return toList(artistRepository.findAll());
	}
	
	@Transactional
	public Artist deleteArtist(String userID) throws IllegalArgumentException{
		Artist artist = artistRepository.findById(userID).orElse(null);
		
		if (artist == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		artistRepository.delete(artist);
		
		return artist;
	}
	
	@Transactional
	public Float getArtistRating(String userID) throws IllegalArgumentException{
		Artist artist = artistRepository.findById(userID).orElse(null);
		Float totalRatings = 0f;
		
		if (artist == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		for (Review r : reviewRepository.findByArtist(artist)) {
			totalRatings += (float)r.getRating();
		}
		
		totalRatings = totalRatings/((float)reviewRepository.findByArtist(artist).size());
		
		
		return totalRatings;
	}
	
	@Transactional
	public Artist updateArtist(String userID, String email, String password, String firstName, String lastName, String phoneNumber, String artistDescription) throws IllegalArgumentException{
		String e = "";

		if (nonValidString(userID)) {
			throw new IllegalArgumentException("Must enter a username.");
		}
		
		Artist artist = artistRepository.findById(userID).orElse(null);
		
		
		if (nonValidString(email)) {
			e += "Must enter an email. ";
		}
    
		if (artist == null) {
			e+="Username cannot be found.";
		}
		
		if (!email.equals(artist.getEmail())&&(endUserRepository.findByEmail(email) != null)) {
			e+="Email already exists.";
		}
		
		if (nonValidString(password)) {
			e += "Must enter a password. ";
		}
		
		if (nonValidString(firstName)) {
			e+="Must enter a first name. ";
		}
		
		if (nonValidString(lastName)) {
			e+= "Must enter a last name. ";
		}
		
		if (!nonValidString(e)) {
			throw new IllegalArgumentException(e.trim());
		}
		
		artist.setEmail(email);
		artist.setFirstName(firstName);
		artist.setLastName(lastName);
		artist.setPassword(password);
		artist.setPhoneNumber(phoneNumber);
		artist.setArtistDescription(artistDescription);
		artistRepository.save(artist);
		
		
		return artist;
	}

	
	// Admin Service Layer ___________________________________________________________________________________

	@Transactional
	public Administrator createAdministrator(String userID, String email, String password, String firstName, String lastName, String phoneNumber) throws IllegalArgumentException{
		Administrator administrator = new Administrator();
		String e = "";
				
		if (nonValidString(userID)) {
			e += "Must enter a username. ";
		}
		
		if (nonValidString(email)) {
			e += "Must enter an email. ";
		}
			
		if (endUserRepository.findById(userID).orElse(null) != null) {
			e+="Username already exists.";
		}
		
		if (endUserRepository.findByEmail(email) != null) {
			e+= "Email already exists. ";
		}
		
		if (nonValidString(password)) {
			e += "Must enter a password. ";
		}
		
		if (nonValidString(firstName)) {
			e+="Must enter a first name. ";
		}
		
		if (nonValidString(lastName)) {
			e+= "Must enter a last name. ";
		}
		
		if (!nonValidString(e)) {
			throw new IllegalArgumentException(e.trim());
		}
		
		administrator.setUserID(userID);
		administrator.setEmail(email);
		administrator.setPassword(password);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPhoneNumber(phoneNumber);
		administratorRepository.save(administrator);
		return administrator;
	}
	
	@Transactional
	public Administrator getAdministratorByID(String userID) throws IllegalArgumentException{
		Administrator administrator = administratorRepository.findById(userID).orElse(null);
		
		if (administrator == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		return administrator;
	}
	
	@Transactional
	public Administrator getAdministratorByEmail(String email) throws IllegalArgumentException{
		Administrator administrator = administratorRepository.findByEmail(email);
		
		if (administrator == null) {
			throw new IllegalArgumentException("Email cannot be found.");
		}
		
		return administrator;
	}
	
	@Transactional
	public List<Administrator> getAllAdministrators(){
		return toList(administratorRepository.findAll());
	}
	
	@Transactional
	public Administrator deleteAdministrator(String userID) throws IllegalArgumentException{
		Administrator administrator = administratorRepository.findById(userID).orElse(null);

		if (administrator == null) {
			throw new IllegalArgumentException("Username cannot be found.");
		}
		
		administratorRepository.deleteById(userID);
		return administrator;
	}
	
	@Transactional
	public Administrator updateAdministrator(String userID, String email, String password, String firstName, String lastName, String phoneNumber) throws IllegalArgumentException{
		String e = "";
		
		if (nonValidString(userID)) {
			throw new IllegalArgumentException("Must enter a username.");
		}
		
		Administrator administrator = administratorRepository.findById(userID).orElse(null);
		
		if (nonValidString(email)) {
			e += "Must enter an email. ";
		}
		
		if (administrator == null) {
			e += "Username cannot be found.";
		}
		
		if (!email.equals(administrator.getEmail())&&(endUserRepository.findByEmail(email) != null)) {
			e+="Email already exists.";
		}
		
		if (nonValidString(password)) {
			e += "Must enter a password. ";
		}
		
		if (nonValidString(firstName)) {
			e+="Must enter a first name. ";
		}
		
		if (nonValidString(lastName)) {
			e+= "Must enter a last name. ";
		}
		
		if (!nonValidString(e)) {
			throw new IllegalArgumentException(e.trim());
		}

		administrator.setEmail(email);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPassword(password);
		administrator.setPhoneNumber(phoneNumber);
		administratorRepository.save(administrator);
		
		
		return administrator;
	}
	
	// Artwork Service Layer ___________________________________________________________________________________
	
	@Transactional
    public Artwork createArtwork(String name, int price, String description, Date dateCreated, int numInStock, Artist artist) {
		String error = "";

        if (nonValidString(name)) {
            error = error + "Artwork name cannot be empty! ";
        }
        if (price < 0) {
        	error = error + "Artwork price cannot be less than 0!";
        }
        if (nonValidString(description)){
            error = error + "Artwork description cannot be empty!";
        }
        if (dateCreated == null) {
            error = error + "Artwork date of creation cannot be empty!";
        } else if (dateCreated.compareTo(new Date(System.currentTimeMillis())) > 0) {
            error = error + "Artwork date of creation cannot be in the future!";
        }
        if (numInStock < 0) {
        	error = error + "Number in stock cannot be less than 0!";
        }
        
        if (artist == null) {
            error = error + "Artist needs to be assigned for an artwork!";
        } else if (!artistRepository.existsById(artist.getUserID())) {
            error = error + "Artist does not exist!";
        }

        error = error.trim();
        
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
        Artwork artwork = new Artwork();
        artwork.setName(name);
        artwork.setDescription(description);
        artwork.setPrice(price);
        artwork.setDateOfCreation(dateCreated);
        artwork.setNumInStock(numInStock);
        artwork.setArtist(artist);
        
        artworkRepository.save(artwork);
        return artwork;
    }
	
	@Transactional
	public List<Artwork> getAllArtworks() {
		return toList(artworkRepository.findAll());
	}
	
	@Transactional
	public List<Artwork> getArtworksByArtist(Artist artist) {
		String error = "";

		List<Artwork> artworksByArtist = new ArrayList<>();
		if (artist == null)
			error = error + "An artist cannot be empty! ";
		for (Artwork a: artworkRepository.findByArtist(artist)) {
			artworksByArtist.add(a);
		}
		return artworksByArtist;
	}
	
	@Transactional
	public Artwork getArtworkById(Integer id) {
		String error = "";
		
		Artwork a = artworkRepository.findById(id).orElse(null);
		
		if (a == null) {
			error = error + "Artwork with the given Id does not exist!";
		}
		
		if (error.trim().length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		return a;
	}
	
	@Transactional 
	public Artwork updateArtwork(Artwork artwork, String name, Integer price, String description,
			Date date, Integer numInStock, Artist artist) {
		
		String error = "";
        if (nonValidString(name)) {
            error = error + "Artwork name cannot be empty!";
        }
        if (description == null || description.trim().length() == 0) {
            error = error + "Artwork description cannot be empty!";
        }
        if (price < 0) {
        	error = error + "Artwork price cannot be less than 0!";
        }
        if (date == null) {
            error = error + "Artwork date of creation cannot be empty!";
        } else if (date.compareTo(new Date(System.currentTimeMillis())) > 0) {
            error = error + "Artwork date of creation cannot be in the future!";
        }
        if (numInStock < 0) {
        	error = error + "Number in stock cannot be less than 0!";
        }
        if (artist == null) {
            error += "Artist needs to be assigned for an artwork!";
        } else if (!artistRepository.existsById(artist.getUserID())) {
            error = error + "Artist does not exist!";
        }

		if (artwork == null) {
			error += "Artwork does not exist!";
		}
        
        if (error.trim().length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
        artwork.setName(name);
        artwork.setDescription(description);
        artwork.setPrice(price);
        artwork.setDateOfCreation(date);
        artwork.setNumInStock(numInStock);
        artwork.setArtist(artist);
        
        Artwork saved = artworkRepository.save(artwork);
        return saved;
	}
	
	@Transactional
	public Artwork deleteArtwork(Integer id) {
		String error = "";
		Artwork a = artworkRepository.findById(id).orElse(null);
		
		if (a == null) {
			error +=  "Artwork with the given Id does not exist!";
		}
		
		if (error.trim().length() > 0) {
            throw new IllegalArgumentException(error);
        }
		
		Artwork deletedArtwork = a;
		artworkRepository.delete(a);
		
		return deletedArtwork;
	}
	
	// Review Service Layer ___________________________________________________________________________________

	public Review createReview(Integer rating, String comment, Boolean wouldRecommend, Customer customer, Artist artist) throws IllegalArgumentException {
		String e = "";
		if(rating == null) {
			e += "Review needs a rating.";
		}
		if (rating < 0 || rating > 5) {
			e += "Rating should be between 0 and 5";
		}
		if(customer == null) {
			e += "Review needs a customer.";
		}
		if(artist == null) {
			e += "A review needs an artist.";
		}
		
		e = e.trim();
		
		if (e.length() > 0) {
			throw new IllegalArgumentException(e);
		}
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
	public Review getReviewbyID(Integer reviewID) throws IllegalArgumentException {
		Review review = reviewRepository.findById(reviewID).orElse(null);
		if(review == null) {
			throw new IllegalArgumentException("Review does not exist.");
		}
		return review;
	}
	
	@Transactional
	public Review deleteReview(Integer reviewID) throws IllegalArgumentException {
		Review review = reviewRepository.findById(reviewID).orElse(null);
		if(review == null) {
			throw new IllegalArgumentException("Review does not exist.");
		}
		reviewRepository.deleteById(reviewID);
		return review;
	}

	@Transactional
	public Review updateReview(Integer reviewID, Integer rating, String comment, Boolean wouldRecommend, Customer customer, Artist artist) throws IllegalArgumentException {
		Review review = reviewRepository.findById(reviewID).orElse(null);
		if(review == null) {
			throw new IllegalArgumentException("Review does not exist.");
		}
		
		String e = "";
		if(rating == null) {
			e += "A review needs a rating.";
		}
		if(customer == null) {
			e += "A review needs a customer.";
		}
		if(artist == null) {
			e += "A review needs an artist.";
		}
		
		e = e.trim();
		
		if (e.length() > 0) {
			throw new IllegalArgumentException(e);
		}
		
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
	public ArtworkOrder createArtworkOrder(DeliveryMethod deliveryMethod, Customer customer, List<Artwork> artworks) throws IllegalArgumentException {
		String e = "";
		if(deliveryMethod == null) {
			e += "Artwork order needs a delivery method.";
		}
		if(customer == null) {
			e += "Artwork order needs a customer.";
		}
		if(artworks.size() == 0) {
			
		}
		
		e = e.trim();
		
		if (e.length() > 0) {
			throw new IllegalArgumentException(e);
		}
		
		int totalPrice = 0;
		
		for(Artwork art : artworks) {
			totalPrice += art.getPrice();
		}
		
		ArtworkOrder order = new ArtworkOrder();
		Date datePlaced  = new Date(System.currentTimeMillis());
		OrderStatus oStatus = OrderStatus.PROCESSING;
		order.setTotalPrice(totalPrice);
		order.setDatePlaced(datePlaced);
		order.setDateCompleted(null);
		order.setDeliveryMethod(deliveryMethod);
		order.setOrderStatus(oStatus);
		order.setCustomer(customer);
		artworkOrderRepository.save(order);
		return order;
	}

	@Transactional
	public ArtworkOrder getArtworkOrderByID(Integer orderID) throws IllegalArgumentException {
		ArtworkOrder order = artworkOrderRepository.findById(orderID).orElse(null);
		if(order == null) {
			throw new IllegalArgumentException("Artwork Order does not exist.");
		}
		return order;
	}
	
	@Transactional
	public ArtworkOrder deleteArtworkOrder(Integer orderID) throws IllegalArgumentException {
		ArtworkOrder artworkOrder = artworkOrderRepository.findById(orderID).orElse(null);
		if(artworkOrder == null) {
			throw new IllegalArgumentException("Artwork Order does not exist.");
		}
		artworkOrderRepository.deleteById(orderID);
		return artworkOrder;
	}

	@Transactional
	public ArtworkOrder updateArtworkOrder(Integer orderID,
										   DeliveryMethod deliveryMethod, OrderStatus orderStatus, 
										   Customer customer, List<Artwork> artworks) throws IllegalArgumentException {
		ArtworkOrder order = artworkOrderRepository.findById(orderID).orElse(null);
		if(order == null) {
			throw new IllegalArgumentException("Artwork Order does not exist.");
		}
		
		String e = "";
		if(deliveryMethod == null) {
			e += "Artwork order needs a delivery method. ";
		}
		if(orderStatus == null) {
			e += "Artwork order needs an order status. ";
		}
		if(customer == null) {
			e += "Artwork order needs a customer.";
		}
		
		e = e.trim();
		
		if (e.length() > 0) {
			throw new IllegalArgumentException(e);
		}
		
		int totalPrice = order.getTotalPrice();
		
		if(artworks.size() > 0) {
			for(Artwork artwork : artworks) {
				addArtworkToOrder(orderID, artwork);
				totalPrice += artwork.getPrice();
			}
		}
		
		if(orderStatus == OrderStatus.DELIVERED) {
			order.setDateCompleted(new Date(System.currentTimeMillis()));
		}
		
		order.setTotalPrice(totalPrice);
		order.setDeliveryMethod(deliveryMethod);
		order.setOrderStatus(orderStatus);
		order.setCustomer(customer);
		artworkOrderRepository.save(order);
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

	@Transactional
	public void addArtworkToOrder(Integer orderId, Artwork artwork) throws IllegalArgumentException {
		String error = "";
		ArtworkOrder order = getArtworkOrderByID(orderId);

		if (artwork == null) {
			throw new IllegalArgumentException(error + "Artwork cannot be empty! ");
		}

		Set<Artwork> artworks = order.getArtworks();
		if (artwork.getNumInStock() == 0) {
			throw new IllegalArgumentException("Artwork is out of stock! ");
		}
		artwork.setNumInStock(artwork.getNumInStock() - 1);
		artworks.add(artwork);

		order.setArtworks(artworks);
	}
	
	// Address Service Layer ___________________________________________________________________________________
	
	@Transactional
	public Address createAddress(String addressLine1, String addressLine2, String city, String province, String postalCode, String country) throws IllegalArgumentException {
		String e = "";
    
		if(nonValidString(addressLine1)) {
			e += "Address cannot be empty. ";
		}
		if(nonValidString(city)) {
			e += "City cannot be empty. ";
		}
		if(nonValidString(province)) {
			e += "Province cannot be empty. ";
		}
		if(nonValidString(postalCode)) {
			e += "Postal code cannot be empty. ";
		}
		if(nonValidString(country)) {
			e += "Country cannot be empty.";
		}
		
		e = e.trim();
		
		if (e.length() > 0) {
			throw new IllegalArgumentException(e);
		}
		
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
	public Address getAddressById(Integer addressID) throws IllegalArgumentException {
		Address address = addressRepository.findById(addressID).orElse(null);
		if(address == null) {
			throw new IllegalArgumentException("Address does not exist.");
		}
		return address;
	}
	
	@Transactional
	public Integer deleteAddress(Integer addressID) throws IllegalArgumentException {
		if(addressRepository.findById(addressID).orElse(null) == null) {
			throw new IllegalArgumentException("Address does not exist.");
		}
		addressRepository.deleteById(addressID);
		return addressID;
	}

	@Transactional
	public Address updateAddress(Integer addressID, String addressLine1, String addressLine2, String city, String province, String postalCode, String country) throws IllegalArgumentException {
		
		if (addressID == null) {
			throw new IllegalArgumentException("Address does not exist.");
		}
		
		Address address = addressRepository.findById(addressID).orElse(null);
		
		if(address == null) {
			throw new IllegalArgumentException("Address does not exist.");
		}
		
		String e = "";
		if(nonValidString(addressLine1)) {
			e += "Address cannot be empty. ";
		}
		if(nonValidString(city)) {
			e += "City cannot be empty. ";
		}
		if(nonValidString(province)) {
			e += "Province cannot be empty. ";
		}
		if(nonValidString(postalCode)) {
			e += "Postal code cannot be empty. ";
		}
		if(nonValidString(country)) {
			e += "Country cannot be empty.";
		}
		
		e = e.trim();
		
		if (e.length() > 0) {
			throw new IllegalArgumentException(e);
		}
		
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
	
	private boolean nonValidString(String string) {
		return (string==null)||(string.trim().isEmpty());
	}
	
}
