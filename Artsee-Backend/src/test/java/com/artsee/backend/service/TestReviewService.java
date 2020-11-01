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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

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
    
    private static final Integer RATING2 = 2;
    private static final String COMMENT2 = "A nice artwork";
	private static final Boolean WOULDRECCOMEND2 = true;
	private static final Customer CUSTOMER2 = new Customer(); 
	private static final Artist ARTIST2 = new Artist();
	private static final String ARTIST_ID2 = "132234";
	private static final String CUSTOMER_ID2 = "334245";
	
	
	@BeforeEach
	public void setMockOuput() {
		ARTIST.setUserID(ARTIST_ID);
        CUSTOMER.setUserID(CUSTOMER_ID);
        
        ARTIST2.setUserID(ARTIST_ID2);
        CUSTOMER2.setUserID(CUSTOMER_ID2);


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
    public void testCreateReview() {
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
    public void testCreateReviewMissingRating() {
        String error = null;

        try {
            service.createReview(-1, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Rating should be between 0 and 5"));

        error = null;

        try {
            service.createReview(null, COMMENT, WOULDRECCOMEND, CUSTOMER, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review needs a rating."));
    }
	
    @Test
    public void testCreateReviewMissingCustomer() {
        String error = null;

        try {
            service.createReview(RATING, COMMENT, WOULDRECCOMEND, null, ARTIST);
        } catch (Exception e) {
            error = e.getMessage();
        } 

        assertThat(error, containsString("Review needs a customer."));
    } 

    @Test
    public void testCreateReviewMissingArtist() {
        String error = null;

        try {
            service.createReview(RATING, COMMENT, WOULDRECCOMEND, CUSTOMER, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review needs an artist."));
    }

    // -------------- other tests ------------------
    @Test
    public void testGetReviewByID() {
        Review review = null;
        try {
            review = service.getReviewbyID(ID);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(RATING, review.getRating());
        assertEquals(COMMENT, review.getComment());
        assertEquals(WOULDRECCOMEND, review.getWouldRecommend());
        assertEquals(CUSTOMER, review.getCustomer());
        assertEquals(ARTIST, review.getArtist());
        assertEquals(ID, review.getReviewID());

    }

    @Test
    public void testGetReviewByIDWrongID() {
        String error = null;

        try {
            service.getReviewbyID(123543);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review does not exist."));
    }

    @Test
    public void testGetAllReviewsOnArtist() {

        try {
           List<Review> r = service.getAllReviewsOnArtist(ARTIST);
            assertEquals(1, r.size());
            assertEquals(ID, r.get(0).getReviewID());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetAllReviewsOnCustomer() {

        try {
           List<Review> r = service.getAllReviewsByCustomer(CUSTOMER);
            assertEquals(1, r.size());
            assertEquals(ID, r.get(0).getReviewID());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    // ------------- Update method -------------------------------
    @Test
    public void testUpdateArtwork() {
        Review review = null;

        try {
            review = service.updateReview(ID, RATING2, COMMENT2, WOULDRECCOMEND2, CUSTOMER2, ARTIST2);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(RATING2, review.getRating());
        assertEquals(COMMENT2, review.getComment());
        assertEquals(WOULDRECCOMEND2, review.getWouldRecommend());
        assertEquals(CUSTOMER2, review.getCustomer());
        assertEquals(ARTIST2, review.getArtist());
	}
	

	@Test
    public void testUpdateReviewMissingID() {
        String error = null;

        try {
            service.updateReview(876543, RATING2, COMMENT2, WOULDRECCOMEND2, CUSTOMER2, ARTIST2);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review does not exist."));

        error = null;

        try {
            service.updateReview(null, RATING2, COMMENT2, WOULDRECCOMEND2, CUSTOMER2, ARTIST2);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review does not exist."));
    }

    @Test
    public void testUpdateReviewMissingRating() {
        String error = null;

        try {
            service.updateReview(ID, -1, COMMENT2, WOULDRECCOMEND2, CUSTOMER2, ARTIST2);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Rating should be between 0 and 5"));

        error = null;

        try {
            service.updateReview(ID, null, COMMENT2, WOULDRECCOMEND2, CUSTOMER2, ARTIST2);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review needs a rating."));
    }

    @Test
    public void testUpdateReviewMissingCustomer() {
        String error = null;

        try {
            service.updateReview(ID, RATING2, COMMENT2, WOULDRECCOMEND2, null, ARTIST2);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review needs a customer."));
    }

    @Test
    public void testUpdateReviewMissingArtist() {
        String error = null;

        try {
            service.updateReview(ID, RATING2, COMMENT2, WOULDRECCOMEND2, CUSTOMER2, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertThat(error, containsString("Review needs an artist."));
    }
}
