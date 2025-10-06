package For_Loops;

import java.util.Scanner;

public class SumOfNumbers {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a postive integer: ");
		int n = sc.nextInt();
		
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += 1;
		}
		System.out.println("Sum of the first " + n + " natural numbers is: " + sum);

	}

}
