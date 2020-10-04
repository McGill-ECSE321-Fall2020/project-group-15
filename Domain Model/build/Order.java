/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.sql.Date;
import java.util.*;

/**
 * buying art
 */
// line 74 "ArtGalleryApplication.ump"
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
  private List<Artwork> artworks;
  private Customer customer;
  private Application application;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(float aTotalPrice, Date aDatePlaced, DeliveryMethod aDeliveryMethod, OrderStatus aOrderStatus, Customer aCustomer, Application aApplication, Artwork... allArtworks)
  {
    totalPrice = aTotalPrice;
    datePlaced = aDatePlaced;
    orderCompleted = null;
    deliveryMethod = aDeliveryMethod;
    orderStatus = aOrderStatus;
    order_id = nextOrder_id++;
    artworks = new ArrayList<Artwork>();
    boolean didAddArtworks = setArtworks(allArtworks);
    if (!didAddArtworks)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 artworks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create order due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public Application getApplication()
  {
    return application;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArtworks()
  {
    return 1;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addArtwork(Artwork aArtwork)
  {
    boolean wasAdded = false;
    if (artworks.contains(aArtwork)) { return false; }
    Order existingOrder = aArtwork.getOrder();
    if (existingOrder != null && existingOrder.numberOfArtworks() <= minimumNumberOfArtworks())
    {
      return wasAdded;
    }
    else if (existingOrder != null)
    {
      existingOrder.artworks.remove(aArtwork);
    }
    artworks.add(aArtwork);
    setOrder(aArtwork,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArtwork(Artwork aArtwork)
  {
    boolean wasRemoved = false;
    if (artworks.contains(aArtwork) && numberOfArtworks() > minimumNumberOfArtworks())
    {
      artworks.remove(aArtwork);
      setOrder(aArtwork,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setArtworks(Artwork... newArtworks)
  {
    boolean wasSet = false;
    if (newArtworks.length < minimumNumberOfArtworks())
    {
      return wasSet;
    }

    ArrayList<Artwork> checkNewArtworks = new ArrayList<Artwork>();
    HashMap<Order,Integer> orderToNewArtworks = new HashMap<Order,Integer>();
    for (Artwork aArtwork : newArtworks)
    {
      if (checkNewArtworks.contains(aArtwork))
      {
        return wasSet;
      }
      else if (aArtwork.getOrder() != null && !this.equals(aArtwork.getOrder()))
      {
        Order existingOrder = aArtwork.getOrder();
        if (!orderToNewArtworks.containsKey(existingOrder))
        {
          orderToNewArtworks.put(existingOrder, new Integer(existingOrder.numberOfArtworks()));
        }
        Integer currentCount = orderToNewArtworks.get(existingOrder);
        int nextCount = currentCount - 1;
        if (nextCount < 1)
        {
          return wasSet;
        }
        orderToNewArtworks.put(existingOrder, new Integer(nextCount));
      }
      checkNewArtworks.add(aArtwork);
    }

    artworks.removeAll(checkNewArtworks);

    for (Artwork orphan : artworks)
    {
      setOrder(orphan, null);
    }
    artworks.clear();
    for (Artwork aArtwork : newArtworks)
    {
      if (aArtwork.getOrder() != null)
      {
        aArtwork.getOrder().artworks.remove(aArtwork);
      }
      setOrder(aArtwork, this);
      artworks.add(aArtwork);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setOrder(Artwork aArtwork, Order aOrder)
  {
    try
    {
      java.lang.reflect.Field mentorField = aArtwork.getClass().getDeclaredField("order");
      mentorField.setAccessible(true);
      mentorField.set(aArtwork, aOrder);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aOrder to aArtwork", e);
    }
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
    for(Artwork aArtwork : artworks)
    {
      setOrder(aArtwork,null);
    }
    artworks.clear();
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
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
            "  " + "application = "+(getApplication()!=null?Integer.toHexString(System.identityHashCode(getApplication())):"null");
  }
}