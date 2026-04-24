import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;

public class SimpleInventorySystem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// JSeperator
	private final JSeparator separator = new JSeparator();
	private final JSeparator separator_1 = new JSeparator();
	private final JSeparator separator_2 = new JSeparator();
	private final JSeparator separator_1_2 = new JSeparator();
	private final JSeparator separator_1_1 = new JSeparator();
	
	// JLabel
	private final JLabel lblAddNewItem = new JLabel("Add New Item");
	private final JLabel lblItemName = new JLabel("Item Name:");
	private final JLabel lblItemPrice = new JLabel("Item Price:");
	private final JLabel lblCategory = new JLabel("Category:");
	private final JLabel lblQuantity = new JLabel("Quantity:");
	private final JLabel lblInventoryList = new JLabel("Inventory List");
	private final JLabel lblTotalItems = new JLabel("Total Items:");
	private final JLabel lblTotalNumber = new JLabel("4");
	private final JLabel lblLastUpdated = new JLabel("Last Updated:");
	private final JLabel lblDateAndTime = new JLabel("April 24, 2026 10:50 AM");
	private final JLabel lblTotal = new JLabel("₱0.00");
	
	// JTextField
	private final JTextField txtItemName = new JTextField();
	private final JTextField txtItemPrice = new JTextField();
	
	private final JButton btnNewButton = new JButton("+ Add Item");
	private final JButton btnRemoveItem = new JButton("Remove Item");
	private final JButton btnClearItem = new JButton("Clear All");
	private final JLabel lblGrandTotal = new JLabel("Grand Total:");
	
	// JComboBox
	private JComboBox<String> cboCategory = new JComboBox<>();
	
	// JSpinner
	private final JSpinner spnQuantity = new JSpinner();
	
	// JTable
	private final JTable table = new JTable();
	private DefaultTableModel tModel = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#", "Item Name", "Category", "Price", "Quantity", "Total Cost"
			});
	
	// JScrollPane
	private final JScrollPane scrollPane = new JScrollPane();
	
	private final JPopupMenu popTable = new JPopupMenu();
	private JMenuItem popDelete = new JMenuItem("Delete Item");
	private JMenuItem popViewDetails = new JMenuItem("View Details");
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnFile = new JMenu("FILE");
	private JMenuItem mnIAdd = new JMenuItem("Add Item");
	private JMenuItem mnIRemove = new JMenuItem("Remove Item");
	private JMenuItem mnIClear = new JMenuItem("Clear All");
	private JMenuItem mnIExit = new JMenuItem("Exit");
	
	private double grandTotal = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleInventorySystem frame = new SimpleInventorySystem();
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
	public SimpleInventorySystem() {
		setTitle("Simple Inventory System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// JLabel
		lblAddNewItem.setForeground(new Color(30, 144, 255));
		lblAddNewItem.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblAddNewItem.setBounds(10, 18, 148, 27);
		
		lblItemName.setForeground(new Color(0, 0, 0));
		lblItemName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblItemName.setBounds(20, 49, 100, 27);
		
		lblItemPrice.setForeground(Color.BLACK);
		lblItemPrice.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblItemPrice.setBounds(20, 87, 100, 27);
		
		lblCategory.setForeground(Color.BLACK);
		lblCategory.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCategory.setBounds(20, 128, 100, 27);
		
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblQuantity.setBounds(20, 166, 100, 27);
		
		lblInventoryList.setForeground(new Color(30, 144, 255));
		lblInventoryList.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblInventoryList.setBounds(10, 228, 148, 27);
		
		lblGrandTotal.setForeground(Color.BLACK);
		lblGrandTotal.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblGrandTotal.setBounds(353, 564, 100, 27);
		
		lblTotalItems.setForeground(Color.BLACK);
		lblTotalItems.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotalItems.setBounds(10, 622, 93, 27);
		
		lblTotalNumber.setForeground(Color.BLACK);
		lblTotalNumber.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotalNumber.setBounds(108, 623, 30, 27);
		
		lblLastUpdated.setForeground(Color.BLACK);
		lblLastUpdated.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblLastUpdated.setBounds(315, 623, 93, 27);
		
		lblDateAndTime.setForeground(Color.BLACK);
		lblDateAndTime.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDateAndTime.setBounds(418, 623, 164, 27);
		
		lblTotal.setForeground(new Color(34, 139, 34));
		lblTotal.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblTotal.setBounds(444, 561, 138, 27);
		
		// TextField
		txtItemName.setBounds(121, 53, 130, 24);
		txtItemName.setColumns(10);

		txtItemPrice.setColumns(10);
		txtItemPrice.setBounds(121, 93, 130, 24);
		
		// Seperator
		separator.setBounds(168, 35, 765, 2);
		separator_1_2.setBounds(-97, 610, 765, 2);
		separator_2.setBounds(168, 241, 765, 2);
		separator_1_1.setBounds(-120, 529, 765, 2);
		separator_1.setBounds(-46, 217, 765, 2);
		btnNewButton.addActionListener(addItem);
		
		// JButton
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton.setBounds(449, 153, 120, 39);
		btnRemoveItem.addActionListener(deleteItem);
		
		btnRemoveItem.setForeground(Color.WHITE);
		btnRemoveItem.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnRemoveItem.setBackground(new Color(255, 0, 0));
		btnRemoveItem.setBounds(20, 552, 139, 39);
		btnClearItem.addActionListener(clearAll);
		
		btnClearItem.setForeground(Color.WHITE);
		btnClearItem.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnClearItem.setBackground(new Color(255, 165, 0));
		btnClearItem.setBounds(168, 552, 120, 39);
		
		// JComboBox
		cboCategory.setBounds(121, 131, 130, 27);
		cboCategory.addItem("Select Category");
        cboCategory.addItem("Electronics");
        cboCategory.addItem("Clothing");
        cboCategory.addItem("Food");
        cboCategory.addItem("Others");
		
		// JSpinner
		spnQuantity.setBounds(121, 169, 130, 26);
		
		// JScrollPane
		scrollPane.setBounds(20, 266, 545, 237);
		table.addMouseListener(popupDetect);
		
		
		scrollPane.setViewportView(table);
		
		popDelete.addActionListener(deleteItem);
		popViewDetails.addActionListener(viewDetails);
		popTable.add(popDelete);
		popTable.add(popViewDetails);
		// JTable
		table.setModel(tModel);
		scrollPane.addMouseListener(popupDetect);
		// Content Pane
		contentPane.add(lblTotal);
		contentPane.add(lblAddNewItem);
		contentPane.add(separator_1);
		contentPane.add(separator);
		contentPane.add(separator_1_1);
		contentPane.add(btnRemoveItem);
		contentPane.add(lblItemName);
		contentPane.add(lblItemPrice);
		contentPane.add(lblCategory);
		contentPane.add(lblQuantity);
		contentPane.add(txtItemName);
		contentPane.add(separator_1_2);
		contentPane.add(lblTotalItems);
		contentPane.add(lblTotalNumber);
		contentPane.add(btnClearItem);
		contentPane.add(lblGrandTotal);
		contentPane.add(lblLastUpdated);
		contentPane.add(txtItemPrice);
		contentPane.add(btnNewButton);
		contentPane.add(cboCategory);
		contentPane.add(spnQuantity);
		contentPane.add(lblInventoryList);
		contentPane.add(separator_2);
		contentPane.add(scrollPane);
		contentPane.add(lblDateAndTime);
		
		
		menuBar.setBounds(0, 0, 592, 22);
		contentPane.add(menuBar);
		
		
		menuBar.add(mnFile);
		mnFile.add(mnIAdd);
		mnIAdd.addActionListener(addItem);
		mnFile.add(mnIRemove);
		mnIRemove.addActionListener(deleteItem);
		mnFile.add(mnIClear);
		mnIClear.addActionListener(clearAll);
		mnFile.add(mnIExit);
		mnIExit.addActionListener(exit);
	}
	
	private void addItem() {

		String itmName = txtItemName.getText().trim();
		String itmPriceText = txtItemPrice.getText().trim();
		int itmQuantity = (int) spnQuantity.getValue();

		// ComboBox check
		if (cboCategory.getSelectedItem() == null
				|| cboCategory.getSelectedItem().toString().equals("Select Category")) {
			JOptionPane.showMessageDialog(contentPane, "Please select a category!");
			return;
		}

		String itmCategory = cboCategory.getSelectedItem().toString();

		// Empty validation
		if (itmName.isEmpty() || itmPriceText.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "All fields are required!");
			return;
		}

		// Name validation
		if (!itmName.matches("[a-zA-Z0-9 .'-]+")) {
			JOptionPane.showMessageDialog(contentPane, "Invalid item name!");
			return;
		}

		// Quantity validation
		if (itmQuantity <= 0) {
			JOptionPane.showMessageDialog(contentPane, "Quantity must be greater than 0!");
			return;
		}

		// Price validation
		double price;
		try {
			price = Double.parseDouble(itmPriceText);
			if (price < 0)
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPane, "Price must be a valid positive number!");
			return;
		}

		// Compute values
		int rowNumber = tModel.getRowCount() + 1;
		double totalCost = price * itmQuantity;

		// UPDATE GRAND TOTAL
		grandTotal += totalCost;

		// Format values
		String priceFormatted = "₱ " + String.format("%.2f", price);
		String totalFormatted = "₱ " + String.format("%.2f", totalCost);

		// Add row to table
		tModel.addRow(new Object[] {
				rowNumber,
				itmName,
				itmCategory,
				priceFormatted,
				itmQuantity,
				totalFormatted
		});

		// Clear inputs
		txtItemName.setText("");
		txtItemPrice.setText("");
		spnQuantity.setValue(0);
		cboCategory.setSelectedIndex(0);

		// Update UI
		updateTotalItems();
		lblTotal.setText("₱ " + String.format("%.2f", grandTotal));

		JOptionPane.showMessageDialog(contentPane, "Item added successfully!");
	}

	private void deleteItem() {

		int selectedRow = table.getSelectedRow();

		// Check if user selected a row
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please select an item to delete.");
			return;
		}

		int choice = JOptionPane.showConfirmDialog(contentPane,
				"Are you sure you want to delete this item?\nThis action cannot be undone.", "Confirm Delete",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (choice == JOptionPane.YES_OPTION) {

			tModel.removeRow(selectedRow);

			for (int i = 0; i < tModel.getRowCount(); i++) {
				tModel.setValueAt(i + 1, i, 0);
			}
			
			updateTotalItems();

			JOptionPane.showMessageDialog(contentPane, "Item deleted successfully!");
		}
	}

	private void clearAll() {
		// Check if table is already empty
		if (tModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Inventory is already empty.");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(contentPane,
				"Are you sure you want to remove ALL items?\nThis action cannot be undone.", "Confirm Clear All",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (confirm == JOptionPane.YES_OPTION) {

			// Clear all rows
			tModel.setRowCount(0);

			// Reset UI
			lblTotal.setText("₱0.00");
			lblTotalNumber.setText("0");
			updateTotalItems();
			
			lblTotal.setText("₱0.00");
			JOptionPane.showMessageDialog(contentPane, "All items have been cleared.");
		}
	}
	
	private void updateTotalItems() {
	    lblTotalNumber.setText(String.valueOf(tModel.getRowCount()));
	}
	
	
	void viewDetails() {
		int target = table.getSelectedRow();
		if(target == -1) return;
		String receipt;
		
		String id = tModel.getValueAt(target, 0).toString();
		String name = tModel.getValueAt(target, 1).toString();
		String category = tModel.getValueAt(target, 2).toString();
		String price = tModel.getValueAt(target, 3).toString();
		String quantity = tModel.getValueAt(target, 4).toString();
		String totalCost = tModel.getValueAt(target, 5).toString();
		
		receipt = "ITEM DETAILS\n" +
				  "PRODUCT ID: " + id + "\n" +
				  "NAME: " + name + "\n" +
				  "CATEGORY: " + category + "\n" +
				  "PRICE: " + price + "\n" +
				  "QUANTITY: " + quantity + "\n" +
				  "TOTAL COST: " + totalCost;
		
		JOptionPane.showMessageDialog(null, receipt);
		
	}
	
	

	
	
	
	ActionListener addItem = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			addItem();
			
		}};
	
	ActionListener deleteItem = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			deleteItem();
			
		}};
		
		
	ActionListener clearAll = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearAll();
				
			}};
			
	ActionListener exit = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}};
			
			
	ActionListener viewDetails = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				viewDetails();
				
			}};
	
	MouseListener popupDetect = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			showPopUp(e);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		private void showPopUp(MouseEvent e) {
			if(e.isPopupTrigger()) {
			popTable.show(e.getComponent(), e.getX(), e.getY());
			}
			
		}
	
	};
}