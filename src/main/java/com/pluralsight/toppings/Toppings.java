package com.pluralsight.toppings;

public abstract class Toppings {
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
    public static double getPrice(String sandwichSize){
        return 0;
    }
}
