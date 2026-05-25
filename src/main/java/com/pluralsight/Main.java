package com.pluralsight;

import com.pluralsight.management.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //User input
        Scanner myScanner = new Scanner(System.in);

       while(true){
        System.out.println("""
                Welcome to AwAw Deli Shop!
                1 New Order
                0 Exit Application
                """);
        String userInput = myScanner.nextLine();
        switch (userInput){
            case "1" :
                System.out.println("Enter 3 numbers");
                int idNumber = myScanner.nextInt();
                //Main Variable
                Order order = new Order(idNumber);
                order.orderScreen();
                break;
            case "0" :
                return;
        }
    }
    }
    }
