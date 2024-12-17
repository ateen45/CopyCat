// MyPanel.java -- Alan Cuevas
/*
 * this will be the base for the top, main, and bottom panels
 * will need to be instantiated with parameters to meet each different requirement
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

    private String tag;
    private Color col = null;

    // constructors
    
    MyPanel(Color color) {

        col = color;
        this.setBackground(col);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
    }

    MyPanel(Color color, int w, int h) {

        col = color;
        this.setBackground(color);
        this.setPreferredSize(new Dimension(w, h));
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
    }

    // methods 

    public void setTag(String tag) {
        
        this.tag = tag;
    }
    
    public String getTag() {
        
        return tag;
    }
    
}
