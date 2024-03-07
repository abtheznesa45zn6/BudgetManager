package budget;

public enum Type {
    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other"),
    ALL("All");

    private final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}