/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 1 "ArtGalleryApplication.ump"
public class Application
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Associations
  private List<User> users;
  private List<Artwork> artworks;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application()
  {
    users = new ArrayList<User>();
    artworks = new ArrayList<Artwork>();
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
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
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    Application existingApplication = aUser.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);
    if (isNewApplication)
    {
      aUser.setApplication(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a application
    if (!this.equals(aUser.getApplication()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArtworks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Artwork addArtwork(String aName, String aDescription, float aPrice, Date aDateOfCreation, int aNumInStock, int aArtwork_id, Artist aArtist)
  {
    return new Artwork(aName, aDescription, aPrice, aDateOfCreation, aNumInStock, aArtwork_id, aArtist, this);
  }

  public boolean addArtwork(Artwork aArtwork)
  {
    boolean wasAdded = false;
    if (artworks.contains(aArtwork)) { return false; }
    Application existingApplication = aArtwork.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);
    if (isNewApplication)
    {
      aArtwork.setApplication(this);
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
    //Unable to remove aArtwork, as it must always have a application
    if (!this.equals(aArtwork.getApplication()))
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
  public Order addOrder(float aTotalPrice, Date aDatePlaced, Order.DeliveryMethod aDeliveryMethod, Order.OrderStatus aOrderStatus, Customer aCustomer, Artist aArtist, Artwork... allArtworks)
  {
    return new Order(aTotalPrice, aDatePlaced, aDeliveryMethod, aOrderStatus, aCustomer, aArtist, this, allArtworks);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Application existingApplication = aOrder.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);
    if (isNewApplication)
    {
      aOrder.setApplication(this);
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
    //Unable to remove aOrder, as it must always have a application
    if (!this.equals(aOrder.getApplication()))
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
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (artworks.size() > 0)
    {
      Artwork aArtwork = artworks.get(artworks.size() - 1);
      aArtwork.delete();
      artworks.remove(aArtwork);
    }
    
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
  }

}