package problemSet_2D_3D_Array;

import java.util.Scanner;

public class SumOfEachColumns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Properties
		Scanner sc = new Scanner(System.in);
		int row, column, sumOfCurrentRow = 0;

		System.out.println("=:> Sum of each Columns in a 2D Array <:=");

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
				System.out.print("Element [" + (cols + 1) + "] : ");
				numberList[rows][cols] = sc.nextInt();
			}
		}
		
		System.out.println("=:> Sum of each columns <:=");

		// Sum of each Columns
		// Outer loop selects the column to be summed.
		// Inner loop goes through all rows in that column and adds the values.
		for (int cols = 0; cols < numberList[0].length; cols++) {
			for (int rows = 0; rows < numberList.length; rows++ ) {
				sumOfCurrentRow += numberList[rows][cols];
			}
			System.out.println("Column " + (cols + 1) + " Sum: " + sumOfCurrentRow);
			sumOfCurrentRow = 0; // Resets
		}

		sc.close();
	}
}
