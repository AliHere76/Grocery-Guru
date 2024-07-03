package ProductCatalogue.Food.NonPerishableGoods.Grains;

public class Flour extends Grains{
    public Flour() {
    }

    public Flour(int price, String weightUnits, int quantity, boolean status,String brand) {
        super(price, weightUnits, quantity, status,brand);
    }
}
