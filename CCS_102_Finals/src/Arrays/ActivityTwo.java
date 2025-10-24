package Arrays;

import java.util.Scanner;

public class ActivityTwo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] number;
		int size, input, even, odd;

		System.out.println("======================");
		// ask the user for the size
		System.out.print("Enter the array size: ");
		size = scan.nextInt();
		
		// Initialize the array size
		number = new int[size];
		
		System.out.println("Array size: " + number.length);
		System.out.println("======================");
		// INPUT
		System.out.println("\nEnter " + size + " array values:");
		for (int index = 0; index < number.length; index++) {
			number[index] = scan.nextInt();
		}
		
		// OUTPUT
		System.out.println("\nArray values:");
		for(int index = 0; index < number.length; index++) {
			System.out.println("|" + index + "| = " + number[index]);
		}
		
		System.out.println();
		
		for(int index = 0; index < number.length; index++) {
			if (number[index] % 2 == 0) {
				System.out.println(number[index] + " is EVEN");
			} else {
				System.out.println(number[index] + " is ODD");
			}
		}
		
		System.out.println("Even Numbers: ");
		for (int i = 0; i < number.length; i++) {
			if(number[i] % 2 == 0) {
				System.out.print(number[i] + ", ");
			}
			System.out.println();
		}
		System.out.println("Odd Numbers: ");
		for (int k = 0; k < number.length; k++) {
			if(number[k] % 2 != 0) {
				System.out.print(number[k] + ", ");
			}
		}
	}
}
