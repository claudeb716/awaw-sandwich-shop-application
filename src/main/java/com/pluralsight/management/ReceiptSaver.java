package com.pluralsight.management;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReceiptSaver {
    //Fields:
    //Constructor:
    //Methods:
    //Pass current Order to be written on file with LocalDateTime
    public void saveReceiptToFile(Order o){
        LocalDateTime nowDateTime = LocalDateTime.now();
        // Create a file to save Receipt
        String AWAW_RECEIPTS_HEADER = nowDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss")) + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AWAW_RECEIPTS_HEADER,true))){
            // Over-write file
            bw.write(AWAW_RECEIPTS_HEADER + o.getOrderId()+ "|" + "\n");
            // Loop through all pricedItems
            for (PricedItem item : o.getPricedItems()){
                    bw.write(item)

            }

        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
        }

    }
}
