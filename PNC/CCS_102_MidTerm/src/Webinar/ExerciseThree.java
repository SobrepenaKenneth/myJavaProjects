package Webinar;

import java.util.Scanner;

public class ExerciseThree {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input, inputTwo ,sum = 0, sumTwo = 0;
		
		System.out.println("=== Strict Divisor ===");
		
		System.out.print("Enter a Number: ");
		input = scan.nextInt();
		
		for (int row = 1; row <= input / 2; row++) {
			if (input % row == 0) {
				sum += row;
				System.out.print(row + " ");
			}
			System.out.println(sum);
		}
		System.out.println();
		
		System.out.println("=== Strict Divisor #2 ===");
		
		System.out.print("Enter a Number: ");
		inputTwo = scan.nextInt();
		
		for (int row = 1; row <= inputTwo / 2; row++) {
			if (inputTwo % row == 0) {
				sumTwo += row;
			}
			System.out.print(sumTwo);
		}
		scan.close();
	}

}
