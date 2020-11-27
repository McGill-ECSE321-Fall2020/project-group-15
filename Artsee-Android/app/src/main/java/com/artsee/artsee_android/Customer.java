package com.artsee.artsee_android;

public class Customer {

    private static Customer customer = null;
    private final int x;

    private Customer(int x) {
        this.x = x;
    }

    public static Customer getInstance() {
        if(customer == null) {
            throw new AssertionError("Customer needs to be intialized");
        }

        return customer;
    }

    public synchronized static Customer init(int x) {
        if (customer != null)
        {
            throw new AssertionError("Customer already intialized");
        }
        customer = new Customer(x);
        return customer;
    }

}
