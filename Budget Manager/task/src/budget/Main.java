package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalCents = 0;

        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            System.out.print(line);

            String number = line.split("\\$")[1];
            String cents = number.replace(".", "");

            totalCents += Integer.parseInt(cents);

            System.out.println();
        }

        System.out.println();
        System.out.print("Total: $");
        System.out.print(totalCents / 100);
        System.out.print(".");
        System.out.print(totalCents % 100);
        System.out.println();
    }
}
