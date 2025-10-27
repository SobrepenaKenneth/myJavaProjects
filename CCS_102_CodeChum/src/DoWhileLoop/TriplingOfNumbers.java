package DoWhileLoop;

import java.util.Scanner;

public class TriplingOfNumbers {
	/*
	 * Write a program that takes an integer n. Use a do-while loop to calculate and
	 * print the tripling of numbers from 1 to the entered number.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, counter = 1, test = 0;

		System.out.print("Enter a number: ");
		n = scan.nextInt();

		do {
			counter += 3;
			test++;
			System.out.println(counter - 1);
		} while (test != n);
		scan.close();
	}
}
