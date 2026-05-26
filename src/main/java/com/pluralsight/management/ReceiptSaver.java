package com.pluralsight.management;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptSaver {
    //Fields:
    //Constructor:
    public ReceiptSaver() {
    }
    //Methods:
    //Pass current Order to be written on file with LocalDateTime
    public void saveReceiptToFile(Order o){
        LocalDateTime nowDateTime = LocalDateTime.now();
        // Create a file date formatted header to save order to receipt folder
        String AWAW_RECEIPTS_HEADER = "receipts/" + nowDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss")) + ".txt";
        //Wrap in try-with-resources
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AWAW_RECEIPTS_HEADER,true))){
            // Write orders to folder
            bw.write("=================================================");
            bw.write( " ID : " + o.getOrderId() + "\n");
            // Loop through all pricedItems
            for (PricedItem item : o.getPricedItems()){
                if (item instanceof Display){ // extract descriptions from Display interface
                    bw.write(((Display) item).getDescription() + "\n");
                }
                bw.write("---------------------------------------------");
            }
            bw.write("=================================================");
            bw.write(String.format("Total: %.2f",o.calculateorderTotal()));
            System.out.println("Receipt saved");
        } catch (Exception e) {
            System.err.println("Error Saving Receipt" + e.getMessage());
        }

    }
}
