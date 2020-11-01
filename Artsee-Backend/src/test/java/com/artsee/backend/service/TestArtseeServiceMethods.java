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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.yaml.snakeyaml.tokens.Token.ID;

import com.artsee.backend.model.*;
import com.artsee.backend.dao.*;
import com.artsee.backend.utility.*;


@ExtendWith(MockitoExtension.class)
public class TestArtseeServiceMethods {

    @Mock
    private EndUserRepository userDao;

    @InjectMocks
    private ArtseeService service;

    private static final String ID = "1234";
    private static final String EMAIL = "artist@gmail.com";

    private static final String ID2 = "37292";
    private static final String EMAIL2 = "otherArtist@gmail.com";

    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "John";
    private static final String LASTNAME = "Doe";
    private static final String PHONE_NUM = "8675309";
    private static final String DESCRIPTION = "i like to paint";

    @BeforeEach
    public void setMockOutput() {

        lenient().when(userDao.findById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ID)) {
                EndUser user = Mockito.mock(EndUser.class, Mockito.CALLS_REAL_METHODS);
                user = TestUtility.createArtist(ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, DESCRIPTION);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        });
    }

    // ------ Test login method -----------
    @Test
    public void testSignInMethod() {
        Artist artist = null;

        try {
            artist = (Artist) service.signIn(ID, PASSWORD);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(ID, artist.getUserID());
        assertEquals(EMAIL, artist.getEmail());
        assertEquals(PASSWORD, artist.getPassword());
        assertEquals(FIRSTNAME, artist.getFirstName());
        assertEquals(LASTNAME, artist.getLastName());
        assertEquals(PHONE_NUM, artist.getPhoneNumber());
        assertEquals(DESCRIPTION, artist.getArtistDescription());

    }

    @Test
    public void testSignInBadInputs() {
        String error = null;

        try {
            service.signIn(ID, "password1");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Password is incorrect.", error);

        error = null;

        try {
            service.signIn("67890", PASSWORD);
        } catch (Exception e) {
            error = e.getMessage();
        }


        assertEquals("Username cannot be found.", error);
    }
}