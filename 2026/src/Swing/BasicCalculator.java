package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BasicCalculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumber1;
	private JTextField txtNumber2;
	private static JTextField txtResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicCalculator frame = new BasicCalculator();
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
	public BasicCalculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Number");
		lblNewLabel.setBounds(10, 64, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Second Number");
		lblNewLabel_1.setBounds(10, 89, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Result");
		lblNewLabel_2.setBounds(10, 114, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNumber1 = new JTextField();
		txtNumber1.setBounds(89, 61, 86, 20);
		contentPane.add(txtNumber1);
		txtNumber1.setColumns(10);
		
		txtNumber2 = new JTextField();
		txtNumber2.setBounds(89, 86, 86, 20);
		contentPane.add(txtNumber2);
		txtNumber2.setColumns(10);
		
		txtResult = new JTextField();
		txtResult.setBounds(89, 111, 86, 20);
		contentPane.add(txtResult);
		txtResult.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumber1.setText("");
				txtNumber2.setText("");
				txtResult.setText("");
			}
		});
		btnClear.setBounds(10, 139, 69, 23);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_3 = new JLabel("CALCULATOR");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_3.setBounds(89, 11, 233, 39);
		contentPane.add(lblNewLabel_3);
		
		JButton btnAddition = new JButton("Addition (+)");
		btnAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num1 = Integer.parseInt(txtNumber1.getText());
					int num2 = Integer.parseInt(txtNumber2.getText());
					int sum = num1 + num2;
					txtResult.setText(String.valueOf(sum));
					
				} catch (NumberFormatException num) {
					JOptionPane.showMessageDialog(null, "Please enter valid numbers!");
				}
			}
		});
		btnAddition.setBounds(198, 60, 139, 23);
		contentPane.add(btnAddition);
		
		JButton btnSubtraction = new JButton("Subtraction (-)");
		btnSubtraction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num1 = Integer.parseInt(txtNumber1.getText());
					int num2 = Integer.parseInt(txtNumber2.getText());
					int difference = num1 - num2;
					txtResult.setText(String.valueOf(difference));
					
				} catch (NumberFormatException num) {
					JOptionPane.showMessageDialog(null, "Please enter valid numbers!");
				}
				
			}
		});
		btnSubtraction.setBounds(198, 85, 139, 23);
		contentPane.add(btnSubtraction);
		
		JButton btnMultiplication = new JButton("Multiplication (x)");
		btnMultiplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num1 = Integer.parseInt(txtNumber1.getText());
					int num2 = Integer.parseInt(txtNumber2.getText());
					int product = num1 * num2;
					txtResult.setText(String.valueOf(product));
					
				} catch (NumberFormatException num) {
					JOptionPane.showMessageDialog(null, "Please enter valid numbers!");
				}
			}
		});
		btnMultiplication.setBounds(198, 110, 139, 23);
		contentPane.add(btnMultiplication);
		
		JButton btnDivision = new JButton("Division (/)");
		btnDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int num1 = Integer.parseInt(txtNumber1.getText());
					int num2 = Integer.parseInt(txtNumber2.getText());
					int quotient = num1 / num2;
					txtResult.setText(String.valueOf(quotient));
					
				} catch (NumberFormatException num) {
					JOptionPane.showMessageDialog(null, "Please enter valid numbers!");
				}
			}
		});
		btnDivision.setBounds(198, 139, 139, 23);
		contentPane.add(btnDivision);
	}
}
