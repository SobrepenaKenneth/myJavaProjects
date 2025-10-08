package Lesson6;

import java.util.Scanner;

public class LeapYears {

	public static void main(String[] args) {
		/* 
		 * LESSON 6: Leap Years
		 * by codechum Admin
		 * 
		 * Write a program that takes an intenger year as input and performs the following
		 * steps to determine if the year is a leap year:
		 * 
		 * 1. Checks if year is divisible by 4 (i.e., year % 4 == 0) to check if it's a
		 *    multiple of 4.
		 * 2. Checks if year is not divisible by 100 (i.e., year % 100 != 0) to exclude years
		 *    that are multiples of 100 but not multiples of 400.
		 * 3. CHecks if year is divisible by 400 (i.e., year % 400 == 0) to include years that
		 *    are multiples of 400.
		 *        > Input: An Integer, year, representing a year.
		 * 
		 * The program should print "Year is a leap year." if condition is met. Otherwise, do
		 * nothing.
		 * */
		
		Scanner scan = new Scanner(System.in);
		// > Declaration
		int year;
		
		// > Input
		System.out.println("Enter the year: ");
		year = scan.nextInt();
		
		// > Process & Output
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			System.out.println("Year is a leap year.");
		}

	}
}
