package While_Loop;

import java.util.Scanner;

public class SumOfDigits {
	/*
	 * Write a program that takes an integer input from the user and calculates the
	 * sum of its digits using a while loop. The program should output the result
	 * the user.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input, sum = 0, digits;

		System.out.print("Enter an integer: ");
		input = scan.nextInt();
		// This needs to be initialized first
		int counter = input;
		while (counter > 0) {
			digits = counter % 10;
			sum += digits;
			counter /= 10;
		}
		System.out.print("Sum of digits: " + sum);
		scan.close();
	}
}
