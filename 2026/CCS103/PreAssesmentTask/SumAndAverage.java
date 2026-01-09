package PreAssesmentTask;

import java.util.Scanner;

/**
 * Problem 3: Sum and Average of Numbers Description Write a Java program that
 * asks the user to enter 5 integers, then computes the sum and average.
 * Requirements • Use a loop • No array needed Sample Input Enter 5 numbers: 10
 * 20 30 40 50 Sample Output Sum: 150 Average: 30
 * 
 * 09/01/2026
 */

public class SumAndAverage {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int numbers, sum = 0, average;

		System.out.println("Enter 5 numbers:");

		for (int number = 0; number < 5; number++) {
			numbers = scan.nextInt();
			sum += numbers;
		}

		average = sum / 5;

		System.out.println("Sum: " + sum);
		System.out.println("Average: " + average);
		scan.close();
	}
}
