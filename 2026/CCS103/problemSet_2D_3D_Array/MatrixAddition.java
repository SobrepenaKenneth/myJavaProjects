package problemSet_2D_3D_Array;

import java.util.Scanner;

public class MatrixAddition {
	public static void main(String[] args) {
		// Properties
		// TODO: The rows and column has to be the same!
		Scanner sc = new Scanner(System.in);
		int row = 0, column = 0;
		int[][] sum = new int[row][column];
		int[][] array_One = new int[row][column];
		int[][] array_Two = new int[row][column];
		
		System.out.println("=:> Matrix Addition <:=");
		// Ask the user
		System.out.print("How many rows: ");
		row = sc.nextInt();
		
		System.out.print("How many columns: ");
		column = sc.nextInt();
		
		// Ask the first Array
		System.out.println("=:> First Array <:=");
		// Ask the user Elements
		for (int rows = 0; rows < array_One.length; rows++) {
			System.out.println("=:> Row " + (rows + 1) + " <:=");
			for (int cols = 0; cols < array_One[rows].length; cols++) {
				System.out.print("Element [" + (cols + 1) + "] : " );
				array_One[rows][cols] = sc.nextInt();
			}
		}
		
		
		
		
	}
}
