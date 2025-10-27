package While_Loop;

import java.util.Scanner;

public class SquareNumber {
	/*
	 * Write a program that takes an integer number. Use a while loop to print the
	 * square of numbers from 1 to the inputted number. Each square should be
	 * printed on a separate line.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number, counter = 1;

		System.out.print("Enter a number: ");
		number = scan.nextInt();

		while (counter <= number) {
			System.out.println(counter * counter);
			counter++;
		}
		scan.close();
	}
}
