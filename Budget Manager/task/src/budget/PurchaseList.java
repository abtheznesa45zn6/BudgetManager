package budget;

import budget.SortingMethods.All;
import budget.SortingMethods.SortingMethod;

import java.util.*;

public class PurchaseList {
    Map<Type, ArrayList<Purchase>> purchases = new HashMap<>();
    private SortingMethod sortingMethod = new All();

    public PurchaseList() {
    }

    public Map<Type, ArrayList<Purchase>> getPurchases() {
        return purchases;
    }

    void clear() {
        this.purchases = new HashMap<>();
    }

    boolean isEmpty() {
        return purchases.isEmpty();
    }

    boolean isEmpty(Type type) {
        return purchases.get(type) == null;
    }

    ArrayList<Purchase> get(Type type) {
        return purchases.get(type);
    }

    void add(Type type, Purchase purchase) {
        purchases.computeIfAbsent(type, k -> new ArrayList<>()).add(purchase);
    }

    Set<Type> keySet() {
        return purchases.keySet();
    }

    public DollarAmount getSumOfAllPurchases() {
        return purchases.values()
                .stream()
                .flatMap(ArrayList::stream)
                .map(Purchase::getPrice)
                .reduce(new DollarAmount(0), (acc, price) -> {
                    acc.plus(price);
                    return acc;
                });
    }

    public DollarAmount getSumOfPurchaseOfType(Type type) {
        return purchases.get(type)
                .stream()
                .map(Purchase::getPrice)
                .reduce(new DollarAmount(0), (acc, price) -> {
                    acc.plus(price);
                    return acc;
                });
    }

    void print() {
        sortingMethod.print(this);
    }

    void print(Type type) {
        if (isEmpty(type)) {
            System.out.println("The purchase list is empty");
        } else {
            for (Purchase purchase : purchases.get(type)) {
                System.out.println(purchase);
            }
            System.out.println("Total sum: " + getSumOfPurchaseOfType(type));
        }
    }

    public void setSortingMethod(All sortingMethod) {
        this.sortingMethod = sortingMethod;
    }
}
