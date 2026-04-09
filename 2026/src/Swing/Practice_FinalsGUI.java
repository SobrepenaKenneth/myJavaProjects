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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private final JComboBox cboFruits = new JComboBox();
	
	// JButton
	private final JButton btnAdd = new JButton("ADD");
	
	// JList
	private final JList list = new JList();
	
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
		
		// XXX NOTE: I Changed the variable declaration on the preference to init.field i think it's more readable -x40
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
		cboFruits.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		// - CENTER PANEL
		centerPanel.add(cboFruits);
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(124, 252, 0));
		btnAdd.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		
		// - EAST PANEL
		eastPanel.add(scrollPane);
		scrollPane.setViewportView(list);
		
		// - SOUTH PANEL
		southPanel.add(btnAdd);
	
		// ========================
		
		// ========================
		// XXX JBUTTONS
		// ========================
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});
	}
	
	// XXX NOTE: Components are instance variable not static
	private void addItem() {
		String fruit = txtFruits.getText().trim();
		
	}

}
