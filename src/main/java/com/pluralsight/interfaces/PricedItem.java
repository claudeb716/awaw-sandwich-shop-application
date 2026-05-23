package com.pluralsight.interfaces;

public interface PricedItem {
    //No Fields or Constructor:

    //Methods:
    //Every item MUST be able to CALCULATE its own PRICE.
    public default double getPrice(){
        return 0;
    }
}
