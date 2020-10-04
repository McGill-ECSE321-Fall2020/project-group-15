/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/



/**
 * review
 */
// line 45 "ArtGalleryApplication.ump"
public class Review
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextReview_id = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Review Attributes
  private float rating;
  private String comment;
  private boolean wouldReccomend;

  //Autounique Attributes
  private int review_id;

  //Review Associations
  private Customer customer;
  private Artist artist;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Review(float aRating, String aComment, boolean aWouldReccomend, Customer aCustomer, Artist aArtist)
  {
    rating = aRating;
    comment = aComment;
    wouldReccomend = aWouldReccomend;
    review_id = nextReview_id++;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create review due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddArtist = setArtist(aArtist);
    if (!didAddArtist)
    {
      throw new RuntimeException("Unable to create review due to artist. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRating(float aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setComment(String aComment)
  {
    boolean wasSet = false;
    comment = aComment;
    wasSet = true;
    return wasSet;
  }

  public boolean setWouldReccomend(boolean aWouldReccomend)
  {
    boolean wasSet = false;
    wouldReccomend = aWouldReccomend;
    wasSet = true;
    return wasSet;
  }

  public float getRating()
  {
    return rating;
  }

  public String getComment()
  {
    return comment;
  }

  public boolean getWouldReccomend()
  {
    return wouldReccomend;
  }

  public int getReview_id()
  {
    return review_id;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isWouldReccomend()
  {
    return wouldReccomend;
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
      existingCustomer.removeReview(this);
    }
    customer.addReview(this);
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
      existingArtist.removeReview(this);
    }
    artist.addReview(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeReview(this);
    }
    Artist placeholderArtist = artist;
    this.artist = null;
    if(placeholderArtist != null)
    {
      placeholderArtist.removeReview(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "review_id" + ":" + getReview_id()+ "," +
            "rating" + ":" + getRating()+ "," +
            "comment" + ":" + getComment()+ "," +
            "wouldReccomend" + ":" + getWouldReccomend()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "artist = "+(getArtist()!=null?Integer.toHexString(System.identityHashCode(getArtist())):"null");
  }
}