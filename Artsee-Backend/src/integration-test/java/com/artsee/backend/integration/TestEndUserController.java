//package com.artsee.backend.integration;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import org.junit.After;
//import org.json.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.artsee.backend.ArtseeApplication;
//import com.artsee.backend.controller.*;
//import com.artsee.backend.model.*;
//import com.artsee.backend.utility.TestUtility;
//import com.artsee.backend.dao.*;
//
//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ArtseeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TestEndUserController {
//
//    @LocalServerPort
//    private int port;
//  
//    @Autowired
//    private TestRestTemplate restTemplate;
//  
//    private HttpHeaders headers = new HttpHeaders();
//  
//    @Autowired
//    private EndUserRepository userDao;
//
//    private static final String ADMIN_ID = "1234";
//    private static final String EMAIL = "artist@gmail.com";
//    private static final String PASSWORD = "password";
//    private static final String FIRSTNAME = "John";
//    private static final String LASTNAME = "Doe";
//    private static final String PHONE_NUM = "8675309";
//    
//    private static final String ADMIN_ID2 = "37292";
//    private static final String EMAIL2 = "otherAdministrator@gmail.com";
//    private static final String PASSWORD2 = "newpassword";
//    private static final String FIRSTNAME2 = "Johnny";
//    private static final String LASTNAME2 = "Doherty";
//    private static final String PHONE_NUM2 = "8675310";
//
//    private static final Administrator ADMIN = TestUtility.createAdmin(ADMIN_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
//    private static final Administrator ADMIN2 = TestUtility.createAdmin(ADMIN_ID2, EMAIL2, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2);
//    
//    @Before
//    @After
//    public void cleanDataBase() {
//        userDao.deleteAll();
//    }
//
//    @Test
//    public void createAdministrator() {
//
//        Administrator c = ADMIN;
//        Administrator c2 = ADMIN2;
//
//        // create new customer
//        HttpEntity<Administrator> entity = new HttpEntity<Administrator>(c, headers);
//        // create new customer
//        HttpEntity<Administrator> entity2 = new HttpEntity<Administrator>(c2, headers);
//
//        // check that there is nothing saved so far -----------------------------------------------------------------------------------
//        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/users/" + ADMIN_ID), String.class);
//        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
//  
//        // Create and post new customer -----------------------------------------------------------------------------------
//        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/administrators"), HttpMethod.POST, entity, String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // get customer should now properly work -----------------------------------------------------------------------------------
//        getResponse = restTemplate.getForEntity(getURLWithPort("/users/" + ADMIN_ID), String.class);
//        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
//
//        assertTrue(getResponse.getBody().contains(response.getBody().toString()), getResponse.getBody().toString());
//
//        // create second customer -----------------------------------------------------------------------------------
//        ResponseEntity<String> response2 = restTemplate.exchange(getURLWithPort("/administrators"), HttpMethod.POST, entity2, String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        //get all customers -----------------------------------------------------------------------------------
//        getResponse = restTemplate.getForEntity(getURLWithPort("/users"), String.class);
//        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
//
//        assertTrue(getResponse.getBody().contains("[" + response.getBody().toString() + "," + response2.getBody().toString() + "]"), getResponse.getBody().toString());
//        
//        //delete customer 1 -----------------------------------------------------------------------------------
//        restTemplate.delete(getURLWithPort("/users/" + ADMIN_ID));
//
//        // check that customer 1 was deleted -----------------------------------------------------------------------------------
//        getResponse = restTemplate.getForEntity(getURLWithPort("/users/" + ADMIN_ID), String.class);
//        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
//
//        // delete everything created
//        delete(ADMIN_ID2);
//    }
//
//    @Test
//    public void testUpdateAdministrator() {
//
//        Administrator c2 = ADMIN2;
//
//        // create new customer
//        HttpEntity<Administrator> entity2 = new HttpEntity<Administrator>(c2, headers);
//
//        // check that there is nothing saved so far -----------------------------------------------------------------------------------
//        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/users/" + ADMIN_ID2), String.class);
//        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
//  
//        // Create and post new customer
//        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/administrators"), HttpMethod.POST, entity2, String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        c2.setEmail("newEmail@gmail.com");
//        entity2 = new HttpEntity<Administrator>(c2, headers);
//
//        ResponseEntity<String> putResponse2 = restTemplate.exchange(getURLWithPort("/users"), HttpMethod.PUT, entity2, String.class);
//        assertEquals(HttpStatus.OK, putResponse2.getStatusCode());
//        assertTrue(!(putResponse2.getBody().contains(response.getBody().toString())), putResponse2.getBody().toString());
//
//        // -- delete everything created
//        delete(ADMIN_ID2);
//    }
//
//    @Test
//    public void testCreateAdministratorBadInput() {
//
//        Administrator c = ADMIN;
//        c.setFirstName(null);
//
//        // create new customer
//        HttpEntity<Administrator> entity = new HttpEntity<Administrator>(c, headers);
//
//        // check that there is nothing saved so far -----------------------------------------------------------------------------------
//        ResponseEntity<String> getResponse = restTemplate.getForEntity(getURLWithPort("/users/" + ADMIN_ID), String.class);
//        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
//  
//        // Create and post new customer -----------------------------------------------------------------------------------
//        ResponseEntity<String> response = restTemplate.exchange(getURLWithPort("/administrators"), HttpMethod.POST, entity, String.class);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//        // nothing should've been created -----------------------------------------------------------------------------------
//        getResponse = restTemplate.getForEntity(getURLWithPort("/users/" + ADMIN_ID), String.class);
//        assertEquals(HttpStatus.BAD_REQUEST, getResponse.getStatusCode());
//
//        c.setFirstName(FIRSTNAME);
//    }
//
//
//    private void delete(String ID) {
//        restTemplate.delete(getURLWithPort("/users/" + ID));
//    }
//
//    private String getURLWithPort(String uri) {
//        return "https://artsee-backend.herokuapp.com" + uri;
//    }
//    
//}