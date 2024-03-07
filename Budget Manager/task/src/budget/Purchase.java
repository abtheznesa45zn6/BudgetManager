package budget;

public class Purchase {
    Type type;
    String name;
    DollarAmount price;

    public Purchase(Type type, String name, DollarAmount price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public DollarAmount getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name.concat(" ").concat(price.toString());
    }
}
