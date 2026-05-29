package com.pluralsight.products;

import com.pluralsight.interfaces.IDisplay;
import com.pluralsight.interfaces.IPricedItem;

public class Chip implements IPricedItem, IDisplay {
    //Fields:
    private  String chipType;
    //Constructor:
    public Chip(String chipType) {
        this.chipType = chipType;
    }
    //Override: Methods from Interfaces
    //PricedItem
    @Override
    public double calculatePrice() {
        // Flat-fee for every bag of chips
        return 1.50;
    }
    //Display
    @Override
    public String getDescription() {
        //return formatted price of chips
        if (!this.chipType.isEmpty()){
            return String.format("Chips: %s ($%.2f)",this.chipType, this.calculatePrice());}
        else {
            return "";
        }
    }
}
