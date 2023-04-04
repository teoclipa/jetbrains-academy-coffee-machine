package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalWater = 400;
    private static int totalMilk = 540;
    private static int totalCoffeeBeans = 120;
    private static int totalCups = 9;
    private static int money = 550;


    public static void main(String[] args) {
        placeOrder();
    }

    public static void placeOrder() {
        while (true) {
            String option = " ";
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            if (scanner.hasNext()) {
                option = scanner.nextLine();
            }
            if (option.equals("exit")) {
                break;
            }
            switch (option) {
                case "buy" -> buyOption();
                case "fill" -> fillOption();
                case "take" -> takeOption();
                case "remaining" -> printCoffeeMachineStatus();

            }
        }
    }

    public static void buyOption() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = scanner.nextLine();
        if (!input.equals("back")) {
            buyCoffeeCupOfType(Integer.parseInt(input));
        }
    }

    public static void fillOption() {
        System.out.println("Write how many ml of water you want to add: ");
        totalWater += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        totalMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        totalCoffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add: ");
        totalCups += scanner.nextInt();
        scanner.nextLine();
    }

    public static void takeOption() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }

    public static void buyCoffeeCupOfType(int type) {
        switch (type) {
            case 1 -> buyCoffeeCup(250, 0, 16, 4);
            case 2 -> buyCoffeeCup(350, 75, 20, 7);
            case 3 -> buyCoffeeCup(200, 100, 12, 6);
        }

    }


    public static void buyCoffeeCup(int water, int milk, int coffeeBeans, int cost) {
        boolean isEnoughIngredients = true;

        if (totalWater < water) {
            System.out.println("Sorry, not enough water!");
            isEnoughIngredients = false;
        }

        if (totalMilk < milk) {
            System.out.println("Sorry, not enough milk!");
            isEnoughIngredients = false;
        }

        if (totalCoffeeBeans < coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            isEnoughIngredients = false;
        }
        if (totalCups < 1) {
            System.out.println("Sorry, not enough coffee cups!");
            isEnoughIngredients = false;
        }
        if (isEnoughIngredients) {
            totalWater -= water;
            totalMilk -= milk;
            totalCoffeeBeans -= coffeeBeans;
            totalCups--;
            money += cost;
            System.out.println("I have enough resources, making you a coffee!");
        }

    }

    public static void printCoffeeMachineStatus() {
        System.out.printf("""              
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                                
                """, totalWater, totalMilk, totalCoffeeBeans, totalCups, money);
    }
}

enum MachineState {
    CHOOSING_ACTION, CHOOSING_COFFEE_TYPE
}

enum Action {
    BUY, Fill, TAKE, REMAINING, EXIT
}