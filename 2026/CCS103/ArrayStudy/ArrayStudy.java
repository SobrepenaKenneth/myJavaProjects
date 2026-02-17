package ArrayStudy;

import java.util.Scanner;

public class ArrayStudy {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// sumOfAllValues();
		// print_Ten_By_Ten_Dashes();
		index_Of_Array();
	}

	/**
	 * 1. Sum all values in an array Write a Java program to sum values of an array.
	 * Status: Done
	 */
	public static void sumOfAllValues() {
		int col = 0, sum = 0;

		System.out.print("How many numbers to add: ");
		col = scan.nextInt();

		int[] number_List = new int[col];

		System.out.println("Input numbers: ");
		for (int index = 0; index < number_List.length; index++) {
			number_List[index] = scan.nextInt();
			sum += number_List[index];
		}

		System.out.println("Total: " + sum);
	}

	/**
	 * 2. Print a 10x10 grid of dashes Write a Java program to print the following
	 * grid.
	 * Status: Done
	 */
	public static void print_Ten_By_Ten_Dashes() {
		String[][] matrix = new String[10][10];

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				matrix[row][col] = "- ";
				System.out.print(matrix[row][col]);
			}
			System.out.println();
		}
	}

	/**
	 * 3. Find index of an element in array
	 * Write a Java program to find the index of an array element.
	 * Status: Solving Currently
	 */
	public static void index_Of_Array() {
		int col = 0;
		boolean found = false;

		System.out.print("How many numbers to add: ");
		col = scan.nextInt();

		int[] array = new int[col];
		
		System.out.println("Input numbers: ");
		for (int index = 0; index < array.length; index++) {
			array[index] = scan.nextInt();
		}
		
		System.out.println("Array elements: ");
		System.out.print("> ");
		
		for (int show_Elements : array) {
			System.out.print(show_Elements + " ");
		}
		System.out.println();
		
		System.out.println("Find Index of an Element: ");
		int find_Element = scan.nextInt();
		
		for (int item : array) {
			if (find_Element == item) {
				found = true;
				System.out.println(found);
				break;
			} else {
				System.out.println(found);
				break;
			}
			
		}
		
	}

}
