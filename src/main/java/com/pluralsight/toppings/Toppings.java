package com.pluralsight.toppings;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;
import com.pluralsight.products.Sandwich;

public abstract class Toppings implements Display {
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

    //Overide Method:  sandwichSize as a parameter to handle class own pricing.
    public abstract double getPrice(String sandwichSize);

    @Override
    public String getDescription(String size){
        return String.format("Toppings: %s ",this.name);
    }
}