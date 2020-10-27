package com.artsee.backend.service;

import com.artsee.backend.dao.*;
import com.artsee.backend.model.Artist;
import com.artsee.backend.model.Artwork;
import com.artsee.backend.model.Customer;
import com.artsee.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    public Artwork createArtwork(String name, int price, String description, Date dateCreated, int numInStock, Artist artist) {
		String error = "";
        if (name == null || name.trim().length() == 0) {
            error = error + "Artwork name cannot be empty! ";
        }
//        if (price == null) {
//            error = error + "Artwork price cannot be empty! ";
//        }
        if (price <= 0) {
        	error = error + "Artwork price cannot be less than 0! ";
        }
        if (description == null || description.trim().length() == 0) {
            error = error + "Artwork description cannot be empty! ";
        }
        if (dateCreated == null) {
            error = error + "Artwork date of creation cannot be empty! ";
        }
//        if (numInStock == null) {
//            error = error + "Number in stock cannot be empty! ";
//        }
        if (numInStock < 0) {
        	error = error + "Number in stock cannot be less than 0! ";
        }
        
//        Artist artist = artistRepository.findArtistByEmail();
        
        if (artist == null) {
            error = error + "Artist needs to be assigned for an artwork! ";
        } else if (!artistRepository.existsById(artist.getEmail())) {
            error = error + "Artist does not exist! ";
        }
        
        Integer id = name.hashCode() * artist.hashCode();
        if (artworkRepository.existsById(id)) {
            error = error + "An artwork with the same name already exists for this artist! ";
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
	public Artwork updateArtwork(Integer id, String name, Integer price,
			Date date, String description, Integer numInStock, Artist artist) {
		
		String error = "";
		if (!artworkRepository.existsById(id)) {
			error = error + "Artwork does not exist!";
		}
        if (name == null || name.trim().length() == 0) {
            error = error + "Artwork name cannot be empty!";
        }
//        if (price == null) {
//            error = error + "Artwork price cannot be empty! ";
//        }
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
            error = error + "Artist needs to be assigned to an artwork! ";
        } else if (!artistRepository.existsById(artist.getEmail())) {
            error = error + "Artist does not exist! ";
        }
        
        error = error.trim();
        
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        Artwork artwork = artworkRepository.findArtworkByArtworkID(id);
        
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
		Artwork a = artworkRepository.findArtworkByArtworkID(id);
		
		if (a == null) {
			error = error + "Artwork with the given Id does not exist! ";
		}
		
		if (error.trim().length() > 0) {
            throw new IllegalArgumentException(error);
        }
		
		Artwork deletedArtwork = a;
		artworkRepository.delete(a);
		
		return deletedArtwork;
	}
	
	public Review createReview(Integer rating, String comment, Boolean wouldRecommend, Customer customer, Artist artist) {
		Review review = new Review();
//		review.setReviewID(reviewID);
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
		Review review = reviewRepository.findReviewByReviewID(reviewID);
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
