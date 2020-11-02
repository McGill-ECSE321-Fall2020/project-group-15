package com.artsee.backend.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;

import org.junit.After;
import org.json.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.artsee.backend.ArtseeApplication;
import com.artsee.backend.controller.*;
import com.artsee.backend.model.*;
import com.artsee.backend.utility.TestUtility;
import com.artsee.backend.dao.*;
import com.artsee.backend.dto.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtseeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestArtworkController {

    @LocalServerPort
    private int port;
  
    @Autowired
    private TestRestTemplate restTemplate;
  
    private HttpHeaders headers = new HttpHeaders();
  
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

    private static final String ARTIST_ID = "1234";
    private static final String EMAIL = "artist@gmail.com";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final String ARTIST_DESCRIPTION = "i like to paint";
    private static final float ARTIST_RATING = 0;
    
    private static final String ARTIST_ID2 = "37292";
    private static final String EMAIL2 = "otherArtist@gmail.com";
    private static final String PASSWORD2 = "newpassword";
    private static final String FIRSTNAME2 = "Johnny";
    private static final String LASTNAME2 = "Doherty";
    private static final String PHONE_NUM2 = "8675310";
    private static final String ARTIST_DESCRIPTION2 = "i like to sculpt";

    private static final Artist ARTIST = TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ARTIST_DESCRIPTION);
    private static final Artist ARTIST2 = TestUtility.createArtist(ARTIST_ID2, EMAIL2, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, ARTIST_DESCRIPTION2);
    
    private static final ArtistDto ARTISTDTO = TestUtility.createArtistDto(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ARTIST_DESCRIPTION,ARTIST_RATING);
    
    private static final int ID = 1234;
    private static final String USER_ID = "423";
    private static final String NAME = "mona lisa";
    private static final int PRICE = 100;
    private static final String DESCRIPTION = "is very nice";
    private static final Date DATE_CREATED = Date.valueOf("2010-01-05");
    private static final int NUM_IN_STOCK = 1;

    private static final int ID2 = 12234;
    private static final String USER_ID2 = "423";
    private static final String NAME2 = "mona lisa";
    private static final int PRICE2 = 100;
    private static final String DESCRIPTION2 = "is very nice";
    private static final Date DATE_CREATED2 = Date.valueOf("2010-01-05");
    private static final int NUM_IN_STOCK2 = 1;

    private static final Artwork ARTWORK = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
    private static final Artwork ARTWORK2 = TestUtility.createArtwork(ID2, NAME2, PRICE2, DESCRIPTION2, DATE_CREATED2, NUM_IN_STOCK2, ARTIST2);
    @Before
    @After
	public void clearDatabase() {
		
		// Clear Artwork before Artist before Review to avoid inconsistency
		artworkRepository.deleteAll();
		artistRepository.deleteAll();
		reviewRepository.deleteAll();

		//Clear ArtworkOrder, Review Before Customer
		artworkOrderRepository.deleteAll();
		reviewRepository.deleteAll();

		//Clear Review before Customer before Address to avoid inconsistency
		customerRepository.deleteAll();
		addressRepository.deleteAll();
		
		// Has no references, can delete in any order
		endUserRepository.deleteAll();
		administratorRepository.deleteAll();
	}

    @Test
    public void createArtwork() {

        Artist a = ARTIST;
        Artist a2 = ARTIST2;

        // create new artist
        HttpEntity<Artist> aentity = new HttpEntity<Artist>(a, headers);
        // create new artist
        HttpEntity<Artist> aentity2 = new HttpEntity<Artist>(a2, headers);

        ResponseEntity<String> aresponse = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity, String.class);
        assertEquals(HttpStatus.OK, aresponse.getStatusCode(), aresponse.getBody().toString());

        ResponseEntity<String> aresponse2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity2, String.class);
        assertEquals(HttpStatus.OK, aresponse2.getStatusCode(), aresponse2.getBody().toString());


        Artwork c = ARTWORK;
        Artwork c2 = ARTWORK2;

        // create new artist
        HttpEntity<Artwork> entity = new HttpEntity<Artwork>(c, headers);
        // create new artist
        HttpEntity<Artwork> entity2 = new HttpEntity<Artwork>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artworks/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new artwork -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artworks"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode(), response.getBody().toString());

        // get artwork should now properly work -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworks/" + ID), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode(), getResponse.getBody().toString());

        assertTrue(getResponse.getBody().contains(response.getBody().toString()), getResponse.getBody().toString());

        // create second artwork -----------------------------------------------------------------------------------
        ResponseEntity<String> response2 = restTemplate.exchange(getURLWithPort("/artworks"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //get all artworks -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworks"), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains("[" + response.getBody().toString() + "," + response2.getBody().toString() + "]"), getResponse.getBody().toString());
        
        //delete artwork 1 -----------------------------------------------------------------------------------
        restTemplate.delete(getURLWithPort("/artworks/" + ID));

        // check that artwork 1 was deleted -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworks/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
    }

    @Test
    public void testUpdateArtwork() {

        Artist a = ARTIST;
        Artist a2 = ARTIST2;

        // create new artist
        HttpEntity<Artist> aentity = new HttpEntity<Artist>(a, headers);
        // create new artist
        HttpEntity<Artist> aentity2 = new HttpEntity<Artist>(a2, headers);

        ResponseEntity<String> aresponse = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity, String.class);
        assertEquals(HttpStatus.OK, aresponse.getStatusCode());

        ResponseEntity<String> aresponse2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity2, String.class);
        assertEquals(HttpStatus.OK, aresponse2.getStatusCode());

        Artwork c2 = ARTWORK2;

        // create new artwork
        HttpEntity<Artwork> entity2 = new HttpEntity<Artwork>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artworks/" + ID2), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new artwork
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artworks"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        c2.setName("white painting");
        entity2 = new HttpEntity<Artwork>(c2, headers);

        ResponseEntity<String> putResponse2 = restTemplate.exchange(getURLWithPort("/artworks"), HttpMethod.PUT, entity2, String.class);
        assertEquals(HttpStatus.OK, putResponse2.getStatusCode());
        assertTrue(!(putResponse2.getBody().contains(response.getBody().toString())), putResponse2.getBody().toString());

    }

    @Test
    public void testCreateArtworkBadInput() {

        Artist a = ARTIST;
        Artist a2 = ARTIST2;

        // create new artist
        HttpEntity<Artist> aentity = new HttpEntity<Artist>(a, headers);
        // create new artist
        HttpEntity<Artist> aentity2 = new HttpEntity<Artist>(a2, headers);

        ResponseEntity<String> aresponse = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity, String.class);
        assertEquals(HttpStatus.OK, aresponse.getStatusCode(), aresponse.getBody().toString());

        ResponseEntity<String> aresponse2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity2, String.class);
        assertEquals(HttpStatus.OK, aresponse2.getStatusCode(), aresponse2.getBody().toString());

        Artwork c = ARTWORK;
        c.setArtist(null);

        // create new artwork
        HttpEntity<Artwork> entity = new HttpEntity<Artwork>(c, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artworks/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new artwork -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artworks"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // nothing should've been created -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworks/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        c.setArtist(ARTIST);

    }


    private void delete(int id) {
        restTemplate.delete(getURLWithPort("/artworks/" + id));
    }

    private String getURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
}