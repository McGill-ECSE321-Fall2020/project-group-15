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
import java.util.List;

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
public class TestReviewService {
	
	@Mock
	private ReviewRepository reviewDao;
	
	@InjectMocks
	private ArtseeService service;
	
	private static final Integer ID = 1234;
	private static final Integer RATING = 4;
	private static final String COMMENT = "A nice artwork";
	private static final Boolean WOULDRECCOMEND = true;
	private static final Customer CUSTOMER = new Customer();
	private static final Artist ARTIST = new Artist();
	private static final String ARTIST_ID = "1234";
	private static final String CUSTOMER_ID = "3245";
	
	
	@BeforeEach
	public void setMockOuput() {
		ARTIST.setUserID(ARTIST_ID);
		CUSTOMER.setUserID(CUSTOMER_ID);


		lenient().when(reviewDao.findByArtist(ARTIST)).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ARTIST)) {
                List<Review> reviewList = new ArrayList<Review>();
                reviewList.add(TestUtility.createReview(ID, RATING, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST));
                return reviewList;
            } else {
                return null;
            }
		});
		
		lenient().when(reviewDao.findByCustomer(CUSTOMER)).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CUSTOMER)) {
                List<Review> reviewList = new ArrayList<Review>();
                reviewList.add(TestUtility.createReview(ID, RATING, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST));
                return reviewList;
            } else {
                return null;
            }
		});
		
		lenient().when(reviewDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ID)) {
                return Optional.of(
					TestUtility.createReview(ID, RATING, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST));
            } else {
                return Optional.empty();
            }
        });
		
		lenient().when(reviewDao.save(any(Review.class))).thenAnswer((InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		});

	}

	@Test
    public void testCreateArtwork() {
        Review review = null;

        try {
            review = service.createReview(RATING, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST);
            review.setReviewID(ID);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(RATING, review.getRating());
        assertEquals(COMMENT, review.getComment());
        assertEquals(WOULDRECCOMEND, review.getWouldRecommend());
        assertEquals(CUSTOMER, review.getCustomer());
        assertEquals(ARTIST, review.getArtist());
	}
	

	@Test
    public void testCreateArtworkMissingName() {
        String error = null;

        try {
            service.createReview(-1, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork name cannot be empty!", error);

        error = null;

        try {
            service.createReview(null, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Artwork name cannot be empty!", error);
    }
}
