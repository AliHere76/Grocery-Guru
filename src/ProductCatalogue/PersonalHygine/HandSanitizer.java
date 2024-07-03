package ProductCatalogue.PersonalHygine;

public class HandSanitizer extends PersonalHygiene {

    public HandSanitizer() {
    }

    public HandSanitizer(int price, String weightUnits, int quantity, boolean status, String brand) {
        super(price, weightUnits, quantity,"handSanitizer",status, brand);
    }


}
