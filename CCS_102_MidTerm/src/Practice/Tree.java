package Practice;

import java.util.Scanner;

public class Tree {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number;
		char symbol;
		
		System.out.print("Enter the height of the tree: ");
		number = scan.nextInt(); 
		
		System.out.print("Enter a symbol for the tree: ");
		symbol = scan.next().charAt(0); 
		
		// Tree
		for (int i = 1; i <= number; i++) {
			// Space
			for (int s = number; s > i; s--) {
				System.out.print(" ");
			}
			// Symbol, must be odd 1,3,5
			for (int j = 1; j <= (2 * i - 1); j++) {
				System.out.print(symbol);
			}
			System.out.println();
		}
		// Trunk
		int trunkWidth = number / 2;
		if (trunkWidth % 2 == 0) trunkWidth++;
		int spaces = number - trunkWidth / 2;
		
		for (int a = 1; a <= 3; a++) {
			for (int s = 1; s < spaces; s++) {
				System.out.print(" ");
			}
			for (int b = 1; b <= trunkWidth; b++) {
				System.out.print("#");
			}
			System.out.println();
		}
	}
}
