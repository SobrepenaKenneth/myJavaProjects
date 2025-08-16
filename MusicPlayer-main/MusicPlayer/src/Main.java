
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main extends JFrame {

    Main() {
        //Border test
        //Border border = BorderFactory.createLineBorder(Color.black, 3);

        //Make an art for redPanel 980x250
        // hope this works animation test
        // Green Panel button
        playButton animatedButton = new playButton();

        // Acosta pixel art
        ImageIcon acostaArt1 = new ImageIcon("src/assets/Acosta_art.png");
        Image scaled2 = acostaArt1.getImage().getScaledInstance(990, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel2 = new JLabel(new ImageIcon(scaled2));
        imageLabel2.setBounds(0, 0, 990, 250);

        //Image for the blue panel
        ImageIcon table = new ImageIcon("src/assets/table.png");
        Image scaled = table.getImage().getScaledInstance(980, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel1 = new JLabel(new ImageIcon(scaled));
        imageLabel1.setBounds(0, 0, 980, 100);

        // Image Button test
        JButton imagePlayButton = new JButton();
        ImageIcon btnPlay = new ImageIcon("src/assets/button.png");
        imagePlayButton.setIcon(btnPlay);
        imagePlayButton.setBorder(null);
        imagePlayButton.setContentAreaFilled(false);

        //Menu Bars
        JMenuBar menuBar = new JMenuBar(); //Constructor

        //Menu in the top
        JMenu filemenu = new JMenu("File"); //Methods
        JMenu editmenu = new JMenu("Edit");
        JMenu helpmenu = new JMenu("Help");

        menuBar.add(filemenu);
        menuBar.add(editmenu);
        menuBar.add(helpmenu);

        //Menu items
        JMenuItem loadItem = new JMenuItem("Import");
        JMenuItem saveItem = new JMenuItem("save");
        JMenuItem exitItem = new JMenuItem("exit");

        filemenu.add(loadItem);
        filemenu.add(saveItem);
        filemenu.add(exitItem);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

        //Panels colored panel you see in the screen
        JPanel buttonPlay = new JPanel();
        buttonPlay.setBackground(Color.GREEN);
        buttonPlay.setBounds(40, 150, 129, 60);
        buttonPlay.setOpaque(true);

        JPanel buttonPause = new JPanel();
        buttonPause.setBackground(Color.pink);
        buttonPause.setBounds(200, 150, 129, 60);
        buttonPause.setLayout(null);

        JPanel buttonStop = new JPanel();
        buttonStop.setBackground(Color.CYAN);
        buttonStop.setBounds(360, 150, 129, 60);

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 240, 980, 250);
        redPanel.setOpaque(true);
        redPanel.setLayout(null);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLACK);
        bluePanel.setBounds(0, 140, 980, 100);
        bluePanel.setLayout(null);
        bluePanel.setOpaque(true);

        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        yellowPanel.setBounds(0, 0, 1000, 140);

        // JFrame for the screen
        // The green panel (button) is sized 129x60
        this.setBackground(Color.BLACK); //Method
        this.setTitle("MusicPlayer by: x40Arcade");
        this.setSize(995, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// method to exit program when window closes 
        this.setLayout(null);
        this.setResizable(false);
        // Add the play button to the green panel
        buttonPlay.setLayout(null); // important for setBounds to work
        imagePlayButton.setBounds(0, 0, 129, 60); // Set bounds for the button

        // PANEL STUFF
        this.add(buttonPause);
        buttonPause.add(animatedButton);
        bluePanel.add(imageLabel1);
        redPanel.add(imageLabel2);
        this.add(buttonStop);
        this.add(buttonPlay);
        this.add(bluePanel);
        this.add(redPanel);
        this.add(yellowPanel);
        this.setVisible(true);
        //this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black, 3));

        ImageIcon icon = new ImageIcon("src/assets/icon.jpg"); // Constructor for an image icon
        this.setIconImage(icon.getImage());

        // Action listener to check if the button is pressed
        imagePlayButton.addActionListener(e -> System.err.println("Image Button pressed"));
    }

    public static void main(String[] args) {
        Main mainFrame = new Main();
        mainFrame.setVisible(true);
    }
}
