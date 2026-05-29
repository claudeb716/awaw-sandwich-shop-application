package com.pluralsight.toppings.upcharge;

import com.pluralsight.toppings.Premium;

public class Cheese extends Premium {
    //Fields:

    //Constructor: created from parent class(Premium) for name of Cheese and if they want extra?
    public Cheese(String name, boolean hasExtra) {
        super(name, hasExtra);
    }

    //Override method:  sandwichSize as a parameter to handle class own pricing. and check if hasExtra Cheese
    @Override
    public double getPrice(String sandwichSize) {
        double cheesePrice;
        // Check the sandwichSize and whether there’s an extra topping to determine the price of the Cheese and add the additional cost.
        switch (sandwichSize){
            case"4in" -> cheesePrice = isHasExtra() ? 1.05 : .75;
            case"8in" -> cheesePrice = isHasExtra() ? 2.10 : 1.50;
            case"12in" -> cheesePrice = isHasExtra() ? 3.15 : 2.25;
            default -> cheesePrice = 0.0;
        }

//        if (sandwichSize.equalsIgnoreCase("4in")){
//            cheesePrice += isHasExtra() ? 1.05 : 0.75; //Ternary Operator (?:) if yes for extra/ if no for extra
//        }else if (sandwichSize.equalsIgnoreCase("8in")){
//            cheesePrice +=  isHasExtra() ? 2.10 : 1.50;
//        }else if (sandwichSize.equalsIgnoreCase("12in")){
//            cheesePrice += isHasExtra() ? 3.15 : 2.25;
//        }

        // return final price
        return cheesePrice;
    }
    @Override
    public String getDescription(String size) {
        return String.format("+Cheese: %s (%s) Has extra \n ($%.2f)",this.getName(),this.isHasExtra() ,this.getPrice(size));
    }
}
