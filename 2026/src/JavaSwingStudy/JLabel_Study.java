package JavaSwingStudy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Image;


public class JLabel_Study {
	
	public JLabel_Study() {
		ImageIcon icon = new ImageIcon("Shrek.png");
		Image img = icon.getImage();

		Image scaledImg = img.getScaledInstance(150, 150, img.SCALE_SMOOTH);
		
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		
		JLabel label = new JLabel(scaledIcon);
		label.setText("Dangal Greetings!");
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.add(label);
	}
	
	public static void main(String[] args) {
		new JLabel_Study();
	}

}
