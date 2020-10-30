package com.artsee.backend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.yaml.snakeyaml.events.Event.ID;

import com.artsee.backend.model.*;
import com.artsee.backend.dao.*;
import com.artsee.backend.utility.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


@ExtendWith(MockitoExtension.class)
public class TestAddressService {

    @Mock
    private AddressRepository addressDao;

    @InjectMocks
    private ArtseeService service;

    private static final int ID = 1234;

    private static final int ID2 = 1235;

    private static final String LINE1 = "5555 street";
    private static final String LINE2 = "apt b";
    private static final String CITY = "chicago";
    private static final String PROVENCE = "Manitoba";
    private static final String POSTAL_CODE = "h1x 2s4";
    private static final String COUNTRY = "Deutchland";


    @BeforeEach
    public void setMockOutput() {

    
        lenient().when(addressDao.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ID)) {
                return Optional.of(TestUtility.createAddress(ID, LINE1, LINE2, CITY, PROVENCE, POSTAL_CODE, COUNTRY));
            } else {
                return Optional.empty();
            }
        });


        lenient().when(addressDao.save(any(Address.class))).thenAnswer((InvocationOnMock invocation) -> {
            return TestUtility.createAddress(ID, LINE1, LINE2, CITY, PROVENCE, POSTAL_CODE, COUNTRY);
        });
    }


    @Test
    public void testCreateAddress() {
        

        Address a = null;

        try {
            a = service.createAddress(LINE1, LINE2, CITY, PROVENCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            fail(e.getMessage());
        }


        assertEquals(LINE1, a.getAddressLine1());
        assertEquals(LINE2, a.getAddressLine2());
        assertEquals(CITY, a.getCity());
        assertEquals(PROVENCE, a.getProvince());
        assertEquals(POSTAL_CODE, a.getPostalCode());
        assertEquals(COUNTRY, a.getCountry());
        
    }

    // --------------- Test incorrect inputs -----------------------
    @Test
    public void testCreateAddressMissingLine1() {
        

        String error = null;

        try {
            service.createAddress("", LINE2, CITY, PROVENCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Address cannot be empty.", error);

        // check again with null input
        error = null;

        try {
            service.createAddress(null, LINE2, CITY, PROVENCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Address cannot be empty.", error);

    }

    @Test
    public void testCreateAddressMissingCity() {

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, "", PROVENCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("City cannot be empty.", error);

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, null, PROVENCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("City cannot be empty.", error);
    }

    @Test
    public void testCreateAddressMissingProvence() {
        

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, "", POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Province cannot be empty.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, null, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Province cannot be empty.", error);

        // check that nothing was added
        
    }

    @Test
    public void testCreateAddressMissingPostalCode() {
        

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVENCE, "", COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Postal code cannot be empty.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVENCE, null, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Postal code cannot be empty.", error);

        // check that nothing was added
        
    }

    @Test
    public void testCreateAddressMissingCountry() {
        

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVENCE, POSTAL_CODE, "");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Country cannot be empty.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVENCE, POSTAL_CODE, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Country cannot be empty.", error);

        // check that nothing was added
        
    }
    
    //------ Test the rest of the crud operations ---------------------
    @Test 
    public void testGetNonexistentAdmin() {
        String error = null;

        try {
            service.getAddressById(ID);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Could not find an Address with email 1234", error);
    }

}