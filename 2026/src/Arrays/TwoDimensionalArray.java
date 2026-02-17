package Arrays;

import java.util.Scanner;

public class TwoDimensionalArray {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int[][] myArray = {
				{1, 2, 3},// row 0
				{4, 5, 6},// row 1
				{6, 7, 8} // row 2
		};
		
		// The outer loops through rows
		for (int row = 0; row < myArray.length; row++) {
			for (int col = 0; col < myArray.length; col++) {
				System.out.print(myArray[row][col]);
			}
			System.out.println();
		}
		
		scan.close();
		
	}
}
