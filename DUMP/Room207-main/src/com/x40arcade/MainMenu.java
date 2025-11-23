package com.x40arcade;

//IMPORTS FROM PACKAGES
import javax.swing.*; // for JFrame, JPanel, JButton, etc.
import java.awt.*;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
	private static final long serialVersionUID = 1L;

	//This is a constructor it has the same name as the class
	public MainMenu() {
		JFrame frame = new JFrame("Room 207 - Game Screen");
		
		frame.setSize(1980, 1280); //set nung size
		frame.setBackground(Color.BLACK);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close the app
		frame.setLocationRelativeTo(null);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	    gd.setFullScreenWindow(frame);// close the window on screen
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setPreferredSize(new Dimension(1280, 720));
	    
	    BackgroundPanel backgroundImage = new BackgroundPanel();
	    backgroundImage.setOpaque(false);
	    backgroundImage.setBounds(0, 0, 1280, 720);
	    backgroundImage.scene1();
	    
		ButtonPanel newGameBtn = new ButtonPanel();
		newGameBtn.setOpaque(false);
		newGameBtn.setBounds(0, 0, 1280, 720);
		
		layeredPane.add(backgroundImage, Integer.valueOf(0));
		layeredPane.add(newGameBtn, Integer.valueOf(1));
		
        frame.setContentPane(layeredPane);
        frame.pack();
        frame.setVisible(true);
        
	}

	// think of this one that runs everything
	public static void main(String[] args) {
		new MainMenu();
	}

}
