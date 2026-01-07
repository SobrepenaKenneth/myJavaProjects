

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class JLabel_Study {
	
	public JLabel_Study() {
		ImageIcon image = new ImageIcon("Shrek.png");
		
		JLabel label = new JLabel();
		label.setText("Dangal Greetings!");
		label.setIcon(image);
		
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
