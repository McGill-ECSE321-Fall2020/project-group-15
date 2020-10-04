/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 28 "ArtGalleryApplication.ump"
public class Artist extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Artist Attributes
  private String artistDescription;

  //Artist Associations
  private List<Review> reviews;
  private List<Artwork> artworks;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Artist(String aEmail, String aPassword, String aFirstname, String aLastname, Application aApplication)
  {
    super(aEmail, aPassword, aFirstname, aLastname, aApplication);
    artistDescription = null;
    reviews = new ArrayList<Review>();
    artworks = new ArrayList<Artwork>();
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setArtistDescription(String aArtistDescription)
  {
    boolean wasSet = false;
    artistDescription = aArtistDescription;
    wasSet = true;
    return wasSet;
  }

  public String getArtistDescription()
  {
    return artistDescription;
  }
  /* Code from template association_GetMany */
  public Review getReview(int index)
  {
    Review aReview = reviews.get(index);
    return aReview;
  }

  public List<Review> getReviews()
  {
    List<Review> newReviews = Collections.unmodifiableList(reviews);
    return newReviews;
  }

  public int numberOfReviews()
  {
    int number = reviews.size();
    return number;
  }

  public boolean hasReviews()
  {
    boolean has = reviews.size() > 0;
    return has;
  }

  public int indexOfReview(Review aReview)
  {
    int index = reviews.indexOf(aReview);
    return index;
  }
  /* Code from template association_GetMany */
  public Artwork getArtwork(int index)
  {
    Artwork aArtwork = artworks.get(index);
    return aArtwork;
  }

  public List<Artwork> getArtworks()
  {
    List<Artwork> newArtworks = Collections.unmodifiableList(artworks);
    return newArtworks;
  }

  public int numberOfArtworks()
  {
    int number = artworks.size();
    return number;
  }

  public boolean hasArtworks()
  {
    boolean has = artworks.size() > 0;
    return has;
  }

  public int indexOfArtwork(Artwork aArtwork)
  {
    int index = artworks.indexOf(aArtwork);
    return index;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReviews()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Review addReview(float aRating, String aComment, boolean aWouldReccomend, Customer aCustomer)
  {
    return new Review(aRating, aComment, aWouldReccomend, aCustomer, this);
  }

  public boolean addReview(Review aReview)
  {
    boolean wasAdded = false;
    if (reviews.contains(aReview)) { return false; }
    Artist existingArtist = aReview.getArtist();
    boolean isNewArtist = existingArtist != null && !this.equals(existingArtist);
    if (isNewArtist)
    {
      aReview.setArtist(this);
    }
    else
    {
      reviews.add(aReview);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReview(Review aReview)
  {
    boolean wasRemoved = false;
    //Unable to remove aReview, as it must always have a artist
    if (!this.equals(aReview.getArtist()))
    {
      reviews.remove(aReview);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReviewAt(Review aReview, int index)
  {  
    boolean wasAdded = false;
    if(addReview(aReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReviews()) { index = numberOfReviews() - 1; }
      reviews.remove(aReview);
      reviews.add(index, aReview);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReviewAt(Review aReview, int index)
  {
    boolean wasAdded = false;
    if(reviews.contains(aReview))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReviews()) { index = numberOfReviews() - 1; }
      reviews.remove(aReview);
      reviews.add(index, aReview);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReviewAt(aReview, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArtworks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Artwork addArtwork(String aName, String aDescription, float aPrice, Date aDateOfCreation, int aNumInStock, int aArtwork_id, Application aApplication)
  {
    return new Artwork(aName, aDescription, aPrice, aDateOfCreation, aNumInStock, aArtwork_id, this, aApplication);
  }

  public boolean addArtwork(Artwork aArtwork)
  {
    boolean wasAdded = false;
    if (artworks.contains(aArtwork)) { return false; }
    Artist existingArtist = aArtwork.getArtist();
    boolean isNewArtist = existingArtist != null && !this.equals(existingArtist);
    if (isNewArtist)
    {
      aArtwork.setArtist(this);
    }
    else
    {
      artworks.add(aArtwork);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArtwork(Artwork aArtwork)
  {
    boolean wasRemoved = false;
    //Unable to remove aArtwork, as it must always have a artist
    if (!this.equals(aArtwork.getArtist()))
    {
      artworks.remove(aArtwork);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addArtworkAt(Artwork aArtwork, int index)
  {  
    boolean wasAdded = false;
    if(addArtwork(aArtwork))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtworks()) { index = numberOfArtworks() - 1; }
      artworks.remove(aArtwork);
      artworks.add(index, aArtwork);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArtworkAt(Artwork aArtwork, int index)
  {
    boolean wasAdded = false;
    if(artworks.contains(aArtwork))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtworks()) { index = numberOfArtworks() - 1; }
      artworks.remove(aArtwork);
      artworks.add(index, aArtwork);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArtworkAt(aArtwork, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(float aTotalPrice, Date aDatePlaced, Order.DeliveryMethod aDeliveryMethod, Order.OrderStatus aOrderStatus, Customer aCustomer, Application aApplication, Artwork... allArtworks)
  {
    return new Order(aTotalPrice, aDatePlaced, aDeliveryMethod, aOrderStatus, aCustomer, this, aApplication, allArtworks);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Artist existingArtist = aOrder.getArtist();
    boolean isNewArtist = existingArtist != null && !this.equals(existingArtist);
    if (isNewArtist)
    {
      aOrder.setArtist(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a artist
    if (!this.equals(aOrder.getArtist()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (reviews.size() > 0)
    {
      Review aReview = reviews.get(reviews.size() - 1);
      aReview.delete();
      reviews.remove(aReview);
    }
    
    for(int i=artworks.size(); i > 0; i--)
    {
      Artwork aArtwork = artworks.get(i - 1);
      aArtwork.delete();
    }
    for(int i=orders.size(); i > 0; i--)
    {
      Order aOrder = orders.get(i - 1);
      aOrder.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "artistDescription" + ":" + getArtistDescription()+ "]";
  }
}