package com.pluralsight.toppings;

public class Sauces extends Toppings{
    //Fields:

    //Constructor: created from parent class(Topping) for name of Sauce.
    public Sauces(String name) {
        super(name);
    }

    //Override method:  sandwichSize as a parameter to handle class own pricing.
    @Override
    public double getPrice(String sandwichSize) {
        double saucePrice = 0.0; //Included
        if (sandwichSize.equalsIgnoreCase("4in") || sandwichSize.equalsIgnoreCase("8in") || sandwichSize.equalsIgnoreCase("12in")){
            saucePrice = 0.0;
        }
        return saucePrice;
    }

    @Override
    public String getDescription(String size) {
        return String.format("+ Sauces: %s  \n ($%.2f)",this.getName(),this.getPrice(size));    }
}
