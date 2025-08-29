import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Final {
	public static void main(String[] args) throws IOException {
		// BufferedReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// User Input Variables
		String input; // Global storage for raw Input
		String pickedItem = "Item Picked"; // Global Temporary Storage for displayable values.

		// Sport Variables
		int redScore = 0;
		int blueScore = 0;
		int scoreDifference = 0;

		// Processing Variables
		int selectInput = 0; // Used for all conversion processes of the raw input.
		int price = 0;
		int quantityInput = 0;

		// >>>>> ----------------- WELCOME SCREEN ----------------- <<<<<
		// Step 2
		System.out.println("--------------------------------------------------");
		System.out.println("<<->> ❤️ - Maligayang pagdalo sa - ❤️ <<->>");
		System.out.println("<<<>>>>> 🏆 - Sorteo Festival - 🏆 <<<<<>>>");
		System.out.println("--------------------------------------------------");
		// Step 3
		System.out.print("Enter your name: ");
		String name = reader.readLine();
		
		System.out.println("--------------------------------------------------");
		System.out.println("<<->> - Maligayang Pagdalo " + name + "! - <<->>");
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- COUNTDOWN ------------------- <<<<<
		// Step 5
		System.out.println("> - Press Enter To Begin The Countdown - <");
		reader.readLine();
		// Step 6
		System.out.println("  >>>>>>>>>> ------ 3 ------ <<<<<<<<<<   ");
		reader.readLine();
		System.out.println("  >>>>>>>>>> ------ 2 ------ <<<<<<<<<<   ");
		reader.readLine();
		System.out.println("  >>>>>>>>>> ------ 1 ------ <<<<<<<<<<   ");
		reader.readLine();
		// Step 7
		System.out.println("--------------------------------------------------");
		System.out.println("^^^^^^^^^ - THE FIESTA BEGINS - ^^^^^^^^^^");
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- TRANSITION 1 ------------------- <<<<<
		// Step 8
		System.out.println("- But before we dive into the GAMES and FUN -");
		System.out.println("--------------------------------------------------");
		// Step 9
		System.out.print("         <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");
		// Step 10
		System.out.println("> Why don't we visit the FOOD STALL to grab <");
		System.out.println("     - you a delicious snack to enjoy! -     ");
		// Step 11
		System.out.println("--------------------------------------------------");
		System.out.print("         <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- FOOD STALL ------------------- <<<<<
		// Step 12
		System.out.println(">>>>> - Welcome to the Food Stall - <<<<<");
		// Step 13
		System.out.println(" - Are you ready to choose your meal? -");
		// Step 14
		System.out.println("--------------------------------------------------");
		System.out.println("^^^^^^^^^^ - Food Stall Menu - ^^^^^^^^^^^^");
		System.out.println("--------------------------------------------------");
		System.out.println(">>>>> - What would you like to buy? - <<<<<");
		System.out.println("..... - [1] Lechon     ||     ₱120  - .....");
		System.out.println("..... - [2] Pancit     ||     ₱80   - .....");
		System.out.println("..... - [3] Lumpia     ||     ₱15   - .....");
		System.out.println("..... - [4] Puto       ||     ₱10   - .....");
		System.out.println("--------------------------------------------------");

		// Step 15
		System.out.print("Enter the number of your choice: ");
		input = reader.readLine();

		// Step 16
		try {
			// XXX: Conversion String to Input (Step 16)
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// Step 16
			System.out.println("--------------------------------------------------");
			System.out.println(">> - Invalid Input! Let me pick for you! - <<");
			// Set input to the most expensive item.
			selectInput = 4;
		}

		// Step 17
		// FIXME: Use OR instead of nesting! -ken
		selectInput = (selectInput <= 0 || selectInput > 4) ? 4 :  selectInput;

		// Step 18 
		// - FOOD -
		pickedItem = (selectInput == 1) ? "Lechon"
				   : (selectInput == 2) ? "Pancit"
				   : (selectInput == 3) ? "Lumpia" 
				   : (selectInput == 4) ? "Puto" : pickedItem;
		// - PRICE OF THE FOOD -,
		price = (selectInput == 1) ? 120
			  : (selectInput == 2) ? 80
			  : (selectInput == 3) ? 15 
			  : (selectInput == 4) ? 10 : price;

		// Step 19
		System.out.println("--------------------------------------------------");
		System.out.println("^^^^^^ - You picked " + pickedItem + "! - ^^^^^^");
		System.out.println("--------------------------------------------------");

		// Step 20
		System.out.print("How many would you like: ");
		input = reader.readLine();

		try {
			// Step 21
			quantityInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// Step 21
			System.out.println("--------------------------------------------------");
			System.out.println(">> - Invalid Input! Let me pick for you! - <<");
			quantityInput = 1;
		}

		// Step 22
		price = quantityInput * price;
		// Step 23
		System.out.println("--------------------------------------------------");
		System.out.println("Successfully purchased " + quantityInput + " " + pickedItem + " for ₱" + price);
		System.out.println("--------------------------------------------------");
		// Step 24
		System.out.println("Please wait for your order on the counter!");
		// Step 25
		System.out.println("--------------------------------------------------");
		System.out.print("         <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- DRINKS STALL ------------------- <<<<<
		// Step 26
		System.out.println("> - Would you like to order some drinks? - <");
		// Step 27
		System.out.println("--------------------------------------------------");
		System.out.println("^^^^^^^^^^^^^ - Beverage Stall - ^^^^^^^^^^^^^^");
		System.out.println("--------------------------------------------------");
		System.out.println(">>>>> - What would you like to buy? - <<<<<");
		System.out.println(".... - [1] Iced Tea       ||     ₱25    - ....");
		System.out.println(".... - [2] Mineral Water  ||     ₱20    - ....");
		System.out.println(".... - [3] Softdrinks     ||     ₱15    - ....");
		System.out.println(".... - [4] Buko Juice     ||     ₱10    - ....");
		System.out.println("--------------------------------------------------");
		// Step 28
		System.out.print("Enter the number of your choice: ");
		input = reader.readLine();

		// Step 29
		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("--------------------------------------------------");
			System.out.println(">> - Invalid Input! Let me pick for you! - <<");
			// Default will be Iced Tea -ken
			selectInput = 1;

		}
		// Makes sure that the user's input doesn't trigger an array
		// If input is below minimum, set to minimum value
		selectInput = (selectInput <= 0) ? 1 : selectInput;
		// If input is above maximum value, set to maximum value
		selectInput = (selectInput > 4) ? 4 : selectInput;

		// 1st Ternary sets the displayed name to the item name. 2nd sets the value to
		// the item's.
		pickedItem = (selectInput == 1) ? "Iced Tea" : pickedItem;
		price = (selectInput == 1) ? 25 : price;
		pickedItem = (selectInput == 2) ? "Mineral Water" : pickedItem;
		price = (selectInput == 2) ? 20 : price;
		pickedItem = (selectInput == 3) ? "Softdrinks" : pickedItem;
		price = (selectInput == 3) ? 15 : price;
		pickedItem = (selectInput == 4) ? "Buko Juice" : pickedItem;
		price = (selectInput == 4) ? 10 : price;

		System.out.println("--------------------------------------------------");
		System.out.println("^^^^^^ - You picked " + pickedItem + "! - ^^^^^^");
		System.out.println("--------------------------------------------------");

		// Ask the user how much they would buy
		System.out.print("How much would you like: ");
		input = reader.readLine();
		try {
			quantityInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("--------------------------------------------------");
			System.out.println(">> - Invalid Input! Let me pick for you! - <<!");
			// Set input to the most expensive item.
			quantityInput = 1;
		}

		// PROCESS
		price = quantityInput * price;

		System.out.println("--------------------------------------------------");
		System.out.println("Successfully purchased " + quantityInput + " " + pickedItem + " for ₱" + price);
		System.out.println("--------------------------------------------------");
		System.out.println("Please wait for your order on the counter!");
		System.out.println("--------------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- TRANSITION ------------------- <<<<<
		System.out.println(">>>>> - Did everyone enjoy the food? - <<<<<");
		System.out.println("--------------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");
		System.out.println(">>>>> - Now that our stomach are full, - <<<<<");
		System.out.println("  - Let's get ready for the Sports Event!- ");
		System.out.println("--------------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- SPORTS EVENT ------------------- <<<<<
		System.out.println(">>>>> - Welcome to the Sports Event - <<<<<");
		System.out.println("- Pick the sport to begin with our fiesta game -");
		System.out.println("--------------------------------------------------");
		System.out.println("^^^^^^^^^^ - Sports Menu - ^^^^^^^^^^^^");
		System.out.println("--------------------------------------------------");
		System.out.println(">>>>> - Choose A Sport - <<<<<");
		System.out.println("..... - [1] Basketball [1] - .....");
		System.out.println("..... - [2] Volleyball [2] - .....");
		System.out.println("..... - [3] Badminton  [3] - .....");
		System.out.println("..... - [4] Tennis     [4] - .....");
		System.out.println("--------------------------------------------------");

		System.out.print("Enter the number of your choice: ");
		input = reader.readLine();

		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("--------------------------------------------------");
			System.out.println(">> - Invalid Input! Let me pick for you! - <<");
			// Set to a valid value.
			selectInput = 1;
		}

		// If input is below minimum, set to minimum value
		selectInput = (selectInput <= 0) ? 1 : selectInput;
		// If input is above maximum value, set to maximum value
		selectInput = (selectInput > 4) ? 4 : selectInput;

		pickedItem = (selectInput == 1) ? "Basketball" : pickedItem;
		pickedItem = (selectInput == 2) ? "Volleyball" : pickedItem;
		pickedItem = (selectInput == 3) ? "Badminton" : pickedItem;
		pickedItem = (selectInput == 4) ? "Tennis" : pickedItem;

		System.out.println("--------------------------------------------------");
		System.out.println("^^ - The teams will play " + pickedItem + "! - ^^");
		System.out.println("--------------------------------------------------");

		System.out.print("How many scores did Team Red get: ");
		input = reader.readLine();

		System.out.println("--------------------------------------------------");

		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("That doesn't seem to be a number! Defaulting to 1!");
			System.out.println("--------------------------------------------------");
			// Set to a valid value.
			selectInput = 1;
		}

		redScore = selectInput;
		System.out.println("Team Red scored " + redScore + "!");
		System.out.println("--------------------------------------------------");

		System.out.print("How many scores did Team Blue get: ");
		input = reader.readLine();
		System.out.println("--------------------------------------------------");

		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("That doesn't seem to be a number! Defaulting to 1!");
			System.out.println("--------------------------------------------------");
			// Set to a valid value.
			selectInput = 0;
		}

		blueScore = selectInput;
		System.out.println("Team Blue scored " + blueScore + "!");
		System.out.println("--------------------------------------------------");

		System.out.println("^^^^^^^^ - AND THE WINNER IS - ^^^^^^^^^");
		System.out.println("--------------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");

		scoreDifference = redScore - blueScore;
		// Check if the value is negative(I.E: Blue got more score than Red) and change
		// it to positive.
		scoreDifference = (scoreDifference < 0) ? -scoreDifference : scoreDifference;

		String result = (redScore == blueScore) ? "The match was a draw!"
				: (redScore > blueScore)
						? "Red Team Won the " + pickedItem + " game with a " + scoreDifference + " lead!"
						: "Blue Team Won the " + pickedItem + " game with a " + scoreDifference + " point lead!";

		System.out.println(result);

		System.out.println("--------------------------------------------------");
		System.out.print("          <-> - (Press Enter) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- RAFFLE ------------------- <<<<<
		System.out.println(" - You are eligible for a raffle prize! - ");
		System.out.println("--------------------------------------------------");
		System.out.println(">>>>> - Select a raffle ticket(1-9) - <<<<<");
		System.out.println("--------------------------------------------------");
		System.out.print("Pick a number : ");

		input = reader.readLine();
		try {
			selectInput = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// Set to a valid value.
			selectInput = 1;
		}

		// If input is below minimum, set to minimum value
		selectInput = (selectInput <= 0) ? 1 : selectInput;
		// If input is above maximum value, set to maximum value
		selectInput = (selectInput > 4) ? 4 : selectInput;

		pickedItem = (selectInput == 1) ? "Ticket to Saja Boys" : pickedItem;
		pickedItem = (selectInput == 2) ? "Tumbler" : pickedItem;
		pickedItem = (selectInput == 3) ? "Fiesta Hat" : pickedItem;
		pickedItem = (selectInput == 4) ? "Electric Fan" : pickedItem;
		pickedItem = (selectInput == 5) ? "Laptop" : pickedItem;
		pickedItem = (selectInput == 6) ? "Smart Tv" : pickedItem;
		pickedItem = (selectInput == 7) ? "Brand New Car" : pickedItem;
		pickedItem = (selectInput == 8) ? "House and Lot" : pickedItem;
		pickedItem = (selectInput == 9) ? "Iphone" : pickedItem;

		System.out.println("--------------------------------------------------");
		System.out.println("^^ > 🎁 You won a " + pickedItem + "! < ^^");
		System.out.println("--------------------------------------------------");
		System.out.print("      <-> - (Press Enter To Continue) - <->   ");
		reader.readLine();
		System.out.println("--------------------------------------------------");

		// >>>>> ------------------- EXIT ------------------- <<<<<
		System.out.println("🎉 Maraming salamat sa lahat ng dumalo! 🎉");
		System.out.println("❤️ - Kita-kits sa susunod na Fiesta! - ❤️");
		System.out.println(" ^^^^ Sponsored by Mayor Losamia! ^^^^");
		System.out.println("--------------------------------------------------");
		System.out.print("      <-> - (Press Enter To Continue) - <->   ");
		reader.readLine();

		// >>>>> ------------------- CREDITS ------------------- <<<<<
		System.out.println("==================================================");
		System.out.println("^^^^^ - FIESTA SIMULATOR - ^^^^^     ");
		System.out.println("       >> - MEMBERS - <<             ");
		System.out.println(">> - Tubera, Joselle May D.      - << ");
		System.out.println(">> - Alemania Jr., Rolando G.    - << ");
		System.out.println(">> - Beltran, Alyssa Ashleigh R. - << ");
		System.out.println(">> - Cabrejas, Arcer Mofreal M.  - << ");
		System.out.println(">> - Completo, John Laurence G.  - << ");
		System.out.println(">> - Kaibigan, Jade Martin V.    - << ");
		System.out.println(">> - Losamia, Jenny B.           - << ");
		System.out.println(">> - Paz, Paul Vincent N.        - << ");
		System.out.println(">> - Rabino, Jules Martin E.     - << ");
		System.out.println(">> - Sobrepeña, Kenneth Ruther B.- <<");
		System.out.println("=================================================");
	}
}