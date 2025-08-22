package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Class variable
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static int operator;
	public static int confirm;
	public static String[] typeOperator = {"Addition" , "Subtraction", "Multiplication", "Division"};
	public static int num1;
	public static int num2;
	
	// TODO: put a UI design
	public static void greet() throws NumberFormatException, IOException {
		System.out.println("----------------------------");
		System.out.println("     - Calculator - ");
		System.out.println("----------------------------");
		System.out.println("> [0] | + | ADDITION       <");
		System.out.println("> [1] | - | SUBTRACTION    <");
		System.out.println("> [2] | * | MULTIPLICATION <");
		System.out.println("> [3] | / | DIVISION       <");
		System.out.println("----------------------------");
		askOperator();
	}
	
	public static void askOperator() throws NumberFormatException, IOException {
		// Local variable
		boolean valid = false;
		while (!valid) {
			try {
				System.out.print("Select operator 0 to 3: ");
				operator = Integer.parseInt(reader.readLine());
				
				if (operator >= 0 && operator <= 3) {
					valid = true;
				} else if (operator < 0){
					System.out.println("----------------------------");
					System.out.println("     - Calculator - ");
					System.out.println("----------------------------");
					System.out.println("> [0] | + | ADDITION       <");
					System.out.println("> [1] | - | SUBTRACTION    <");
					System.out.println("> [2] | * | MULTIPLICATION <");
					System.out.println("> [3] | / | DIVISION       <");
					System.out.println("----------------------------");
					System.out.println("Please enter a number \nbetween 0 to 3!");	
					System.out.println("----------------------------");
				} else {
					System.out.println("----------------------------");
					System.out.println("     - Calculator - ");
					System.out.println("----------------------------");
					System.out.println("> [0] | + | ADDITION       <");
					System.out.println("> [1] | - | SUBTRACTION    <");
					System.out.println("> [2] | * | MULTIPLICATION <");
					System.out.println("> [3] | / | DIVISION       <");
					System.out.println("----------------------------");
					System.out.println("Please enter a number \nbetween 0 to 3!");
					System.out.println("----------------------------");
				}
			} catch (Exception e) {
				System.out.println("----------------------------");
				System.out.println("     - Calculator - ");
				System.out.println("----------------------------");
				System.out.println("> [0] | + | ADDITION       <");
				System.out.println("> [1] | - | SUBTRACTION    <");
				System.out.println("> [2] | * | MULTIPLICATION <");
				System.out.println("> [3] | / | DIVISION       <");
				System.out.println("----------------------------");
				System.out.println("Please only enter a number \nbetween 0 to 3!");
				System.out.println("----------------------------");
			}			
		}
		System.out.println("----------------------------");
		System.out.println("You choosed " + typeOperator[operator] + "!");	
		
		
		askInput();
	}
	
	public static void askInput() throws NumberFormatException, IOException {
		Addition addNum = new Addition();
		Subtraction subtractNum = new Subtraction();
		Multiplication multiplyNum = new Multiplication();
		//XXX: Apparently you cannot call the constructor in the objects
		Division divideNum = new Division();
		// 0 - addition 1 - subtraction 2 - multiplication - 3 - division
		// TODO: add the operators objects
		// TODO: Finish the case
		switch (operator) {
		case 0:
			System.out.println("----------------------------");
			System.out.print("Enter The First Number: ");
			num1 = Integer.parseInt(reader.readLine());
			System.out.println("----------------------------");
			System.out.print("Enter The Second Number: ");
			num2 = Integer.parseInt(reader.readLine());			
			System.out.println("----------------------------");
			System.out.println("-The sum of " + num1 + " and " + num2 + " is!- ");
			System.out.println("        >>> " + addNum.addNumbers(num1, num2) + " <<<");
			break;
			
		case 1: //TODO: make the input double for division
			System.out.println("----------------------------");
			System.out.print("Enter The First Number: ");
			num1 = Integer.parseInt(reader.readLine());
			System.out.println("----------------------------");
			System.out.print("Enter The Second Number: ");
			num2 = Integer.parseInt(reader.readLine());			
			System.out.println("----------------------------");
			System.out.println("-The difference of " + num1 + " and " + num2 + " is!- ");
			System.out.println("        >>> " + subtractNum.subtractNumbers(num1, num2) + " <<<");
			break;
		case 2:
			System.out.println("----------------------------");
			System.out.print("Enter The First Number: ");
			num1 = Integer.parseInt(reader.readLine());
			System.out.println("----------------------------");
			System.out.print("Enter The Second Number: ");
			num2 = Integer.parseInt(reader.readLine());			
			System.out.println("----------------------------");
			System.out.println("-The product of " + num1 + " and " + num2 + " is!- ");
			System.out.println("        >>> " + multiplyNum.multiplyNumbers(num1, num2) + " <<<");
			break;
		case 3:
			System.out.println("----------------------------");
			System.out.print("Enter The First Number: ");
			num1 = Integer.parseInt(reader.readLine());
			System.out.println("----------------------------");
			System.out.print("Enter The Second Number: ");
			num2 = Integer.parseInt(reader.readLine());			
			System.out.println("----------------------------");
			System.out.println("-The quotient of " + num1 + " and " + num2 + " is!- ");
			System.out.println("        >>> " + divideNum.divideNumbers(num1, num2) + " <<<");
			System.out.println("----------------------------");
			System.out.println("- And the remainder is! - ");
			System.out.println("        >>> " + divideNum.getRemainder(num1, num2) + " <<<");
			break;
		default : 
			askOperator();
			break;
		}
		askUser();
	}
	
	// TODO: ask the user if he wants to use the calculator again
	public static void askUser() throws NumberFormatException, IOException {
		System.out.println("----------------------------");
		System.out.println(">> Do you want to use the << \n    - Calculator Again? -");
		System.out.println("----------------------------");
		System.out.println(" [1] - YES ");
		System.out.println(" [2] - NO ");
		System.out.println("----------------------------");
		boolean valid = false;
		
		while (!valid) {
			try {
				System.out.print("Select option 1 to 2: ");
				confirm = Integer.parseInt(reader.readLine());
				
				if (confirm >= 0 && confirm <= 3) {
					valid = true;
				} else if (confirm < 0){
					System.out.println("Please enter a number \nbetween 1 to 2!");	
				} else {
					System.out.println("Please enter a number \nbetween 1 to 2!");
				}
			} catch (Exception e) {
				System.out.println("----------------------------");
				System.out.println("Please only enter a number between 1 to 2!");
				System.out.println("----------------------------");
			}			
		}
		
		if (confirm == 1) {
			greet();
		} else {
			exit();
		}
	}
	public static void exit() {
		System.out.println("----------------------------");
		System.out.println("- Thank you for using the Calculator! -");
		System.out.println(" >>> Github: Kenneth Sobrepeña / @x40arcade <<<");
		System.out.println("----------------------------");
	}
	
	public static void main(String[] args) throws IOException{
		greet();
	}
}
