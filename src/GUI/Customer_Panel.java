/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ASUS TUFF
 */

import ProductCatalogue.Food.Food;
import ProductCatalogue.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ProductCatalogueManagement.ProductCatalogueManagement;
import Stores.Store;
import Users.*;
import javax.swing.border.EtchedBorder;


public class Customer_Panel extends JFrame implements ActionListener {
    ImageIcon icon;
    JLabel foodFilter, totalLabel, text, label1, label2, label3;
    JButton checkout, placeOrder, confirmCartButton, shop, viewAccount, cart, logOut, food, houseHold, personalHygiene, perishabale, nonPersishable, buy, addToCart, removeFromCart,searchItem;
    JPanel totalPanel, filterPanel, mainButtonPanel, navBar, customerLeftBar, footer, imageContainer;
    JFrame cartFrame;
    JPanel tablePanel;
    JTable foodTable;
    JScrollPane foodScrollPane;
    JTextField brandTextField, quantityTextField;
    
    private Customer customer;
    private ArrayList<Store> stores;
    private HashMap<Product, Boolean> products;
    private String quantity;
    private Store store;

    DefaultTableModel model;

    public Customer_Panel() {
        super("Customer Panel");
        icon = new ImageIcon("src\\Images\\Company_Logo.png");
        setSize(900, 600);
        setResizable(false);
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ImageIcon image1 = new ImageIcon("src\\Images\\Customer_Screen.png");
        ImageIcon image2 = new ImageIcon("src\\Images\\Customer_Info.png");
        ImageIcon image3 = new ImageIcon("src\\Images\\Shopping_Screen.png");
        label1 = new JLabel();
        label1.setIcon(image1);
        label1.setBounds(0, 0, 900, 550);
        label2 = new JLabel();
        label2.setIcon(image2);
        label2.setBounds(0, 0, 900, 550);
        label3 = new JLabel();
        label3.setIcon(image3);
        label3.setBounds(0, 0, 900, 550);

        imageContainer = new JPanel();
        imageContainer.add(label1);
        imageContainer.add(label2);
        imageContainer.add(label3);
        label2.setVisible(false);
        label3.setVisible(false);

        loadCurrentCustomer(); // Setting Customer 
        fetchingStoreAccToLocation(); // Setting the Products 

        stores = new ArrayList<>();
        this.stores = UserWriter.LoadStoresList(stores); // This will add all the stores in the ArrayList
        this.store = getStore(); // This will set the Store according to Customer's location
        
        // Setting Buttons
        shop = new Custom_Button("Shop");
        cart = new Custom_Button("Cart");
        viewAccount = new Custom_Button("Account");
        logOut = new Custom_Button("Log Out");
        food = new Custom_Button("Food");
        personalHygiene = new Custom_Button("Hygiene");
        houseHold = new Custom_Button("Household");
//        searchItem=new Custom_Button("Seach Item");
        perishabale = new Custom_Button("Perishable");
        perishabale.setBackground(Color.green);
        nonPersishable = new Custom_Button("Non Perishable");
        nonPersishable.setBackground(Color.green);
        confirmCartButton = new Custom_Button("Confirm");
        placeOrder = new Custom_Button("Place Order");
        checkout = new Custom_Button("Checkout");
        buy = new Custom_Button("Buy");
        addToCart = new Custom_Button("Add to Cart");
        removeFromCart = new Custom_Button("Remove Product");

        shop.setPreferredSize(new Dimension(80, 25));
        cart.setPreferredSize(new Dimension(80, 25));
        viewAccount.setPreferredSize(new Dimension(100, 25));
        logOut.setPreferredSize(new Dimension(100, 25));
        food.setPreferredSize(new Dimension(130, 30));
        personalHygiene.setPreferredSize(new Dimension(130, 30));
        houseHold.setPreferredSize(new Dimension(130, 30));
//        searchItem.setPreferredSize(new Dimension(110, 25));
        perishabale.setPreferredSize(new Dimension(110, 20));
        nonPersishable.setPreferredSize(new Dimension(140, 20));
        checkout.setPreferredSize(new Dimension(100,25));
        buy.setPreferredSize(new Dimension(80,25));
        addToCart.setPreferredSize(new Dimension(115,25));
        removeFromCart.setPreferredSize(new Dimension(150,25));
        
        shop.addActionListener(this);
        cart.addActionListener(this);
        viewAccount.addActionListener(this);
        logOut.addActionListener(this);
        food.addActionListener(this);
        personalHygiene.addActionListener(this);
        houseHold.addActionListener(this);
//        searchItem.addActionListener(this);
        perishabale.addActionListener(this);
        nonPersishable.addActionListener(this);
        confirmCartButton.addActionListener(this);
        placeOrder.addActionListener(this);
        checkout.addActionListener(this);
        buy.addActionListener(this);
        addToCart.addActionListener(this);
        removeFromCart.addActionListener(this);
        
        // Creating the MENU bar to put on top of Buttons
        text = new JLabel();
        text.setText("  MENU  ");
        text.setMaximumSize(new Dimension(157,30));
        text.setBorder(new EtchedBorder());
        text.setForeground(Color.WHITE);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        // Creating Filter Label to put Buttons in Navbar
        foodFilter = new JLabel();
        foodFilter.setText("Filter:");
        foodFilter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        foodFilter.setBackground(Color.BLACK);
        foodFilter.setForeground(Color.WHITE);
        
        // Creating Panel to put the Food Filter Components
        filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 14));
        filterPanel.setBackground(Color.BLACK);
        filterPanel.add(foodFilter);
        filterPanel.add(perishabale);
        filterPanel.add(nonPersishable);
        
        // Creating Label to show the Total Bill of items present in the Cart
        totalLabel = new JLabel();
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBackground(Color.BLACK);

        // Creating Panel to put the Total Bill Label
        totalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        totalPanel.add(totalLabel);
        totalPanel.setBackground(Color.BLACK);

        // Creating Main Button Panel that'll be showed on the Left Side
        mainButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        mainButtonPanel.setBackground(Color.BLACK);
        mainButtonPanel.add(shop);
//        mainButtonPanel.add(searchItem);
        mainButtonPanel.add(cart);
        mainButtonPanel.add(viewAccount);
        mainButtonPanel.add(logOut);

        // Creating a Top Panel that'll contain all the panels of the Navbar
        navBar = new JPanel(new BorderLayout());
        navBar.setPreferredSize(new Dimension(getWidth(), 44));
        navBar.setBackground(Color.BLACK);

        navBar.add(mainButtonPanel, BorderLayout.WEST);
        navBar.add(filterPanel, BorderLayout.EAST);
        navBar.add(totalPanel, BorderLayout.CENTER);

        //Creating Left Panel that'll contain buttons to view Products by categories
        customerLeftBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 17));
        customerLeftBar.setPreferredSize(new Dimension(160, getHeight()));
        customerLeftBar.setBackground(Color.decode("#0D1152"));

        customerLeftBar.add(food);
        customerLeftBar.add(houseHold);
        customerLeftBar.add(personalHygiene);

        // Creating White Panel in which the Tables will be shown
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);

        // Creating a Footer Panel that'll contain desired Buttons
        footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setPreferredSize(new Dimension(getWidth(), 40));
        footer.setBackground(Color.BLACK);
        footer.add(buy);
        footer.add(addToCart);
        footer.add(removeFromCart);
        footer.add(checkout);

        // Initiating table for showing Products and Customers in default Table style
        model = new DefaultTableModel();

        // These Panels will be set visible when required
        customerLeftBar.setVisible(false);
        tablePanel.setVisible(false);
        footer.setVisible(false);
        filterPanel.setVisible(false);
        totalPanel.setVisible(false);

        // Adding Panels to the Frame
        add(customerLeftBar, BorderLayout.WEST);
        add(navBar, BorderLayout.NORTH);
        add(imageContainer, BorderLayout.CENTER);
        setVisible(true);
    }

    // Getter and Setter for Product's quantity & Customer
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    @Override
    public void actionPerformed(ActionEvent e) {
        label1.setVisible(false);
        imageContainer.setVisible(false);
        footer.remove(buy);
        footer.remove(addToCart);
        footer.remove(checkout);
        footer.remove(removeFromCart);

        totalPanel.setVisible(false);
        footer.setVisible(false);
        filterPanel.setVisible(false);

        if (e.getSource() == shop) { // Sets the Panel to Shopping Screen
            imageContainer.setVisible(true);
            label2.setVisible(false);
            label3.setVisible(true);
            tablePanel.setVisible(false);
            customerLeftBar.setVisible(true);
            revalidate();
        } 
        
        else if (e.getSource() == cart) { // Used to open Cart page
            // This resets the table if the button is re-pressed
            model.setColumnCount(0);
            model.setRowCount(0);

            totalLabel.setText("Grand Total: " + getGrandTotal());
            totalPanel.setVisible(true);
            footer.setVisible(true);

            footer.add(checkout);
            footer.add(removeFromCart);

            tablePanel.setVisible(true);
            customerLeftBar.setVisible(false);

            String[][] rows = getCustomerCart();
            String[] coloumns = {"Name ", "Price ", "Weight Units", "Brand", "Selected Quantity"};

            model.setColumnIdentifiers(coloumns); // This sets the header according to given array

            // This sets the rows according to given 2D array of Stores
            for (String[] row : rows){
                model.addRow(row);
            }

            // Inserting the Table style into Table
            foodTable = new JTable(model);

            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);

            foodScrollPane = new JScrollPane(foodTable); // This enables us to view the Head row and other Rows together
            foodScrollPane.setBorder(BorderFactory.createEmptyBorder());

            tablePanel.add(foodScrollPane, BorderLayout.CENTER);

            add(tablePanel, BorderLayout.CENTER);
            add(footer, BorderLayout.SOUTH);

            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        } 
        
        else if (e.getSource() == viewAccount) { // Used to View Customer's account info
            customerLeftBar.setVisible(false);
            footer.setVisible(false);
            tablePanel.setVisible(false);
            
            imageContainer.setVisible(true);
            label3.setVisible(false);
            label2.setVisible(true);

            JOptionPane.showMessageDialog(null, "Full Name : " + customer.getFirstName() + " " + customer.getLastName() + "\nUsername :" + customer.getUsername() + "\nEmail        : " + customer.getEmail() + "\nPassword  :" + customer.getPassword() + "\nLocation  : " + customer.getCity());

            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        }     
        
        else if (e.getSource() == logOut) { // Used to Logout
            dispose();
        }
        
        else if (e.getSource() == food) { // Sets the Panel to Table with category Food
            // This resets the table if the button is re-pressed
            model.setColumnCount(0);
            model.setRowCount(0);

            filterPanel.setVisible(true);
            footer.setVisible(true);

            footer.add(buy);
            footer.add(addToCart);

            customerLeftBar.setVisible(true);
            tablePanel.setVisible(true);

            fetchingStoreAccToLocation();
            
            //Creating Table and setting table
            String[][] rows = getRequiredProduct("Food");
            String[] coloumns = {"Name", "Price", "Weight Units", "Availability", "Brand"};

            model.setColumnIdentifiers(coloumns); // This sets the header according to given array

            // This sets the rows according to given 2D array of Stores
            for (String[] row : rows) {
                model.addRow(row);
            }

            // Inserting the Table style into Table
            foodTable = new JTable(model);

            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);

            foodScrollPane = new JScrollPane(foodTable); // This enables us to view the Head row and other Rows together
            foodScrollPane.setBorder(BorderFactory.createEmptyBorder());
            tablePanel.add(foodScrollPane);

            add(tablePanel, BorderLayout.CENTER);
            add(footer, BorderLayout.SOUTH);

            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        }
        
        else if (e.getSource() == perishabale) { // Sets the Food Table with sub category Perishable
            // This resets the table if the button is re-pressed
            model.setRowCount(0);
            model.setColumnCount(0);

            filterPanel.setVisible(true);
            footer.setVisible(true);

            footer.add(buy);
            footer.add(addToCart);

            tablePanel.setVisible(true);
            customerLeftBar.setVisible(true);
            
            fetchingStoreAccToLocation();

            String[][] rows = getRequiredFoodProduct("Food", "perishable");
            String[] coloumns = {"Name", "Price", "Weight Units", "Availability", "Brand"};

            model.setColumnIdentifiers(coloumns); // This sets the header according to given array

            // This sets the rows according to given 2D array of Stores
            for (String[] row : rows) {
                model.addRow(row);
            }

            // Inserting the Table style into Table
            foodTable = new JTable(model);

            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);

            foodScrollPane = new JScrollPane(foodTable); // This enables us to view the Head row and other Rows together
            foodScrollPane.setBorder(BorderFactory.createEmptyBorder());

            tablePanel.add(foodScrollPane);
            add(tablePanel, BorderLayout.CENTER);
            add(footer, BorderLayout.SOUTH);

            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        } 
        
        else if (e.getSource() == nonPersishable) { // Sets the Food Table with sub category Non-Perishable
            // This resets the table if the button is re-pressed
            model.setColumnCount(0);
            model.setRowCount(0);

            filterPanel.setVisible(true);
            footer.setVisible(true);

            footer.add(buy);
            footer.add(addToCart);

            tablePanel.setVisible(true);
            customerLeftBar.setVisible(true);

            fetchingStoreAccToLocation();
            
            String[][] rows = getRequiredFoodProduct("Food", "nonPerishable");
            String[] coloumns = {"Name", "Price", "Weight Units", "Availability", "Brand"};

            model.setColumnIdentifiers(coloumns); // This sets the header according to given array

            // This sets the rows according to given 2D array of Stores
            for (String[] row : rows) {
                model.addRow(row);
            }

            // Inserting the Table style into Table
            foodTable = new JTable(model);

            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);

            foodScrollPane = new JScrollPane(foodTable); // This enables us to view the Head row and other Rows together
            foodScrollPane.setBorder(BorderFactory.createEmptyBorder());

            tablePanel.add(foodScrollPane);
            add(tablePanel, BorderLayout.CENTER);

            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        } 
        
        else if (e.getSource() == personalHygiene) { // Sets the Panel to Table with category Personal Hygiene
            // This resets the table if the button is re-pressed
            model.setRowCount(0);
            model.setColumnCount(0);

            footer.setVisible(true);

            footer.add(buy);
            footer.add(addToCart);

            tablePanel.setVisible(true);
            customerLeftBar.setVisible(true);

            fetchingStoreAccToLocation();
            String[][] rows = getRequiredProduct("personalHygiene");
            String[] coloumns = {"Name", "Price", "Weight Units", "Availability", "Brand"};

            model.setColumnIdentifiers(coloumns); // This sets the header according to given array

            // This sets the rows according to given 2D array of Stores
            for (String[] row : rows) {
                model.addRow(row);
            }

            // Inserting the Table style into Table
            foodTable = new JTable(model);

            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);

            foodScrollPane = new JScrollPane(foodTable); // This enables us to view the Head row and other Rows together
            foodScrollPane.setBorder(BorderFactory.createEmptyBorder()); 

            tablePanel.add(foodScrollPane);
            add(tablePanel, BorderLayout.CENTER);
            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        } 
        
        else if (e.getSource() == houseHold) { // Sets the Panel to Table with category Household
            // This resets the table if the button is re-pressed
            model.setColumnCount(0);
            model.setRowCount(0);

            revalidate();
            footer.setVisible(true);

            footer.add(buy);
            footer.add(addToCart);

            tablePanel.setVisible(true);
            customerLeftBar.setVisible(true);

            fetchingStoreAccToLocation();

            String[][] rows = getRequiredProduct("houseHoldCleaning");
            String[] coloumns = {"Name", "Price", "Weight Units", "Availability", "Brand"};

            model.setColumnIdentifiers(coloumns); // This sets the header according to given array

            // This sets the rows according to given 2D array of Stores
            for (String[] row : rows) {
                model.addRow(row);
            }

            // Inserting the Table style into Table
            foodTable = new JTable(model);

            // Setting Style for Table
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(Color.BLUE);
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            foodTable.getTableHeader().setDefaultRenderer(headerRenderer);

            foodScrollPane = new JScrollPane(foodTable); // This enables us to view the Head row and other Rows together
            foodScrollPane.setBorder(BorderFactory.createEmptyBorder());

            tablePanel.add(foodScrollPane);
            add(tablePanel, BorderLayout.CENTER);
            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        }  
        
        else if (e.getSource() == addToCart) { // Used to add Item to cart by searching
            footer.add(buy);
            footer.add(addToCart);
            footer.setVisible(true);

            JLabel brandLabel = new JLabel("Product Brand: ");
            brandLabel.setForeground(Color.WHITE);
            brandLabel.setBounds(25, 20, 150, 20);
            brandTextField = new JTextField();
            brandTextField.setBounds(25, 40, 150, 20);
            JLabel quantityLabel = new JLabel("Product Quantity: ");
            quantityLabel.setForeground(Color.WHITE);
            quantityLabel.setBounds(25, 70, 170, 20);
            quantityTextField = new JTextField();
            quantityTextField.setBounds(25, 90, 150, 20);
            confirmCartButton.setBounds(50, 130, 100, 20);

            cartFrame = new JFrame();
            cartFrame.setIconImage(new ImageIcon("src\\Images\\Company_Logo.png").getImage());
            cartFrame.getContentPane().setBackground(Color.decode("#0D1152"));
            cartFrame.setSize(210, 200);
            cartFrame.setTitle("ADD");
            cartFrame.setLocationRelativeTo(null);
            cartFrame.setLayout(null);
            cartFrame.setResizable(false);
            cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cartFrame.setVisible(true);
            cartFrame.add(brandLabel);
            cartFrame.add(brandTextField);
            cartFrame.add(quantityLabel);
            cartFrame.add(quantityTextField);
            cartFrame.add(confirmCartButton);
            
            revalidate(); // Used to notify the layout manager that the layout of a container has changed and needs to be recalculated
        } 
        
        else if (e.getSource() == confirmCartButton) { // This one is in the Add to Cart frame and will check the Fields entered
            footer.add(buy);
            footer.add(addToCart);
            footer.setVisible(true);

            String brandNameText = brandTextField.getText();
            String quantity = quantityTextField.getText();

            if (!brandTextField.getText().isEmpty() && !quantityTextField.getText().isEmpty()) {
                Product p = ProductCatalogueManagement.SearchItem(brandNameText, getCustomer().getCity());
                if (p != null) {
                    if (Integer.parseInt(quantity) <= p.getQuantity() && Integer.parseInt(quantity) > 0) { // Checking Quantity 
                        // To update Customer Cart in file
                        ArrayList<Customer> customers = new ArrayList<>();
                        customers = UserWriter.LoadArrayList1(customers);
                        for (Customer customer : customers) {
                            if (customer.getUsername().equalsIgnoreCase(getCustomer().getUsername())) {
                                p.setQuantity(Integer.parseInt(quantity));
                                customer.getCart().addProduct(p);
                                getCustomer().getCart().addProduct(p);
                            }
                        }

                        //updating All customers File
                        UserWriter.WriteCustomerToFile(customers);
                        cartFrame.dispose();
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Quantity error");
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Product does not exist");
                }
            } 
            else {
                JOptionPane.showMessageDialog(null, "Some fields are empty");
            }
        } 
        
        else if (e.getSource() == removeFromCart) { // Used to remove Product from Cart
            footer.add(checkout);
            footer.add(removeFromCart);
            footer.setVisible(true);
            String removeProduct = JOptionPane.showInputDialog(null, "Enter Product Brand name:");

            if (removeProduct != null) {
                // To update Customer Cart in file
                ArrayList<Customer> customers = new ArrayList<>();
                customers = UserWriter.LoadArrayList1(customers);
                for (Customer customer : customers) {
                    if (customer.getUsername().equalsIgnoreCase(getCustomer().getUsername())) {
                        ArrayList<Product> pro = customer.getCart().getProducts();
                        for (Product p : pro) {
                            if (removeProduct.equalsIgnoreCase(p.getBrand())) {
                                customer.getCart().getProducts().remove(p);
                                break;
                            }
                        }
                    }
                }
                UserWriter.WriteCustomerToFile(customers);
            } 
            else {
                JOptionPane.showMessageDialog(null, "No input");
            }
        } 
        
        else if (e.getSource() == buy) { // Used to add a Product to cart
            footer.add(buy);
            footer.add(addToCart);
            footer.setVisible(true);

            cartFrame = new JFrame();
            cartFrame.setSize(250, 200);
            cartFrame.getContentPane().setBackground(Color.decode("#0D1152"));
            cartFrame.setIconImage(new ImageIcon("src\\Images\\Company_Logo.png").getImage());
            cartFrame.setTitle("Place Order");
            cartFrame.setLayout(null);
            cartFrame.setLocationRelativeTo(null);
            cartFrame.setResizable(false);
            cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel brandLabel = new JLabel("Product Brand: ");
            brandLabel.setForeground(Color.WHITE);
            JLabel quantityLabel = new JLabel("Enter Quantity: ");
            quantityLabel.setForeground(Color.WHITE);

            quantityTextField = new JTextField();
            brandTextField = new JTextField();

            brandLabel.setBounds(45, 20, 150, 20);
            quantityLabel.setBounds(45, 70, 170, 20);

            brandTextField.setBounds(45, 45, 150, 20);
            quantityTextField.setBounds(45, 95, 150, 20);
            placeOrder.setBounds(45, 130, 150, 20);

            cartFrame.setVisible(true);
            cartFrame.add(brandLabel);
            cartFrame.add(brandTextField);
            cartFrame.add(quantityLabel);
            cartFrame.add(quantityTextField);
            cartFrame.add(placeOrder);
        } 
        
        else if (e.getSource() == placeOrder) { // Used in the Buy frame to Purchase the product

            footer.add(buy);
            footer.add(addToCart);
            footer.setVisible(true);

            String brandNameText = brandTextField.getText();
            String quantity = quantityTextField.getText();

            if (!brandTextField.getText().isEmpty() && !quantityTextField.getText().isEmpty()) {
                Product p = ProductCatalogueManagement.SearchItem(brandNameText, getCustomer().getCity());
                if (p != null) {
                    if (Integer.parseInt(quantity) <= p.getQuantity() && Integer.parseInt(quantity) > 0) {
                        int bill = Integer.parseInt(quantity) * p.getPrice();
                        int choice = JOptionPane.showConfirmDialog(cartFrame, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(cartFrame, "Product Name: " + p.getName() + "\nProduct Brand: " + p.getBrand() + "\nPrice per: " + " " + p.getWeightUnits() + " " + p.getPrice() + "\nTotal Bill: " + bill);
                            ProductCatalogueManagement.updateItemQuantityAfterBuy(brandNameText, getCustomer().getCity(), Integer.parseInt(quantity));
                            cartFrame.dispose();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Quantity error!");
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Product does not exist!");
                }
            } 
            else {
                JOptionPane.showMessageDialog(null, "Required fields are empty!");
            }
        } 
        
        else if (e.getSource() == checkout) { // Used to checkout from Cart
            footer.add(checkout);
            footer.add(removeFromCart);
            footer.setVisible(true);
            
            double bill = 0;
            // To update Customer data in file
            ArrayList<Customer> customers = new ArrayList<>();
            customers = UserWriter.LoadArrayList1(customers);

            for (Customer customer : customers) {
                if (customer.getUsername().equalsIgnoreCase(getCustomer().getUsername())) {
                    bill = customer.getCart().calculateTotal();

                    ArrayList<Product> products = customer.getCart().getProducts();
                    Iterator<Product> iterator = products.iterator();
                    while (iterator.hasNext()) {
                        Product p = iterator.next();
                        ProductCatalogueManagement.updateItemQuantityAfterBuy(p.getBrand(), getCustomer().getCity(), p.getQuantity());
                        iterator.remove();
                        customer.getCart().removeProduct(p);
                    }
                    break;
                }
            }

            JOptionPane.showMessageDialog(null, "Total Bill: Rs. " + bill + "\nThanks for Shopping!");

            UserWriter.WriteCustomerToFile(customers);
        } 
        
//        else if(e.getSource() == searchItem){
//
//            String brandName=JOptionPane.showInputDialog(null,"Enter brand Name of item ");
//
//            while(brandName==null){
//                 brandName=JOptionPane.showInputDialog(null,"Enter brand Name of item Again ur field is empty ");
//            }
//
//            System.out.printf("brandName "+brandName);
//            Product product=ProductCatalogueManagement.SearchItem(brandName,getCustomer().getCity());
//
//            if(product==null){
//                JOptionPane.showMessageDialog(null,"Item not available with this brand Name ");
//            }
//            else{
//                JOptionPane.showMessageDialog(null,"Item found with this information: \n "+"Product Type:" +product.getName() +"\nProduct Quantity ::" +product.getQuantity()+"\nProduct Price :" + product.getPrice()+"\nproduct WeightUnits  :"+product.getWeightUnits());
//            }
//        }


    }

    // Method to load the Customer that just logged in
    public void loadCurrentCustomer() {
        File file = new File("src\\Database\\Current Login Customer.ser");
        if (!file.exists()) {
            return;
        } else {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                this.setCustomer((Customer) objectInputStream.readObject());

                objectInputStream.close();
            } 
            catch (Exception e) {
                System.out.println("Problem in reading Customer File!");
            }
        }
    }

    // Method that sets the store according to Customer's city
    public Store getStore() {
        for (Store s : stores) {
            if (s.getLocation().equalsIgnoreCase(customer.getCity())) {
                return s;
            }
        }
        return null;
    }

    // Method to set the Store according to Customer's location
    public void fetchingStoreAccToLocation() {
        this.products = ProductCatalogueManagement.LoadDataIntoHashMap(getCustomer().getCity());
        ProductCatalogueManagement.viewHashmapFromFile(getCustomer().getCity());
    }

    // Method to return an ArrayList of products
    public ArrayList<Product> getCartList() {

        ArrayList<Customer> per = new ArrayList<>();
        per = UserWriter.LoadArrayList1(per);

        for (Customer p : per) {
            if (p.getUsername().equalsIgnoreCase(getCustomer().getUsername())) {
                ArrayList<Product> products = p.getCart().getProducts();
                return products;
            }
        }
        return null;
    }

    // Method that returns the total bill
    public double getGrandTotal() {
        double price = getCustomer().getCart().calculateTotal();
        return price;
    }

    // Method to return a 2D array of products to show in table
    public String[][] getCustomerCart() {
        getCartList();

        String[][] result = new String[getCartList().size()][];
        int i = 0;
        for (Product p : getCartList()) {
            String[] s = new String[5];
            s[0] = p.getName();
            s[1] = String.valueOf(p.getPrice());
            s[2] = p.getWeightUnits();
            s[3] = p.getBrand();
            s[4] = Integer.toString(p.getQuantity());
            result[i++] = s;
        }
        return result;
    }

    // Method to return a 2D array of Products to show in Table
    public String[][] getRequiredProduct(String productCategory) {
        int i = 0;
        String[][] result = new String[products.size()][];
        for (Map.Entry<Product, Boolean> product : products.entrySet()) {
            if (product.getKey().getMainCategory().equals(productCategory)) {
                if (product.getKey().isStatus()) {
                    String[] str = new String[5];

                    str[0] = product.getKey().getName();
                    String price = String.valueOf(product.getKey().getPrice());
                    str[1] = price;
                    str[2] = product.getKey().getWeightUnits();
                    if (product.getKey().getQuantity() > 0) {
                        str[3] = "In Stock";
                    } else {
                        str[3] = "Out of Stock";
                    }

                    str[4] = product.getKey().getBrand();
                    result[i++] = str;
                }
            }
        }
        return result;
    }

    // Separate method for Food as there are further TWO categories of Food
    public String[][] getRequiredFoodProduct(String productCategory, String foodType) {
        int i = 0;
        String[][] result = new String[products.size()][];
        HashMap<Food, Boolean> foods = new HashMap<>();
        for (Map.Entry<Product, Boolean> product : products.entrySet()) {
            if ((product.getKey().getMainCategory().equalsIgnoreCase(productCategory))) {
                if (product.getKey().isStatus()) {
                    foods.put((Food) product.getKey(), product.getValue());
                }
            }
        }
        for (Map.Entry<Food, Boolean> food : foods.entrySet()) {
            if (food.getKey().getType().equals(foodType)) {
                if (food.getKey().isStatus()) {
                    String[] str = new String[5];

                    str[0] = food.getKey().getName();
                    String price = String.valueOf(food.getKey().getPrice());
                    str[1] = price;
                    str[2] = food.getKey().getWeightUnits();
                    if (food.getKey().getQuantity() > 0) {
                        str[3] = "In Stock";

                    } else {
                        str[3] = "Out of Stock";
                    }

                    str[4] = food.getKey().getBrand();
                    result[i++] = str;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        new Customer_Panel();
    }
}