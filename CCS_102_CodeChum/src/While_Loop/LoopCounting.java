package While_Loop;

import java.util.Scanner;

public class LoopCounting {
	public static void main(String[] args) {
		/*
		 * Write a program that takes an integer n.
		 * Use a while loop to count from 1 to n (inclusive), 
		 * and print each number on a new line.*/
		Scanner scan = new Scanner(System.in);
		int n, counter = 0;

		System.out.print("Enter a number: ");
		n = scan.nextInt();

		// this is like for loop so i will set a counter and then increase its value
		// as long as the condition became false
		while (counter < n) {
			counter++;
			System.out.println(counter);
		}
		scan.close();
	}
}
