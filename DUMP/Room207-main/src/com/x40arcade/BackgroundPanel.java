package com.x40arcade;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;

public class BackgroundPanel extends JPanel implements BgMenu {
    private static final long serialVersionUID = 1L;

    // Variables
    private Image background;
    private Image middleground;
    private Image foreground;

    private Image Room207;
    
    public BackgroundPanel() {
    	scene1();
    }

	public void background() {
		background = new ImageIcon(getClass().getResource("/assets/MainMenu/o-1.png")).getImage();
		
	}

	
	public void midBackground() {
		middleground = new ImageIcon(getClass().getResource("/assets/MainMenu/o-2.png")).getImage();
		
	}

	
	public void forGround() {
		foreground = new ImageIcon(getClass().getResource("/assets/MainMenu/o-3.png")).getImage();
	}

	
	public void titleText() {
		Room207 = new ImageIcon(getClass().getResource("/assets/MainMenu/o-4.png")).getImage();
	}
	
	public void scene1() {
		background();
    	midBackground();
    	forGround();
    	titleText();
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(middleground, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(foreground, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(Room207, 380, 40, 582, 136, this);
    }






}
