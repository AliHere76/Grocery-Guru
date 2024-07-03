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
import Stores.Store;
import Users.Manager;
import Users.UserWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Manage_Manager extends JFrame implements ActionListener {
    JLabel IDLabel, nameLabel, emailLabel, salaryLabel, storeLocationLabel, usernameLabel, passwordLabel;
    JTextField IDTextField, nameTextField, emailTextField, salaryTextField, storeLocationTextField, usernameTextField, passwordTextField;
    JButton ADD;
    String ID;
    ImageIcon icon;

    public Manage_Manager() {}
    
    public Manage_Manager(String ID) {
        this.ID = ID;
    }

    public void addManagerFrame() {
        icon = new ImageIcon("src\\Images\\Company_Logo.png");
        setTitle("Store Creation");
        setIconImage(icon.getImage());
        setSize(300, 257);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#0D1152"));
        setResizable(false);

        storeLocationLabel = new JLabel("Store Location:");
        IDLabel = new JLabel("Manager ID:");
        nameLabel = new JLabel("Manager Name:");
        usernameLabel = new JLabel("Manager Username:");
        passwordLabel = new JLabel("Manager Password:");
        emailLabel = new JLabel("Manager Email:");
        salaryLabel = new JLabel("Manager Salary:");

        storeLocationLabel.setForeground(Color.WHITE);
        IDLabel.setForeground(Color.WHITE);
        nameLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        salaryLabel.setForeground(Color.WHITE);
        
        storeLocationLabel.setBounds(39, 10, 90, 20);
        IDLabel.setBounds(57, 35, 90, 20);
        nameLabel.setBounds(38, 60, 90, 20);
        usernameLabel.setBounds(13, 85, 120, 20);
        passwordLabel.setBounds(16, 110, 120, 20);
        emailLabel.setBounds(40, 135, 90, 20);
        salaryLabel.setBounds(36, 160, 90, 20);

        IDTextField = new JTextField();
        nameTextField = new JTextField();
        emailTextField = new JTextField();
        salaryTextField = new JTextField();
        storeLocationTextField = new JTextField();
        usernameTextField = new JTextField();
        passwordTextField = new JTextField();

        storeLocationTextField.setBounds(140, 10, 120, 20);
        IDTextField.setBounds(140, 35, 120, 20);
        nameTextField.setBounds(140, 60, 120, 20);
        usernameTextField.setBounds(140, 85, 120, 20);
        passwordTextField.setBounds(140, 110, 120, 20);
        emailTextField.setBounds(140, 135, 120, 20);
        salaryTextField.setBounds(140, 160, 120, 20);

        ADD = new Custom_Button("Create");
        ADD.setPreferredSize(new Dimension(100,20));
        ADD.addActionListener(this);

        JPanel ADDPanel = new JPanel();
        ADDPanel.setBackground(Color.BLACK);
        ADDPanel.add(ADD);
        ADDPanel.setBounds(0, 187, getWidth(), 33);

        add(storeLocationLabel);
        add(IDLabel);
        add(nameLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(emailLabel);
        add(salaryLabel);
        add(storeLocationTextField);
        add(IDTextField);
        add(nameTextField);
        add(usernameTextField);
        add(passwordTextField);
        add(emailTextField);
        add(salaryTextField);
        add(ADDPanel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addingStore();
        dispose();
    }
    
    // Method to add the new Store
    public void addingStore() {
        String ID = IDTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String salary = salaryTextField.getText();
        String location = storeLocationTextField.getText().toLowerCase();

        Manager manager = new Manager(ID, name, username, password, email, salary, location);

        ArrayList<Manager> managerList = new ArrayList<>();
        managerList = UserWriter.LoadArrayList(managerList);

        ArrayList<Store> storeList = new ArrayList<>();
        storeList = UserWriter.LoadStoresList(storeList);

        if (storeList != null) {
            credentialsVerification c = new credentialsVerification();
            if(c.isUniqueStore(location)){
                if (c.isUniqueManager(ID)) {
                    if (c.isUniqueManager(username)) {
                        if (c.isUniqueManager(email)) {
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "Manager email already exists.");
                            return;
                        }
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Manager username already exists.");
                        return;
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Manager ID already exists.");
                    return;
                }
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Store at this location already exists!");
                return;    
            }
        } else if (storeList == null) {
            storeList = new ArrayList<>();
        }

        Store newStore = new Store(manager,location);
        newStore.createStore();
        storeList.add(newStore);
        UserWriter.WriteToFileStores(storeList);
    }
    
    public static void main(String[] args) {
        Manage_Manager m = new Manage_Manager();
        m.addManagerFrame();
    }
}


