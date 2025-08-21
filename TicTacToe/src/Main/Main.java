package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String top1 = "n", top2 = "n", top3 = "n";
		String middle1 = "n", middle2 = "n", middle3 = "n";
		String bottom1 = "n", bottom2 = "n", bottom3 = "n";	
		
		int topRow = 3;
		int middleRow = 3;
		int bottomRow = 3;
		int objects = 2;
		int columns = 3;
		
		int userRowInput = 0;
		int userObjectInput1 = 0;
		int userObjectInput2 = 0;
		
		// array starts from 0
		String[] topSection = new String[3];
		topSection[0] = top1;
		topSection[1] = top2;
		topSection[2] = top3;
		
		String[] middleSection = new String[3];
		middleSection[0] = top1;
		middleSection[1] = top2;
		middleSection[2] = top3;
		
		String[] bottomSection = new String[3];
		bottomSection[0] = top1;
		bottomSection[1] = top2;
		bottomSection[2] = top3;
				
		System.out.println(" Tic-Tac-Toe");
		System.out.println("-------------");
		System.out.println("| "+ top1 + " | " + top2 + " | " + top3 + " | [1] - Top");
		System.out.println("-------------");
		System.out.println("| "+ middle1 + " | " + middle2 + " | " + middle3 + " | [2] - Middle");
		System.out.println("-------------");
		System.out.println("| "+ bottom1 + " | " + bottom2 + " | " + bottom3 + " | [3] - Bottom");
		System.out.println("-------------");
		
		// Problem: user will pick X or ) using the number index
		System.out.println("- [1] X or O [2] -");
		System.out.print("Player 1 pick one: ");
		userObjectInput1 = Integer.parseInt(reader.readLine());

		
		//Checker of user picked
		if (userObjectInput1 >= 3) {
			System.out.println("Only pick the number inside the index!");
			System.out.print("Player 1 pick one: ");
			userObjectInput1 = Integer.parseInt(reader.readLine());
		} else if (userObjectInput1 < 0) {
			System.out.println("Only pick the number inside the index!");
			System.out.print("Player 1 pick one: ");
			userObjectInput1 = Integer.parseInt(reader.readLine());
		} else {		

		}
		
		if (userObjectInput1 == 1) {
			System.out.println("Player 1 picked X");
		} else if (userObjectInput1 == 2){
			System.out.println("Player 1 picked O");
		} else {

		}
		
		System.out.print("Player 2 pick one: ");
		userObjectInput1 = Integer.parseInt(reader.readLine());

		
		//Checker of user picked
		if (userObjectInput2 > columns) {
			System.out.println("Only pick the number inside the index!");
			System.out.print("Player 2 pick one: ");
			userObjectInput2 = Integer.parseInt(reader.readLine());
		} else if (userObjectInput2 < 0) {
			System.out.println("Only pick the number inside the index!");
			System.out.print("Player 2 pick one: ");
			userObjectInput2 = Integer.parseInt(reader.readLine());
		} else {		

		}
		
		// Problem: to what row
		System.out.print("Pick where you wanna place: ");
		userRowInput = Integer.parseInt(reader.readLine());
		
		// this only check for breaking the rules to where you wanna place
		if (userRowInput > columns) {
			System.out.println("Only pick the number inside the index!");
			System.out.print("Pick where you wanna place: ");
			userRowInput = Integer.parseInt(reader.readLine());
		} else if (userRowInput <= 0){
			System.out.println("Only pick the number inside the index!");
			System.out.print("Pick where you wanna place: ");
			userRowInput = Integer.parseInt(reader.readLine());
		} else {
			System.out.println("You picked " + userRowInput + "!");		
		}
		
		// Check which row the user picked
		if (userRowInput == 1) {
			System.out.println("");
		}
		
	}

}
 