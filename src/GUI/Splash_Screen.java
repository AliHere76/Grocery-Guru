package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Splash_Screen {
    private static JWindow splash;
    private static JProgressBar progressBar;

    public Splash_Screen() {
        this.showSplashScreen();
        this.simulateLoadingTasks(this.splash);
    }

    private static void showSplashScreen() {
        splash = new JWindow();
        JPanel splashPanel = new JPanel(new BorderLayout());
        JLabel splashLabel = new JLabel(new ImageIcon("src\\Images\\Splash_Screen.png"));
        splashLabel.setBorder(new LineBorder(Color.black, 2));

        // Creating the progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setIndeterminate(false);
        progressBar.setForeground(Color.red);
        progressBar.setBorder(new LineBorder(Color.black, 2));

        // Adding components to the splashPanel
        splashPanel.add(splashLabel);
        splashPanel.add(progressBar, BorderLayout.SOUTH);
        

        // Adding the splashPanel to the content pane
        splash.getContentPane().add(splashPanel);
        splash.setSize(500, 320);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
    }

    private static void simulateLoadingTasks(JWindow splash) {
        try {
            for (int i = 0; i <= 100; i++) {
                progressBar.setValue(i);
                progressBar.setString("Loading: " + i + "%");
                Thread.sleep(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (splash != null) {
                splash.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new Splash_Screen();
    }
}
