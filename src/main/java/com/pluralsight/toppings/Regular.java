package com.pluralsight.toppings;

public class Regular extends Toppings{
    //Fields
    //Constructor: created from parent class(Topping) for name of RegularToppings.
    public Regular(String name) {
        super(name);
    }
    //Overide Method:  sandwichSize as a parameter to handle class own pricing.
    @Override
    public double getPrice(String sandwichSize) {
        double regToppingPrice = 0.0; //Included
        if (sandwichSize.equalsIgnoreCase("4in") || sandwichSize.equalsIgnoreCase("8in") || sandwichSize.equalsIgnoreCase("12in")){
            regToppingPrice = 0.0;
        }
        return regToppingPrice;
    }
}
