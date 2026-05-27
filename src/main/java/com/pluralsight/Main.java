package com.pluralsight;

import com.pluralsight.management.Order;
import com.pluralsight.products.Chips;
import com.pluralsight.products.Drink;
import com.pluralsight.products.Sandwich;
import com.pluralsight.toppings.*;

import java.util.Scanner;

public class Main {
    private static final Scanner myScanner = new Scanner(System.in); //Shared scanner for handling input through whole menu
    private static  Order order = null; //Main Order Variable
    //Global item count holders
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
                Welcome to AwAw Deli Shop!
                1) New Order
                0) Exit Application
                """);
           System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();

            switch (userInput){
                //TODO Generate random numbers 0-100 for Order ID
                case 1 :
                System.out.println("Enter 3 numbers.");
                int idNumber = myScanner.nextInt();
                myScanner.nextLine();

                order = new Order(idNumber);
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
            System.out.println("===================");
            System.out.println("Sandwiches: " + sandwichCount + "\n" + "Toppings Selected: " + addedtoppings + "\n" + "Chips & Drinks: " + sideCount);
            System.out.println("""
                AwAw Deli Screen
                ====================
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                ====================
                """);
            System.out.println("Enter Choice: ");
            int userInput = myScanner.nextInt();
            switch (userInput){
                case 1 : {
                    System.out.println("Add Sandwich: ");
                    handleSandwichBuilder();
                    break;
                }
                case 2 : {
                    System.out.println("Add Drink: ");
                    handleDrinkDistributor();
                    break;
                }
                case 3 : {
                    System.out.println("Add Chips: ");
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
       //boolean sandwichLoop = true;

        while(menuLoop) {
            System.out.println("============================");
            System.out.println("Cart: \n" +"============ \n" + "Sandwiches: " + sandwichCount + "\n " + "Chips & Drinks: " + sideCount);
            System.out.println("""
                    =====================
                    Select Sandwich Size:
                    =====================
                    1) (4in)
                    2) (8in)
                    3) (12in)
                    """);
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
            System.out.println("""
                    ======================
                    Select Your Bread:
                    ======================
                    1) (White)
                    2) (Wheat)
                    3) (Rye)
                    4) (Wrap)
                    """);
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
            System.out.println("""
                    ===============================
                    Would you like it toasted?:
                    ===============================
                    1) (Yes)
                    2) (No)
                    """);
            boolean isToasted = switch (myScanner.nextInt()) {
                case 1 -> true;
                case 2 -> false;
                default -> {
                    System.out.println("Default Not Toasted");
                    yield false;
                }
            };


            //Add user input choices into sandwich constructor
            Sandwich sandwich = new Sandwich(size, bread, isToasted);
            //Meat Loop
            //boolean meatLoop = true;
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
                        """);
                    System.out.println("Added toppings:" + addedtoppings);
                    int meatChoice =  myScanner.nextInt();
                    switch(meatChoice){
                    case 1:
                        sandwich.addTopping(new Meat("Steak", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 2:
                        sandwich.addTopping(new Meat("Ham", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 3:
                        sandwich.addTopping(new Meat("Salami", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 4:
                        sandwich.addTopping(new Meat("Roast Beef", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 5:
                        sandwich.addTopping(new Meat("Chicken", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 6:
                        sandwich.addTopping(new Meat("Bacon", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 0:
                        menuLoop = false;
                        break;
                    default:
                            System.out.println("Invalid choice: Enter an option from 0-6.");
                            break;
                     }
            }
            // Cheese Loop
                //boolean cheeseLoop = true;
                while (menuLoop) {
                    System.out.println("""
                        ===========================
                        Select Your Cheese:
                        ===========================
                        1) (American)
                        2) (Provolone)
                        3) (Cheddar)
                        4) (Swiss)
                        0) (None/Next ->)
                        """);
                    System.out.println("Added toppings:" + addedtoppings);
                    int cheeseChoice = myScanner.nextInt();
                    switch (cheeseChoice) {
                    case 1:
                        sandwich.addTopping(new Cheese("American", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 2:
                        sandwich.addTopping(new Cheese("Provolone", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 3:
                        sandwich.addTopping(new Cheese("Cheddar", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 4:
                        sandwich.addTopping(new Cheese("Swiss", askIfExtra()));
                        addedtoppings++;
                        break;
                    case 0:
                        menuLoop = false;
                        break;
                    default:
                        System.out.println("Invalid choice: Enter an option from 0-4.");
                        break;
                     }
                }
                // Topping Loop
                //boolean toppingLoop = true;
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
                        0) (None/Next ->)
                      
                        """);

                    int toppingChoice = myScanner.nextInt();
                    switch (toppingChoice) {
                    case 1:
                        sandwich.addTopping(new Regular("Lettuce"));
                        break;
                    case 2:
                        sandwich.addTopping(new Regular("Peppers"));
                        break;
                    case 3:
                        sandwich.addTopping(new Regular("Onions"));
                        break;
                    case 4:
                        sandwich.addTopping(new Regular("Tomatoes"));
                        break;
                    case 5:
                        sandwich.addTopping(new Regular("Jalapenos"));
                        break;
                    case 6:
                        sandwich.addTopping(new Regular("Cucumbers"));
                        break;
                    case 7:
                        sandwich.addTopping(new Regular("Pickles"));
                        break;
                    case 8:
                        sandwich.addTopping(new Regular("Guac"));
                        break;
                    case 9:
                        sandwich.addTopping(new Regular("Mushrooms"));
                        break;
                    case 0:
                        menuLoop = false;
                        break;
                    default:
                        System.out.println("Invalid choice: Enter an option from 0-9.");
                        break;
                    }
                }
           // Side Loop
            System.out.println("""
                    =============================
                    Would you like any Au Jus?:
                    =============================
                    1) (Yes)
                    2) (No)
                    """);
                while (menuLoop) {
                    int sauce = myScanner.nextInt();
                            switch (sauce) {
                        case 1:
                            sandwich.addTopping(new Sides("Au Jus"));
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
                }
                //Sauce Loop
           // boolean sauceLoop =true;
            while (!menuLoop) {
                System.out.println("""
                        Would you like any Sauces?:
                        ===============================
                        1) (Mayo)
                        2) (Mustard)
                        3) (Ketchup)
                        4) (Ranch)
                        5) (Thousand Islands)
                        6) (Vinaigrette)
                        0) (Done)
                        """);

                    String sauceChoice = myScanner.nextLine();
                    switch (sauceChoice) {
                        case "1":
                            sandwich.addTopping(new Sauces("Mayo"));
                            break;
                        case "2":
                            sandwich.addTopping(new Sauces("Mustard"));
                            break;
                        case "3":
                            sandwich.addTopping(new Sauces("Ketchup"));
                            break;
                        case "4":
                            sandwich.addTopping(new Sauces("Ranch"));
                            break;
                        case "5":
                            sandwich.addTopping(new Sauces("Thousand Island"));
                            break;
                        case "6":
                            sandwich.addTopping(new Sauces("Vinaigrette"));
                            break;
                        case "0":
                            menuLoop = false;
                            break;
                        default:
                            System.out.println("Invalid Choice: Enter options 0-6.");
                    }
            }
            System.out.println("Sandwich Added!");
            //Add finalized sandwich into Order ArrayList<>
            order.addItem(sandwich);
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

//        if (toastedChoice.equals("1")){
//            isToasted = true;
//        } else if (toastedChoice.equals("2")) {
//            isToasted = false;
//        }
    }
    public static void handleDrinkDistributor(){
        while(menuLoop) {
            System.out.println("Sandwiches: " + sandwichCount + "\n Chips & Drinks: " + sideCount);
            System.out.println("""
                    =====================
                    AwAw Drink Station:
                    =====================
                    1) Small Drink
                    2) Medium Drink
                    3) Large Drink
                    0) Exit
                    """);
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

            if (!sizeSelected.isEmpty()) { // if size is Not empty prompt drink flavor
                System.out.println("""
                        ==================
                        Choose Flavor:
                        ==================
                        1) Sprite
                        2) Cola
                        3) Lemonade
                        4) Hi-c
                        """);
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
            System.out.println("Cart: \n" + "Sandwiches: " + sandwichCount + "\n Chips & Drinks: " + sideCount);
            System.out.println("""
                    =======================
                    AwAw Chip Selection:
                    =======================
                    1) Lays's (Classic)
                    2) Lay's (BBQ)
                    3) Doritos (Nacho)
                    4) Herr's (SC & Onion)
                    0) Done !
                    """);
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
                    System.out.println("No chips");
                    menuLoop =false;
                    yield "No Chips";
                }
                default -> throw new IllegalStateException("Unexpected value: " + myScanner.nextInt());
            };
            order.addItem(new Chips(chipsSelected));
            sideCount++;


        }
    }
    public static boolean askIfExtra(){
        System.out.println("""
                        Would you like extra?:
                        ===============================
                        1) (Yes)
                        2) (No)
                        """);
        return switch (myScanner.nextLine()) {
            case "1" -> true;
            case "2" -> false;
            default -> {
                System.out.println("Default no Extra meat");
                yield false;
            }
        };
    }
    }
