package Lesson6;

import java.util.Scanner;

public class SpecialCharacters {

	public static void main(String[] args) {
		/* 
		 * LESSON 6: Special Characters
		 * by codechum Admin
		 * 
		 * Write a program that takes a character. character, as input and checks if it is a
		 * special character. if the character is neither an alphabet letter nor a digit, it prints
		 * "Character is a special character." Otherwise, it does nothing.
		 * */
		Scanner scan = new Scanner(System.in);
		// > Declaration
		char inputChar;
		
		// > Input
		System.out.println("Enter character: ");
		inputChar = scan.next().charAt(0);
		
		// > Process & Output
		// problem: 1 is getting recognized as a digit same with a
		// Mayber AND instead of OR
		if (!Character.isDigit(inputChar) && !Character.isLetter(inputChar)) {
			System.out.println("Character is a special character.");
		}
		scan.close();
	}
}
