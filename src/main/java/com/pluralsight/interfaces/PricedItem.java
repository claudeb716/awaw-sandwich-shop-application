package com.pluralsight.interfaces;

public interface PricedItem {
    //No Fields or Constructor:

    //Methods:
    //Every item MUST be able to CALCULATE its own PRICE.
    public default double getPrice(){
        return 0;
    }

    //Derived Method: to get Price with passing sandwichSize (Child classes will handle their own pricing)
    double getPrice(String sandwichSize);
    //Override: Methods from Interfaces
    //PricedItem

    //Derived Method to get Price with passing sandwichSize (Child classes will handle their own pricing)
    //double getPrice(String sandwichSize);
}
