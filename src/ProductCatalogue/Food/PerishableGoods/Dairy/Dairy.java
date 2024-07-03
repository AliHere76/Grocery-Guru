package ProductCatalogue.Food.PerishableGoods.Dairy;

import ProductCatalogue.Food.PerishableGoods.PerishableGoods;

public abstract class Dairy extends PerishableGoods {
    public Dairy() {
    }

    public Dairy(int price, String weightUnits, int quantity, boolean status,String brand) {
        super(price, weightUnits, quantity, "dairy", status,brand);
    }

}
