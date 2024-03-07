package budget;

import java.util.*;

public class PurchaseList {
    Map<Type, ArrayList<Purchase>> purchases = new HashMap<>();

    public PurchaseList() {
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

    public Set<Type> keySet() {
        return purchases.keySet();
    }

    DollarAmount getSumOfAllPurchases() {
        return purchases.values()
                .stream()
                .flatMap(ArrayList::stream)
                .map(Purchase::getPrice)
                .reduce(new DollarAmount(0), (acc, price) -> {
                    acc.plus(price);
                    return acc;
                });
    }

    DollarAmount getSumOfPurchaseOfType(Type type) {
        return purchases.get(type)
                .stream()
                .map(Purchase::getPrice)
                .reduce(new DollarAmount(0), (acc, price) -> {
                    acc.plus(price);
                    return acc;
                });
    }
}
