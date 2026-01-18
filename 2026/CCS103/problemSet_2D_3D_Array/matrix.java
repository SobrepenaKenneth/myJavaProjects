package problemSet_2D_3D_Array;

import java.util.Scanner;

public class matrix {
	public static void main(String[] args) {
		// Scanner object
		Scanner scan = new Scanner(System.in);
		
		// Array matrix
		int[][] numbers = new int[3][3];
		
		// Ask the user
		System.out.println("Enter 9 numbers: ");
		for (int rows = 0; rows < numbers.length; rows++) {
			for (int cols = 0; cols < numbers.length; cols++) {
				numbers[rows][cols] = scan.nextInt();
			}
		}
		
		// Print the Matrix
		System.out.println("-");
		System.out.println("Matrix:");
		for (int rows = 0; rows < numbers.length; rows++) {
			for (int cols = 0; cols < numbers.length; cols++) {
				System.out.print(numbers[rows][cols] + " ");
			}
			System.out.println();
		}
		
		scan.close();
	}
}
