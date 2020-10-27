package com.artsee.backend.controller;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artsee.backend.model.*;
import com.artsee.backend.dto.*;

import com.artsee.backend.service.ArtseeService;


@CrossOrigin(origins = "*")
@RestController
public class ArtseeRestController {

	@Autowired
	private ArtseeService service;
		
//	@GetMapping(value = { "/artworks", "/artworks/" })
//    public List<ArtworkDto> getAllPersons() {
//        return service.getAllArtworks().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
//    }
//	
//	@PostMapping(value = { "/artworks/{name}", "/artworks/{name}/" })
//    public ArtworkDto createArtwork(@PathVariable("name") String name, @RequestParam(name = "artist") ArtistDto aDto,
//                                    @RequestParam String description, @RequestParam Integer price,
//                                    @RequestParam Integer numInStock, ) throws IllegalArgumentException {
//    }

//	@GetMapping(value = { "/reviews", "/reviews/" })
	

}