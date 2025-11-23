package For_Loops;

import java.util.Scanner;

public class Nested {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number;
		
		// Right Angle
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
		// Number Pyramid
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
		// Multiplication Table
		System.out.print("Enter a Number: ");
		number = sc.nextInt();
		for (int i = 1; i <= number; i++) {
			for (int j = 1; j <= number; j++) {
				System.out.print(i * j + "\t");
			}
			System.out.println();
		}

	}
}
