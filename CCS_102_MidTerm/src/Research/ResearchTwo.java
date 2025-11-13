package Research;

import java.util.Scanner;

public class ResearchTwo {
	// Global variables
	private static Scanner scanner = new Scanner(System.in);

	// Product inventory array [Product ID, Name, Price, Quantity]
	private static String[][] products = new String[100][4];
	private static int productCount = 0;

	// Sales transactions array [Transaction ID, Product ID, Quantity Sold, Total
	// Amount]
	private static String[][] transactions = new String[100][4];
	private static int transactionCount = 0;

	// Authentication
	private static final String DEFAULT_USERNAME = "admin";
	private static final String DEFAULT_PASSWORD = "admin";

	public static void main(String[] args) {
		initializeDummyData();
		if (authenticateUser()) {
			showMainMenu();
		}
	}

	// Initialize dummy data for testing
	private static void initializeDummyData() {
		// Add dummy products
		addProductToArray("P001", "Laptop", "999.99", "10");
		addProductToArray("P002", "Mouse", "25.50", "50");
		addProductToArray("P003", "Keyboard", "45.75", "30");
		addProductToArray("P004", "Monitor", "199.99", "15");
		addProductToArray("P005", "Headphones", "79.99", "25");

		// Add dummy transactions
		addTransactionToArray("T001", "P001", "2", "1999.98");
		addTransactionToArray("T002", "P002", "5", "127.50");
		addTransactionToArray("T003", "P003", "3", "137.25");
	}

	// User authentication
	private static boolean authenticateUser() {
		System.out.println("=== PRODUCT INVENTORY MANAGEMENT SYSTEM ===");
		System.out.println("Please login to continue:");

		int attempts = 3;
		while (attempts > 0) {
			System.out.print("Username: ");
			String username = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();

			if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)) {
				System.out.println("Login successful! Welcome, " + username + "!");
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
				performSalesTransaction();
				break;
			case "3":
				viewTransactionHistory();
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

	// Product Management Menu
	private static void showProductManagementMenu() {
		while (true) {
			System.out.println("\n=== PRODUCT MANAGEMENT ===");
			System.out.println("1. Add Product");
			System.out.println("2. View All Products");
			System.out.println("3. Update Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Back to Main Menu");
			System.out.print("Choose an option (1-5): ");

			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				addProduct();
				break;
			case "2":
				viewAllProducts();
				break;
			case "3":
				updateProduct();
				break;
			case "4":
				deleteProduct();
				break;
			case "5":
				return;
			default:
				System.out.println("Invalid option! Please choose 1-5.");
			}
		}
	}

	// Add Product
	private static void addProduct() {
		System.out.println("\n=== ADD NEW PRODUCT ===");

		String productId = getValidatedInput("Product ID: ", true);
		if (productId.isEmpty())
			return;

		// Check if product ID already exists
		if (findProductById(productId) != -1) {
			System.out.println("Error: Product ID already exists!");
			return;
		}

		String productName = getValidatedInput("Product Name: ", true);
		if (productName.isEmpty())
			return;

		String price = getValidatedInput("Product Price: ", false);
		if (price.isEmpty())
			return;

		// Validate price is a positive number
		if (!isValidPrice(price)) {
			System.out.println("Error: Price must be a positive number!");
			return;
		}

		String quantity = getValidatedInput("Product Quantity: ", false);
		if (quantity.isEmpty())
			return;

		// Validate quantity is a positive integer
		if (!isValidQuantity(quantity)) {
			System.out.println("Error: Quantity must be a positive whole number!");
			return;
		}

		if (addProductToArray(productId, productName, price, quantity)) {
			System.out.println("Product added successfully!");
		} else {
			System.out.println("Error: Cannot add product. Inventory might be full!");
		}
	}

	// Add product to array
	private static boolean addProductToArray(String id, String name, String price, String quantity) {
		if (productCount >= products.length) {
			return false;
		}

		products[productCount][0] = id;
		products[productCount][1] = name;
		products[productCount][2] = price;
		products[productCount][3] = quantity;
		productCount++;
		return true;
	}

	// View All Products
	private static void viewAllProducts() {
		System.out.println("\n=== ALL PRODUCTS ===");
		if (productCount == 0) {
			System.out.println("No products found in inventory.");
			return;
		}

		System.out.println("+----------+-----------------+-----------+----------+");
		System.out.println("| ID       | Name            | Price     | Quantity |");
		System.out.println("+----------+-----------------+-----------+----------+");

		for (int i = 0; i < productCount; i++) {
			System.out.printf("| %-8s | %-15s | $%-8s | %-8s |\n", products[i][0], products[i][1], products[i][2],
					products[i][3]);
		}
		System.out.println("+----------+-----------------+-----------+----------+");
		System.out.println("Total products: " + productCount);
	}

	// Update Product
	private static void updateProduct() {
		System.out.println("\n=== UPDATE PRODUCT ===");
		if (productCount == 0) {
			System.out.println("No products available to update.");
			return;
		}

		String productId = getValidatedInput("Enter Product ID to update: ", true);
		if (productId.isEmpty())
			return;

		int index = findProductById(productId);
		if (index == -1) {
			System.out.println("Error: Product not found!");
			return;
		}

		System.out.println("Current product details:");
		System.out.println("ID: " + products[index][0]);
		System.out.println("Name: " + products[index][1]);
		System.out.println("Price: " + products[index][2]);
		System.out.println("Quantity: " + products[index][3]);

		System.out.println("\nEnter new details (press Enter to keep current value):");

		String newName = getValidatedInput("New Product Name: ", false);
		if (!newName.isEmpty()) {
			products[index][1] = newName;
		}

		String newPrice = getValidatedInput("New Product Price: ", false);
		if (!newPrice.isEmpty()) {
			if (!isValidPrice(newPrice)) {
				System.out.println("Error: Price must be a positive number!");
				return;
			}
			products[index][2] = newPrice;
		}

		String newQuantity = getValidatedInput("New Product Quantity: ", false);
		if (!newQuantity.isEmpty()) {
			if (!isValidQuantity(newQuantity)) {
				System.out.println("Error: Quantity must be a positive whole number!");
				return;
			}
			products[index][3] = newQuantity;
		}

		System.out.println("Product updated successfully!");
	}

	// Delete Product
	private static void deleteProduct() {
		System.out.println("\n=== DELETE PRODUCT ===");
		if (productCount == 0) {
			System.out.println("No products available to delete.");
			return;
		}

		String productId = getValidatedInput("Enter Product ID to delete: ", true);
		if (productId.isEmpty())
			return;

		int index = findProductById(productId);
		if (index == -1) {
			System.out.println("Error: Product not found!");
			return;
		}

		System.out.println("Product to delete:");
		System.out.println("ID: " + products[index][0]);
		System.out.println("Name: " + products[index][1]);
		System.out.println("Price: " + products[index][2]);
		System.out.println("Quantity: " + products[index][3]);

		System.out.print("Are you sure you want to delete this product? (yes/no): ");
		String confirmation = scanner.nextLine();

		if (confirmation.equalsIgnoreCase("yes")) {
			// Shift all products after the deleted one
			for (int i = index; i < productCount - 1; i++) {
				products[i][0] = products[i + 1][0];
				products[i][1] = products[i + 1][1];
				products[i][2] = products[i + 1][2];
				products[i][3] = products[i + 1][3];
			}
			productCount--;
			System.out.println("Product deleted successfully!");
		} else {
			System.out.println("Deletion cancelled.");
		}
	}

	// Perform Sales Transaction
	private static void performSalesTransaction() {
		System.out.println("\n=== PERFORM SALES TRANSACTION ===");
		if (productCount == 0) {
			System.out.println("No products available for sale.");
			return;
		}

		viewAllProducts();

		String productId = getValidatedInput("Enter Product ID to sell: ", true);
		if (productId.isEmpty())
			return;

		int index = findProductById(productId);
		if (index == -1) {
			System.out.println("Error: Product not found!");
			return;
		}

		String quantityToSell = getValidatedInput("Enter quantity to sell: ", false);
		if (quantityToSell.isEmpty())
			return;

		if (!isValidQuantity(quantityToSell)) {
			System.out.println("Error: Quantity must be a positive whole number!");
			return;
		}

		int currentQuantity = Integer.parseInt(products[index][3]);
		int sellQuantity = Integer.parseInt(quantityToSell);

		if (sellQuantity > currentQuantity) {
			System.out.println("Error: Insufficient stock! Available: " + currentQuantity);
			return;
		}

		// Calculate total amount
		double price = Double.parseDouble(products[index][2]);
		double totalAmount = price * sellQuantity;

		// Update product quantity
		products[index][3] = String.valueOf(currentQuantity - sellQuantity);

		// Generate transaction ID
		String transactionId = "T" + String.format("%03d", transactionCount + 1);

		// Record transaction
		if (addTransactionToArray(transactionId, productId, quantityToSell, String.format("%.2f", totalAmount))) {
			System.out.println("Sales transaction completed successfully!");
			System.out.println("Transaction ID: " + transactionId);
			System.out.println("Product: " + products[index][1]);
			System.out.println("Quantity Sold: " + sellQuantity);
			System.out.println("Total Amount: $" + String.format("%.2f", totalAmount));
		} else {
			System.out.println("Error: Failed to record transaction!");
		}
	}

	// Add transaction to array
	private static boolean addTransactionToArray(String transId, String prodId, String quantity, String total) {
		if (transactionCount >= transactions.length) {
			return false;
		}

		transactions[transactionCount][0] = transId;
		transactions[transactionCount][1] = prodId;
		transactions[transactionCount][2] = quantity;
		transactions[transactionCount][3] = total;
		transactionCount++;
		return true;
	}

	// View Transaction History
	private static void viewTransactionHistory() {
		System.out.println("\n=== TRANSACTION HISTORY ===");
		if (transactionCount == 0) {
			System.out.println("No transactions found.");
			return;
		}

		System.out.println("+-----------+----------+-------------+--------------+");
		System.out.println("| Trans ID  | Prod ID  | Quantity    | Total Amount |");
		System.out.println("+-----------+----------+-------------+--------------+");

		double grandTotal = 0;
		for (int i = 0; i < transactionCount; i++) {
			System.out.printf("| %-9s | %-8s | %-11s | $%-11s |\n", transactions[i][0], transactions[i][1],
					transactions[i][2], transactions[i][3]);
			grandTotal += Double.parseDouble(transactions[i][3]);
		}
		System.out.println("+-----------+----------+-------------+--------------+");
		System.out.printf("Total Sales: $%.2f\n", grandTotal);
		System.out.println("Total Transactions: " + transactionCount);
	}

	// Helper methods
	private static String getValidatedInput(String prompt, boolean required) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();

			if (required && input.isEmpty()) {
				System.out.println("Error: This field is required!");
				continue;
			}

			if (!required && input.isEmpty()) {
				return input;
			}

			return input;
		}
	}

	private static int findProductById(String productId) {
		for (int i = 0; i < productCount; i++) {
			if (products[i][0].equals(productId)) {
				return i;
			}
		}
		return -1;
	}

	private static boolean isValidPrice(String price) {
		try {
			double value = Double.parseDouble(price);
			return value > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean isValidQuantity(String quantity) {
		try {
			int value = Integer.parseInt(quantity);
			return value > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean confirmExit() {
		System.out.print("Are you sure you want to exit? (yes/no): ");
		String confirmation = scanner.nextLine();
		return confirmation.equalsIgnoreCase("yes");
	}
}