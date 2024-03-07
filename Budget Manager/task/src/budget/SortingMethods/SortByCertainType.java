package budget.SortingMethods;

import budget.Purchase;
import budget.PurchaseList;
import budget.Type;

import java.util.Comparator;
public class SortByCertainType implements SortingMethod {

    @Override
    public void print(PurchaseList purchaseList) {
        Type type = purchaseList.getSortByType();
        var purchases = purchaseList.getPurchases().get(type);

        if (purchaseList.isEmpty(type)) {
            System.out.println("The purchase list is empty");
        } else {
            System.out.println(type.getTypeName()+":");

            if (purchases.isEmpty()) {
                System.out.println("The purchase list is empty!");
            } else {
                purchases
                        .stream()
                        .sorted(Comparator.comparingInt(Purchase::getPriceInCent).reversed())
                        .forEach(System.out::println);
            }
            System.out.println("Total sum: " + purchaseList.getSumOfPurchaseOfType(type));
        }
    }
}
