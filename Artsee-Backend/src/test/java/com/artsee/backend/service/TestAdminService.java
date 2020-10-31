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
import org.yaml.snakeyaml.events.Event.ID;

import com.artsee.backend.model.*;
import com.artsee.backend.dao.*;
import com.artsee.backend.utility.*;


@ExtendWith(MockitoExtension.class)
public class TestAdminService {

    @Mock
    private AdministratorRepository adminDao;

    @Mock
    private EndUserRepository userDao;

    @InjectMocks
    private ArtseeService service;

    private static final String ID = "1234";
    private static final String EMAIL = "admin@gmail.com";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    
    private static final String ID2 = "37292";
    private static final String EMAIL2 = "otherAdmin@gmail.com";
    private static final String PASSWORD2 = "Newpassword";
    private static final String FIRSTNAME2 = "Johnny";
    private static final String LASTNAME2 = "Doherty";
    private static final String PHONE_NUM2 = "8675310";
    

    @BeforeEach
    public void setMockOutput() {
        lenient().when(adminDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createAdmin(ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
            } else {
                return null;
            }
        });

        

        lenient().when(adminDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ID)) {
                return Optional.of(TestUtility.createAdmin(ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM));
            } else {
                return Optional.empty();
            }
        });


        lenient().when(userDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ID)) {
                EndUser user = Mockito.mock(EndUser.class, Mockito.CALLS_REAL_METHODS);
                user = TestUtility.createAdmin(ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        });

        lenient().when(userDao.findByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createAdmin(ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
            } else {
                return null;
            }
        });


        lenient().when(adminDao.save(any(Administrator.class))).thenAnswer((InvocationOnMock invocation) -> {
            return TestUtility.createAdmin(ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        });
    }

    @Test
    public void testCreateAdmin() {
        

        Administrator admin = null;

        try {
            admin = service.createAdministrator(ID2, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            fail(e.getMessage());
        }


        assertEquals(ID2, admin.getUserID());
        assertEquals(EMAIL2, admin.getEmail());
        assertEquals(PASSWORD, admin.getPassword());
        assertEquals(FIRSTNAME, admin.getFirstName());
        assertEquals(LASTNAME, admin.getLastName());
        assertEquals(PHONE_NUM, admin.getPhoneNumber());
        
    }

    // --------------- Test incorrect inputs -----------------------
    @Test
    public void testCreateAdmintMissingID() {
        

        String error = null;

        try {
            service.createAdministrator("", EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertTrue(error.contains("Must enter a username."));

        // check again with null input
        error = null;

        try {
            service.createAdministrator(null, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertTrue(error.contains("Must enter a username."));
    }

    @Test
    public void testCreateAdminMissingEmail() {


        String error = null;

        try {
            service.createAdministrator(ID2, "", PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter an email.", error);



        // check again with null input
        error = null;

        try {
            service.createAdministrator(ID2, null, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter an email.", error);
    }

    @Test
    public void testCreateAdminMissingPassword() {

        String error = null;

        try {
            service.createAdministrator(ID2, EMAIL2, "", FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a password.", error);

        // check again with null input
        error = null;

        try {
            service.createAdministrator(ID2, EMAIL2, null, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a password.", error);
    }

    @Test
    public void testCreateAdminMissingFirstname() {
        

        String error = null;

        try {
            service.createAdministrator(ID2, EMAIL2, PASSWORD, "", LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a first name.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAdministrator(ID2, EMAIL2, PASSWORD, null, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a first name.", error);

        // check that nothing was added
        
    }

    @Test
    public void testCreateAdminMissingLastname() {
        

        String error = null;

        try {
            service.createAdministrator(ID2, EMAIL2, PASSWORD, FIRSTNAME, "", PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a last name.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAdministrator(ID2, EMAIL2, PASSWORD, FIRSTNAME, null, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a last name.", error);

        // check that nothing was added
        
    }
    
    //------ Test the rest of the crud operations ---------------------
    @Test 
    public void testGetNonexistentAdmin() {
        String error = null;

        try {
            service.getAdministratorByEmail("hello@gmail.com");
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertTrue(error.contains("Email cannot be found."));
    }
    
    @Test 
    public void testGetNonexistentAdminID() {
        String error = null;

        try {
            service.getAdministratorByID("wrongID");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertTrue(error.contains("Username cannot be found."));
    }

    @Test
    public void testDuplicateAdmin() {
        String error = null;

        try {
            service.createAdministrator(ID, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Username already exists.", error);

        error = null;

        try {
            service.createAdministrator(ID2, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Email already exists.", error);
    }
    
    @Test
    public void testGetAdminByID() {
    	Administrator admin = null;
    	String error = null;
    	try {
           admin = service.getAdministratorByID(ID);
        } catch (Exception e) {
            error = e.getMessage();
        }

    	assertEquals(ID, admin.getUserID());
    	assertEquals(EMAIL, admin.getEmail());
    	assertEquals(PASSWORD, admin.getPassword());
    	assertEquals(FIRSTNAME, admin.getFirstName());
    	assertEquals(LASTNAME, admin.getLastName());
    	assertEquals(PHONE_NUM, admin.getPhoneNumber());
    
    }
    
    @Test
    public void testGetAdminByEmail() {
    	Administrator admin = null;
    	String error = null;
    	try {
           admin = service.getAdministratorByEmail(EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }

    	assertEquals(ID, admin.getUserID());
    	assertEquals(EMAIL, admin.getEmail());
    	assertEquals(PASSWORD, admin.getPassword());
    	assertEquals(FIRSTNAME, admin.getFirstName());
    	assertEquals(LASTNAME, admin.getLastName());
    	assertEquals(PHONE_NUM, admin.getPhoneNumber());
    }
    
    
    @Test
    public void testUpdateAdmin() {
        Administrator admin = null;
        String error = null;
       
    	
    	try {
    		admin = service.updateAdministrator(ID, EMAIL2, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}

    	assertEquals(EMAIL2, admin.getEmail());
    	assertEquals(PASSWORD2, admin.getPassword());
    	assertEquals(FIRSTNAME2, admin.getFirstName());
    	assertEquals(LASTNAME2, admin.getLastName());
    	assertEquals(PHONE_NUM2, admin.getPhoneNumber());
    }
    
    
    @Test
    public void testUpdateAdminInvalidEmail() {
    	
    	String error = null;
	
    	try {
    		service.updateAdministrator(ID, "", PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals( "Must enter an email.",error);
    }
    
    @Test
    public void testUpdateAdminInvalidPassword() {
    	String error = null;
	
    	try {
    		service.updateAdministrator(ID, EMAIL2, "", FIRSTNAME2, LASTNAME2, PHONE_NUM2);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals("Must enter a password.", error);
    }
    
    @Test
    public void testUpdateAdminInvalidFirstName() {
    	String error = null;
	
    	try {
    		service.updateAdministrator(ID, EMAIL2, PASSWORD2, "", LASTNAME2, PHONE_NUM2);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals("Must enter a first name.",error);
    }
    
    @Test
    public void testUpdateAdminInvalidLastName() {
    	String error = null;
	
    	try {
    		service.updateAdministrator(ID, EMAIL2, PASSWORD2, FIRSTNAME2, "", PHONE_NUM2);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals( "Must enter a last name.", error);
    }
    
    
    @Test
    public void testUpdateAdminInvalidID() {
    	String error = null;
	
    	try {
    		service.updateAdministrator("", EMAIL2, PASSWORD2, FIRSTNAME, LASTNAME2, PHONE_NUM2);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals( "Must enter a username.", error);
    }
    
    
    @Test
    public void testUpdateAdminEmailExists() {
    	String error = null;
	
    	
    	try {
    		service.updateAdministrator(ID2, EMAIL, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertTrue(error.contains("Email already exists."), error);
    }
    
}
