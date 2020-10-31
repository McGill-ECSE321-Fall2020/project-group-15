package com.artsee.backend.controller;

import com.artsee.backend.dao.EndUserRepository;

import com.artsee.backend.dto.AddressDto;
import com.artsee.backend.dto.AdministratorDto;
import com.artsee.backend.dto.ArtistDto;
import com.artsee.backend.dto.CustomerDto;
import com.artsee.backend.dto.EndUserDto;
import com.artsee.backend.dto.SignInDto;
import com.artsee.backend.dto.ArtworkDto;

import com.artsee.backend.model.Address;
import com.artsee.backend.model.Administrator;
import com.artsee.backend.model.Artist;
import com.artsee.backend.model.Customer;
import com.artsee.backend.model.EndUser;
import com.artsee.backend.model.Artwork;
import com.artsee.backend.service.ArtseeService;

import java.util.List;
import java.util.stream.Collectors;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.artsee.backend.model.*;
import com.artsee.backend.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class ArtseeRestController {

	@Autowired
	private ArtseeService service;
	
	// REST api for user authentication __________________________________________________________
	
	@PostMapping(value = {"/signIn", "/signIn/"})
	public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {
		try {
			return new ResponseEntity<>(service.signIn(signInDto.getUserID(), signInDto.getPassword()), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	// REST api for EndUser  __________________________________________________________

	@GetMapping(value = {"/users", "/users/"})
	public ResponseEntity<?> getAllUsers(){
		return new ResponseEntity<>(service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/users/{userID}","/users/{userID}/"})
	public ResponseEntity<?> getUser(@PathVariable("userID") String userID){
		try {
			return new ResponseEntity<>(convertToDto(service.getUser(userID)), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@DeleteMapping(value = {"/users/{userID}","/users/{userID}/"})
	public ResponseEntity<?> deleteUser(@PathVariable("userID") String userID){
		try {
			return new ResponseEntity<>(convertToDto(service.deleteUser(userID)), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping(value = {"/users", "/users/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody EndUserDto userDto) {
		try {
			EndUser user = service.updateUser(userDto.getUserID(), userDto.getEmail(), 
											  userDto.getPassword(), userDto.getFirstName(), 
											  userDto.getLastName(), userDto.getPhoneNumber());
			return new ResponseEntity<>(convertToDto(user), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	
	// REST api for Customer __________________________________________________________

	@PostMapping(value = {"/customers", "/customers/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto){
		try {
			AddressDto addressDto = customerDto.getAddress();
			Address address = service.createAddress(addressDto.getAddressLine1(), addressDto.getAddressLine2(), addressDto.getCity(), addressDto.getProvince(), addressDto.getPostalCode(), addressDto.getCountry());
			Customer customer = service.createCustomer(customerDto.getUserID(), customerDto.getEmail(), 
													   customerDto.getPassword(), customerDto.getFirstName(), 
													   customerDto.getLastName(), customerDto.getPhoneNumber(), address);
			return new ResponseEntity<>(convertToDto(customer), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@GetMapping(value = {"/customers/{userID}","/customers/{userID}/"})
	public ResponseEntity<?> getCustomerByID(@PathVariable("userID") String userID){
		try {
			return new ResponseEntity<>(convertToDto(service.getCustomerByID(userID)), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = {"/customers", "/customers/"})
	public ResponseEntity<?> getAllCustomers(){
		return new ResponseEntity<>(service.getAllCustomers().stream().map(c -> convertToDto(c)).collect(Collectors.toList()), HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = {"/customers/{userID}","/customers/{userID}/"})
	public ResponseEntity<?> deleteCustomer(@PathVariable("userID") String userID){
		try {
			return new ResponseEntity<>(convertToDto(service.deleteCustomer(userID)), HttpStatus.OK);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = {"/customers", "/customers/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto) {
		
		try {
			AddressDto addressDto = customerDto.getAddress();
			Address address = service.createAddress(addressDto.getAddressLine1(), addressDto.getAddressLine2(), 
													addressDto.getCity(), addressDto.getProvince(), 
													addressDto.getPostalCode(), addressDto.getCountry());
			Customer customer = service.updateCustomer(customerDto.getUserID(), customerDto.getEmail(), 
													   customerDto.getPassword(), customerDto.getFirstName(), 
													   customerDto.getLastName(), customerDto.getPhoneNumber(), address);
			return new ResponseEntity<>(convertToDto(customer), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	// REST api for Artist __________________________________________________________

	@PostMapping(value = {"/artists", "/artists/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createArtist(@RequestBody ArtistDto artistDto) {
		try {
			Artist artist = service.createArtist(artistDto.getUserID(), artistDto.getEmail(), artistDto.getPassword(), artistDto.getFirstName(), artistDto.getLastName(), artistDto.getPhoneNumber(), artistDto.getArtistDescription());
			return new ResponseEntity<>(convertToDto(artist), HttpStatus.OK);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = {"/artists/{userID}","/artists/{userID}/"})
	public ResponseEntity<?> getArtistByID(@PathVariable("userID") String userID) {
		try {
			return new ResponseEntity<>(convertToDto(service.getArtistByID(userID)), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = {"/artists", "/artists/"})
	public ResponseEntity<?> getAllArtists(){
		return new ResponseEntity<>(service.getAllArtists().stream().map(a -> convertToDto(a)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@DeleteMapping(value = {"/artists/{userID}", "/artists/{userID}/"})
	public ResponseEntity<?> deleteArtist(@PathVariable("userID") String userID) {
		try {
			return new ResponseEntity<>(convertToDto(service.deleteArtist(userID)), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = {"/artists", "/artists/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateArtist(@RequestBody ArtistDto artistDto) {
		try {
			Artist artist = service.updateArtist(artistDto.getUserID(), artistDto.getEmail(), 
					artistDto.getPassword(), artistDto.getFirstName(), artistDto.getLastName(), 
					artistDto.getPhoneNumber(), artistDto.getArtistDescription());
			return new ResponseEntity<>(convertToDto(artist), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = {"/artists/{userID}/rating","/artists/{userID}/rating/"})
	public ResponseEntity<?> getArtistRating(@PathVariable("userID") String userID) {
		try {
			return new ResponseEntity<>(service.getArtistRating(userID), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	// REST api for Administrator  __________________________________________________________
	
	@PostMapping(value = {"/administrators", "/administrators/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createAdministrator(@RequestBody AdministratorDto adminDto) {
		try {
			Administrator administrator = service.createAdministrator(adminDto.getUserID(), adminDto.getEmail(), 
																      adminDto.getPassword(), adminDto.getFirstName(), 
																      adminDto.getLastName(), adminDto.getPhoneNumber());
			return new ResponseEntity<>(convertToDto(administrator), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = {"/administrators/{userID}","/administrators/{userID}/"})
	public ResponseEntity<?> getAdministratorByID(@PathVariable("userID") String userID) {
		try {
			return new ResponseEntity<>(convertToDto(service.getAdministratorByID(userID)), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = {"/administrators", "/administrators/"})
	public ResponseEntity<?> getAllAdministrators(){
		return new ResponseEntity<>(service.getAllAdministrators().stream().map(a -> convertToDto(a)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@DeleteMapping(value = {"/administrators/{userID}", "/administrators/{userID}/"})
	public ResponseEntity<?> deleteAdministrator(@PathVariable("userID") String userID) {
		try {
			return new ResponseEntity<>(convertToDto(service.deleteAdministrator(userID)), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = {"/administrators", "/administrators/"}, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateAdministrator(@RequestBody AdministratorDto adminDto) {
		try {
			Administrator admin = service.updateAdministrator(adminDto.getUserID(), adminDto.getEmail(), adminDto.getPassword(), adminDto.getFirstName(), adminDto.getLastName(), adminDto.getPhoneNumber());
			return new ResponseEntity<>(convertToDto(admin), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// REST api for Review  __________________________________________________________
	
	@GetMapping(value = { "/reviews", "/reviews/" })
	public ResponseEntity<?> getAllReviews(){
		return new ResponseEntity<>(service.getAllReviews().stream().map(u -> convertToDto(u)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping(value = { "/reviews", "/reviews/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto) {
		try {
			Customer customer = service.getCustomerByID(reviewDto.getCustomer().getUserID());
			Artist artist = service.getArtistByID(reviewDto.getArtist().getUserID());
			Review review = service.createReview(reviewDto.getRating(), reviewDto.getComment(), reviewDto.getRecomendation(), customer, artist);
			return new ResponseEntity<>(convertToDto(review), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = { "/reviews", "/reviews/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto) {
		try {
			Customer customer = service.getCustomerByID(reviewDto.getCustomer().getUserID());
			Artist artist = service.getArtistByID(reviewDto.getArtist().getUserID());
			Review review = service.updateReview(reviewDto.getReviewID(), reviewDto.getRating(), reviewDto.getComment(), reviewDto.getRecomendation(), customer, artist);
			return new ResponseEntity<>(convertToDto(review), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@DeleteMapping(value = { "/reviews/{id}", "/reviews/{id}/" })
	public ResponseEntity<?> deleteReview(@PathVariable("id") Integer id) {
		try {
			Review review = service.deleteReview(id);
			return new ResponseEntity<>(convertToDto(review), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	// REST api for Artwork  __________________________________________________________
	
	@GetMapping(value = { "/artworks", "/artworks/" })
	public ResponseEntity<?> getAllArtworks(){
		return new ResponseEntity<>(service.getAllArtworks().stream().map(u -> convertToDto(u)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping(value = { "/artworks", "/artworks/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createArtwork(@RequestBody ArtworkDto artworkDto) {
		try {
			Artist artist = service.getArtistByID(artworkDto.getArtist().getUserID());
			Artwork artwork = service.createArtwork(artworkDto.getName(), artworkDto.getPrice(), artworkDto.getDescription(), artworkDto.getDateOfCreation(), artworkDto.getNumInStock(), artist);
			return new ResponseEntity<>(convertToDto(artwork), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = { "/artworks", "/artworks/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateArtwork(@RequestBody ArtworkDto artworkDto) {
		try {
			Artist artist = service.getArtistByID(artworkDto.getArtist().getUserID());
			Artwork curr = service.getArtworkById(artworkDto.getID());
			Artwork artwork = service.updateArtwork(curr, artworkDto.getName(), artworkDto.getPrice(), artworkDto.getDescription(), artworkDto.getDateOfCreation(), artworkDto.getNumInStock(), artist);
			return new ResponseEntity<>(convertToDto(artwork), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = { "/artworks/{id}", "/artworks/{id}/" })
	public ResponseEntity<?> deleteArtwork(@PathVariable("id") Integer id) {
		try {
			Artwork artwork = service.deleteArtwork(id);
			return new ResponseEntity<>(convertToDto(artwork), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	// REST api for Artwork Order  __________________________________________________________
	
	@GetMapping(value = { "/artworkOrders", "/artworkOrders/" })
	public ResponseEntity<?> getAllArtworkOrders() {
		return new ResponseEntity<>(service.getAllArtworkOrders().stream().map(u -> convertToDto(u)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping(value = { "/artworkOrders", "/artworkOrders/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createArtworkOrder(@RequestBody ArtworkOrderDto artworkOrderDto) {
		try {
			Customer customer = service.getCustomerByID(artworkOrderDto.getCustomer().getUserID());
			List<Artwork> artworks = new ArrayList<>();
			artworks = convertFromDto(artworkOrderDto.getArtworks());
			DeliveryMethod deliveryMethod = convertFromDto(artworkOrderDto.getDeliveryMethod());
			ArtworkOrder artworkOrder = service.createArtworkOrder(deliveryMethod, customer, artworks);
			return new ResponseEntity<>(convertToDto(artworkOrder), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping(value = { "/artworkOrders", "/artworkOrders/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateArtworkOrder(@RequestBody ArtworkOrderDto artworkOrderDto) {
		try {
			Customer customer = service.getCustomerByID(artworkOrderDto.getCustomer().getUserID());
			List<Artwork> artworks = new ArrayList<>();
			artworks = convertFromDto(artworkOrderDto.getArtworks());
			DeliveryMethod deliveryMethod = convertFromDto(artworkOrderDto.getDeliveryMethod());
			OrderStatus orderStatus = convertFromDto(artworkOrderDto.getOrderStatus());
			ArtworkOrder artworkOrder = service.updateArtworkOrder(artworkOrderDto.getOrderID(), deliveryMethod, orderStatus, customer, artworks);
			return new ResponseEntity<>(convertToDto(artworkOrder), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = { "/artworkOrders/{id}", "/artworksOrders/{id}/" })
	public ResponseEntity<?> deleteArtworkOrder(@PathVariable("id") Integer id) {
		try {
			ArtworkOrder artworkOrder = service.deleteArtworkOrder(id);
			return new ResponseEntity<>(convertToDto(artworkOrder), HttpStatus.OK);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	// Convert to Dto Methods ______________________________________________________________________________________________
	
	private EndUserDto convertToDto(EndUser user) {
			if(user==null) {
				throw new IllegalArgumentException("There is no such User.");
			}
			return new EndUserDto(user.getUserID(), user.getEmail(), user.getPassword(), user.getFirstName(),user.getLastName(), user.getPhoneNumber());
		}
		
	private CustomerDto convertToDto(Customer customer) {
			if(customer==null) {
				throw new IllegalArgumentException("There is no such Customer.");
			}
			return new CustomerDto(customer.getUserID(), customer.getEmail(), customer.getPassword(), customer.getFirstName(),customer.getLastName(), customer.getPhoneNumber(), convertToDto(customer.getAddress()));
		}
		
	private AdministratorDto convertToDto(Administrator admin) {
			if(admin==null) {
				throw new IllegalArgumentException("There is no such Administrator.");
			}
			return new AdministratorDto(admin.getUserID(), admin.getEmail(), admin.getPassword(), admin.getFirstName(),admin.getLastName(), admin.getPhoneNumber());
		}
		
	private ArtistDto convertToDto(Artist artist) {
			if(artist==null) {
				throw new IllegalArgumentException("There is no such Artist.");
			}
			return new ArtistDto(artist.getUserID(), artist.getEmail(), artist.getPassword(), artist.getFirstName(),artist.getLastName(), artist.getPhoneNumber(),artist.getArtistDescription(), artist.getRating());
		}
		
	private AddressDto convertToDto(Address address) {
			if(address==null) {
				throw new IllegalArgumentException("There is no such Address.");
			}
			return new AddressDto(address.getAddressID(),address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getProvince(), address.getPostalCode(), address.getCountry());
		}
		
	private ReviewDto convertToDto(Review review) {
			if(review==null) {
				throw new IllegalArgumentException("There is no such Review.");
			}
			ReviewDto reviewDto = new ReviewDto(review.getReviewID(), review.getRating(), review.getComment(), review.getWouldRecommend(), convertToDto(review.getCustomer()), convertToDto(review.getArtist()));
			return reviewDto;
		}
		
	private ArtworkOrderDto convertToDto(ArtworkOrder artworkOrder) {
			if(artworkOrder==null) {
				throw new IllegalArgumentException("There is no such Artwork Order.");
			}
			ArtworkOrderDto artworkOrderDto = new ArtworkOrderDto(artworkOrder.getOrderID(), artworkOrder.getTotalPrice(), artworkOrder.getDatePlaced(), artworkOrder.getDateCompleted(), convertToDto(artworkOrder.getDeliveryMethod()), convertToDto(artworkOrder.getOrderStatus()), convertToDto(artworkOrder.getCustomer()), convertToDto(artworkOrder.getArtworks()));
			return artworkOrderDto;
		}
		
	private ArtworkDto convertToDto(Artwork artwork) {
			if(artwork==null) {
				throw new IllegalArgumentException("There is no such Artwork.");
			}
			ArtworkDto artworkDto = new ArtworkDto(artwork.getArtworkID(), artwork.getName(), artwork.getDescription(), artwork.getPrice(), artwork.getDateOfCreation(), artwork.getNumInStock(), convertToDto(artwork.getArtist()));
			return artworkDto;
		}
		
	private DeliveryMethodDto convertToDto(DeliveryMethod deliveryMethod) {
			DeliveryMethodDto deliveryMethodDto;
			if(deliveryMethod.equals(DeliveryMethod.SHIP)) {
				deliveryMethodDto = DeliveryMethodDto.SHIP;
			} else {
				deliveryMethodDto = DeliveryMethodDto.PICKUP;
			}
			return deliveryMethodDto;
		}
		
	private OrderStatusDto convertToDto(OrderStatus orderStatus) {
			OrderStatusDto orderStatusDto;
			if(orderStatus.equals(OrderStatus.PROCESSING)) {
				orderStatusDto = OrderStatusDto.PROCESSING;
			} else {
				orderStatusDto = OrderStatusDto.DELIVERED;
			}
			return orderStatusDto;
		}
		
	private List<ArtworkDto> convertToDto(Set<Artwork> artworks){
			List<ArtworkDto> artworkListDto = new ArrayList<ArtworkDto>();
			for(Artwork artwork : artworks) {
				artworkListDto.add(convertToDto(artwork));
			}
			return artworkListDto;
		}
		
	private DeliveryMethod convertFromDto(DeliveryMethodDto deliveryMethodDto) {
			DeliveryMethod deliveryMethod;
			if(deliveryMethodDto.equals(DeliveryMethodDto.SHIP)) {
				deliveryMethod = DeliveryMethod.SHIP;
			} else {
				deliveryMethod = DeliveryMethod.PICKUP;
			}
			return deliveryMethod;
		}
		
	private OrderStatus convertFromDto(OrderStatusDto orderStatusDto) {
			OrderStatus orderStatus;
			if(orderStatusDto.equals(OrderStatusDto.PROCESSING)) {
				orderStatus = OrderStatus.PROCESSING;
			} else {
				orderStatus = OrderStatus.DELIVERED;
			}
			return orderStatus;
		}
		
	private List<Artwork> convertFromDto(List<ArtworkDto> artworks){
			List<Artwork> artworkList = new ArrayList<Artwork>();
			for(ArtworkDto artworkDto : artworks) {
				Artwork artwork = service.getArtworkById(artworkDto.getID());
				artworkList.add(artwork);
			}
			return artworkList;
		}
	
}