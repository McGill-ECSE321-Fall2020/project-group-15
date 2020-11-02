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
public class TestCustomerController {

    @LocalServerPort
    private int port;
  
    @Autowired
    private TestRestTemplate restTemplate;
  
    private HttpHeaders headers = new HttpHeaders();
  
    @Autowired
    private CustomerRepository customerDao;

    private static final int ADDRESS_ID = 1;
    private static final String LINE1 = "5555 street";
    private static final String LINE2 = "apt b";
    private static final String CITY = "chicago";
    private static final String PROVINCE = "Manitoba";
    private static final String POSTAL_CODE = "h1x 2s4";
    private static final String COUNTRY = "Deutchland";

    private static final String CUSTOMER_ID = "c1";
    private static final String EMAIL = "customer1@gmail.com";

    private static final String CUSTOMER_ID2 = "c2";
    private static final String EMAIL2 = "customer2@gmail.com";

    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final Address ADDRESS = TestUtility.createAddress(ADDRESS_ID, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);

    private static final Customer CUSTOMER = TestUtility.createCustomer(CUSTOMER_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
    private static final Customer CUSTOMER2 = TestUtility.createCustomer(CUSTOMER_ID2, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
  
    @Before
    @After
    public void cleanDataBase() {
      customerDao.deleteAll();
    }

    @Test
    public void createCustomer() {

        Customer c = CUSTOMER;
        Customer c2 = CUSTOMER2;

        // create new customer
        HttpEntity<Customer> entity = new HttpEntity<Customer>(c, headers);
        // create new customer
        HttpEntity<Customer> entity2 = new HttpEntity<Customer>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/customers/" + CUSTOMER_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // get customer should now properly work -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/customers/" + CUSTOMER_ID), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains(response.getBody().toString()), getResponse.getBody().toString());

        // create second customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response2 = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //get all customers -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/customers"), String.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertTrue(getResponse.getBody().contains("[" + response.getBody().toString() + "," + response2.getBody().toString() + "]"), getResponse.getBody().toString());
        
        //delete customer 1 -----------------------------------------------------------------------------------
        restTemplate.delete(getURLWithPort("/customers/" + CUSTOMER_ID));

        // check that customer 1 was deleted -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/customers/" + CUSTOMER_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        // delete everything created
        delete(CUSTOMER_ID2);
    }

    @Test
    public void testUpdateCustomer() {

        Customer c2 = CUSTOMER2;

        // create new customer
        HttpEntity<Customer> entity2 = new HttpEntity<Customer>(c2, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/customers/" + CUSTOMER_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, entity2, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        c2.setEmail("newEmail@gmail.com");
        entity2 = new HttpEntity<Customer>(c2, headers);

        ResponseEntity<String> putResponse2 = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.PUT, entity2, String.class);
        assertEquals(HttpStatus.OK, putResponse2.getStatusCode());
        assertTrue(!(putResponse2.getBody().contains(response.getBody().toString())), putResponse2.getBody().toString());

        // -- delete everything created
        delete(CUSTOMER_ID2);
    }

    @Test
    public void testCreateCustomerBadInput() {

        Customer c = CUSTOMER;
        c.setFirstName(null);

        // create new customer
        HttpEntity<Customer> entity = new HttpEntity<Customer>(c, headers);

        // check that there is nothing saved so far -----------------------------------------------------------------------------------
        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/customers/" + CUSTOMER_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
  
        // Create and post new customer -----------------------------------------------------------------------------------
        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/customers"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // nothing should've been created -----------------------------------------------------------------------------------
        getResponse = restTemplate.getForEntity(getURLWithPort("/customers/" + CUSTOMER_ID), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());

        c.setFirstName(FIRSTNAME);
    }


    private void delete(String ID) {
        restTemplate.delete(getURLWithPort("/customers/" + ID));
    }

    private String getURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
}
