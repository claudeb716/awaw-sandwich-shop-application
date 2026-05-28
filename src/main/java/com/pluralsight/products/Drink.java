package com.pluralsight.products;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;

public class Drink implements PricedItem, Display {
    //Fields:
    private String drinkSize;
    private String drinkFlavor;
    //Constructor:
    public Drink(String drinkSize, String drinkFlavor) {
        this.drinkSize = drinkSize;
        this.drinkFlavor = drinkFlavor;
    }
    //Override: Methods from Interfaces
    //PricedItem
    @Override
    public double calculatePrice() {
        double drinkPrice = 0.0;
        // check drinkSize matches String size return drinkPrice based on size
        if (this.drinkSize.equalsIgnoreCase("Small")){
                drinkPrice = 2.00;
        } else if (this.drinkSize.equalsIgnoreCase("Medium")) {
                drinkPrice = 2.50;
        } else if (this.drinkSize.equalsIgnoreCase("Large")) {
                drinkPrice = 3.00;
        }
        return drinkPrice;
    }
    //Display
    @Override
    public String getDescription(String size) {
        return String.format("Drink: %s %s ($%.2f)", this.drinkSize, this.drinkFlavor, this.calculatePrice());
    }
}
