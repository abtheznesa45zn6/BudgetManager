package budget.SortingMethods;

import budget.Purchase;
import budget.PurchaseList;
import budget.Type;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class All implements SortingMethod {

    @Override
    public void print(PurchaseList purchaseList) {

        Map<Type, ArrayList<Purchase>> purchases = purchaseList.getPurchases();

        if (purchases.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }

        purchases.values()
                .stream()
                .flatMap(ArrayList::stream)
                .sorted(Comparator.comparing(p -> ((Purchase) p).getPrice().getInCent()).reversed())
                .forEach(System.out::println);

        System.out.println("Total sum: " + purchaseList.getSumOfAllPurchases());
    }
}
