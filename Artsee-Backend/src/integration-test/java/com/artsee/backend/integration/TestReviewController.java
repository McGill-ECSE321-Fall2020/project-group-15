package com.artsee.backend.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
import com.artsee.backend.model.*;
import com.artsee.backend.utility.TestUtility;
import com.artsee.backend.dao.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtseeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestReviewController {

    @LocalServerPort
    private int port;
  
    @Autowired
    private TestRestTemplate restTemplate;
  
    private HttpHeaders headers = new HttpHeaders();

    private static final String ARTIST_ID = "1234";
    private static final String EMAIL = "artist@gmail.com";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final String ARTIST_DESCRIPTION = "i like to paint";
    
    private static final String ARTIST_ID2 = "37292";
    private static final String EMAIL2 = "otherArtist@gmail.com";
    private static final String PASSWORD2 = "newpassword";
    private static final String FIRSTNAME2 = "Johnny";
    private static final String LASTNAME2 = "Doherty";
    private static final String PHONE_NUM2 = "8675310";
    private static final String ARTIST_DESCRIPTION2 = "i like to sculpt";

    private static final Artist ARTIST = TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ARTIST_DESCRIPTION);
    private static final Artist ARTIST2 = TestUtility.createArtist(ARTIST_ID2, EMAIL2, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, ARTIST_DESCRIPTION2);

    private static final int ADDRESS_ID = 1;
    private static final String LINE1 = "5555 street";
    private static final String LINE2 = "apt b";
    private static final String CITY = "chicago";
    private static final String PROVINCE = "Manitoba";
    private static final String POSTAL_CODE = "h1x 2s4";
    private static final String COUNTRY = "Deutchland";

    private static final String CUSTOMER_ID = "c1";
    private static final String CUSTOMER_EMAIL = "customer1@gmail.com";

    private static final String CUSTOMER_ID2 = "c2";
    private static final String CUSTOMER_EMAIL2 = "customer2@gmail.com";

    private static final Address ADDRESS = TestUtility.createAddress(ADDRESS_ID, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);

    private static final Customer CUSTOMER = TestUtility.createCustomer(CUSTOMER_ID, CUSTOMER_EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
    private static final Customer CUSTOMER2 = TestUtility.createCustomer(CUSTOMER_ID2, CUSTOMER_EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);



	private static final Integer ID = 1234;
    private static final Integer RATING = 4;
	private static final String COMMENT = "A nice artwork";
	private static final Boolean WOULDRECCOMEND = true;
    
    private static final Integer ID2 = 2;
    private static final Integer RATING2 = 2;
    private static final String COMMENT2 = "A nice artwork";
    private static final Boolean WOULDRECCOMEND2 = true;
    
    private static final Review REVIEW = TestUtility.createReview(ID, RATING, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST);
    private static final Review REVIEW2 = TestUtility.createReview(ID2, RATING2, COMMENT2, WOULDRECCOMEND2, CUSTOMER2, ARTIST2);

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
    public void createReview() {

        Artist a = ARTIST;
        Artist a2 = ARTIST2;

        // create new customer
        HttpEntity<Artist> aentity = new HttpEntity<Artist>(a, headers);
        // create new customer
        HttpEntity<Artist> aentity2 = new HttpEntity<Artist>(a2, headers);

        ResponseEntity<String> aresponse = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity, String.class);
        assertEquals(HttpStatus.OK, aresponse.getStatusCode(), aresponse.getBody().toString());

        ResponseEntity<String> aresponse2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity2, String.class);
        assertEquals(HttpStatus.OK, aresponse2.getStatusCode(), aresponse2.getBody().toString());

        Customer C = CUSTOMER;
        Customer C2 = CUSTOMER2;

        // create new customer
        HttpEntity<Customer> centity = new HttpEntity<Customer>(C, headers);
        // create new customer
        HttpEntity<Customer> centity2 = new HttpEntity<Customer>(C2, headers);

        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> cresponse = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, centity, String.class);
        assertEquals(HttpStatus.OK, cresponse.getStatusCode(), cresponse.getBody().toString());

        ResponseEntity<String> cresponse2 = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, centity2, String.class);
        assertEquals(HttpStatus.OK, cresponse2.getStatusCode(), cresponse.getBody().toString());

        Review c = REVIEW;
        Review c2 = REVIEW2;

        // create new customer
        HttpEntity<Review> entity = new HttpEntity<Review>(c, headers);
        // create new customer
        HttpEntity<Review> entity2 = new HttpEntity<Review>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/reviews/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/reviews"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // get review should now properly work -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/reviews/" + ID), String.class);
//        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
//        assertTrue(getResponse.getBody().contains(response.getBody().toString()), getResponse.getBody().toString());

        // create second review -----------------------------------------------------------------------------------
        ResponseEntity<String> response2 = restTemplate.exchange(getURLWithPort("/reviews"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //get all reviews -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/reviews"), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains("[" + response.getBody().toString() + "," + response2.getBody().toString() + "]"), getResponse.getBody().toString());
        
        //delete review 1 -----------------------------------------------------------------------------------
        restTemplate.delete(getURLWithPort("/reviews/" + ID));

        // check that review 1 was deleted -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/reviews/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
    }

    @Test
    public void testUpdateReview() {

        Artist a = ARTIST;
        Artist a2 = ARTIST2;

        // create new customer
        HttpEntity<Artist> aentity = new HttpEntity<Artist>(a, headers);
        // create new customer
        HttpEntity<Artist> aentity2 = new HttpEntity<Artist>(a2, headers);

        ResponseEntity<String> aresponse = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity, String.class);
        assertEquals(HttpStatus.OK, aresponse.getStatusCode(), aresponse.getBody().toString());

        ResponseEntity<String> aresponse2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity2, String.class);
        assertEquals(HttpStatus.OK, aresponse2.getStatusCode(), aresponse2.getBody().toString());

        Customer C = CUSTOMER;
        Customer C2 = CUSTOMER2;

        // create new customer
        HttpEntity<Customer> centity = new HttpEntity<Customer>(C, headers);
        // create new customer
        HttpEntity<Customer> centity2 = new HttpEntity<Customer>(C2, headers);

        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> cresponse = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, centity, String.class);
        assertEquals(HttpStatus.OK, cresponse.getStatusCode(), cresponse.getBody().toString());

        ResponseEntity<String> cresponse2 = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, centity2, String.class);
        assertEquals(HttpStatus.OK, cresponse2.getStatusCode(), cresponse.getBody().toString());

        Review c2 = REVIEW2;

        // create new customer
        HttpEntity<Review> entity2 = new HttpEntity<Review>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/reviews/" + ID2), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new review
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/reviews"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        c2.setComment("white painting");
        entity2 = new HttpEntity<Review>(c2, headers);

        ResponseEntity<String> putResponse2 = restTemplate.exchange(getURLWithPort("/reviews"), HttpMethod.PUT, entity2, String.class);
//        assertEquals(HttpStatus.OK, putResponse2.getStatusCode());
        assertTrue(!(putResponse2.getBody().contains(response.getBody().toString())), putResponse2.getBody().toString());
    }

    @Test
    public void testCreateReviewBadInput() {

        Artist a = ARTIST;
        Artist a2 = ARTIST2;

        // create new customer
        HttpEntity<Artist> aentity = new HttpEntity<Artist>(a, headers);
        // create new customer
        HttpEntity<Artist> aentity2 = new HttpEntity<Artist>(a2, headers);

        ResponseEntity<String> aresponse = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity, String.class);
        assertEquals(HttpStatus.OK, aresponse.getStatusCode(), aresponse.getBody().toString());

        ResponseEntity<String> aresponse2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, aentity2, String.class);
        assertEquals(HttpStatus.OK, aresponse2.getStatusCode(), aresponse2.getBody().toString());

        Customer C = CUSTOMER;
        Customer C2 = CUSTOMER2;

        // create new customer
        HttpEntity<Customer> centity = new HttpEntity<Customer>(C, headers);
        // create new customer
        HttpEntity<Customer> centity2 = new HttpEntity<Customer>(C2, headers);

        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> cresponse = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, centity, String.class);
        assertEquals(HttpStatus.OK, cresponse.getStatusCode(), cresponse.getBody().toString());

        ResponseEntity<String> cresponse2 = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, centity2, String.class);
        assertEquals(HttpStatus.OK, cresponse2.getStatusCode(), cresponse.getBody().toString());

        Review c = REVIEW;
        c.setArtist(null);

        // create new customer
        HttpEntity<Review> entity = new HttpEntity<Review>(c, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/reviews/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/reviews"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // nothing should've been created -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/reviews/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        c.setArtist(ARTIST);
    }


    private void delete(int id) {
        restTemplate.delete(getURLWithPort("/reviews/" + id));
    }

    private String getURLWithPort(String uri) {
        return "https://artsee-backend.herokuapp.com" + uri;
    }
    
}
