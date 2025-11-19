package Webinar;

import java.util.Scanner;

public class ExerciseFour {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input;

		System.out.print("Enter an integer: ");
		input = sc.nextInt();
		System.out.println();
		System.out.println("Multiplication Table");
		for (int row = 1; row <= input; row++) {
			for (int column = 1; column <= input; column++) {
				System.out.print((row * column) + "\t");
			}
			System.out.println();
		}
	}
}
