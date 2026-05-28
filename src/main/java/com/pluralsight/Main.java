package com.pluralsight;

import com.pluralsight.management.Order;
import com.pluralsight.products.Chips;
import com.pluralsight.products.Drink;
import com.pluralsight.products.Sandwich;
import com.pluralsight.toppings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final Scanner myScanner = new Scanner(System.in); //Shared scanner for handling input through whole menu
    private static  Order order = null; //Temp Order Variable
    private static List<Order> orderList = new ArrayList<>(); // The ArrayList stays here to hold all orders
    private static Sandwich sandwichBuilder;
    //Global item count holders:
    public static int sandwichCount = 0; // keep count of how many sandwiches are created.
    public static int sideCount = 0; // keep count of chips and drinks selected.
    public static int addedtoppings = 0;// keep count of added toppings
    public static boolean menuLoop = true;
    public static int sauces = 0;

    public static void main(String[] args) {
        //Menu Loop
        boolean mainLoop = true;
        while(mainLoop){
            System.out.println("""
                ===========================
                Welcome to AwAw Deli Shop!
                ===========================
                1) New Order
                0) Exit Application
                ===========================
                """);
           System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();

            switch (userInput){
                //TODO Generate random numbers 0-100 for Order ID
                case 1 :
                int orderIdNumber = generateID();  //Make a unique ID by checking the existing order lis
                order = new Order(orderIdNumber);
                orderScreen();
                break;
                case 0 :
                System.out.println("Thank you, Come Again! ");
                mainLoop = false;
                break;
                default:
                System.out.println("Invalid option, Enter 1 or 0.");
             }
        }
    }
    //OUTSIDE OF MAIN:

    //Order Screen Method:
    public static void orderScreen(){
        boolean orderingLoop = true;
        while(orderingLoop){
            System.out.println("""
                ====================
                AwAw Deli Screen
                ====================
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                ====================
                """);
            System.out.println("Sandwiches: " + sandwichCount + "\n" + "Toppings Selected: " + addedtoppings + "\n" + "Chips & Drinks: " + sideCount);
            System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();
            myScanner.nextLine();
            switch (userInput){
                case 1 : {
                    System.out.println("Build Your Sandwich: ");
                    handleSandwichBuilder();
                    break;
                }
                case 2 : {
                    System.out.println("Add a Drink: ");
                    handleDrinkDistributor();
                    break;
                }
                case 3 : {
                    System.out.println("Add Bag of Chips: ");
                    handleChipSelection();
                    break;
                }
                case 4 : {
                    System.out.println("Checkout: ");
                    break;
                }
                case 0 : {
                    System.out.println("Order Canceled");
                    order = null; // To clear out current order from processing
                    orderingLoop = false;
                    break;
                }
                default : {
                    System.out.println("Enter an option from 0-4.");
                    break;
                }
            }
        }
    }
    // Helper Methods for orderScreen to process order building:
    public static void handleSandwichBuilder(){
        //Bread Loop
        while(menuLoop) {
            System.out.println("""
                    =====================
                    Select Sandwich Size:
                    =====================
                    1) (4in)
                    2) (8in)
                    3) (12in)
                    =====================
                    """);
            System.out.println("Enter Choice: ");
            //size of sandwich equals to switch expression String and the switch equals to scanner.
            String size = switch (myScanner.nextInt()) {
                case 1 -> "4in";
                case 2 -> "8in";
                case 3 -> "12in";
                default -> {
                    System.out.println("Default size(4in)");
                    yield "4in"; //returns the default size fallback value when no other switch cases match user input.
                }
            };
            myScanner.nextLine();
            System.out.println("""
                    ======================
                    Select Your Bread:
                    ======================
                    1) (White)
                    2) (Wheat)
                    3) (Rye)
                    4) (Wrap)
                    =====================
                    """);
            System.out.println("Enter Choice: ");
            String bread = switch (myScanner.nextInt()) {
                case 1 -> "White";
                case 2 -> "Wheat";
                case 3 -> "Rye";
                case 4 -> "Wrap";
                default -> {
                    System.out.println("Default bread(White)");
                    yield "White";
                }
            };
            myScanner.nextLine();
            System.out.println("""
                    ===============================
                    Would you like it toasted?:
                    ===============================
                    1) (Yes)
                    2) (No)
                    ===============================
                    """);
            System.out.println("Enter Choice: ");
            boolean isToasted = switch (myScanner.nextInt()) {
                case 1 -> true;
                case 2 -> false;
                default -> {
                    System.out.println("Default Not Toasted");
                    yield false;
                }
            };

            //Pass user input choices into Sandwich constructor to create start of sandwich.
            sandwichBuilder = new Sandwich(size, bread, isToasted);
            menuLoop = false;
        }//Meat Loop
        while (!menuLoop) {
                        System.out.println("""
                        ===========================
                        Select Your Meat:
                        ===========================
                        1) (Steak)
                        2) (Ham)
                        3) (Salami)
                        4) (Roast Beef)
                        5) (Chicken)
                        6) (Bacon)
                        0) (None/Next->)
                        ===========================
                        """);
                    System.out.println("Added toppings:" + addedtoppings);
                    System.out.println("Enter Choice: ");
                    int meatChoice =  myScanner.nextInt();
                    myScanner.nextLine();
                    switch(meatChoice){
                    case 1:
                        sandwichBuilder.addTopping(new Meat("Steak", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 2:
                        sandwichBuilder.addTopping(new Meat("Ham", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 3:
                        sandwichBuilder.addTopping(new Meat("Salami", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 4:
                        sandwichBuilder.addTopping(new Meat("Roast Beef", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 5:
                        sandwichBuilder.addTopping(new Meat("Chicken", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 6:
                        sandwichBuilder.addTopping(new Meat("Bacon", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 0:
                        menuLoop = false;
                        break;
                    default:
                            System.out.println("Invalid choice: Enter an option from 0-6.");
                            break;
                     }
        }// Cheese Loop
        while (menuLoop) {
                       System.out.println("""
                        ===========================
                        Select Your Cheese:
                        ===========================
                        1) (American)
                        2) (Provolone)
                        3) (Cheddar)
                        4) (Swiss)
                        0) (Next ->)
                        ===========================
                        """);
                    System.out.println("Added toppings:" + addedtoppings);
                    System.out.println("Enter Choice: ");
                    int cheeseChoice = myScanner.nextInt();
                    myScanner.nextLine();
                    switch (cheeseChoice) {
                    case 1:
                        sandwichBuilder.addTopping(new Cheese("American", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 2:
                        sandwichBuilder.addTopping(new Cheese("Provolone", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 3:
                        sandwichBuilder.addTopping(new Cheese("Cheddar", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 4:
                        sandwichBuilder.addTopping(new Cheese("Swiss", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 0:
                        menuLoop = false;
                        break;
                    default:
                        System.out.println("Invalid choice: Enter an option from 0-4.");
                        break;
                     }
        }// Topping Loop
        while (!menuLoop) {
                        System.out.println("""
                        ===============================
                        Select any included Toppings :
                        ===============================
                        1) (Lettuce)
                        2) (Peppers)
                        3) (Onions)
                        4) (Tomatoes)
                        5) (Jalapenos)
                        6) (Cucumbers)
                        7) (Pickles)
                        8) (Guac)
                        9) (Mushrooms)
                        0) (Next ->)
                        ==============================
                        """);
                    System.out.println("Enter Choice: ");
                    int toppingChoice = myScanner.nextInt();
                    myScanner.nextLine();
                    switch (toppingChoice) {
                    case 1:
                        sandwichBuilder.addTopping(new Regular("Lettuce"));
                        break;
                    case 2:
                        sandwichBuilder.addTopping(new Regular("Peppers"));
                        break;
                    case 3:
                        sandwichBuilder.addTopping(new Regular("Onions"));
                        break;
                    case 4:
                        sandwichBuilder.addTopping(new Regular("Tomatoes"));
                        break;
                    case 5:
                        sandwichBuilder.addTopping(new Regular("Jalapenos"));
                        break;
                    case 6:
                        sandwichBuilder.addTopping(new Regular("Cucumbers"));
                        break;
                    case 7:
                        sandwichBuilder.addTopping(new Regular("Pickles"));
                        break;
                    case 8:
                        sandwichBuilder.addTopping(new Regular("Guac"));
                        break;
                    case 9:
                        sandwichBuilder.addTopping(new Regular("Mushrooms"));
                        break;
                    case 0:
                        menuLoop = false;
                        break;
                    default:
                        System.out.println("Invalid choice: Enter an option from 0-9.");
                        break;
                    }
        }// Side Loop
        while (menuLoop) {
                   System.out.println("""
                    =============================
                    Would you like any Au Jus?:
                    =============================
                    1) (Yes)
                    2) (No)
                    =============================
                    """);
                    int sauce = myScanner.nextInt();
                    myScanner.nextLine();
                    switch (sauce) {
                    case 1:
                       sandwichBuilder.addTopping(new Sides("Au Jus"));
                       System.out.println("Au Ju Added!");
                       sauces++;
                       break;
                    case 2:
                       System.out.println("Au Jus not Added");
                       menuLoop = false;
                       break;
                       default:
                       System.out.println("Invalid choice: Enter an option 1 or 2.");
                       break;
                    }
        }//Sauce Loop
         while (!menuLoop) {
                        System.out.println("""
                        =============================
                        Would you like any Sauces?:
                        =============================
                        1) (Mayo)
                        2) (Mustard)
                        3) (Ketchup)
                        4) (Ranch)
                        5) (Thousand Islands)
                        6) (Vinaigrette)
                        0) (Next ->)
                        ===========================
                        """);
                        System.out.println("Enter Choice: ");
                        int sauceChoice = myScanner.nextInt();
                        myScanner.nextLine();
                        switch (sauceChoice) {
                        case 1:
                            System.out.println("Mayo Added! ");
                            sandwichBuilder.addTopping(new Sauces("Mayo"));
                            break;
                        case 2:
                            System.out.println("Mustard Added!");
                            sandwichBuilder.addTopping(new Sauces("Mustard"));
                            break;
                        case 3:
                            System.out.println("Ketchup Added!");
                            sandwichBuilder.addTopping(new Sauces("Ketchup"));
                            break;
                        case 4:
                            System.out.println("Ranch Added!");
                            sandwichBuilder.addTopping(new Sauces("Ranch"));
                            break;
                        case 5:
                            System.out.println("Thousand Island Added!");
                            sandwichBuilder.addTopping(new Sauces("Thousand Island"));
                            break;
                        case 6:
                            sandwichBuilder.addTopping(new Sauces("Vinaigrette"));
                            break;
                        case 0:
                            menuLoop = false;
                            break;
                        default:
                            System.out.println("Invalid Choice: Enter options 0-6.");
                        }
         }
            System.out.println("Sandwich Added!");
            //Add finalized sandwich into Order ArrayList<>
            order.addItem(sandwichBuilder);
            sandwichCount++;

            // Add switch to confirm making another sandwich or return to main menu
                System.out.println("""
                =======================
                Add another Sandwich?
                =======================
                1) Add Sandwich!
                0) Exit!
                """);
            System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();
            myScanner.nextLine();
            switch (userInput) {
            case 1:
                break;
            case 0:
              System.out.println("Back to Home Screen ! ");
               menuLoop = false; // Stop menu Loop and return back to order screen
                break;
            default:
             System.out.println("Invalid option, Enter 1 or 0.");
               break;
            }
        }
    public static void handleDrinkDistributor(){
        while(menuLoop) {
            System.out.println("Sandwiches: " + sandwichCount + "\n" + "Chips & Drinks: " + sideCount);
                   System.out.println("""
                    =====================
                    AwAw Drink Station:
                    =====================
                    1) Small Drink
                    2) Medium Drink
                    3) Large Drink
                    0) (Next ->)
                    =====================
                    """);
                   //TODO change to regular switch case
                System.out.println("Enter Choice: ");
                String sizeSelected = switch (myScanner.nextInt()) {
                case 1 -> "Small";
                case 2 -> "Medium";
                case 3 -> "large";
                case 0 -> {
                    menuLoop =false;
                    yield "";
                }
                default -> {
                    System.out.println("Default no drink");
                    yield "";
                }
                };
            // if size is Not empty prompt drink flavor
            if (!sizeSelected.isEmpty()) {
                System.out.println("""
                        ==================
                        Choose Flavor:
                        ==================
                        1) Sprite
                        2) Cola
                        3) Lemonade
                        4) Hi-c
                        0) (Next ->)
                        ==================
                        """);
                    System.out.println("Enter Choice: ");
                    String flavorSelected = switch (myScanner.nextInt()) {
                    case 1 -> {
                        System.out.println("Drink Added");
                        yield "Sprite";
                    }
                    case 2 -> {
                        System.out.println("Drink Added");
                        yield "Cola";
                    }
                    case 3 -> {
                        System.out.println("Drink Added");
                        yield "Lemonade";
                    }
                    case 4 -> {
                        System.out.println("Drink Added");
                        yield "Hi-c";
                    }
                    case 0 -> {
                        System.out.println("No Drink");
                        menuLoop = false;
                        yield "";
                    }
                    default -> {
                        System.out.println("Default Flavor: Water");
                        yield "Water";
                    }
                };
                order.addItem(new Drink(sizeSelected, flavorSelected));
                sideCount++;
            }
        }
    }
    public static void handleChipSelection(){
        while(menuLoop) {
            System.out.println("Sandwiches: " + sandwichCount + "\n" + "Chips & Drinks: " + sideCount);
            System.out.println("""
                    =======================
                    AwAw Chip Selection:
                    =======================
                    1) Lays's (Classic)
                    2) Lay's (BBQ)
                    3) Doritos (Nacho)
                    4) Herr's (SC & Onion)
                    0) (Next ->)
                    =======================
                    """);
            System.out.println("Enter Choice: ");
            String chipsSelected = switch (myScanner.nextInt()) {
                case 1 -> {
                    System.out.println("Chips Added");
                    yield "Lays's (Classic)";
                }
                case 2 -> {
                    System.out.println("Chips Added");
                    yield "Lay's (BBQ)";
                }
                case 3 -> {
                    System.out.println("Chips Added");
                    yield "Doritos (Nacho)";
                }
                case 4 -> {
                    System.out.println("Chips Added");
                    yield "Herr's (SC & Onion)";
                }
                case 0 -> {
                    System.out.println("No Chips");
                    menuLoop =false;
                    yield "No Chips";
                }
                default -> {
                    System.out.println(" No Chips");
                    yield "";
                }
            };
            order.addItem(new Chips(chipsSelected));
            sideCount++;
        }
    }
    public static boolean askIfExtra(){
        System.out.println("""
                        =======================
                        Would you like extra?:
                        =======================
                        1) (Yes)
                        2) (No)
                        =======================
                        """);
        return switch (myScanner.nextInt()) {
            case 1 -> true;
            case 2 -> false;
            default -> {
                System.out.println("No Extra meat");
                yield false;
            }
        };
    }
    public static int generateID(){
        int id;
        boolean idExists;
        do {
            id = ThreadLocalRandom.current().nextInt(0,101);
            idExists = false;
            // Check if any existing order already uses this ID
            for (Order order : orderList){
                if (order.getOrderId() == id){
                    idExists = true;
                    break;
                }
            }
        }while (idExists);
        return id;
    }
    }
