import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	/* Note para bukas: tatanungin muna kung anong team gusto nila
	 * team 1 ba or team 2, then ask the sports na lalaruin nila,
	 * then ask each team anong team color gusto nila
	 * */
	public static void main(String[] args) throws IOException  {
		// Buffered Reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// Variable Declaration
		int userInput;
		final int SPORTS_OPTIONS = 2, COLOR_OPTIONS = 6;
		
		int team1;
		int team2;
		
		int team1Color;
		int team2Color;
		
		int team1Score;
		int team2Score;
		
		// String array for sports - 
		String[] sports = {"Basketball", "Volleyball", "Tennis"};
		String[] teamColor = {"Yellow", "Blue", "Red", "Orange", "Green", "Pink"};
		
		String[] testArray = new String[0]; //Null
		
		//Break
		System.out.println("-----------------------");
		
		// Display a list of sports
		System.out.println("- Pick a sport -");
		
		//Break
		System.out.println("-----------------------");
		
		System.out.println(" [0] BASKETBALL ");
		System.out.println(" [1] VOLLEYBALL ");
		System.out.println(" [2] TENNIS ");
		
		//Break
		System.out.println("-----------------------");
		
		// Ask the user
		System.out.print("Enter number of the sport: ");
		userInput = Integer.parseInt(reader.readLine());
		
		//Break
		System.out.println("-----------------------");
		 
		if (userInput > SPORTS_OPTIONS) {
			System.out.println("Please only select the number listed on the index!");
			//Break
			System.out.println("-----------------------");
			System.out.print("Enter number of the sport: ");
			// Reader
			userInput = Integer.parseInt(reader.readLine());
		} else if (userInput < 0){
			System.out.println("Please only select the number listed on the index!");
			//Break
			System.out.println("-----------------------");
			System.out.print("Enter number of the sport: ");
			// Reader
			userInput = Integer.parseInt(reader.readLine());	
		} else {
			System.out.println("You choose: " + sports[userInput]);
			//Break
			System.out.println("-----------------------");
			System.out.println("- Pick a team color - ");
			//Break
			System.out.println("-----------------------");
		}	
		//Team Color
		System.out.println(" [0] YELLOW ");
		System.out.println(" [1] BLUE ");
		System.out.println(" [2] RED ");
		System.out.println(" [3] ORANGE ");
		System.out.println(" [4] GREEN ");
		System.out.println(" [5] PINK ");
		//Break
		System.out.println("-----------------------");
		System.out.print("Enter color of the team: ");
		// Reader
		userInput = Integer.parseInt(reader.readLine());
	}
}
