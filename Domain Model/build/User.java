/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4607.2d2b84eb8 modeling language!*/


import java.util.*;

// line 18 "ArtGalleryApplication.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();
  private static int nextUser_id = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String password;
  private String firstname;
  private String lastname;
  private String phoneNumber;

  //Autounique Attributes
  private int user_id;

  //User Associations
  private Application application;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aPassword, String aFirstname, String aLastname, Application aApplication)
  {
    password = aPassword;
    firstname = aFirstname;
    lastname = aLastname;
    phoneNumber = null;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    user_id = nextUser_id++;
    boolean didAddApplication = setApplication(aApplication);
    if (!didAddApplication)
    {
      throw new RuntimeException("Unable to create user due to application. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      usersByEmail.remove(anOldEmail);
    }
    usersByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setFirstname(String aFirstname)
  {
    boolean wasSet = false;
    firstname = aFirstname;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastname(String aLastname)
  {
    boolean wasSet = false;
    lastname = aLastname;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public String getFirstname()
  {
    return firstname;
  }

  public String getLastname()
  {
    return lastname;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public int getUser_id()
  {
    return user_id;
  }
  /* Code from template association_GetOne */
  public Application getApplication()
  {
    return application;
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
      existingApplication.removeUser(this);
    }
    application.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByEmail.remove(getEmail());
    Application placeholderApplication = application;
    this.application = null;
    if(placeholderApplication != null)
    {
      placeholderApplication.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "user_id" + ":" + getUser_id()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "firstname" + ":" + getFirstname()+ "," +
            "lastname" + ":" + getLastname()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "application = "+(getApplication()!=null?Integer.toHexString(System.identityHashCode(getApplication())):"null");
  }
}