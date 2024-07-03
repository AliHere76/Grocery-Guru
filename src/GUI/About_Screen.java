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

public class About_Screen extends JFrame {

    ImageIcon icon;
    JLabel logo,text;

    public About_Screen() {
        super("About Us");

        icon = new ImageIcon("src\\Images\\Company_Logo.png");

        // Setting Frame
        setIconImage(icon.getImage());
        setSize(460, 380);
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setLayout(null);
        
        getContentPane().setBackground(Color.decode("#0D1152"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        String Text = "<html>"
                + "<p style='text-align: center;'>"
                + "Welcome to Grocery Guru, your one-stop solution for all your grocery "
                + "needs! At Grocery Guru, we are dedicated to providing you with a seamless"
                + "and convenient shopping experience. Browse through our wide range of "
                + "high-quality products, from fresh produce to household essentials. Our "
                + "mission is to make grocery shopping easy, affordable, and enjoyable for "
                + "you and your family. Feel free to explore our online platform and discover"
                + "the best deals on your favorite brands. </p>"
                + ""
                + "<p>Thank you for choosing Grocery Guru as your trusted grocery partner!</p>"
                + "</html>";
        
        // Setting a label for logo
        logo = new JLabel(icon);
        logo.setMaximumSize(new Dimension(350,300));
        logo.setBounds(170,35, 100, 100);

        // Setting a Lebel for text
        text = new JLabel(Text);
        text.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        text.setForeground(Color.WHITE);
        text.setHorizontalTextPosition(JLabel.CENTER);
        text.setVerticalTextPosition(JLabel.BOTTOM);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setBounds(20,130,410,200);
        
        // Adding components in the Frame
        add(logo);
        add(text);
        setResizable(false);
        setVisible(true);

    }
    public static void main(String[] args) {
        new About_Screen();
    }
}
