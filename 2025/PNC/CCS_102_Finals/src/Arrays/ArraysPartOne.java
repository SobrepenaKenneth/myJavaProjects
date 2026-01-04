package Arrays;

import java.util.Scanner;

public class ArraysPartOne {
	public static void main(String[] args) {
		// ===== ARRAYS =====
		// is a data structure used to store multiple variable
		// Finals [24/10/2007] lecture
		Scanner scan = new Scanner(System.in);
		String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};// String 
		int[] numbers = {1,2,3,4,5};// Integer
		int sum = 0;
		
		// access the elements
		System.out.println(cars[1] + ", " + cars[3]);
		System.out.println(numbers[2]);
		System.out.println();
		
		// change the value of array
		System.out.println("BEFORE: " + cars[0]);
		cars[0] = "Honda";
		System.out.println("AFTER: " + cars[0]);
		cars[0] = cars[3];
		System.out.println("INDEX[0]: " + cars[0]);
		System.out.println("INDEX[3]: " + cars[3]);
		System.out.println();
		
		// lenght of an array
		System.out.println("Size of cars array: " +  cars.length);
		System.out.println("Size of numbers array: " +  cars.length);
		System.out.println();
		
		// defining empty array & filling values
		String[] softdrinks = new String[4];
		softdrinks[0] = "Coke";
		softdrinks[1] = "Sprite";
		softdrinks[2] = "Pepsi";
		softdrinks[3] = "Cobra";
		System.out.println(softdrinks[0]);
		
		// defining empty array
		String[] student = new String[4];// size
//		System.out.println(names[0]);// null
		
		// Loop through array
		student[0] = "Kenneth";
		student[1] = "Goku";
		student[2] = "Vegeta";
		student[3] = "Gohan";
		
		for (int index = 0; index < student.length; index++) {
			System.out.println("|" + index + "| = " + student[index]);
		}
		
		System.out.println();
		
		// reverse
		for (int index = student.length - 1; index >= 0; index--) {
			System.out.println("|" + index + "| = " + student[index]);
		}
		
		System.out.println();
		
		for (int index = 0; index < cars.length; index++) {
			if (cars[index] != null) {
				System.out.println("|" + index + "| = " + cars[index]);
			}
		}
		
		System.out.println();
		
		for (int index = 0; index < numbers.length; index++) {
			sum += numbers[index];
		}
		System.out.println("Sum: " + sum);
		System.out.println();
		/**0 + 1 = 1
		 * 1 + 2 = 3
		 * 3 + 3 = 6
		 * 6 + 4 = 10
		 * 10 + 5 = 15
		 * */
		
		// > for each <
		for (int element : numbers) {
			System.out.println(element);
		}
		System.out.println();
		
		// Dynamic user input
		int[] age;
		int size = 0;
		
		// ask the user for the size
		System.out.print("Enter the array size: ");
		size = scan.nextInt();
		
		// Initialize the array size
		age = new int[size];
		
		System.out.println("Array size: " + age.length);
		
		// INPUT
		System.out.println("\nEnter " + size + " array values:");
		for (int index = 0; index < age.length; index++) {
			age[index] = scan.nextInt();
		}
		
		// OUTPUT
		System.out.println("\nArray values:");
		for(int index = 0; index < age.length; index++) {
			System.out.println("|" + index + "| = " + age[index]);
		}
		
	}// main method
}// Main class
