package ArrayStudy;

import java.util.Scanner;

public class Assigning {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int row;
		
		System.out.print("How many rows: ");
		row = scan.nextInt();
		
		int[][] array = new int[row][];
		
		for (int a = 0; a < array.length; a++) {
			int col;
			System.out.print("Enter columns for Row " + (a + 1) + ": ");
			col = scan.nextInt();
		}
		
		for (int a = 0; a < array.length; a++) {
			System.out.println("Row " + (a + 1) + ":");
			for (int b = 0; b < array[a].length; b++) {
				System.out.println("Length of column in Row" + (a + 1) + ": ");
				System.out.print(array[a].length);
			}
		}

	}
}
