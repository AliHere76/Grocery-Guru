/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/*
 *
 * @author ASUS TUFF
 */

//import Login_SignUp_Validation.credentialsVerification;
import ProductCatalogue.Product;
import ProductCatalogueManagement.ProductCatalogueManagement;
import Stores.Store;
import Users.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.EtchedBorder;

public class Admin_Panel extends JFrame implements ActionListener {

    // Main panel components
    JPanel leftPanel,rightPanel,adminFooter,storeNavBar,mainCenter;
    JButton manageManagers, manageCatalouge, viewAccount, managestores, logOut, food, houseHold, personalHygiene, setStatus,addStore, falseProducts;
    private JTable mangerTable, foodTable, houseHoldTable, personalHygieneTable;
    JScrollPane scrollPane;
    JTable viewTable, storeTable;
    
    public ArrayList<Manager> managerList;
    public ArrayList<Store> storeList;
    public ArrayList<Person> adminList;
    public HashMap<Product, Boolean> products;
    public HashMap<Product, Boolean> foodCategory;
    public HashMap<Product, Boolean> houseHoldCategory;
    public HashMap<Product, Boolean> personalHygieneCategory;

    DefaultTableModel model;

    public Admin_Panel() {
        super("Admin Panel");
        model = new DefaultTableModel();
        storeList = UserWriter.LoadStoresList(storeList);

        // Setting up the Frame
        setSize(820, 650);
        setIconImage(new ImageIcon("src\\Images\\Company_Logo.png").getImage());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(Color.decode("#0D1152"));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Setting necessary buttons for admin
        manageManagers = new Custom_Button("        Managers       ");
        manageCatalouge = new Custom_Button("Product Catalogue ");
        viewAccount = new Custom_Button("     Account Info     ");
        logOut = new Custom_Button("         Log Out        ");
        managestores = new Custom_Button("           Stores          ");
        addStore = new Custom_Button("Add Store");
        falseProducts = new Custom_Button("False Products");
        falseProducts.addActionListener(this);

        // Creating the MENU bar to put on top of Buttons
        JLabel text = new JLabel();
        text.setText("   MENU   ");
        text.setMaximumSize(new Dimension(162,30));
        text.setBorder(new EtchedBorder());
        text.setForeground(Color.WHITE);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        // Creating footer panel for holding buttons to manage Managers in Store Menu
        adminFooter = new JPanel();
        adminFooter.setBackground(Color.BLACK);
        adminFooter.setPreferredSize(new Dimension(640, 40));

        //Creating navBar for store
        storeNavBar = new JPanel();
        storeNavBar.setBackground(Color.BLACK);
        storeNavBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        storeNavBar.setPreferredSize(new Dimension(640, 40));
        storeNavBar.add(falseProducts);

        //Creating center panel for managing stores
        mainCenter = new JPanel();
        mainCenter.setLayout(new BorderLayout());
        mainCenter.setBackground(Color.WHITE);

        // Creating store Nav Bar menu for managing Stores
        food = new Custom_Button("Food");
        houseHold = new Custom_Button("House Holding Cleaning");
        personalHygiene = new Custom_Button("Personal Hygiene");
        setStatus = new Custom_Button("Set Status");

        //Creating tables
        mangerTable = new JTable();
        foodTable = new JTable();
        houseHoldTable = new JTable();
        personalHygieneTable = new JTable();
        viewTable = new JTable();
        storeTable = new JTable();

        // Adding buttons to Admin Footer
        adminFooter.add(setStatus);
        adminFooter.add(addStore);

        //Adding buttons to store NavBar
        storeNavBar.add(food);
        storeNavBar.add(houseHold);
        storeNavBar.add(personalHygiene);

        // Setting left panel of Frame
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.decode("#0D1152"));
        leftPanel.setPreferredSize(new Dimension(180, getHeight()));
        leftPanel.add(Box.createVerticalStrut(180));
        leftPanel.add(text);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(manageManagers);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(manageCatalouge);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(managestores);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(viewAccount);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(logOut);
        leftPanel.add(Box.createHorizontalStrut(20));
                
        //Setting right panel of Frame
        rightPanel = new JPanel();
        rightPanel.setSize(new Dimension(640,650));
        JLabel image = new JLabel(new ImageIcon("src\\Images\\Admin_Panel.png"));
        image.setSize(660,670);
        rightPanel.add(image);
        
        //initially setting footer panel, manager center, store Nav Bar and store Center visibility false
        adminFooter.setVisible(false);
        storeNavBar.setVisible(false);

        //Adding Action Listener to Admin panel buttons
        manageManagers.addActionListener(this);
        manageCatalouge.addActionListener(this);
        managestores.addActionListener(this);
        food.addActionListener(this);
        personalHygiene.addActionListener(this);
        houseHold.addActionListener(this);
        setStatus.addActionListener(this);
        addStore.addActionListener(this);
        viewAccount.addActionListener(this);
        logOut.addActionListener(this);

        // Adding components to Main Frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel);
        setVisible(true);
    }

    // Button Functions
    public void actionPerformed(ActionEvent e) {
        rightPanel.setVisible(false);
        adminFooter.setVisible(false);
        adminFooter.remove(addStore);
        adminFooter.remove(setStatus);

        String[][] rows = null;
        if(e.getSource() == falseProducts){ // Show False products
            model.setRowCount(0);
            model.setColumnCount(0);
            
            String[] coloumn = {"Category", "Price", "Weight Units", "Quantity", "Brand", "Status"};
            model.setColumnIdentifiers(coloumn);

            rows = null;
            if (fetchingProduct()) {
                rows = getTwoDArrayForFalseProduct();
            }

            adminFooter.setVisible(true);
            mainCenter.setVisible(true);
            adminFooter.add(setStatus);

            for (String[] row : rows){
                model.addRow(row);
            }

            foodTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(foodTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            mainCenter.add(scrollPane);
            add(storeNavBar, BorderLayout.NORTH);
            add(mainCenter, BorderLayout.CENTER);
            revalidate();            
        }
        if (e.getSource() == manageManagers) { // Shows managers and there stores
            mainCenter.setVisible(true);
            storeNavBar.setVisible(false);
            model.setRowCount(0);
            model.setColumnCount(0);
            
            if (loadManagersFromStores()) {
                rows = getManagersList();
            }
            
            String[] coloumns = {"ID", "Name", "Email", "Salary", "Manager At"};
            model.setColumnIdentifiers(coloumns);

            for (String[] row: rows){
                model.addRow(row);
            }
            
            mangerTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            mangerTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(mangerTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            mainCenter.add(scrollPane);
            add(mainCenter, BorderLayout.CENTER);
            revalidate();
        }

        else if (e.getSource() == manageCatalouge) { // Shows the Product Catalogue and it's functions
            mainCenter.setVisible(true);
            storeNavBar.setVisible(true);
            adminFooter.setVisible(true);
            adminFooter.add(setStatus);

            model.setRowCount(0);
            model.setColumnCount(0);

            String[] coloumn = {"Category", "Price", "Weight Units", "Quantity", "Brand", "Status"};
            model.setColumnIdentifiers(coloumn);
            
            rows = null;
            if (fetchingProduct()) {
                rows = getAllCatalogue();
            }

            for (String[] row : rows){
                model.addRow(row);
            }

            foodTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(foodTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            mainCenter.add(scrollPane);
            add(storeNavBar, BorderLayout.NORTH);
            add(mainCenter, BorderLayout.CENTER);
            add(adminFooter, BorderLayout.SOUTH);
            revalidate();
        } 
        
        else if (e.getSource() == food) { // Acts as a filter while showing Product Catalogue to show only Food items
            model.setRowCount(0);
            model.setColumnCount(0);
            
            String[] coloumn = {"Category", "Price", "Weight Units", "Quantity", "Brand", "Status"};
            model.setColumnIdentifiers(coloumn);

            rows = null;
            if (fetchingProduct()) {
                rows = getTwoDArrayForRequiredProduct("Food");
            }

            adminFooter.setVisible(true);
            mainCenter.setVisible(true);
            adminFooter.add(setStatus);

            for (String[] row : rows){
                model.addRow(row);
            }

            foodTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(foodTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            mainCenter.add(scrollPane);
            add(storeNavBar, BorderLayout.NORTH);
            add(mainCenter, BorderLayout.CENTER);
            revalidate();

        } 
        
        else if (e.getSource() == houseHold) { // Acts as a filter while showing Product Catalogue to show only Household items
            model.setRowCount(0);
            model.setColumnCount(0);
            
            adminFooter.setVisible(true);
            adminFooter.add(setStatus);
            
            String[] coloumn = {"Category", "Price", "Weight Units", "Quantity", "Brand", "Status"};
            model.setColumnIdentifiers(coloumn);


            rows = null;
            if (fetchingProduct()) {
                rows = getTwoDArrayForRequiredProduct("houseHoldCleaning");
            }

            for (String[] row : rows){
                model.addRow(row);
            }

            houseHoldTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            houseHoldTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(houseHoldTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            mainCenter.add(scrollPane);
            mainCenter.setVisible(true);
            add(storeNavBar, BorderLayout.NORTH);
            add(mainCenter, BorderLayout.CENTER);
            add(adminFooter, BorderLayout.SOUTH);
            revalidate();

        } 
        
        else if (e.getSource() == personalHygiene) { // Acts as a filter while showing Product Catalogue to show only Personal Hygiene items
            model.setRowCount(0);
            model.setColumnCount(0);

            adminFooter.setVisible(true);
            adminFooter.add(setStatus);

            String[] coloumn = {"Category", "Price", "Weight Units", "Quantity", "Brand", "Status"};
            model.setColumnIdentifiers(coloumn);


            rows = null;
            if (fetchingProduct()) {
                rows = getTwoDArrayForRequiredProduct("personalHygiene");
            }

            for (String[] row : rows){
                model.addRow(row);
            }

            personalHygieneTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            personalHygieneTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(personalHygieneTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            mainCenter.add(scrollPane);
            mainCenter.setVisible(true);
            add(storeNavBar, BorderLayout.NORTH);
            add(mainCenter, BorderLayout.CENTER);
            add(adminFooter, BorderLayout.SOUTH);
            revalidate();

        } 
        
        else if (e.getSource() == setStatus) { // Used to set the status of the product in the Product Catalogue
            adminFooter.setVisible(true);
            adminFooter.add(setStatus);

            String brandName = JOptionPane.showInputDialog(null, "Enter brand name:");
            if (brandName != null) {
                ProductCatalogueManagement.updateStatus(brandName, "Productcatalogue");
            }
        } 
        
        else if (e.getSource() == managestores) { // Used to View all Stores
            mainCenter.setVisible(true);
            adminFooter.setVisible(true);
            adminFooter.add(addStore);
            
            model.setColumnCount(0);
            model.setRowCount(0);
            
            String[] coloumn = {"Location", "Manager"};
            model.setColumnIdentifiers(coloumn);

            storeList = UserWriter.LoadStoresList(storeList);
            storeNavBar.setVisible(false);

            rows = null;
            if (fetchingStores()) {
                rows = getStoresList();
            }

            if(rows==null){
                rows=new String[0][];
            }
            
            for (String[] row : rows){
                model.addRow(row);
            }

            storeTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            storeTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(storeTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            mainCenter.add(scrollPane);
            add(mainCenter, BorderLayout.CENTER);
            add(adminFooter, BorderLayout.SOUTH);
            revalidate();
        } 
        
        else if (e.getSource() == addStore) { // Used to add a new Store
            adminFooter.setVisible(true);
            adminFooter.add(addStore);
            Manage_Manager manager = new Manage_Manager();
            manager.addManagerFrame();
        } 
        
        else if (e.getSource() == viewAccount) { // Used to view Admin's information
            storeNavBar.setVisible(false);
            model.setColumnCount(0);
            model.setRowCount(0);
            mainCenter.setVisible(true);

            String[] coloumn = {"First Name", "Last Name", "Username", "Email", "Password"};
            model.setColumnIdentifiers(coloumn);

            if (fetchingAdmins()) {
                rows = getTwoDArrayForAdmin();
            }

            for (String[] row : rows){
                model.addRow(row);
            }

            viewTable = new JTable(model);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            viewTable.getTableHeader().setDefaultRenderer(headerRenderer);
            scrollPane = new JScrollPane(viewTable);
            mainCenter.add(scrollPane);
            add(mainCenter, BorderLayout.CENTER);
            revalidate();
        } 
        
        else if (e.getSource() == logOut) { // Used to log out the admin
            dispose();
        }
    }
    
    // Stores all the Admins in the "adminList" ArrayList
    public boolean fetchingAdmins() {
        adminList = UserWriter.LoadArrayList(adminList, "admin");
        if (adminList == null) {
            return false;
        }
        return true;
    }

    // Stores all the Products in the "products" HashMap
    public boolean fetchingProduct() {
        products = ProductCatalogueManagement.LoadDataIntoHashMap("ProductCatalogue");
        if (products == null) {
            return false;
        }
        return true;
    }

    // Stores all the Stores in the "storeList" ArrayList
    public boolean fetchingStores() {
        if (storeList == null) {
            return false;
        }
        return true;
    }

    // Stores all the Managers in the "managerList" ArrayList
    public boolean loadManagersFromStores() {
        managerList = new ArrayList<>();
        if (storeList != null) {
            for (Store store : storeList) {
                managerList.add(store.getManager());
            }
            return true;
        }
        return false;
    }

    // Returns the Manager's ArrayList in the form of 2D array to display in table
    public String[][] getManagersList() {
        int i = 0;
        String[][] result = new String[managerList.size()][];
        for (Manager m : managerList) {
            String[] str = new String[5];
            str[0] = m.getId();
            String name = m.getFirstName() + " " + m.getLastName();
            str[1] = name;
            str[2] = m.getEmail();
            str[3] = m.getSalary();
            str[4] = m.getCity().substring(0, 1).toUpperCase() + m.getCity().substring(1);
            result[i++] = str;
        }
        return result;
    }

    // Returns the Admin's ArrayList in the form of 2D array to display in table
    public String[][] getTwoDArrayForAdmin() {
        int i = 0;
        String[][] result = new String[adminList.size()][];
        for (Person m : adminList) {
            String[] str = new String[5];
            str[0] = m.getFirstName();

            str[1] = m.getLastName();
            str[2] = m.getUsername();
            str[3] = m.getEmail();
            str[4] = m.getPassword();
            result[i++] = str;
        }
        return result;
    }

    // Returns the specific Product's ArrayList in the form of 2D array to display in table
    public String[][] getTwoDArrayForRequiredProduct(String productCategory) {
        int i = 0;
        String[][] result = new String[products.size()][];
        for (Map.Entry<Product, Boolean> product : products.entrySet()) {
            if (product.getKey().getMainCategory().equals(productCategory)) {
                String[] str = new String[6];
                str[0] = product.getKey().getName();
                String price = String.valueOf(product.getKey().getPrice());
                str[1] = price;
                str[2] = product.getKey().getWeightUnits();
                String quantity = String.valueOf(product.getKey().getQuantity());
                str[3] = quantity;
                str[4] = product.getKey().getBrand();
                String stringValue = Boolean.toString(product.getKey().isStatus());
                str[5] = stringValue;

                result[i++] = str;

            }

        }
        return result;
    }

        public String[][] getTwoDArrayForFalseProduct() {
        int i = 0;
        String[][] result = new String[products.size()][];
        for (Map.Entry<Product, Boolean> product : products.entrySet()) {
            if (product.getValue()== false) {
                String[] str = new String[6];
                str[0] = product.getKey().getName();
                String price = String.valueOf(product.getKey().getPrice());
                str[1] = price;
                str[2] = product.getKey().getWeightUnits();
                String quantity = String.valueOf(product.getKey().getQuantity());
                str[3] = quantity;
                str[4] = product.getKey().getBrand();
                String stringValue = Boolean.toString(product.getKey().isStatus());
                str[5] = stringValue;

                result[i++] = str;

            }

        }
        return result;
    }
    // Returns the Product's ArrayList in the form of 2D array to display in table
    public String[][] getAllCatalogue() {
        int i = 0;
        String[][] result = new String[products.size()][];
        for (Map.Entry<Product, Boolean> product : products.entrySet()) {

                String[] str = new String[6];
                str[0] = product.getKey().getName();
                String price = String.valueOf(product.getKey().getPrice());
                str[1] = price;
                str[2] = product.getKey().getWeightUnits();
                String quantity = String.valueOf(product.getKey().getQuantity());
                str[3] = quantity;
                str[4] = product.getKey().getBrand();
                String stringValue = Boolean.toString(product.getKey().isStatus());
                str[5] = stringValue;

                result[i++] = str;
        }
        return result;
    }

    // Returns the Store's ArrayList in the form of 2D array to display in table
    public String[][] getStoresList() {
        String[][] ans = new String[storeList.size()][];
        int i = 0;
        for (Store s : storeList) {
            String[] str = new String[2];
            str[0] = s.getLocation().substring(0, 1).toUpperCase() + s.getLocation().substring(1);
            str[1] = s.getManager().getFirstName() + s.getManager().getLastName();

            ans[i++] = str;
        }
        return ans;
    }

    public static void main(String[] args) {
        new Admin_Panel();
    }


}

