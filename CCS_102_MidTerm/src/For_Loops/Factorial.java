package For_Loops;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num, factorial =1;
		
		System.out.print("Enter a Number: ");
		num = scan.nextInt();
		
		for (int counter = 1; counter <= num; counter++) {
			System.out.print(counter);
			if (counter < num) {
				System.out.print(" X ");
			}
			factorial *= counter;
		}
		System.out.print(" = " + factorial);
	}

}
