package com.pluralsight.toppings;

public abstract class Premium extends Topping {
    //Fields: For Meat and Cheese (Child classes)
    private boolean hasExtra;
    //Constructor
    public Premium(String name, boolean hasExtra) {
        super(name);
        this.hasExtra = hasExtra;
    }
    //Getter
    public boolean isHasExtra() {
        return hasExtra;
    }
    @Override
    public abstract double getPrice(String sandwichSize);
    @Override
    public abstract String getDescription(String size);
}