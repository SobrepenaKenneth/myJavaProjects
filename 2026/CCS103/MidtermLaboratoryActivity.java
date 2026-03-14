import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
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
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;

//v7

public class MidtermLaboratoryActivity {

	// XXX Prices

	double coffeePrices[] = { 120, 130, 140, 150, 160 };
	double nonCoffeePrices[] = { 100, 150, 150, 120 };
	double extrasPrices[] = { 50, 25, 20, 10, 60 };
	double pastriesPrices[] = { 60, 80, 100, 90, 110 };
	double dessertsPrices[] = { 140, 200, 25, 20, 35 };
	int id = 0;

	private JRadioButton rdbtnStudent;
	private JRadioButton rdbtnSenior;
	private JRadioButton rdbtnNone;
	

	// Coffee components
	JCheckBox[] grpCoffeeSelect = new JCheckBox[5];
	JTextField[] grpCoffeeAmount = new JTextField[5];

	// Non Coffee
	JCheckBox[] grpNonCoffeeSelect = new JCheckBox[4];
	JTextField[] grpNonCoffeeAmount = new JTextField[4];

	// Extras
	JCheckBox[] grpExtrasSelect = new JCheckBox[5];
	JTextField[] grpExtrasAmount = new JTextField[5];

	// Pastries
	JCheckBox[] grpPastriesSelect = new JCheckBox[5];
	JTextField[] grpPastriesAmount = new JTextField[5];

	// Desserts
	JCheckBox[] grpDessertsSelect = new JCheckBox[5];
	JTextField[] grpDessertsAmount = new JTextField[5];

	ButtonGroup groupDiscount = new ButtonGroup();
	private JTextField txtCashPayment;

	/**
	 * Launch the application.
	 */
	// XXX Main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MidtermLaboratoryActivity();
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

		JFrame frame = new JFrame("Café Management System");
		frame.setBounds(100, 100, 843, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().setBackground(new Color(255, 228, 196));

		JPanel paneCoffees = new JPanel();
		paneCoffees.setBounds(10, 32, 280, 200);
		paneCoffees.setLayout(null);
		paneCoffees.setBackground(new Color(253, 245, 230));

		// Coffee checkboxes with warm colors
		JCheckBox chckbxLatte = new JCheckBox("Espresso");
		chckbxLatte.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxLatte.setBounds(6, 36, 110, 23);
		chckbxLatte.setBackground(new Color(253, 245, 230));
		chckbxLatte.setForeground(new Color(92, 64, 51));
		paneCoffees.add(chckbxLatte);
		grpCoffeeSelect[0] = chckbxLatte;

		JCheckBox chckbxAmericano = new JCheckBox("Americano");
		chckbxAmericano.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxAmericano.setBounds(6, 62, 110, 23);
		chckbxAmericano.setBackground(new Color(253, 245, 230));
		chckbxAmericano.setForeground(new Color(92, 64, 51));
		paneCoffees.add(chckbxAmericano);
		grpCoffeeSelect[1] = chckbxAmericano;

		JCheckBox chckbxCappucino = new JCheckBox("Cappuccino");
		chckbxCappucino.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxCappucino.setBounds(6, 88, 110, 23);
		chckbxCappucino.setBackground(new Color(253, 245, 230));
		chckbxCappucino.setForeground(new Color(92, 64, 51));
		paneCoffees.add(chckbxCappucino);
		grpCoffeeSelect[2] = chckbxCappucino;

		JCheckBox chckbxCaramel = new JCheckBox("Caramel");
		chckbxCaramel.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxCaramel.setBounds(6, 114, 110, 23);
		chckbxCaramel.setBackground(new Color(253, 245, 230));
		chckbxCaramel.setForeground(new Color(92, 64, 51));
		paneCoffees.add(chckbxCaramel);
		grpCoffeeSelect[3] = chckbxCaramel;

		JCheckBox chckbxMocha = new JCheckBox("Mocha");
		chckbxMocha.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxMocha.setBounds(6, 140, 110, 23);
		chckbxMocha.setBackground(new Color(253, 245, 230));
		chckbxMocha.setForeground(new Color(92, 64, 51));
		paneCoffees.add(chckbxMocha);
		grpCoffeeSelect[4] = chckbxMocha;

		JLabel lblCoffee = new JLabel("Coffee");
		lblCoffee.setBounds(90, 0, 98, 37);
		lblCoffee.setForeground(new Color(240, 128, 128)); // Saddle brown
		lblCoffee.setFont(new Font("Lucida Handwriting", Font.BOLD, 26));
		paneCoffees.add(lblCoffee);

		JTextField txtEspresso = new JTextField();
		txtEspresso.addKeyListener(ignoreLetters);
		txtEspresso.setBounds(220, 39, 50, 20);
		txtEspresso.setBackground(new Color(255, 255, 245));
		txtEspresso.setForeground(new Color(92, 64, 51));
		txtEspresso.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneCoffees.add(txtEspresso);
		txtEspresso.setColumns(10);
		grpCoffeeAmount[0] = txtEspresso;

		JTextField txtAmericano = new JTextField();
		txtAmericano.addKeyListener(ignoreLetters);
		txtAmericano.setColumns(10);
		txtAmericano.setBounds(220, 65, 50, 20);
		txtAmericano.setBackground(new Color(255, 255, 245));
		txtAmericano.setForeground(new Color(92, 64, 51));
		txtAmericano.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneCoffees.add(txtAmericano);
		grpCoffeeAmount[1] = txtAmericano;

		JTextField txtCappucino = new JTextField();
		txtCappucino.addKeyListener(ignoreLetters);
		txtCappucino.setColumns(10);
		txtCappucino.setBounds(220, 91, 50, 20);
		txtCappucino.setBackground(new Color(255, 255, 245));
		txtCappucino.setForeground(new Color(92, 64, 51));
		txtCappucino.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneCoffees.add(txtCappucino);
		grpCoffeeAmount[2] = txtCappucino;

		JTextField txtCaramel = new JTextField();
		txtCaramel.addKeyListener(ignoreLetters);
		txtCaramel.setColumns(10);
		txtCaramel.setBounds(220, 117, 50, 20);
		txtCaramel.setBackground(new Color(255, 255, 245));
		txtCaramel.setForeground(new Color(92, 64, 51));
		txtCaramel.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneCoffees.add(txtCaramel);
		grpCoffeeAmount[3] = txtCaramel;

		JTextField txtMocha = new JTextField();
		txtMocha.addKeyListener(ignoreLetters);
		txtMocha.setColumns(10);
		txtMocha.setBounds(220, 143, 50, 20);
		txtMocha.setBackground(new Color(255, 255, 245));
		txtMocha.setForeground(new Color(92, 64, 51));
		txtMocha.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneCoffees.add(txtMocha);
		grpCoffeeAmount[4] = txtMocha;
		

		JTabbedPane paneCoffeeSelect = new JTabbedPane(JTabbedPane.TOP);
		paneCoffeeSelect.setBounds(12, 73, 280, 218);
		paneCoffeeSelect.setBackground(new Color(230, 210, 190)); // Light mocha
		paneCoffeeSelect.setForeground(new Color(205, 92, 92));
		paneCoffeeSelect.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		frame.getContentPane().add(paneCoffeeSelect);
		paneCoffeeSelect.add(paneCoffees);
		paneCoffeeSelect.setTitleAt(0, "Coffee");

		JPanel paneNonCoffees = new JPanel();
		paneNonCoffees.setBounds(300, 32, 280, 200);
		paneNonCoffees.setLayout(null);
		paneNonCoffees.setBackground(new Color(253, 245, 230)); // Light beige

		JTextField txtLemonade = new JTextField();
		grpNonCoffeeAmount[3] = txtLemonade;
		txtLemonade.addKeyListener(ignoreLetters);
		txtLemonade.setColumns(10);
		txtLemonade.setBounds(220, 124, 50, 20);
		txtLemonade.setBackground(new Color(255, 255, 245));
		txtLemonade.setForeground(new Color(92, 64, 51));
		txtLemonade.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneNonCoffees.add(txtLemonade);

		JTextField txtMatchaLatte = new JTextField();
		grpNonCoffeeAmount[1] = txtMatchaLatte;
		txtMatchaLatte.addKeyListener(ignoreLetters);
		txtMatchaLatte.setColumns(10);
		txtMatchaLatte.setBounds(220, 72, 50, 20);
		txtMatchaLatte.setBackground(new Color(255, 255, 245));
		txtMatchaLatte.setForeground(new Color(92, 64, 51));
		txtMatchaLatte.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneNonCoffees.add(txtMatchaLatte);


		JTextField txtHotChoco = new JTextField();
		grpNonCoffeeAmount[0] = txtHotChoco;
		txtHotChoco.addKeyListener(ignoreLetters);
		txtHotChoco.setColumns(10);
		txtHotChoco.setBounds(220, 46, 50, 20);
		txtHotChoco.setBackground(new Color(255, 255, 245));
		txtHotChoco.setForeground(new Color(92, 64, 51));
		txtHotChoco.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneNonCoffees.add(txtHotChoco);
		
		
		JTextField txtStrawberryMilk = new JTextField();
		grpNonCoffeeAmount[2] = txtStrawberryMilk;
		txtStrawberryMilk.addKeyListener(ignoreLetters);
		txtStrawberryMilk.setColumns(10);
		txtStrawberryMilk.setBounds(220, 98, 50, 20);
		txtStrawberryMilk.setBackground(new Color(255, 255, 245));
		txtStrawberryMilk.setForeground(new Color(92, 64, 51));
		txtStrawberryMilk.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneNonCoffees.add(txtStrawberryMilk);

		JCheckBox chckbxLemonade = new JCheckBox("Fresh Lemonade");
		chckbxLemonade.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxLemonade.setBounds(6, 123, 150, 23);
		chckbxLemonade.setBackground(new Color(253, 245, 230));
		chckbxLemonade.setForeground(new Color(92, 64, 51));
		paneNonCoffees.add(chckbxLemonade);
		grpNonCoffeeSelect[3] = chckbxLemonade;

		JCheckBox chckbxStrawberryMilk = new JCheckBox("Strawberry Milk");
		chckbxStrawberryMilk.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxStrawberryMilk.setBounds(6, 97, 137, 23);
		chckbxStrawberryMilk.setBackground(new Color(253, 245, 230));
		chckbxStrawberryMilk.setForeground(new Color(92, 64, 51));
		paneNonCoffees.add(chckbxStrawberryMilk);
		grpNonCoffeeSelect[2] = chckbxStrawberryMilk;

		JCheckBox chckbxMatchaLatte = new JCheckBox("Matcha Latte");
		chckbxMatchaLatte.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxMatchaLatte.setBounds(6, 71, 124, 23);
		chckbxMatchaLatte.setBackground(new Color(253, 245, 230));
		chckbxMatchaLatte.setForeground(new Color(92, 64, 51));
		paneNonCoffees.add(chckbxMatchaLatte);
		grpNonCoffeeSelect[1] = chckbxMatchaLatte;

		JCheckBox chckbxHotChoco = new JCheckBox("Hot Chocolate");
		chckbxHotChoco.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxHotChoco.setBounds(6, 45, 124, 23);
		chckbxHotChoco.setBackground(new Color(253, 245, 230));
		chckbxHotChoco.setForeground(new Color(92, 64, 51));
		paneNonCoffees.add(chckbxHotChoco);
		grpNonCoffeeSelect[0] = chckbxHotChoco;
		paneCoffeeSelect.add(paneNonCoffees);
		paneCoffeeSelect.setTitleAt(1, "Non Coffee");

		JLabel lblNoncoffee = new JLabel("Non-Coffee");
		lblNoncoffee.setForeground(new Color(240, 128, 128));
		lblNoncoffee.setFont(new Font("Lucida Handwriting", Font.BOLD, 26));
		lblNoncoffee.setBounds(57, 0, 163, 38);
		paneNonCoffees.add(lblNoncoffee);

		JPanel paneExtras = new JPanel();
		paneExtras.setLayout(null);
		paneExtras.setBackground(new Color(253, 245, 230));
		paneCoffeeSelect.addTab("Extras", null, paneExtras, null);

		JCheckBox chckbxExtraEspresso = new JCheckBox("Extra Shot Espresso");
		chckbxExtraEspresso.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxExtraEspresso.setBounds(6, 36, 163, 23);
		chckbxExtraEspresso.setBackground(new Color(253, 245, 230));
		chckbxExtraEspresso.setForeground(new Color(92, 64, 51));
		paneExtras.add(chckbxExtraEspresso);
		grpExtrasSelect[0] = chckbxExtraEspresso;

		JCheckBox chckbxExtraSyrup = new JCheckBox("Extra Syrup");
		chckbxExtraSyrup.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxExtraSyrup.setBounds(6, 62, 129, 23);
		chckbxExtraSyrup.setBackground(new Color(253, 245, 230));
		chckbxExtraSyrup.setForeground(new Color(92, 64, 51));
		paneExtras.add(chckbxExtraSyrup);
		grpExtrasSelect[1] = chckbxExtraSyrup;

		JCheckBox chckbxWhippedCream = new JCheckBox("Whipped Cream");
		chckbxWhippedCream.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxWhippedCream.setBounds(6, 88, 145, 23);
		chckbxWhippedCream.setBackground(new Color(253, 245, 230));
		chckbxWhippedCream.setForeground(new Color(92, 64, 51));
		paneExtras.add(chckbxWhippedCream);
		grpExtrasSelect[2] = chckbxWhippedCream;

		JCheckBox chckbxExtraIce = new JCheckBox("Extra Ice");
		chckbxExtraIce.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxExtraIce.setBounds(6, 114, 145, 23);
		chckbxExtraIce.setBackground(new Color(253, 245, 230));
		chckbxExtraIce.setForeground(new Color(92, 64, 51));
		paneExtras.add(chckbxExtraIce);
		grpExtrasSelect[3] = chckbxExtraIce;

		JCheckBox chckbxToLarge = new JCheckBox("Upgrade to Large");
		chckbxToLarge.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxToLarge.setBounds(6, 140, 163, 23);
		chckbxToLarge.setBackground(new Color(253, 245, 230));
		chckbxToLarge.setForeground(new Color(92, 64, 51));
		paneExtras.add(chckbxToLarge);
		grpExtrasSelect[4] = chckbxToLarge;

		JTextField txtExtraEspresso_1 = new JTextField();
		txtExtraEspresso_1.addKeyListener(ignoreLetters);
		txtExtraEspresso_1.setColumns(10);
		txtExtraEspresso_1.setBounds(217, 37, 50, 20);
		txtExtraEspresso_1.setBackground(new Color(255, 255, 245));
		txtExtraEspresso_1.setForeground(new Color(92, 64, 51));
		txtExtraEspresso_1.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneExtras.add(txtExtraEspresso_1);
		grpExtrasAmount[0] = txtExtraEspresso_1;

		JTextField txtExtraSyrup = new JTextField();
		txtExtraSyrup.addKeyListener(ignoreLetters);
		txtExtraSyrup.setColumns(10);
		txtExtraSyrup.setBounds(217, 63, 50, 20);
		txtExtraSyrup.setBackground(new Color(255, 255, 245));
		txtExtraSyrup.setForeground(new Color(92, 64, 51));
		txtExtraSyrup.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneExtras.add(txtExtraSyrup);
		grpExtrasAmount[1] = txtExtraSyrup;

		JTextField txtWhippedCream = new JTextField();
		txtWhippedCream.addKeyListener(ignoreLetters);
		txtWhippedCream.setColumns(10);
		txtWhippedCream.setBounds(217, 89, 50, 20);
		txtWhippedCream.setBackground(new Color(255, 255, 245));
		txtWhippedCream.setForeground(new Color(92, 64, 51));
		txtWhippedCream.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneExtras.add(txtWhippedCream);
		grpExtrasAmount[2] = txtWhippedCream;

		JTextField txtExtraIce = new JTextField();
		txtExtraIce.addKeyListener(ignoreLetters);
		txtExtraIce.setColumns(10);
		txtExtraIce.setBounds(217, 115, 50, 20);
		txtExtraIce.setBackground(new Color(255, 255, 245));
		txtExtraIce.setForeground(new Color(92, 64, 51));
		txtExtraIce.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneExtras.add(txtExtraIce);
		grpExtrasAmount[3] = txtExtraIce;

		JTextField txtToLarge = new JTextField();
		txtToLarge.addKeyListener(ignoreLetters);
		txtToLarge.setColumns(10);
		txtToLarge.setBounds(217, 141, 50, 20);
		txtToLarge.setBackground(new Color(255, 255, 245));
		txtToLarge.setForeground(new Color(92, 64, 51));
		txtToLarge.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneExtras.add(txtToLarge);
		grpExtrasAmount[4] = txtToLarge;

		JLabel lblExtra = new JLabel("Extras");
		lblExtra.setForeground(new Color(240, 128, 128));
		lblExtra.setFont(new Font("Lucida Handwriting", Font.BOLD, 24));
		lblExtra.setBounds(89, 0, 98, 39);
		paneExtras.add(lblExtra);

		JTabbedPane paneFoods = new JTabbedPane(JTabbedPane.TOP);
		paneFoods.setBounds(290, 73, 280, 218);
		paneFoods.setBackground(new Color(230, 210, 190));
		paneFoods.setForeground(new Color(205, 92, 92));
		paneFoods.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		frame.getContentPane().add(paneFoods);

		JPanel panePastries = new JPanel();
		paneFoods.addTab("Pastries", null, panePastries, null);
		panePastries.setLayout(null);
		panePastries.setBackground(new Color(253, 245, 230));

		JCheckBox chckbxCroissant = new JCheckBox("Croissant");
		chckbxCroissant.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxCroissant.setBounds(6, 36, 176, 23);
		chckbxCroissant.setBackground(new Color(253, 245, 230));
		chckbxCroissant.setForeground(new Color(92, 64, 51));
		panePastries.add(chckbxCroissant);
		grpPastriesSelect[0] = chckbxCroissant;

		JCheckBox chckbxChocoCroissant = new JCheckBox("Chocolate Croissant");
		chckbxChocoCroissant.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxChocoCroissant.setBounds(6, 62, 176, 23);
		chckbxChocoCroissant.setBackground(new Color(253, 245, 230));
		chckbxChocoCroissant.setForeground(new Color(92, 64, 51));
		panePastries.add(chckbxChocoCroissant);
		grpPastriesSelect[1] = chckbxChocoCroissant;

		JCheckBox chckbxBBMuffin = new JCheckBox("Blueberry Muffin");
		chckbxBBMuffin.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxBBMuffin.setBounds(6, 88, 176, 23);
		chckbxBBMuffin.setBackground(new Color(253, 245, 230));
		chckbxBBMuffin.setForeground(new Color(92, 64, 51));
		panePastries.add(chckbxBBMuffin);
		grpPastriesSelect[2] = chckbxBBMuffin;

		JCheckBox chckbxBananaBread = new JCheckBox("Banana Bread");
		chckbxBananaBread.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxBananaBread.setBounds(6, 114, 176, 23);
		chckbxBananaBread.setBackground(new Color(253, 245, 230));
		chckbxBananaBread.setForeground(new Color(92, 64, 51));
		panePastries.add(chckbxBananaBread);
		grpPastriesSelect[3] = chckbxBananaBread;

		JCheckBox chckbxCinnamonRoll = new JCheckBox("Cinnamon Roll");
		chckbxCinnamonRoll.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxCinnamonRoll.setBounds(6, 140, 176, 23);
		chckbxCinnamonRoll.setBackground(new Color(253, 245, 230));
		chckbxCinnamonRoll.setForeground(new Color(92, 64, 51));
		panePastries.add(chckbxCinnamonRoll);
		grpPastriesSelect[4] = chckbxCinnamonRoll;

		JTextField txtCinnamonRoll = new JTextField();
		grpPastriesAmount[4] = txtCinnamonRoll;
		txtCinnamonRoll.addKeyListener(ignoreLetters);
		txtCinnamonRoll.setColumns(10);
		txtCinnamonRoll.setBounds(220, 141, 50, 20);
		txtCinnamonRoll.setBackground(new Color(255, 255, 245));
		txtCinnamonRoll.setForeground(new Color(92, 64, 51));
		txtCinnamonRoll.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		panePastries.add(txtCinnamonRoll);

		JTextField txtBananaBread = new JTextField();
		grpPastriesAmount[3] = txtBananaBread;
		txtBananaBread.addKeyListener(ignoreLetters);
		txtBananaBread.setColumns(10);
		txtBananaBread.setBounds(220, 115, 50, 20);
		txtBananaBread.setBackground(new Color(255, 255, 245));
		txtBananaBread.setForeground(new Color(92, 64, 51));
		txtBananaBread.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		panePastries.add(txtBananaBread);

		JTextField txtBBMuffin = new JTextField();
		grpPastriesAmount[2] = txtBBMuffin;
		txtBBMuffin.addKeyListener(ignoreLetters);
		txtBBMuffin.setColumns(10);
		txtBBMuffin.setBounds(220, 89, 50, 20);
		txtBBMuffin.setBackground(new Color(255, 255, 245));
		txtBBMuffin.setForeground(new Color(92, 64, 51));
		txtBBMuffin.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		panePastries.add(txtBBMuffin);

		JTextField txtChocoCroissant = new JTextField();
		grpPastriesAmount[1] = txtChocoCroissant;
		txtChocoCroissant.addKeyListener(ignoreLetters);
		txtChocoCroissant.setColumns(10);
		txtChocoCroissant.setBounds(220, 63, 50, 20);
		txtChocoCroissant.setBackground(new Color(255, 255, 245));
		txtChocoCroissant.setForeground(new Color(92, 64, 51));
		txtChocoCroissant.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		panePastries.add(txtChocoCroissant);

		JTextField txtCroissant = new JTextField();
		grpPastriesAmount[0] = txtCroissant;
		txtCroissant.addKeyListener(ignoreLetters);
		txtCroissant.setColumns(10);
		txtCroissant.setBounds(220, 37, 50, 20);
		txtCroissant.setBackground(new Color(255, 255, 245));
		txtCroissant.setForeground(new Color(92, 64, 51));
		txtCroissant.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		panePastries.add(txtCroissant);

		JLabel lblPastries = new JLabel("Pastries");
		lblPastries.setForeground(new Color(240, 128, 128));
		lblPastries.setFont(new Font("Lucida Handwriting", Font.BOLD, 26));
		lblPastries.setBounds(71, 0, 129, 37);
		panePastries.add(lblPastries);

		JPanel paneDesserts = new JPanel();
		paneDesserts.setLayout(null);
		paneDesserts.setBackground(new Color(253, 245, 230));
		paneFoods.addTab("Desserts", null, paneDesserts, null);

		JCheckBox chckbxCheesecake = new JCheckBox("Cheesecake");
		chckbxCheesecake.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxCheesecake.setBounds(6, 37, 138, 23);
		chckbxCheesecake.setBackground(new Color(253, 245, 230));
		chckbxCheesecake.setForeground(new Color(92, 64, 51));
		paneDesserts.add(chckbxCheesecake);
		grpDessertsSelect[0] = chckbxCheesecake;

		JCheckBox chckbxChocoCake = new JCheckBox("Chocolate Cake");
		chckbxChocoCake.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxChocoCake.setBounds(6, 63, 138, 23);
		chckbxChocoCake.setBackground(new Color(253, 245, 230));
		chckbxChocoCake.setForeground(new Color(92, 64, 51));
		paneDesserts.add(chckbxChocoCake);
		grpDessertsSelect[1] = chckbxChocoCake;

		JCheckBox chckbxBrownies = new JCheckBox("Brownies");
		chckbxBrownies.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxBrownies.setBounds(6, 89, 121, 23);
		chckbxBrownies.setBackground(new Color(253, 245, 230));
		chckbxBrownies.setForeground(new Color(92, 64, 51));
		paneDesserts.add(chckbxBrownies);
		grpDessertsSelect[2] = chckbxBrownies;

		JCheckBox chckbxDonut = new JCheckBox("Donut");
		chckbxDonut.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxDonut.setBounds(6, 115, 97, 23);
		chckbxDonut.setBackground(new Color(253, 245, 230));
		chckbxDonut.setForeground(new Color(92, 64, 51));
		paneDesserts.add(chckbxDonut);
		grpDessertsSelect[3] = chckbxDonut;

		JCheckBox chckbxIceCream = new JCheckBox("Ice Cream");
		chckbxIceCream.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		chckbxIceCream.setBounds(6, 141, 97, 23);
		chckbxIceCream.setBackground(new Color(253, 245, 230));
		chckbxIceCream.setForeground(new Color(92, 64, 51));
		paneDesserts.add(chckbxIceCream);
		grpDessertsSelect[4] = chckbxIceCream;

		JTextField txtIceCream = new JTextField();
		txtIceCream.addKeyListener(ignoreLetters);
		txtIceCream.setColumns(10);
		txtIceCream.setBounds(220, 142, 50, 20);
		txtIceCream.setBackground(new Color(255, 255, 245));
		txtIceCream.setForeground(new Color(92, 64, 51));
		txtIceCream.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneDesserts.add(txtIceCream);
		grpDessertsAmount[4] = txtIceCream;

		JTextField txtDonut = new JTextField();
		txtDonut.addKeyListener(ignoreLetters);
		txtDonut.setColumns(10);
		txtDonut.setBounds(220, 116, 50, 20);
		txtDonut.setBackground(new Color(255, 255, 245));
		txtDonut.setForeground(new Color(92, 64, 51));
		txtDonut.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneDesserts.add(txtDonut);
		grpDessertsAmount[3] = txtDonut;

		JTextField txtBrownies = new JTextField();
		txtBrownies.addKeyListener(ignoreLetters);
		txtBrownies.setColumns(10);
		txtBrownies.setBounds(220, 90, 50, 20);
		txtBrownies.setBackground(new Color(255, 255, 245));
		txtBrownies.setForeground(new Color(92, 64, 51));
		txtBrownies.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneDesserts.add(txtBrownies);
		grpDessertsAmount[2] = txtBrownies;

		JTextField txtChocoCake = new JTextField();
		txtChocoCake.addKeyListener(ignoreLetters);
		txtChocoCake.setColumns(10);
		txtChocoCake.setBounds(220, 64, 50, 20);
		txtChocoCake.setBackground(new Color(255, 255, 245));
		txtChocoCake.setForeground(new Color(92, 64, 51));
		txtChocoCake.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneDesserts.add(txtChocoCake);
		grpDessertsAmount[1] = txtChocoCake;

		JTextField txtCheesecake = new JTextField();
		txtCheesecake.addKeyListener(ignoreLetters);
		txtCheesecake.setColumns(10);
		txtCheesecake.setBounds(220, 38, 50, 20);
		txtCheesecake.setBackground(new Color(255, 255, 245));
		txtCheesecake.setForeground(new Color(92, 64, 51));
		txtCheesecake.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneDesserts.add(txtCheesecake);
		grpDessertsAmount[0] = txtCheesecake;

		JLabel lblDesserts = new JLabel("Desserts");
		lblDesserts.setForeground(new Color(240, 128, 128));
		lblDesserts.setFont(new Font("Lucida Handwriting", Font.BOLD, 26));
		lblDesserts.setBounds(70, 0, 121, 37);
		paneDesserts.add(lblDesserts);

		JPanel paneActionMenu = new JPanel();
		paneActionMenu.setBackground(new Color(230, 210, 190)); // Light mocha
		paneActionMenu.setBounds(575, 11, 249, 343);
		paneActionMenu.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145), 2));
		frame.getContentPane().add(paneActionMenu);
		paneActionMenu.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 229, 254);
		scrollPane.getViewport().setBackground(new Color(255, 255, 245));
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		paneActionMenu.add(scrollPane);

		JTextArea txtAreaReceipt = new JTextArea();
		txtAreaReceipt.setFont(new Font("Monospaced", Font.PLAIN, 10));
		txtAreaReceipt.setBackground(new Color(255, 255, 245));
		txtAreaReceipt.setForeground(new Color(92, 64, 51));
		scrollPane.setViewportView(txtAreaReceipt);
		txtAreaReceipt.setEditable(false);

		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computeOrder(txtAreaReceipt);
			}
		});
		btnCompute.setBounds(20, 307, 95, 25);
		btnCompute.setBackground(new Color(205, 133, 63)); // Coffee brown
		btnCompute.setForeground(new Color(255, 255, 255));
		btnCompute.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnCompute.setBorder(BorderFactory.createRaisedBevelBorder());
		btnCompute.setFocusPainted(false);
		paneActionMenu.add(btnCompute);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields(txtAreaReceipt);
			}
		});
		btnClear.setBounds(137, 307, 90, 25);
		btnClear.setBackground(new Color(205, 133, 63)); // Darker coffee brown
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnClear.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.setFocusPainted(false);
		paneActionMenu.add(btnClear);

		JLabel lblNewLabel_2 = new JLabel("Receipt");
		lblNewLabel_2.setFont(new Font("Lucida Handwriting", Font.BOLD, 24));
		lblNewLabel_2.setForeground(new Color(240, 128, 128));
		lblNewLabel_2.setBounds(72, 11, 115, 32);
		paneActionMenu.add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 302, 564, 2);
		separator.setBackground(new Color(205, 170, 145));
		separator.setForeground(new Color(205, 170, 145));
		frame.getContentPane().add(separator);

		JLabel lblDiscount = new JLabel("Discount:");
		lblDiscount.setBounds(12, 314, 70, 14);
		lblDiscount.setForeground(new Color(92, 64, 51));
		lblDiscount.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		frame.getContentPane().add(lblDiscount);

		rdbtnNone = new JRadioButton("None");
		rdbtnNone.setForeground(new Color(92, 64, 51));
		rdbtnNone.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		rdbtnNone.setBackground(new Color(255, 228, 196));
		rdbtnNone.setBounds(319, 314, 90, 14);
		frame.getContentPane().add(rdbtnNone);
		
		rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.setBounds(410, 314, 90, 14);
		rdbtnStudent.setBackground(new Color(255, 228, 196));
		rdbtnStudent.setForeground(new Color(92, 64, 51));
		rdbtnStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnStudent);

		rdbtnSenior = new JRadioButton("Senior");
		rdbtnSenior.setBounds(500, 314, 70, 14);
		rdbtnSenior.setBackground(new Color(255, 228, 196));
		rdbtnSenior.setForeground(new Color(92, 64, 51));
		rdbtnSenior.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		frame.getContentPane().add(rdbtnSenior);
		
		groupDiscount.add(rdbtnNone);
		groupDiscount.add(rdbtnStudent);
		groupDiscount.add(rdbtnSenior);

		JLabel lblPayment = new JLabel("Payment:");
		lblPayment.setBounds(12, 334, 73, 14);
		lblPayment.setForeground(new Color(92, 64, 51));
		lblPayment.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		frame.getContentPane().add(lblPayment);

		txtCashPayment = new JTextField();
		txtCashPayment.setBounds(410, 332, 160, 22);
		txtCashPayment.setBackground(new Color(255, 255, 245));
		txtCashPayment.setForeground(new Color(92, 64, 51));
		txtCashPayment.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 145)));
		frame.getContentPane().add(txtCashPayment);
		txtCashPayment.setColumns(10);
		txtCashPayment.addKeyListener(ignoreLetters);

		JLabel lblTag = new JLabel("₱");
		lblTag.setBounds(400, 334, 20, 14);
		lblTag.setForeground(new Color(92, 64, 51));
		lblTag.setFont(new Font("Segoe UI", Font.BOLD, 12));
		frame.getContentPane().add(lblTag);

		JLabel lblKapePindot = new JLabel("KapePindot");
		lblKapePindot.setFont(new Font("Lucida Handwriting", Font.BOLD, 34));
		lblKapePindot.setForeground(new Color(240, 128, 128));
		lblKapePindot.setBounds(12, 11, 249, 41);
		frame.getContentPane().add(lblKapePindot);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(205, 170, 145));
		separator_1.setBackground(new Color(205, 170, 145));
		separator_1.setBounds(6, 60, 564, 2);
		frame.getContentPane().add(separator_1);
		
		

		frame.setVisible(true);
	}

	KeyListener ignoreLetters = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {

			if (Character.isLetter(e.getKeyChar()))
				e.consume();
		}
		// TODO Auto-generated method stub

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	};;

	// XXX computation logic- Felonia addendum and revision - Paz (im tired) Fixed
	// design - ken format method
	private void computeOrder(JTextArea recieptArea) {
		int quantity;
		double subtotal = 0;
		double discount = 0;
		double cashPayment = 0;
		double change = 0;
		double finalTotal = 0;

		recieptArea.setText(
				"========== KapePindot Cafe ==========\n\n" + String.format("%-20s %3s %12s\n", "Item", "Qty", "Total")
						+ "-------------------------------------\n");
		boolean itemSelected = false;

		// XXX COFFEE
		for (int i = 0; i < grpCoffeeSelect.length; i++) {
			if (grpCoffeeSelect[i].isSelected()) {
				itemSelected = true;
				try {
					quantity = Integer.parseInt(grpCoffeeAmount[i].getText());
				} catch (NumberFormatException e) {

					quantity = 1;
				}
				double itemTotal = quantity * coffeePrices[i];

				subtotal += itemTotal;

				recieptArea
						.append(String.format("%-20s %3d %12.2f\n", grpCoffeeSelect[i].getText(), quantity, itemTotal));

			}
		}

		// XXX Non Coffee drink
		for (int i = 0; i < grpNonCoffeeSelect.length; i++) {
			if (grpNonCoffeeSelect[i].isSelected()) {
				itemSelected = true;
				try {
					quantity = Integer.parseInt(grpNonCoffeeAmount[i].getText());
				} catch (NumberFormatException e) {

					quantity = 1;
				}
				double itemTotal = quantity * nonCoffeePrices[i];

				subtotal += itemTotal;

				recieptArea
						.append(String.format("%-20s %3d %12.2f\n", grpNonCoffeeSelect[i].getText(), quantity, itemTotal));
			}
		}

		// XXX Extras
		for (int i = 0; i < grpExtrasSelect.length; i++) {
			if (grpExtrasSelect[i].isSelected()) {
				itemSelected = true;
				try {
					quantity = Integer.parseInt(grpExtrasAmount[i].getText());
				} catch (NumberFormatException e) {

					quantity = 1;
				}
				double itemTotal = quantity * extrasPrices[i];

				subtotal += itemTotal;

				recieptArea
						.append(String.format("%-20s %3d %12.2f\n", grpExtrasSelect[i].getText(), quantity, itemTotal));
			}
		}

		// XXX pastries
		for (int i = 0; i < grpPastriesSelect.length; i++) {
			if (grpPastriesSelect[i].isSelected()) {
				itemSelected = true;
				try {
					quantity = Integer.parseInt(grpPastriesAmount[i].getText());
				} catch (NumberFormatException e) {

					quantity = 1;
				}
				double itemTotal = quantity * pastriesPrices[i];

				subtotal += itemTotal;

				recieptArea
						.append(String.format("%-20s %3d %12.2f\n", grpPastriesSelect[i].getText(), quantity, itemTotal));
			}
		}

		// XXX Deserts
		for (int i = 0; i < grpDessertsSelect.length; i++) {
			if (grpDessertsSelect[i].isSelected()) {
				itemSelected = true;
				try {
					quantity = Integer.parseInt(grpDessertsAmount[i].getText());
				} catch (NumberFormatException e) {

					quantity = 1;
				}
				double itemTotal = quantity * dessertsPrices[i];

				subtotal += itemTotal;

				recieptArea
						.append(String.format("%-20s %3d %12.2f\n", grpDessertsSelect[i].getText(), quantity, itemTotal));
			}
		}

		// Finalise Receipt
		if (!itemSelected) {
			recieptArea.setText(" No Items Selected");
			return;
		}

		recieptArea.append("-------------------------------------\n");
		recieptArea.append(String.format("%-23s ₱%10.2f\n", "Subtotal", subtotal));

		recieptArea.append("\n Discount:");

		if (rdbtnStudent.isSelected()) {

			recieptArea.append(" Student(10%)\n");
			discount = .10;

		} else if (rdbtnSenior.isSelected()) {

			recieptArea.append(" Senior(20%)\n");
			discount = .20;

		} else {

			recieptArea.append(" None Applied\n");
			discount = 0.00;

		}

		// Calculate Discount
		double discountedValue = subtotal * discount;

		finalTotal = subtotal - discountedValue;
		recieptArea.append(String.format("%-23s -₱%9.2f\n", "Discount", discountedValue));
		try {
			cashPayment = Double.parseDouble(txtCashPayment.getText());
		} catch (NumberFormatException e) {
			cashPayment = 0;
		}
		change = cashPayment - finalTotal;
		if (change < 0)
			change = 0;

		recieptArea.append(String.format("%-23s ₱%10.2f\n", "Grand Total", finalTotal));
		recieptArea.append(String.format("%-23s ₱%10.2f\n", "Payment", cashPayment));
		recieptArea.append(String.format("%-23s ₱%10.2f\n", "Change", change));

		recieptArea.append("\nReceipt No: " + id++ + "\n");
		recieptArea.append(LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	}

	private void clearFields(JTextArea recieptArea) {
		
		groupDiscount.setSelected(null, false);

		for (int i = 0; i < grpCoffeeSelect.length; i++) {
			grpCoffeeSelect[i].setSelected(false);
			grpCoffeeAmount[i].setText("");
		}
		for (int i = 0; i < grpNonCoffeeSelect.length; i++) {
			grpNonCoffeeSelect[i].setSelected(false);
			grpNonCoffeeAmount[i].setText("");
		}

		for (int i = 0; i < grpExtrasSelect.length; i++) {
			grpExtrasSelect[i].setSelected(false);
			grpExtrasAmount[i].setText("");
		}
		
		for (int i = 0; i < grpDessertsSelect.length; i++) {
			grpDessertsSelect[i].setSelected(false);
			grpDessertsAmount[i].setText("");
		}
		
		for (int i = 0; i < grpPastriesSelect.length; i++) {
			grpPastriesSelect[i].setSelected(false);
			grpPastriesAmount[i].setText("");
		}
		
		recieptArea.setText("");
		txtCashPayment.setText("");
	}
}