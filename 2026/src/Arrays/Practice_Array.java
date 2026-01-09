package Arrays;
import java.util.Arrays;

/**
 * Array Practice
 * 09/01/2026
 */
public class Practice_Array {
	public static void main(String[] args) {
		double[] myList = {1.9, 2.9, 3.4, 3.5};
		
		System.out.println("=:> Print all the array elements <:=");
		
		System.out.println("Arrays size: " + myList.length);
		
		// Print all the array elements
		for (int index = 0; index < myList.length; index++ ) {
			System.out.println(myList[index] + " ");
		}
		
		
		System.out.println("\n=:> Summing all elements <:=");
		
		// Summing all elements
		double total = 0;
		
		for (int index = 0; index < myList.length; index++) {
			total += myList[index];
		}
		System.out.println("Total is " + total);
		
		System.out.println("\n=:> Finding the largest element <:=");
		double max = 0;
		
		for (int index = 0; index < myList.length; index++) {
			if (myList[index] > max) max = myList[index];
		}
		
		System.out.println("Max is " + max);
		
		System.out.println("\n=:> For Each Loop <:=");
		for (double element: myList) {
			System.out.println(element);
		}
		
		System.out.println("\n=:> Finding the largest element <:=");
		
		// PrintArray method
		printArray(new int[] {3,1,2,3,6,7});
		
		System.out.println("\n=:> Returning an Array from a Method <:=");
		// Revers method
		int[] reversed = reverse(new int[] {2,4,6,8,10});
		System.out.println(Arrays.toString(reversed));
		
	}// Main Method
	
	// Passing Arrays to Methods
	public static void printArray(int[] array) {
		for(int index = 0; index < array.length; index++) {
			System.out.print(array[index] + " ");
		}
	}// PrintArray Method
	
	//Returning an Array from a Method
	public static int[] reverse(int[] list) {
		int[] result = new int[list.length];
		
		for (int front = 0, back = result.length - 1; front < list.length; front++, back--) {
			result[back] = list[front];
		}
		return result;
	}
}
