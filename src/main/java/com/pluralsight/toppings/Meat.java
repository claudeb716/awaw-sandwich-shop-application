package com.pluralsight.toppings;

public class Meat extends Premium{
    //Fields:

    //Constructor: created from parent class(Premium) for name of Meat and if they want extra?
    public Meat(String name, boolean hasExtra) {
        super(name, hasExtra);
    }


    //Override method:  sandwichSize as a parameter to handle class own pricing. and check if hasExtra Meat

    @Override
    public double getPrice(String sandwichSize) {
        double meatPrice = 0.0;
        //TODO refactor to switch case.
        // Check the sandwich size and whether there’s an extra topping to determine the price of the Meat and add the additional cost.
        if (sandwichSize.equalsIgnoreCase("4in")){
            meatPrice += isHasExtra() ? 1.50 : 1.00; //Ternary Operator (?:) if yes for extra/ if no for extra
        }else if (sandwichSize.equalsIgnoreCase("8in")){
            meatPrice +=  isHasExtra() ? 3.00 : 2.00;
        }else if (sandwichSize.equalsIgnoreCase("12in")){
            meatPrice += isHasExtra() ? 4.50 : 3.00;
        }
        // return final price
        return meatPrice;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
