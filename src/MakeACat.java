import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class MakeACat extends MyFrame {

    private MyLabel preview;
    private boolean pictureChosen = false;
    private boolean moodChosen = false;
    
    private String mood = null;
    private String path = null;

    CatArray myCats;

    // constructor 
    MakeACat(CatArray cats) {

        /* ---------- things to add ----------
         * title / label
         * empty rectangle that populates with chosen picture
         * tag chooser (can only have one tag)
         * creation button that adds new cat and cat button to the list
         * back button that exits the window into main
         */

        myCats = cats;

        // top panel
        MyPanel topPanel = new MyPanel(null);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        
        JButton backButton = new JButton();
        backButton.setText("<< back");
        backButton.addActionListener(action -> this.dispose());

        JLabel title = new JLabel();
        title.setText(" CAT MAKER!");
        title.setIcon(new ImageIcon("img/new_cat.png"));
        title.setFont(new Font("Kristen ITC", Font.PLAIN, 30));

        // add to top panel
        topPanel.add(backButton);
        topPanel.add(title);

        // main panel
        MyPanel mainPanel = new MyPanel(null);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // preview panel
        MyPanel previewPanel = new MyPanel(null);
        previewPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        preview = new MyLabel("[preview]", null);
        preview.setHorizontalAlignment(JLabel.CENTER);
        preview.setPreferredSize(new Dimension(300, 300));

        // add to preview panel
        previewPanel.add(preview);

        // buttons panel
        MyPanel buttonsPanel = new MyPanel(null);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JButton imageButton = new JButton();
        imageButton.setText("choose file >>");
        imageButton.addActionListener(action -> chooseFile());

        // radio buttons that choose mood
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < myCats.moods.length; i++) {

            String m = myCats.moods[i];
            JRadioButton btn = new JRadioButton();

            btn.setText(m);
            btn.setBackground(null);
            btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

            btn.addActionListener(action -> this.moodChosen = true);
            btn.addActionListener(action -> this.mood = m);

            group.add(btn);
            buttonsPanel.add(btn);
        }

        JButton generateButton = new JButton();
        // generateButton.setText("generate");
        generateButton.setIcon(new ImageIcon("img/generate_button.png"));
        generateButton.setBackground(null);
        generateButton.setBorder(null);

        generateButton.addActionListener(action -> generate());

        
        // add to buttons panel
        buttonsPanel.add(imageButton);
        // mainPanel.add(generateButton);

        // add to main panel
        mainPanel.add(previewPanel);
        mainPanel.add(buttonsPanel);
        mainPanel.add(generateButton);

        // add to THIS
        this.add(topPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

    }

    private void chooseFile() {

        JFileChooser chooser = new JFileChooser();
        int response = chooser.showOpenDialog(null);
       
        if (response == JFileChooser.APPROVE_OPTION) {
            
            path = chooser.getSelectedFile().getAbsolutePath();
        }
        System.out.println(path);

        ImageIcon img = new ImageIcon(path);
		preview.setIcon(img);
        preview.setText(path);

        pictureChosen = true;
    }

    private void generate() {

        if (pictureChosen && moodChosen) {

            myCats.catsList.add(new Cat(path, mood));
            JOptionPane.showMessageDialog(null, String.format("Your %s cat has been generated!", mood), "Congratulations! :)", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        }
        else {

            JOptionPane.showMessageDialog(null, "Please ensure that you have both a MOOD and FILE selected.", "NOTICE! :/", JOptionPane.WARNING_MESSAGE);
        }

    }
}
