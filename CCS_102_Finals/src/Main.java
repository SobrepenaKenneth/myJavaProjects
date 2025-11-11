import java.util.Scanner;

//CSS102 Finals Project - Code v2.0

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static String[][] items = new String[10][4]; // 0 - ID, 1 - Name, 2 - Price, 3 - Quantity

	static int usedSlots = 0;

	static boolean seeList = true;

	static int totalCosts = 0;

	public static void main(String[] args) {
		inititalizeDummyData();
		if (authenticateUser()) {
			showMainMenu();
		}
	}

	// Main menu
	private static void showMainMenu() {
		while (true) {
			System.out.println("\n=== MAIN MENU ===");
			System.out.println("1. Product Management");
			System.out.println("2. Sales Transaction");
			System.out.println("3. View Transaction History");
			System.out.println("4. Exit System");
			System.out.print("Choose an option (1-4): ");

			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				showProductManagementMenu();
				break;
			case "2":
				// performSalesTransaction();
				break;
			case "3":
				// viewTransactionHistory();
				break;
			case "4":
				if (confirmExit()) {
					System.out.println("Thank you for using the system. Goodbye!");
					return;
				}
				break;
			default:
				System.out.println("Invalid option! Please choose 1-4.");
			}
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

	public static void inititalizeDummyData() {
		addProductToArray("P001", "Laptop", "999.99", "10");
		addProductToArray("P002", "Mouse", "25.50", "50");
		addProductToArray("P003", "Keyboard", "45.75", "30");
		addProductToArray("P004", "Monitor", "199.99", "15");
		addProductToArray("P005", "Headphones", "79.99", "25");
	}

	// Add product to array
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

	public static boolean authenticateUser() {

		final String DEFAULT_USERNAME = "admin";
		final String DEFAULT_PASSWORD = "admin";

		System.out.println("=== INVENTORY PRODUCT MANAGEMENT SYSTEM ===");
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

	public static void showProductManagementMenu() {
		System.out.println("usedSlots: " + usedSlots);
		System.out.flush();
		System.out.println("----------------------------------------------------------------");
		System.out.println("");
		System.out.println("\n=== PRODUCT MANAGEMENT ===");
		System.out.println(
				"[1] - Add Product \n[2] - View All Product \n[3] - Update Product \n[4] - Delete Product \n[5] - Back to MainMenu");

		System.out.print("Enter Command: ");
		String input = scanner.nextLine();

		switch (input) {

		case "1":
			CreateItem();
			break;

		case "2":
			SeeList();
			break;
		case "3":
			// Toggles List View
			ModifyItem();

			break;

		case "4":
			DeleteItem();
			break;

		case "5":
			return;
		default:
			System.out.println("Invalid option! Please choose 1-5.");
		}
	}

	public static void CreateItem() {
		if (usedSlots >= 10) {
			System.out.print("Maximum Entries Reached.");
			main(null);
		}

		String[] inputs = { "ID", "Name", "Price", "Quantity" };
		String input = null;
		boolean validIn = false;
		for (int l = 0; l <= 3; l++) {
			validIn = false;
			validation: while (!validIn) { // Labels the loop.

				System.out.print("Set Product " + inputs[l] + ": ");

				input = scanner.nextLine();

				if (input.length() < 1) {
					System.out.println("Invalid input for Product " + inputs[l] + ", please try again.");
					continue validation;
				}
				if (l == 0) { // Only checks for ID
					for (int e = 0; e < usedSlots; e++) {
						if (input.equals(items[e][0])) {
							System.out.println(
									"There is already an Entry with ID '" + input + "', unable to complete action.");
							continue validation; // Use the label to specify the while loop.
						}
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

		showProductManagementMenu();
	}

	public static void ModifyItem() {
		System.out.println("Please Input the ID of the Product you'd like to modify: ");
		boolean noMatch = true;
		String userInput = scanner.nextLine();

		for (int i = 0; i < items.length; i++) {

			if (userInput.equals(items[i][0])) {
				noMatch = false;
				System.out.println("Product Match Found: What ");

				System.out.println("Choose modification to perform.");
				System.out.println("1 - Change Product Name \n2 - Change Product Price \n3 - Change Product Quantity");
				String selectActionIn = scanner.nextLine();

				switch (selectActionIn) {

				case "1":
					System.out.println("Set New Product Name: ");
					String newName = scanner.nextLine();
					items[i][1] = newName;
					System.out.println("Product '" + items[i][0] + "' Name successfully changed!");

					break;
				case "2":
					System.out.println("Set New Product Price: ");
					String newPrice = scanner.nextLine();
					items[i][2] = newPrice;
					System.out.println("Product '" + items[i][0] + "' Price successfully changed!");

					break;

				case "3":
					System.out.println("Set New Product Quantity: ");
					String newQuantity = scanner.nextLine();
					items[i][3] = newQuantity;
					System.out.println("Product '" + items[i][0] + "' Price successfully changed!");

					break;

				default:
					System.out.println("Invalid Input");
				} // switch closing
				break; // Closes for loop
			}
		} // For loop closing
			// Only reached if loop
		if (noMatch)
			System.out.println("No Matching Product Entry was found.");

		showProductManagementMenu();
	}

	public static void DeleteItem() {

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
					for (int d = i; d < usedSlots; d++) {
						if (!(d >= 10)) {
							items[d][0] = items[d + 1][0];
							items[d][1] = items[d + 1][1];
							items[d][2] = items[d + 1][2];
							items[d][3] = items[d + 1][3];
						} else {
							items[d][0] = null;
							items[d][1] = null;
							items[d][2] = null;
							items[d][3] = null;
						}
					}
					usedSlots -= 1;
					System.out.println("Product Entry deleted succesfully.");
				}
				break; // Closes the loop if match is found early.
			} // if closing
		} // For loop closing
		if (noMatch)
			System.out.println("No Matching Product Entry was found.");

		showProductManagementMenu();
	}

	public static void SeeList() {
		if (usedSlots <= 0) {

			System.out.println("Item List is Empty!");
			System.out.println("----------------------------------------------------------------");

		} else {
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
					System.out.print("      ");
					for (int column = 0; column < 4; column++) {

						String item = items[i][column];
						System.out.print(items[i][column]);

						for (int space = 1; space <= 16 - item.length(); space++) { // e2 start
							System.out.print(" ");
						} // e2 loop end

					} // column loop end
					System.out.println();
				}
			}
			System.out.println("----------------------------------------------------------------");
			System.out.println("");

		}

		showProductManagementMenu();
	}

}