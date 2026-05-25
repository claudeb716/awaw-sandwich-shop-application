package com.pluralsight.toppings;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;
import com.pluralsight.products.Sandwich;

public abstract class Toppings implements PricedItem, Display {
    //Fields:
    private String name;
    //Constructor (pass(super) to child class for them to have a name)
    public Toppings(String name) {
        this.name = name;
    }

    //Getter
    public String getName() {
        return name;
    }
    //Derived Method to get Price with passing sandwichSize (Child classes will handle their own pricing)
    @Override
    public double getPrice() {
        return 0.0;
    }

    //Overide Method:  sandwichSize as a parameter to handle class own pricing.
    public abstract double getPrice(String sandwichSize);

    @Override
    public String getDescription(){
        return String.format("Toppings: %s ($%.2f)",this.name);
    }
}