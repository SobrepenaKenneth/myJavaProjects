package Midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Meralco {
	public static void main(String[]args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		char choice;
		char paymentChoice;
		int kilowatt;
		double bill = 0;
		
		System.out.println("==============================");
		System.out.println("--------- > Meralco < --------");
		System.out.println("--> Enter Consumption Type <--");
		System.out.println("-----> (R) - Residential <----");
		System.out.println("-----> (C) - Commercial <-----");
		System.out.println("------------------------------");
		System.out.print("> Enter Letter Choice: "   );
		choice = reader.readLine().toUpperCase().charAt(0);
		System.out.println("------------------------------");
		
		// > Consumption Choice
		switch (choice) {
		case 'R' :
			System.out.println("--> You picked Residential <--");
			System.out.println("------------------------------");
			System.out.print("> Enter Killowatt-hours: ");
			kilowatt = Integer.parseInt(reader.readLine());
			System.out.println("------------------------------");
			
			// > Process 
			if (kilowatt < 200) {
				bill = kilowatt * 7;
			} else {
				bill = kilowatt * 5;
			}
			break;
			
		case 'C' :
			System.out.println("--> You picked Commercial <--");
			System.out.println("------------------------------");
			System.out.print("> Enter Killowatt-hours: ");
			kilowatt = Integer.parseInt(reader.readLine());
			System.out.println("------------------------------");
			
			if (kilowatt < 500) {
				bill = kilowatt * 15;
			} else {
				bill = kilowatt * 10;
			}
			break;
			
		default :
			System.out.println("-------> Invalid Input <-------");
			System.out.println("-------------------------------");
			break;
		}// Consumption Choice
		
		// > Payment Method
		System.out.println("=============================");
		System.out.println("---> Enter Payment Method <--");
		System.out.println("--> (O) - Online Payment < --");
		System.out.println("---> (C) - Cash Payment < ---");
		System.out.println("-----------------------------");
		System.out.print("> Enter Letter Choice: "   );
		paymentChoice = reader.readLine().toUpperCase().charAt(0);
		System.out.println("------------------------------");
		
		switch (paymentChoice) {
		case 'O' :
			System.out.println("> You picked Online Payment <");
			bill -= bill * 0.05; // apply 5% discount
			
			if (bill > 10000) {
				bill += bill * 0.02; // add 2% supercharge
			}
			System.out.println("> Your Total Bill: ₱" + bill);
			System.out.println("------------------------------");
			break;
			
		case 'C' :
			System.out.println("> You picked Online Payment <");
			if (bill > 10000) {
				bill += bill * 0.02; // add 2% supercharge
			}
			System.out.println("> Your Total Bill: ₱" + bill);
			System.out.println("------------------------------");
			break;
			
		default :
			System.out.println("-------> Invalid Input <-------");
			System.out.println("-------------------------------");
			break;
		} // Payment Coice
		System.out.println("=============================");
	}
}
