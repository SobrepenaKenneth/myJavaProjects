package DAS;

public class Array {

	public static void main(String[] args) {
		//arrayBasics();
		accessingValue();

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

}
