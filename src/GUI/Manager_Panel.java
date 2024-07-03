/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ASUS TUFF
 */

import ProductCatalogue.Product;
import ProductCatalogueManagement.ProductCatalogueManagement;
import Stores.Store;
import Users.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import javax.swing.border.EtchedBorder;


public class Manager_Panel extends JFrame implements ActionListener {
    JPanel leftpanel,rightpanel;
    JPanel footerPanel,mainCenterPanel;
    JButton managerStoreButton,viewManagerButton,viewCustomerButton,logOutButton;
    JButton setQuantityButton,setPriceButton,removeCustomer,removeProduct,seachCustomer;
    Manager manager;
    JLabel text,image,image1;

    private ArrayList<Person>  customerList;
    private ArrayList<Store>  stores;
    private HashMap<Product,Boolean> storeInventory;
    Store store;

    DefaultTableModel model;

    public Manager_Panel(){
        this.loadLoginManager();
        stores = new ArrayList<Store>();
        stores = UserWriter.LoadStoresList(stores);
        setStore(); // Setting Store according to Manager's Location
        this.customerList=UserWriter.LoadArrayList(customerList,"customer"); // Setting Customers according to Manager's location

        // Setting GUI
        setIconImage(new ImageIcon("src\\Images\\Company_Logo.png").getImage());
        setLayout(new BorderLayout(0,0));
        setSize(830,700);
        setLocationRelativeTo(null);
        setTitle("Manager Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Setting Buttons for Main Panel
        managerStoreButton=new Custom_Button("          Store          ");
        viewManagerButton=new Custom_Button("       Account        ");
        viewCustomerButton=new Custom_Button("     Customers      ");
        logOutButton=new Custom_Button("        Logout         ");
//        seachCustomer=new Custom_Button("     Search      ");
        
        managerStoreButton.addActionListener(this);
        viewManagerButton.addActionListener(this);
        viewCustomerButton.addActionListener(this);
        logOutButton.addActionListener(this);
//        seachCustomer.addActionListener(this);
        
        // Creating the MENU bar to put on top of Buttons
        text = new JLabel();
        text.setText("MENU");
        text.setMaximumSize(new Dimension(150,50));
        text.setBorder(new EtchedBorder());
        text.setForeground(Color.WHITE);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font("Times New Roman", Font.BOLD, 19));
        
        // Creating left Panel for buttons
        leftpanel=new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
        leftpanel.setBackground(Color.decode("#0D1152"));
        leftpanel.setPreferredSize(new Dimension(180,getHeight()));
        leftpanel.add(Box.createVerticalStrut(220));
        leftpanel.add(text);
        leftpanel.add(Box.createVerticalStrut(10));
        leftpanel.add(managerStoreButton);
        leftpanel.add(Box.createVerticalStrut(10));
        leftpanel.add(viewManagerButton);
        leftpanel.add(Box.createVerticalStrut(10));
        leftpanel.add(viewCustomerButton);
        leftpanel.add(Box.createVerticalStrut(10));
        leftpanel.add(logOutButton);
        leftpanel.add(Box.createHorizontalStrut(26));

        // Setting right panel of Frame
        rightpanel = new JPanel();
        rightpanel.setSize(new Dimension(640,650));
        image = new JLabel(new ImageIcon("src\\Images\\Manager_Panel.png"));
        image1 = new JLabel(new ImageIcon("src\\Images\\Manager_Info.png"));
        image.setBounds(180,0,660,670);// This image to show by default
        image1.setBounds(180,0,660,670);// This image to show while displaying Admin info
        
        rightpanel.add(image);
        rightpanel.add(image1);
        image1.setVisible(false);// As by default we don't need the Info screen
        image.setVisible(true);

        // Setting Footer Panel of Frame
        footerPanel=new JPanel();
        footerPanel.setPreferredSize(new Dimension(getWidth(),40));
        footerPanel.setBackground(Color.BLACK);

        // Setting Buttons for Footer
        setQuantityButton=new Custom_Button("Set Quantity");
        setPriceButton=new Custom_Button("Set Price");
        removeProduct=new Custom_Button("Remove Product");
        removeCustomer=new Custom_Button("Remove Customer");

        setQuantityButton.addActionListener(this);
        setPriceButton.addActionListener(this);
        removeProduct.addActionListener(this);
        removeCustomer.addActionListener(this);

        // Setting Panel to show when viewing Products and Customers
        mainCenterPanel=new JPanel();
        mainCenterPanel.setBackground(Color.WHITE);
        mainCenterPanel.setLayout(new BorderLayout());
        mainCenterPanel.setVisible(false); // Would be visible when Store products and Customers are selected

        // Initiating table for showing Products and Customers in default Table style
        model = new DefaultTableModel();

        // Initially footer will not be visible
        footerPanel.setVisible(false);

        // Adding components to Main Frame
        add(leftpanel,BorderLayout.WEST);
        add(rightpanel);
        add(footerPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
    
    // Getter and Setter for the Manager
    public Manager getManager() { return manager; }
    public void setManager(Manager manager) { this.manager = manager; }

    // Method to set the Store according to Manager's location
    public void setStore(){
        for (Store st: stores){
            if (st.getLocation().equals(manager.getCity())){
                store=st;
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        image.setVisible(false); // As soon as a button is clicked default image will not be invisible
        String brandName; // This will be used later on to perform some function on a specific product

        // Initially the footer will not be visible, It will be set visible according to our need
        footerPanel.setVisible(false);
        footerPanel.remove(setPriceButton);
        footerPanel.remove(setQuantityButton);
        footerPanel.remove(removeCustomer);
        footerPanel.remove(removeProduct);

        if(e.getSource() == managerStoreButton){ // When Store button is pressed 
            // This resets the table if the button is re-pressed
            model.setRowCount(0);
            model.setColumnCount(0);
            rightpanel.setVisible(false); // It's disabled to show the table

            // Adding required buttons to Footer Panel
            footerPanel.add(setQuantityButton);
            footerPanel.add(setPriceButton);
            footerPanel.add(removeProduct);
            footerPanel.setVisible(true);

            // Main Panel set to visible as we'll be showing Tables
            mainCenterPanel.setVisible(true);

            // setting Store according to Location
            fetchStoreAccToLocation();
            String[] coloumns = {"Name", "Price", "Weight Units", "Quantity", "Brand"}; // For Header field in Table
            String[][] rows = getStoreInventory(); // For getting 2D array of Products to display in Table

            model.setColumnIdentifiers(coloumns); // This sets the header according to given array

            // This sets the rows according to given 2D array of products
            for (String[] row : rows) {
                model.addRow(row);
            }

            // Inserting the Table style into Table
            JTable store = new JTable(model);
            
            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            store.getTableHeader().setDefaultRenderer(headerRenderer);
            JScrollPane customerScrollPane = new JScrollPane(store); // This enables us to view the Head row and other Rows together

            mainCenterPanel.add(customerScrollPane);
            add(mainCenterPanel,BorderLayout.CENTER);
            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        }
        
        else if (e.getSource() == viewManagerButton) { // When Manager Info button is pressed
            // This enables us to view the panel containing Manager Info Image
            rightpanel.setVisible(true);
            image1.setVisible(true);
            mainCenterPanel.setVisible(false);
            
            // Setting variables containing manager Information to be showed in Message Dialogue Box 
            String title = "Manager Information";
            String message = "Full Name:   " + manager.getFirstName() + " " + manager.getLastName() + "\n" +
                "Username:  " + manager.getUsername() + "\n" +
                "Email:          " + manager.getEmail() + "\n" +
                "Password:  " + manager.getPassword() + "\n" +
                "Salary:        " + manager.getSalary() + "\n" +
                "Location:    " + manager.getCity().substring(0, 1).toUpperCase() + manager.getCity().substring(1);
            
            // Using Message Dialogue Box
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
        
        else if (e.getSource() == viewCustomerButton){ // When Customers button is pressed to view Customers for the store
            // This resets the table if the button is re-pressed
            model.setRowCount(0);
            model.setColumnCount(0);
            
            rightpanel.setVisible(false); // Disabled because Table has to be shown

            // Adding required buttons to Footer Panel
//            footerPanel.add(seachCustomer);
            footerPanel.add(removeCustomer);
            footerPanel.setVisible(true);
            
            // Main Panel set to visible as we'll be showing Tables
            mainCenterPanel.setVisible(true);

            // This stores the Customer List from the file in ArrayList
            this.customerList=UserWriter.LoadArrayList(customerList,"customer");

            String[][] rows=getCustomers(); // For getting Header field in table
            String[] columns={"First Name ","Last Name ","Email","Location","CNIC"}; // For getting 2D array of Customers to display in Table

            model.setColumnIdentifiers(columns); // This sets the header according to given array

            // This sets the rows according to given 2D array of Stores
            for (String[] row : rows) {
                model.addRow(row);
            }

            // Inserting the Table style into Table
            JTable table=new JTable(model);

            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.getTableHeader().setDefaultRenderer(headerRenderer);
            JScrollPane customerScrollPane = new JScrollPane(table); // This enables us to view the Head row and other Rows together

            mainCenterPanel.add(customerScrollPane);
            add(mainCenterPanel,BorderLayout.CENTER);
            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        } 
        
        else if(e.getSource() == setQuantityButton ){ // Used to set quantity of a Product
            // Adding required Buttons to Footer
            footerPanel.add(setQuantityButton);
            footerPanel.add(setPriceButton);
            footerPanel.add(removeProduct);

            mainCenterPanel.setVisible(true);
            footerPanel.setVisible(true);

            brandName =  JOptionPane.showInputDialog(null,"Enter brand name: ");
            if (brandName!=null){
                ProductCatalogueManagement.updateItemFromFile(brandName,getManager().getCity(),1);
            }
            else{
                JOptionPane.showMessageDialog(null,"No name entered!");
            }
        }
        
        else if(e.getSource() == setPriceButton){ // Used to set Price of a Product
            // Adding required Buttons to Footer
            footerPanel.add(setQuantityButton);
            footerPanel.add(setPriceButton);
            footerPanel.add(removeProduct);

            mainCenterPanel.setVisible(true);
            footerPanel.setVisible(true);

            brandName =  JOptionPane.showInputDialog(null,"Enter brand name: ");
            if (brandName!=null){
                ProductCatalogueManagement.updateItemFromFile(brandName,getManager().getCity(),2);

            }
            else{
                JOptionPane.showMessageDialog(null,"No name entered!");
            }

        }
        
        else if(e.getSource() == removeProduct){ // Used to Remove a Product from Store
            // Adding required Buttons to Footer
            footerPanel.add(setQuantityButton);
            footerPanel.add(setPriceButton);
            footerPanel.add(removeProduct);
            footerPanel.setVisible(true);

            mainCenterPanel.setVisible(true);

            brandName =  JOptionPane.showInputDialog(null,"Enter brand name: ");
            if (brandName!=null){
                ProductCatalogueManagement.removeProductFromStoreFile(brandName,getManager().getCity());
            }
            else{
                JOptionPane.showMessageDialog(null,"No name entered!");
            }
        }
        
//        else if (e.getSource()==seachCustomer) { // Used to Search a specific Customer
//            String cnic=JOptionPane.showInputDialog(null,"Enter Customer CNIC:");
//
//            for (Person c: customerList){
//                if (cnic.equals(c.getCNIC())){
//                    JOptionPane.showMessageDialog(null,"Full Name : " + c.getFirstName() + " " + c.getLastName() + "\nUsername :" + c.getUsername() + "\nEmail        : " + c.getEmail() );
//                        return;
//                    }
//                }
//            JOptionPane.showMessageDialog(null,"Customer not found!");
//        }
        
        else if(e.getSource() == removeCustomer){ // Used to remove a Customer
            mainCenterPanel.setVisible(true);
            footerPanel.setVisible(true);
            footerPanel.add(removeCustomer);
            removeCustomerAccount();
            revalidate();
        }
        
        else if(e.getSource() == logOutButton){ // Used to Logout
            File file=new File("managerLoginned.ser");
            System.out.println(file.delete());
            dispose();
        }
    }

    // Sets the Window according to the Manager
    public void loadLoginManager(){
        File file=new File("src\\Database\\Current Login Manager.ser");
        if(!file.exists()){
            return;
        }
        else{
            try{
                ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
                setManager((Manager)objectInputStream.readObject());
                objectInputStream.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"Manager Login Reading problem!");
            }
        }
    }
    
    // Method to set Store's Inventory according to Manager's Location
    public void fetchStoreAccToLocation(){
        storeInventory = ProductCatalogueManagement.LoadDataIntoHashMap(getManager().getCity());
    }

    // Method to return a 2D array of Products of a store to display
    public String[][] getStoreInventory() {
        int i = 0;
        String[][] result = new String[storeInventory.size()][];
        for (Map.Entry<Product, Boolean> product : storeInventory.entrySet()){
            if (product.getKey().isStatus()) {
                String[] str = new String[5] ;
                str[0] = product.getKey().getName();
                str[1] = Integer.toString(product.getKey().getPrice());
                str[2] =  product.getKey().getWeightUnits();
                str[3] =  Integer.toString(product.getKey().getQuantity());
                str[4] =  product.getKey().getBrand();
                result[i++] = str;
            }
        }
        return result;
    }

    // Method to return a 2D array of Customers of a store to display
    public String[][] getCustomers() {
        int i = 0;
        String[][] result = new String[customerList.size()][];
        for (Person person : customerList) {
            person.toString();
            if(person.getCity().equalsIgnoreCase(getManager().getCity())){
                String[] str = new String[5];
                str[0] = person.getFirstName();
                str[1] = person.getLastName();
                str[2] = person.getEmail();
                str[3] = person.getCity();
                str[4] = person.getCNIC();
                result[i++] = str;
            }
        }
        return result;
    }
    
    // Method to remove a Customer from a Store
    public void removeCustomerAccount() {
        String cnic = JOptionPane.showInputDialog(null, "Enter Customer CNIC: ");
        if (cnic != null) {
            ArrayList<Person> customerList = new ArrayList<>();
            customerList = UserWriter.LoadArrayList(customerList,"customer");
            if (customerList == null) {
                JOptionPane.showMessageDialog(null, "This person is not registered in our system!");
                return;
            }
            boolean found = false;
            for (Person customer : customerList) {
                if (customer.getCNIC().equalsIgnoreCase(cnic)) {
                    found = true;
                    customerList.remove(customer);
                    UserWriter.WriteToFile(customerList,"customer");
                    break;
                }
            }
            if (found == false) {
                JOptionPane.showMessageDialog(null, "This person is not registered in our system!");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ID entered");
        }

    }

    public static void main(String[] args) {
        new Manager_Panel();
    }
}