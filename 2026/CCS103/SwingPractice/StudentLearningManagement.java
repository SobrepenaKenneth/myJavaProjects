package SwingPractice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class StudentLearningManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JLabel lblNewLabel = new JLabel("Student Learning Management");
	private final JLabel lblNewLabel_1 = new JLabel("Student Name :");
	private final JTextField textField = new JTextField();
	private final JLabel lblNewLabel_1_1 = new JLabel("Program :");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblNewLabel_1_2 = new JLabel("Student ID No. :");
	private final JTextField textField_2 = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLearningManagement frame = new StudentLearningManagement();
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
	public StudentLearningManagement() {
		textField.setBounds(104, 71, 114, 20);
		textField.setColumns(10);
		setBounds(100, 100, 478, 369);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblNewLabel.setBounds(71, 11, 322, 59);
		
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 67, 82, 27);
		
		getContentPane().add(lblNewLabel_1);
		
		getContentPane().add(textField);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 128, 59, 27);
		
		getContentPane().add(lblNewLabel_1_1);
		textField_1.setColumns(10);
		textField_1.setBounds(104, 132, 114, 20);
		
		getContentPane().add(textField_1);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(10, 98, 94, 27);
		
		getContentPane().add(lblNewLabel_1_2);
		textField_2.setColumns(10);
		textField_2.setBounds(104, 101, 114, 20);
		
		getContentPane().add(textField_2);

	}
}
