package PreAssesmentTask;

import java.util.Scanner;

/**
 * Problem 5: Display Elements of a 1D Array Description Write a Java program
 * that stores 5 integers in a 1D array and displays all the elements.
 * Requirements • Use a 1D array • Use a loop to display values Sample Input
 * Enter 5 integers: 3 6 9 12 15 Sample Output Array elements: 3 6 9 12 15
 * 
 * 01/09/2026
 */

public class ArrayElements {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int[] numbers = new int[5];

		System.out.println("Enter 5 integers: ");
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = scan.nextInt();
		}

		System.out.println("Array elements:");
		for (int elements : numbers) {
			System.out.print(elements + " ");
		}
		scan.close();
	}
}