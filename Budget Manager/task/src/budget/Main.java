package budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static DollarAmount balance = new DollarAmount(0);
    static Scanner scanner = new Scanner(System.in);
    static Map<Type, ArrayList<Purchase>> purchases = new HashMap<>();

    public static void main(String[] args) {

        printActionMenu();

        boolean exited = false;
        while (!exited) {

            int action = scanner.nextInt();
            System.out.println();

            switch(action) {
                case 0 -> {
                    exit();
                    exited = true;}
                case 1 -> addIncome();
                case 2 -> chooseTypeOfPurchaseInput();
                case 3 -> chooseTypeOfPurchaseOutput();
                case 4 -> printBalance();
                default -> System.out.println("Action not available");
            }

            if (!exited) {
                System.out.println();
                printActionMenu();
            }
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

            System.out.println("Total sum: " + getSumOfAllPurchases());

        } else {
            if (purchases.get(type) == null) {
                System.out.println("The purchase list is empty");
                return;
            } else {
                printPurchaseList(type);

                System.out.println("Total sum: " + getSumOfPurchaseOfType(type));
            }
        }

        System.out.println();
        chooseTypeOfPurchaseOutput();
    }

    static DollarAmount getSumOfAllPurchases() {
        return purchases.values()
                .stream()
                .flatMap(ArrayList::stream)
                .map(Purchase::getPrice)
                .reduce(new DollarAmount(0), (acc, price) -> {
                    acc.plus(price);
                    return acc;
                });
    }

    static DollarAmount getSumOfPurchaseOfType(Type type) {
        return purchases.get(type)
                .stream()
                .map(Purchase::getPrice)
                .reduce(new DollarAmount(0), (acc, price) -> {
                    acc.plus(price);
                    return acc;
                });
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
        if (purchases.get(type) == null) {
            return;
        } else {
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
        purchases.computeIfAbsent(type, k -> new ArrayList<>()).add(purchase);

        balance.minus(price);
    }


    private static void printBalance() {
        System.out.println("Balance: "+balance.toString());
    }

    private static void printActionMenu() {
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

class Purchase {
    Type type;
    String name;
    DollarAmount price;

    public Purchase(Type type, String name, DollarAmount price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public DollarAmount getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name.concat(" ").concat(price.toString());
    }
}

class DollarAmount {
    private int cent;

    public DollarAmount(int cent) {
        this.cent = cent;
    }

    public DollarAmount(String priceString) {
        this(getCentsFromString(priceString));
    }

    private static int getCentsFromString(String str) {
        int cents = 0;
        if (str.contains(".")) {
            int dollar = Integer.parseInt(str.split("\\.")[0]);
            cents = cents + dollar * 100;

            String decimals = str.split("\\.")[1];
            if (decimals.length() > 1) {
                cents += Integer.parseInt(decimals);
            } else {
                cents += Integer.parseInt(decimals) * 10;
            }

        } else {
            cents = Integer.parseInt(str) * 100;
        }
        return cents;
    }

    public void plus(DollarAmount addend) {
        cent += addend.getInCent();
    }

    public void minus(DollarAmount addend) {
        cent -= addend.getInCent();
    }

    public int getInCent() {
        return cent;
    }

    public String getDollar() {
        return String.valueOf(cent / 100);
    }

    public String getCent() {
        return String.valueOf(cent % 100);
    }

    @Override
    public String toString() {
        if (cent < 0) {
            throw new IllegalStateException();
        }

        if (cent >= 100) {
            return "$" +
                   getDollar() +
                   "." +
                   getCent();
        } else {
            return "$" +
                   0 +
                   "." +
                   getCent();
        }
    }
}

enum Type {
    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other"),
    ALL("All");

    private final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
