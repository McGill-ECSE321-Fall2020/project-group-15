package com.artsee.artsee_android;

public class Customer {

    private static Customer customer = null;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;

    private Customer(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername(){
        return this.username;
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

    public synchronized static Customer initialize(String username, String email, String firstName, String lastName) {
        if (customer != null)
        {
            throw new AssertionError("Customer already intialized");
        }
        customer = new Customer(username, email, firstName, lastName);
        return customer;
    }

}
