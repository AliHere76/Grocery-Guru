package ProductCatalogue.PersonalHygine;

public class Shampoo extends PersonalHygiene {
    public Shampoo() {
    }

    public Shampoo(int price, String weightUnits, int quantity, boolean status, String brand) {
        super(price, weightUnits, quantity,"shampoo", status, brand);
    }


}
