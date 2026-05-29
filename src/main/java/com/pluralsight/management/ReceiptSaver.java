package com.pluralsight.management;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptSaver {
    //Fields:
    //Constructor:
    public ReceiptSaver() {
    }
    //Methods:
    public void saveReceiptToFile(List<Order> orderList){
        // TODO Create a file date formatted header to save order to receipt folder
        LocalDateTime nowDateTime = LocalDateTime.now();
        String AWAW_RECEIPTS_HEADER = "receipts/"
                + nowDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss"))
                + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AWAW_RECEIPTS_HEADER,true))){ //Wrap in try-with-resources
            //TODO Write order list to folder
            for (Order o : orderList){
                bw.write(o.toString());
                bw.newLine();
            }
            System.out.println("Receipt saved");
        } catch (Exception e) {
            System.err.println("Error Saving Receipt" + e.getMessage());
        }

    }
}
