package Practice;

import java.util.Scanner;

public class PatterMatrix {
	public static void main(String[] args) {
		// Goal ask the user rows, columns, symbols to make pattern
		Scanner sc = new Scanner(System.in);
		
		int rows, columns;
		char symbol;
		
		System.out.print("Enter the number of Rows: ");
		rows = sc.nextInt();
		System.out.print("Enter the number of Columns: ");
		columns = sc.nextInt();
		System.out.print("Enter the Symbol to use: ");
		symbol = sc.next().charAt(0);
		
		for (int i = 1; i <= columns; i++) {
			for (int j = 1; j <= rows; j++) {
				System.out.print(symbol + " ");
			}// end of inner
			System.out.println();
		}// end of outer
	}// main method
}// Main class
