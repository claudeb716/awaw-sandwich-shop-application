package com.pluralsight.toppings;

public abstract class Premium extends Toppings{
    //Fields: For Meat and Cheese (Child classes)
    private boolean hasExtra;
    //Constructor calling parent class(Topping) for the name and asking for extra topping from (child class)
    public Premium(String sandwichSize, String breadType, boolean isToasted, String name, boolean hasExtra) {
        super(sandwichSize, breadType, isToasted, name);
        this.hasExtra = hasExtra;
    }

    //Getter
    public boolean isHasExtra() {
        return this.hasExtra;
    }
}