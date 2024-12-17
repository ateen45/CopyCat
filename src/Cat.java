// Cat.java -- Alan Cuevas
/*
 * this is the class that defines a "Cat," an object with an image, its URL, and a defining tag
 */

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Cat implements Icon {

    private ImageIcon img;
    private String path;
    private String tag;
    
    Cat(String imgPath, String tag) {

        this.path = imgPath;
        this.img = new ImageIcon(imgPath);
        this.tag = tag;
    }

    // getter methods
    public ImageIcon getImage() {

        return img;
    }

    public String getTag() {

        return tag;
    }

    public String getPath() {

        return path;
    }

    // useless stuff I don't understand
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'paintIcon'");
    }

    @Override
    public int getIconWidth() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIconWidth'");
    }

    @Override
    public int getIconHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIconHeight'");
    }
    
}
