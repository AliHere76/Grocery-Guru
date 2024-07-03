/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

/**
 *
 * @author ASUS TUFF
 */
import ProductCatalogue.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<Product> products=new ArrayList<>();
    private double total;
    
    public double getTotal() {return total;}
    public ArrayList<Product> getProducts() {return products;}
    public void addProduct(Product p){
        products.add(p);
    }
    public void removeProduct(Product p){
        products.remove(p);
    }

    public Cart(){total=calculateTotal();}

    public double calculateTotal(){
        double total=0;
        for (Product product: products){
            total+=(product.getPrice())* product.getQuantity();
        }
        return total;
    }
}