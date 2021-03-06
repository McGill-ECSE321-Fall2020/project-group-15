package com.artsee.backend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;


import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import com.artsee.backend.model.*;
import com.artsee.backend.dao.*;
import com.artsee.backend.utility.*;


@ExtendWith(MockitoExtension.class)
public class TestArtistService {

    @Mock
    private ArtistRepository artistDao;

    @Mock
    private EndUserRepository userDao;

    @InjectMocks
    private ArtseeService service;

    private static final String ARTIST_ID = "1234";
    private static final String EMAIL = "artist@gmail.com";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final String DESCRIPTION = "i like to paint";
    private static final String profilePictureURL = "url";

 
    private static final String ARTIST_ID2 = "37292";
    private static final String EMAIL2 = "otherArtist@gmail.com";
    private static final String PASSWORD2 = "newpassword";
    private static final String FIRSTNAME2 = "Johnny";
    private static final String LASTNAME2 = "Doherty";
    private static final String PHONE_NUM2 = "8675310";
    private static final String DESCRIPTION2 = "i like to sculpt";
    
    private static final String ARTIST_ID3 = "37232";
    private static final String EMAIL3 = "thirdArtist@gmail.com";
    private static final String PASSWORD3 = "newerpassword";
    private static final String FIRSTNAME3 = "Joe";
    private static final String LASTNAME3 = "Deer";
    private static final String PHONE_NUM3 = "8675311";
    private static final String DESCRIPTION3 = "i like to draw";
    

    @BeforeEach
    public void setMockOutput() {
        lenient().when(artistDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
            } else {
                return null;
            }
        });

        

        lenient().when(artistDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ARTIST_ID)) {
                return Optional.of(TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL));
            } else if(invocation.getArgument(0).equals(ARTIST_ID3)) {
            	return Optional.of(TestUtility.createArtist(ARTIST_ID3, EMAIL3, PASSWORD3, FIRSTNAME3, LASTNAME3, PHONE_NUM3, DESCRIPTION3, profilePictureURL));
            }else {
                return Optional.empty();
            }
        });


        lenient().when(userDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ARTIST_ID)) {
                EndUser user = Mockito.mock(EndUser.class, Mockito.CALLS_REAL_METHODS);
                user = TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        });

        lenient().when(userDao.findByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
            } else {
                return null;
            }
        });


        lenient().when(artistDao.save(any(Artist.class))).thenAnswer((InvocationOnMock invocation) -> {
            return TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);

        });
    }
    
    @Test
    public void testCreateArtist() {
        assertEquals(0, service.getAllArtists().size());

        Artist artist = null;

        try {
            artist = service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            fail(e.getMessage());
        }


        assertEquals(ARTIST_ID2, artist.getUserID());
        assertEquals(EMAIL2, artist.getEmail());
        assertEquals(PASSWORD, artist.getPassword());
        assertEquals(FIRSTNAME, artist.getFirstName());
        assertEquals(LASTNAME, artist.getLastName());
        assertEquals(PHONE_NUM, artist.getPhoneNumber());
        assertEquals(DESCRIPTION, artist.getArtistDescription());

        
    }

    // --------------- Test incorrect inputs -----------------------
    @Test
    public void testCreateArtistMissingID() {
        assertEquals(0, service.getAllArtists().size());

        String error = null;

        try {
            service.createArtist("", EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a username.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(null, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a username.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());
    }

    @Test
    public void testCreateArtistMissingEmail() {
        assertEquals(0, service.getAllArtists().size());

        String error = null;

        try {
            service.createArtist(ARTIST_ID2, "", PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter an email.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, null, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter an email.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());
    }

    @Test
    public void testCreateArtistMissingPassword() {
        assertEquals(0, service.getAllArtists().size());

        String error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, "", FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a password.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, null, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a password.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());
    }

    @Test
    public void testCreateArtistMissingFirstname() {
        assertEquals(0, service.getAllArtists().size());

        String error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, "", LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a first name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, null, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a first name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());
    }

    @Test
    public void testCreateArtistMissingLastname() {
        assertEquals(0, service.getAllArtists().size());

        String error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, FIRSTNAME, "", PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a last name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, FIRSTNAME, null, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a last name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());
    }
    
    //------ Test the rest of the crud operations ---------------------
    @Test 
    public void testGetNonexistentArtist() {
        String error = null;

        try {
            service.getArtistByEmail("hello@gmail.com");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Email cannot be found.", error);

    }
    
    @Test
    public void testGetNonexistentArtistID() {
    	String error = null;
    	
    	try { 
    		service.getArtistByID("otherID");
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals("Username cannot be found.", error);
    }

    @Test
    public void testDuplicateArtist() {
        String error = null;

        try {
            service.createArtist(ARTIST_ID, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Username already exists.", error);

        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION, profilePictureURL);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Email already exists.", error);
    }

    @Test
    public void testUpdateArtist() {
    	String error;
    	Artist artist = null;
    	
    	try {
    		artist = service.updateArtist(ARTIST_ID, EMAIL2, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION2, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}

    	assertEquals(EMAIL2, artist.getEmail());
    	assertEquals(PASSWORD2, artist.getPassword());
    	assertEquals(FIRSTNAME2, artist.getFirstName());
    	assertEquals(LASTNAME2, artist.getLastName());
    	assertEquals(PHONE_NUM2, artist.getPhoneNumber());
    	assertEquals(DESCRIPTION2, artist.getArtistDescription());
    }
    
    @Test
    public void testUpdateArtistInvalidEmail() {
    	
    	String error = null;
	
    	try {
    		service.updateArtist(ARTIST_ID, "", PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION2, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals("Must enter an email.", error);

    }
    
    @Test
    public void testUpdateArtistInvalidFirstName() {
    	
    	String error = null;
	
    	try {
    		service.updateArtist(ARTIST_ID, EMAIL2, PASSWORD2, "", LASTNAME2, PHONE_NUM2, DESCRIPTION2, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals("Must enter a first name.", error);

    }

    @Test
    public void testUpdateArtistInvalidLastName() {
    	
    	String error = null;
	
    	try {
    		service.updateArtist(ARTIST_ID, EMAIL2, PASSWORD2, FIRSTNAME2, "", PHONE_NUM2, DESCRIPTION2, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals("Must enter a last name.", error);

    }
    
    @Test
    public void testUpdateArtistInvalidPassword() {
    	
    	String error = null;
	
    	try {
    		service.updateArtist(ARTIST_ID, EMAIL2, "", FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION2, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals("Must enter a password.", error);

    }
    @Test
    public void testUpdateArtistInvalidID() {
    	
    	String error = null;
	
    	try {
    		service.updateArtist("", EMAIL2, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION2, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertTrue(error.contains("Must enter a username."), error);

    }
    
    @Test
    public void testUpdateArtistEmailExists() {
    	String error = null;
	
    	try {
    		service.updateArtist(ARTIST_ID3, EMAIL, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION3, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals("Email already exists.", error);
    }
    
    
    @Test // new email = old email
    public void testUpdateCustomerRepeatEmail() {
    	Artist artist = null;
    	String error;
	
    	try {
    		artist = service.updateArtist(ARTIST_ID, EMAIL, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals(EMAIL, artist.getEmail());
    	assertEquals(PASSWORD2, artist.getPassword());
    	assertEquals(FIRSTNAME2, artist.getFirstName());
    	assertEquals(LASTNAME2, artist.getLastName());
    	assertEquals(PHONE_NUM2, artist.getPhoneNumber());
    	assertEquals(DESCRIPTION, artist.getArtistDescription());
    }
    
    @Test
    public void testUpdateInvalidID() {
    	String error = "123";
    	try {
    		service.updateArtist("32d", EMAIL, PASSWORD2, FIRSTNAME2, LASTNAME2, PHONE_NUM2, DESCRIPTION3, profilePictureURL);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	assertEquals("Username cannot be found.", error);
    }
    
    
    @Test
    public void testGetArtistByEmail() {
    	Artist artist = null;
    	String error = null;
    	try {
           artist = service.getArtistByEmail(EMAIL);
        } catch (Exception e) {
            error = e.getMessage();
        }

    	assertEquals(ARTIST_ID, artist.getUserID());
    	assertEquals(EMAIL, artist.getEmail());
    	assertEquals(PASSWORD, artist.getPassword());
    	assertEquals(FIRSTNAME, artist.getFirstName());
    	assertEquals(LASTNAME, artist.getLastName());
    	assertEquals(PHONE_NUM, artist.getPhoneNumber());
    	assertEquals(DESCRIPTION, artist.getArtistDescription());
    }
    
    @Test
    public void testGetArtistByID() {
    	Artist artist = null;
    	String error = null;
    	try {
           artist = service.getArtistByID(ARTIST_ID);
        } catch (Exception e) {
            error = e.getMessage();
        }
    	
    	assertEquals(ARTIST_ID, artist.getUserID());
    	assertEquals(EMAIL, artist.getEmail());
    	assertEquals(PASSWORD, artist.getPassword());
    	assertEquals(FIRSTNAME, artist.getFirstName());
    	assertEquals(LASTNAME, artist.getLastName());
    	assertEquals(PHONE_NUM, artist.getPhoneNumber());
    	assertEquals(DESCRIPTION, artist.getArtistDescription());
    }
    
    @Test
    public void testDeleteArtistNoUser() {
    	assertEquals(0,service.getAllArtists().size());
    	String error = "";
   	Artist artist = new Artist();
   	try {
   		artist = service.deleteArtist("wrongnID");
   	} catch (Exception e) {
   		error = e.getMessage();
    	}
   	
   	assertEquals("Username cannot be found.",error);
    }
    
}
