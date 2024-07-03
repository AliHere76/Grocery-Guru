package ProductCatalogueManagement;

import ProductCatalogue.Product;
import Stores.Store;
import Users.UserWriter;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductCatalogueManagement implements Serializable {
    private static String path="src\\Database\\";
    protected static HashMap<Product, Boolean> hashMap = new HashMap<>();

    public ProductCatalogueManagement() {}
    public ProductCatalogueManagement(String storeLocation) {
        LoadDataIntoHashMap(storeLocation);
    }

    public void setHashMap(HashMap<Product, Boolean> hashMap) {this.hashMap = hashMap;}
    public HashMap<Product, Boolean> getHashMap() {return hashMap;}

    // Method to write the product Catalogue for a store in the file
    public static void WriteHashMapToFile(HashMap<Product, Boolean> productSet, String fileName) {
        File f = new File(path + fileName + "Store.ser");
        try {
            FileOutputStream out = new FileOutputStream(f);
            ObjectOutputStream Oout = new ObjectOutputStream(out);

            Oout.writeObject(productSet);
            
            out.close();
            Oout.flush();
            Oout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to view the Product Catalogue for a store from a file
    public static void viewHashmapFromFile(String storeLocation) {
        try {
            FileInputStream fin = new FileInputStream(path + storeLocation + "Store.ser");
            ObjectInputStream oin = new ObjectInputStream(fin);

            hashMap = (HashMap<Product, Boolean>) oin.readObject();
//            int count = 0;
//            for (Map.Entry<Product, Boolean> product : hashMap.entrySet()) {
//                count++;
//                System.out.println(product.getKey().toString());
//            }
//            System.out.println("============================Count ==============================" + count);
//            System.out.println("=============================End of Loop========================");

            fin.close();
            oin.close();
        } catch (Exception ex) {
            System.out.println("File with This Name Not exist");
        }
    }

    // Method to store data of Products of a specific store into the HashMap 
    public static HashMap<Product, Boolean> LoadDataIntoHashMap(String storeLocation) {
        File f = new File(path + storeLocation + "Store.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                try {
                    FileInputStream in = new FileInputStream(f);
                    ObjectInputStream oin = new ObjectInputStream(in);

                    HashMap<Product, Boolean> tempHashMap = (HashMap<Product, Boolean>) oin.readObject();
                    hashMap.clear();
                    hashMap.putAll(tempHashMap);

                    in.close();
                    oin.close();
                    return hashMap;
                } catch (EOFException e) {}
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "System under Maintenance.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Method to Update an item's quantity after buying
    public static void updateItemQuantityAfterBuy(String productBrand, String storeLocation, int quantity) {
        for (Map.Entry<Product, Boolean> product : hashMap.entrySet()) {
            if (product.getKey().getBrand().equalsIgnoreCase(productBrand)) {
                product.getKey().setQuantity(product.getKey().getQuantity() - quantity);
                hashMap.put(product.getKey(), product.getKey().isStatus());
                WriteHashMapToFile(hashMap, storeLocation);
                break;
            }
        }
    }

    // Method to Update a Product's Quantity / Price
    public static void updateItemFromFile(String productBrand, String storeLocation, int choice) {
        boolean found = false;
        for (Map.Entry<Product, Boolean> product : hashMap.entrySet()) {
            if (product.getKey().getBrand().equalsIgnoreCase(productBrand)) {
                found = true;
                switch (choice) {
                    case 1: {
                        String quantity = JOptionPane.showInputDialog(null, "Enter new quantity of product");
                        while (quantity == null) {
                            quantity = JOptionPane.showInputDialog(null, "Invalid Input!\nEnter new quantity of product");
                        }
                        product.getKey().setQuantity(Integer.parseInt(quantity));
                        hashMap.put(product.getKey(), product.getKey().isStatus());
                        WriteHashMapToFile(hashMap, storeLocation);
                        break;
                    }
                    case 2: {
                        String price = JOptionPane.showInputDialog(null, "Enter new Price");
                        while (price == null) {
                            price = JOptionPane.showInputDialog(null, "Invalid Input!\nEnter new Price");
                        }
                        product.getKey().setPrice(Integer.parseInt(price));
                        hashMap.put(product.getKey(), product.getKey().isStatus());
                        WriteHashMapToFile(hashMap, storeLocation);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
        if (found == false) {
            JOptionPane.showMessageDialog(null, "Product not Found");
        } else {
            JOptionPane.showMessageDialog(null, "Updated Successfully");
        }
    }

    // Method to Update a Product's Status for all Stores
    public static void updateStatus(String brandName, String storeLocation) {
        boolean found = false;
        boolean statusUpdate;
        for (Map.Entry<Product, Boolean> product : hashMap.entrySet()) {
            if (product.getKey().getBrand().equalsIgnoreCase(brandName)) {
                found = true;
                Object[] options = {"Set True", "Set False"};
                int result = JOptionPane.showOptionDialog(null,
                        "Choose status:", "Setting Product Status",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                if (result == 0) {
                    statusUpdate = true;
                } else if (result == 1) {
                    statusUpdate = false;
                } else {
                    JOptionPane.showMessageDialog(null, "No option was selected");
                    return;
                }
                product.getKey().setStatus(statusUpdate);
                hashMap.put(product.getKey(), product.getKey().isStatus());
                break;
            }
        }
        if (found == false) {
            JOptionPane.showMessageDialog(null, "Product Not Found ");
        }
        else {
            WriteHashMapToFile(hashMap, "ProductCatalogue");
            ArrayList<Store> stores = new ArrayList<>();
            stores= UserWriter.LoadStoresList(stores);
            for (Store store  : stores){
                ProductCatalogueManagement.WriteHashMapToFile(hashMap, store.getLocation());
            }
        }
    }

    // Method to remove a product for a Specific Store
    public static void removeProductFromStoreFile(String productBrand, String storeLocation) {
        boolean found = false;
        for (Map.Entry<Product, Boolean> product : hashMap.entrySet()) {
            if (product.getKey().getBrand().equalsIgnoreCase(productBrand)) {
                found = true;
                hashMap.remove(product.getKey());
                break;
            }
        }
        if (found == false) {
            JOptionPane.showMessageDialog(null, "Product not found!");
        } else {
            WriteHashMapToFile(hashMap, storeLocation);
        }
    }

    // Method to get a specific Product from a specific Store
    public static Product SearchItem(String brandName, String storeLocation) {
        try {
            FileInputStream fin = new FileInputStream(path + storeLocation + "Store.ser");
            ObjectInputStream oin = new ObjectInputStream(fin);

            hashMap = (HashMap<Product, Boolean>) oin.readObject();
            for (Map.Entry<Product, Boolean> product : hashMap.entrySet()) {
                if (product.getKey().getBrand().equalsIgnoreCase(brandName)) {
//                    System.out.println(product.toString());
                    return product.getKey();
                }
            }
            fin.close();
            oin.close();
        } catch (EOFException e) {
            System.out.println("End of file reached!");
        } catch (Exception ex) {
            System.out.println("File with This Name Not exist");
        }
        return null;
    }

    // Method to make the Product Catalogue for a new Store
    public static void makeNewStore() {
        WritingProductCatalogueForNewStore b = new WritingProductCatalogueForNewStore();
        hashMap = b.getHashMap();
        WriteHashMapToFile(hashMap, "ProductCatalogue");
    }

//    public static void main(String[] args) {
//        ProductCatalogueManagement pr=new ProductCatalogueManagement();
//        WritingProdcutCatalogueToNewStore writingProdcutCatalogueToNewStore=new WritingProdcutCatalogueToNewStore();
////        ProductCatalogueManagement.LoadDataIntoHashMap("Productcatalogue");
//        ProductCatalogueManagement.makeNewStore();
////        ProductCatalogueManagement.SearchItem("Rice","Productcatalogue");
////        HashMap<Product,Boolean> hashMap1=writingProdcutCatalogueToNewStore.getHashMap();
//
   //     ProductCatalogueManagement.viewHashmapFromFile("ProductCatalogue");
//
//
//    }

}
