package activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
		// User Input Variables
		String itemName = "<->";

		// Local Variables
		double price = 0;
		double discountedPrice = 0;
		double quantity = 0;
		double total = 0;
		double discount = 0;

		// ===== NAME =====
		System.out.println("--------------------------------------------------");
		System.out.println("Item: " + itemName + " | Price: " + price + " | Quantity: " + quantity + " | Discount(%): "
				+ discount + " | Total: " + total);
		System.out.println("--------------------------------------------------");

		// Step 2/3
		System.out.print("Insert Item Name: ");
		itemName = scanner.readLine();

		// Step 4
		System.out.println("--------------------------------------------------");
		System.out.println("Item: " + itemName + " | Price: " + price + " | Quantity: " + quantity + " | Discount(%): "
				+ discount + " | Total: " + total);
		System.out.println("--------------------------------------------------");

		// ===== PRICE =====
		// Step 5
		System.out.print("Insert Item Price: ");

		// Step 6
		try {
			price = Integer.parseInt(scanner.readLine());
		} catch (NumberFormatException e) {
			System.out.println("--------------------------------------------------");
			System.out.println("Invalid Value: Defaulting to 1");
			price = 1;
		}

		discountedPrice = price;

		// Step 7
		System.out.println("--------------------------------------------------");
		System.out.println("Item: " + itemName + " | Price: " + price + " | Quantity: " + quantity + " | Discount(%): "
				+ discount + " | Total: " + total);
		System.out.println("--------------------------------------------------");

		// ===== QUANTITY =====
		// Step 8
		System.out.print("Insert Quantity Bought: ");

		// Step 9
		try {
			quantity = Integer.parseInt(scanner.readLine());
		} catch (NumberFormatException e) {
			System.out.println("--------------------------------------------------");
			System.out.println("Invalid Value: Defaulting to 1");

			quantity = 1;
		}

		// Step 10
		total = quantity * price;

		// Step 11
		System.out.println("--------------------------------------------------");
		System.out.println("RECEIPT UPDATED: Item: " + itemName + " | Price: " + price + " | Quantity: " + quantity
				+ " | Discount(%): " + discount + " | Total: " + total);
		System.out.println("--------------------------------------------------");

		// ===== Discount =====
		// Step 12
		System.out.print("Insert Discount(%) (Leave empty if none): ");

		// Step 13
		try {
			discount = Integer.parseInt(scanner.readLine());
		} catch (NumberFormatException e) {
			discount = 0;
		}

		// Step 14
		discount /= 100;

		// Step 15
		discountedPrice *= discount;

		// Step 16
		price -= discountedPrice;

		// Step 17
		total = price * quantity;

		// FINAL RECEIPT
		// Step 18
		System.out.println("--------------------------------------------------");
		System.out.println("FINAL RECEIPT");
		System.out.println("--------------------------------------------------");
		System.out.println("Item: " + itemName + " | Price: " + price + " | Quantity: " + quantity + " | Discount(%): "
				+ discount + " | Total: " + total);
		System.out.println("--------------------------------------------------");
	}

}
