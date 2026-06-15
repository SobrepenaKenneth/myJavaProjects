package DAS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array {

	public static void main(String[] args) throws IOException {
		//arrayBasics();
		//accessingValue();
		//readingInputs();
		linearSearch();

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
			System.out.println("Enter a name: ");
			names[index] = reader.readLine();
		}
		
		for (int index = 0; index < names.length; index++) {
			System.out.println("Element at index " + index + " : " + names[index]);
		}
	}
	
	// Linear Search attempt
	// i don't much about it but let's say we have 10 numbers and we are going to look
	// for a specific number
	public static void linearSearch() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int[] numberList = {2, 5, 6, 4, 3, 8, 9, 7, 10, 12, 54, 67};
		
		int index = 0;
		int number = 0;
		
		System.out.println("Search number: ");
		number = Integer.parseInt(reader.readLine());
		
		for (int i = 0; i < numberList.length; i++) {
			if (number != numberList[i]) {
				index++;
				continue;
			} else {
				System.out.println("Number found at " + index + " index: " + number);
			}
		}
	}
}
