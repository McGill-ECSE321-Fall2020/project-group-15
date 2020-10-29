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
import java.util.List;
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
public class TestArtworkService {

    @Mock
    private ArtworkRepository artworkDao;

    @Mock
    private ArtistRepository artistDao;

    @InjectMocks
    private ArtseeService service;

    private static final int ID = 1234;
    private static final String USER_ID = "423";
    private static final Artist ARTIST = new Artist();
    private static final String NAME = "mona lisa";
    private static final int PRICE = 100;
    private static final String DESCRIPTION = "is very nice";
    private static final Date DATE_CREATED = Date.valueOf("2010-01-05");
    private static final int NUM_IN_STOCK = 1;

    @BeforeEach
    public void setMockOutput() {

        // Set up artist
        ARTIST.setUserID(USER_ID);

        lenient().when(artworkDao.findByArtist(ARTIST)).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ARTIST)) {
                List<Artwork> artList = new ArrayList<Artwork>();
                artList.add(
                        TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST));
                return artList;
            } else {
                return null;
            }
        });

        lenient().when(artworkDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ID)) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(artistDao.existsById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USER_ID)) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(artworkDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ID)) {
                return Optional.of(
                        TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST));
            } else {
                return Optional.empty();
            }
        });

        lenient().when(artworkDao.save(any(Artwork.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);

        });
    }

    @Test
    public void testCreateArtwork() {
        Artwork art = null;

        try {
            art = service.createArtwork(NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
            art.setArtworkID(ID);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(NAME, art.getName());
        assertEquals(PRICE, art.getPrice());
        assertEquals(DESCRIPTION, art.getDescription());
        assertEquals(DATE_CREATED.toString(), art.getDateOfCreation().toString());
        assertEquals(NUM_IN_STOCK, art.getNumInStock());
        assertEquals(ARTIST, art.getArtist());
    }

    // --------- attempt to break create artwork method ---------------
    @Test
    public void testCreateArtworkMissingName() {
        String error = null;

        try {
            service.createArtwork("", PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork name cannot be empty!", error);

        error = null;

        try {
            service.createArtwork(null, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork name cannot be empty!", error);
    }

    @Test
    public void testCreateArtworkMissingPrice() {
        String error = null;

        try {
            service.createArtwork(NAME, -1, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork price cannot be less than 0!", error);
    }

    @Test
    public void testCreateArtworkMissingDescription() {
        String error = null;

        try {
            service.createArtwork(NAME, PRICE, "", DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork description cannot be empty!", error);

        error = null;

        try {
            service.createArtwork(NAME, PRICE, null, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork description cannot be empty!", error);
    }

    @Test
    public void testCreateArtworkMissingDateCreated() {
        String error = null;

        try {
            service.createArtwork(NAME, PRICE, DESCRIPTION, Date.valueOf("2050-01-01"), NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork date of creation cannot be in the future!", error);

        error = null;

        try {
            service.createArtwork(NAME, PRICE, DESCRIPTION, null, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork date of creation cannot be empty!", error);
    }

    @Test
    public void testCreateArtworkMissingNumInStock() {
        String error = null;

        try {
            service.createArtwork(NAME, PRICE, DESCRIPTION, DATE_CREATED, -1, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Number in stock cannot be less than 0!", error);
    }

    @Test
    public void testCreateArtworkMissingArtist() {
        String error = null;

        try {
            service.createArtwork(NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artist needs to be assigned for an artwork!", error);

        error = null;

        try {
            service.createArtwork(NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, new Artist());
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artist does not exist!", error);
    }

    @Test
    public void testGetArtworkById() {
        Artwork a = null;

        try {
            a = service.getArtworkById(ID);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(NAME, a.getName());
        assertEquals(ID, a.getArtworkID());

    }

    @Test
    public void testGetArtworkByIDNonexistantArtwork() {
        String error = null;
        try {
            service.getArtworkById(4212);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork with the given Id does not exist!", error);
    }

    //------ test update method ----------------------------------------------

    @Test
    public void testUpdateArtwork() {
        Artwork art = null;
        Artwork a = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);

        try {
            art = service.updateArtwork(a, "car", 123, "i dont like art", DATE_CREATED, 24, ARTIST);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals("car", art.getName());
        assertEquals(123, art.getPrice());
        assertEquals("i dont like art", art.getDescription());
        assertEquals(DATE_CREATED.toString(), art.getDateOfCreation().toString());
        assertEquals(24, art.getNumInStock());
        assertEquals(ARTIST, art.getArtist());
    }


    @Test
    public void testUpdateArtworkMissingName() {
        String error = null;
        Artwork a = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        try {
            service.updateArtwork(a, "", PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork name cannot be empty!", error);

        error = null;

        try {
            service.updateArtwork(a, null, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork name cannot be empty!", error);
    }

    @Test
    public void testUpdateArtworkMissingPrice() {
        String error = null;
        Artwork a = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        try {
            service.updateArtwork(a, NAME, -1, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork price cannot be less than 0!", error);
    }

    @Test
    public void testUpdateArtworkMissingDescription() {
        String error = null;
        Artwork a = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        try {
            service.updateArtwork(a, NAME, PRICE, "", DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork description cannot be empty!", error);

        error = null;

        try {
            service.updateArtwork(a, NAME, PRICE, null, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork description cannot be empty!", error);
    }

    @Test
    public void testUpdateArtworkMissingDateCreated() {
        String error = null;
        Artwork a = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        try {
            service.updateArtwork(a, NAME, PRICE, DESCRIPTION, Date.valueOf("2050-01-01"), NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork date of creation cannot be in the future!", error);

        error = null;

        try {
            service.updateArtwork(a, NAME, PRICE, DESCRIPTION, null, NUM_IN_STOCK, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork date of creation cannot be empty!", error);
    }

    @Test
    public void testUpdateArtworkMissingNumInStock() {
        String error = null;
        Artwork a = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        try {
            service.updateArtwork(a, NAME, PRICE, DESCRIPTION, DATE_CREATED, -1, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Number in stock cannot be less than 0!", error);
    }

    @Test
    public void testUpdateArtworkMissingArtist() {
        String error = null;
        Artwork a = TestUtility.createArtwork(ID, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, ARTIST);
        try {
            service.updateArtwork(a, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artist needs to be assigned for an artwork!", error);

        error = null;

        try {
            service.updateArtwork(a, NAME, PRICE, DESCRIPTION, DATE_CREATED, NUM_IN_STOCK, new Artist());
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artist does not exist!", error);
    }
}