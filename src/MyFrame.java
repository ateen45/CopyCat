// MyFrame.java -- Alan Cuevas
/*
 * base frame for all windows
 */

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;

public class MyFrame extends JFrame {
    
    public MyFrame() {

        this.setTitle("CopyCat");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024, 600);
        this.getContentPane().setBackground(Color.pink); // OxFCD4FF or Color.pink
        this.setLayout(new BorderLayout(5, 5)); // layout manager
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);      

        // logo
        ImageIcon logo = new ImageIcon("img/CopyCat.png");
        this.setIconImage(logo.getImage());

    }    
}
