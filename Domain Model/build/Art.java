/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.sql.Date;
import java.util.*;

/**
 * art pieces
 */
// line 62 "ArtGalleryApplication.ump"
public class Art
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Art> artsByArtwork_id = new HashMap<Integer, Art>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Art Attributes
  private String name;
  private String description;
  private float price;
  private Date dateOfCreation;
  private boolean isSold;
  private int artwork_id;

  //Art Associations
  private Artist artist;
  private Application application;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Art(String aName, String aDescription, float aPrice, Date aDateOfCreation, boolean aIsSold, int aArtwork_id, Artist aArtist, Application aApplication)
  {
    name = aName;
    description = aDescription;
    price = aPrice;
    dateOfCreation = aDateOfCreation;
    isSold = aIsSold;
    if (!setArtwork_id(aArtwork_id))
    {
      throw new RuntimeException("Cannot create due to duplicate artwork_id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create artWork due to artist. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddApplication = setApplication(aApplication);
    if (!didAddApplication)
    {
      throw new RuntimeException("Unable to create artWork due to application. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setDateOfCreation(Date aDateOfCreation)
  {
    boolean wasSet = false;
    dateOfCreation = aDateOfCreation;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsSold(boolean aIsSold)
  {
    boolean wasSet = false;
    isSold = aIsSold;
    wasSet = true;
    return wasSet;
  }

  public boolean setArtwork_id(int aArtwork_id)
  {
    boolean wasSet = false;
    Integer anOldArtwork_id = getArtwork_id();
    if (anOldArtwork_id != null && anOldArtwork_id.equals(aArtwork_id)) {
      return true;
    }
    if (hasWithArtwork_id(aArtwork_id)) {
      return wasSet;
    }
    artwork_id = aArtwork_id;
    wasSet = true;
    if (anOldArtwork_id != null) {
      artsByArtwork_id.remove(anOldArtwork_id);
    }
    artsByArtwork_id.put(aArtwork_id, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public float getPrice()
  {
    return price;
  }

  public Date getDateOfCreation()
  {
    return dateOfCreation;
  }

  public boolean getIsSold()
  {
    return isSold;
  }

  public int getArtwork_id()
  {
    return artwork_id;
  }
  /* Code from template attribute_GetUnique */
  public static Art getWithArtwork_id(int aArtwork_id)
  {
    return artsByArtwork_id.get(aArtwork_id);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithArtwork_id(int aArtwork_id)
  {
    return getWithArtwork_id(aArtwork_id) != null;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsSold()
  {
    return isSold;
  }
  /* Code from template association_GetOne */
  public Artist getArtist()
  {
    return artist;
  }
  /* Code from template association_GetOne */
  public Application getApplication()
  {
    return application;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }

  public boolean hasOrder()
  {
    boolean has = order != null;
    return has;
  }
  /* Code from template association_SetOneToMany */
  public boolean setArtist(Artist aArtist)
  {
    boolean wasSet = false;
    if (aArtist == null)
    {
      return wasSet;
    }

    Artist existingArtist = artist;
    artist = aArtist;
    if (existingArtist != null && !existingArtist.equals(aArtist))
    {
      existingArtist.removeArtWork(this);
    }
    artist.addArtWork(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setApplication(Application aApplication)
  {
    boolean wasSet = false;
    if (aApplication == null)
    {
      return wasSet;
    }

    Application existingApplication = application;
    application = aApplication;
    if (existingApplication != null && !existingApplication.equals(aApplication))
    {
      existingApplication.removeArtWork(this);
    }
    application.addArtWork(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setOrder(Order aOrder)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    Order existingOrder = order;

    if (existingOrder == null)
    {
      if (aOrder != null)
      {
        if (aOrder.addArtWork(this))
        {
          existingOrder = aOrder;
          wasSet = true;
        }
      }
    } 
    else if (existingOrder != null)
    {
      if (aOrder == null)
      {
        if (existingOrder.minimumNumberOfArtWorks() < existingOrder.numberOfArtWorks())
        {
          existingOrder.removeArtWork(this);
          existingOrder = aOrder;  // aOrder == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingOrder.minimumNumberOfArtWorks() < existingOrder.numberOfArtWorks())
        {
          existingOrder.removeArtWork(this);
          aOrder.addArtWork(this);
          existingOrder = aOrder;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      order = existingOrder;
    }
    return wasSet;
  }
  
  public void delete()
  {
    artsByArtwork_id.remove(getArtwork_id());
    Artist placeholderArtist = artist;
    this.artist = null;
    if(placeholderArtist != null)
    {
      placeholderArtist.removeArtWork(this);
    }
    Application placeholderApplication = application;
    this.application = null;
    if(placeholderApplication != null)
    {
      placeholderApplication.removeArtWork(this);
    }
    if (order != null)
    {
      if (order.numberOfArtWorks() <= 1)
      {
        order.delete();
      }
      else
      {
        Order placeholderOrder = order;
        this.order = null;
        placeholderOrder.removeArtWork(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "," +
            "price" + ":" + getPrice()+ "," +
            "isSold" + ":" + getIsSold()+ "," +
            "artwork_id" + ":" + getArtwork_id()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dateOfCreation" + "=" + (getDateOfCreation() != null ? !getDateOfCreation().equals(this)  ? getDateOfCreation().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "application = "+(getApplication()!=null?Integer.toHexString(System.identityHashCode(getApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}