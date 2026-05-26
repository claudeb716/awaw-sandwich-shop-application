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

    public static void main(String[] args) {
        //Fields:
        //User input



       while(true){
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
                break;
            default:
                System.out.println("Invalid option, Enter 1 or 0.");
        }

        }


    }
    //Order Screen
    public static void orderScreen(){
        boolean orderingLoop = true;
        while(orderingLoop){
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
    // Helper Methods
    public static void handleSandwichBuilder(){
        boolean orderingLoop = true;
        while(orderingLoop) {
            System.out.println("""
                    Sandwich Maker:
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

            System.out.println("""
                    Select Meat:
                    ===============================
                    1) (Steak)
                    2) (Ham)
                    3) (Salami)
                    4) (Roast Beef)
                    5) (Chicken)
                    6) (Bacon)
                    """);
            String meatChoice = switch (myScanner.nextLine()) {
                case "1" -> "Steak";
                case "2" -> "Ham";
                case "3" -> "Salami";
                case "4" -> "Roast Beef";
                case "5" -> "Chicken";
                case "6" -> "Bacon";
                case "7" -> "None";
                default -> {
                    System.out.println("Default no meat");
                    yield "None";
                }
            };
            System.out.println("""
                    Would you like extra Meat?:
                    ===============================
                    1) (Yes)
                    2) (No)
                    """);
            boolean extraMeat = switch (myScanner.nextLine()) {
                case "1" -> true;
                case "2" -> false;
                default -> {
                    System.out.println("Default no Extra meat");
                    yield false;
                }
            };

            System.out.println("""
                    Select Cheese:
                    ===============================
                    1) (American)
                    2) (Provolone)
                    3) (Cheddar)
                    4) (Swiss)
                    """);
            String cheeseChoice = switch (myScanner.nextLine()) {
                case "1" -> "American";
                case "2" -> "Provolone";
                case "3" -> "Cheddar";
                case "4" -> "Swiss";
                default -> {
                    System.out.println("Default no Cheese");
                    yield "None";
                }
            };
            System.out.println("""
                    Would you like extra Cheese?:
                    ===============================
                    1) (Yes)
                    2) (No)
                    """);
            boolean extraCheese = switch (myScanner.nextLine()) {
                case "1" -> true;
                case "2" -> false;
                default -> {
                    System.out.println("Default no Extra cheese");
                    yield false;
                }

            };
            System.out.println("Sandwich Added!");
            sandwich.addTopping(new Meat(meatChoice, extraMeat));
            sandwich.addTopping(new Cheese(cheeseChoice, extraCheese));

            //Add finalized sandwich into Order ArrayList<>
            order.addItem(sandwich);
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

    }
