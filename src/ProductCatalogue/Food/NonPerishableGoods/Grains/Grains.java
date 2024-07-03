package ProductCatalogue.Food.NonPerishableGoods.Grains;

import ProductCatalogue.Food.NonPerishableGoods.NonPerishableGoods;

public abstract class Grains extends NonPerishableGoods {
    public Grains() {
    }

    public Grains(int price, String weightUnits, int quantity, boolean status,String brand ) {
        super(price, weightUnits, quantity, "Grain", status,brand);
    }
}
