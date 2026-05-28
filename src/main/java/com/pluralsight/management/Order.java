package com.pluralsight.management;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;
import com.pluralsight.products.Sandwich;

import java.util.ArrayList;
import java.util.List;

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

    // Add PricedItem to the Order
    public void addItem(PricedItem item){
        this.pricedItems.add(item);
    }
    // Adds up every pricedItem added to the Order
    public double calculateOrderTotal(){
        double orderTotal = 0.0;
        for (PricedItem item : this.pricedItems){
            orderTotal += item.calculatePrice();
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
    // Description Method
    @Override
    public String toString() {
        String orderReceipt = "================= \n" + "Order ID: " + orderId + "\n" + "=================\n";
        for (PricedItem item : this.pricedItems){
            if (item instanceof Display){
                orderReceipt += ((Display) item).getDescription() + "\n";
                orderReceipt += "----------------------------------\n";
            }
        }
        orderReceipt += "Total Due: $" + calculateOrderTotal() +  "\n";
        orderReceipt += "=================\n";
        return orderReceipt;
    }
}
