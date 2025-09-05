import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		// BufferedReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// User Input
		String input;
		// Food Stall Array >> FOOD MENU <<
		String[] shopItems = { "Lechon", "Pancit", "Lumpia", "Puto" };
		// Food Stall Array >> DRINKS <<
		String[] drinks = {"Softdrinks", "Buko Juice", "Iced Tea", "Mineral Water"};
		// Food Stall Array >> PRICES <<
		int[] shopPrices = { 5000, 100, 50, 5 };
		// Raffle Priced
		String[] miniPrizes = { "I can just subtract one on finalInput but this is funnier", 
				"Free Candy", "Fiesta Hat", "Discount Coupon", "Keychain", "Free Pancit", 
				"Free Lechon Bones", "Rice Cooker", "Electric Fan", "Meeting with God" };
		// XXX: The method is a final topic too much risk must be removed
		
		// Welcome the user, by asking their name and Greeting Them
		// >>>>> ----------------- WELCOME SCREEN ----------------- <<<<<
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
		// >>>>> ------------------- COUNTDOWN ------------------- <<<<<
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
		// >>>>> ------------------- TRANSITION ------------------- <<<<<
		System.out.println("- But before we dive into the GAMES and FUN -");
		System.out.println("-------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");
		System.out.println("> Why don't we visit the FOOD STALL to grab <");
		System.out.println("     - you a delicious snack to enjoy! -     ");
		System.out.println("-------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");
		
		// TODO: 
		// >>>>> ------------------- FOOD STALL ------------------- <<<<<
		// Welcome the user
		System.out.println(">>>>> - Welcome to the Food Stall - <<<<<");
		System.out.println(" - Are you ready to choose your meal? -");
		// Show the menu of foods available
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^^^^^ - Food Stall Menu - ^^^^^^^^^^^^");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - What would you like to buy? - <<<<<");
		System.out.println("..... - [1]Lechon     ||     ₱5000  - .....");
		System.out.println("..... - [2]Pancit     ||     ₱100   - .....");
		System.out.println("..... - [3]Lumpia     ||     ₱50    - .....");
		System.out.println("..... - [4]Puto       ||     ₱5     - .....");
		System.out.println("-------------------------------------------");
		// Ask the user what food they would like
		System.out.print("Enter the number of your choice: ");
		input = reader.readLine();
		
		int selectInput = Integer.parseInt(input);

		int store = shopPrices[selectInput - 1];
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^ - You picked " + shopItems[selectInput - 1] + "! - ^^^^^^");
		System.out.println("-------------------------------------------");

		// Ask the user how much they would buy
		System.out.print("How much would you like: ");
		input = reader.readLine();
		int quantityInput = Integer.parseInt(input);
		store = quantityInput * store;

		System.out.println("-------------------------------------------");
		System.out.println(
				"Successfully purchased " + quantityInput + " " + shopItems[selectInput - 1] + " for ₱" + store);
		System.out.println("-------------------------------------------");
		System.out.println("Please wait for your order on the counter!");
		System.out.println("-------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("-------------------------------------------");
		
		// TODO: Ask the user if they want a drinks
		// >>>>> ------------------- DRINKS STALL ------------------- <<<<<
		System.out.println(">>>>> - Would you like to order some drinks? - <<<<<");
		System.out.println("-------------------------------------------");
		System.out.println("^^^^^^^^^^^^^ - Food Stall - ^^^^^^^^^^^^^^");
		System.out.println("-------------------------------------------");
		System.out.println(">>>>> - What would you like to buy? - <<<<<");
		System.out.println("..... - [1]Lechon     ||     ₱5000  - .....");
		System.out.println("..... - [2]Pancit     ||     ₱100   - .....");
		System.out.println("..... - [3]Lumpia     ||     ₱50    - .....");
		System.out.println("..... - [4]Puto       ||     ₱5     - .....");
		System.out.println("-------------------------------------------");
		// Ask the user what drinks they would like
		System.out.print("Enter the number of your choice: ");
		input = reader.readLine();
		
		
		
		// >>>>> ------------------- RAFFLE ------------------- <<<<<
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
