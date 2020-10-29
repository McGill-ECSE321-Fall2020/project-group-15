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
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class ArtseeRestController {

	@Autowired
	private ArtseeService service;
	
	@PostMapping(value = {"/signIn"})
	public EndUserDto signIn(SignInDto signInDto) {
		EndUser user = service.signIn(signInDto.getUserID(), signInDto.getPassword());
		return convertToDto(user);
	}
	
	
	// Convert to Dto Methods _______________________
	
	public EndUserDto convertToDto(EndUser user) {
		return new EndUserDto(user.getUserID(), user.getEmail(), user.getPassword(), user.getFirstName(),user.getLastName(), user.getPhoneNumber());
	}
	
	public CustomerDto convertToDto(Customer customer) {
		return new CustomerDto(customer.getUserID(), customer.getEmail(), customer.getPassword(), customer.getFirstName(),customer.getLastName(), customer.getPhoneNumber(), convertToDto(customer.getAddress()));
	}
	
	public AdministratorDto convertToDto(Administrator admin) {
		return new AdministratorDto(admin.getUserID(), admin.getEmail(), admin.getPassword(), admin.getFirstName(),admin.getLastName(), admin.getPhoneNumber());
	}
	
	public ArtistDto convertToDto(Artist artist) {
		return new ArtistDto(artist.getUserID(), artist.getEmail(), artist.getPassword(), artist.getFirstName(),artist.getLastName(), artist.getPhoneNumber(),artist.getArtistDescription(), artist.getRating());
	}
	
	public AddressDto convertToDto(Address address) {
		return new AddressDto(address.getAddressID(),address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getProvince(), address.getPostalCode(), address.getCountry());
	}
	
	public ArtworkDto convertToDto(Artwork artwork, Artist artist) {
		ArtistDto aDto = convertToDto(artist);
		return new ArtworkDto(artwork.getArtworkID(), artwork.getName(), artwork.getDescription(), artwork.getPrice(), artwork.getDateOfCreation(), 
				artwork.getNumInStock(), aDto);
	}
	
	@GetMapping(value = { "/artworks", "/artworks/" })
    public List<ArtworkDto> getAllArtworks() {
		List<Artwork> artworks = service.getAllArtworks();
		List<ArtworkDto> aDtos = new ArrayList<>();
		for(Artwork a: artworks) {
			aDtos.add(convertToDto(a, a.getArtist()));
		}
        return aDtos;
    }

    @GetMapping(value = { "/artworks/{id}", "/artworks/{id}/" })
    public ArtworkDto getArtworkById(@PathVariable("id") int id) throws IllegalArgumentException {
    	Artwork a = service.getArtworkById(id);
        return convertToDto(a, a.getArtist());
    }

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

//	@GetMapping(value = { "/reviews", "/reviews/" })


	

}