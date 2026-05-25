package com.pluralsight.management;

import com.pluralsight.interfaces.PricedItem;
import com.pluralsight.products.Sandwich;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    //Fields: List to hold PricedItems
    // Order tracker
    private List<PricedItem> pricedItems;
    private int orderId;
    //Constructor
    public Order(int orderId) {
        this.orderId = orderId;
        this.pricedItems = new ArrayList<>();
    }
    //Getters
    public List<PricedItem> getPricedItems() {
        return this.pricedItems;
    }

    public int getOrderId() {
        return orderId;
    }
    //Derived Methods:
    //Order Screen
    public void orderScreen(){
        Scanner myScanner = new Scanner(System.in);
        while(true){
            System.out.println("""
                AwAw Deli Screen
                
                
                """);
            String userInput = myScanner.nextLine();
            switch (userInput){
                case "1" -> System.out.println("New Order");
                case "0" -> {
                    return;
                }


            }
        }
    }
    // Add PricedItem to the Order
    public void addItem(PricedItem item){
        this.pricedItems.add(item);
    }
    // Adds up every pricedItem added to the Order
    public double calculateorderTotal(){
        double orderTotal = 0.0;
        for (PricedItem item : this.pricedItems){
            orderTotal += item.getPrice();
        }
        return orderTotal;
    }
    // If check 0 sandwiches ordered, then they MUST purchase Chips or a Drink
    public boolean orderRequirementValidator(){
        int numberOfSandwiches = 0;
        int numberOfOtherItems = 0;

        if (this.pricedItems.isEmpty()){
            return false;
        }
        //Loop through items and count how many
        for (PricedItem item : this.pricedItems){
            if (item instanceof Sandwich){
                numberOfSandwiches++;
            }else {
                numberOfOtherItems++;
            }
        }
        // if check to have at least 1 sandwich. if none MUST have at least one bag of chips or drink
        if (numberOfSandwiches > 0) {
            return true;
        }else {
            return numberOfOtherItems > 0;
        }
    }
}
