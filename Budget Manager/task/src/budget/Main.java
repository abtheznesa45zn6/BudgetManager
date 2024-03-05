package budget;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int balance;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> purchases = new ArrayList<>();
    static int purchaseTotal = 0;

    public static void main(String[] args) {

        printMenu();

        boolean exited = false;
        while (!exited) {

            int action = scanner.nextInt();
            System.out.println();

            switch(action) {
                case 0 -> {
                    exit();
                    exited = true;}
                case 1 -> addIncome();
                case 2 -> addPurchase();
                case 3 -> showListOfPurchases();
                case 4 -> printBalance();
                default -> System.out.println("Option nicht vorhanden");
            }

            if (!exited) {
                System.out.println();
                printMenu();
            }
        }
    }

    private static void exit() {
        System.out.println("Bye!");
    }

    private static void addIncome() {
        System.out.println("Enter income:");
        balance += getCentsFromString(scanner.next());
        System.out.println("Income was added!");
    }

    private static int getCentsFromString(String str) {
        int cents = Integer.parseInt(str.replace(".", ""));
        if (!str.contains(".")) {
            cents *= 100;
        }
        return cents;
    }


    private static void addPurchase() {
        System.out.println("Enter purchase name:");
        String name = "";
        while (name.isEmpty()) {
            name = scanner.nextLine();
        }
        System.out.println("Enter its price:");
        String priceString = scanner.nextLine();
        System.out.println("Purchase was added!");

        purchases.add(name.concat(" $").concat(priceString));

        int priceInt = Integer.parseInt(priceString.replace(".",""));
        purchaseTotal += priceInt;
        balance -= priceInt;
    }

    private static void showListOfPurchases() {
        if (purchases.isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            for (String purchase : purchases) {
                System.out.println(purchase);
            }
            System.out.printf("Total sum: $%d.%d\n", purchaseTotal/100, purchaseTotal%100);
        }
    }

    private static void printBalance() {

        if (balance >= 100) {
            String bal = String.valueOf(balance);
            String dollar= bal.substring(0, bal.length()-2);
            String cents = bal.substring(bal.length()-2);

            System.out.printf("Balance: $%s.%s", dollar, cents);
        }

        if (balance <= 99) {
            System.out.printf("Balance: $0.%d", balance);
        }

        System.out.println();
    }

    private static void printMenu() {
        System.out.print(
                """
                        Choose your action:
                        1) Add income
                        2) Add purchase
                        3) Show list of purchases
                        4) Balance
                        0) Exit
                """
        );
    }
}
