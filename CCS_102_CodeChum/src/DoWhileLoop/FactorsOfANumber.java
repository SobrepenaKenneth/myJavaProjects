package DoWhileLoop;

import java.util.Scanner;

public class FactorsOfANumber {
	/*
	 * Write a program that takes an integer number. Use a do-while loop to print
	 * all the factors of the entered number. A factor is a number that divides
	 * another number without leaving a remainder.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number, counter = 1;

		System.out.print("Enter an integer: ");
		number = scan.nextInt();
		
		System.out.print("Factors of " + number + ": ");
		do {
			if (number % counter == 0) {
				// And this is also print but with empty space
				System.out.print(counter + " ");
			}
			counter++;
		} while (counter <= number);
		scan.close();
	}
}
