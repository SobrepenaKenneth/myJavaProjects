package dev.x40arcade.toolbox;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VinylDisc extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage vinylImage;
    private double angle = 0;
    private boolean isRotating = false;
    private Timer rotationTimer;
    
    //Constructor
    public VinylDisc(String imagePath) {
        setOpaque(false); // Keep transparency

        try {
            vinylImage = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        rotationTimer = new Timer(16, e -> {
            angle += 5;
            if (angle >= 360) angle = 0;
            repaint();
        });
    }
    
    // METHODS base dun sa plan BWAHAHHA
    public boolean isRotating() {
        return isRotating;
    }
    // clicked
    public void start() {
        if (!isRotating) {
            isRotating = true;
            rotationTimer.start();
        }
    }
    //not clicked
    public void stop() {
        if (isRotating) {
            isRotating = false;
            rotationTimer.stop();
        }
    }

    // Copy paste lng to dko pa master tong method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (vinylImage == null) return;

        Graphics2D g2d = (Graphics2D) g.create();

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Rotate around the center
        g2d.translate(centerX, centerY);
        g2d.rotate(Math.toRadians(angle));

        // Scale the image to fit the panel size
        Image scaled = vinylImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

        g2d.drawImage(scaled, -getWidth() / 2, -getHeight() / 2, null);
        g2d.dispose();
    }
}
