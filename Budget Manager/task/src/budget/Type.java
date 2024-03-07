package budget;

import java.util.Set;

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

    public static Type[] getTypesWithoutAll() {
        return new Type[]{FOOD, CLOTHES, ENTERTAINMENT, OTHER};
    }
}