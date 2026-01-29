package problemSet_2D_3D_Array;

import java.util.Scanner;

public class DiagonalElements {

	public static void main(String[] args) {
		// Properties
		Scanner sc = new Scanner(System.in);
		int row, column;

		System.out.println("=:> Diagonal Elements <:=");
		
		// INPUT
		// Ask the user
		System.out.print("How many rows: ");
		row = sc.nextInt();
		
		System.out.print("How many columns: ");
		column = sc.nextInt();
		
		System.out.println("-");
		
		int[][] numberList = new int[row][column];
		
		// Ask the user Elements
		for (int rows = 0; rows < numberList.length; rows++) {
			System.out.println("=:> Row " + (rows + 1) + " <:=");
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				System.out.print("Element [" + (cols + 1) + "] : " );
				numberList[rows][cols] = sc.nextInt();
			}
		}
		
		System.out.println("-");
		
		System.out.println("Diagonal Elements:");
		// OUTPUT
		for (int index = 0; index < numberList.length; index++) {
			System.out.println(numberList[index][index]);
		}
		
		sc.close();

	}
}
