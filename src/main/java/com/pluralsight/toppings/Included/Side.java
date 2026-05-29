package com.pluralsight.toppings.Included;

import com.pluralsight.toppings.Topping;

public class Side extends Topping {
    //Field:

    //Constructor: created from parent class(Topping) for name of Side.
    public Side(String name) {
        super(name);
    }

    //Override method:  sandwichSize as a parameter to handle class own pricing.

    @Override
    public double getPrice(String sandwichSize) {
        double sidePrice = 0.0; //Included
        if (sandwichSize.equalsIgnoreCase("4in") || sandwichSize.equalsIgnoreCase("8in") || sandwichSize.equalsIgnoreCase("12in")){
            sidePrice = 0.0;
        }
        return sidePrice;
    }

    @Override
    public String getDescription(String size) {
        return String.format("+ Side: %s  \n ($%.2f)",this.getName(),this.getPrice(size));
    }
}
