package problemSet_2D_3D_Array;

import java.util.Scanner;

public class AverageOfEachLayer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int row, col, layer;

		System.out.println("=:> Average of Each Layer <:=");
		System.out.print("How many layers: ");
		layer = scan.nextInt();
		System.out.print("How many rows: ");
		row = scan.nextInt();
		System.out.print("How many columns: ");
		col = scan.nextInt();

		System.out.println();

		int[][][] numbers = new int[layer][row][col];

		// INPUT
		// I used unicode for the bullet looks better in console :3
		for (int layers = 0; layers < numbers.length; layers++) {
			System.out.println("===== Layer " + (layers + 1) + " =====");
			for (int rows = 0; rows < numbers[layers].length; rows++) {
				System.out.println("Row " + (rows + 1) + ":");
				for (int cols = 0; cols < numbers[layers][rows].length; cols++) {
					System.out.print("   \u2022 Element " + (cols + 1) + ": ");
					numbers[layers][rows][cols] = scan.nextInt();
				}
			}
			System.out.println();
		}

		// PROCESS
		for (int layers = 0; layers < numbers.length; layers++) {
			int sum = 0;

			for (int rows = 0; rows < numbers[layers].length; rows++) {
				for (int cols = 0; cols < numbers[layers][rows].length; cols++) {
					sum += numbers[layers][rows][cols];
				}
			}

			int totalElements = row * col;
			double average = (double) sum / totalElements;

			System.out.println("Layer " + (layers + 1) + " Average: " + average);
		}

		scan.close();

	}

}
