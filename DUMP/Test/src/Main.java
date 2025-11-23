import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

	// main() variables
	static String name;
	static int age;

	// Store() variables
	final static int raffleRequirement = 10000;
	static String[] shopItems = { "Lechon", "Pancit", "Lumpia" };
	static String input;
	static int[] shopPrices = { 5000, 100, 50 };
	static int totalSpent = 0;
	static int userInput;

	// Raffle() variables
	static final int winningTicket = 8;

	public static void main(String[] args) throws IOException {
		//Store(); just looks cleaner in code.
		System.out.println("-------------------------------------------");
		System.out.println("❤️ - Maligayang pagdalo sa - ❤️");
		System.out.println("🏆 - Aninag Festival 2025 - 🏆");
		System.out.println("-------------------------------------------");
		
		try {
			System.out.print("Enter your name: ");
			name = scanner.readLine();
			System.out.println("-------------------------------------------");

		} catch (NumberFormatException e) {
			
			System.out.println("Invalid Input!");
			main(args);
			
		}	
		
		System.out.println("Maligayang Pagdalo " + name + "!");
		
		System.out.println("-------------------------------------------");
		System.out.println("Happy to have you join the Fiesta!");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - Saan mo gusto magtungo - <<<<<");
		System.out.println("...... [1] - Food Stall - ......");
		System.out.println("...... [2] -   Raffle   - ......");
		System.out.println("-------------------------------------------");
		FiestaMap();

		userInput = (userInput == 1) ? FoodStall () : Raffle();
		System.out.println("-------------------------------------------");
		
		//scanner.readLine();
	}

	public static int FiestaMap() throws IOException {
		System.out.print("Pick a number in the index: ");
		userInput = Integer.parseInt(scanner.readLine());
		userInput = (userInput > 2) ? FiestaMap() : (userInput <= 0) ? FiestaMap() : userInput;
		return 0;
	}

	public static int FoodStall() throws IOException {
		// Local variable
		int selectInput = 0;
		int quantityInput = 0;
		int store = 0;
		// Menu
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^^^^^^^^ - Food Stall - ^^^^^^^^^^^^^^");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - What would you like to buy? - <<<<<");
		System.out.println("..... - [1]Lechon     ||     ₱5000  - .....");
		System.out.println("..... - [2]Pancit     ||     ₱100   - .....");
		System.out.println("..... - [3]Lumpia     ||     ₱50    - .....");
		System.out.println("-------------------------------------------");
		// Ask the user
		System.out.print("Enter the number of your choice: ");
		input = scanner.readLine();
		System.out.println("-------------------------------------------");

		// Checker
		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {

			System.out.println("Invalid Input!");
			FoodStall();
		}

		// Makes sure that the user's input doesn't trigger an array overflow
		selectInput = (selectInput <= 0) ? 1 : selectInput;
		selectInput = (selectInput > 3) ? 3 : selectInput;

		store = shopPrices[selectInput - 1];
		// Checker

		System.out.println("You picked " + shopItems[selectInput - 1] + "!");
		System.out.println("-------------------------------------------");
		System.out.print("How much would you like: ");
		input = scanner.readLine();

		try {
			quantityInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {

			System.out.println("Invalid Input!");
			FoodStall();
		}

		store = quantityInput * store;

		System.out.println("-------------------------------------------");
		System.out.println(
				"Successfully purchased " + quantityInput + " " + shopItems[selectInput - 1] + " for ₱" + store);
		System.out.println("-------------------------------------------");
		System.out.println("Please wait for your order on the counter!");
		System.out.println("-------------------------------------------");
		Raffle();
		return 0;
	}

	static int Raffle() throws IOException {
		String[] miniPrizes = { "I can just subtract one on finalInput but this is funnier", "Free Candy", "Fiesta Hat",
				"Discount Coupon", "Keychain", "Free Pancit", "Free Lechon Bones", "Rice Cooker", "Electric Fan",
				"Meeting with God" };

		int finalInput = 0;
		System.out.println(" - You are eligible for a raffle prize! - ");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - Select a raffle ticket(1-9) - <<<<<");
		System.out.println("-------------------------------------------");
		
		System.out.print("Pick a number : ");
		input = scanner.readLine();

		try {
			finalInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input!");
			FoodStall();
		}
		// Makes sure that the user's input doesn't trigger an array overflow
		finalInput = (finalInput <= 0) ? 1 : finalInput;
		finalInput = (finalInput > 9) ? 9 : finalInput;
		System.out.println("-------------------------------------------");
		System.out.println((finalInput == winningTicket) ? "Wala kang mini prize kasi major prize napanalunan mo!"
				: "🎁 You won a " + miniPrizes[finalInput]);
		System.out.println("-------------------------------------------");
		totalSpent = 0;

		// Useless input, only there to add a pause before the main method is called
		// again
		input = scanner.readLine();
		System.out.println("🎉 Maraming salamat sa lahat ng dumalo! 🎉");	
		System.out.println("❤️ Kita-kits sa susunod na Fiesta! ❤️");
		System.out.println("Sponsored by: Mayor Paz");

		FoodStall();
		return 0;

	}

}
