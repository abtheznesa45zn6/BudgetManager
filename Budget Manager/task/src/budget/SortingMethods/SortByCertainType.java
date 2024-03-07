package budget.SortingMethods;

import budget.Purchase;
import budget.PurchaseList;
import budget.Type;

import java.util.ArrayList;
import java.util.Map;

public class SortByCertainType implements SortingMethod {

    @Override
    public void print(PurchaseList purchaseList) {
        Type type = purchaseList.getSortByType();
        Map<Type, ArrayList<Purchase>> purchases = purchaseList.getPurchases();

        if (purchaseList.isEmpty(type)) {
            System.out.println("The purchase list is empty");
        } else {
            System.out.println(type.getTypeName()+":");
            for (Purchase purchase : purchases.get(type)) {
                System.out.println(purchase);
            }
            System.out.println("Total sum: " + purchaseList.getSumOfPurchaseOfType(type));
        }
    }
}
