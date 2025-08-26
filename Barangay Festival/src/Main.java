import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// ENTRY POINT
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name;
		
		final int PUTO = 5;
		
		int age;
		int order1;
		int order2;
		int userPicked;
		
		// MADE BY:
		// Greets the User
		// Difficulty 1/5
		System.out.println("----------------------------------------");	
		System.out.println("❤️ - Maligayang pagdalo sa - ❤️");
		System.out.println("🏆 - Aninag Festival 2025 - 🏆");
		System.out.println("----------------------------------------");
		
		// MADE BY: 
		// Ask the user their name
		// Difficulty 3/5
		System.out.print("Enter your First Name: ");
		name = reader.readLine();
		
		System.out.println("-----------------------------------------");
		System.out.println("Kamusta " + name + "!");
		System.out.println("-----------------------------------------");
		
		System.out.print("Ilang taon ka na: ");
		age = Integer.parseInt(reader.readLine());
		
		System.out.println("-----------------------------------------");
		System.out.println("Ikaw ay " + age + " na taong gulang!");
		System.out.println("-----------------------------------------");
		System.out.println("Maligayang pagdalo sa Aninag Festival!");
		System.out.println("-----------------------------------------");
		
		// MADE BY:
		// Show the menu
		// Difficulty 5/5
		System.out.println(">>>>> - Saan mo gusto magtungo? - <<<<<");
		System.out.println(".... [1] - Festival Schedule - [1] ....");
		System.out.println(".... [2]    - Festival Map -   [2] ....");
		System.out.println(".... [3]     - Food Stall -    [3] ....");
		System.out.println(".... [4]     - Game Booth -    [4] ....");
		System.out.println(".... [5]       - Sports -      [5] ....");
		
		System.out.println("-----------------------------------------");	
		System.out.print("Pumile ng numero: ");
		userPicked = Integer.parseInt(reader.readLine());
		System.out.println("-----------------------------------------");
		
		// MADE BY:
		// Festival Schedule (1)
		String menuOption1 = (userPicked == 1) ? "- Aninag Festival Shedule - " 
				+ "\n 8:00 am : Start of event" 
				+ "\n 9:00 am : Parade": "";
		
		String menuOption2 = (userPicked == 2) ? "Test 2" 
				+ "\n 9:00 am : Parade": "";
		String menuOption3 = (userPicked == 2) ? "Test 2" 
				+ "\n 9:00 am : Parade": "";
		String menuOption4 = (userPicked == 2) ? "Test 2" 
				+ "\n 9:00 am : Parade": "";
		String menuOption5 = (userPicked == 2) ? "Test 2" 
				+ "\n 9:00 am : Parade": "";

		System.out.println(menuOption1);
		System.out.println(menuOption2);
		System.out.println(menuOption2);
		System.out.println(menuOption2);
		System.out.println(menuOption2);

		// MADE BY:
		// Food Stall (3)
		
		
	}

}
