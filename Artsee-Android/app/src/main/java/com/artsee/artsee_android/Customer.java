package com.artsee.artsee_android;

public class Customer {

    private static Customer customer = null;
    private final String userID;
    private final String email;
    private final String firstName;
    private final String lastName;

    private Customer(String userID, String email, String firstName, String lastName) {
        this.userID = userID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static void resetCustomer(){
        customer = null;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public static Customer getInstance() {
        if(customer == null) {
            throw new AssertionError("Customer needs to be intialized");
        }
        return customer;
    }

    public synchronized static Customer initialize(String userID, String email, String firstName, String lastName) {
        if (customer != null)
        {
            throw new AssertionError("Customer already intialized");
        }
        customer = new Customer(userID, email, firstName, lastName);
        return customer;
    }

}
