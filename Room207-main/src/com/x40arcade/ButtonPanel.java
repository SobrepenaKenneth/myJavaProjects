package com.x40arcade;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	
	private Image NewGame;
    private Rectangle newGameBounds; // For click detection

	public ButtonPanel() {
		newGameButton();
	}
	
    private void newGameButton() {
        NewGame = new ImageIcon(getClass().getResource("/assets/MainMenu/o-5.png")).getImage();
        newGameBounds = new Rectangle(300, 220, 166, 36); // Match paint position
        addMouseListener(this);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(NewGame, 300, 220, 166, 36, this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (newGameBounds != null && newGameBounds.contains(e.getPoint())) {
            System.out.println("New Game button clicked!");
            // Here you can trigger a transition or action
        }
    }

    // Unused methods required by MouseListener
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

}
