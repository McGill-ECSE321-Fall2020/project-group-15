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
public class TestArtistService {

    @Mock
    private ArtistRepository artistDao;

    @Mock
    private EndUserRepository userDao;

    @InjectMocks
    private ArtseeService service;

    private static final String ARTIST_ID = "1234";
    private static final String EMAIL = "artist@gmail.com";

    private static final String ARTIST_ID2 = "37292";
    private static final String EMAIL2 = "otherArtist@gmail.com";

    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final String DESCRIPTION = "i like to paint";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(artistDao.findByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
            } else {
                return null;
            }
        });

        

        lenient().when(artistDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ARTIST_ID)) {
                return Optional.of(TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION));
            } else {
                return Optional.empty();
            }
        });


        lenient().when(userDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ARTIST_ID)) {
                EndUser user = Mockito.mock(EndUser.class, Mockito.CALLS_REAL_METHODS);
                user = TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        });

        lenient().when(userDao.findByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(EMAIL)) {
                return TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
            } else {
                return null;
            }
        });


        lenient().when(artistDao.save(any(Artist.class))).thenAnswer((InvocationOnMock invocation) -> {
            return TestUtility.createArtist(ARTIST_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);

        });
    }
    
    @Test
    public void testCreateArtist() {
        assertEquals(0, service.getAllArtists().size());

        Artist artist = null;

        try {
            artist = service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
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
            service.createArtist("", EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a username.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(null, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
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
            service.createArtist(ARTIST_ID2, "", PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter an email.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, null, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
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
            service.createArtist(ARTIST_ID2, EMAIL2, "", FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a password.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, null, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
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
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, "", LASTNAME, PHONE_NUM, DESCRIPTION);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a first name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, null, LASTNAME, PHONE_NUM, DESCRIPTION);
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
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, FIRSTNAME, "", PHONE_NUM, DESCRIPTION);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Must enter a last name.", error);

        // check that nothing was added
        assertEquals(0, service.getAllArtists().size());

        // check again with null input
        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL2, PASSWORD, FIRSTNAME, null, PHONE_NUM, DESCRIPTION);
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
    public void testDuplicateArtist() {
        String error = null;

        try {
            service.createArtist(ARTIST_ID, EMAIL2, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Username already exists.", error);

        error = null;

        try {
            service.createArtist(ARTIST_ID2, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Email already exists.", error);
    }



    
}
