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
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome_Screen extends JFrame implements ActionListener{
    
    ImageIcon icon;
    JLabel text;
    JButton login, signUp, admin, manager, about, exit;

    public Welcome_Screen() {
        super("Grocery Guru");
        icon = new ImageIcon("src\\Images\\Company_Logo.png");
        
        // Setting the main Frame
        setSize(850, 670);
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(Color.decode("#0D1152"));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        // Creating a container panel for the center
        JPanel centerContainer = new JPanel();
        centerContainer.setBackground(Color.decode("#0D1152"));
        icon = new ImageIcon("src\\Images\\Welcome_Screen.png");
        JLabel image = new JLabel(icon);
        image.setSize(600,600);
        centerContainer.add(image);
        
        // Creating the MENU bar to put on top of Buttons
        text = new JLabel();
        text.setText("  MENU  ");
        text.setMaximumSize(new Dimension(157,30));
        text.setBorder(new EtchedBorder());
        text.setForeground(Color.WHITE);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setFont(new Font("Times New Roman", Font.BOLD, 18));
            
        //Creating Buttons         
        login = new Custom_Button("  Customer Login  ");
        login.addActionListener(this);
        signUp = new Custom_Button("Customer Sign Up "); 
        signUp.addActionListener(this);
        admin = new Custom_Button("     Admin Login    ");
        admin.addActionListener(this);
        manager = new Custom_Button("   Manager Login  ");
        manager.addActionListener(this);
        about = new Custom_Button("        About Us        ");
        about.addActionListener(this);
        exit = new Custom_Button("            Exit            ");
        exit.addActionListener(this);
        

        // Creating a ButtonPanel and Adding the MENU bar & buttons in the ButtonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(225,600));
        buttonPanel.setBackground(Color.decode("#0D1152"));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(170));
        buttonPanel.add(text);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(admin);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(manager);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(login);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(signUp);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(about);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exit);
        

        // Creating Copy Rights & Name Label for South Region
        int copyrightSymbolCodePoint = 169;
        String s = Character.toString(copyrightSymbolCodePoint);
        JLabel rightsLabel = new JLabel("Copyright " + s + " 2023, Grocery Guru, All rights reserved.");
        rightsLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        rightsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightsLabel.setBackground(Color.BLACK);

        JLabel contributionLabel = new JLabel("Developed by FA22-BCS-053 | Muhammad Ali");
        contributionLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        contributionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contributionLabel.setBackground(Color.BLACK);

        //Creating panel for south region and Adding Labels
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(rightsLabel,BorderLayout.NORTH);
        bottomPanel.add(contributionLabel,BorderLayout.CENTER);

        // Adding all the components in the main Frame
        add(bottomPanel, BorderLayout.SOUTH);
        add(centerContainer,BorderLayout.WEST);
        add(buttonPanel, BorderLayout.EAST);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUp) {
            new SignUp_Screen();
        } else if (e.getSource() == login) {
            new Login_Screen(false, false);
        } else if (e.getSource() == admin) {
            new Login_Screen(true, false);
        } else if (e.getSource() == manager) {
            new Login_Screen(false, true);
        } else if (e.getSource() == about) {
            new About_Screen();
        } else if(e.getSource() == exit) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new Welcome_Screen();
    }
}
