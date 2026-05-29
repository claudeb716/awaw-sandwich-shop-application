package com.pluralsight.toppings.Included;

import com.pluralsight.toppings.Topping;

public class Sauce extends Topping {
    //Fields:

    //Constructor: created from parent class(Topping) for name of Sauce.
    public Sauce(String name) {
        super(name);
    }

    //Override method:  sandwichSize as a parameter to handle class own pricing.
    @Override
    public double getPrice(String sandwichSize) {
        //Included
        return 0.0;
    }

    @Override
    public String getDescription(String size) {
        return String.format("+ Sauces: %s  \n ($%.2f)",this.getName(),this.getPrice(size));    }
}
