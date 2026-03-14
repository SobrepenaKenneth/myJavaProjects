package SwingLabActivity;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MidtermLaboratoryActivity extends JFrame {

	// XXX Prices

	double coffeePrices[] = { 120, 130, 140, 150, 160 };
	double nonCoffeePrices[] = { 100, 150, 150, 120 };
	double pastriesPrices[] = { 60, 80, 100, 90, 110 };
	double dessertsPrices[] = { 140, 200, 25, 20, 35 };

	// Coffee components
	JCheckBox[] grpCoffeeSelect = new JCheckBox[5];
	JTextField[] grpCoffeeAmount = new JTextField[5];

	// Non Coffee
	JCheckBox[] grpNonCoffeeSelect = new JCheckBox[4];
	JTextField[] grpNonCoffeeAmount = new JTextField[4];

	// Pastries
	JCheckBox[] grpPastriesSelect = new JCheckBox[5];
	JTextField[] grpPastriesAmount = new JTextField[5];

	// Desserts
	JCheckBox[] grpDessertsSelect = new JCheckBox[5];
	JTextField[] grpDessertsAmount = new JTextField[5];

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	// XXX Main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MidtermLaboratoryActivity frame = new MidtermLaboratoryActivity();
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
	public MidtermLaboratoryActivity() {
		initialize();
	}

	// XXX Initialization
	private void initialize() {
		JFrame frame = new JFrame("Cafe Management System");
		frame.setBounds(100, 100, 820, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel paneCoffees = new JPanel();
		paneCoffees.setBounds(10, 32, 280, 200);
		paneCoffees.setLayout(null);

		JCheckBox chckbxLatte = new JCheckBox("Espresso");
		chckbxLatte.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxLatte.setBounds(6, 42, 77, 23);
		paneCoffees.add(chckbxLatte);
		JCheckBox[] grpCoffeeSelect = new JCheckBox[5];
		grpCoffeeSelect[0] = chckbxLatte;

		JCheckBox chckbxAmericano = new JCheckBox("Americano");
		chckbxAmericano.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxAmericano.setBounds(6, 68, 97, 23);
		paneCoffees.add(chckbxAmericano);
		grpCoffeeSelect[1] = chckbxAmericano;

		JCheckBox chckbxCappucino = new JCheckBox("Cappuccino");
		chckbxCappucino.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxCappucino.setBounds(6, 94, 97, 23);
		paneCoffees.add(chckbxCappucino);
		grpCoffeeSelect[2] = chckbxCappucino;

		JCheckBox chckbxCaramel = new JCheckBox("Caramel");
		chckbxCaramel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxCaramel.setBounds(6, 120, 97, 23);
		paneCoffees.add(chckbxCaramel);
		grpCoffeeSelect[3] = chckbxCaramel;

		JCheckBox chckbxMocha = new JCheckBox("Mocha");
		chckbxMocha.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxMocha.setBounds(6, 146, 97, 23);
		paneCoffees.add(chckbxMocha);
		grpCoffeeSelect[4] = chckbxMocha;

		JLabel lblCoffee = new JLabel("Coffee");
		lblCoffee.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblCoffee.setBounds(97, 3, 81, 39);
		paneCoffees.add(lblCoffee);

		JTextField txtEspresso = new JTextField();
		txtEspresso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtEspresso.setBounds(220, 43, 50, 20);
		paneCoffees.add(txtEspresso);
		txtEspresso.setColumns(10);

		JTextField txtAmericano = new JTextField();
		txtAmericano.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtAmericano.setColumns(10);
		txtAmericano.setBounds(220, 69, 50, 20);
		paneCoffees.add(txtAmericano);

		JTextField txtCappucino = new JTextField();
		txtCappucino.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();

			}
		});
		txtCappucino.setColumns(10);
		txtCappucino.setBounds(220, 95, 50, 20);
		paneCoffees.add(txtCappucino);

		JTextField txtCaramel = new JTextField();
		txtCaramel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();

			}
		});
		txtCaramel.setColumns(10);
		txtCaramel.setBounds(220, 121, 50, 20);
		paneCoffees.add(txtCaramel);

		JTextField txtMocha = new JTextField();
		txtMocha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();

			}
		});
		txtMocha.setColumns(10);
		txtMocha.setBounds(220, 147, 50, 20);
		paneCoffees.add(txtMocha);

		JPanel paneNonCoffees = new JPanel();
		paneNonCoffees.setBounds(300, 32, 280, 200);
		paneNonCoffees.setLayout(null);

		JTextField txt = new JTextField();
		JTextField txtLemonade = null;
		grpNonCoffeeAmount[3] = txtLemonade;
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txt.setColumns(10);
		txt.setBounds(220, 96, 50, 20);
		paneNonCoffees.add(txt);

		JTextField txtMatchaLatte = new JTextField();
		grpNonCoffeeAmount[1] = txtMatchaLatte;
		txtMatchaLatte.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtMatchaLatte.setColumns(10);
		txtMatchaLatte.setBounds(220, 44, 50, 20);
		paneNonCoffees.add(txtMatchaLatte);

		JTextField textField_2 = new JTextField();
		JTextField txtStrawberryMilk = null;
		grpNonCoffeeAmount[2] = txtStrawberryMilk;
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(220, 70, 50, 20);
		paneNonCoffees.add(textField_2);

		JTextField txtHotChoco = new JTextField();
		grpNonCoffeeAmount[0] = txtHotChoco;
		txtHotChoco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtHotChoco.setColumns(10);
		txtHotChoco.setBounds(220, 18, 50, 20);
		paneNonCoffees.add(txtHotChoco);

		JCheckBox chckbxLemonade = new JCheckBox("Fresh Lemonade");
		chckbxLemonade.setBounds(6, 95, 124, 23);
		paneNonCoffees.add(chckbxLemonade);
		grpNonCoffeeSelect[3] = chckbxLemonade;

		JCheckBox chckbxStrawberryMilk = new JCheckBox("Strawberry Milk");
		chckbxStrawberryMilk.setBounds(6, 69, 124, 23);
		paneNonCoffees.add(chckbxStrawberryMilk);
		grpNonCoffeeSelect[2] = chckbxStrawberryMilk;

		JCheckBox chckbxMatchaLatte = new JCheckBox("Macha Latte");
		chckbxMatchaLatte.setBounds(6, 43, 97, 23);
		paneNonCoffees.add(chckbxMatchaLatte);
		grpNonCoffeeSelect[1] = chckbxMatchaLatte;

		JCheckBox chckbxHotChoco = new JCheckBox("Hot Chocolate");
		chckbxHotChoco.setBounds(6, 17, 107, 23);
		paneNonCoffees.add(chckbxHotChoco);
		grpNonCoffeeSelect[0] = chckbxHotChoco;

		JTabbedPane paneCoffeeSelect = new JTabbedPane(JTabbedPane.TOP);
		paneCoffeeSelect.setBounds(12, 79, 280, 225);
		frame.getContentPane().add(paneCoffeeSelect);
		paneCoffeeSelect.add(paneCoffees);
		paneCoffeeSelect.add(paneNonCoffees);

		JLabel lblNonCoffee = new JLabel("Non-Coffee");
		lblNonCoffee.setBounds(108, 0, 64, 14);
		paneNonCoffees.add(lblNonCoffee);
		paneCoffeeSelect.setTitleAt(1, "Non Coffee");
		paneCoffeeSelect.setTitleAt(0, "Coffee");

		JPanel paneNonCoffees_1 = new JPanel();
		paneNonCoffees_1.setLayout(null);
		paneCoffeeSelect.addTab("Extras", null, paneNonCoffees_1, null);

		JCheckBox chckbxExtraSyrup = new JCheckBox("Extra Syrup");
		chckbxExtraSyrup.setBounds(9, 21, 97, 23);
		paneNonCoffees_1.add(chckbxExtraSyrup);

		JCheckBox chckbxExtraShot = new JCheckBox("Extra Shot Espresso");
		chckbxExtraShot.setBounds(9, 51, 145, 23);
		paneNonCoffees_1.add(chckbxExtraShot);

		JLabel lblExtras = new JLabel("Extras");
		lblExtras.setBounds(108, 0, 46, 14);
		paneNonCoffees_1.add(lblExtras);

		JLabel lblNewLabel_1 = new JLabel("Total Cost");
		lblNewLabel_1.setBounds(32, 370, 73, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JTextField txtTotalCost = new JTextField();
		txtTotalCost.setBounds(470, 367, 100, 20);
		frame.getContentPane().add(txtTotalCost);
		txtTotalCost.setEditable(false);
		txtTotalCost.setColumns(10);

		JTabbedPane paneFoods = new JTabbedPane(JTabbedPane.TOP);
		paneFoods.setBounds(290, 79, 280, 225);
		frame.getContentPane().add(paneFoods);

		JPanel panePastries = new JPanel();
		paneFoods.addTab("Pastries", null, panePastries, null);
		panePastries.setLayout(null);

		JCheckBox chckbxCroissant = new JCheckBox("Croissant");
		chckbxCroissant.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxCroissant.setBounds(6, 40, 97, 23);
		panePastries.add(chckbxCroissant);
		grpPastriesSelect[0] = chckbxCroissant;

		JCheckBox chckbxChocoCroissant = new JCheckBox("Chocolate Croissant");
		chckbxChocoCroissant.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxChocoCroissant.setBounds(6, 66, 160, 23);
		panePastries.add(chckbxChocoCroissant);
		grpPastriesSelect[1] = chckbxChocoCroissant;

		JCheckBox chckbxBBMuffin = new JCheckBox("Blueberry Muffin");
		chckbxBBMuffin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxBBMuffin.setBounds(6, 92, 121, 23);
		panePastries.add(chckbxBBMuffin);
		grpPastriesSelect[2] = chckbxBBMuffin;

		JCheckBox chckbxBananaBread = new JCheckBox("Banana Bread");
		chckbxBananaBread.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxBananaBread.setBounds(6, 118, 121, 23);
		panePastries.add(chckbxBananaBread);
		grpPastriesSelect[3] = chckbxBananaBread;

		JCheckBox chckbxCinnamonRoll = new JCheckBox("Cinnamon Roll");
		chckbxCinnamonRoll.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		chckbxCinnamonRoll.setBounds(6, 144, 121, 23);
		panePastries.add(chckbxCinnamonRoll);
		grpPastriesSelect[4] = chckbxCinnamonRoll;

		JTextField txtCinnamonRoll = new JTextField();
		grpPastriesAmount[4] = txtCinnamonRoll;
		txtCinnamonRoll.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtCinnamonRoll.setColumns(10);
		txtCinnamonRoll.setBounds(220, 145, 50, 20);
		panePastries.add(txtCinnamonRoll);

		JTextField txtBananaBread = new JTextField();
		grpPastriesAmount[3] = txtBananaBread;
		txtBananaBread.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtBananaBread.setColumns(10);
		txtBananaBread.setBounds(220, 119, 50, 20);
		panePastries.add(txtBananaBread);

		JTextField txtBBMuffin = new JTextField();
		grpPastriesAmount[2] = txtBBMuffin;
		txtBBMuffin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtBBMuffin.setColumns(10);
		txtBBMuffin.setBounds(220, 93, 50, 20);
		panePastries.add(txtBBMuffin);

		JTextField txtChocoCroissant = new JTextField();
		grpPastriesAmount[1] = txtChocoCroissant;
		txtChocoCroissant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtChocoCroissant.setColumns(10);
		txtChocoCroissant.setBounds(220, 67, 50, 20);
		panePastries.add(txtChocoCroissant);

		JTextField txtCroissant = new JTextField();
		grpPastriesAmount[0] = txtCroissant;
		txtCroissant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtCroissant.setColumns(10);
		txtCroissant.setBounds(220, 41, 50, 20);
		panePastries.add(txtCroissant);

		JLabel lblPastries = new JLabel("Pastries");
		lblPastries.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblPastries.setBounds(88, 0, 105, 39);
		panePastries.add(lblPastries);

		JPanel paneDesserts = new JPanel();
		paneDesserts.setLayout(null);
		paneFoods.addTab("Desserts", null, paneDesserts, null);

		JCheckBox chckbxCheesecake = new JCheckBox("Cheesecake");
		chckbxCheesecake.setBounds(6, 16, 97, 23);
		paneDesserts.add(chckbxCheesecake);
		grpDessertsSelect[0] = chckbxCheesecake;

		JCheckBox chckbxChocoCake = new JCheckBox("Chocolate Cake");
		chckbxChocoCake.setBounds(6, 42, 121, 23);
		paneDesserts.add(chckbxChocoCake);
		grpDessertsSelect[1] = chckbxChocoCake;

		JCheckBox chckbxBrownies = new JCheckBox("Brownies");
		chckbxBrownies.setBounds(6, 68, 121, 23);
		paneDesserts.add(chckbxBrownies);
		grpDessertsSelect[2] = chckbxBrownies;

		JCheckBox chckbxDonut = new JCheckBox("Donut");
		chckbxDonut.setBounds(6, 94, 97, 23);
		paneDesserts.add(chckbxDonut);
		grpDessertsSelect[3] = chckbxDonut;

		JCheckBox chckbxIceCream = new JCheckBox("Ice Cream");
		chckbxIceCream.setBounds(6, 120, 97, 23);
		paneDesserts.add(chckbxIceCream);
		grpDessertsSelect[4] = chckbxIceCream;

		JTextField txtIceCream = new JTextField();
		txtIceCream.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtIceCream.setColumns(10);
		txtIceCream.setBounds(220, 121, 50, 20);
		paneDesserts.add(txtIceCream);
		grpDessertsAmount[4] = txtIceCream;
		JTextField txtDonut = new JTextField();
		txtDonut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtDonut.setColumns(10);
		txtDonut.setBounds(220, 95, 50, 20);
		paneDesserts.add(txtDonut);
		grpDessertsAmount[3] = txtDonut;
		JTextField txtBrownies = new JTextField();
		txtBrownies.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtBrownies.setColumns(10);
		txtBrownies.setBounds(220, 69, 50, 20);
		paneDesserts.add(txtBrownies);
		grpDessertsAmount[2] = txtBrownies;
		JTextField txtChocoCake = new JTextField();
		txtChocoCake.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtChocoCake.setColumns(10);
		txtChocoCake.setBounds(220, 43, 50, 20);
		paneDesserts.add(txtChocoCake);
		grpDessertsAmount[1] = txtChocoCake;
		JTextField txtCheesecake = new JTextField();
		txtCheesecake.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});
		txtCheesecake.setColumns(10);
		txtCheesecake.setBounds(220, 17, 50, 20);
		paneDesserts.add(txtCheesecake);
		grpDessertsAmount[0] = txtCheesecake;
		JLabel lblDesserts = new JLabel("Desserts");
		lblDesserts.setBounds(105, 0, 60, 14);
		paneDesserts.add(lblDesserts);

		JPanel paneActionMenu = new JPanel();
		paneActionMenu.setBounds(575, 79, 219, 318);
		frame.getContentPane().add(paneActionMenu);
		paneActionMenu.setLayout(null);

		JTextArea txtAreaReceipt = new JTextArea();
		txtAreaReceipt.setEditable(false);
		txtAreaReceipt.setBounds(10, 11, 199, 258);
		paneActionMenu.add(txtAreaReceipt);

		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCompute.setBounds(10, 284, 89, 23);
		paneActionMenu.add(btnCompute);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(120, 284, 89, 23);
		paneActionMenu.add(btnClear);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 315, 564, 14);
		frame.getContentPane().add(separator);

		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setBounds(32, 327, 58, 14);
		frame.getContentPane().add(lblDiscount);

		JRadioButton rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setBounds(218, 327, 73, 14);
		frame.getContentPane().add(rdbtnStudent);

		JRadioButton rdbtnSenior = new JRadioButton("Senior");
		rdbtnSenior.setBounds(329, 327, 73, 14);
		frame.getContentPane().add(rdbtnSenior);

		ButtonGroup groupDiscount = new ButtonGroup();
		groupDiscount.add(rdbtnStudent);
		groupDiscount.add(rdbtnSenior);

		JLabel lblNewLabel = new JLabel("KapePindot");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 45));
		lblNewLabel.setBounds(12, 11, 280, 45);
		frame.getContentPane().add(lblNewLabel);
	}

	// XXX computation logic- Felonia
	private void computeOrder(JTextArea recieptArea) {

		double subtotal = 0;

		recieptArea.setText("===== Cafe Reciept =====\n\n");

		boolean itemSelected = false;

		// XXX COFFEE
		for (int i = 0; i < grpCoffeeSelect.length; i++) {
			if (grpCoffeeSelect[i].isSelected()) {
				itemSelected = true;

				int quanty = Integer.parseInt(grpCoffeeSelect[i].getText());
				double itemTotal = quanty * coffeePrices[i];

				subtotal += itemTotal;

				recieptArea.append(grpCoffeeSelect[i] + " x" + quanty + " =" + itemTotal + ("\n"));

			}
		}

		// XXX Non Coffee drink
		for (int i = 0; i < grpNonCoffeeSelect.length; i++) {
			if (grpNonCoffeeSelect[i].isSelected()) {
				itemSelected = true;

				int quanty = Integer.parseInt(grpNonCoffeeSelect[i].getText());
				double itemTotal = quanty * nonCoffeePrices[i];

				subtotal += itemTotal;

				recieptArea.append(grpNonCoffeeSelect[i] + " x" + quanty + " =" + itemTotal + ("\n"));
			}
		}

		// XXX pastries
		for (int i = 0; i < grpPastriesSelect.length; i++) {
			if (grpPastriesSelect[i].isSelected()) {
				itemSelected = true;

				int quanty = Integer.parseInt(grpPastriesSelect[i].getText());
				double itemTotal = quanty * pastriesPrices[i];

				subtotal += itemTotal;

				recieptArea.append(grpPastriesSelect[i] + " x" + quanty + " =" + itemTotal + ("\n"));
			}
		}

		// XXX Deserts
		for (int i = 0; i < grpDessertsSelect.length; i++) {
			if (grpDessertsSelect[i].isSelected()) {
				itemSelected = true;

				int quanty = Integer.parseInt(grpDessertsSelect[i].getText());
				double itemTotal = quanty * dessertsPrices[i];

				subtotal += itemTotal;

				recieptArea.append(grpDessertsSelect[i] + " x" + quanty + " =" + itemTotal + ("\n"));
			}
		}
		// XXX Discount function
		if (!itemSelected) {
			recieptArea.setText("no Items Selecte");
			return;
		}
		recieptArea.append("\nSubtotal" + subtotal + "\n");

		double discount = 0;
		String discountType = "None";

	}
}