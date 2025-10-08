package Lesson6;

import java.util.Scanner;

public class Equality {

	public static void main(String[] args) {
		/* 
		 * LESSON 6: EQUALITY
		 * by codechum Admin
		 * 
		 * Write a program that takes two integers, x and y, 
		 * as input and checks if they are equal, if x is equal to y,
		 * it print "Numbers are equal."
		 * */
		
		// My Code
		
		Scanner scan = new Scanner(System.in);
		
		// > Declaration
		int x, y;
		
		// > Input
		System.out.print("Enter x: ");
		x = scan.nextInt();
		System.out.print("Enter y: ");
		y = scan.nextInt();
		
		// > Process & Output
		if (x == y) {
			System.out.println("Numbers are equal.");
		}

	}

}
