package com.pluralsight.toppings;

public abstract class Topping {
    //Fields:
    private String name;
    //Constructor (pass(super) to child class for them to have a name)
    public Topping(String name) {
        this.name = name;
    }
    //Getter
    public String getName() {
        return name;
    }

    // Create Override Methods:  sandwichSize as a parameter to handle subclasses own pricing and description.
    public abstract double getPrice(String sandwichSize);

    public abstract String getDescription(String size);
}