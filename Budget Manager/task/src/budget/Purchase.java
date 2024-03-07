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
}
