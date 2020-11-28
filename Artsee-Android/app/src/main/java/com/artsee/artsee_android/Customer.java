package com.artsee.artsee_android;

/**
 * Customer singleton class
 */
public class Customer {

    private static Customer customer = null;
    private final String userID;
    private final String email;
    private final String firstName;
    private final String lastName;

    /**
     * create the customer object
     * @param userID
     * @param email
     * @param firstName
     * @param lastName
     */
    private Customer(String userID, String email, String firstName, String lastName) {
        this.userID = userID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * reset customer
     */
    public static void resetCustomer(){
        customer = null;
    }

    /**
     * get userID of the customer
     * @return
     */
    public String getUserID(){
        return this.userID;
    }

    /**
     * get the customer object
     * @return
     */
    public static Customer getInstance() {
        if(customer == null) {
            throw new AssertionError("Customer needs to be intialized");
        }
        return customer;
    }

    /**
     * initialize the singleton customer
     * @param userID
     * @param email
     * @param firstName
     * @param lastName
     * @return
     */
    public synchronized static Customer initialize(String userID, String email, String firstName, String lastName) {
        if (customer != null)
        {
            throw new AssertionError("Customer already intialized");
        }
        customer = new Customer(userID, email, firstName, lastName);
        return customer;
    }

}
