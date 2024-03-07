package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSaver {
    final String fileName;
    final DollarAmount balance;
    final PurchaseList purchases;

    public FileSaver(DollarAmount balance, PurchaseList purchaseList) {
        this.fileName = "purchases.txt";
        this.balance = balance;
        this.purchases = purchaseList;
    }
    
    public boolean save() {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write("BALANCE"+"\n");
            writer.write(balance.getInCent()+"\n"); //"$785.64" is written as "78564" in one line

            for (Type type : purchases.keySet()) {
                writer.write("TYPE"+"\n");
                writer.write(type.getTypeName()+"\n");
                for (Purchase purchase : purchases.get(type)) {
                    writer.write("PURCHASE"+"\n");
                    writer.write(purchase.getName()+"\n");
                    writer.write(purchase.getPriceInCent()+"\n");
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean load() {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (scanner.nextLine().equals("BALANCE")) {
                balance.setCent(Integer.parseInt(scanner.nextLine()));

                purchases.clear();
                Type type = null;
                while (scanner.hasNext()) {
                    if (scanner.nextLine().equals("TYPE")) {
                        type = getTypeFromString(scanner.nextLine());

                    } else {
                        String name = scanner.nextLine();
                        DollarAmount price = new DollarAmount(Integer.parseInt(scanner.nextLine()));
                        Purchase purchase = new Purchase(type, name, price);
                        purchases.add(type, purchase);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + fileName);
            return false;
        }
        return true;
    }

    private static Type getTypeFromString(String string) {
        return switch (string) {
            case "Food" -> Type.FOOD;
            case "Clothes" -> Type.CLOTHES;
            case "Entertainment" -> Type.ENTERTAINMENT;
            case "Other" -> Type.OTHER;
            default -> throw new IllegalArgumentException("Unknown type: " + string);
        };
    }
}
