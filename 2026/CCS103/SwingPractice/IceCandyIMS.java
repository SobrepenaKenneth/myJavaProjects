package SwingPractice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class IceCandyIMS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFlavor;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IceCandyIMS frame = new IceCandyIMS();
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
	public IceCandyIMS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Ice Candy Inventory System");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitle.setBounds(89, 0, 262, 45);
		contentPane.add(lblTitle);
		
		JLabel lblFlavor = new JLabel("Flavor:");
		lblFlavor.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblFlavor.setBounds(10, 61, 78, 27);
		contentPane.add(lblFlavor);
		
		JLabel lblLine = new JLabel("---------------------------------------------------------------");
		lblLine.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLine.setBounds(-21, 24, 486, 45);
		contentPane.add(lblLine);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblQuantity.setBounds(10, 97, 88, 27);
		contentPane.add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPrice.setBounds(10, 135, 88, 27);
		contentPane.add(lblPrice);
		
		txtFlavor = new JTextField();
		txtFlavor.setBounds(116, 69, 97, 20);
		contentPane.add(txtFlavor);
		txtFlavor.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(116, 105, 97, 20);
		contentPane.add(txtQuantity);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(116, 143, 97, 20);
		contentPane.add(txtPrice);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(10, 173, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(124, 174, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(10, 204, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(124, 204, 89, 23);
		contentPane.add(btnClear);
		
		table = new JTable();
		table.setBounds(238, 69, 186, 162);
		contentPane.add(table);

	}
}
