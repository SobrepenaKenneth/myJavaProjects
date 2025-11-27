package FinalResearch;

import java.util.Scanner;

//CSS102 Finals Project - Code v3.6

public class Main {
	static Scanner scanner = new Scanner(System.in);

	// MENU CONTROLS
	static boolean isActive = true;
	static int activeMenu = 0; // 0 - MAIN, 1 - PRODUCT MANAGEMENT

	// DATA MANAGEMENT VARIABLES
	static String[][] items = new String[10][4]; // 0 - ID, 1 - Name, 2 - Price, 3 - Quantity
	static String[][] transactions = new String[10][4]; // 0 - TRID, 1 - ID, 2 - Amount Sold, 3 - Total Sale

	static int usedSlots = 0;
	static int transactionsMade = 0;
	static int CurrentTransactionSlot = 0;
	static int overwriteQueue = 0;

	public static void main(String[] args) {
		if (authenticateUser()) {
			while (isActive) {
				if (activeMenu == 0)
					showMainMenu();
				if (activeMenu == 1)
					showProductManagementMenu();
			}
		}
	}

	public static boolean authenticateUser() {

		final String DEFAULT_USERNAME = "admin";
		final String DEFAULT_PASSWORD = "admin";

		System.out.println("=== INVENTORY PRODUCT MANAGEMENT SYSTEM v3.6 ===");
		System.out.println("Please log in to continue:");

		int attempts = 3;
		while (attempts > 0) {
			System.out.print("Username: ");
			String username = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();

			if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)) {
				System.out.println("Login successful! Welcome, " + username + "!\n");
				return true;
			} else {
				attempts--;
				System.out.println("Invalid credentials! Attempts remaining: " + attempts);
				if (attempts == 0) {
					System.out.println("Too many failed attempts. System exiting.");
					return false;
				}
			}
		}
		return false;
	}

	// Main menu
	private static void showMainMenu() {
		System.out.println("\n=== MAIN MENU ===");
		System.out.println("[1] Product Management");
		System.out.println("[2] Sales Transaction");
		System.out.println("[3] View Transaction History");
		System.out.println("[4] Exit System");
		System.out.println("-------------------------");
		System.out.println("[5] Initialize Dummy Data(DEBUG)");
		System.out.print("Choose an option (1-4): ");

		String choice = scanner.nextLine();

		switch (choice) {
		case "1":
			activeMenu = 1;
			return;
		case "2":
			performSalesTransaction();
			break;
		case "3":
			TransactionHistory();
			break;
		case "4":
			if (confirmExit()) {
				System.out.println("Thank you for using the system. Goodbye!");
				isActive = false;
				return;
			}
			break;
		case "5":
			if (confirmDummyData()) {
				inititalizeDummyData();
			}
			break;
		default:
			System.out.println("Invalid option! Please choose 1-4.");
			break;
		}

	}

	private static boolean confirmExit() {
		System.out.print("Are you sure you want to exit? (yes/no): ");
		String confirmation = scanner.nextLine().toLowerCase();

		switch (confirmation) {
		case "yes":
			return true;
		case "no":
			return false;
		default:
			System.out.println("Invalid input. Returning to menu...");
			return false;
		}
	}

	// XXX: PRODUCT MANAGEMENT MENU
	public static void showProductManagementMenu() {
		System.out.println();
		System.out.println("== Product Management ==");
		System.out.println(
				"[1] - Add Product \n[2] - View All Product \n[3] - Update Product \n[4] - Delete Product \n[5] - Back to Main Menu");

		System.out.print("Enter Command: ");
		String input = scanner.nextLine();

		switch (input) {

		case "1":
			CreateItem();
			break;

		case "2":
			SeeList();
			showProductManagementMenu();
			break;
		case "3":
			ModifyItem();
			break;

		case "4":
			DeleteItem();
			break;

		case "5":
			activeMenu = 0;
			return;
		default:
			System.out.println("Invalid option! Please choose 1-5.");
		}

	}

	public static void CreateItem() {
		if (usedSlots >= 10) {
			System.out.println("Maximum Entries Reached.");
			return;
		}

		String[] inputTypes = { "ID", "Name", "Price", "Quantity" }; // Local array, dictates what is displayed when
																		// asking for input
		String input = null;
		boolean validIn = false;
		for (int l = 0; l <= 3; l++) { // 4 iterations: 0, 1, 2, 3
			validIn = false;
			while (!validIn) { // Labels the loop.

				System.out.print("Set Product " + inputTypes[l] + ": ");

				input = scanner.nextLine();

				if (input.length() < 1) {
					System.out.println("Invalid input for Product " + inputTypes[l] + ", please try again.");
					continue;
				}
				if (l == 0) { // Only checks for ID
					for (int e = 0; e < usedSlots; e++) {
						if (input.equals(items[e][0])) {
							System.out.println(
									"There is already an Entry with ID '" + input + "', unable to complete action.");
							continue;
						}
					}
				}
				// Only checks for Price and Quantity
				if (l >= 2) {
					double valueContainer;
					try {
						valueContainer = Double.parseDouble(input);

					} catch (NumberFormatException e) {
						System.out.println("Invalid input for Product " + inputTypes[l] + ", please try again.");
						continue;
					}

					if (valueContainer < 0) {
						System.out.println("Invalid input for Product " + inputTypes[l] + ", please try again.");
						continue;
					}

				}
				items[usedSlots][l] = input;
				validIn = true;

			}
		}

		System.out.println("----------------------------------------------------------------");
		System.out.println("");
		System.out.println("ITEM CREATION SUCCESSFUL");
		System.out.println("Product ID: " + items[usedSlots][0] + "\nProduct Name: " + items[usedSlots][1]
				+ "\nProduct Price: " + items[usedSlots][2] + "\nProduct Quantity: " + items[usedSlots][3]);
		System.out.println("");
		System.out.println("----------------------------------------------------------------");
		usedSlots++;

		return;
	}

	public static void ModifyItem() {
		if (usedSlots <= 0) {
			System.out.println("There are no products to modify!");
			return;
		}
		SeeList();
		System.out.print("Please Input the ID of the Product you'd like to modify: ");
		boolean noMatch = true;
		String userInput = scanner.nextLine();

		for (int i = 0; i < items.length; i++) {

			if (userInput.equals(items[i][0])) {
				noMatch = false;
				System.out.println("Product Match Found: Choose modification to perform.");
				System.out.println("1 - Change Product Name \n2 - Change Product Price \n3 - Change Available Stock");
				String selectActionIn = scanner.nextLine();

				switch (selectActionIn) {

				case "1":
					// Item Name Modification
					System.out.println("Set New Product Name: ");
					String newName = scanner.nextLine();
					items[i][1] = newName;
					System.out.println("Product '" + items[i][0] + "' Name successfully changed!");

					break;
				case "2":
					// Item Price Modification
					double newPrice = 0;
					while (newPrice < 0) {
						System.out.println("Set New Product Price: ");
						try {
							newPrice = Double.parseDouble(scanner.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Input is not a valid value for this column.");
							newPrice = 0;
						}

					}

					items[i][2] = Double.toString(newPrice);
					System.out.println("Product '" + items[i][0] + "' Price successfully changed!");
					break;

				case "3":
					// Item Stock Modification
					int newAmount = 0;
					while (newAmount <= 0) {
						System.out.println("Set New Available Stock Quantity: ");
						try {
							newAmount = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Input is not a valid value for this column.");
							newAmount = 0;
						}

					}

					items[i][3] = Integer.toString(newAmount);
					System.out.println("Product '" + items[i][0] + "' Stock successfully changed!");
					break;

				default:
					// No Match
					System.out.println("Invalid Input");
				} // switch closing
				break; // Closes for loop early if match is found
			}
		} // For loop closing
			// Only reached if loop
		if (noMatch)
			System.out.println("No Matching Product Entry was found.");

		return;
	}

	public static void DeleteItem() {
		if (usedSlots <= 0) {
			System.out.println("There are no products to delete!");
			return;
		}
		SeeList();
		System.out.println("Please Input the ID of the Product you'd like to delete: ");
		boolean noMatch = true;
		String userInput = scanner.nextLine();

		for (int i = 0; i < usedSlots; i++) {
			if (userInput.equals(items[i][0])) {
				noMatch = false;
				System.out.println("Are you sure you want to delete Product " + items[i][0] + "?");
				System.out.println("[1] - YES");
				System.out.println("[2] - NO");
				userInput = scanner.nextLine();
				if (userInput.equals("1") || userInput.toLowerCase().equals("yes")) {
					for (int d = i; d < usedSlots - 1; d++) {
						items[d][0] = items[d + 1][0];
						items[d][1] = items[d + 1][1];
						items[d][2] = items[d + 1][2];
						items[d][3] = items[d + 1][3];
					}
					usedSlots -= 1;

					System.out.println("Product Entry deleted succesfully.");
				} else {

					System.out.println("Product Entry deletion cancelled.");

				}
				break; // Closes the loop if match is found early.
			} // if closing
		} // For loop closing
		if (noMatch)
			System.out.println("No Matching Product Entry was found.");

		return;
	}

	public static void SeeList() {
		if (usedSlots <= 0) {
			System.out.println();
			System.out.println("Item List is Empty!");
			System.out.println();
			return;
		}
		System.out.println("");
		System.out.println("----------------------------------------------------------------");
		System.out.println("ITEM LIST");
		System.out.println("+------------------+-------------+---------------+---------------+");
		System.out.println("|    Product ID    |    Name     |     Price     |    Quantity   |");
		System.out.println("+------------------+-------------+---------------+---------------+");
		for (int i = 0; i < items.length; i++) {

			String index = items[i][0];
			if (index != null) {
				System.out.print("[" + (i + 1) + "]");
				if (i != 9)
					System.out.print("      ");
				else
					System.out.print("     ");
				for (int column = 0; column < 4; column++) {
					String item = items[i][column];
					int spaceVariance[] = { 0, 12, 18, 18 };
					int spaceLength = spaceVariance[column] - item.length();
					if (column != 0) {

						for (int space = 1; space <= spaceLength; space++) { // e2 start
							System.out.print(" ");
						} // e1 loop end
					}

					System.out.print(items[i][column]);

				} // column loop end
				System.out.println();
			}
		}
		System.out.println("----------------------------------------------------------------");
		System.out.println("");

		return;
	}

	public static void TransactionHistory() {
		if (transactionsMade <= 0) {

			System.out.println("No Recent Transactions have been made!");

		} else {
			System.out.println("");
			System.out.println("+------------------+-------------+---------------+---------------+");
			System.out.println("== Transaction History ==");
			System.out.println("+------------------+-------------+---------------+---------------+");
			for (int i = 0; i < transactions.length; i++) {
				String index = transactions[i][0];
				if (index != null) {

					System.out.print("[" + (i + 1) + "]");
					System.out.print("      ");

					System.out.println("[" + transactions[i][0] + "] ");
					System.out.println("\tProduct ID: " + transactions[i][1]);
					System.out.println("\tAmount Sold: " + transactions[i][2]);
					System.out.println("\tTotal Sales: P" + transactions[i][3]);
					System.out.println();
				}
			}
			System.out.println("----------------------------------------------------------------");
			System.out.println("");

		}
	}

	public static void performSalesTransaction() {
		if (usedSlots <= 0) {
			// DISPLAY
			System.out.println();
			System.out.println("Product List is Empty!");
			return;
		}
		SeeList(); // This executes the List Method which displays the Product List
		System.out.println("Transaction - Input Product ID : ");
		boolean noMatch = true;
		String userInput = scanner.nextLine();

		for (int i = 0; i < items.length; i++) {

			if (userInput.equals(items[i][0])) {
				noMatch = false;
				// DISPLAY
				int productStock = Integer.parseInt(items[i][3]);
				System.out.println("Product Match Found");
				System.out.println("Available Stock: " + productStock);

				System.out.print("Purchase Quantity: ");
				int input = 0;
				boolean valid = false;
				while (!valid) {
					try {
						input = Integer.parseInt(scanner.nextLine());
						valid = true;
					} catch (NumberFormatException e) {
						System.out.println("Invalid number, try again: ");
					}
				}

				if (input <= productStock) { // input amount is less than or equal to available Product - Valid
					double totalSales = Double.parseDouble(items[i][2]) * input; // Record total Sales amount.
					productStock -= input; // Deduct amount purchased from stock.
					items[i][3] = Integer.toString(productStock); // Update Main Array with new Quantity value for
																	// Product.
					// DISPLAY
					System.out.println("Transaction Successful.");

					String transactionID = "TR" + transactionsMade; // Assigned ID for this transaction

					transactions[CurrentTransactionSlot][0] = transactionID; // Transaction ID
					transactions[CurrentTransactionSlot][1] = items[i][0]; // Product ID
					transactions[CurrentTransactionSlot][2] = Integer.toString(input); // Product Quantity Sold
					transactions[CurrentTransactionSlot][3] = Double.toString(totalSales); // Product Total Sales

					// DISPLAY
					System.out.println("[" + transactions[CurrentTransactionSlot][0] + "]");
					System.out.println("• Product ID " + "[" + transactions[CurrentTransactionSlot][1] + "]");
					System.out.println("• Amount Sold: " + transactions[CurrentTransactionSlot][2]);
					System.out.println("• Total Sales: P" + transactions[CurrentTransactionSlot][3]);

					if (CurrentTransactionSlot <= 9)
						CurrentTransactionSlot++;
					else
						CurrentTransactionSlot = 0;

					transactionsMade++; // Total amount of transactions made

				} else { // Invalid Transaction

					System.out.println("Transaction Failed: Not Enough Stock!");

				}
				break; // break loop early if match is found.
			} // Inner IF closing
		} // Loop closing

		if (noMatch) {

			System.out.println("No Product Found Matching the Provided ID. Please Double Check.");

		}
		return;
	}

	private static boolean confirmDummyData() {
		System.out.print("Are you sure you want to create dummy data?\n"
				+ " This will attempt to fill the database with test entries.\n"
				+ "  <!> Won't overwrite Product Entries\n" + "  <!> Will overwrite Transaction History\n"
				+ " (yes / no): ");
		String confirmation = scanner.nextLine().toLowerCase();

		switch (confirmation) {
		case "yes":
			return true;
		case "no":
			return false;
		default:
			System.out.println("Invalid input. Returning to menu...");
			return false;
		}
	}

	public static void inititalizeDummyData() {
		// Product Dummies
		addProductToArray("P001", "Laptop", "999.99", "10");
		addProductToArray("P002", "Mouse", "999.9", "50");
		addProductToArray("P003", "Keyboard", "45.75", "30");
		addProductToArray("P004", "Pours", "199.99", "15");
		addProductToArray("P005", "Headphones", "79.99", "25");
		addProductToArray("P006", "Keypad", "79.99", "25");
		addProductToArray("P007", "Speakers", "79.99", "25");
		addProductToArray("P008", "Mouse", "79.99", "25");
		addProductToArray("P009", "RGB Monitor", "79.99", "25");
		addProductToArray("P010", "Potato", "79.99", "25");

		// Transaction Dummies
		addTransactionToArray("TR00", "P001", "10", "9990.0");
		addTransactionToArray("TR01", "P001", "10", "9990.0");
		addTransactionToArray("TR02", "P001", "10", "9990.0");
		addTransactionToArray("TR03", "P001", "10", "9990.0");
		addTransactionToArray("TR04", "P001", "10", "9990.0");
		addTransactionToArray("TR05", "P001", "10", "9990.0");
		addTransactionToArray("TR06", "P001", "10", "9990.0");
		addTransactionToArray("TR07", "P001", "10", "9990.0");
		addTransactionToArray("TR08", "P001", "10", "9990.0");
		addTransactionToArray("TR09", "P001", "10", "9990.0");
		addTransactionToArray("TR10", "P001", "10", "9990.0");
		System.out.println("Debug Data added to relevant arrays.");
		// Should overwrite TR00
	}

	// XXX: This is a Debug Method. It does not need to be added to the Algorithm
	private static boolean addProductToArray(String id, String name, String price, String quantity) {
		if (usedSlots >= items.length) {
			return false;
		}

		items[usedSlots][0] = id;
		items[usedSlots][1] = name;
		items[usedSlots][2] = price;
		items[usedSlots][3] = quantity;
		usedSlots++;
		return true;
	}

	private static boolean addTransactionToArray(String id, String proID, String amount, String sales) {
		if (CurrentTransactionSlot >= transactions.length) {
			return false;
		}

		transactions[CurrentTransactionSlot][0] = id;
		transactions[CurrentTransactionSlot][1] = proID;
		transactions[CurrentTransactionSlot][2] = amount;
		transactions[CurrentTransactionSlot][3] = sales;
		if (CurrentTransactionSlot < 9)
			CurrentTransactionSlot++;
		else
			CurrentTransactionSlot = 0;
		transactionsMade++;
		return true;
	}

}
