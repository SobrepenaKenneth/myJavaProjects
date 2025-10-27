package While_Loop;

import java.util.Scanner;

public class EvenNumberCounter {
	/*
	 * Write a program that takes an integer n. Use a while loop to iterate through
	 * the numbers from 1 to n (inclusive). For each number, check if it is even,
	 * and if so, print it on a separate line.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, counter = 1;

		System.out.print("Enter a positive integer: ");
		n = scan.nextInt();

		// lmao i will copy paste this to 2 but instead i will add 2 to the counter
		// apparantly that doen't work
		// oh so the increment is in the last
		while (counter <= n) {
			if (counter % 2 == 0) {
				System.out.println(counter);
			}
			counter++;
		}
		scan.close();
	}
}
