// CatButton.java -- Alan Cuevas
/*
 * this class is made special for the buttons that contain Cat objects to be copied
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.util.Timer;
import java.util.TimerTask;

public class CatButton extends JButton implements ActionListener {

    private Cat cat;

    CatButton(int w, int h, Cat cat) {

        this.cat = cat;

        this.setBounds(0, 0, w, h);
        this.setIcon(cat.getImage());
        this.addActionListener(this);
        this.setBackground(Color.lightGray); // 0xE7A5F0 for frame color
        // this.setForeground(Color.red);
        this.setBorder(BorderFactory.createEtchedBorder());
        
        this.setVerticalTextPosition(JButton.CENTER);
        this.setHorizontalTextPosition(JButton.CENTER);
        
    }

    CatButton(int w, int h) {

        this.setBounds(0, 0, w, h);
        // this.setIcon(cat.getImage());
        this.addActionListener(this);
        this.setBackground(Color.lightGray); // 0xE7A5F0 for frame color
        // this.setForeground(Color.red);
        this.setBorder(BorderFactory.createEtchedBorder());
        
        this.setVerticalTextPosition(JButton.CENTER);
        this.setHorizontalTextPosition(JButton.CENTER);

    }

    public Cat getCat() {

        return cat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {

            // feedback
            CatButton self = this;            
            self.setText("copied!");
            self.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
            self.setEnabled(false);

            // copy image
            ImageCopier cpy = new ImageCopier();
            String path = self.getCat().getPath();
            cpy.toClipboard(path);

            // erase feeback
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    self.setText(null);
                    self.setEnabled(true);
                }
            }, 1500);

            System.out.println("copied to clipboard!");
        }
    } 
    
}