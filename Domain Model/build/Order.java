/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.sql.Date;
import java.util.*;

/**
 * buying art
 */
// line 78 "ArtGalleryApplication.ump"
public class Order
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DeliveryMethod { Pickup, Delivery }
  public enum OrderStatus { PlacedOrder, ShippedOrder, ReadyForPickup, DeliveredOrder, OrderPickedup }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextOrder_id = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private float totalPrice;
  private Date datePlaced;
  private Date orderCompleted;
  private DeliveryMethod deliveryMethod;
  private OrderStatus orderStatus;

  //Autounique Attributes
  private int order_id;

  //Order Associations
  private List<Art> artWorks;
  private Customer customer;
  private Artist artist;
  private Application application;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(float aTotalPrice, Date aDatePlaced, DeliveryMethod aDeliveryMethod, OrderStatus aOrderStatus, Customer aCustomer, Artist aArtist, Application aApplication, Art... allArtWorks)
  {
    totalPrice = aTotalPrice;
    datePlaced = aDatePlaced;
    orderCompleted = null;
    deliveryMethod = aDeliveryMethod;
    orderStatus = aOrderStatus;
    order_id = nextOrder_id++;
    artWorks = new ArrayList<Art>();
    boolean didAddArtWorks = setArtWorks(allArtWorks);
    if (!didAddArtWorks)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 artWorks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create order due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create order due to artist. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddApplication = setApplication(aApplication);
    if (!didAddApplication)
    {
      throw new RuntimeException("Unable to create order due to application. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalPrice(float aTotalPrice)
  {
    boolean wasSet = false;
    totalPrice = aTotalPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setDatePlaced(Date aDatePlaced)
  {
    boolean wasSet = false;
    datePlaced = aDatePlaced;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderCompleted(Date aOrderCompleted)
  {
    boolean wasSet = false;
    orderCompleted = aOrderCompleted;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeliveryMethod(DeliveryMethod aDeliveryMethod)
  {
    boolean wasSet = false;
    deliveryMethod = aDeliveryMethod;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderStatus(OrderStatus aOrderStatus)
  {
    boolean wasSet = false;
    orderStatus = aOrderStatus;
    wasSet = true;
    return wasSet;
  }

  public float getTotalPrice()
  {
    return totalPrice;
  }

  public Date getDatePlaced()
  {
    return datePlaced;
  }

  public Date getOrderCompleted()
  {
    return orderCompleted;
  }

  public DeliveryMethod getDeliveryMethod()
  {
    return deliveryMethod;
  }

  public OrderStatus getOrderStatus()
  {
    return orderStatus;
  }

  public int getOrder_id()
  {
    return order_id;
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
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArtWorks()
  {
    return 1;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addArtWork(Art aArtWork)
  {
    boolean wasAdded = false;
    if (artWorks.contains(aArtWork)) { return false; }
    Order existingOrder = aArtWork.getOrder();
    if (existingOrder != null && existingOrder.numberOfArtWorks() <= minimumNumberOfArtWorks())
    {
      return wasAdded;
    }
    else if (existingOrder != null)
    {
      existingOrder.artWorks.remove(aArtWork);
    }
    artWorks.add(aArtWork);
    setOrder(aArtWork,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArtWork(Art aArtWork)
  {
    boolean wasRemoved = false;
    if (artWorks.contains(aArtWork) && numberOfArtWorks() > minimumNumberOfArtWorks())
    {
      artWorks.remove(aArtWork);
      setOrder(aArtWork,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setArtWorks(Art... newArtWorks)
  {
    boolean wasSet = false;
    if (newArtWorks.length < minimumNumberOfArtWorks())
    {
      return wasSet;
    }

    ArrayList<Art> checkNewArtWorks = new ArrayList<Art>();
    HashMap<Order,Integer> orderToNewArtWorks = new HashMap<Order,Integer>();
    for (Art aArtWork : newArtWorks)
    {
      if (checkNewArtWorks.contains(aArtWork))
      {
        return wasSet;
      }
      else if (aArtWork.getOrder() != null && !this.equals(aArtWork.getOrder()))
      {
        Order existingOrder = aArtWork.getOrder();
        if (!orderToNewArtWorks.containsKey(existingOrder))
        {
          orderToNewArtWorks.put(existingOrder, new Integer(existingOrder.numberOfArtWorks()));
        }
        Integer currentCount = orderToNewArtWorks.get(existingOrder);
        int nextCount = currentCount - 1;
        if (nextCount < 1)
        {
          return wasSet;
        }
        orderToNewArtWorks.put(existingOrder, new Integer(nextCount));
      }
      checkNewArtWorks.add(aArtWork);
    }

    artWorks.removeAll(checkNewArtWorks);

    for (Art orphan : artWorks)
    {
      setOrder(orphan, null);
    }
    artWorks.clear();
    for (Art aArtWork : newArtWorks)
    {
      if (aArtWork.getOrder() != null)
      {
        aArtWork.getOrder().artWorks.remove(aArtWork);
      }
      setOrder(aArtWork, this);
      artWorks.add(aArtWork);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setOrder(Art aArtWork, Order aOrder)
  {
    try
    {
      java.lang.reflect.Field mentorField = aArtWork.getClass().getDeclaredField("order");
      mentorField.setAccessible(true);
      mentorField.set(aArtWork, aOrder);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aOrder to aArtWork", e);
    }
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
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeOrder(this);
    }
    customer.addOrder(this);
    wasSet = true;
    return wasSet;
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
      existingArtist.removeOrder(this);
    }
    artist.addOrder(this);
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
      existingApplication.removeOrder(this);
    }
    application.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(Art aArtWork : artWorks)
    {
      setOrder(aArtWork,null);
    }
    artWorks.clear();
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
    }
    Artist placeholderArtist = artist;
    this.artist = null;
    if(placeholderArtist != null)
    {
      placeholderArtist.removeOrder(this);
    }
    Application placeholderApplication = application;
    this.application = null;
    if(placeholderApplication != null)
    {
      placeholderApplication.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "order_id" + ":" + getOrder_id()+ "," +
            "totalPrice" + ":" + getTotalPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "datePlaced" + "=" + (getDatePlaced() != null ? !getDatePlaced().equals(this)  ? getDatePlaced().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderCompleted" + "=" + (getOrderCompleted() != null ? !getOrderCompleted().equals(this)  ? getOrderCompleted().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "deliveryMethod" + "=" + (getDeliveryMethod() != null ? !getDeliveryMethod().equals(this)  ? getDeliveryMethod().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderStatus" + "=" + (getOrderStatus() != null ? !getOrderStatus().equals(this)  ? getOrderStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "application = "+(getApplication()!=null?Integer.toHexString(System.identityHashCode(getApplication())):"null");
  }
}