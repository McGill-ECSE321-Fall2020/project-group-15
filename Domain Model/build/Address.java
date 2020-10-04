/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.util.*;

// line 4 "ArtGalleryApplication.ump"
public class Address
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Address> addresssByAddress_id = new HashMap<String, Address>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String address_id;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String province;
  private String postalCode;
  private String country;

  //Address Associations
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aAddress_id, String aAddressLine1, String aAddressLine2, String aCity, String aProvince, String aPostalCode, String aCountry, Customer aCustomer)
  {
    addressLine1 = aAddressLine1;
    addressLine2 = aAddressLine2;
    city = aCity;
    province = aProvince;
    postalCode = aPostalCode;
    country = aCountry;
    if (!setAddress_id(aAddress_id))
    {
      throw new RuntimeException("Cannot create due to duplicate address_id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create address due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress_id(String aAddress_id)
  {
    boolean wasSet = false;
    String anOldAddress_id = getAddress_id();
    if (anOldAddress_id != null && anOldAddress_id.equals(aAddress_id)) {
      return true;
    }
    if (hasWithAddress_id(aAddress_id)) {
      return wasSet;
    }
    address_id = aAddress_id;
    wasSet = true;
    if (anOldAddress_id != null) {
      addresssByAddress_id.remove(anOldAddress_id);
    }
    addresssByAddress_id.put(aAddress_id, this);
    return wasSet;
  }

  public boolean setAddressLine1(String aAddressLine1)
  {
    boolean wasSet = false;
    addressLine1 = aAddressLine1;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddressLine2(String aAddressLine2)
  {
    boolean wasSet = false;
    addressLine2 = aAddressLine2;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setProvince(String aProvince)
  {
    boolean wasSet = false;
    province = aProvince;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setCountry(String aCountry)
  {
    boolean wasSet = false;
    country = aCountry;
    wasSet = true;
    return wasSet;
  }

  public String getAddress_id()
  {
    return address_id;
  }
  /* Code from template attribute_GetUnique */
  public static Address getWithAddress_id(String aAddress_id)
  {
    return addresssByAddress_id.get(aAddress_id);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithAddress_id(String aAddress_id)
  {
    return getWithAddress_id(aAddress_id) != null;
  }

  public String getAddressLine1()
  {
    return addressLine1;
  }

  public String getAddressLine2()
  {
    return addressLine2;
  }

  public String getCity()
  {
    return city;
  }

  public String getProvince()
  {
    return province;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public String getCountry()
  {
    return country;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer == null)
    {
      //Unable to setCustomer to null, as address must always be associated to a customer
      return wasSet;
    }
    
    Address existingAddress = aNewCustomer.getAddress();
    if (existingAddress != null && !equals(existingAddress))
    {
      //Unable to setCustomer, the current customer already has a address, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Customer anOldCustomer = customer;
    customer = aNewCustomer;
    customer.setAddress(this);

    if (anOldCustomer != null)
    {
      anOldCustomer.setAddress(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    addresssByAddress_id.remove(getAddress_id());
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.setAddress(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "address_id" + ":" + getAddress_id()+ "," +
            "addressLine1" + ":" + getAddressLine1()+ "," +
            "addressLine2" + ":" + getAddressLine2()+ "," +
            "city" + ":" + getCity()+ "," +
            "province" + ":" + getProvince()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "country" + ":" + getCountry()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}