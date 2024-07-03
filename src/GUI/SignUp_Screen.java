/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ASUS TUFF
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Login_SignUp_Validation.credentialsVerification;
import Stores.Store;
import Users.Customer;
import Users.DateOfBirth;
import Users.Person;
import Users.UserWriter;


public class SignUp_Screen extends JFrame implements ActionListener{
    JLabel signUpImage, firstName, lastName, username, userEmail, mobileNo, CNIC, location, password, retypePassword;
    JTextField forEmail, forUsername, forFirstName, forLastName, forMobileNo, forCNIC;
    JComboBox<String> locationComboBox;
    JPasswordField forPassword, forRetypePassword;

    ArrayList<Store> storesList = new ArrayList<>();
    JCheckBox acceptTerms;
    JButton terms,register;
    String[] forLocation;
    ImageIcon icon;


    public SignUp_Screen() {
        super("Sign Up");
        this.storesList = UserWriter.LoadStoresList(storesList);

        this.forLocation = getLocationsList();

        icon = new ImageIcon("src\\Images\\Company_Logo.png");
        
        // Setting the Frame
        setIconImage(icon.getImage());
        setSize(600, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#0D1152"));
        setLayout(null);
        setResizable(false);

        // Setting up the JLabel for Image
        signUpImage = new JLabel();
        signUpImage.setIcon(icon);
        signUpImage.setBounds(230, 0, icon.getIconWidth(), icon.getIconHeight());

        // Setting up the JLabel for First name & it's textfield
        firstName = new JLabel();
        firstName.setText("First Name:");
        firstName.setForeground(Color.WHITE);
        firstName.setBounds(45, 140, 100, 20);
        forFirstName = new JTextField();
        forFirstName.setBounds(120, 140, 140, 20);

        // Setting up the JLabel for Last Name & it's textfield
        lastName = new JLabel();
        lastName.setText("Last Name:");
        lastName.setForeground(Color.WHITE);
        lastName.setBounds(315, 140, 100, 20);
        forLastName = new JTextField();
        forLastName.setBounds(390, 140, 140, 20);

        // Ssetting up the JLabel for Username & it's textfield
        username = new JLabel();
        username.setText("Username: ");
        username.setForeground(Color.WHITE);
        username.setBounds(45, 180, 100, 20);
        forUsername = new JTextField();
        forUsername.setBounds(120, 180, 140, 20);

        // Setting up the JLabel for email & it's textfield
        userEmail = new JLabel();
        userEmail.setText("Email: ");
        userEmail.setForeground(Color.WHITE);
        userEmail.setBounds(343, 180, 80, 20);
        forEmail = new JTextField();
        forEmail.setBounds(390, 180, 140, 20);

        // Setting up the JLabel for Mobile Number & it's textfield
        mobileNo = new JLabel();
        mobileNo.setText("Cell Number:");
        mobileNo.setForeground(Color.WHITE);
        mobileNo.setBounds(32, 220, 100, 20);
        forMobileNo = new JTextField();
        forMobileNo.setBounds(120, 220, 140, 20);

        // Setting up the JLabel for CNIC & it's textfield
        CNIC = new JLabel();
        CNIC.setText("CNIC: ");
        CNIC.setForeground(Color.WHITE);
        CNIC.setBounds(344, 220, 100, 20);
        forCNIC = new JTextField();
        forCNIC.setBounds(390, 220, 140, 20);

        // Setting up the JLabel for Location & it's textfield
        location = new JLabel();
        location.setText("Location: ");
        location.setForeground(Color.WHITE);
        location.setBounds(53, 260, 100, 20);
        locationComboBox = new JComboBox<>(forLocation);
        locationComboBox.setSelectedItem(forLocation[0]);
        locationComboBox.setBounds(120, 260, 140, 20);

        // Setting up the JLabel for Password & it's textfield
        password = new JLabel();
        password.setText("Password: ");
        password.setForeground(Color.WHITE);
        password.setBounds(47, 300, 100, 20);
        forPassword = new JPasswordField();
        forPassword.setBounds(120, 300, 140, 20);

        // Setting up the JLabel for Re-typing password & it's textfield
        retypePassword = new JLabel();
        retypePassword.setText("Confirm Pass:");
        retypePassword.setForeground(Color.WHITE);
        retypePassword.setBounds(295, 300, 100, 20);
        forRetypePassword = new JPasswordField();
        forRetypePassword.setBounds(390, 300, 140, 20);

        // Setting up a checkbox for acceptance of Terms and Condition
        acceptTerms = new JCheckBox("By ticking, you are confirming that you agree to our Terms & Conditions.");
        acceptTerms.setBackground(Color.decode("#0D1152"));
        acceptTerms.setFont(new Font("Arial", Font.BOLD, 12));
        acceptTerms.setForeground(Color.WHITE);
        acceptTerms.setBounds(85, 340, 500, 20);
        
        // Setting up a terms button
        terms = new Custom_Button("TERMS & CONDITIONS");
        terms.setFont(new Font("Times New Roman", Font.BOLD, 16));
        terms.setForeground(Color.WHITE);
        terms.setBackground(Color.decode("#0379D1"));
        terms.setBounds(185, 375, 220, 30);
        terms.addActionListener(this);
        
        // Setting up a register button
        register = new Custom_Button("REGISTER");
        register.setFont(new Font("Times New Roman", Font.BOLD, 16));
        register.setForeground(Color.WHITE);
        register.setBackground(Color.decode("#0379D1"));
        register.setBounds(215, 420, 160, 30);
        register.addActionListener(this);

        // Adding al the components in the JFrame
        add(signUpImage);
        add(firstName);
        add(forFirstName);
        add(lastName);
        add(forLastName);
        add(forUsername);
        add(username);
        add(userEmail);
        add(forEmail);
        add(mobileNo);
        add(forMobileNo);
        add(CNIC);
        add(forCNIC);
        add(location);
        add(locationComboBox);
        add(password);
        add(forPassword);
        add(retypePassword);
        add(forRetypePassword);
        add(acceptTerms);
        add(terms);
        add(register);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Fetching text from Text Fields
        String getFirstName = forFirstName.getText();
        String getLastName = forLastName.getText();
        String getUsername = forUsername.getText();
        String getPassword = new String(forPassword.getPassword());
        String getRetypePassword = new String(forRetypePassword.getPassword());
        String getEmail = forEmail.getText();
        String getCNIC = forCNIC.getText();
        String getMobileNumber = forMobileNo.getText();
        String getLocation = forLocation[0];
        
        if (e.getSource() == terms) {
            new TermsAndCondition_Screen();
            return;
        }
        else if (e.getSource() == register) {
            getLocation = (String) locationComboBox.getSelectedItem();
        }
        
        // Checking if any of the fields is empty
        if (!getFirstName.isEmpty() && !getLastName.isEmpty() && !getUsername.isEmpty() && !getPassword.isEmpty() && !getRetypePassword.isEmpty() && !getEmail.isEmpty() && !getCNIC.isEmpty() && !getMobileNumber.isEmpty()) {
            // Checks if username already exists
            if (checkUsername(getUsername)) {
                // Checks if Password and Re-Type Passwords are same or not
                if (getPassword.equals(getRetypePassword)) {
                    // Checks if Password meets the requirements
                    if (isValidPassword(getPassword)) {
                        // Checks an Email's validity and if it already exists
                        if (validateEmail(getEmail)) {
                            // Checks if Mobile Number is valid or not
                            if (isValidMobileNumber(getMobileNumber)) {
                                //Checks if CNIC already exists
                                if (validateCNIC(getCNIC)) {
                                    // Checks if Terms & Condition is checked or not
                                    if (acceptTerms.isSelected()) {
                                        Customer customer = new Customer(getFirstName, getLastName, getUsername, getEmail, getPassword, getLocation, new DateOfBirth("", "", ""), getCNIC);
                                        ArrayList<Person> customerList = new ArrayList<>();
                                        customerList = UserWriter.LoadArrayList(customerList, "customer");
                                        if (customerList != null) {
                                            credentialsVerification c = new credentialsVerification("customer");
                                            if (c.isUnique(getUsername)) {
                                                if (c.isUnique(getEmail)) {
                                                    if (c.isUnique(getCNIC)) {
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "CNIC already exists.");
                                                        return;
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Email already exists.");
                                                    return;
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Username already exists.");
                                                return;
                                            }
                                        }
                                        if (customerList == null) {
                                            customerList = new ArrayList<>();
                                        }
                                        customerList.add(customer);
                                        UserWriter.WriteToFile(customerList, "customer");
                                        JOptionPane.showMessageDialog(null, "Registered SuccessFully");
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Please accept Terms & Conditions");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, getCNIC + " is an invalid CNIC.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, getMobileNumber + " is an invalid Mobile number.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, getEmail + " is an invalid email address.\nOnly Gmail,Hotmail and Yahoo mail are supported");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Password does not meet requirements");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Password mismatch");
                }
            } else {
                JOptionPane.showMessageDialog(null, getUsername + " is an invalid username.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Some of the fields are empty");
        }
    }

    // Method to check the validity of Username
    public static boolean checkUsername(String username) {
        String pattern = "^[a-zA-Z0-9_]+$";
        Pattern usernamePattern = Pattern.compile(pattern);
        return usernamePattern.matcher(username).matches();
    }

    // Method to check the validity of Password
    private static boolean isValidPassword(String password) {
        // Defining the criterias for validation
        int minLength = 8;
        int minUpperCase = 1;
        int minLowerCase = 1;
        int minDigits = 1;
        int minSpecialChars = 1;

        if (password.length() < minLength) {
            return false;
        }

        int upperCaseCount = 0;
        int lowerCaseCount = 0;
        int digitCount = 0;
        int specialCharCount = 0;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCaseCount++;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseCount++;
            } else if (Character.isDigit(ch)) {
                digitCount++;
            } else {
                specialCharCount++;
            }
        }

        return upperCaseCount >= minUpperCase && lowerCaseCount >= minLowerCase && digitCount >= minDigits && specialCharCount >= minSpecialChars;
    }

    // Method to check the validity of Email
    public static boolean validateEmail(String email){
        String pattern = "^[a-z0-9_.+-]+@(gmail|hotmail|yahoo)\\.com$";
        Pattern emailPattern = Pattern.compile(pattern);
        return emailPattern.matcher(email).matches();
    }

    // Method to check the validity of CNIC
    public static boolean validateCNIC(String cnic) {
        cnic = cnic.replaceAll("[\\s-]", "");
        if (cnic.length() != 13 || !cnic.matches("\\d+")) {
            return false;
        }
        return true;
    }

    //Method to check the validity of Mobile Number
    public static boolean isValidMobileNumber(String mobileNumber) {
        if (mobileNumber.startsWith("03")) {
            return mobileNumber.length() == 11 && mobileNumber.matches("\\d+");
        } else if (mobileNumber.startsWith("+92")) {
            return mobileNumber.length() == 13 && mobileNumber.substring(1).matches("\\d+");
        }
        return false;
    }

    // Method to store the List of Stores in an Array
    public String[] getLocationsList() {
        String[] str = new String[storesList.size()];
        int i = 0;
        for (Store store : storesList) {
            str[i++] = store.getLocation().substring(0, 1).toUpperCase() + store.getLocation().substring(1);;
        }
        return str;
    }

    public static void main(String[] args) {
        new SignUp_Screen();
    }
}
