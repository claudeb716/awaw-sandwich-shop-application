package com.pluralsight.interfaces;

public interface IPricedItem {
    //No Fields or Constructor:
    //Method:
    //Every item(Sandwich,Chip,Drink) MUST be able to CALCULATE its own PRICE.
     double calculatePrice();
}
