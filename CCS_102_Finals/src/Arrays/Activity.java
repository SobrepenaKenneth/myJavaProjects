package Arrays;

import java.util.Scanner;

public class Activity {
	static Scanner scan = new Scanner(System.in);
	static int[] number;
	static int size, input;
	
	public static void main(String[] args) {
		askTheUser();
		switch (input) {
			case 1 :
				findTheHighest();
				break;
			case 2 :
				findTheLowest();
				break;
			case 3 :
				findBoth();
				break;
			case 4:
				endProgram();
				break;
		}
	}// main method
	
	public static void askTheUser() {
		do {
			// ask the user for the size
			System.out.println("What would you like to do today?");
			System.out.println("[1] - Find the Highest");
			System.out.println("[2] - Find the Lowest");
			System.out.println("[3] - Find Both");
			System.out.println("[4] - Exit");
			System.out.print("Enter your choice: ");
			input = scan.nextInt();
		} while (input < 0 || input > 4);
	}// end of askTheUser method
	
	public static void findTheHighest() {
		System.out.println("======================");
		// ask the user for the size
		System.out.print("Enter the array size: ");
		size = scan.nextInt();
		
		// Initialize the array size
		number = new int[size];
		
		System.out.println("Array size: " + number.length);
		System.out.println("======================");
		// INPUT
		System.out.println("\nEnter " + size + " array values:");
		for (int index = 0; index < number.length; index++) {
			number[index] = scan.nextInt();
		}
		
		int max = number[0];
		
		// OUTPUT
		System.out.println("\nArray values:");
		for(int index = 0; index < number.length; index++) {
			System.out.println("|" + index + "| = " + number[index]);
		}
		
		for(int index = 0; index < number.length; index++) {
			if (number[index] > max) {
				max = number[index];
			}
		}
		System.out.println();
		System.out.println("The highest is: " + max);
		System.out.println();
		askTheUserAgain();
	}// end of findTheHighest
	
	public static void findTheLowest() {
		System.out.println("======================");
		// ask the user for the size
		System.out.print("Enter the array size: ");
		size = scan.nextInt();
		
		// Initialize the array size
		number = new int[size];
		
		System.out.println("Array size: " + number.length);
		System.out.println("======================");
		// INPUT
		System.out.println("\nEnter " + size + " array values:");
		for (int index = 0; index < number.length; index++) {
			number[index] = scan.nextInt();
		}
		int max = number[0];
		
		// OUTPUT
		System.out.println("\nArray values:");
		for(int index = 0; index < number.length; index++) {
			System.out.println("|" + index + "| = " + number[index]);
		}
		
		
		for(int index = 0; index < number.length; index++) {
			if (number[index] > max) {
				max = number[index];
			}
		}
		
		System.out.println("The lowest is: " + max);
		System.out.println();
		askTheUserAgain();
	} // end of findTheLowest
	
	public static void findBoth() {
		System.out.println("======================");
		// ask the user for the size
		System.out.print("Enter the array size: ");
		size = scan.nextInt();
		
		// Initialize the array size
		number = new int[size];
		
		System.out.println("Array size: " + number.length);
		System.out.println("======================");
		// INPUT
		System.out.println("\nEnter " + size + " array values:");
		for (int index = 0; index < number.length; index++) {
			number[index] = scan.nextInt();
		}
		int lowest = number[0];
		int highest = number[0];
		
		// OUTPUT
		System.out.println("\nArray values:");
		for(int index = 0; index < number.length; index++) {
			System.out.println("|" + index + "| = " + number[index]);
		}
		
		
		for(int index = 0; index < number.length; index++) {
			if (number[index] < lowest) {
				lowest = number[index];
			}
			if (number[index] > highest) {
				highest = number[index];
			}
		}
		System.out.println();
		
		System.out.println("The highest is: " + highest);
		System.out.println("The lowest is: " + lowest);
		System.out.println();
		askTheUserAgain();
	}
	
	public static void endProgram() {
		System.out.println("Program terminated....");
	}// end of findTheHighest
	
	public static void askTheUserAgain() {
		do {
			// ask the user for the size
			System.out.println("Would you want to do it again?");
			System.out.println("[1] - Yes");
			System.out.println("[2] - No");
			System.out.print("Enter your choice: ");
			input = scan.nextInt();
			System.out.println();
		} while (input < 0 || input > 2);
		
		switch (input) {
		case 1 :
			askTheUser();
			break;
		case 2 :
			endProgram();
			break;
		}
	}// end of askTheUserAgain method
	
}// Main Class
