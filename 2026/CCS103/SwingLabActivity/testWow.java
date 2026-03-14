package SwingLabActivity;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class testWow extends JFrame {

	// Prices
	double coffeePrices[] = { 120, 130, 140, 150, 160 };
	double nonCoffeePrices[] = { 110, 120, 115, 125 };
	double pastriesPrices[] = { 85, 95, 90, 80, 95 };
	double dessertsPrices[] = { 140, 150, 100, 60, 85 };

	int receiptNumber = 0;

	private JRadioButton rdbtnStudent;
	private JRadioButton rdbtnSenior;

	// Coffee components
	JCheckBox[] coffeeCheckboxes = new JCheckBox[5];
	JTextField[] coffeeQuantityFields = new JTextField[5];

	// Non Coffee components
	JCheckBox[] nonCoffeeCheckboxes = new JCheckBox[4];
	JTextField[] nonCoffeeQuantityFields = new JTextField[4];

	// Pastries components
	JCheckBox[] pastriesCheckboxes = new JCheckBox[5];
	JTextField[] pastriesQuantityFields = new JTextField[5];

	// Desserts components
	JCheckBox[] dessertsCheckboxes = new JCheckBox[5];
	JTextField[] dessertsQuantityFields = new JTextField[5];

	ButtonGroup discountGroup = new ButtonGroup();

	private JTextField txtCashPayment;
	private JTextArea receiptArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new testWow().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public testWow() {
		initialize();
	}

	private void initialize() {
		// Frame setup
		setTitle("Cafe Management System - KapaPindot");
		setBounds(100, 100, 900, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 248, 235)); // Cream color

		// Title
		JLabel lblTitle = new JLabel("KapaPindot");
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 36));
		lblTitle.setBounds(20, 10, 300, 50);
		getContentPane().add(lblTitle);

		JLabel lblSubtitle = new JLabel("Cafe Management System");
		lblSubtitle.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblSubtitle.setBounds(20, 50, 200, 20);
		getContentPane().add(lblSubtitle);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 580, 2);
		getContentPane().add(separator);

		// Create main tabbed pane for categories
		JTabbedPane mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainTabbedPane.setBounds(10, 90, 580, 280);
		mainTabbedPane.setBackground(new Color(255, 248, 235));
		getContentPane().add(mainTabbedPane);

		// Coffee Panel
		JPanel coffeePanel = createCoffeePanel();
		mainTabbedPane.addTab("Coffee", null, coffeePanel, "Coffee beverages");

		// Non-Coffee Panel
		JPanel nonCoffeePanel = createNonCoffeePanel();
		mainTabbedPane.addTab("Non-Coffee", null, nonCoffeePanel, "Non-coffee beverages");

		// Pastries Panel
		JPanel pastriesPanel = createPastriesPanel();
		mainTabbedPane.addTab("Pastries", null, pastriesPanel, "Fresh pastries");

		// Desserts Panel
		JPanel dessertsPanel = createDessertsPanel();
		mainTabbedPane.addTab("Desserts", null, dessertsPanel, "Delicious desserts");

		// Discount Panel
		JPanel discountPanel = new JPanel();
		discountPanel.setBounds(10, 380, 250, 60);
		discountPanel.setLayout(null);
		discountPanel.setBackground(new Color(255, 248, 235));
		getContentPane().add(discountPanel);

		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDiscount.setBounds(10, 10, 70, 20);
		discountPanel.add(lblDiscount);

		rdbtnStudent = new JRadioButton("Student (10%)");
		rdbtnStudent.setBounds(80, 10, 100, 20);
		rdbtnStudent.setBackground(new Color(255, 248, 235));
		discountPanel.add(rdbtnStudent);

		rdbtnSenior = new JRadioButton("Senior (20%)");
		rdbtnSenior.setBounds(180, 10, 100, 20);
		rdbtnSenior.setBackground(new Color(255, 248, 235));
		discountPanel.add(rdbtnSenior);

		discountGroup.add(rdbtnStudent);
		discountGroup.add(rdbtnSenior);

		// Payment Panel
		JPanel paymentPanel = new JPanel();
		paymentPanel.setBounds(270, 380, 320, 60);
		paymentPanel.setLayout(null);
		paymentPanel.setBackground(new Color(255, 248, 235));
		getContentPane().add(paymentPanel);

		JLabel lblPayment = new JLabel("Payment:");
		lblPayment.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPayment.setBounds(10, 10, 70, 20);
		paymentPanel.add(lblPayment);

		JLabel lblPesoSign = new JLabel("₱");
		lblPesoSign.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPesoSign.setBounds(80, 10, 20, 20);
		paymentPanel.add(lblPesoSign);

		txtCashPayment = new JTextField();
		txtCashPayment.setBounds(100, 10, 150, 25);
		txtCashPayment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		});
		paymentPanel.add(txtCashPayment);
		txtCashPayment.setColumns(10);

		// Receipt Panel
		JPanel receiptPanel = new JPanel();
		receiptPanel.setBounds(600, 10, 280, 500);
		receiptPanel.setLayout(null);
		receiptPanel.setBackground(new Color(240, 230, 210));
		receiptPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(180, 150, 120)));
		getContentPane().add(receiptPanel);

		JLabel lblReceipt = new JLabel("ORDER RECEIPT");
		lblReceipt.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblReceipt.setBounds(70, 10, 150, 25);
		receiptPanel.add(lblReceipt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 260, 380);
		receiptPanel.add(scrollPane);

		receiptArea = new JTextArea();
		receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		receiptArea.setEditable(false);
		receiptArea.setBackground(new Color(255, 255, 240));
		scrollPane.setViewportView(receiptArea);

		// Buttons Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 450, 580, 50);
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(new Color(255, 248, 235));
		getContentPane().add(buttonPanel);

		JButton btnCompute = new JButton("COMPUTE");
		btnCompute.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCompute.setBounds(100, 10, 150, 35);
		btnCompute.setBackground(new Color(100, 150, 100));
		btnCompute.setForeground(Color.WHITE);
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computeOrder();
			}
		});
		buttonPanel.add(btnCompute);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnClear.setBounds(300, 10, 150, 35);
		btnClear.setBackground(new Color(200, 100, 100));
		btnClear.setForeground(Color.WHITE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllFields();
			}
		});
		buttonPanel.add(btnClear);
	}

	private JPanel createCoffeePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 248, 235));

		String[] coffeeItems = { "Espresso", "Americano", "Cappuccino", "Caramel", "Mocha" };
		int[] yPositions = { 20, 50, 80, 110, 140 };

		for (int i = 0; i < coffeeItems.length; i++) {
			JCheckBox chk = new JCheckBox(coffeeItems[i] + " - ₱" + coffeePrices[i]);
			chk.setBounds(20, yPositions[i], 150, 25);
			chk.setBackground(new Color(255, 248, 235));
			panel.add(chk);
			coffeeCheckboxes[i] = chk;

			JTextField txt = new JTextField();
			txt.setBounds(200, yPositions[i], 60, 25);
			txt.addKeyListener(createNumberKeyListener());
			panel.add(txt);
			coffeeQuantityFields[i] = txt;
		}

		return panel;
	}

	private JPanel createNonCoffeePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 248, 235));

		String[] nonCoffeeItems = { "Hot Chocolate", "Matcha Latte", "Strawberry Milk", "Fresh Lemonade" };
		int[] yPositions = { 20, 50, 80, 110 };

		for (int i = 0; i < nonCoffeeItems.length; i++) {
			JCheckBox chk = new JCheckBox(nonCoffeeItems[i] + " - ₱" + nonCoffeePrices[i]);
			chk.setBounds(20, yPositions[i], 180, 25);
			chk.setBackground(new Color(255, 248, 235));
			panel.add(chk);
			nonCoffeeCheckboxes[i] = chk;

			JTextField txt = new JTextField();
			txt.setBounds(220, yPositions[i], 60, 25);
			txt.addKeyListener(createNumberKeyListener());
			panel.add(txt);
			nonCoffeeQuantityFields[i] = txt;
		}

		return panel;
	}

	private JPanel createPastriesPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 248, 235));

		String[] pastriesItems = { "Croissant", "Chocolate Croissant", "Blueberry Muffin", "Banana Bread",
				"Cinnamon Roll" };
		int[] yPositions = { 20, 50, 80, 110, 140 };

		for (int i = 0; i < pastriesItems.length; i++) {
			JCheckBox chk = new JCheckBox(pastriesItems[i] + " - ₱" + pastriesPrices[i]);
			chk.setBounds(20, yPositions[i], 180, 25);
			chk.setBackground(new Color(255, 248, 235));
			panel.add(chk);
			pastriesCheckboxes[i] = chk;

			JTextField txt = new JTextField();
			txt.setBounds(220, yPositions[i], 60, 25);
			txt.addKeyListener(createNumberKeyListener());
			panel.add(txt);
			pastriesQuantityFields[i] = txt;
		}

		return panel;
	}

	private JPanel createDessertsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 248, 235));

		String[] dessertsItems = { "Cheesecake", "Chocolate Cake", "Brownies", "Donut", "Ice Cream" };
		int[] yPositions = { 20, 50, 80, 110, 140 };

		for (int i = 0; i < dessertsItems.length; i++) {
			JCheckBox chk = new JCheckBox(dessertsItems[i] + " - ₱" + dessertsPrices[i]);
			chk.setBounds(20, yPositions[i], 180, 25);
			chk.setBackground(new Color(255, 248, 235));
			panel.add(chk);
			dessertsCheckboxes[i] = chk;

			JTextField txt = new JTextField();
			txt.setBounds(220, yPositions[i], 60, 25);
			txt.addKeyListener(createNumberKeyListener());
			panel.add(txt);
			dessertsQuantityFields[i] = txt;
		}

		return panel;
	}

	private KeyListener createNumberKeyListener() {
		return new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		};
	}

	private void computeOrder() {
		double subtotal = 0;
		double discount = 0;
		double cashPayment = 0;
		double finalTotal = 0;
		double change = 0;

		StringBuilder receipt = new StringBuilder();
		receipt.append("=====================================\n");
		receipt.append("         KAPAPINDOT CAFE\n");
		receipt.append("=====================================\n\n");
		receipt.append(String.format("%-20s %3s %8s\n", "Item", "Qty", "Total"));
		receipt.append("-------------------------------------\n");

		boolean hasItems = false;

		// Coffee items
		hasItems |= processItems("Coffee", coffeeCheckboxes, coffeeQuantityFields, coffeePrices, receipt);

		// Non-Coffee items
		hasItems |= processItems("Non-Coffee", nonCoffeeCheckboxes, nonCoffeeQuantityFields, nonCoffeePrices, receipt);

		// Pastries items
		hasItems |= processItems("Pastries", pastriesCheckboxes, pastriesQuantityFields, pastriesPrices, receipt);

		// Desserts items
		hasItems |= processItems("Desserts", dessertsCheckboxes, dessertsQuantityFields, dessertsPrices, receipt);

		if (!hasItems) {
			receiptArea.setText("No items selected!\nPlease select items to order.");
			return;
		}

		receipt.append("-------------------------------------\n");
		receipt.append(String.format("%-23s ₱%9.2f\n", "Subtotal:", subtotal));

		// Calculate discount
		if (rdbtnStudent.isSelected()) {
			discount = subtotal * 0.10;
			receipt.append(String.format("%-23s ₱%9.2f (10%%)\n", "Student Discount:", discount));
		} else if (rdbtnSenior.isSelected()) {
			discount = subtotal * 0.20;
			receipt.append(String.format("%-23s ₱%9.2f (20%%)\n", "Senior Discount:", discount));
		} else {
			receipt.append(String.format("%-23s\n", "No Discount Applied"));
		}

		finalTotal = subtotal - discount;
		receipt.append(String.format("%-23s ₱%9.2f\n", "Grand Total:", finalTotal));

		// Process payment
		try {
			cashPayment = Double.parseDouble(txtCashPayment.getText());
		} catch (NumberFormatException e) {
			cashPayment = 0;
		}

		receipt.append(String.format("%-23s ₱%9.2f\n", "Payment:", cashPayment));

		change = cashPayment - finalTotal;
		if (change < 0) {
			receipt.append(String.format("%-23s ₱%9.2f\n", "Change:", 0.00));
			receipt.append("\nINSUFFICIENT PAYMENT!\n");
		} else {
			receipt.append(String.format("%-23s ₱%9.2f\n", "Change:", change));
		}

		receipt.append("\n-------------------------------------\n");
		receipt.append("Receipt No: " + (++receiptNumber) + "\n");
		receipt.append(LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
		receipt.append("\n=====================================\n");
		receipt.append("      Thank you! Come again!\n");

		receiptArea.setText(receipt.toString());
	}

	private boolean processItems(String category, JCheckBox[] checkboxes, JTextField[] quantityFields, double[] prices,
			StringBuilder receipt) {
		boolean hasItems = false;

		for (int i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i] != null && checkboxes[i].isSelected()) {
				hasItems = true;
				int quantity = 1;
				try {
					quantity = Integer.parseInt(quantityFields[i].getText());
					if (quantity <= 0)
						quantity = 1;
				} catch (NumberFormatException e) {
					quantity = 1;
				}

				String itemName = checkboxes[i].getText().split(" - ")[0];
				double itemTotal = quantity * prices[i];

				receipt.append(String.format("%-20s %3d ₱%8.2f\n", itemName, quantity, itemTotal));
			}
		}

		return hasItems;
	}

	private void clearAllFields() {
		// Clear coffee fields
		for (int i = 0; i < coffeeCheckboxes.length; i++) {
			if (coffeeCheckboxes[i] != null) {
				coffeeCheckboxes[i].setSelected(false);
				coffeeQuantityFields[i].setText("");
			}
		}

		// Clear non-coffee fields
		for (int i = 0; i < nonCoffeeCheckboxes.length; i++) {
			if (nonCoffeeCheckboxes[i] != null) {
				nonCoffeeCheckboxes[i].setSelected(false);
				nonCoffeeQuantityFields[i].setText("");
			}
		}

		// Clear pastries fields
		for (int i = 0; i < pastriesCheckboxes.length; i++) {
			if (pastriesCheckboxes[i] != null) {
				pastriesCheckboxes[i].setSelected(false);
				pastriesQuantityFields[i].setText("");
			}
		}

		// Clear desserts fields
		for (int i = 0; i < dessertsCheckboxes.length; i++) {
			if (dessertsCheckboxes[i] != null) {
				dessertsCheckboxes[i].setSelected(false);
				dessertsQuantityFields[i].setText("");
			}
		}

		// Clear radio buttons
		discountGroup.clearSelection();

		// Clear payment field
		txtCashPayment.setText("");

		// Clear receipt area
		receiptArea.setText("");
	}
}