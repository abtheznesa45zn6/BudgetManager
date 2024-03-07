package budget;

public class DollarAmount {
    private int cent;

    public DollarAmount(int cent) {
        this.cent = cent;
    }

    public DollarAmount(String priceString) {
        this(getCentsFromString(priceString));
    }

    private static int getCentsFromString(String str) {
        int cents = 0;
        if (str.contains(".")) {
            int dollar = Integer.parseInt(str.split("\\.")[0]);
            cents = cents + dollar * 100;

            String decimals = str.split("\\.")[1];
            if (decimals.length() > 1) {
                cents += Integer.parseInt(decimals);
            } else {
                cents += Integer.parseInt(decimals) * 10;
            }

        } else {
            cents = Integer.parseInt(str) * 100;
        }
        return cents;
    }

    void setCent(int cent) {
        this.cent = cent;
    }

    public void plus(DollarAmount addend) {
        cent += addend.getInCent();
    }

    public void minus(DollarAmount addend) {
        cent -= addend.getInCent();
    }

    public int getInCent() {
        return cent;
    }

    private Integer getDollar() {
        return cent / 100;
    }

    private Integer getCent() {
        return cent % 100;
    }

    @Override
    public String toString() {
        if (cent < 0) {
            return "$-" +
                    getDollar()*-1 +
                    "." +
                    returnZeroIfNeeded() +
                    getCent()*-1;
        }

        if (cent >= 100) {
            return "$" +
                    getDollar() +
                    "." +
                    returnZeroIfNeeded() +
                    getCent();
        } else {
            return "$" +
                    0 +
                    "." +
                    returnZeroIfNeeded() +
                    getCent();
        }
    }

    String returnZeroIfNeeded() {
        if (getCent()<10) {return "0";}
        return "";
    }
}