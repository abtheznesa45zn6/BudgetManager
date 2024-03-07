package budget.SortingMethods;

import budget.Purchase;
import budget.PurchaseList;
import budget.Type;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class SortAll implements SortingMethod {

    @Override
    public void print(PurchaseList purchaseList) {
        Map<Type, ArrayList<Purchase>> purchases = purchaseList.getPurchases();

        if (purchases.isEmpty()) {
            System.out.println("The purchase list is empty!");
        } else {
            System.out.println("All:");
            purchases.values()
                    .stream()
                    .flatMap(ArrayList::stream)
                    .sorted(Comparator.reverseOrder())
                    .forEach(System.out::println);
            System.out.println("Total: " + purchaseList.getSumOfAllPurchases());
        }
    }
}
