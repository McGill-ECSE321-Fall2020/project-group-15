package com.artsee.backend.controller;

import com.artsee.backend.service.ArtseeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class ArtseeRestController {

	@Autowired
	private ArtseeService service;
		
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


	

}