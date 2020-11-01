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
import java.util.Set;
import java.util.HashSet;
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
public class TestArtworkOrderService {
	
	@Mock
	private ArtworkOrderRepository artworkOrderDao;
	
	@InjectMocks
	private ArtseeService service;
	
	private static final int ID = 1234;
	private static final DeliveryMethod DELIVERYMETHOD = DeliveryMethod.SHIP;
	private static final Customer CUSTOMER = new Customer();
	private static final Set<Artwork> ARTLIST = new HashSet<Artwork>();

	private static final String CUSTOMER_ID = "123";
	
	/*
	private static final String EMAIL = "customer@gmail.com";
	private static final String PASSWORD = "password";
	private static final String FIRSTNAME = "John";
	private static final String LASTNAME = "Doe";
	private static final String PHONE_NUM = "8675309";
	private static final Address ADDRESS = new Address();
	
	private static final Customer CUSTOMER = TestUtility.createCustomer(CUSTOMER_ID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, PHONE_NUM, ADDRESS);
	/*
	private static final int ARTID = 12;
	private static final String ARTNAME1 = "masterpiece";
	private static final int ARTPRICE1 = 12;
	private static final String ARTDESC1 = "a nice work of art.";
	private static final Date DATEMADE1 = Date.valueOf("2020-10-31");
	private static final int ARTSTOCK1 = 2;
	private static final Artist ARTIST1 = new Artist();
	private static final Artwork ARTWORK1 = TestUtility.createArtwork(ARTID, ARTNAME1, ARTPRICE1, ARTDESC1, DATEMADE1, ARTSTOCK1, ARTIST1);
	*/
	
	
	@BeforeEach
	public void setMockOuput() {

		CUSTOMER.setUserID(CUSTOMER_ID);
		ARTLIST.add(new Artwork());
		ARTLIST.add(new Artwork());

		//ARTLIST.get(0).setArtworkID(1);
		//ARTLIST.get(1).setArtworkID(2);

		
		lenient().when(artworkOrderDao.findByCustomer(CUSTOMER)).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CUSTOMER)) {
                List<ArtworkOrder> artOrderList = new ArrayList<ArtworkOrder>();
                artOrderList.add(TestUtility.createArtworkOrder(ID, DELIVERYMETHOD, CUSTOMER, ARTLIST));
                return artOrderList;
            } else {
                return null;
            }
		});
		
		lenient().when(artworkOrderDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ID)) {
                return Optional.of(
					TestUtility.createArtworkOrder(ID, DELIVERYMETHOD, CUSTOMER, ARTLIST));
            } else {
                return Optional.empty();
            }
        });
		
		lenient().when(artworkOrderDao.save(any(ArtworkOrder.class))).thenAnswer((InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		});

    }
    
	@Test
    public void testCreateArtworkOrder() {
        ArtworkOrder artworkOrder = null;
        String error = null;
        try {
            artworkOrder = service.createArtworkOrder(DELIVERYMETHOD, CUSTOMER, ARTLIST);
            artworkOrder.setOrderID(ID);
        } catch (Exception e) {
            error = e.getMessage();
        }
        System.out.println(error);
        assertEquals(1,0,String.valueOf(artworkOrder));
        assertEquals(CUSTOMER, artworkOrder.getCustomer());
        assertEquals(DELIVERYMETHOD, artworkOrder.getDeliveryMethod());
        assertEquals(ARTLIST, artworkOrder.getArtworks());
	}
	
	@Test
    public void testGetArtworkOrderByID() {
        ArtworkOrder artworkOrder = null;
        try {
            artworkOrder = service.getArtworkOrderByID(ID);
        } catch (Exception e) {
            fail(e.getMessage());
        }

      
        assertEquals(CUSTOMER,artworkOrder.getCustomer());
        assertEquals(DELIVERYMETHOD, artworkOrder.getDeliveryMethod());
        assertEquals(ARTLIST,artworkOrder.getArtworks());

    }
	
}
	
