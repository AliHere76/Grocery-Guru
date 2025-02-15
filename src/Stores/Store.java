/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Stores;

/**
 *
 * @author ASUS TUFF
 */
import ProductCatalogue.Product;
import Users.Manager;
import ProductCatalogueManagement.ProductCatalogueManagement;
import Users.UserWriter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Store implements Serializable{
    private HashMap<Product,Boolean> product;
    private Manager manager;
    private String location;
    ArrayList<Manager> managers;
    
    public Store(Manager manager, String location) {
        ProductCatalogueManagement productCatalogueManagement=new ProductCatalogueManagement("ProductCatalogue");
        this.product = productCatalogueManagement.getHashMap();
        managers= UserWriter.LoadArrayList(managers);
        if (managers==null){
            managers = new ArrayList<>();
        }
        managers.add(manager);
        UserWriter.WriteToFile(managers);
        this.manager = manager;
        this.location=location;
    }

    public Manager getManager() {return manager;}
    public void setManager(Manager manager) {this.manager = manager;}

    public HashMap<Product, Boolean> getProduct() {return product;}
    public void setProduct(HashMap<Product, Boolean> product) {this.product = product;}

    public String getLocation() {return location;}
    public void setLocation() {this.location = manager.getCity();}

    public void setLocation(String location) {this.location = location;}
    
    public void createStore(){
        ProductCatalogueManagement.WriteHashMapToFile(product,location);
    }

//    public static void main(String[] args) {
//        Store lahore=new Store(new Manager("78","Sohail","sss","09876","email","123",""),"lahore");
//        Store kharian=new Store(new Manager("78","Sohail","sss","09876","email","123","pajsohas"),"Kharian");
//        ProductCatalogueManagement.viewHashmapFromFile("");
//        lahore.createStore();
//    }
}