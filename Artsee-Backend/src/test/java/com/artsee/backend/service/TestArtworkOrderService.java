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
	private static final int ID2 = 1235;
	private static final DeliveryMethod DELIVERYMETHOD = DeliveryMethod.SHIP;
	
	private static final Customer CUSTOMER = new Customer();
	
	
	private static final OrderStatus ORDERSTATUS = OrderStatus.PROCESSING;
	private static final String CUSTOMER_ID = "123";
	
	private static final DeliveryMethod DELIVERYMETHOD2= DeliveryMethod.PICKUP;
	
	
	private static final OrderStatus ORDERSTATUS2 = OrderStatus.DELIVERED;
	private static final Set<Artwork> ARTLIST = new HashSet<Artwork>();
	private static final Set<Artwork> ARTLIST2 = new HashSet<Artwork>();
	// Generate Artwork inputs
	private static final int ARTID = 1;
	private static final String ARTNAME1 = "masterpiece";
	private static final int ARTPRICE1 = 12;
	private static final String ARTDESC1 = "a nice work of art.";
	private static final Date DATEMADE1 = Date.valueOf("2020-10-31");
	private static final int ARTSTOCK1 = 2;
	private static final Artist ARTIST1 = new Artist();
	private static final Artwork ARTWORK1 = TestUtility.createArtwork(ARTID, ARTNAME1, ARTPRICE1, ARTDESC1, DATEMADE1, ARTSTOCK1, ARTIST1);

	
	private static final int ARTID2 = 2;
	private static final String ARTNAME2 = "late night vibes";
	private static final int ARTPRICE2 = 7;
	private static final String ARTDESC2 = "a classic piece of art";
	private static final Date DATEMADE2 = Date.valueOf("2020-11-01");
	private static final int ARTSTOCK2 = 4;
	private static final Artist ARTIST2 = new Artist();
	private static final Artwork ARTWORK2 = TestUtility.createArtwork(ARTID2, ARTNAME2, ARTPRICE2, ARTDESC2, DATEMADE2, ARTSTOCK2, ARTIST2);
	
	private static final int ARTID3 = 3;
	private static final String ARTNAME3 = "early morning sun";
	private static final int ARTPRICE3 = 6;
	private static final String ARTDESC3 = "beautiful sunrise";
	private static final Date DATEMADE3 = Date.valueOf("2020-11-01");
	private static final int ARTSTOCK3 = 0;
	private static final Artist ARTIST3 = new Artist();
	private static final Artwork ARTWORK3 = TestUtility.createArtwork(ARTID3, ARTNAME3, ARTPRICE3, ARTDESC3, DATEMADE3, ARTSTOCK3, ARTIST3);
	
	private static final int ARTID4 = 4;
	private static final String ARTNAME4 = "noon";
	private static final int ARTPRICE4 = 47;
	private static final String ARTDESC4 = "a sculpture";
	private static final Date DATEMADE4 = Date.valueOf("2020-11-01");
	private static final int ARTSTOCK4 = 50;
	private static final Artist ARTIST4 = new Artist();
	private static final Artwork ARTWORK4 = TestUtility.createArtwork(ARTID4, ARTNAME4, ARTPRICE4, ARTDESC4, DATEMADE4, ARTSTOCK4, ARTIST4);

	@BeforeEach
	public void setMockOuput() {

		CUSTOMER.setUserID(CUSTOMER_ID);
		ARTLIST.add(ARTWORK1);
		ARTLIST.add(ARTWORK2);
		ARTLIST2.add(ARTWORK3);
		
		
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
                return Optional.of(TestUtility.createArtworkOrder(ID, DELIVERYMETHOD, CUSTOMER, ARTLIST));
            } else if (invocation.getArgument(0).equals(ID)){
            	return Optional.of(TestUtility.createArtworkOrder(ID2, DELIVERYMETHOD2, CUSTOMER, ARTLIST2));
            }else {
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
            artworkOrder = service.createArtworkOrder(DELIVERYMETHOD, CUSTOMER, ARTLIST2);
            artworkOrder.setOrderID(ID);
        } catch (Exception e) {
            error = e.getMessage();
        }
      
        assertEquals(CUSTOMER, artworkOrder.getCustomer());
        assertEquals(DELIVERYMETHOD, artworkOrder.getDeliveryMethod());
        assertEquals(ARTLIST2, artworkOrder.getArtworks());
	}
	
	
	@Test
	public void testCreateArtworkOrderNoDelivery() {
		ArtworkOrder artworkOrder = null;
        String error = null;
        try {
            artworkOrder = service.createArtworkOrder(null, CUSTOMER, ARTLIST2);
           // artworkOrder.setOrderID(ID);
        } catch (Exception e) {
            error = e.getMessage();
        }
        
        assertEquals("Artwork order needs a delivery method.", error);
	}
	
	@Test
	public void testCreateArtworkOrderNoCustomer() {
		ArtworkOrder artworkOrder = null;
        String error = null;
        try {
            artworkOrder = service.createArtworkOrder(DELIVERYMETHOD, null, ARTLIST2);
           // artworkOrder.setOrderID(ID);
        } catch (Exception e) {
            error = e.getMessage();
        }
        
        assertEquals("Artwork order needs a customer.", error);
	}
	
	@Test 
	public void createArtworkOrderNoStock() {
        String error = null;
        Set<Artwork> works = new HashSet<Artwork>();
        works.add(ARTWORK3);
        try {
            service.createArtworkOrder(DELIVERYMETHOD, CUSTOMER, works);
        } catch (Exception e) {
            error = e.getMessage();
        }
        
        assertEquals("Artwork is out of stock!", error);
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
	
	
	  @Test
	    public void testGetArtworkOrderByIDWrongID() {
	        String error = null;

	        try {
	            service.getArtworkOrderByID(123543);
	        } catch (Exception e) {
	            error = e.getMessage();
	        }

	        assertThat(error, containsString("Artwork Order does not exist."));
	    }
	  
	  @Test
	  public void testUpdateArtworkOrder() {

		  ArtworkOrder artworkOrder = service.createArtworkOrder(DELIVERYMETHOD, CUSTOMER, ARTLIST);
		  int you = artworkOrder.getTotalPrice();
		  System.out.println(you);
		  Customer cust = new Customer();
		  DeliveryMethod del = DeliveryMethod.SHIP;
		  Set<Artwork> list = new HashSet<Artwork>();
		  list.add(ARTWORK1);
		  list.add(ARTWORK2);
		  
		  String error = null;
		 
		  try {
			  artworkOrder = service.updateArtworkOrder(ID, del, ORDERSTATUS2, cust, list);
		  }catch (Exception e) {
			  error = e.getMessage();
		  }
		  

		  assertEquals(cust, artworkOrder.getCustomer());
		  assertEquals(list, artworkOrder.getArtworks());
		  assertEquals(del, artworkOrder.getDeliveryMethod());
		  assertEquals(ORDERSTATUS2, artworkOrder.getOrderStatus());
		  
	  }
	  
	  @Test
	  public void testUpdateArtworkOrderNoStock() {
		  
	  }
	  @Test
	  public void testUpdateArtworkOrderNoDelivery() {
		  Set<Artwork> ARTLIST2 = new HashSet<Artwork>();
		  ArtworkOrder artworkOrder = new ArtworkOrder();
		  Customer cust = new Customer();
		  DeliveryMethod del = DeliveryMethod.SHIP;
		  
		  
		  String error = null;
		 
		  try {
			  artworkOrder = service.updateArtworkOrder(ID, null, ORDERSTATUS2, cust, ARTLIST2);
		  }catch (Exception e) {
			  error = e.getMessage();
		  }
		  
		  
		 assertEquals("Artwork order needs a delivery method.", error);
		 
	  }
	  
	  @Test
	  public void testUpdateArtworkOrderNoStatus() {
		  Set<Artwork> ARTLIST2 = new HashSet<Artwork>();
		  ArtworkOrder artworkOrder = null;
		  Customer cust = new Customer();
		  DeliveryMethod del = DeliveryMethod.SHIP;
		  
		  
		  String error = null;
		 
		  try {
			  artworkOrder = service.updateArtworkOrder(ID, del, null, cust, ARTLIST2);
		  }catch (Exception e) {
			  error = e.getMessage();
		  }
		  
		  
		 assertEquals("Artwork order needs an order status.", error);
		 
	  }
	  

	  @Test
	  public void testUpdateArtworkOrderNoCustomer() {
		  Set<Artwork> ARTLIST2 = new HashSet<Artwork>();
		  ArtworkOrder artworkOrder = null;
		  Customer cust = new Customer();
		  DeliveryMethod del = DeliveryMethod.SHIP;
		  
		  
		  String error = null;
		 
		  try {
			  artworkOrder = service.updateArtworkOrder(ID, del, ORDERSTATUS2, null, ARTLIST2);
		  }catch (Exception e) {
			  error = e.getMessage();
		  }
		  
		  
		 assertEquals("Artwork order needs a customer.", error);
		 
	  }
	  
	  @Test
	    public void testUpdateArtworkOrderNonExistent() {
	        String error = null;
	        try {
	            service.updateArtworkOrder(47,DELIVERYMETHOD, ORDERSTATUS, CUSTOMER, ARTLIST);
	        } catch (Exception e) {
	            error = e.getMessage();
	        }

	        assertEquals("Artwork Order does not exist.", error);
	    }
	  
	  @Test
	  public void testaddArtworkToOrderNoStock() {
		  String error = null;
		  try {
			  service.addArtworkToOrder(ID, ARTWORK3);
		  } catch (Exception e) {
			  error = e.getMessage();
		  }
		  
		  assertEquals("Artwork is out of stock! ",error);
	  }
	  
	  @Test
	  public void testaddArtworkToOrderNull() {
		  String error = null;
		  try {
			  service.addArtworkToOrder(ID, null);
		  } catch (Exception e) {
			  error = e.getMessage();
		  }
		  
		  assertEquals("Artwork cannot be empty!",error);
	  }
	  
	  @Test
	  public void testaddArtworkToOrder() {
		  String error = null;
		  
		  Set<Artwork> test = new HashSet<Artwork>();
		  test.add(ARTWORK1);
		  test.add(ARTWORK2);
		  test.add(ARTWORK4);
		  try { 
			  service.addArtworkToOrder(ID, ARTWORK4);
		  }catch (Exception e) {
			  error = e.getMessage();
		  }
		  
		  assertEquals(test, service.getArtworkOrderByID(ID).getArtworks());
	  }
	  
}
	
