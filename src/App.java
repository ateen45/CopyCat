// App.java -- Alan Cuevas

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class App {

    private static MyFrame frame;

    private static CatArray myCats;
    public static void main(String[] args) throws Exception {
        
        myCats = new CatArray();

        // frame settings
        frame = new MyFrame();

        // panels and border layouts
        MyPanel topPanel = new MyPanel(Color.pink);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        MyPanel mainPanel = new MyPanel(Color.pink);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        MyPanel bottomPanel = new MyPanel(Color.pink);

        // -------- TOP PANEL -------- // 

        // sub panels
        MyPanel titlePanel = new MyPanel(Color.pink);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 10));
        MyPanel buttonsPanel = new MyPanel(Color.pink);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 35));

        // title label
        MyLabel title = new MyLabel("CopyCat", "img/CopyCat_1.png");
        title.setFont(new Font("Kristen ITC", Font.PLAIN, 55));
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setHorizontalTextPosition(JLabel.RIGHT);
        title.setBorder(null);

        // buttons
        JButton newCatBtn = new JButton();
        newCatBtn.setBounds(0, 0, 100, 50);
        newCatBtn.setText("create a cat!");
        newCatBtn.setIcon(new ImageIcon("img/new_cat_1.png"));
        newCatBtn.addActionListener(action -> catGenerator(myCats));
        newCatBtn.setVerticalTextPosition(JButton.BOTTOM);
        newCatBtn.setHorizontalTextPosition(JButton.CENTER);
        newCatBtn.setBackground(null);
        newCatBtn.setBorder(null);

        JButton randomCaButton = new JButton();
        randomCaButton.setBounds(0, 0, 100, 50);
        randomCaButton.setText("random cat!");
        randomCaButton.setIcon(new ImageIcon("img/random_cat_1.png"));
        randomCaButton.addActionListener(action -> randomCatAction(randomCaButton, myCats));
        randomCaButton.setVerticalTextPosition(JButton.BOTTOM);
        randomCaButton.setHorizontalTextPosition(JButton.CENTER);
        randomCaButton.setBackground(null);
        randomCaButton.setBorder(null);


        // adding all components
        titlePanel.add(title);
        buttonsPanel.add(newCatBtn);
        buttonsPanel.add(randomCaButton);
        topPanel.add(titlePanel);
        topPanel.add(buttonsPanel);

        // -------- END TOP PANEL -------- //

        // -------- MAIN PANEL -------- //

        // JScrollPane w/ mainPanel
        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        MyPanel[] allSubPanels = new MyPanel[myCats.moods.length];

        for (int i = 0; i < myCats.moods.length; i++) {

            MyPanel pan = new MyPanel(null, 1000, 260);
            pan.setTag(myCats.moods[i]);
            allSubPanels[i] = pan;
        }

        // add panels to frame
        frame.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(mainPanel);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        // add subpanels to main panel
        for (int i = 0; i < allSubPanels.length; i++) {
            
            mainPanel.add(allSubPanels[i]);
        }

        for (int i = 0; i < allSubPanels.length; i++) {

            String path = String.format("img/%s_cat.png", myCats.moods[i]);
            MyLabel lab = new MyLabel(myCats.moods[i], path);

            allSubPanels[i].add(lab);
        }

        // add catButtons to subpanels
        for (int i = 0; i < myCats.baseList.size(); i++) {

            Cat c = myCats.baseList.get(i);
            CatButton btn = new CatButton(150, 150, c);

            for (int j = 0; j < allSubPanels.length; j++) {

                if (allSubPanels[j].getTag() == c.getTag()) {

                    allSubPanels[j].add(btn);
                }
            }
        }
        for (int i = 0; i < allSubPanels.length; i++) {

            JButton seeAllButton = new JButton();
            seeAllButton.setText("see all >>");
            String tag = allSubPanels[i].getTag();
            seeAllButton.addActionListener(action -> seeAll(tag, myCats));
            allSubPanels[i].add(seeAllButton);
        }

        // make visible at end
        frame.setVisible(true);
    }

    // ---------- FUNCTIONS ---------- //
    private static void catGenerator(CatArray myCats) {

        MakeACat catGen = new MakeACat(myCats);
        catGen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        catGen.setVisible(true);
    }

    private static void randomCatAction(JButton btn, CatArray cats) {

        // action 
        Random random = new Random();
        int choice = random.nextInt(cats.catsList.size());

        Cat c = cats.catsList.get(choice);
        ImageCopier cpy = new ImageCopier();
        String path = c.getPath();
        cpy.toClipboard(path);

        // feedback
        btn.setText("copied!"); 
        btn.setIcon(new ImageIcon("img/okay_cat_1.png"));
        // btn.setEnabled(false);

        // timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                btn.setIcon(new ImageIcon("img/random_cat_1.png"));
                btn.setText("random cat!");
                btn.setEnabled(true);
                }
            }, 500);
        
    }

    private static void seeAll(String tag, CatArray cats) {

        // redo the below code to create a PANEL into which add all the buttons... 
        // frame should have border layout so catspanel is at the CENTER and a generated label is set to the NORTH (picture???) with DROPDOWN

        MyFrame allCats = new MyFrame();
        allCats.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // top panel
        MyPanel topPanel = new MyPanel(null);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton backBtn = new JButton();
        backBtn.setText("<< back");
        backBtn.addActionListener(action -> allCats.dispose());

        JComboBox<String> dropdown = new JComboBox<String>(cats.moods);
        dropdown.setSelectedItem(tag);
        dropdown.addActionListener(action -> seeAll(dropdown.getSelectedItem().toString(), cats));
        // dropdown.addActionListener(action -> allCats.dispose());
        Timer timer = new Timer();
        dropdown.addActionListener(action -> timer.schedule(new TimerTask() {
            public void run() {

                allCats.dispose();
                }
            }, 350));

        // add to top panel
        topPanel.add(backBtn);
        topPanel.add(new MyLabel(tag, String.format("img/%s_cat.png", tag)));    // add title panel here
        topPanel.add(dropdown);
        // cats panel
        MyPanel catsPanel = new MyPanel(null);

        for (int i = 0; i < cats.catsList.size(); i++) {

            Cat c = cats.catsList.get(i);
            if (c.getTag() == tag) {

                catsPanel.add(new CatButton(150, 150, cats.catsList.get(i)));
            }
        }

        allCats.add(topPanel, BorderLayout.NORTH);
        allCats.add(catsPanel, BorderLayout.CENTER);
        allCats.setVisible(true);
    }
}