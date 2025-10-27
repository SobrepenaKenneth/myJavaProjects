package While_Loop;

import java.util.Scanner;

public class DigitCount {
	public static void main(String[] args) {
		/*Write a program that takes an integer number. 
		 * Use a while loop to count the number of digits 
		 * in the entered number and print it.
		 * */
		Scanner scan = new Scanner(System.in);
		// LOCAL VARIABLES
		int number, count = 0;

		System.out.print("Enter a positive integer: ");
		number = scan.nextInt();

		if (number == 0) {
			count = 1;
		} else {
			int temporary = number;
			while (temporary != 0) {
				temporary /= 10;
				count++;
			}
		}
		System.out.println("Number of digits: " + count);
		scan.close();
	}
}
