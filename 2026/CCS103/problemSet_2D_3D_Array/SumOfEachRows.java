package problemSet_2D_3D_Array;

import java.util.Scanner;

/**
 * Write a program that calculates and displays the sum of each row in a 2D
 * array.
 */
public class SumOfEachRows {
	public static void main(String[] args) {
		// Properties
		Scanner sc = new Scanner(System.in);
		int row, column, sumOfCurrentRow = 0;

		System.out.println("=:> Sum of each Rows in a 2D Array <:=");
		
		// Ask the user
		System.out.print("How many rows: ");
		row = sc.nextInt();
		
		System.out.print("How many columns: ");
		column = sc.nextInt();
		
		int[][] numberList = new int[row][column];
		
		// Ask the user Elements
		for (int rows = 0; rows < numberList.length; rows++) {
			System.out.println("=:> Row " + (rows + 1) + " <:=");
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				System.out.print("Element [" + (cols + 1) + "] : " );
				numberList[rows][cols] = sc.nextInt();
			}
		}
		
		// Sum of each Rows
		for (int rows = 0; rows < numberList.length; rows++) {
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				sumOfCurrentRow += numberList[rows][cols];
			}
			System.out.println("Row " + (rows + 1) + " Sum: " + sumOfCurrentRow);
			sumOfCurrentRow = 0; // Resets
		}
		
		sc.close();
	}
}
