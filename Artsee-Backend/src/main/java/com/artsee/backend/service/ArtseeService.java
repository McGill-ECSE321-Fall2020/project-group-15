package com.artsee.backend.service;

import com.artsee.backend.dao.*;
import com.artsee.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	
	public EndUser getUser(String userID) {
		EndUser user = endUserRepository.findById(userID).orElse(null);
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
		customerRepository.deleteById(customerID);
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
			customerRepository.save(customer);
		}
		
		return customer;
	}

	// Artist Service Layer ___________________________________________________________________________________

	
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
	
	public Artist getArtistByID(String artistID) {
		Artist artist = artistRepository.findById(artistID).orElse(null);
		return artist;
	}
	
	public Artist getArtistByEmail(String email) {
		Artist artist = artistRepository.findByEmail(email);
		return artist;
	}
	
	public List<Artist> getAllArtists() {
		return toList(artistRepository.findAll());
	}
	
	public String deleteArtist(String artistID) {
		artistRepository.deleteById(artistID);
		return artistID;
	}
	
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
	
	public Administrator getAdministratorByID(String administratorID) {
		Administrator administrator = administratorRepository.findById(administratorID).orElse(null);
		return administrator;
	}
	
	public Administrator getAdministratorByEmail(String email) {
		Administrator administrator = administratorRepository.findByEmail(email);
		return administrator;
	}
	
	public List<Administrator> getAllAdministrators() {
		return toList(administratorRepository.findAll());
	}
	
	public String deleteAdministrator(String administratorID) {
		administratorRepository.deleteById(administratorID);
		return administratorID;
	}
	
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
	
	// Artwork Service Layer ___________________________________________________________________________________
	
	@Transactional
    public Artwork createArtwork(String name, int price, String description, Date dateCreated, int numInStock, Artist artist) {
		String error = "";
        if (name == null || name.trim().length() == 0) {
            error = error + "Artwork name cannot be empty! ";
        }
        if (price <= 0) {
        	error = error + "Artwork price cannot be less than 0! ";
        }
        if (description == null || description.trim().length() == 0) {
            error = error + "Artwork description cannot be empty! ";
        }
        if (dateCreated == null) {
            error = error + "Artwork date of creation cannot be empty! ";
        }
        if (numInStock < 0) {
        	error = error + "Number in stock cannot be less than 0! ";
        }
        
        if (artist == null) {
            error = error + "Artist needs to be assigned for an artwork! ";
        } else if (!artistRepository.existsById(artist.getUserID())) {
            error = error + "Artist does not exist! ";
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
			error = error + "Artwork with the given Id does not exist! ";
		}
		
		if (error.trim().length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		return a;
	}
	
	@Transactional 
	public Artwork updateArtwork(Artwork artwork, String name, Integer price,
			Date date, String description, Integer numInStock, Artist artist) {
		
		String error = "";
        if (name == null || name.trim().length() == 0) {
            error = error + "Artwork name cannot be empty!";
        }
        if (price <= 0) {
        	error = error + "Artwork price cannot be less than 0! ";
        }
        if (description == null || description.trim().length() == 0) {
            error = error + "Artwork description cannot be empty! ";
        }
        if (date == null) {
            error = error + "Artwork date of creation cannot be empty! ";
        }
        if (numInStock < 0) {
        	error = error + "Number in stock cannot be less than 0! ";
        }
        if (artist == null) {
            error += "Artist needs to be assigned to an artwork! ";
        } else if (!artistRepository.existsById(artist.getUserID())) {
            error += "Artist does not exist! ";
        }

		if (artwork == null) {
			error += "Artwork does not exist! ";
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
			error +=  "Artwork with the given Id does not exist! ";
		}
		
		if (error.trim().length() > 0) {
            throw new IllegalArgumentException(error);
        }
		
		Artwork deletedArtwork = a;
		artworkRepository.delete(a);
		
		return deletedArtwork;
	}
	
	// Review Service Layer ___________________________________________________________________________________

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

	@Transactional
	public void addArtworkToOrder(Integer orderId, Artwork artwork) {
		String error = "";
		ArtworkOrder order = getArtworkOrderByID(orderId);

		if (artwork == null) {
			throw new IllegalArgumentException(error + "Artwork cannot be empty! ");
		}

		Set<Artwork> artworks = order.getArtworks();
		artworks.add(artwork);

		order.setArtworks(artworks);
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
