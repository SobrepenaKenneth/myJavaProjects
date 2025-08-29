package Revise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		// BufferedReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// User Input
		String input;
		// Food Stall Arrays >> Food Menu <<
		String[] foodItems = { "Lechon", "Pancit", "Lumpia", "Puto" };
		// >> Food Menu Prices <<
		int[] foodPrices = { 5000, 100, 50, 5 };
		// Drinks Stall Arrays >> Beverage Menu <<
		String[] drinkItems = { "Iced Tea", "Mineral Water", "Softdrinks", "Buko Juice" };
		// >> Beverage Prices <<
		int[] drinkPrices = { 25, 20, 15, 10 };

		// Processing Variables
		int selectInput = 0;
		int store = 0;
		int quantityInput = 0;

		// Raffle Prizes
		String[] miniPrizes = { "I can just subtract one on finalInput but this is funnier", "Free Candy", "Fiesta Hat",
				"Discount Coupon", "Keychain", "Free Pancit", "Free Lechon Bones", "Rice Cooker", "Electric Fan",
				"Meeting with God" };
		// XXX: The method is a final topic too much risk must be removed

		// Welcome the user, by asking their name and Greeting Them
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ----------------- WELCOME SCREEN ----------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		System.out.println("-------------------------------------------");
		System.out.println("<<->> ❤️ - Maligayang pagdalo sa - ❤️ <<->>");
		System.out.println("<<<>>>>> 🏆 - Aninag Festival - 🏆 <<<<<>>>");
		System.out.println("-------------------------------------------");
		// Ask the user their name
		System.out.print("Enter your name: ");
		String name = reader.readLine();
		// Greet the user :3
		System.out.println("-------------------------------------------");
		System.out.println("<<->> - Maligayang Pagdalo " + name + "! - <<->>");
		System.out.println("-------------------------------------------");

		// TODO: To make it fun i will add like a short transition using
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ------------------- COUNTDOWN ------------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		System.out.println("> - Press Enter To Begin The Countdown - <");
		reader.readLine();
		System.out.println("  >>>>>>>>>> ------ 3 ------ <<<<<<<<<<   ");
		reader.readLine();
		System.out.println("  >>>>>>>>>> ------ 2 ------ <<<<<<<<<<   ");
		reader.readLine();
		System.out.println("  >>>>>>>>>> ------ 1 ------ <<<<<<<<<<   ");
		reader.readLine();
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^^^^ - THE FIESTA BEGINS - ^^^^^^^^^^");
		System.out.println("-------------------------------------------");

		// FIXME: The fiesta map doesn't work so i will remove it - ken

		// TODO: To make it fun i will add like a short transition using
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ------------------- TRANSITION 1 ------------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		System.out.println("- But before we dive into the GAMES and FUN -");
		System.out.println("-------------------------------------------");
		System.out.print("         <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");
		System.out.println("> Why don't we visit the FOOD STALL to grab <");
		System.out.println("     - you a delicious snack to enjoy! -     ");
		System.out.println("-------------------------------------------");
		System.out.print("         <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");

		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ------------------- FOOD STALL ------------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// Welcome the user
		System.out.println(">>>>> - Welcome to the Food Stall - <<<<<");
		System.out.println(" - Are you ready to choose your meal? -");
		// Show the menu of foods available
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^^^^^ - Food Stall Menu - ^^^^^^^^^^^^");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - What would you like to buy? - <<<<<");
		System.out.println("..... - [1] Lechon     ||     ₱5000  - .....");
		System.out.println("..... - [2] Pancit     ||     ₱100   - .....");
		System.out.println("..... - [3] Lumpia     ||     ₱50    - .....");
		System.out.println("..... - [4] Puto       ||     ₱5     - .....");
		System.out.println("-------------------------------------------");
		// Ask the user what food they would like
		System.out.print("Enter the number of your choice: ");
		input = reader.readLine();

		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("-------------------------------------------");
			System.out.println(">> - Invalid Input! Let me pick for you! - <<");
			// Set input to the most expensive item.
			selectInput = 4; // DEFAULT VALUE
		}

		// Makes sure that the user's input doesn't trigger an array overflow
		selectInput = (selectInput <= 0) ? 1 : selectInput;
		selectInput = (selectInput > 4) ? 4 : selectInput;

		store = foodPrices[selectInput - 1];
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^ - You picked " + foodItems[selectInput - 1] + "! - ^^^^^^");
		System.out.println("-------------------------------------------");

		// Ask the user how much they would buy
		System.out.print("How much would you like: ");
		input = reader.readLine();
		try {
			quantityInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("-------------------------------------------");
			System.out.println("That doesn't seem to be a number! Let me pick for you!");
			// Set input to the most expensive item.
			quantityInput = 1;

		}
		store = quantityInput * store;

		System.out.println("-------------------------------------------");
		System.out.println("Successfully purchased " + quantityInput + " " 
		+ foodItems[selectInput - 1] + " for ₱" + store);
		System.out.println("-------------------------------------------");
		System.out.println("Please wait for your order on the counter!");
		System.out.println("-------------------------------------------");
		System.out.print("         <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");

		// TODO: Ask the user if they want a drinks
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ------------------- DRINKS STALL ------------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		System.out.println(">>>>> - Would you like to order some drinks? - <<<<<");
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^^^^^^^^ - Beverage Stall - ^^^^^^^^^^^^^^");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - What would you like to buy? - <<<<<");
		System.out.println("..... - [1] Iced Tea     ||     ₱25    - .....");
		System.out.println("..... - [2] Mineral Water||     ₱20    - .....");
		System.out.println("..... - [3] Softdrinks   ||     ₱15    - .....");
		System.out.println("..... - [4] Buko Juice   ||     ₱10    - .....");
		System.out.println("-------------------------------------------");
		// Ask the user what drinks they would like
		System.out.print("Enter the number of your choice: ");
		input = reader.readLine();

		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("-------------------------------------------");
			System.out.println("That doesn't seem to be a number! Let me pick for you!");
			// Set input to the most expensive item.
			selectInput = 1;

		}
		// Makes sure that the user's input doesn't trigger an array
		// If input is below minimum, set to minimum value
		selectInput = (selectInput <= 0) ? 1 : selectInput;
		// If input is above maximum value, set to maximum value
		selectInput = (selectInput > 4) ? 4 : selectInput;

		store = drinkPrices[selectInput - 1];
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^ - You picked " + drinkItems[selectInput - 1] + "! - ^^^^^^");
		System.out.println("-------------------------------------------");

		// Ask the user how much they would buy
		System.out.print("How much would you like: ");
		input = reader.readLine();
		try {
			quantityInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("-------------------------------------------");
			System.out.println("That doesn't seem to be a number! Let me pick for you!");
			// Set input to the most expensive item.
			quantityInput = 1;

		}

		store = quantityInput * store;

		System.out.println("-------------------------------------------");
		System.out.println(
				"Successfully purchased " + quantityInput + " " + drinkItems[selectInput - 1] + " for ₱" + store);
		System.out.println("-------------------------------------------");
		System.out.println("Please wait for your order on the counter!");
		System.out.println("-------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");

		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ------------------- TRANSITION 2 ------------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		System.out.println(">>>>> - Did everyone enjoy the food? - <<<<<");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - Now that our stomach are full, - <<<<<");
		System.out.println("  - Let's get ready for the Sports Event!- ");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");
		
		// TODO: ask the user their sports
		// TODO: ask the user their team color
		// TODO: ask the user the team 1 score
		// TODO: ask the user the team 2 score
		// TODO: display the result
		// TODO: Transition to raffle or small mini game -ken2025 R.I.P
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ------------------- SPORTS EVENT ------------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		System.out.println(">>>>> - Welcome to the Sports Event - <<<<<");
		System.out.println("- Pick the sport to begin with our fiesta game -");
		// Show the menu of foods available
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^^^^^ - Sports Menu - ^^^^^^^^^^^^");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - Choose A Sport - <<<<<");
		System.out.println("..... - [1] Basketball [1] - .....");
		System.out.println("..... - [2] Volleyball [2] - .....");
		System.out.println("..... - [3] Badminton  [3] - .....");
		System.out.println("..... - [4] Tennis     [4] - .....");
		System.out.println("-------------------------------------------");
		
		
		// TODO : Add other activities HERE
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		// >>>>> ------------------- RAFFLE ------------------- <<<<<
		// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		System.out.println(" - You are eligible for a raffle prize! - ");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - Select a raffle ticket(1-9) - <<<<<");
		System.out.println("-------------------------------------------");
		System.out.print("Pick a number : ");
		input = reader.readLine();
		int finalInput = Integer.parseInt(input);

		System.out.println("-------------------------------------------");
		System.out.println("🎁 You won a " + miniPrizes[finalInput] + "!");
		System.out.println("-------------------------------------------");
		reader.readLine(); // just pause

		// >>>>> ------------------- EXIT ------------------- <<<<<
		System.out.println("🎉 Maraming salamat sa lahat ng dumalo! 🎉");
		System.out.println("❤️ Kita-kits sa susunod na Fiesta! ❤️");
		System.out.println("Sponsored by Mayor Losamia!");
	}
}
