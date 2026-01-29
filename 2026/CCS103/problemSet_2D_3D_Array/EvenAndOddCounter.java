package problemSet_2D_3D_Array;

import java.util.Scanner;

public class EvenAndOddCounter {
	public static void main(String[] args) {
		// Properties
		Scanner sc = new Scanner(System.in);
		int row, column, odd = 0, even = 0;

		System.out.println("=:> Even and Odd Counter <:=");

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
		
		// PROCESS
		for (int rows = 0; rows < numberList.length; rows++) {
			for (int cols = 0; cols < numberList[rows].length; cols++) {
				if (numberList[rows][cols] % 2 == 0) {
					even++;
				} else {
					odd++;
				}
			}
		}
		
		System.out.println("-");
		
		// OUTPUT
		System.out.println("Even numbers count: " + even);
		System.out.println("Odd numbers count: " + odd);
		
		
		sc.close();
	}
}
