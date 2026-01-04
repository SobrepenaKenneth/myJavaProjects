package DoWhileLoop;

import java.util.Scanner;

public class IntegerClassification {
	/*
	 * Write a program that prints "positive" if the number is positive and
	 * "negative" if it is not. Use ado-while loop to repeatedly prompt for input
	 * until a 0 is entered.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number;

		do {
			System.out.print("Enter an integer: ");
			number = scan.nextInt();
			if (number < 0) {
				System.out.println("negative");
			} else if (number == 0) {
				System.out.println("");
			} else {
				System.out.println("positive");
			}
		} while (number != 0);
		scan.close();
	}
}
