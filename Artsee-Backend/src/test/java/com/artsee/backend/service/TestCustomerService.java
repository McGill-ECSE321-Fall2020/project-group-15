package com.artsee.backend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
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

import com.artsee.backend.model.*;
import com.artsee.backend.dao.*;
import com.artsee.backend.utility.*;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {
	
	@Mock
	private CustomerRepository customerDao;
	
	@Mock EndUserRepository userDao;
	
	@InjectMocks
	private ArtseeService service;
	
    private static final String CUSTOMER_ID = "1234";
    private static final String EMAIL = "customer@gmail.com";

    private static final String CUSTOMER_ID2 = "37292";
    private static final String EMAIL2 = "otherCustomer@gmail.com";

    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final Address ADDRESS = new Address();
    
    @BeforeEach
    public void setMockOutput() {
        lenient().when(customerDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createCustomer(CUSTOMER_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
            } else {
                return null;
            }
        });

        

        lenient().when(customerDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_ID)) {
                return Optional.of(TestUtility.createCustomer(CUSTOMER_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS));
            } else {
                return Optional.empty();
            }
        });


        lenient().when(userDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CUSTOMER_ID)) {
                EndUser user = Mockito.mock(EndUser.class, Mockito.CALLS_REAL_METHODS);
                user = TestUtility.createCustomer(CUSTOMER_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        });

        lenient().when(userDao.findByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createCustomer(CUSTOMER_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
            } else {
                return null;
            }
        });


        lenient().when(customerDao.save(any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
            return TestUtility.createCustomer(CUSTOMER_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);

        });
    }
    
    @Test
    public void testCreateCustomer() {
        assertEquals(0, service.getAllCustomers().size());

        Customer customer = null;

        try {
            customer = service.createCustomer(CUSTOMER_ID2, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            fail(e.getMessage());
        }


        assertEquals(CUSTOMER_ID2, customer.getUserID());
        assertEquals(EMAIL2, customer.getEmail());
        assertEquals(PASSWORD, customer.getPassword());
        assertEquals(FIRSTNAME, customer.getFirstName());
        assertEquals(LASTNAME, customer.getLastName());
        assertEquals(PHONE_NUM, customer.getPhoneNumber());
        assertEquals(ADDRESS, customer.getAddress());

        
    }

    // --------------- Test incorrect inputs -----------------------
    @Test
    public void testCreateCustomerMissingID() {
        assertEquals(0, service.getAllCustomers().size());

        String error = null;

        try {
            service.createCustomer("", EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a username.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());

        // check again with null input
        error = null;

        try {
            service.createCustomer(null, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a username.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());
    }

    @Test
    public void testCreateCustomerMissingEmail() {
        assertEquals(0, service.getAllCustomers().size());

        String error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, "", PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter an email.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());

        // check again with null input
        error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, null, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter an email.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());
    }

    @Test
    public void testCreateCustomerMissingPassword() {
        assertEquals(0, service.getAllCustomers().size());

        String error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, EMAIL2, "", FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a password.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());

        // check again with null input
        error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, EMAIL2, null, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a password.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());
    }

    @Test
    public void testCreateCustomerMissingFirstname() {
        assertEquals(0, service.getAllCustomers().size());

        String error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, EMAIL2, PASSWORD, "", LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a first name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());

        // check again with null input
        error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, EMAIL2, PASSWORD, null, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a first name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());
    }

    @Test
    public void testCreateCustomerMissingLastname() {
        assertEquals(0, service.getAllCustomers().size());

        String error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, EMAIL2, PASSWORD, FIRSTNAME, "", PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a last name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());

        // check again with null input
        error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, EMAIL2, PASSWORD, FIRSTNAME, null, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a last name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllCustomers().size());
    }
    
    //------ Test the rest of the crud operations ---------------------
    @Test 
    public void testGetNonexistentCustomer() {
        String error = null;

        try {
            service.getCustomerByEmail("hello@gmail.com");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Email cannot be found.", error);
    }

    @Test
    public void testDuplicateCustomer() {
        String error = null;

        try {
            service.createCustomer(CUSTOMER_ID, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Username already exists.", error);

        error = null;

        try {
            service.createCustomer(CUSTOMER_ID2, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Email already exists.", error);
    }



    
}
