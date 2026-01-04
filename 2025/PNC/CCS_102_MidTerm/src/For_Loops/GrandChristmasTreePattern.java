package For_Loops;

import java.util.Scanner;

public class GrandChristmasTreePattern {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input;
		
		do {
			System.out.print("Enter the height of each tree tier: ");
			input = sc.nextInt();
			
			if (input <= 0 ) {
				System.out.println("----------------------------------");
				System.out.println("Enter a positive integer only!");
				System.out.println("----------------------------------");
			}
		} while (input <= 0);
		
		// Setup for the tree
		int totalTiers = 3;
		int treeMaxWidth = (input + totalTiers - 1) * 2 - 1;
		
		// Building each tier
		for (int tier = 0; tier < totalTiers; tier++) {
			// Inner loop for spacing
			for (int line = 1; line <= input; line++) {
				int symbolsInLine = line * 2 - 1 + tier * 2;
				int spacesBefore = (treeMaxWidth - symbolsInLine) / 2;
				
				// print the spaces
				for (int space = 0; space < spacesBefore; space++) {
					System.out.print(" ");
				}// end of spaces loop
				
				// DECORATION
				// problem: it does not match to the pattern
				// maybe wrong order?
				// The pattern is that every 3rd becomes 0
				// every 5th became @ then 0
				// the pattern should look like this * **0 **0*@
				// why is the last pattern keeps doing **0@*
				for (int symbol = 0; symbol < symbolsInLine; symbol++) {
					if (symbol % 6 == 4) {
						System.out.print("@");
					} else if (symbol % 3 == 2) {
						System.out.print("0");
					} else {
						System.out.print("*");
					}
				}// end of decoration loop
				System.out.println();
			}
		}
		
		// TRUNK 
		int trunkHeight = input;
		int trunkWidth = input / 2 + 1;
		if (trunkWidth % 2 == 0) {
			trunkWidth++;
		}
		int trunkSpaces = (treeMaxWidth - trunkWidth) / 2;
		
		
		// rows of the trunk
		for (int row = 0; row < trunkHeight; row++) {
			for (int space = 0; space < trunkSpaces; space++) {
				System.out.print(" ");
			}
			for (int trunk = 0; trunk < trunkWidth; trunk++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}// main method
}// Main class
