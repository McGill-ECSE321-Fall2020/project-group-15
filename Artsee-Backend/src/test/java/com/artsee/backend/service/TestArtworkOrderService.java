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
public class TestArtworkOrderService {
	
	@Mock
	private ArtworkOrderRepository artworkOrderDao;
	
	@InjectMocks
	private ArtseeService service;
	
	public static final int ID = 1234;
	public static final DeliveryMethod DELIVERYMETHOD = DeliveryMethod.SHIP;
	public static final Customer CUSTOMER = new Customer();
	public static final List<Artwork> ARTLIST = new ArrayList<Artwork>();

	public static final String CUSTOMER_ID = "123";
	
	@BeforeEach
	public void setMockOuput() {

		CUSTOMER.setUserID(CUSTOMER_ID);
		ARTLIST.add(new Artwork());
		ARTLIST.add(new Artwork());

		ARTLIST.get(0).setArtworkID(1);
		ARTLIST.get(1).setArtworkID(2);

		
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
    
}
