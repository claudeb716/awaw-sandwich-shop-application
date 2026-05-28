package com.pluralsight;

import com.pluralsight.interfaces.Display;
import com.pluralsight.interfaces.PricedItem;
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
    private static List<Order> orderList = new ArrayList<>(); // The ArrayList stays here to hold all orders
    private static Sandwich sandwichBuilder;
    private static Drink drinksHolder;
    private static Chips chipsHolder;
    //Global item count holders:
    public static int sandwichCount = 0; // keep count of how many sandwiches are created.
    public static int sideCount = 0; // keep count of chips and drinks selected.
    public static int addedToppings = 0;// keep count of added toppings
    public static boolean menuLoop = true;
    public static int sauces = 0;

    public static void main(String[] args) {

        //Menu Loop
        while (menuLoop) {
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

            switch (userInput) {
                //TODO Generate random numbers 0-100 for Order ID
                case 1:
                    orderScreen();
                    break;
                case 0:
                    System.out.println("Thank you, Come Again! ");
                    menuLoop = false;
                    break;
                default:
                    System.out.println("Invalid option, Enter 1 or 0.");
            }
        }


    }// OUTSIDE OF MAIN Method:
    //Order Screen Method:
    public static void orderScreen() {
        while (menuLoop) {
            Order orderBuilder; //Main Order Variable
            int orderIdNumber = generateID();  //Make a unique ID by checking the existing order lis
            orderBuilder = new Order(orderIdNumber);
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
            System.out.println("Sandwiches: " + sandwichCount + "\n" + "Premiums Selected: " + addedToppings + "\n" + "Chips & Drinks: " + sideCount);
            System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();
            myScanner.nextLine();
            switch (userInput) {
                case 1: {
                    System.out.println("Build Your Sandwich: ");
                    handleSandwichBuilder();
                    orderBuilder.addItem(sandwichBuilder);
                    break;
                }
                case 2: {
                    System.out.println("Add a Drink: ");
                    handleDrinkDistributor();
                    orderBuilder.addItem(drinksHolder);
                    break;
                }
                case 3: {
                    System.out.println("Add Bag of Chips: ");
                    handleChipSelection();
                    orderBuilder.addItem(chipsHolder);
                    break;
                }
                case 4: {
                    System.out.println("Checkout: ");
                    processCheckOut(orderBuilder); // Handles validation and file saving string
                    break;
                }
                case 0: {
                    System.out.println("Order Canceled");
                    sandwichBuilder = null; // To clear out current order from processing
                    drinksHolder = null;
                    chipsHolder = null;
                    menuLoop = false;
                    break;
                }
                default: {
                    System.out.println("Enter an option from 0-4.");
                    break;
                }
            }
        }
    }
    // Helper Methods for orderScreen to process order building:
    public static void handleSandwichBuilder() {
        //Bread Loop
        boolean breadLoop = true;
        while (breadLoop) {
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
            //Pass user input choices into global Sandwich constructor to create start of sandwich.
            sandwichBuilder = new Sandwich(size, bread, isToasted);
            System.out.println("Now Select Toppings !");
            //Meat Loop
            meatSelection();
            // Cheese Loop
            cheeseSelection();
            // Topping Loop
            toppingSelection();
            // Side Loop
            sideSelection();
            //Sauce Loop
            sauceSelection();
            // Add switch to confirm making another sandwich or return to main menu
            System.out.println("""
                    =======================
                    Add another Sandwich?
                    =======================
                    1) Add Sandwich!
                    0) Exit!
                    =======================
                    """);
            System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();
            myScanner.nextLine();
            switch (userInput) {
                case 1:
                    break;
                case 0:
                    System.out.println("Back to Home Screen ! ");
                    breadLoop = false; // Stop menu Loop and return back to order screen
                    break;
                default:
                    System.out.println("Invalid option, Enter 1 or 0.");
                    break;
            }
        }
    }
    public static void handleDrinkDistributor() {
        //Drink Loop
        boolean drinkLoop = true;
        while (drinkLoop) {
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
            System.out.println("Sandwiches: " + sandwichCount + "\n" + "Chips & Drinks: " + sideCount);
            System.out.println("Enter Choice: ");
            String sizeSelected = switch (myScanner.nextInt()) {
                case 1 -> "Small";
                case 2 -> "Medium";
                case 3 -> "large";
                case 0 -> {
                    drinkLoop = false;
                    yield "";
                }
                default -> {
                    System.out.println("Default no drink");
                    yield "";
                }
            };
            // if size is Not empty prompt drink flavor
            if (drinkLoop) {
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
                        drinkLoop = false;
                        yield "";
                    }
                    default -> {
                        System.out.println("Default Flavor: Water");
                        yield "Water";
                    }
                };
                drinksHolder = new Drink(sizeSelected, flavorSelected);
                sideCount++;
            }
            // Add switch to confirm adding another Drink or return to main menu
            System.out.println("""
                    =======================
                    Add another Drink?
                    =======================
                    1) Add Drink!
                    0) Exit!
                    =======================
                    """);
            System.out.println("Drinks & Chips: " + sideCount);
            System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();
            myScanner.nextLine();
            switch (userInput) {
                case 1:
                    break;
                case 0:
                    System.out.println("Back to Home Screen ! ");
                    drinkLoop = false; // Stop menu Loop and return back to order screen
                    break;
                default:
                    System.out.println("Invalid option, Enter 1 or 0.");
                    break;
            }
        }
    }
    public static void handleChipSelection() {
        //Chip Loop
        boolean chipLoop = true;
        while (chipLoop) {
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
            System.out.println("Sandwiches: " + sandwichCount + "\n" + "Chips & Drinks: " + sideCount);
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
                    chipLoop = false;
                    yield "No Chips";
                }
                default -> {
                    System.out.println(" No Chips");
                    yield "";
                }
            };
            chipsHolder = new Chips(chipsSelected);
            sideCount++;
            // Add switch to confirm adding another bag of Chips or return to main menu
            System.out.println("""
                    =======================
                    Add another Bag of Chips?
                    =======================
                    1) Add Chips!
                    0) Exit!
                    ========================
                    """);
            System.out.println("Chips & Drink: " + sideCount);
            System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();
            myScanner.nextLine();
            switch (userInput) {
                case 1:
                    break;
                case 0:
                    System.out.println("Back to Home Screen ! ");
                    chipLoop = false; // Stop menu Loop and return back to order screen
                    break;
                default:
                    System.out.println("Invalid option, Enter 1 or 0.");
                    break;
            }
        }
    }
    public static void meatSelection() {
        //Meat Loop
        boolean meatLoop = true;
        while (meatLoop) {
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
            System.out.println("Added Premiums:" + addedToppings);
            System.out.println("Enter Choice: ");
            int meatChoice = myScanner.nextInt();
            myScanner.nextLine();
            switch (meatChoice) {
                case 1: {
                    sandwichBuilder.addTopping(new Meat("Steak", askIfExtra()));
                    System.out.println("Steak Added! ");
                    addedToppings++;
                    break;}
                case 2: {
                    sandwichBuilder.addTopping(new Meat("Ham", askIfExtra()));
                    System.out.println("Ham Added! ");
                    addedToppings++;
                    break;}
                case 3:{
                    sandwichBuilder.addTopping(new Meat("Salami", askIfExtra()));
                    System.out.println("Salami Added! ");
                    addedToppings++;
                    break;}
                case 4:{
                    sandwichBuilder.addTopping(new Meat("Roast Beef", askIfExtra()));
                    System.out.println("Roast Beef Added! ");
                    addedToppings++;}
                    break;
                case 5:{
                    sandwichBuilder.addTopping(new Meat("Chicken", askIfExtra()));
                    System.out.println("Chicken Added! ");
                    addedToppings++;
                    break;}
                case 6:{
                    sandwichBuilder.addTopping(new Meat("Bacon", askIfExtra()));
                    System.out.println("Bacon Added! ");
                    addedToppings++;
                    break;}
                case 0:{
                    meatLoop = false;
                    break;}
                default:{
                    System.out.println("Invalid choice: Enter an option from 0-6.");
                    break;}
            }

        }
    }
    public static boolean askIfExtra() {
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
    public static int generateID() {
        int id;
        boolean idExists;
        do {
            id = ThreadLocalRandom.current().nextInt(0, 101);
            idExists = false;
            for (Order order : orderList) {
                if (order.getOrderId() == id) {
                    idExists = true;
                    break;
                }
            }
        } while (idExists);
        return id;
    }
    public static void cheeseSelection() {
        //Cheese Loop
        boolean cheeseLoop = true;
        while (cheeseLoop) {
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
            System.out.println("Added Premiums:" + addedToppings);
            System.out.println("Enter Choice: ");
            int cheeseChoice = myScanner.nextInt();
            myScanner.nextLine();
            switch (cheeseChoice) {
                case 1:
                    sandwichBuilder.addTopping(new Cheese("American", askIfExtra()));
                    addedToppings++;
                    break;
                case 2:
                    sandwichBuilder.addTopping(new Cheese("Provolone", askIfExtra()));
                    addedToppings++;
                    break;
                case 3:
                    sandwichBuilder.addTopping(new Cheese("Cheddar", askIfExtra()));
                    addedToppings++;
                    break;
                case 4:
                    sandwichBuilder.addTopping(new Cheese("Swiss", askIfExtra()));
                    addedToppings++;
                    break;
                case 0:
                    cheeseLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice: Enter an option from 0-4.");
                    break;
            }
        }
    }
    public static void toppingSelection() {
        //Topping Loop
        boolean toppingLoop = true;
        while (toppingLoop) {
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
                case 1:{
                    System.out.println("Lettuce Added! ");
                    sandwichBuilder.addTopping(new Regular("Lettuce"));
                    break;}
                case 2:{
                    System.out.println("Peppers Added! ");
                    sandwichBuilder.addTopping(new Regular("Peppers"));
                    break;}
                case 3:{
                    System.out.println("Onions Added! ");
                    sandwichBuilder.addTopping(new Regular("Onions"));
                    break;}
                case 4:{
                    System.out.println("Tomatoes Added! ");
                    sandwichBuilder.addTopping(new Regular("Tomatoes"));
                    break;}
                case 5:{
                    System.out.println("Jalapenos Added! ");
                    sandwichBuilder.addTopping(new Regular("Jalapenos"));
                    break;}
                case 6:{
                    System.out.println("Cucumbers Added! ");
                    sandwichBuilder.addTopping(new Regular("Cucumbers"));
                    break;}
                case 7:{
                    System.out.println("Pickles Added! ");
                    sandwichBuilder.addTopping(new Regular("Pickles"));
                    break;}
                case 8:{
                    System.out.println("Guac Added! ");
                    sandwichBuilder.addTopping(new Regular("Guac"));
                    break;}
                case 9:{
                    System.out.println("Mushrooms Added! ");
                    sandwichBuilder.addTopping(new Regular("Mushrooms"));
                    break;}
                case 0:{
                    toppingLoop = false;
                    break;}
                default:{
                    System.out.println("Invalid choice: Enter an option from 0-9.");
                    break;}
            }
        }
    }
    public static void sideSelection () {
        //Side Loop
         boolean sideLoop = true;
            while (sideLoop) {
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
                    case 1:{
                        sandwichBuilder.addTopping(new Sides("Au Jus"));
                        System.out.println("Au Ju Added!");
                        sauces++;
                        break;}
                    case 2:{
                        System.out.println("Au Jus not Added");
                        sideLoop = false;
                        break;}
                    default:{
                        System.out.println("Invalid choice: Enter an option 1 or 2.");
                        break;}
                }
            }
        }
    public static void sauceSelection () {
        //Sauce Loop
         boolean sauceLoop = true;
            while (sauceLoop) {
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
                    case 1: {
                        System.out.println("Mayo Added! ");
                        sandwichBuilder.addTopping(new Sauces("Mayo"));
                        break;}
                    case 2: {
                        System.out.println("Mustard Added!");
                        sandwichBuilder.addTopping(new Sauces("Mustard"));
                        break;}
                    case 3: {
                        System.out.println("Ketchup Added!");
                        sandwichBuilder.addTopping(new Sauces("Ketchup"));
                        break;}
                    case 4: {
                        System.out.println("Ranch Added!");
                        sandwichBuilder.addTopping(new Sauces("Ranch"));
                        break;}
                    case 5: {
                        System.out.println("Thousand Island Added!");
                        sandwichBuilder.addTopping(new Sauces("Thousand Island"));
                        break;}
                    case 6:{
                        sandwichBuilder.addTopping(new Sauces("Vinaigrette"));
                        break;}
                    case 0: {
                        sauceLoop = false;
                        break;}
                    default: {
                        System.out.println("Invalid Choice: Enter options 0-6.");
                    }
                }
            }
            System.out.println("Sandwich Added!");
            sandwichCount++;
        }
    public static void processCheckOut(Order order) {
        if (!order.orderRequirementValidator()){
            System.out.println("""
                    Your Cart Does NOT meet minium requirement.
                                     NOTE:
                            You must have at least
                    1 Sandwich OR 1 Side item (Chips/Drink) to order!
                    """);
        }
        for (PricedItem item : order.getPricedItems()){
            if (item instanceof Display){
                System.out.println(((Display) item).getDescription());
            }
        }
    }
}// End of MAIN
