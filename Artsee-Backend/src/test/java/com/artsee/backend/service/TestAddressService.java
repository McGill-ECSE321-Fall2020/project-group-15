package com.artsee.backend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;

import com.artsee.backend.model.*;
import com.artsee.backend.dao.*;
import com.artsee.backend.utility.*;

@ExtendWith(MockitoExtension.class)
public class TestAddressService {

    @Mock
    private AddressRepository addressDao;

    @InjectMocks
    private ArtseeService service;

    private static final int ID = 1234;

    private static final int ID2 = 1235;

    private static final String LINE1 = "5555 street";
    private static final String LINE2 = "apt b";
    private static final String CITY = "chicago";
    private static final String PROVINCE = "Manitoba";
    private static final String POSTAL_CODE = "h1x 2s4";
    private static final String COUNTRY = "Deutchland";


    @BeforeEach
    public void setMockOutput() {

    
        lenient().when(addressDao.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ID)) {
                return Optional.of(TestUtility.createAddress(ID, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY));
            } else {
                return Optional.empty();
            }
        });


        lenient().when(addressDao.save(any(Address.class))).thenAnswer((InvocationOnMock invocation) -> {
            return TestUtility.createAddress(ID, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
        });
    }


    @Test
    public void testCreateAddress() {
        

        Address a = null;

        try {
            a = service.createAddress(LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            fail(e.getMessage());
        }


        assertEquals(LINE1, a.getAddressLine1());
        assertEquals(LINE2, a.getAddressLine2());
        assertEquals(CITY, a.getCity());
        assertEquals(PROVINCE, a.getProvince());
        assertEquals(POSTAL_CODE, a.getPostalCode());
        assertEquals(COUNTRY, a.getCountry());
        
    }

    // --------------- Test incorrect inputs -----------------------
    @Test
    public void testCreateAddressMissingLine1() {
        

        String error = null;

        try {
            service.createAddress("", LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertTrue(error.contains("Address cannot be empty."));

        // check again with null input
        error = null;

        try {
            service.createAddress(null, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertTrue(error.contains("Address cannot be empty."));

    }

    @Test
    public void testCreateAddressMissingCity() {

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, "", PROVINCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("City cannot be empty.", error);

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, null, PROVINCE, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("City cannot be empty.", error);
    }

    @Test
    public void testCreateAddressMissingProvence() {
        

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, "", POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Province cannot be empty.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, null, POSTAL_CODE, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Province cannot be empty.", error);

        // check that nothing was added
        
    }

    @Test
    public void testCreateAddressMissingPostalCode() {
        

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVINCE, "", COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Postal code cannot be empty.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVINCE, null, COUNTRY);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Postal code cannot be empty.", error);

        // check that nothing was added
        
    }

    @Test
    public void testCreateAddressMissingCountry() {
        

        String error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, "");
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Country cannot be empty.", error);

        // check that nothing was added
        

        // check again with null input
        error = null;

        try {
            service.createAddress(LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, null);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Country cannot be empty.", error);

        // check that nothing was added
        
    }
    
    //------ Test the rest of the crud operations ---------------------
    @Test 
    public void testGetNonexistentAddress() {
        String error = null;

        try {
            service.getAddressById(ID2);
        } catch (Exception e) {
            error = e.getMessage();
        }

        assertEquals("Address does not exist.", error);

    }
    
    @Test
    public void testGetAddressByID() {
    	String error = null;
    	Address address = null;
    	try {
    		address = service.getAddressById(ID);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals(LINE1, address.getAddressLine1());
        assertEquals(LINE2, address.getAddressLine2());
        assertEquals(CITY, address.getCity());
        assertEquals(PROVINCE, address.getProvince());
        assertEquals(POSTAL_CODE, address.getPostalCode());
        assertEquals(COUNTRY, address.getCountry());
    }
    @Test
    public void testUpdateAddress() {
    	Address address = null;
    	String error = null;
    	
    	try {
    		address = service.updateAddress(ID, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals(LINE1, address.getAddressLine1());
    	assertEquals(LINE2, address.getAddressLine2());
    	assertEquals(CITY, address.getCity());
    	assertEquals(PROVINCE, address.getProvince());
    	assertEquals(POSTAL_CODE, address.getPostalCode());
    	assertEquals(COUNTRY, address.getCountry());
    	
    }
    
    @Test
    public void testUpdateAddressInvalidLine1() {
    	String error = null;
    	
    	try {
    		service.updateAddress(ID, "", LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals("Address cannot be empty.", error);
    }

    @Test
    public void testUpdateAddressInvalidCity() {
    	String error = null;
    	
    	try {
    		service.updateAddress(ID, LINE1, LINE2, "", PROVINCE, POSTAL_CODE, COUNTRY);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals("City cannot be empty.", error);
    }
    
    @Test
    public void testUpdateAddressInvalidProvince() {
    	String error = null;
    	
    	try {
    		service.updateAddress(ID, LINE1, LINE2, CITY, "", POSTAL_CODE, COUNTRY);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals("Province cannot be empty.", error);
    }
    
    
    @Test
    public void testUpdateAddressInvalidPostalCode() {
    	String error = null;
    	
    	try {
    		service.updateAddress(ID, LINE1, LINE2, CITY, PROVINCE, "", COUNTRY);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals("Postal code cannot be empty.", error);
    }
    
    @Test
    public void testUpdateAddressInvalidCountry() {
    	String error = null;
    	
    	try {
    		service.updateAddress(ID, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, "");
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
    	
    	assertEquals("Country cannot be empty.", error);
    }
    
    @Test
    public void testUpdateAddressInvalidID() {
    	String error = null;
	
    	try {
    		service.updateAddress(ID2, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals( "Address does not exist.", error);
    }
    
    @Test
    public void testUpdateAddressNullID() {
    	String error = null;
	
    	try {
    		service.updateAddress(null, LINE1, LINE2, CITY, PROVINCE, POSTAL_CODE, COUNTRY);
    	}catch (Exception e) {
    		error = e.getMessage();
    	}
	
    	assertEquals( "Address does not exist.", error);
    }
    
    @Test
    public void testDeleteAddressNotexistent() {
    	assertEquals(0,service.getAllAddresses().size());
    	String error = "";
   	Integer address = 42;
   	try {
   		address = service.deleteAddress(address);
   	} catch (Exception e) {
   		error = e.getMessage();
    	}
   	
   	assertEquals("Address does not exist.",error);
    }
}
