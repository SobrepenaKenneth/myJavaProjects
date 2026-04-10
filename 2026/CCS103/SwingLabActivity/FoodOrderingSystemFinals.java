package SwingLabActivity;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultListModel;

public class FoodOrderingSystemFinals extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final JSeparator separator = new JSeparator();

	private final JLabel lblHeader = new JLabel("♥ Kenneth Restaurant ♥");
	private final JLabel lblName = new JLabel("Name : ");
	private final JLabel lblMainFood = new JLabel("Main Food :");
	private final JLabel lblOrderType = new JLabel("Order Type :");
	private final JLabel lblExtras = new JLabel("Extras :");
	private final JLabel lblAddOns = new JLabel("Add-Ons");

	private final JTextField txtName = new JTextField();

	private final JComboBox<String> cboMainFood = new JComboBox<String>();

	private final DefaultListModel<String> addonModel = new DefaultListModel<String>();
	private final JList<String> addonList = new JList<String>(addonModel);

	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPaneTextArea = new JScrollPane();

	private final JRadioButton rdbtnDineIn = new JRadioButton("Dine-In");
	private final JRadioButton rdbtnTakeout = new JRadioButton("Take-Out");

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private final JCheckBox chckbxExtraSauce = new JCheckBox("Extra Sauce");
	private final JCheckBox chckbxSpicy = new JCheckBox("Spicy");

	private final JTextArea txtAreaOrderSummary = new JTextArea();

	private final JButton btnPlaceOrder = new JButton("Place Order");
	private final JButton btnClear = new JButton("Clear");

	private String[] foods = { "Burger", "Pizza", "Pasta", "Lasagna", "Hotsilog", "Pancit", "Menudo", "Champorado" };
	private String[] addOns = { "Cheese", "Bacon", "Fries", "Drink", "Pepperoni", "Pineapples", "Ketchup",
			"Mayonnaise" };

	private String orderType = "";
	private String extras = "";
	private String extras2 = "";
	private final JSeparator separator_1 = new JSeparator();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodOrderingSystemFinals frame = new FoodOrderingSystemFinals();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FoodOrderingSystemFinals() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(lblHeader);
		separator.setBounds(-75, 42, 652, 2);
		contentPane.add(separator);

		lblHeader.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblHeader.setBounds(156, 11, 236, 28);

		lblName.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblName.setBounds(10, 55, 63, 14);

		lblMainFood.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblMainFood.setBounds(10, 84, 121, 14);

		lblOrderType.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblOrderType.setBounds(10, 138, 121, 14);

		lblExtras.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblExtras.setBounds(10, 192, 80, 14);

		lblAddOns.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblAddOns.setBounds(224, 50, 63, 14);

		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtName.setBounds(66, 53, 110, 20);

		cboMainFood.setBounds(10, 105, 146, 22);
		for (int i = 0; i < foods.length; i++) {
			cboMainFood.addItem(foods[i]);
		}

		for (int i = 0; i < addOns.length; i++) {
			addonModel.addElement(addOns[i]);
		}

		scrollPane.setBounds(186, 67, 134, 95);
		scrollPane.setViewportView(addonList);

		scrollPaneTextArea.setBounds(330, 51, 209, 199);
		txtAreaOrderSummary.setEditable(false);
		scrollPaneTextArea.setViewportView(txtAreaOrderSummary);

		buttonGroup.add(rdbtnDineIn);
		rdbtnDineIn.setBounds(6, 163, 80, 23);

		buttonGroup.add(rdbtnTakeout);
		rdbtnTakeout.setBounds(85, 163, 100, 23);

		chckbxExtraSauce.setBounds(75, 213, 110, 23);
		chckbxSpicy.setBounds(6, 213, 60, 23);

		btnClear.setBackground(Color.RED);
		btnClear.setForeground(Color.WHITE);
		btnClear.setBounds(186, 213, 134, 23);
		btnClear.addActionListener(e -> clearItems());

		btnPlaceOrder.setBackground(Color.GREEN);
		btnPlaceOrder.setForeground(Color.WHITE);
		btnPlaceOrder.setBounds(186, 173, 134, 23);
		btnPlaceOrder.addActionListener(e -> placeOrders());

		contentPane.add(cboMainFood);
		contentPane.add(lblName);
		contentPane.add(lblMainFood);
		contentPane.add(txtName);
		contentPane.add(scrollPane);
		contentPane.add(lblOrderType);
		contentPane.add(rdbtnDineIn);
		contentPane.add(rdbtnTakeout);
		contentPane.add(lblExtras);
		contentPane.add(chckbxExtraSauce);
		contentPane.add(chckbxSpicy);
		contentPane.add(scrollPaneTextArea);
		contentPane.add(btnPlaceOrder);
		contentPane.add(btnClear);
		contentPane.add(lblAddOns);

		separator_1.setBounds(-90, 261, 652, 2);
		contentPane.add(separator_1);
	}

	public void placeOrders() {
		String textName = txtName.getText().trim();
		String mainOrder = (String) cboMainFood.getSelectedItem();
		String addOn = addonList.getSelectedValue();

		extras = "";
		extras2 = "";

		if (chckbxExtraSauce.isSelected()) {
			extras = "Extra Sauce";
		}
		if (chckbxSpicy.isSelected()) {
			extras2 = "Spicy";
		}

		if (rdbtnDineIn.isSelected()) {
			orderType = "Dine-In";
		} else if (rdbtnTakeout.isSelected()) {
			orderType = "Take-Out";
		}

		if (textName.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Please enter your name!");
			return;
		}

		if (!rdbtnDineIn.isSelected() && !rdbtnTakeout.isSelected()) {
			JOptionPane.showMessageDialog(contentPane, "Please select an order type!");
			return;
		}

		orderSummary(textName, orderType, mainOrder, addOn, extras, extras2);
	}

	public void clearItems() {
		txtName.setText("");
		cboMainFood.setSelectedIndex(0);
		buttonGroup.clearSelection();
		chckbxExtraSauce.setSelected(false);
		chckbxSpicy.setSelected(false);
		addonList.clearSelection();
		txtAreaOrderSummary.setText("");
	}

	public void orderSummary(String customerName, String orderType, String mainFood, String addOns, String extras,
			String extras2) {
		txtAreaOrderSummary.setText(
				"=============================\n" + "     KENNETH RESTAURANT\n" + "=============================\n"
						+ "Customer Name: " + customerName + "\n" + "Order Type: " + orderType + "\n\n" + "ORDER:\n  ● "
						+ mainFood + "\n\n" + "ADD-ONS:\n  ● " + addOns + "\n\n" + "EXTRAS:\n  ● " + extras + "\n  ● "
						+ extras2 + "\n\n" + "=============================\n" + "Thank you for ordering!");
	}
}