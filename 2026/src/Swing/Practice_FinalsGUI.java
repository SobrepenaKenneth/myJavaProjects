package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class Practice_FinalsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// JPanels
	private final JPanel northPanel = new JPanel();
	private final JPanel westPanel = new JPanel();
	private final JPanel centerPanel = new JPanel();
	private final JPanel southPanel = new JPanel();
	private final JPanel eastPanel = new JPanel();

	// JLabels
	private final JLabel lblHeader = new JLabel("Java GUI Advance Study");
	private final JLabel lblFruits = new JLabel("Enter a fruits:");

	// JTextFields
	private final JTextField txtFruits = new JTextField();

	// JComboBox
	private final JComboBox<String> cboFruits = new JComboBox<>();

	// JButton
	private final JButton btnAdd = new JButton("ADD");
	private final JButton btnRemove = new JButton("REMOVE");
	private final JButton btnClear = new JButton("CLEAR");

	// JList & Model
	private final DefaultListModel<String> lstModel = new DefaultListModel<String>();
	private final JList<String> listFruits = new JList<String>(lstModel);

	// JScrollPane
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Practice_FinalsGUI frame = new Practice_FinalsGUI();
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
	public Practice_FinalsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// XXX NOTE: I Changed the variable declaration on the preference to init.field
		// i think it's more readable -x40
		// I Prefer to organize each components so i can easily see it in the Outline
		// ========================
		// XXX ARRAYS
		// ========================

		// ========================
		// XXX JLABELS
		// ========================
		lblHeader.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblFruits.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		// ========================
		// XXX JTEXTFIELD
		// ========================
		txtFruits.setColumns(10);

		// ========================
		// XXX JCOMBOBOX
		// ========================
		cboFruits.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		listFruits.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		// ========================
		// XXX JLIST && MODEL
		// ========================
		scrollPane.setViewportView(listFruits);

		// ========================
		// XXX CONTENT PANE
		// ========================
		contentPane.add(westPanel, BorderLayout.WEST);
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(southPanel, BorderLayout.SOUTH);
		contentPane.add(eastPanel, BorderLayout.EAST);

		// ========================
		// XXX ALL PANELS
		// ========================
		// - NORTH PANEL
		northPanel.add(lblHeader);

		// - WEST PANEL
		westPanel.add(lblFruits);
		westPanel.add(txtFruits);

		// - CENTER PANEL
		centerPanel.add(cboFruits);

		// - EAST PANEL
		eastPanel.add(scrollPane, "name_5735362582400");
		eastPanel.setLayout(new CardLayout(0, 0));

		// - SOUTH PANEL
		southPanel.add(btnAdd);
		southPanel.add(btnRemove);
		southPanel.add(btnClear);

		// ========================
		// XXX JBUTTONS
		// ========================
		// - Add Button
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(124, 252, 0));
		btnAdd.setFont(new Font("Segoe UI Black", Font.BOLD, 20));

		// - Remove Button
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		btnRemove.setBackground(new Color(255, 99, 71));
		btnClear.setForeground(Color.WHITE);

		// - Clear Button
		btnClear.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		btnClear.setBackground(new Color(255, 99, 71));

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeItem();
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearItem();
			}
		});
	}// End of Constructor

	// XXX NOTE: Components are instance variable not static
	/**
	 * This method adds an Item to ComboBox and List
	 */
	private void addItem() {
		System.out.println("Add Button Clicked!");

		String fruit = txtFruits.getText().trim();

		if (!fruit.isEmpty()) {
			System.out.println("Added an Item!");
			cboFruits.addItem(fruit);
			lstModel.addElement(fruit);
		} else {
			System.out.println("No Item!");
			JOptionPane.showMessageDialog(contentPane, "Field must have an item!");
		}
	}// End of addItem()

	/**
	 * This method only removes an item
	 */
	private void removeItem() {
		System.out.println("Remove Button Clicked!");

		int index = listFruits.getSelectedIndex();

		if (index != -1) {
			System.out.println("Removed an item!");
			String item = lstModel.getElementAt(index);
			lstModel.remove(index); 
			cboFruits.removeItem(item); 
			
		} else {
			System.out.println("No item selected!");
			JOptionPane.showMessageDialog(contentPane, "Please select an item!");
		}
	}// End of removeItem()

	/**
	 * This method clears all items
	 */
	private void clearItem() {
		System.out.println("Clear Button Clicked!");
		cboFruits.removeAllItems();
		lstModel.clear();
	}// End of clearItem()
}// End of Class
