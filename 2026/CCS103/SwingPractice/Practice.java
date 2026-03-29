package SwingPractice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Practice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Practice frame = new Practice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Practice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(50, 50));
		northPanel.setBackground(new Color(255, 0, 0));
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(null);
		
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(50, 50));
		westPanel.setBackground(new Color(210, 105, 30));
		contentPane.add(westPanel, BorderLayout.WEST);
		
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(50, 50));
		southPanel.setBackground(new Color(0, 206, 209));
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(50, 50));
		eastPanel.setBackground(new Color(138, 43, 226));
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(50, 50));
		centerPanel.setBackground(new Color(255, 215, 0));
		contentPane.add(centerPanel, BorderLayout.CENTER);

	}

}
