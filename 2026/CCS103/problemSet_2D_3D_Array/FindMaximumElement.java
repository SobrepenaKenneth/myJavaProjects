package problemSet_2D_3D_Array;

import java.util.Scanner;

public class FindMaximumElement {
	public static void main(String[] args) {
		// Properties
		Scanner sc = new Scanner(System.in);
		int row, column;

		System.out.println("=:> Find the Maximum Element in a 2D Array <:=");

		// Input
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
		
		int max = numberList[0][0];
		
		// PROCESS
		for (int rows = 0; rows < numberList.length; rows++) {
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				if (max < numberList[rows][cols]) {
					max = numberList[rows][cols];
				}
			}
		}
		
		System.out.println("-");
		
		// Output
		System.out.println("Maximum Element: " + max);
		sc.close();

	}
}
