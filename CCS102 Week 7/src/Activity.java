import java.util.Scanner;
public class Activity {
	public static void main(String[] args) {
		// Scanner object
		Scanner scan = new Scanner(System.in);
		
		// Local Variables
		int score;
		
		// Input
		System.out.print("Enter score: ");
		score = scan.nextInt();
		
		// Process
		// Note: Do the Invalid first!
		if (score >= 0 && score <= 60) { // below 60 - F
			// Output
			System.out.println("Your Grade is: F"); 
		} else if (score >= 60 && score < 70) { // 60 and above - E
			System.out.println("Your Grade is: E");
		} else if (score >= 70 && score < 80) { // 70 and above - D
			System.out.println("Your Grade is: D");
		} else if (score >= 80 && score < 90) { //  80 and above - C
			System.out.println("Your Grade is: C");
		} else if (score >= 90 && score < 100) { // 90 and above - B
			System.out.println("Your Grade is: B");
		} else if (score == 100) { // 100 - A
			System.out.println("Your Grade is: A");
		} else { // Error Message
			System.out.println("Invalid Score!");
		}
		scan.close();
	} // Main method
} // Main Class
