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
  private List<Art> artWorks;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application()
  {
    users = new ArrayList<User>();
    artWorks = new ArrayList<Art>();
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
  public Art getArtWork(int index)
  {
    Art aArtWork = artWorks.get(index);
    return aArtWork;
  }

  public List<Art> getArtWorks()
  {
    List<Art> newArtWorks = Collections.unmodifiableList(artWorks);
    return newArtWorks;
  }

  public int numberOfArtWorks()
  {
    int number = artWorks.size();
    return number;
  }

  public boolean hasArtWorks()
  {
    boolean has = artWorks.size() > 0;
    return has;
  }

  public int indexOfArtWork(Art aArtWork)
  {
    int index = artWorks.indexOf(aArtWork);
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
  public static int minimumNumberOfArtWorks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Art addArtWork(String aName, String aDescription, float aPrice, Date aDateOfCreation, boolean aIsSold, int aArtwork_id, Artist aArtist)
  {
    return new Art(aName, aDescription, aPrice, aDateOfCreation, aIsSold, aArtwork_id, aArtist, this);
  }

  public boolean addArtWork(Art aArtWork)
  {
    boolean wasAdded = false;
    if (artWorks.contains(aArtWork)) { return false; }
    Application existingApplication = aArtWork.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);
    if (isNewApplication)
    {
      aArtWork.setApplication(this);
    }
    else
    {
      artWorks.add(aArtWork);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArtWork(Art aArtWork)
  {
    boolean wasRemoved = false;
    //Unable to remove aArtWork, as it must always have a application
    if (!this.equals(aArtWork.getApplication()))
    {
      artWorks.remove(aArtWork);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addArtWorkAt(Art aArtWork, int index)
  {  
    boolean wasAdded = false;
    if(addArtWork(aArtWork))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtWorks()) { index = numberOfArtWorks() - 1; }
      artWorks.remove(aArtWork);
      artWorks.add(index, aArtWork);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArtWorkAt(Art aArtWork, int index)
  {
    boolean wasAdded = false;
    if(artWorks.contains(aArtWork))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArtWorks()) { index = numberOfArtWorks() - 1; }
      artWorks.remove(aArtWork);
      artWorks.add(index, aArtWork);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArtWorkAt(aArtWork, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(float aTotalPrice, Date aDatePlaced, Order.DeliveryMethod aDeliveryMethod, Order.OrderStatus aOrderStatus, Customer aCustomer, Artist aArtist, Art... allArtWorks)
  {
    return new Order(aTotalPrice, aDatePlaced, aDeliveryMethod, aOrderStatus, aCustomer, aArtist, this, allArtWorks);
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
    
    while (artWorks.size() > 0)
    {
      Art aArtWork = artWorks.get(artWorks.size() - 1);
      aArtWork.delete();
      artWorks.remove(aArtWork);
    }
    
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
  }

}