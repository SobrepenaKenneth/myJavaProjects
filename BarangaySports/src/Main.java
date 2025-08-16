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
		int sportInput = 0, colorInput1, colorInput2;
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
		
		try {
			// Ask the user
			System.out.print("Enter number of the sport: ");
			sportInput = Integer.parseInt(reader.readLine());

			}	catch (Exception e) {
				System.out.println("-----------------------");
				System.out.println("Please enter an whole number only! ");
				System.out.println("-----------------------");
				System.out.print("Enter number of the sport: ");
				sportInput = Integer.parseInt(reader.readLine());
			}	
		
		//Break
		System.out.println("-----------------------");
		 
		// Sports process
		if (sportInput > SPORTS_OPTIONS) {
			System.out.println("Please only select the number listed on the index!");
			//Break
			System.out.println("-----------------------");
			System.out.print("Enter number of the sport: ");
			// Reader
			sportInput = Integer.parseInt(reader.readLine());
		} else if (sportInput < 0){
			System.out.println("Please only select the number listed on the index!");
			//Break
			System.out.println("-----------------------");
			System.out.print("Enter number of the sport: ");
			// Reader
			sportInput = Integer.parseInt(reader.readLine());	
		} else {
			System.out.println("You choose: " + sports[sportInput]);
			//Break
			System.out.println("-----------------------");
			System.out.println("-----------------------");
			System.out.println("- Pick a color - ");
			//Break
			System.out.println("-----------------------");
			}
		
		//Team1 Color
		System.out.println(" [0] YELLOW ");
		System.out.println(" [1] BLUE ");
		System.out.println(" [2] RED ");
		System.out.println(" [3] ORANGE ");
		System.out.println(" [4] GREEN ");
		System.out.println(" [5] PINK ");
		//Break
		System.out.println("-----------------------");
		
		try {
			// Ask user to input team1
			System.out.print("Enter color of the team1: ");
			// Reader
			colorInput1 = Integer.parseInt(reader.readLine());


			}	catch (Exception e) {
				System.out.println("-----------------------");
				System.out.println("Please enter an whole number only! ");
				System.out.println("-----------------------");
				System.out.print("Enter color of the team1: ");
				colorInput1 = Integer.parseInt(reader.readLine());
			} 
		

		//Break
		System.out.println("-----------------------");
		
		// team 1 color picked
		if (colorInput1 > COLOR_OPTIONS) {
			System.out.println("Please only select the number listed on the index");
			//Break
			System.out.println("-----------------------");
			colorInput1 = Integer.parseInt(reader.readLine());
		} else if (colorInput1 < 0){
			System.out.println("Please only select the number listed on the index");
			//Break
			System.out.println("-----------------------");
			colorInput1 = Integer.parseInt(reader.readLine());
		} else {
			System.out.println("Team 1 picked color: " + teamColor[colorInput1]);
			System.out.println("-----------------------");
		}
		
		// Ask user to input team2
		System.out.print("Enter color of the team2: ");
		// Reader
		colorInput2 = Integer.parseInt(reader.readLine());
		System.out.println("-----------------------");
		System.out.println("Team 1 picked color: " + teamColor[colorInput2]) ;
		System.out.println("-----------------------");
		// VERSUS
		System.out.println("=====================================");
		System.out.println("Team " + teamColor[colorInput1] + " Vs " + "Team " + teamColor[colorInput2]);
		System.out.println("=====================================");
		
	}
}
