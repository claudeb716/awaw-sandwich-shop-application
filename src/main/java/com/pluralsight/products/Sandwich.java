package com.pluralsight.products;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;
import com.pluralsight.toppings.Toppings;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements PricedItem, Display {
    //Fields:
    private String sandwichSize;
    private String breadType;
    private boolean isToasted;
    private List<Toppings> allToppings; //Collection List for added toppings.
    //Constructor: initializes the fields and creates and empty topping collection list
    public Sandwich(String sandwichSize, String breadType, boolean isToasted) {
        this.sandwichSize = sandwichSize;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.allToppings = new ArrayList<>();
    }
    //Derived Method: pass Toppings as a parameter and add toppings in order to List.
    public void addTopping(Toppings t){
        this.allToppings.add(t);
    }
    //Override:
    //PriceItem
    @Override
    public double getPrice() {
        double sandwichPrice= 0.0;
        // Check for sandwichSize to equal String and update sandwichPrice
        if (this.sandwichSize.equalsIgnoreCase("4in")){
            sandwichPrice += 5.50;
        } else if (this.sandwichSize.equalsIgnoreCase("8in")) {
            sandwichPrice += 7.00;
        } else if (this.sandwichSize.equalsIgnoreCase("12in")) {
            sandwichPrice += 8.50;
        }
        //Loop through all added toppings and then add their cost to sandwichPrice
        for (Toppings t : allToppings){
            sandwichPrice += t.getPrice(this.sandwichSize);
        }
        return sandwichPrice;
    }
    //Display
    @Override
    public String getDescription() {
        // Create string of a built sandwich
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s Sandwich on %s Bread (%s)",this.sandwichSize, this.breadType, "%s Toasted: ",this.isToasted));
        if (this.allToppings.isEmpty()){ //if all toppings are empty
            return sb.append("Toppings: None").toString();
        }
        //Loop through toppings size and add name to string
        for (int i = 0; i <= allToppings.size()-1; i++) {
            String toppingName = this.allToppings.get(i).getName();
                sb.append(toppingName);
                if (i == allToppings.size()-1 && i > 0){ // if topping size is equal to i and i greater than 0 add "and"
                    sb.append("and ").append(toppingName);
                } else if (i == 0) { //i equal to 0  add topping
                    sb.append(toppingName);
                }else { // more than 2
                    sb.append(", ").append(toppingName);
                }
        }
        return sb.toString();
    }
}
