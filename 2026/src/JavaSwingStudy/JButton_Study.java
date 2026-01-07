package JavaSwingStudy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JButton_Study implements ActionListener{
	private static JFrame frame;
	private static JPanel controlPanel;
	private static JButton newGameButton;
	
	public JButton_Study() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		controlPanel = new JPanel();
		frame.add(controlPanel);
		frame.setVisible(true);
	}
	
	private static  void newGameBtn() {
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new JButton_Study());
		
		controlPanel.add(newGameButton);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new JButton_Study();
		newGameBtn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==newGameButton) {
			System.out.println("Button Clicked");
		}
		
	}

}
