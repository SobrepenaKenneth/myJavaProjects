package Webinar;

public class Webinar {

	public static void main(String[] args) {
		// ==== WEBINAR ====
		System.out.println("== Basic Nested Loop ==");
		// Outer loop
		for (int row = 1; row <= 3; row++) {
			// Inner loop
			for (int col = 1; col <= 2; col++) {
				System.out.println("Row = " + row + ", Column = " + col);
			}
		}
		
		System.out.println("== Number Pattern ==");
		// Outer loop
		for (int row = 1; row <= 4; row++) {
			// Inner loop
			for (int col = 1; col <= row; col++) {
				System.out.print(col + " ");
			}
			System.out.println();
		}
		
		System.out.println("== Multiplication Table ==");
		// Outer loop
		for (int num1 = 1; num1 <= 3; num1++) {
			// Inner loop
			for (int num2 = 1; num2 <= 3; num2++) {
				int product = num1 + num2;
				System.out.print(num1 + "x" + num2 + "=" + product + "\t");
			}
			System.out.println();
		}
		
		System.out.println("== Right Angle Triangle ==");
		for (int row = 1; row <= 5; row++) {
			for (int col = 1; col <= row; col++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
		System.out.println("== Right Angle Triangle Alternating Pattern ==");
		for (int row = 1; row <= 5; row++) {
			for (int col = 1; col <= row; col++) {
				if ((row + col) % 2 == 0) {
					System.out.print("# ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.println();
		}
		
		System.out.println("== Inverted Right Angle Triangle ==");
		for (int row = 5; row >= 1; row--) {
			for (int col = 1; col <= row; col++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
		System.out.println("== Pyramid Pattern ==");
		for (int row = 1; row <= 5; row++) {
			
			for (int space = 1; space <= 5 - row; space++) {
				System.out.print("  ");
			}
			
			for (int col = 1; col <= (2 * row - 1); col++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
}
