package methods_OnlineClass;

import java.util.Scanner;

public class Methods {
	// method (entry point)
	public static void main(String[] args) {
		// is a block of code designed to perform a specific task.
		Methods object = new Methods(); // instantiation
		object.greetUser(); // method call or method invocation
		
		sayHello(); // noticed that static are italicize
		computeSum();
		
		int result = computeDifference(); // Catch
		// OUTPUT
		System.out.println("The difference is " + result);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("> DIVISION <");
		int num1, num2;
		// INPUT
		System.out.print("Enter the first number: ");
		num1 = scan.nextInt();
		System.out.print("Enter the second number: ");
		num2 = scan.nextInt();
		
		computeQuotient(num1, num2);
		
		int result2 = computeProduct(10, 20);
		System.out.println("The product is: " + result2);
		
		scan.close();
	}// end of main() method 
	
	// > user-defined methods
	// Syntax: access modifier (visibility) / reurn value / methodName(param)
	// lower camel case (VERB) Action word
	// (INSTANCE METHOD) need an object of main
	public void greetUser() {
		// method body
		System.out.println("Dangal Greetings!");
	}// end of greetUser()
	
	// static method
	public static void sayHello() {
		// method body
		System.out.println("Hello World!");
	}// end of sayHello()
	
	// w/o Parameter, w/o Return Value
	public static void computeSum() {
		Scanner scan = new Scanner(System.in);
		System.out.println("> ADDITION <");
		int num1, num2, sum = 0;
		// INPUT
		System.out.print("Enter the first number: ");
		num1 = scan.nextInt();
		System.out.print("Enter the second number: ");
		num2 = scan.nextInt();
		// PROCESS
		sum = num1 + num2;
		// OUTPUT
		System.out.println("The sum is " + sum);
		scan.close();
	}// end of computeSum()
	
	// w/o Parameter, w/Return Value
	// JAVA CAN'T RETURN MORE THAN ONE VALUE
	public static int computeDifference() {
		Scanner scan = new Scanner(System.in);
		System.out.println("> SUBTRACTION <");
		int num1, num2, difference = 0;
		// INPUT
		System.out.print("Enter the first number: ");
		num1 = scan.nextInt();
		System.out.print("Enter the second number: ");
		num2 = scan.nextInt();
		// PROCESS
		difference = num1 + num2;
		scan.close();
		return difference;
	}// end of computeDifference()
	
	// w/ Parameter, w/o Return Value
	public static void computeQuotient(int num1, int num2) {
		// PROCESS
		int quotient = num1 / num2;
		// OUTPUT
		System.out.println("The quotient is " + quotient);
	}// end of computeQuotient()
	
	// w/ Parameter, w/ Return Value
	public static int computeProduct(int num1, int num2) {
		// PROCESS
		int product = num1 * num2;
		return product;
	}// end of computeProduct()
}// end of Main class
