package DoWhileLoop;

import java.util.Scanner;

public class Counting {
	/*
	 * Write a program that takes an integer n. Use a do-while loop to count from 1
	 * to n (inclusive), and print each number on a new line.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, counter = 0;

		System.out.print("Enter a number: ");
		n = scan.nextInt();

		do {
			counter++;
			System.out.println(counter);
		} while (counter != n);
		scan.close();
	}
}
