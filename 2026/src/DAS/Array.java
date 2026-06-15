package DAS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array {

	public static void main(String[] args) throws IOException {
		//arrayBasics();
		//accessingValue();
		//readingInputs();
//		linearSearch();
		linearSearch2();
	}
	
	public static void arrayBasics() {
		// Initializing an array
		
		int[] numbers = {1, 2, 3, 4, 5};
		
		String[] names = {"Kenneth" , "Felonia" , "Kyle"};
		
		for (int num : numbers) {
			System.out.print(num + ", ");
		}
		
		System.out.println();
		
		for (String name : names) {
			System.out.print(name + ", ");
		}
	}
	
	public static void accessingValue() {
		String[] names = new String[5];
		
		names[0] = "Kenneth";
		names[1] = "Kyle";
		names[2] = "Felonia";
		names[3] = "Losamia";
		names[4] = "Paz";
		
		System.out.println("Element at index 0: " + names[0]);
		System.out.println("Element at index 1: " + names[1]);
		System.out.println("Element at index 2: " + names[2]);
		System.out.println("Element at index 3: " + names[3]);
		System.out.println("Element at index 4: " + names[4]);
	}
	
	public static void readingInputs() throws IOException {
		// BufferedReader Objects
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String[] names = new String[5];
		
		for (int index = 0; index < names.length; index++) {
			System.out.print("Enter a name: ");
			names[index] = reader.readLine();
		}
		
		for (int index = 0; index < names.length; index++) {
			System.out.println("Element at index " + index + " : " + names[index]);
		}
	}
	
	// Searching a value
	
	// Linear Search attempt
	// i don't much about it but let's say we have 10 numbers and we are going to look
	// for a specific number
	public static void linearSearch() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int[] numberList = {2, 5, 6, 4, 3, 8, 9, 7, 10, 12, 54, 67};
		
		boolean found = false;	
		
		System.out.print("Search number: ");
		int number = Integer.parseInt(reader.readLine());
		
		for (int i = 0; i < numberList.length; i++) {
			if (number == numberList[i]) {
				System.out.println("Number found at index " + i + ": " + number);
				found = true;
				break;
			}
		}
		
		if (!found) {
			System.out.println("Number " + number + " is not in the array!");
		}
	}
	
	// Big 0(n) Linear Search
	
	public static void linearSearch2() throws IOException {
		// The size of an array
		int size = 0;
		
		// The number to be searched
		int numToSearch;
		
		// if true, it means that numToSearch has been found
		boolean found = false;
		boolean error = false;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// Set the size of the array
		try {
			System.out.print("Enter the size of the array: ");
			size = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Enter an integer only!");
			error = true;
		}
		
		if (error == true) {
			System.out.println("Please try again!");
		} else {
			// Crate the array
			int[] array = new int[size];
			
			// assign a value to the array
			for (int i=0; i < size; i++) {
				System.out.print("Enter a number: ");
				array[i] = Integer.parseInt(reader.readLine());
			}
			
			// set a value to numSearch
			System.out.print("Enter the number to be searched: ");
			numToSearch = Integer.parseInt(reader.readLine());
			
			// apply linear search algorithm
			for (int i=0; i<size; i++) {
				if (numToSearch == array[i]) {
					found = true;
					break;
				}
			}
			
			if (found == true) {
				System.out.println("The number is present in the array!");
			} else {
				System.out.println("The number is not present in the array!");
			}
		}
	}
}
