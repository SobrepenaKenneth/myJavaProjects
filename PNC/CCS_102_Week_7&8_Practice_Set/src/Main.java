import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		/* TODO: Ask the user if he/she is 18 years old or older ✓
		 * TODO: Ask the user if he/she a registered voter ✓
		 * TODO: if both true display - “You are eligible  to vote” ✓
		 * TODO: if not registered display - “You must register to vote” ✓
		 * */
		// BufferedReader object
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// > local variables <
		int age;
		char registered;
		
		System.out.println("=============================");
		System.out.println("*****> Election 2025 <*****");
		System.out.println("-----------------------------");
		// > Ask the user their age < 
		try {
			System.out.print("Enter your age: ");
			age = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("-----------------------------");
			System.out.println(">> - Invalid Input! - <<");
			System.out.println("-----------------------------");
			System.out.print("Enter your age: ");
			age = Integer.parseInt(reader.readLine());
		}
		
		// > if age is greater than or equal to 18 <
		if (age >= 18) {
			// > This will check if user is a registered voter <
			System.out.println("-----------------------------");
			System.out.println("Enter (Y) yes or (N) no <");
			System.out.println("-----------------------------");
			System.out.println("Are you a registered voter?");
			System.out.println("-----------------------------");
			System.out.print("Enter character only: ");
			registered = (char)reader.read();
			System.out.println("-----------------------------");
			
			// > If Yes (Y) <
			if (registered == 'Y') {
				System.out.println("<<>> Congratulations <<>>");
				System.out.println("You are eligible to vote!");
			// > If No (N) <
			} else if (registered == 'N') {
				System.out.println("     <<>> Sorry! <<>>");
				System.out.println("You must register to vote!");
			// > Else wrong input <
			} else {
				System.out.println("Enter (Y) yes or (N) no only!");
				System.out.println("-----------------------------");
				System.out.println("     > Please Try Again <");
				System.out.println("-----------------------------");
			}
		// > if the user is below 18 <
		} else {
			System.out.println("-----------------------------");
			System.out.println("      <<>> Sorry! <<>>");
			System.out.println("You are not eligible to vote!");
		}
		System.out.println("=============================");
	}// Main method
}// Main Class
