package problemSet_2D_3D_Array;

import java.util.Scanner;

public class RowWithHighestSum {
	public static void main(String[] args) {
		// Properties
		Scanner sc = new Scanner(System.in);
		int row, column;

		System.out.println("=:> Row with Highest Sum <:=");

		// Ask the user
		System.out.print("How many rows: ");
		row = sc.nextInt();

		System.out.print("How many columns: ");
		column = sc.nextInt();

		int[][] numberList = new int[row][column];

		System.out.println("-");

		// Ask the user Elements
		for (int rows = 0; rows < numberList.length; rows++) {
			System.out.println("=:> Row " + (rows + 1) + " <:=");
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				System.out.print("Element [" + (cols + 1) + "] : ");
				numberList[rows][cols] = sc.nextInt();
			}
		}
		
		System.out.println("-");
		int max = 0;
		
		// Assuming that the first row has the max
		for (int cols = 0; cols < numberList[0].length; cols++) {
			max += numberList[0][cols];
		}
		
		int rowIndex = 0;
		
		// Then checked the remaining rows start from index 1
		for (int rows = 1; rows < numberList.length; rows++) {
			// Sum needs to be reset
			int sum = 0;
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				sum += numberList[rows][cols];
			} // Outer Loop
			
			// then checked if the current sum is greater than the first
			if (sum > max) {
				// if it is, then the current sum will be the max
				max = sum;
				// then tracked the index of the rows
				rowIndex = rows;
			}// End of IF
		}// Inner Loop
		
		System.out.println("Row with highest sum: Row " + (rowIndex + 1));
		

	}
}
