package ProductCatalogue.Food.PerishableGoods.Meat;

import ProductCatalogue.Food.PerishableGoods.PerishableGoods;

public abstract class Meat extends PerishableGoods {
    public Meat() {
    }

    public Meat(int price, String weightUnits, int quantity, boolean status,String brand) {
        super(price, weightUnits, quantity, "meats", status,brand);
    }
}
