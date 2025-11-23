package Practice;

import java.util.Scanner;

public class MultiplicationTable {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number;
		
		System.out.print("Enter a number: ");
		number = scan.nextInt();
		
		for (int i = 1; i <= number; i++) {
			for (int j = 1; j <= number; j++) {
				System.out.print(i * j + "\t");
			}
			System.out.println();
		}
		scan.close();
	}
}
