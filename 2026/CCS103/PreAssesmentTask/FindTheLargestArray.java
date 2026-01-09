package PreAssesmentTask;

import java.util.Scanner;

/**
 * Problem 6: Find the Largest Number in a 1D Array Description Write a Java
 * program that stores numbers in a 1D array and finds the largest value.
 * Requirements • Ask user for number of elements • Use a loop and array •
 * Display the largest number Sample Input Enter number of elements: 5 Enter
 * elements: 12 45 8 30 25 Sample Output Largest number: 45
 * 
 * 01/09/2026
 */

public class FindTheLargestArray {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int elements = 0, max = 0;

		System.out.println("Enter number of elements: ");
		elements = scan.nextInt();

		int[] numbers = new int[elements];

		System.out.println("Enter elements:");

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = scan.nextInt();
		}

// Find the largest
		for (int a = 0; a < numbers.length; a++) {
			if (numbers[a] > max) {
				max = numbers[a];
			}
		}

		System.out.println("Largerst number: " + max);
		
		scan.close();
	}
}