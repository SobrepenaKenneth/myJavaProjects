package preLim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name;
		int age;
		int temparature;
		
		System.out.println("-------------------------");
		try {
			System.out.print("Enter your age: ");
			age = Integer.parseInt(reader.readLine());
		} catch (Exception e) {		
			System.out.print("Enter your age: ");
			age = Integer.parseInt(reader.readLine());
		} finally {
			System.out.println("-------------------------");
			System.out.println("Checking your age");
			System.out.println("-------------------------");
		}		
		String message = (age < 18) ? "You are not old enough" 
				: "You are old Enough";		
		System.out.println(message);
		System.out.println("-------------------------");
		System.out.print("Enter your name: ");
		name = reader.readLine();
		System.out.println("Hello " + name + "!");
		System.out.println("You are " + age + "!");
		System.out.println("-------------------------");
		try {
			System.out.print("What is the temparature outside today?: ");
			temparature = Integer.parseInt(reader.readLine());
			// Ternary Operator
			// Pwede iba't ibang condition btw
			String tempCheck = (temparature > 35) ? "Extremely Hot" :
				(temparature > 25) ? "Warm" :
					(temparature > 15) ? "Mild" :
						(temparature > 0) ? "Cold" : "Freezing";
			System.out.println("-------------------------");
			System.out.println("The temparature outside is: " + tempCheck + "!");
		} catch (Exception e) {
			System.out.println("Enter only the number!");
		}
		
		// || - or && - and
		// || - [or] atleast one is true
		// && - [and] both must be true
		// ! - Uno reverse card !True = false !false = true


		
		
		
		

	}
}
