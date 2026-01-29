package problemSet_2D_3D_Array;

import java.util.Scanner;

public class SearchAnElement {

	public static void main(String[] args) {
		// Properties
		Scanner sc = new Scanner(System.in);
		int row, column, element;

		System.out.println("=:> Search An Element <:=");

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
		
		System.out.print("Enter element to search: ");
		element = sc.nextInt();
		
		for (int rows = 0; rows < numberList.length; rows++) {
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				if (element == numberList[rows][cols]) {
					System.out.println("Element found at Row " + (rows + 1) + ", Column " + (cols + 1));
				}
			}
		}
		sc.close();
	}
}
