package budget;

public class Purchase implements Comparable<Purchase> {
    Type type;
    String name;
    DollarAmount price;

    public Purchase(Type type, String name, DollarAmount price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public Type getType() {return type;}

    public String getName() {
        return name;
    }

    public DollarAmount getPrice() {
        return price;
    }

    public int getPriceInCent() {
        return getPrice().getInCent();
    }

    @Override
    public String toString() {
        return name.concat(" ").concat(price.toString());
    }

    @Override
    public int compareTo(Purchase o) {
        int result = Double.compare(this.getPriceInCent(), o.getPriceInCent());
        if(result == 0) {
            result = this.getName().compareToIgnoreCase(o.getName());
        }
        return result;
    }
}
