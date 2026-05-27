package com.pluralsight;

import com.pluralsight.management.Order;
import com.pluralsight.products.Chips;
import com.pluralsight.products.Drink;
import com.pluralsight.products.Sandwich;
import com.pluralsight.toppings.*;

import java.util.Scanner;

public class Main {
    //Shared scanner for handling input through whole menu
    private static final Scanner myScanner = new Scanner(System.in);
    //Main Order Variable
    private static  Order order = null;
    //Shared item count holder
    public static int sandwichCount = 0; // keep count of how many sandwiches are created.
    public static int sideCount = 0; // keep count of chips and drinks.
    public static void main(String[] args) {
        //User input
        //Menu Loop
        boolean mainLoop = true;
       while(mainLoop){
            System.out.println("""
                Welcome to AwAw Deli Shop!
                1) New Order
                0) Exit Application
                """);
           System.out.println("Enter Choice: ");
            String userInput = myScanner.nextLine();

            switch (userInput){
                case "1" :
                System.out.println("Enter 3 numbers.");
                int idNumber = myScanner.nextInt();
                myScanner.nextLine();

                order = new Order(idNumber);
                orderScreen();
                break;
                case "0" :
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
            System.out.println("Cart: \n" + "Sandwiches: " + sandwichCount + "\n Chips & Drinks: " + sideCount);
            System.out.println("""
                AwAw Deli Screen
                ===============================
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                """);
            System.out.println("Enter Choice: ");
            String userInput = myScanner.nextLine();
            switch (userInput){
                case "1" -> {
                    System.out.println("Add Sandwich: ");
                    handleSandwichBuilder();
                    break;
                }
                case "2" -> {
                    System.out.println("Add Drink: ");
                    handleDrinkDistributor();
                    break;
                }
                case "3" -> {
                    System.out.println("Add Chips: ");
                    handleChipSelection();
                    break;
                }
                case "4" -> {
                    System.out.println("Checkout: ");
                    break;
                }
                case "0" -> {
                    System.out.println("Order Canceled");
                    order = null; // To clear out current order from processing
                    orderingLoop = false;
                    break;
                }
                default -> {
                    System.out.println("Enter an option from 0-4.");
                    break;
                }
            }
        }
    }
    // Helper Methods for orderScreen:
    public static void handleSandwichBuilder(){
        boolean orderingLoop = true;

        while(orderingLoop) {
            System.out.println("Cart: \n" + "Sandwiches: " + sandwichCount + "\n Chips & Drinks: " + sideCount);
            System.out.println("""
                    Sandwich Maker
                    =================
                    Select Sandwich Size:
                    ===============================
                    1) (4in)
                    2) (8in)
                    3) (12in)
                    """);
            String size = switch (myScanner.nextLine()) {
                case "1" -> "4in";
                case "2" -> "8in";
                case "3" -> "12in";
                default -> {
                    System.out.println("Default size");
                    yield "4in";
                }
            };

            System.out.println("""
                    Select Bread Type:
                    ===============================
                    1) (White)
                    2) (Wheat)
                    3) (Rye)
                    4) (Wrap)
                    """);
            String bread = switch (myScanner.nextLine()) {
                case "1" -> "White";
                case "2" -> "Wheat";
                case "3" -> "Rye";
                case "4" -> "Wrap";
                default -> {
                    System.out.println("Default bread");
                    yield "White";
                }
            };

            System.out.println("""
                    Would you like your sandwich toasted?:
                    ===============================
                    1) (Yes)
                    2) (No)
                    """);
            boolean isToasted = switch (myScanner.nextLine()) {
                case "1" -> true;
                case "2" -> false;
                default -> {
                    System.out.println("Default isToasted");
                    yield false;
                }
            };
            //Add user input choices into sandwich constructor
            Sandwich sandwich = new Sandwich(size, bread, isToasted);

            boolean meatLoop = true;
            while (meatLoop) {
                System.out.println("""
                        Select Meat:
                        ===============================
                        1) (Steak)
                        2) (Ham)
                        3) (Salami)
                        4) (Roast Beef)
                        5) (Chicken)
                        6) (Bacon)
                        0) (None)
                        """);
                    String meatChoice =  myScanner.nextLine();
                    switch(meatChoice){
                    case "1":
                        sandwich.addTopping(new Meat("Steak", askIfExtra()));
                        break;
                    case "2":
                        sandwich.addTopping(new Meat("Ham", askIfExtra()));
                        break;
                    case "3":
                        sandwich.addTopping(new Meat("Salami", askIfExtra()));
                        break;
                    case "4":
                        sandwich.addTopping(new Meat("Roast Beef", askIfExtra()));
                        break;
                    case "5":
                        sandwich.addTopping(new Meat("Chicken", askIfExtra()));
                        break;
                    case "6":
                        sandwich.addTopping(new Meat("Bacon", askIfExtra()));
                        break;
                    case "0":
                        sandwich.addTopping(new Meat("", !askIfExtra())); //
                        meatLoop = false;
                        break;
                    default:
                            System.out.println("Invalid choice");
                            break;
                     }
            }
            System.out.println("====================================================================================");

                boolean cheeseLoop = true;
                while (cheeseLoop) {
                    System.out.println("""
                        Select Cheese:
                        ===============================
                        1) (American)
                        2) (Provolone)
                        3) (Cheddar)
                        4) (Swiss)
                        0) (None)
                        """);
                    String cheeseChoice = myScanner.nextLine();
                    switch (cheeseChoice) {
                    case "1":
                        sandwich.addTopping(new Cheese("American", askIfExtra()));
                        break;
                    case "2":
                        sandwich.addTopping(new Cheese("Provolone", askIfExtra()));
                        break;
                    case "3":
                        sandwich.addTopping(new Cheese("Cheddar", askIfExtra()));
                        break;
                    case "4":
                        sandwich.addTopping(new Cheese("Swiss", askIfExtra()));
                        break;
                    case "0":
                        sandwich.addTopping(new Cheese("", !askIfExtra()));
                        cheeseLoop = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                     }
                 }
            System.out.println("=====================================================================================");

                boolean toppingLoop = true;
                while (toppingLoop) {
                    System.out.println("""
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
                      
                        """);
                    String toppingChoice = myScanner.nextLine();
                    switch (toppingChoice) {
                    case "1":
                        sandwich.addTopping(new Regular("Lettuce"));
                        break;
                    case "2":
                        sandwich.addTopping(new Regular("Peppers"));
                        break;
                    case "3":
                        sandwich.addTopping(new Regular("Onions"));
                        break;
                    case "4":
                        sandwich.addTopping(new Regular("Tomatoes"));
                        break;
                    case"5":
                        sandwich.addTopping(new Regular("Jalapenos"));
                        break;
                    case"6":
                        sandwich.addTopping(new Regular("Cucumbers"));
                        break;
                    case "7":
                        sandwich.addTopping(new Regular("Pickles"));
                        break;
                    case"8":
                        sandwich.addTopping(new Regular("Guac"));
                        break;
                    case"9":
                        sandwich.addTopping(new Regular("Mushrooms"));
                        break;
                    case "0":
                        sandwich.addTopping(new Regular(""));
                        toppingLoop = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                    }
                }
            System.out.println("====================================================================================");
            System.out.println("""
                    Would you like any Au Jus?:
                    ===============================
                    1) (Yes)
                    2) (No)
                    """);
            String sauce = switch (myScanner.nextLine()) {
                case "1" -> "Au Jus";
                case "2" -> "";
                default -> {
                    System.out.println("Default no Au Jus Added");
                    yield "";
                }

            };
            sandwich.addTopping(new Sides(sauce));
            System.out.println("======================================================================================");
            boolean sauceLoop =true;
            while (sauceLoop) {
                System.out.println("""
                        Would you like any Sauces?:
                        ===============================
                        1) (Mayo)
                        2) (Mustard)
                        3) (Ketchup)
                        4) (Ranch)
                        5) (Thousand Islands)
                        6) (Vinaigrette)
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
                            sandwich.addTopping(new Sauces(""));
                            sauceLoop = false;
                            break;
                        default:
                            System.out.println("Invalid Choice");
                    }
            }
            System.out.println("Sandwich Added!");

            //Add finalized sandwich into Order ArrayList<>
            order.addItem(sandwich);
            sandwichCount++;

            // Add switch to confirm making another sandwiche or return to main menu
            System.out.println("""
                Add another Sandwich?
                1) Add Sandwich!
                0) Exit!
                """);
            System.out.println("Enter Choice: ");
            String userInput = myScanner.nextLine();

            switch (userInput) {
                case "1":
                    break;
                case "0":
                    System.out.println("Back to Home Screen ! ");
                    orderingLoop = false; // Stop ordering Loop and return
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
        boolean orderingLoop = true;
        while(orderingLoop) {
            System.out.println("Cart: \n" + "Sandwiches: " + sandwichCount + "\n Chips & Drinks: " + sideCount);
            System.out.println("""
                    AwAw Drink Station:
                    ===============================
                    1) Small Drink
                    2) Medium Drink
                    3) Large Drink
                    """);
            String sizeSelected = switch (myScanner.nextLine()) {
                case "1" -> "Small";
                case "2" -> "Medium";
                case "3" -> "large";
                case "0" -> "none";
                default -> {
                    System.out.println("Default no drink");
                    yield "";
                }
            };
            System.out.println("""
                    Choose Flavor:
                    ===============================
                    1) Sprite
                    2) Cola
                    3) Lemonade
                    4) Hi-c
                    """);
            String flavorSelected = switch (myScanner.nextLine()) {
                case "1" -> "Small";
                case "2" -> "Medium";
                case "3" -> "large";
                case "4" -> "Hi-c";
                default -> {
                    System.out.println("Default no drink");
                    yield "";
                }
            };
            order.addItem(new Drink(sizeSelected, flavorSelected));
            System.out.println("Drink Added");
        }
    }
    public static void handleChipSelection(){
        boolean orderingLoop = true;
        while(orderingLoop) {
            System.out.println("Cart: \n" + "Sandwiches: " + sandwichCount + "\n Chips & Drinks: " + sideCount);
            System.out.println("""
                    AwAw Chip Selection:
                    ===============================
                    1) Lays's (Classic)
                    2) Lay's (BBQ)
                    3) Doritos (Nacho)
                    4) Herr's (SC & Onion)
                    """);
            String chipsSelected = switch (myScanner.nextLine()) {
                case "1" -> "Lays's (Classic)";
                case "2" -> "Lay's (BBQ)";
                case "3" -> "oritos (Nacho)";
                case "0" -> "Herr's (SC & Onion)";
                default -> {
                    System.out.println("Default no chips");
                    yield "";
                }
            };
            order.addItem(new Chips(chipsSelected));
            System.out.println("Chips Added");
        }
    }
    public  static void askForToppings(){
        System.out.println("""
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
              
                """);
        String toppingChoice = switch (myScanner.nextLine()){
            case "1" -> "Lettuce";
            case "2" -> "Peppers";
            case "3" -> "Onions";
            case "4" -> "Tomatoes";
            case "5" -> "Jalapenos";
            case "6" -> "Cucumbers";
            case "7" -> "Pickles";
            case "8" -> "Guac";
            case "9" -> "Mushrooms";
            case "0" -> "None";
            default -> {
                System.out.println("Default no toppings");
                yield "None";
            }
        };
        order.addItem(new Regular(toppingChoice));
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
