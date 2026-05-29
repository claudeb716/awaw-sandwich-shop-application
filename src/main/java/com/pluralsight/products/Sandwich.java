package com.pluralsight.products;

import com.pluralsight.interfaces.IDisplay;
import com.pluralsight.interfaces.IPricedItem;
import com.pluralsight.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements IPricedItem, IDisplay {
    //Fields:
    private String sandwichSize;
    private String breadType;
    private boolean isToasted;
    private List<Topping> allToppings; //Collection List for added toppings.
    //Constructor: initializes the fields and creates and empty topping collection list
    public Sandwich(String sandwichSize, String breadType, boolean isToasted) {
        this.sandwichSize = sandwichSize;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.allToppings = new ArrayList<>();
    }
    //Derived Method: pass Toppings as a parameter and add toppings in order to List.
    public void addTopping(Topping t){

        this.allToppings.add(t);
    }
    //Derived Method:
    @Override
    public double calculatePrice(){
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
        for (Topping t : allToppings){
            sandwichPrice += t.getPrice(this.sandwichSize);
        }
        return sandwichPrice;
    }
    @Override
    public String getDescription() {
        // Create string of a built sandwich
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s Sandwich on %s Bread (%s Toasted)\n", this.sandwichSize, this.breadType, this.isToasted));

        if (this.allToppings.isEmpty()) { //if all toppings are empty
            return sb.append("None").toString();
        }else {
            sb.append("Toppings:\n");
            for (Topping t : allToppings) {
                sb.append("  ").append(t.getDescription(this.sandwichSize)).append("\n ");
            }
            return sb.toString();
        }
    }
}

