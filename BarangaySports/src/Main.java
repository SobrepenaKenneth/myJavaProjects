import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/* Barangay Sports Event - Sobrepeña
	 * Fiesta - Paz
	 * */
	public static void main(String[] args) throws IOException  {
		// Buffered Reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// Variable Declaration
		int sportInput = 0, colorInput1, colorInput2;
		final int SPORTS_OPTIONS = 2, COLOR_OPTIONS = 6;
		
		int team1;
		int team2;
		
		int team1Color;
		int team2Color;
		
		int team1Score = 0;
		int team2Score = 0;
		
		// Gawin ko lng readable
		// String array for sports - 
		String[] sports = {"Basketball", "Volleyball", "Tennis"};
		String[] teamColor = {"Yellow", "Blue", "Red", "Orange", "Green", "Pink"};
		
		System.out.println("---------------------------------");	
		System.out.println("❤️ - Maligayang pagdalo sa - ❤️");
		System.out.println("🏆 Barangay Sports Event 2025 🏆");
		System.out.println("---------------------------------");	
		// Display a list of sports
		System.out.println("---------------------------------");	
		System.out.println("🏀 - Pick a sport - 🏀");
		System.out.println("---------------------------------");
		// Ito yung mga options pwede dagdagan
		System.out.println(" [0] BASKETBALL ");
		System.out.println(" [1] VOLLEYBALL ");
		System.out.println(" [2] TENNIS ");
		System.out.println("---------------------------------");
		
		/* Goal ng try catch, if hindi nag enter si user ng whole numbers
		 * huhulihin niya yung error and then i priprint yung statement sa catch
		 * */
		try {
			// Ask the user
			System.out.print("Enter number of the sport: ");
			sportInput = Integer.parseInt(reader.readLine());

			}	catch (Exception e) {
				System.out.println("---------------------------------");
				System.out.println("⚠ Please enter a whole number only! ⚠");
				System.out.println("---------------------------------");
				System.out.print("Enter number of the sport: ");
				sportInput = Integer.parseInt(reader.readLine());
				System.out.println("---------------------------------");
			}	
		 
		/* If hindi allowed if else mabilis nlng palitan sa ternary operator
		 * if yung piniling sports ay lumampas sa final variable ng options sa sports
		 * mag rurun yung statement sa if, at papipiliin ulit yung user
		 * 
		 * else if yung input ng user ay negative number mag rurun yung statement sa else if,
		 * papipiliin ulit yung user
		 * 
		 * (else) if hindi applicable yung if at else if mag rurun yung nase (else)
		 * */
		if (sportInput > SPORTS_OPTIONS) {
			System.out.println("⚠ Please only select the number listed on the index! ⚠");
			//Break
			System.out.println("---------------------------------");
			System.out.print("Enter number of the sport: ");
			System.out.println("---------------------------------");
			// Reader
			sportInput = Integer.parseInt(reader.readLine());
		} else if (sportInput < 0){
			System.out.println("⚠ Please only select the number listed on the index! ⚠");
			//Break
			System.out.println("---------------------------------");
			System.out.print("Enter number of the sport: ");
			sportInput = Integer.parseInt(reader.readLine());	
			System.out.println("---------------------------------");
			// Reader
		} else {
			System.out.println("---------------------------------");
			System.out.println("You choosed: " + sports[sportInput]);
			}
		System.out.println("---------------------------------");
		System.out.println("🎨 - Pick a color - 🎨");
		System.out.println("---------------------------------");
		System.out.println(" [0] YELLOW ");
		System.out.println(" [1] BLUE ");
		System.out.println(" [2] RED ");
		System.out.println(" [3] ORANGE ");
		System.out.println(" [4] GREEN ");
		System.out.println(" [5] PINK ");
		System.out.println("---------------------------------");
		
		// Copy paste lng din to ng try catch katulad sa sports
		try {
			// Ask user to input team1
			System.out.print("Pick a color team 1: ");
			// Reader
			colorInput1 = Integer.parseInt(reader.readLine());
			}	catch (Exception e) {
				System.out.println("---------------------------------");
				System.out.println("⚠ Please enter an whole number only! ⚠");
				System.out.println("---------------------------------");
				System.out.print("Pick a color team 1: ");
				colorInput1 = Integer.parseInt(reader.readLine());
				System.out.println("---------------------------------");
			} 
		
		/* same logic din sa if else ng sports
		 * */
		if (colorInput1 > COLOR_OPTIONS) {
			System.out.println("⚠ Please only select the number listed on the index ⚠");
			//Break
			colorInput1 = Integer.parseInt(reader.readLine());
			System.out.println("---------------------------------");
		} else if (colorInput1 < 0){
			System.out.println("⚠ Please only select the number listed on the index ⚠");
			//Break
			colorInput1 = Integer.parseInt(reader.readLine());
			System.out.println("---------------------------------");
		} else {
			System.out.println("---------------------------------");
			System.out.println("Team 1 choosed: " + teamColor[colorInput1]);
			System.out.println("---------------------------------");
		}
		
		// Ask user to input team2
		System.out.print("Pick a color team 2: ");
		colorInput2 = Integer.parseInt(reader.readLine());
		
		System.out.println("---------------------------------");
		System.out.println("Team 2 choosed: " + teamColor[colorInput2]);
		System.out.println("---------------------------------");
		
		// Check if mag ka parehas
		if (colorInput1 == colorInput2) {
			System.out.println("---------------------------------");
			System.out.println("- Team 1/2 picked the same color -");
			System.out.println("---------------------------------");
			// Ask user to input team1
			System.out.print("Pick a color team1: ");
			colorInput1 = Integer.parseInt(reader.readLine());
			System.out.println("---------------------------------");
			// Reader
			
			// Ask user to input team2
			System.out.print("Pick a color team2: ");
			// Reader
			colorInput2 = Integer.parseInt(reader.readLine());
			System.out.println("---------------------------------");
		} else {
			// VERSUS
			System.out.println("---------------------------------");
			System.out.println("🏆 Team " + teamColor[colorInput1] + " Vs " + "Team " + teamColor[colorInput2] + " 🏆");
			System.out.println("---------------------------------");
		}
		
		// catch if may maglagay ng hindi whole number
		try {
			System.out.println("✏️ - Please enter each team individual score! - ✏️");
			System.out.println("---------------------------------");
			System.out.print("Team " + teamColor[colorInput1] + " score: ");
			team1Score = Integer.parseInt(reader.readLine());
			System.out.println("---------------------------------");
			
			System.out.print("Team " + teamColor[colorInput2] + " score: ");
			team2Score = Integer.parseInt(reader.readLine());
			System.out.println("---------------------------------");
			
		} catch (Exception e) {
			System.out.println("---------------------------------");
			System.out.println("⚠ Please enter an whole number only! ⚠");
			System.out.println("---------------------------------");
			System.out.print("Team " + teamColor[colorInput2] + " score: ");
			team2Score = Integer.parseInt(reader.readLine());
			System.out.println("---------------------------------");
		}	
		// LAKI NG FLOWCHART NITO
		// Problem: what if yung score tie
		// Problem: dapat kapag tie may rematch
		if (team1Score > team2Score) { // if score ng team1 > team2
			System.out.println("✨ - Congratulations team " + teamColor[colorInput1] + " 🏆 winning the gold throphy! 🏆 - ✨");
			System.out.println("---------------------------------");
		} else if (team1Score == team2Score){ // Check if tie yung score
			System.out.println("📣 - It's a Tie! - 📣");
			System.out.println("---------------------------------");
			
			System.out.println("Team " + teamColor[colorInput1] + " scored: " + team1Score);
			System.out.println("Team " + teamColor[colorInput2] + " scored: " + team2Score);
			System.out.println("---------------------------------");
		} else { // else team1 < team2
			System.out.println("✨ - Congratulations team " + teamColor[colorInput2] + " 🏆 for winning the gold throphy! 🏆 - ✨");
			System.out.println("---------------------------------");
		}		
		System.out.println("🎉 Maraming salamat sa lahat ng lumahok! 🎉");	
		System.out.println("❤️ Kita-kits sa susunod na barangay sports day! ❤️");
		System.out.println("Sponsored by: Mayor Paz");
		System.out.println("---------------------------------");
	}
}