package methods_OnlineClass;

import java.util.Scanner;

public class Methods2 {
	public static void main(String[] args) {
		computeSum();
	}
	
	public static int getInput() {
		Scanner scan = new Scanner(System.in);
		int num;
		scan.close();
		// INPUT
		System.out.print("Enter a number: ");
		num = scan.nextInt();
		return num;
	}
	
	public static void computeSum() {
		int num1, num2, sum;
		num1 = getInput();
		num2 = getInput();
		
		sum = num1 + num2;
		displaySum(sum);
	}
	
	public static void displaySum(int sum) {
		System.out.println("The sum is " + sum);
	}
}
