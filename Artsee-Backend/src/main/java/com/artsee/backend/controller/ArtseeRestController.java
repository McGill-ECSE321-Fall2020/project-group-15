package com.artsee.backend.controller;

import com.artsee.backend.dao.EndUserRepository;
import com.artsee.backend.dto.EndUserDto;
import com.artsee.backend.dto.SignInDto;
import com.artsee.backend.model.EndUser;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artsee.backend.model.*;
import com.artsee.backend.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class ArtseeRestController {

	@Autowired
	private ArtseeService service;
	@Autowired
	private EndUserRepository endUserRepository;
	
	@PostMapping(value = {"/signIn"})
	public EndUserDto signIn(SignInDto signInDto) {
		EndUser user = service.signIn(signInDto.getUserID(), signInDto.getPassword());
		return covertToDto(user);
	}
	
	
	public EndUserDto covertToDto(EndUser user) {
		return new EndUserDto(user.getUserID(), user.getEmail(), user.getPassword(), user.getFirstName(),user.getLastName(), user.getPhoneNumber());
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

//	@GetMapping(value = { "/reviews", "/reviews/" })


	private ReviewDto convertToDto(Review r) {
		if(r==null) {
			throw new IllegalArgumentException("There is no such Review.");
		}
		ReviewDto reviewDto = new ReviewDto(r.getReviewID(), r.getRating(), r.getComment(), r.getWouldRecommend(), r.getCustomer(), r.getArtist());
		return reviewDto;
	}

}