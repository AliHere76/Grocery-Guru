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

public class Custom_Button extends JButton{
    public Custom_Button(String Label){
        super(Label);
        this.setFocusable(true);
        this.setFont(new Font("Times New Roman", Font.BOLD, 16));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
