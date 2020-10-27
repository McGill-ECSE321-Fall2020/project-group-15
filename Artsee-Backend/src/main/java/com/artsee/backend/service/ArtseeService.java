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
	
	@Transactional
    public Artwork createArtwork(String name, Integer price, String description, Date dateCreated, Integer numInStock, Artist artist) {
		String error = "";
        if (name == null || name.trim().length() == 0) {
            error = error + "Artwork name cannot be empty!";
        }
        if (price == null) {
            error = error + "Artwork price cannot be empty! ";
        }
        if (price < 0) {
        	error = error + "Artwork price cannot be less than 0! ";
        }
        if (description == null || description.trim().length() == 0) {
            error = error + "Artwork description cannot be empty! ";
        }
        if (dateCreated == null) {
            error = error + "Artwork date of creation cannot be empty! ";
        }
        if (numInStock == null) {
            error = error + "Number in stock cannot be empty! ";
        }
        if (numInStock < 0) {
        	error = error + "Number in stock cannot be less than 0! ";
        }
        if (artist == null) {
            error = error + "Artist needs to be selected for an artwork! ";
        } else if (!artistRepository.existsById(artist.getEmail())) {
            error = error + "Artist does not exist! ";
        }
        
        Integer id = name.hashCode() * artist.hashCode();
        if (artworkRepository.existsById(id)) {
            error = error + "An artwork with the same name already exists for this artist!";
        }
        error = error.trim();
        
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
        Artwork artwork = new Artwork();
        artwork.setName(name);
        artwork.setArtworkID(id);
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
		List<Artwork> artworksByArtist = new ArrayList<>();
		for (Artwork a: artworkRepository.findByArtist(artist)) {
			artworksByArtist.add(a);
		}
		return artworksByArtist;
	}
	
	@Transactional
	public Artwork getArworkById(Integer id) {
		String error = "";
		
		Artwork a = artworkRepository.findArtworkByArtworkID(id);
		
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
			String description, Date dateCreated, Integer numInStock, Artist artist) {
		
		String error = "";
		if (artwork == null || !artworkRepository.existsById(artwork.getArtworkID())) {
			error = error + "Artwork does not exist!";
		}
        if (name == null || name.trim().length() == 0) {
            error = error + "Artwork name cannot be empty!";
        }
        if (price == null) {
            error = error + "Artwork price cannot be empty! ";
        }
        if (price < 0) {
        	error = error + "Artwork price cannot be less than 0! ";
        }
        if (description == null || description.trim().length() == 0) {
            error = error + "Artwork description cannot be empty! ";
        }
        if (dateCreated == null) {
            error = error + "Artwork date of creation cannot be empty! ";
        }
        if (numInStock == null) {
            error = error + "Number in stock cannot be empty! ";
        }
        if (numInStock < 0) {
        	error = error + "Number in stock cannot be less than 0! ";
        }
        if (artist == null) {
            error = error + "Artist needs to be selected for an artwork! ";
        } else if (!artistRepository.existsById(artist.getEmail())) {
            error = error + "Artist does not exist! ";
        }
        
        error = error.trim();
        
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        
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
	public Artwork deleteArtwork(Integer id) {
		String error = "";
		Artwork a = artworkRepository.findArtworkByArtworkID(id);
		
		if (a == null) {
			error = error + "Artwork with the given Id does not exist! ";
		}
		error.trim();
		
		if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
		
		Artwork deletedArtwork = a;
		artworkRepository.delete(a);
		
		return deletedArtwork;
	
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
