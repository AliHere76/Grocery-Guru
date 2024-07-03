/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ASUS TUFF
 */

import Login_SignUp_Validation.credentialsVerification;
import Users.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Login_Screen extends JFrame implements ActionListener{
    JButton loginButton;
    JTextField usernameField;
    JPasswordField passwordField;
    ImageIcon icon;
    boolean isAdminButtonClicked;
    boolean isManagerButtonClicked;
    private Manager manager;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Login_Screen(){}

    public Login_Screen(boolean isAdminButtonClicked, boolean isManagerButtonClicked) {
        this.isAdminButtonClicked = isAdminButtonClicked;
        this.isManagerButtonClicked = isManagerButtonClicked;
        icon = new ImageIcon("src\\Images\\Company_Logo.png");
        
        // Setting the frame
        if(isAdminButtonClicked==true){
            setTitle("ADMIN LOGIN");
        }
        else if(isManagerButtonClicked==true){
            setTitle("MANAGER LOGIN");
        }
        else{
            setTitle("CUSTOMER LOGIN");
        }
        setSize(400, 400);
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#0D1152"));
        setLayout(null);
        setResizable(false);

        // Setting the logo in JFrame
        ImageIcon image = new ImageIcon("src\\Images\\Company_logo.png");
        JLabel loginImage = new JLabel();
        loginImage.setIcon(image);
        loginImage.setBounds(130, 15, image.getIconWidth(), image.getIconHeight());

        // Setting Fields for Username
        JLabel username = new JLabel();
        username.setText("Username / Email: ");
        username.setFont(new Font("Times New Roman", Font.BOLD, 14));
        username.setForeground(Color.WHITE);
        username.setBounds(60, 160, 120, 20);
        usernameField = new JTextField();
        usernameField.setBounds(190, 160, 140, 25);

        // Setting Fields for Password
        JLabel password = new JLabel();
        password.setText("Password: ");
        password.setFont(new Font("Times New Roman", Font.BOLD, 14));
        password.setForeground(Color.WHITE);
        password.setBounds(110, 210, 100, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 210, 140, 25);

        // Creating Login Button
        loginButton = new Custom_Button("Log In");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        loginButton.setBounds(150, 260, 100, 25);
        loginButton.addActionListener(this);

        //adding content to Frame;
        add(loginImage);
        add(username);
        add(usernameField);
        add(password);
        add(passwordField);
        add(loginButton);
        setVisible(true);
    }
    public Manager getManager() {return manager;}
    public void setManager(Manager manager) {this.manager = manager;}
    
    public void writeManagertoFile(Manager manager){
        File f=new File("src\\Database\\"+"Current Login Manager.ser");
        try{
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(f));
            objectOutputStream.writeObject(manager);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch(EOFException e){
            return;
        }
        catch(Exception e){
            System.out.println("Excepion in  Manager Login_Screen");
        }
    }
    
    public void writeCustomertoFile(Customer customer){
        File f=new File("src\\Database\\" + "Current Login Customer.ser");
        try{
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(f));
            objectOutputStream.writeObject(customer);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch(EOFException e){
            return;
        }
        catch(Exception e){
            System.out.println("Excepion in Customer Login_Screen");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isAdminButtonClicked) { // Admin button is clicked
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            credentialsVerification login = new credentialsVerification(username, password, "admin");
            if(login.getAdmin()!=null && login.verfiyLogin()){
                dispose();
                new Admin_Panel();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password..", "Error as Title",JOptionPane.ERROR_MESSAGE);
            }

        } else if (isManagerButtonClicked) { // Manager button is clicked
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            credentialsVerification login = new credentialsVerification(username, password, "manager");
            if(login.getManager() == null){
                JOptionPane.showMessageDialog(null, "Invalid Username / Password!", "Login Error",JOptionPane.ERROR_MESSAGE);
            }
            else if (login.verfiyLogin()) {
                this.setManager(login.getManager());
                this.writeManagertoFile(getManager());
                dispose();
                new Manager_Panel();
            }

        } else {
            // Default login logic for customers
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            credentialsVerification login = new credentialsVerification(username, password, "customer");
            if (login.verfiyLogin()) {
                this.setCustomer(login.getCustomer());
                this.writeCustomertoFile(this.getCustomer());
                dispose();
                new Customer_Panel();

            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password..", "Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        new Login_Screen();
    }
}