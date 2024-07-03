/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ASUS TUFF
 */

import java.awt.*;
import javax.swing.*;

public class TermsAndCondition_Screen extends JFrame {
    ImageIcon icon;
    JLabel logo,text;

    public TermsAndCondition_Screen() {
        super("Terms & Condition");

        icon = new ImageIcon("src\\Images\\Company_Logo.png");

        // Setting Frame
        setIconImage(icon.getImage());
        setSize(510, 725);
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setLayout(null);
        
        getContentPane().setBackground(Color.decode("#0D1152"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String Text = "<html>" +
                    "<h2 style='text-align: center;'>Grocery Guru Terms and Conditions</h2>" +
                    "<p style='text-align: justify;'>Welcome to Grocery Guru! Before you proceed, we kindly ask you to review our terms and conditions carefully. By logging in as a new customer, you acknowledge and agree to the following terms:</p>" +
                    "<h3>1. Account Registration</h3>"+
                    "<p> - Users must provide accurate information during account registration.</p>"+
                    "<p> - Users are responsible for maintaining the confidentiality of their account credentials.</p>"+
                    "<h3>2. Privacy and Security</h3>" +
                    "<p> - We respect user privacy; refer to our Privacy Policy for details.</p>"+
                    "<p> - Users are responsible for their accounts and reporting unauthorized access.</p>"+
                    "<h3>3. Product Ordering</h3>" +
                    "<p> - Product availability is subject to change.</p>" +
                    "<p> - Delivery times are estimates and may vary.</p>"+
                    "<h3>4. User Conduct</h3>" +
                    "<p> - Users agree not to use the platform for unlawful purposes.</p>" +
                    "<p> - Respectful behavior towards other users is expected.</p>"+
                    "<h3>5. Changes to Terms</h3>" +
                    "<p> - We may update terms; users should review periodically.</p>" +
                    "<p> - Continued use after changes implies acceptance.</p>"+
                    "<p></p>"+
                    "<p style='text-align: center;'><b>Thank you for choosing Grocery Guru! If you have any questions or concerns, please contact us at +92-319-9263327.</b></p>" +
                    "" +
                    "</html>";
        
        // Setting a label for logo
        logo = new JLabel(icon);
        logo.setMaximumSize(new Dimension(350,300));
        logo.setBounds(190,35, 100, 100);

        // Setting a Lebel for text
        text = new JLabel(Text);
        text.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        text.setForeground(Color.WHITE);
        text.setHorizontalTextPosition(JLabel.CENTER);
        text.setVerticalTextPosition(JLabel.BOTTOM);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setBounds(23,100,460,600);
        
        // Adding components in the Frame
        add(logo);
        add(text);
        setResizable(false);
        setVisible(true);

    }
    public static void main(String[] args) {
        new TermsAndCondition_Screen();
    }
}