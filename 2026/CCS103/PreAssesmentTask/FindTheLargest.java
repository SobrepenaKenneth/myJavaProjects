package PreAssesmentTask;

import java.util.Scanner;

/**
 * Problem 2: Find the Largest of Three Numbers (Fundamentals) Description Write
 * a Java program that asks the user to input three integers and displays the
 * largest number. Requirements • Use conditional statements • Display the
 * largest value Sample Input Enter first number: 10 Enter second number: 25
 * Enter third number: 15 Sample Output The largest number is: 25
 * 
 * 01/09/2026
 */

public class FindTheLargest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int firstNumber, secondNumber, thirdNumber, max = 0;

		System.out.print("Enter first number: ");
		firstNumber = scan.nextInt();

		System.out.print("Enter second number: ");
		secondNumber = scan.nextInt();

		System.out.print("Enter third number: ");
		thirdNumber = scan.nextInt();

		if (firstNumber > secondNumber) {
			max = firstNumber;
		} else if (secondNumber > max) {
			max = secondNumber;
		} else {
			max = thirdNumber;
		}
		System.out.println("The largest number is: " + max);
		scan.close();
	}
}