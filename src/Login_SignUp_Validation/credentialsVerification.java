/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login_SignUp_Validation;

/**
 *
 * @author ASUS TUFF
 */
import Users.Person;
import Users.Admin;
import Users.Manager;
import Users.Customer;
import Users.UserWriter;
import Stores.Store;

import java.util.ArrayList;

public class credentialsVerification {
    private String username, password, type;
    private ArrayList<Person> users;
    private ArrayList<Manager> managers;
    private ArrayList<Store> stores;
    private Admin admin;
    private Manager manager;
    private Customer customer;

    public credentialsVerification(String type) {
        this.type = type;
        users = UserWriter.LoadArrayList(users, type);
    }

    // Constructor to check while Logging In
    public credentialsVerification(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
        users = UserWriter.LoadArrayList(users, type);
        if (type.equalsIgnoreCase("admin")) {
            this.admin = verfiyAdminLogin();
        }
        else if (type.equalsIgnoreCase("manager")) {
            this.manager = verfiyManagerLogin();
        }
        else if(type.equalsIgnoreCase("customer")){
            this.customer=verfiyCustomerLogin();
        }
    }

    public credentialsVerification() {
        managers = UserWriter.LoadArrayList(managers);
        stores = UserWriter.LoadStoresList(stores);
    }

    public Customer getCustomer() {return customer;}
    public Manager getManager() {return manager;}
    public Admin getAdmin() {return admin;}
    
    public boolean verfiyLogin() {
        if (users == null) {
            return false;
        }
        for (Person p : users) {
            if (p.getUsername().equals(username) || p.getEmail().equals(username)) {
                if (p.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    //======================================this function is used for manager Screen Management============================
    public Admin verfiyAdminLogin() {
        if (users == null) {
            return null;
        } else {
            for (Person admin1 : users) {
                if (admin1.getUsername().equals(username) || admin1.getEmail().equals(username)) {
                    if (admin1.getPassword().equals(password)) {
                        return (Admin) admin1;
                    }
                }
            }
        }
        return null;
    }

    public Manager verfiyManagerLogin() {
        if (users == null) {
            return null;
        } else {
            for (Person manager1 : users) {
                if (manager1.getUsername().equals(username) || manager1.getEmail().equals(username)) {
                    if (manager1.getPassword().equals(password)) {
                        return (Manager) manager1;
                    }
                }
            }
        }
        return null;
    }

    public Customer verfiyCustomerLogin() {
        if (users == null) {
            return null;
        } else {
            for (Person customer1 : users) {
                if (customer1.getUsername().equals(username) || customer1.getEmail().equals(username)) {
                    if (customer1.getPassword().equals(password)) {
                        return (Customer) customer1;
                    }
                }
            }
        }
        return null;
    }

    public boolean isUnique(String userData) {
        for (Person p : users) {
            if (p.getUsername().equals(userData)) {
                return false;
            } else if (p.getEmail().equals(userData)) {
                return false;
            } else if (p.getCNIC().equals(userData)) {
                return false;
            }
        }
        return true;
    }

    public boolean isUniqueManager(String id) {
        for (Manager m : managers) {
            if (m.getUsername().equals(id)) {
                return false;
            } else if (m.getEmail().equals(id)) {
                return false;
            } else if (m.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public boolean isUniqueStore(String name){
        for(Store s : stores){
            if(s.getLocation().toLowerCase().equals(name.toLowerCase())){
                return false;
            }
        }
        return true;
    }
}