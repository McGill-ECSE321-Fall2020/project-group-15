/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.util.*;

// line 39 "ArtGalleryApplication.ump"
public class Administrator extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(String aEmail, String aPassword, Application aApplication, String aName)
  {
    super(aEmail, aPassword, aApplication);
    name = aName;
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

  public String getName()
  {
    return name;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}