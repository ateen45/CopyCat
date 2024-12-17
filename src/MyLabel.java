// MyLabel.java -- Alan Cuevas
/*
 * base label for the labels of the emotions in main frame
 */

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyLabel extends JLabel {

	MyLabel(String txt, String imgURL) {

		this.setForeground(Color.BLACK);
		this.setBackground(null);
		this.setOpaque(true);
		
		this.setFont(new Font("Kristen ITC", Font.PLAIN, 20));
		this.setText(txt);
		this.setVerticalTextPosition(JLabel.BOTTOM);
		this.setHorizontalTextPosition(JLabel.CENTER);

		Border border = BorderFactory.createLineBorder(Color.black, 5);
		this.setBorder(border);

		this.setVerticalAlignment(JLabel.CENTER);
		
		ImageIcon img = new ImageIcon(imgURL);
		this.setIcon(img);
	}
}
