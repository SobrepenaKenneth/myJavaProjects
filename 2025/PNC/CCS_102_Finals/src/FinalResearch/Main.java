package FinalResearch;

import java.util.Scanner;

//CSS102 Finals Project - Code v4.1

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
	}// End of main()

	public static boolean authenticateUser() {

		final String DEFAULT_USERNAME = "admin";
		final String DEFAULT_PASSWORD = "admin";

		System.out.println("=== INVENTORY PRODUCT MANAGEMENT SYSTEM v4.1 ===");
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
	}// End of authenticateUser() 

	// Main menu
	public static void showMainMenu() {
		System.out.println("\n=== MAIN MENU ===");
		System.out.println("[1] Product Management");
		System.out.println("[2] Sales Transaction");
		System.out.println("[3] View Transaction History");
		System.out.println("[4] Exit System");
		System.out.println("-------------------------");
		System.out.println("[5] Initialize Dummy Data(DEBUG)");
		System.out.print("Choose an option (1-5): ");

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
			System.out.println("Invalid option! Please choose 1-5.");
			break;
		}
	}// End of showMainMenu()

	public static boolean confirmExit() {
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
	}// End of confirmExit

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
	}// End of showProductManagementMenu()

	public static void CreateItem() {
		if (usedSlots >= 10) {
			System.out.println("Maximum Entries Reached.");
			return;
		}

		String[] inputTypes = { "ID", "Name", "Price", "Quantity" };
		String input = null;

		for (int l = 0; l <= 3; l++) {
			boolean validIn = false;
			while (!validIn) {
				System.out.print("Set Product " + inputTypes[l] + ": ");
				input = scanner.nextLine();

				// Check for empty input
				if (input.length() < 1) {
					System.out.println("Input cannot be empty. Please try again.");
					continue;
				}

				// ID Validation (Must be unique)
				if (l == 0) {
					boolean duplicateFound = false;
					for (int e = 0; e < usedSlots; e++) {
						if (input.equals(items[e][0])) {
							System.out.println("ID '" + input + "' already exists. Please use a unique ID.");
							duplicateFound = true;
							break;
						}
					}
					if (duplicateFound)
						continue;
				}

				// Price Validation (Must be a valid number, e.g., 10.50)
				if (l == 2) {
					try {
						double priceCheck = Double.parseDouble(input);
						if (priceCheck < 0) {
							System.out.println("Price cannot be negative.");
							continue;
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid Price. Please enter a number (e.g., 99.99).");
						continue;
					}
				}

				// Must be a WHOLE number, e.g., 10
				// <!> This prevents the crash in Sales Transaction <!>
				if (l == 3) {
					try {
						int qtyCheck = Integer.parseInt(input);
						if (qtyCheck < 0) {
							System.out.println("Quantity cannot be negative.");
							continue;
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid Quantity. Please enter a whole number (no decimals).");
						continue;
					}
				}
				// If all checks pass:
				items[usedSlots][l] = input;
				validIn = true;
			}
		}

		System.out.println("----------------------------------------------------------------");
		System.out.println("ITEM CREATION SUCCESSFUL");
		// Adjusted to match your spacing style slightly
		System.out.println("Product ID:       " + items[usedSlots][0]);
		System.out.println("Product Name:     " + items[usedSlots][1]);
		System.out.println("Product Price:    " + items[usedSlots][2]);
		System.out.println("Product Quantity: " + items[usedSlots][3]);
		System.out.println("----------------------------------------------------------------");
		usedSlots++;
	}// End of CreateItem()

	public static void ModifyItem() {
		if (usedSlots <= 0) {
			System.out.println("There are no products to modify!");
			return;
		}
		SeeList();
		System.out.print("Please Input the ID of the Product you'd like to modify: ");
		String userInput = scanner.nextLine();
		boolean noMatch = true;

		for (int i = 0; i < usedSlots; i++) { // Using usedSlots is safer/faster than items.length

			if (userInput.equals(items[i][0])) {
				noMatch = false;
				System.out.println("Product Match Found: Choose modification to perform.");
				System.out.println("1 - Change Product Name \n2 - Change Product Price \n3 - Change Available Stock");
				String selectActionIn = scanner.nextLine();

				switch (selectActionIn) {

				case "1":
					// Item Name Modification
					System.out.print("Set New Product Name: ");
					String newName = scanner.nextLine();
					items[i][1] = newName;
					System.out.println("Product '" + items[i][0] + "' Name successfully changed!");
					break;

				case "2":
					// Item Price Modification
					double newPrice = -1; // Start at -1 to force the loop to run
					while (newPrice < 0) {
						System.out.print("Set New Product Price: ");
						try {
							newPrice = Double.parseDouble(scanner.nextLine());
							if (newPrice < 0)
								System.out.println("Price cannot be negative.");
						} catch (NumberFormatException e) {
							System.out.println("Invalid input. Please enter a valid price.");
							newPrice = -1;
						}
					}
					items[i][2] = Double.toString(newPrice);
					System.out.println("Product '" + items[i][0] + "' Price successfully changed!");
					break;

				case "3":
					// Item Stock Modification
					int newAmount = -1; // Start at -1 to force the loop to run
					while (newAmount < 0) {
						System.out.print("Set New Available Stock Quantity: ");
						try {
							newAmount = Integer.parseInt(scanner.nextLine());
							if (newAmount < 0)
								System.out.println("Stock cannot be negative.");
						} catch (NumberFormatException e) {
							System.out.println("Invalid input. Please enter a whole number.");
							newAmount = -1;
						}
					}
					items[i][3] = Integer.toString(newAmount);
					System.out.println("Product '" + items[i][0] + "' Stock successfully changed!");
					break;

				default:
					System.out.println("Invalid Option");
				}
				break; // Stop looking after finding the product
			}
		}

		if (noMatch)
			System.out.println("No Matching Product Entry was found.");
	}// End of ModifyItem()

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

					// ADD THIS: Clean up the last slot to remove "ghost" data
					int lastIndex = usedSlots - 1;
					items[lastIndex][0] = null;
					items[lastIndex][1] = null;
					items[lastIndex][2] = null;
					items[lastIndex][3] = null;

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
	}// End of DeleteItem()

	public static void SeeList() {
		if (usedSlots <= 0) {
			System.out.println("\nItem List is Empty!\n");
			return;
		}

		System.out.println("");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("ITEM LIST");
		// Header Layout
		System.out.println("No.     Prod ID         Name                  Price           Quantity");
		System.out.println("-----------------------------------------------------------------------");

		for (int i = 0; i < usedSlots; i++) {
			// 1. Print Index (Allocated Space: 8)
			String indexStr = "[" + (i + 1) + "]";
			System.out.print(indexStr);
			for (int s = 0; s < (8 - indexStr.length()); s++)
				System.out.print(" ");

			// 2. Print ID (Allocated Space: 16)
			// Fixes the issue where "1" vs "P001" shifted the row
			String id = items[i][0];
			System.out.print(id);
			for (int s = 0; s < (16 - id.length()); s++)
				System.out.print(" ");

			// 3. Print Name (Allocated Space: 22)
			// Increased space to fit "Mechanical Keyboard" (19 chars)
			String name = items[i][1];
			System.out.print(name);
			// Prevent crash if name is super long by cutting it off visually if needed,
			// or just ensure loop doesn't break
			int namePadding = 22 - name.length();
			if (namePadding > 0) {
				for (int s = 0; s < namePadding; s++)
					System.out.print(" ");
			} else {
				System.out.print(" "); // Minimal spacing if name is too long
			}

			// 4. Print Price (Allocated Space: 16)
			String price = items[i][2];
			System.out.print(price);
			for (int s = 0; s < (16 - price.length()); s++)
				System.out.print(" ");

			// 5. Print Quantity (No padding needed for last column)
			System.out.println(items[i][3]);
		}
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("");
	}// End of SeeList() 

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
	}// End of TransactionHistory()

	public static void performSalesTransaction() {
		if (usedSlots <= 0) {
			System.out.println("\nProduct List is Empty!");
			return;
		}
		SeeList();
		System.out.print("Transaction - Input Product ID : ");
		String userInput = scanner.nextLine();
		boolean noMatch = true;

		for (int i = 0; i < usedSlots; i++) { // usedSlots is more accurate than items.length

			if (userInput.equals(items[i][0])) {
				noMatch = false;

				// Safely parse the current stock
				int productStock = 0;
				try {
					productStock = Integer.parseInt(items[i][3]);
				} catch (NumberFormatException e) {
					System.out.println("Error: Stock data is corrupted (not a whole number).");
					return;
				}

				System.out.println("Product Match Found");
				System.out.println("Available Stock: " + productStock);

				System.out.print("Purchase Quantity: ");
				int input = 0;
				boolean valid = false;
				while (!valid) {
					try {
						input = Integer.parseInt(scanner.nextLine());
						if (input <= 0) {
							System.out.println("Quantity must be greater than 0.");
						} else {
							valid = true;
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid number, try again: ");
					}
				}

				if (input <= productStock) {
					double price = Double.parseDouble(items[i][2]);
					double totalSales = price * input;

					productStock -= input;
					items[i][3] = Integer.toString(productStock); // Update Array

					System.out.println("Transaction Successful.");

					// Recording Transaction
					String transactionID = "TR" + transactionsMade;
					transactions[CurrentTransactionSlot][0] = transactionID;
					transactions[CurrentTransactionSlot][1] = items[i][0];
					transactions[CurrentTransactionSlot][2] = Integer.toString(input);
					transactions[CurrentTransactionSlot][3] = Double.toString(totalSales);

					// Receipt Display
					System.out.println("--------------------------------");
					System.out.println("Transaction ID: "  + transactions[CurrentTransactionSlot][0]);
					System.out.println("Product ID:     "  + transactions[CurrentTransactionSlot][1]);
					System.out.println("Amount Sold:    "  + transactions[CurrentTransactionSlot][2]);
					System.out.println("Total Sales:    P" + transactions[CurrentTransactionSlot][3]);
					System.out.println("--------------------------------");

					if (CurrentTransactionSlot < 9)
						CurrentTransactionSlot++;
					else
						CurrentTransactionSlot = 0; // Overwrite loop

					transactionsMade++;

				} else {
					System.out.println("Transaction Failed: Not Enough Stock!");
				}
				break;
			}
		}

		if (noMatch) {
			System.out.println("No Product Found Matching the Provided ID.");
		}
	}// End of performSalesTransaction()

	public static boolean confirmDummyData() {
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
	}// End of confirmDummyData()

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
		System.out.println("Debug Data added to relevant arrays.");
		// Should overwrite TR00
	}// End of inititalizeDummyData()

	// XXX: This is a Debug Method. It does not need to be added to the Algorithm
	public static boolean addProductToArray(String id, String name, String price, String quantity) {
		if (usedSlots >= items.length) {
			return false;
		}

		// Check for duplicate IDs before adding dummy data
		for (int i = 0; i < usedSlots; i++) {
			if (items[i][0].equals(id)) {
				return false; // ID exists, skip adding this dummy item
			}
		}

		items[usedSlots][0] = id;
		items[usedSlots][1] = name;
		items[usedSlots][2] = price;
		items[usedSlots][3] = quantity;
		usedSlots++;
		return true;
	}// End of addProductToArray()

	public static boolean addTransactionToArray(String id, String proID, String amount, String sales) {
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
}// End of addTransactionToArray()
