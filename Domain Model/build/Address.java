/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/



// line 4 "ArtGalleryApplication.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String provence;
  private String postalCode;
  private String country;

  //Address Associations
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aAddressLine1, String aAddressLine2, String aCity, String aProvence, String aPostalCode, String aCountry, Customer aCustomer)
  {
    addressLine1 = aAddressLine1;
    addressLine2 = aAddressLine2;
    city = aCity;
    provence = aProvence;
    postalCode = aPostalCode;
    country = aCountry;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create address due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setProvence(String aProvence)
  {
    boolean wasSet = false;
    provence = aProvence;
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

  public String getProvence()
  {
    return provence;
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
            "addressLine1" + ":" + getAddressLine1()+ "," +
            "addressLine2" + ":" + getAddressLine2()+ "," +
            "city" + ":" + getCity()+ "," +
            "provence" + ":" + getProvence()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "country" + ":" + getCountry()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}