package budget.SortingMethods;

import budget.Purchase;
import budget.PurchaseList;
import budget.Type;

import java.util.*;
import java.util.stream.Collectors;

public class SortByType implements SortingMethod {

    @Override
    public void print(PurchaseList purchaseList) {
        Map<Type, Long> purchases =
                purchaseList
                        .getPurchases()
                        .values()
                        .stream()
                        .flatMap(ArrayList::stream)
                        .collect(Collectors.groupingBy(
                                Purchase::getType,
                                Collectors.summingLong(Purchase::getPriceInCent))
                        );

        List<Map.Entry<Type, Long>> purchasesSorted = new ArrayList<>(purchases.entrySet());
        purchasesSorted.sort(Map.Entry.comparingByValue());

        System.out.println("Types:");
        for (Map.Entry<Type, Long> entry : purchasesSorted) {
            System.out.println(
                    entry.getKey().getTypeName() +
                    " - " +
                    purchaseList.getSumOfPurchaseOfType(entry.getKey())
                    );
        }

        Set<Type> remainingTypes = purchases.keySet();
        remainingTypes.remove(Type.values());

        for (Type type : remainingTypes) {
            System.out.println(
                    type.getTypeName() +
                            " - $0"
            );
        }

        System.out.println("Total sum: " + purchaseList.getSumOfAllPurchases());
    }
}
