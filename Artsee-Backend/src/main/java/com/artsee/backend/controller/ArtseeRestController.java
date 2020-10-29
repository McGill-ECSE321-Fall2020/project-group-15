package com.artsee.backend.controller;

import com.artsee.backend.dao.EndUserRepository;
import com.artsee.backend.model.Address;
import com.artsee.backend.model.Administrator;
import com.artsee.backend.model.Artist;
import com.artsee.backend.model.Customer;
import com.artsee.backend.model.EndUser;
import com.artsee.backend.service.ArtseeService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artsee.backend.model.*;
import com.artsee.backend.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class ArtseeRestController {

	@Autowired
	private ArtseeService service;
	
	// REST api for user authentication __________________________________________________________
	
	@PostMapping(value = {"/signIn"})
	public EndUserDto signIn(@RequestBody SignInDto signInDto) {
		EndUser user = service.signIn(signInDto.getUserID(), signInDto.getPassword());
		return convertToDto(user);
	}
	
	// REST api for EndUser  __________________________________________________________

	@GetMapping(value = {"/users", "/users/"})
	public List<EndUserDto> getAllUsers(){
		return service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/users/{userID}","/users/{userID}/"})
	public EndUserDto getUser(@PathVariable("userID") String userID){
		return convertToDto(service.getUser(userID));
	}
	
	@DeleteMapping(value = {"/users/{userID}","/users/{userID}/"})
	public EndUser deleteUser(@PathVariable("userID") String userID){
		return service.deleteUser(userID);
	}
	
	@PutMapping(value = {"/users/"}, consumes = "application/json", produces = "application/json")
	public EndUserDto updateUser(@RequestBody EndUserDto userDto) {
		EndUser user = service.updateUser(userDto.getUserID(), userDto.getEmail(), userDto.getPassword(), userDto.getFirstName(), userDto.getLastName(), userDto.getPhoneNumber());
		return convertToDto(user);
	}
	
	// REST api for Customer __________________________________________________________

	@PostMapping(value = {"/customers"}, consumes = "application/json", produces = "application/json")
	public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
		
		AddressDto addressDto = customerDto.getAddress();
		Address address = service.createAddress(addressDto.getAddressLine1(), addressDto.getAddressLine2(), addressDto.getCity(), addressDto.getProvince(), addressDto.getPostalCode(), addressDto.getCountry());
		Customer customer = service.createCustomer(customerDto.getUserID(), customerDto.getEmail(), customerDto.getPassword(), customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPhoneNumber(), address);
		
		return convertToDto(customer);
	}
	
	@GetMapping(value = {"/customers/{userID}","/customers/{userID}/"})
	public CustomerDto getCustomerByID(@PathVariable("userID") String userID){
		return convertToDto(service.getCustomerByID(userID));
	}
	
	@GetMapping(value = {"/customers/{email}","/customers/{email}/"})
	public CustomerDto getCustomerByEmail(@PathVariable("email") String email){
		return convertToDto(service.getCustomerByEmail(email));
	}
	
	@GetMapping(value = {"/customers", "/customers/"})
	public List<CustomerDto> getAllCustomers(){
		return service.getAllCustomers().stream().map(c -> convertToDto(c)).collect(Collectors.toList());
	}
	
	@DeleteMapping(value = {"/customers/{userID}","/customers/{userID}/"})
	public Customer deleteCustomer(@PathVariable("userID") String userID){
		return service.deleteCustomer(userID);
	}
	
	@PutMapping(value = {"/customers/"}, consumes = "application/json", produces = "application/json")
	public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
		
		AddressDto addressDto = customerDto.getAddress();
		Address address = service.createAddress(addressDto.getAddressLine1(), addressDto.getAddressLine2(), addressDto.getCity(), addressDto.getProvince(), addressDto.getPostalCode(), addressDto.getCountry());
		Customer customer = service.updateCustomer(customerDto.getUserID(), customerDto.getEmail(), customerDto.getPassword(), customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPhoneNumber(), address);
		
		return convertToDto(customer);
	}
	
	// REST api for Artist __________________________________________________________

	@PostMapping(value = {"/artists"}, consumes = "application/json", produces = "application/json")
	public ArtistDto createArtist(@RequestBody ArtistDto artistDto) {
		Artist artist = service.createArtist(artistDto.getUserID(), artistDto.getEmail(), artistDto.getPassword(), artistDto.getFirstName(), artistDto.getLastName(), artistDto.getPhoneNumber(), artistDto.getArtistDescription());
		return convertToDto(artist);
	}
	
	@GetMapping(value = {"/artists/{userID}","/artists/{userID}/"})
	public ArtistDto getArtistByID(@PathVariable("userID") String userID){
		return convertToDto(service.getArtistByID(userID));
	}
	
	@GetMapping(value = {"/artists/{email}","/artists/{email}/"})
	public ArtistDto getArtistByEmail(@PathVariable("email") String email){
		return convertToDto(service.getArtistByEmail(email));
	}
	
	@GetMapping(value = {"/artists", "/artists/"})
	public List<ArtistDto> getAllArtists(){
		return service.getAllArtists().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
	}

	@DeleteMapping(value = {"/artists/{userID}", "/artists/{userID}/"})
	public Artist deleteArtist(@PathVariable("userID") String userID){
		return service.deleteArtist(userID);
	}
	
	@PutMapping(value = {"/artists/"}, consumes = "application/json", produces = "application/json")
	public ArtistDto updateArtist(@RequestBody ArtistDto artistDto) {
		Artist artist = service.updateArtist(artistDto.getUserID(), artistDto.getEmail(), artistDto.getPassword(), artistDto.getFirstName(), artistDto.getLastName(), artistDto.getPhoneNumber(), artistDto.getArtistDescription());
		return convertToDto(artist);
	}
	
	@GetMapping(value = {"/artists/{userID}/rating","/artists/{userID}/rating/"})
	public Float getArtistRating(@PathVariable("userID") String userID) {
		return service.getArtistRating(userID);
	}
	
	// REST api for Administrator  __________________________________________________________

	@GetMapping(value = {"/administrators", "/administrators/"})
	public List<AdministratorDto> getAllAdministrators(){
		return service.getAllAdministrators().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
	}
	
	
	
	
	// Convert to Dto Methods _______________________
	
	private EndUserDto convertToDto(EndUser user) {
		return new EndUserDto(user.getUserID(), user.getEmail(), user.getPassword(), user.getFirstName(),user.getLastName(), user.getPhoneNumber());
	}
	
	private CustomerDto convertToDto(Customer customer) {
		return new CustomerDto(customer.getUserID(), customer.getEmail(), customer.getPassword(), customer.getFirstName(),customer.getLastName(), customer.getPhoneNumber(), convertToDto(customer.getAddress()));
	}
	
	private AdministratorDto convertToDto(Administrator admin) {
		return new AdministratorDto(admin.getUserID(), admin.getEmail(), admin.getPassword(), admin.getFirstName(),admin.getLastName(), admin.getPhoneNumber());
	}
	
	private ArtistDto convertToDto(Artist artist) {
		return new ArtistDto(artist.getUserID(), artist.getEmail(), artist.getPassword(), artist.getFirstName(),artist.getLastName(), artist.getPhoneNumber(),artist.getArtistDescription(), artist.getRating());
	}
	
	private AddressDto convertToDto(Address address) {
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
	
	
//	@GetMapping(value = { "/artworks", "/artworks/" })
//    public List<ArtworkDto> getAllArtworks() {
//        return service.getAllArtworks().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
//    }
//
//    @GetMapping(value = { "/artworks/{id}", "/artworks/{id}/" })
//    public ArtworkDto getArtworkById(@PathVariable("id") int id) throws IllegalArgumentException {
//        return convertToDto(service.getArtworkById(id));
//    }

//    @GetMapping(value = { "/artworks/{artist_id}", "/artworks/{artist_id}/" })
//    public ArtworkDto getArtworkById(@PathVariable("id") String id) throws IllegalArgumentException {
//    	Artist a = getArtistById(id);
//        return convertToDto(service.getArtworksByArtist(a));
//    }
	
//	@PostMapping(value = { "/artworks/{id}", "/artworks/{id}/" })
//    public ArtworkDto createArtwork(@PathVariable("id") String id, @RequestParam(name ="name") String name,
//                                    @RequestParam(name="description") String description, @RequestParam(name="price") Integer price,
//                                    @RequestParam(name="numInStock") Integer numInStock, @RequestParam(name="date") Date date)
//            throws IllegalArgumentException {
//
//	    Artist a = service.getArtistById(id);
//
//    }
//
//    private ArtworkDto convertToDto(Artwork artwork, Artist artist) {
//        ArtistDto aDto = convertToDto()
//    }

//    private ArtistDto convertToDto(Artist a) {
//	    if (a == null) {
//	        throw new IllegalArgumentException("There is no such artist! ");
//        }
//	    ArtistDto artistDto = new ArtistDto()
//    }

	// REST api for Review  __________________________________________________________
	
	@GetMapping(value = { "/reviews", "/reviews/" })
	public List<ReviewDto> getAllReviews(){
		return service.getAllReviews().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/reviews" }, consumes = "application/json", produces = "application/json")
	public ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
		Customer customer = service.getCustomerByID(reviewDto.getCustomer().getUserID());
		Artist artist = service.getArtistByID(reviewDto.getArtist().getUserID());
		Review review = service.createReview(reviewDto.getRating(), reviewDto.getComment(), reviewDto.getRecomendation(), customer, artist);
		return convertToDto(review);
	}
	
	@PutMapping(value = { "/reviews" }, consumes = "application/json", produces = "application/json")
	public ReviewDto updateReview(@RequestBody ReviewDto reviewDto) {
		Customer customer = service.getCustomerByID(reviewDto.getCustomer().getUserID());
		Artist artist = service.getArtistByID(reviewDto.getArtist().getUserID());
		Review review = service.updateReview(reviewDto.getReviewID(), reviewDto.getRating(), reviewDto.getComment(), reviewDto.getRecomendation(), customer, artist);
		return convertToDto(review);
	}
	
	@DeleteMapping(value = { "/reviews/{id}" })
	public ReviewDto deleteReview(@PathVariable("id") Integer id) {
		Review review = service.deleteReview(id);
		return convertToDto(review);
	}
	
	// REST api for Artwork  __________________________________________________________
	
	@GetMapping(value = { "/artworks", "/artworks/" })
	public List<ArtworkDto> getAllArtworks(){
		return service.getAllArtworks().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/artwork" }, consumes = "application/json", produces = "application/json")
	public ArtworkDto createArtwork(@RequestBody ArtworkDto artworkDto) {
		Artist artist = service.getArtistByID(artworkDto.getArtist().getUserID());
		Artwork artwork = service.createArtwork(artworkDto.getName(), artworkDto.getPrice(), artworkDto.getDescription(), artworkDto.getDateOfCreation(), artworkDto.getNumInStock(), artist);
		return convertToDto(artwork);
	}
	
	@PutMapping(value = { "/artwork" }, consumes = "application/json", produces = "application/json")
	public ArtworkDto updateArtwork(@RequestBody ArtworkDto artworkDto) {
		Artist artist = service.getArtistByID(artworkDto.getArtist().getUserID());
		Artwork artwork = service.createArtwork(artworkDto.getName(), artworkDto.getPrice(), artworkDto.getDescription(), artworkDto.getDateOfCreation(), artworkDto.getNumInStock(), artist);
		return convertToDto(artwork);
	}
	
	@DeleteMapping(value = { "/artwork/{id}" })
	public ArtworkDto deleteArtwork(@PathVariable("id") Integer id) {
		Artwork artwork = service.deleteArtwork(id);
		return convertToDto(artwork);
	}
	
	// REST api for Artwork Order  __________________________________________________________
	
	@GetMapping(value = { "/artworkOrders", "/artworkOrders/" })
	public List<ArtworkOrderDto> getAllArtworkOrders(){
		return service.getAllArtworkOrders().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/artworkOrder" }, consumes = "application/json", produces = "application/json")
	public ArtworkOrderDto createArtworkOrder(@RequestBody ArtworkOrderDto artworkOrderDto) {
		Customer customer = service.getCustomerByID(artworkOrderDto.getCustomer().getUserID());
		List<Artwork> artworks = new ArrayList<>();
		artworks = convertFromDto(artworkOrderDto.getArtworks());
		DeliveryMethod deliveryMethod = convertFromDto(artworkOrderDto.getDeliveryMethod());
		OrderStatus orderStatus = convertFromDto(artworkOrderDto.getOrderStatus());
		ArtworkOrder artworkOrder = service.createArtworkOrder(artworkOrderDto.getDatePlaced(), artworkOrderDto.getDateCompleted(), deliveryMethod, orderStatus, customer, artworks);
		return convertToDto(artworkOrder);
	}
	
//	@PutMapping(value = { "/artwork" }, consumes = "application/json", produces = "application/json")
//	public ArtworkOrderDto updateArtworkOrder(@RequestBody ArtworkOrderDto artworkOrderDto) {
//		Customer customer = service.getCustomerByID(artworkOrderDto.getCustomer().getCustomerID());
//		List<Artwork> artworks = new ArrayList<>();
//		artworks = convertFromDto(artworkOrderDto.getArtworks());
//		DeliveryMethod deliveryMethod = convertFromDto(artworkOrderDto.getDeliveryMethod());
//		OrderStatus orderStatus = convertFromDto(artworkOrderDto.getOrderStatus());
//		ArtworkOrder artworkOrder = service.updateArtworkOrder(artworkOrderDto.getOrderID(), artworkOrderDto.getDatePlaced(), artworkOrderDto.getDateCompleted(), deliveryMethod, orderStatus, customer, artworks);
//		return convertToDto(artworkOrder);
//	}
	
	@DeleteMapping(value = { "/artworkOrder/{id}" })
	public ArtworkOrderDto deleteArtworkOrder(@PathVariable("id") Integer id) {
		ArtworkOrder artworkOrder = service.deleteArtworkOrder(id);
		return convertToDto(artworkOrder);
	}
	
}