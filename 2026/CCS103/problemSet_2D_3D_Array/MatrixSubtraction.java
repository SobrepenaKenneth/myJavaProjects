package problemSet_2D_3D_Array;

import java.util.Scanner;

public class MatrixSubtraction {
	public static void main(String[] args) {
		// Properties
		// TODO: The rows and column has to be the same!
		Scanner sc = new Scanner(System.in);
		int row, column;

		System.out.println("=:> Matrix Subtraction <:=");
		// Ask the user
		System.out.print("How many rows: ");
		row = sc.nextInt();

		System.out.print("How many columns: ");
		column = sc.nextInt();

		System.out.println("-");

		int[][] sum = new int[row][column];
		int[][] array_One = new int[row][column];
		int[][] array_Two = new int[row][column];

		// Ask the first Array
		System.out.println("=:> First Array <:=");

		// Ask the user Elements
		for (int rows = 0; rows < array_One.length; rows++) {
			System.out.println("=:> Row " + (rows + 1) + " <:=");
			for (int cols = 0; cols < array_One[rows].length; cols++) {
				System.out.print("Element [" + (cols + 1) + "] : ");
				array_One[rows][cols] = sc.nextInt();
			}
		}

		System.out.println("-");

		// Ask the second Array
		System.out.println("=:> Second Array <:=");

		// Ask the user Elements
		for (int rows = 0; rows < array_Two.length; rows++) {
			System.out.println("=:> Row " + (rows + 1) + " <:=");
			for (int cols = 0; cols < array_Two[rows].length; cols++) {
				System.out.print("Element [" + (cols + 1) + "] : ");
				array_Two[rows][cols] = sc.nextInt();
			}
		}

		// PROCESS
		for (int rows = 0; rows < array_Two.length; rows++) {
			for (int cols = 0; cols < array_Two[rows].length; cols++) {
				sum[rows][cols] = array_One[rows][cols] - array_Two[rows][cols];
			}
		}

		System.out.println("-");

		// OUTPUT
		System.out.println("Resultant Matrix:");

		for (int rows = 0; rows < array_One.length; rows++) {
			for (int cols = 0; cols < array_One[rows].length; cols++) {
				System.out.print(sum[rows][cols] + " ");
			}
			System.out.println();
		}

		sc.close();

	}
}
