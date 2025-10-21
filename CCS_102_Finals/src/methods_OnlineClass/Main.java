package methods_OnlineClass;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// I can recall this one in codechum but in this one
		// it's number instead of word
		String num, reverse;
		while (true) {
			System.out.print("Enter a number: ");
			num = scan.nextLine();
			
			reverse = new StringBuilder(num).reverse().toString();
			
			if (num.equals(reverse)) {
				System.out.println("The number is a palindrome");
			} else {
				System.out.println("The number is not a palindrome");
			}
		}

	}

}
