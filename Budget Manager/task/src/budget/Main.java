package budget;

import java.util.Scanner;

public class Main {

    static DollarAmount balance = new DollarAmount(0);
    static Scanner scanner = new Scanner(System.in);
    static final PurchaseList purchases = new PurchaseList();
    static final FileSaver fileSaver = new FileSaver(balance, purchases);

    public static void main(String[] args) {
        try {
            printActionMenu();

            boolean exited = false;
            while (!exited) {

                int action = scanner.nextInt();
                System.out.println();

                switch(action) {
                    case 0 -> exited = true;
                    case 1 -> addIncome();
                    case 2 -> chooseTypeOfPurchaseInput();
                    case 3 -> chooseTypeOfPurchaseOutput();
                    case 4 -> printBalance();
                    case 5 -> save();
                    case 6 -> load();
                    case 7 -> analyze();
                    default -> System.out.println("Action not available");
                }

                if (!exited) {
                    System.out.println();
                    printActionMenu();
                }
            }
        } finally {
            scanner.close();
            exit();
        }
    }

    private static void exit() {
        System.out.println("Bye!");
    }

    private static void addIncome() {
        System.out.println("Enter income:");
        balance.plus(new DollarAmount(scanner.next()));
        System.out.println("Income was added!");
    }

    private static void chooseTypeOfPurchaseInput() {
        printPurchaseTypeInputMenu();

        int action = scanner.nextInt();
        System.out.println();

        if (action == 5) {
            return;
        }

        addPurchase(getTypeForInt(action));

        System.out.println();
        chooseTypeOfPurchaseInput();
    }

    private static void chooseTypeOfPurchaseOutput() {
        if (purchases.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }

        printPurchaseTypeOutputMenu();

        int action = scanner.nextInt();
        System.out.println();

        if (action == 6) {
            return;
        }

        Type type = getTypeForInt(action);
        System.out.println(type.getTypeName()+":");

        if (action == 5) {
            for (Type t : Type.values()) {
                printPurchaseList(t);
            }

            System.out.println("Total sum: " + purchases.getSumOfAllPurchases());

        } else {
            if (purchases.isEmpty(type)) {
                System.out.println("The purchase list is empty");
                return;
            } else {
                printPurchaseList(type);

                System.out.println("Total sum: " + purchases.getSumOfPurchaseOfType(type));
            }
        }

        System.out.println();
        chooseTypeOfPurchaseOutput();
    }

    static Type getTypeForInt(int number) {

        // test #5 calls this for some reason
        if (number == 0) {
            System.exit(0);
        }

        return switch(number) {
            case 1 -> Type.FOOD;
            case 2 -> Type.CLOTHES;
            case 3 -> Type.ENTERTAINMENT;
            case 4 -> Type.OTHER;
            case 5 -> Type.ALL;
            default -> throw new IllegalStateException("Type does not exist");
        };
    }

    private static void printPurchaseList(Type type) {
        if (purchases.get(type) != null) {
            for (Purchase purchase : purchases.get(type)) {
                System.out.println(purchase);
            }
        }
    }

    private static void addPurchase(Type type) {
        System.out.println("Enter purchase name:");
        String name = "";
        while (name.isEmpty()) {
            name = scanner.nextLine();
        }
        System.out.println("Enter its price:");
        String priceString = scanner.nextLine();
        System.out.println("Purchase was added!");

        DollarAmount price = new DollarAmount(priceString);

        Purchase purchase = new Purchase(type, name, price);
        purchases.add(type, purchase);

        balance.minus(price);
    }


    private static void printBalance() {
        System.out.println("Balance: "+balance.toString());
    }

    private static void save() {
        if (fileSaver.save()) {
            System.out.println("Purchases were saved!");
        } else {
            System.out.println("Purchases were not saved!");
        }
    }

    private static void load() {
        if (fileSaver.load()) {
            System.out.println("Purchases were loaded!");
        }
    }

    private static void analyze() {
    }
    

    private static void printActionMenu() {
        System.out.print(
                """
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit
                """
        );
    }

    private static void printPurchaseTypeInputMenu() {
        System.out.print(
                """
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back
                """
        );
    }

    private static void printPurchaseTypeOutputMenu() {
        System.out.print(
                """
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back
                """
        );
    }
}


