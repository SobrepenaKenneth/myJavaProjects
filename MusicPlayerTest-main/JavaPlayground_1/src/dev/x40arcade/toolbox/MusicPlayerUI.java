package dev.x40arcade.toolbox;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MusicPlayerUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private VinylDisc vinyl;

    public MusicPlayerUI() {
        JFrame frame = new JFrame();
        ImageIcon image = new ImageIcon("logo.png");

        frame.setTitle("MusicPlayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setIconImage(image.getImage());

        // Use a LayeredPane for background layering
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 720);

        JLabel background = backgroundLabel();
        JLabel sideUI = sidebarLabel();

        vinyl = new VinylDisc("vinylDisc.png");
        vinyl.setBounds(900, 0, 720, 720); // Position and size for the vinyl

        layeredPane.add(background, Integer.valueOf(0));
        layeredPane.add(sideUI, Integer.valueOf(1));
        layeredPane.add(vinyl, Integer.valueOf(2));

        frame.setLayout(null); // Keep this since you're using absolute layout
        frame.add(layeredPane);

        // Add toggle button separately
        JButton toggleButton = new JButton("Toggle Vinyl");
        toggleButton.setBounds(550, 600, 150, 30); // Absolute position

        toggleButton.addActionListener(e -> {
        	if (vinyl.isRotating()) {
        	    vinyl.stop();
        	} else {
        	    vinyl.start();
        	}
        });

        layeredPane.add(toggleButton, Integer.valueOf(3));

        frame.setVisible(true); // Last
    }

    public JLabel backgroundLabel() {
        ImageIcon backgroundImage = new ImageIcon("background.png");
        Image img = backgroundImage.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0, 0, 1280, 720);
        return label;
    }

    public JLabel sidebarLabel() {
        ImageIcon sideImage = new ImageIcon("sidebar2.png");
        Image img1 = sideImage.getImage().getScaledInstance(720, 720, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img1);
        JLabel label1 = new JLabel(scaledIcon);
        label1.setBounds(-300, 0, 1280, 720);
        return label1;
    }

    public static void main(String[] args) {
        new MusicPlayerUI();
    }
}
