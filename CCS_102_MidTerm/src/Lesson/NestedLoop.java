package Lesson;

import java.util.Scanner;

public class NestedLoop {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		int num, numTwo, numThree, numFour;
		
		// Recall
		for (int counter = 1; counter <= 1; counter++) {
			System.out.println("Nested Loops");
		}// end of for loop
		
		System.out.print("Enter a number: ");
		num = scan.nextInt();
		
		// Pattern
		// Outer loop vertical printing
		for (int outer = 1; outer <= num; outer++) {
			// Inner loop horizontal printing
			for (int inner = 1; inner <= num; inner++) {
				System.out.print(inner);
			}// end of inner loop
			System.out.println();
		}// end of outer loop
		
		// Half Triangle
		System.out.println("Half Triangle");
		System.out.print("Enter a number: ");
		numTwo = scan.nextInt();
		
		for (int outer = 1; outer <= numTwo; outer++) {
			for (int inner = 1; inner <= outer; inner++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		// Reverse Triangle
		System.out.println("Reverse Triangle");
		System.out.print("Enter a number: ");
		numThree = scan.nextInt();
		
		for (int outer = numThree; outer >= 1; outer--) {
			for (int inner = 1; inner <= outer; inner++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		// Half side / right Triangle
		System.out.println("Half side right Triangle");
		System.out.print("Enter a number: ");
		numFour = scan.nextInt();
		
		for (int outer = 1; outer <= numFour; outer++) {
			for (int space = numFour; space > outer; space--) {
				System.out.print("");
			}
			
			for (int inner = 1; inner <= outer; inner++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
}
