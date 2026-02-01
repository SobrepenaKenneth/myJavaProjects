package problemSet_2D_3D_Array;

import java.util.Scanner;

public class Count_Positive_NegativeAndZero {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int row, col, layer, positive = 0, negative = 0, zero = 0;
		
		System.out.println("=:> Count Positive, Negative, and Zero <:=");
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
			for (int rows = 0; rows < numbers[layers].length; rows++) {
				for (int cols = 0; cols < numbers[layers][rows].length; cols++) {
					if (numbers[layers][rows][cols] == 0) {
						zero++;
					} else if (numbers[layers][rows][cols] > 0) {
						positive++;
					} else {
						negative++;
					}
				}
			}
		}
		
		// OUTPUT
		System.out.println("Postive numbers: " + positive);
		System.out.println("Negative numbers: " + negative);
		System.out.println("Zero Values: " + zero);
		
		
		scan.close();
	}
}
