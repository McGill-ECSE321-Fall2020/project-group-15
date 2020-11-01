package com.artsee.backend.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
public class TestArtworkOrderController {

    @LocalServerPort
    private int port;
  
    @Autowired
    private TestRestTemplate restTemplate;
  
    private HttpHeaders headers = new HttpHeaders();
  
    @Autowired
    private ArtworkOrderRepository reviewDao;

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

    private static final int ARTWORK_ID = 1234;
    private static final String NAME = "mona lisa";
    private static final int PRICE = 100;
    private static final String DESCRIPTION = "is very nice";
    private static final Date DATE_CREATED = Date.valueOf("2010-01-05");
    private static final int NUM_IN_STOCK = 1;

    private static final int ARTWORK_ID2 = 1234;
    private static final String NAME2 = "mona lisa";
    private static final int PRICE2 = 100;
    private static final String DESCRIPTION2 = "is very nice";
    private static final Date DATE_CREATED2 = Date.valueOf("2010-01-05");
    private static final int NUM_IN_STOCK2 = 1;

    private static final Artwork ARTWORK = TestUtility.createArtwork(ARTWORK_ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
    private static final Artwork ARTWORK2 = TestUtility.createArtwork(ARTWORK_ID2, NAME2, PRICE2, DESCRIPTION2, DATE_CREATED2, NUM_IN_STOCK2, ARTIST2);

    private static final DeliveryMethod DELIVERYMETHOD = DeliveryMethod.SHIP;
    private static final int ID = 1;
    private static final Set<Artwork> ARTLIST = new HashSet<Artwork>();

    private static final ArtworkOrder ARTWORKORDER = TestUtility.createArtworkOrder(ID, DELIVERYMETHOD, CUSTOMER, ARTLIST);
    
    private static final DeliveryMethod DELIVERYMETHOD2 = DeliveryMethod.SHIP;
    private static final int ID2 = 1;
    private static final Set<Artwork> ARTLIST2 = new HashSet<Artwork>();

    private static final ArtworkOrder ARTWORKORDER2 = TestUtility.createArtworkOrder(ID, DELIVERYMETHOD, CUSTOMER, ARTLIST);
    @Before
    @After
    public void cleanDataBase() {
        reviewDao.deleteAll();

        ARTLIST.add(ARTWORK);
        ARTLIST.add(ARTWORK2);
    }

    @Test
    public void createArtworkOrder() {

        ArtworkOrder c = ARTWORKORDER;
        ArtworkOrder c2 = ARTWORKORDER2;

        // create new customer
        HttpEntity<ArtworkOrder> entity = new HttpEntity<ArtworkOrder>(c, headers);
        // create new customer
        HttpEntity<ArtworkOrder> entity2 = new HttpEntity<ArtworkOrder>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artworkOrders/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artworkorders"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // get customer should now properly work -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworkOrders/" + ID), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains(response.getBody().toString()), getResponse.getBody().toString());

        // create second customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response2 = restTemplate.exchange(getURLWithPort("/artworkOrders"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //get all customers -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworkOrders"), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains("[" + response.getBody().toString() + "," + response2.getBody().toString() + "]"), getResponse.getBody().toString());
        
        //delete customer 1 -----------------------------------------------------------------------------------
        restTemplate.delete(getURLWithPort("/artworkOrders/" + ID));

        // check that customer 1 was deleted -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworkOrders/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        // delete everything created
        delete(ID2);
    }

    @Test
    public void testUpdateArtworkOrder() {

        ArtworkOrder c2 = ARTWORKORDER2;

        // create new customer
        HttpEntity<ArtworkOrder> entity2 = new HttpEntity<ArtworkOrder>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artworkOrders/" + ID2), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artworkOrders"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        c2.setDeliveryMethod(DeliveryMethod.PICKUP);
        entity2 = new HttpEntity<ArtworkOrder>(c2, headers);

        ResponseEntity<String> putResponse2 = restTemplate.exchange(getURLWithPort("/artworkOrders"), HttpMethod.PUT, entity2, String.class);
        assertEquals(HttpStatus.OK, putResponse2.getStatusCode());
        assertTrue(!(putResponse2.getBody().contains(response.getBody().toString())), putResponse2.getBody().toString());

        // -- delete everything created
        delete(ID2);
    }

    @Test
    public void testCreateArtworkOrderBadInput() {

        ArtworkOrder c = ARTWORKORDER;
        c.setCustomer(null);

        // create new customer
        HttpEntity<ArtworkOrder> entity = new HttpEntity<ArtworkOrder>(c, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/artworkOrders/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/artworkOrders"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // nothing should've been created -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/artworkOrders/" + ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        c.setCustomer(CUSTOMER);
    }


    private void delete(int id) {
        restTemplate.delete(getURLWithPort("/artworkOrders/" + id));
    }

    private String getURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
}