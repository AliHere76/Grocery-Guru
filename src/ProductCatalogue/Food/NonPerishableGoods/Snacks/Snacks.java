package ProductCatalogue.Food.NonPerishableGoods.Snacks;

import ProductCatalogue.Food.NonPerishableGoods.NonPerishableGoods;

public abstract class Snacks extends NonPerishableGoods {
    private String brandName;
    public Snacks() {
    }

    public Snacks(int price, String weightUnits, int quantity, boolean status, String brandName) {
        super(price, weightUnits, quantity,"snaks", status,brandName);
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
