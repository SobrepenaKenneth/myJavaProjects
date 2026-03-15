package ACSS;

import java.util.Scanner;

public class JavaLoops {
	public static void main(String[] args) {
		// DRY (Don't Repeat Yourself) Principle

		// Ctrl + Shift + O - IMPORTS
		// Ctrl + D - REMOVE
		Scanner scan = new Scanner(System.in);

		// XXX: For Loop - is best used when you know how many times an operation needs
		// to run
		// Initialization -> Condition -> Increment/Decrement

		// Write a hollow square with n*n size
		int n = 5;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == 1 || i == n || j == 1 || j == n) {
					System.out.print("* ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}

		// BREAK
		System.out.println("-");

		int a = 15;

		// XXX: While Loop
		// Modulo long method
		// 15/2 = 7.5
		// 2 * 7 = 14
		// 15 - 14 = 1

		System.out.println(n + " ");
		while (a != 1) {
			if (a % 2 == 0)
				a = a / 2;
			else if (a % 2 == 1)
				a = (a * 3) + 1;

			System.out.println(a + " ");
		}

		System.out.println("You reached 1!");

		// BREAK
		System.out.println("-");

		// XXX: Do While Loop
		int min = 1;
		int max = 100;
		int guess;
		String feedback;
		int attempts = 0;

		System.out.println("I will guess your number 1 to 100!");

		do {
			guess = (min + max) / 2;
			attempts++;

			System.out.print("Is it " + guess + "? (Type H for High, L for too Low, C for Correct): ");
			feedback = scan.next().toUpperCase();

			if (feedback.equals("H")) {
				max = guess - 1;
			} else if (feedback.equals("L")) {
				min = guess + 1;
			}

		} while (!feedback.equals("C") && min <= max);

		if (feedback.equals("C")) {
			System.out.println("Nice! I guessed it in " + attempts + " attempts.");
		} else {
			System.out.println("Impossible! Check your math!");
		}

	}// main method
}// main class
