/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Users;

/**
 *
 * @author ASUS TUFF
 */

import Stores.Store;

import javax.swing.*;
import java.io.Serializable;
import java.util.*;
import java.io.*;

public class UserWriter implements Serializable {

    private static String path="src\\Database\\";
    private static ArrayList<Person> arrayList;

    public UserWriter() {}

    public UserWriter(Person person) {
        arrayList = (ArrayList<Person>) ArraylistAccToType(person);
    }

    public static ArrayList<Person> getArrayList() {return arrayList;}

    //==Returning loaded data Arraylist According to the type of object we passed in constructor==
    public static ArrayList<?> ArraylistAccToType(Person person) {
        if (person instanceof Admin) {
            ArrayList<Admin> admins = new ArrayList<>();
            return admins;
        } else if (person instanceof Customer) {
            ArrayList<Customer> customer = new ArrayList<>();
            return customer;
        } else if (person instanceof Manager) {
            ArrayList<Manager> manager = new ArrayList<>();
            return manager;
        }
        return null;
    }

    // Loading Person type file data into the ArrayList
    public static ArrayList<Person> LoadArrayList(ArrayList<Person> users, String type) {
        File f = new File(path + type + "Data.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            else {
                try {
                    FileInputStream in = new FileInputStream(path + type + "Data.ser");
                    ObjectInputStream oin = new ObjectInputStream(in);

                    users = (ArrayList<Person>) oin.readObject();

                    in.close();
                    oin.close();
                    return users;
                } 
                catch (EOFException e) {} 
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "System under maintenance");
                }
            }
        } catch (Exception e) {
            System.out.println("File Not Created");
        }
        return null;
    }
    
    // Loading Manager type file data into the ArrayList
    public static ArrayList<Manager> LoadArrayList(ArrayList<Manager> managers) {
        File f = new File(path +  "managerData.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                try {
                    FileInputStream in = new FileInputStream(f);
                    ObjectInputStream oin = new ObjectInputStream(in);

                    managers = (ArrayList<Manager>) oin.readObject();

                    in.close();
                    oin.close();
                    return managers;
                }
                catch (EOFException ex) {}
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "System under maintenance.");
                }
            }
        } catch (Exception e) {
            System.out.println("File Not Created");
        }
        return null;
    }
    
    // Loading Customer type file data into the ArrayList
    public static ArrayList<Customer> LoadArrayList1(ArrayList<Customer> customers) {
        File f = new File(path + "customerData.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            else {
                try {
                    FileInputStream in = new FileInputStream(f);
                    ObjectInputStream oin = new ObjectInputStream(in);

                    customers = (ArrayList<Customer>) oin.readObject();

                    in.close();
                    oin.close();
                    return customers;
                } 
                catch (EOFException e) {}
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "System under maintenance.");
                }
            }
        } catch (Exception e) {
            System.out.println("File Not Created");
        }
        return null;
    }

    // Loading Store type file data into the ArrayList
    public static ArrayList<Store> LoadStoresList(ArrayList<Store> stores) {
        File f = new File(path + "Stores.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            else {
                try {
                    FileInputStream in = new FileInputStream(f);
                    ObjectInputStream oin = new ObjectInputStream(in);

                    stores = (ArrayList<Store>) oin.readObject();

                    in.close();
                    oin.close();
                    return stores;
                }
                catch (EOFException e) {} 
                catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "System under maintenance.");
                }
            }
        } catch (Exception e) {
            System.out.println("File Not Created");
        }
        return null;
    }

    // Writing Person type data into File of the specific type of person 
    public static void WriteToFile(ArrayList<Person> person, String personType) {
        try {
            FileOutputStream out = new FileOutputStream(path + personType + "Data.ser");
            ObjectOutputStream Oout = new ObjectOutputStream(out);

            Oout.writeObject(person);
            
            out.close();
            Oout.flush();
            Oout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Writing Manager type data into Manager's File
    public static void WriteToFile(ArrayList<Manager> managers) {
        try {
            FileOutputStream out = new FileOutputStream(path + "managerData.ser");
            ObjectOutputStream Oout = new ObjectOutputStream(out);

            Oout.writeObject(managers);
            
            out.close();
            Oout.flush();
            Oout.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Writing Customer type data into Customer's File
    public static void WriteCustomerToFile(ArrayList<Customer> customer) {
        try {
            FileOutputStream out = new FileOutputStream(path + "customerData.ser");
            ObjectOutputStream Oout = new ObjectOutputStream(out);

            Oout.writeObject(customer);
            
            out.close();
            Oout.flush();
            Oout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Writing Store type data into Store's File
    public static void WriteToFileStores(ArrayList<Store> stores) {
        try {
            FileOutputStream out = new FileOutputStream(path + "Stores.ser");
            ObjectOutputStream Oout = new ObjectOutputStream(out);

            Oout.writeObject(stores);
            
            out.close();
            Oout.flush();
            Oout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //==================================== Main Method just for checking ============================================
//    public static void main(String[] args) {
//        Admin admin=new Admin("Muhamad","Muzzammil","admin","muzzammil@gmail.com","admin","Faisalabad",new DateOfBirth("20","October","2003"),"123456790123");
//       Admin admin1=new Admin("Muhamad","Muzzammil","muzzammil2","muzzammil@712.gmail.com","sndajda","Faisalabad",new DateOfBirth("20","October","2003"));
//       Customer customer=new Customer("Muhamad","Muzzammil","customer","muzzammil@712.gmail.com","1234","Faisalabad",new DateOfBirth("20","October","2003"),"98765");
//       Customer customer1=new Customer("Muhamad","Muzzammil","customer1","muzzammil@gmail.com","1234","Faisalabad",new DateOfBirth("20","October","2003"),"12345678");
//       Admin admin=new Admin("Muhammad","Ali","Ali","mu.ali.7645@gmail.com","admin","Faisalabad",new DateOfBirth("3","October","2003"),"3310270234021");
//       Manager manager=new Manager("Muhamad","Ali","manager","Ali@712.gmail.com","manager","islamabad",new DateOfBirth("19","October","2005"),"2","200","123456789");
//        Manager manager2 = new Manager("Muhamad", "Sohail", "m1", "Sohail@712.gmail.com", "1", "November", new DateOfBirth("22", "October", "2006"), "3", "500", "123456789");
//

//        arrayList = LoadArrayList(arrayList,"admin");
//
//        if (arrayList == null) {
//            arrayList = new ArrayList<>();
//        }
//
//        arrayList.add(admin);
//        WriteToFile(arrayList, "admin");
//        readFromFile("admin");


//        MyStore st=new MyStore(new Manager("2","Sohail","asdf","asdww","wwedwe","adwed","Kharian"),"Kharian");

//        ArrayList<MyStore> sto=new ArrayList<>();
//        sto.add(st);

//        WriteToFileStores(sto);
//    }
}




