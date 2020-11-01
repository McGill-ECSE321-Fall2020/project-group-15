package com.artsee.backend.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.After;
import org.json.*;
import org.junit.Before;
import org.junit.Test;
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

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtseeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestArtistController {

    @LocalServerPort
    private int port;
  
    @Autowired
    private TestRestTemplate restTemplate;
  
    private HttpHeaders headers = new HttpHeaders();
  
    @Autowired
    private ArtistRepository artistDao;

    private static final String ARTIST_ID = "1234";
    private static final String EMAIL = "artist@gmail.com";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final String DESCRIPTION = "i like to paint";
    
    private static final String ARTIST_ID2 = "37292";
    private static final String EMAIL2 = "otherArtist@gmail.com";
    private static final String PASSWORD2 = "newpassword";
    private static final String FIRSTNAME2 = "Johnny";
    private static final String LASTNAME2 = "Doherty";
    private static final String PHONE_NUM2 = "8675310";
    private static final String DESCRIPTION2 = "i like to sculpt";

    private static final Artist ARTIST = TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
    private static final Artist ARTIST2 = TestUtility.createArtist(ARTIST_ID2, EMAIL2, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION2);
    @Before
    @After
    public void cleanDataBase() {
        artistDao.deleteAll();
    }

    @Test
    public void createArtist() {

        Artist c = ARTIST;
        Artist c2 = ARTIST2;

        // create new customer
        HttpEntity<Artist> entity = new HttpEntity<Artist>(c, headers);
        // create new customer
        HttpEntity<Artist> entity2 = new HttpEntity<Artist>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artists/" + ARTIST_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // get customer should now properly work -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artists/" + ARTIST_ID), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains(response.getBody().toString()), getResponse.getBody().toString());

        // create second customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //get all customers -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artists"), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains("[" + response.getBody().toString() + "," + response2.getBody().toString() + "]"), getResponse.getBody().toString());
        
        //delete customer 1 -----------------------------------------------------------------------------------
        restTemplate.delete(getURLWithPort("/artists/" + ARTIST_ID));

        // check that customer 1 was deleted -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artists/" + ARTIST_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        // delete everything created
        delete(ARTIST_ID2);
    }

    @Test
    public void testUpdateArtist() {

        Artist c2 = ARTIST2;

        // create new customer
        HttpEntity<Artist> entity2 = new HttpEntity<Artist>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artists/" + ARTIST_ID2), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        c2.setEmail("newEmail@gmail.com");
        entity2 = new HttpEntity<Artist>(c2, headers);

        ResponseEntity<String> putResponse2 = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.PUT, entity2, String.class);
        assertEquals(HttpStatus.OK, putResponse2.getStatusCode());
        assertTrue(!(putResponse2.getBody().contains(response.getBody().toString())), putResponse2.getBody().toString());

        // -- delete everything created
        delete(ARTIST_ID2);
    }

    @Test
    public void testCreateArtistBadInput() {

        Artist c = ARTIST;
        c.setFirstName(null);

        // create new customer
        HttpEntity<Artist> entity = new HttpEntity<Artist>(c, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artists/" + ARTIST_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artists"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // nothing should've been created -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artists/" + ARTIST_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        c.setFirstName(FIRSTNAME);
    }


    private void delete(String ID) {
        restTemplate.delete(getURLWithPort("/artists/" + ID));
    }

    private String getURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
}