import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Input
		Scanner scan = new Scanner(System.in);
		int num;
		int age;
		int remainder;
		double score;
		
		System.out.println("--------------------------");
		
		System.out.println("> Positive or Negative <");
		System.out.print("Enter a number: ");
		num = scan.nextInt();
		// Single statement works without the brackets but only
		// Process
		if (num > 0) { // number is greater than 0
			// Output
			System.out.println(num + " is a Positive Number!");
		} else if (num < 0){ // number is less than 0
			System.out.println("(" + num + ") is a Negative Number!");
		} else {
			System.out.println("(" + num + ") is equal to ZERO");
		}
		
		System.out.println("--------------------------");
		
		System.out.println("> Age verification <");
		System.out.print("Enter your age: ");
		age = scan.nextInt();
		
		if (age >= 18) {
			System.out.println("Valid age!");
		} else {
			System.out.println("Invalid age!");
		}
		
		System.out.println("--------------------------");
		
		System.out.println("> Check if even or odd <");
		System.out.print("Enter a number: ");
		num = scan.nextInt();
		
		remainder = num & 2;
		System.out.println("Remainder: " + remainder);
		
		if (remainder == 0 ) {
			System.out.println("(" + num +  ") is an Even Number");
		} else {
			System.out.println("(" + num +  ") is an Odd Number");
		}
		
		System.out.println("--------------------------");
		
		System.out.println("> PASS OR FAIL <");
		System.out.print("Enter your score: ");
		score = scan.nextDouble();
		
		if (score >= 60 && score <= 100) { // T && T = T (AND Logical)
			System.out.println("Passed!");
		} else if (score >= 0 && score < 60){
			System.out.println("Failed!");
		} else {
			System.out.println("Invalid Score!");
		}
		
		System.out.println("--------------------------");
		
		// Nested "If" Statement
		
		System.out.print("Enter your score: ");
		score = scan.nextDouble();
		
		// Process
		if (score >= 0 && score <= 100) { // valid scores
			// Passed (60 - 100)
			if (score >= 60 && score <= 100) {
				System.out.println("Passed!");
			} else {// Failed (below 60)
				System.out.println("Failed");
			}
		} else {
			System.out.println("Invalid Score!");
		}
		
		System.out.println("Program Terminated..");
	} // Main method
} // Main class
