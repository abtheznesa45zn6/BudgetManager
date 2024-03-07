package budget.SortingMethods;

import budget.PurchaseList;
import budget.Type;

public class SortByType implements SortingMethod {

    @Override
    public void print(PurchaseList purchaseList) {
        for (Type type : Type.getTypesWithoutAll()) {
            System.out.print(
                    type.getTypeName() +
                    " - " +
                    purchaseList.getSumOfPurchaseOfType(type)
                    );
        }
        System.out.println("Total sum: " + purchaseList.getSumOfAllPurchases());
    }
}
