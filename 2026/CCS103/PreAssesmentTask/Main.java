package PreAssesmentTask;

import java.util.Scanner;

/**
 * Problem 1: Even or Odd Number (Fundamentals) Description Write a Java program
 * that asks the user to enter an integer and determines whether it is even or
 * odd. Requirements • Input one integer • Display whether the number is EVEN or
 * ODD Sample Input Enter a number: 7 Sample Output 7 is an odd number.
 * 
 * 01/09/2026
 */
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int number;
		
		System.out.print("Input an number: ");
		
		number = scan.nextInt();
		
		if (number % 2 == 0) {
			System.out.println(number + " is an even number.");
		} else {
			System.out.println(number + " is an odd number.");
		}
		
		scan.close();
	}
}