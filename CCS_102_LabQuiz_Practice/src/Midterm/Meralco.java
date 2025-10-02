package Midterm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Meralco {
	public static void main(String[]args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		char choice;
		char choice_Payment;
		int kilowatt;
		double bill = 0;
		double discount;
		int totalBill = 0;
		
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
		}
		
		// > Payment Method
		System.out.println("=============================");
		System.out.println("---> Enter Payment Method <--");
		System.out.println("--> (O) - Online Payment < --");
		System.out.println("---> (C) - Cash Payment < ---");
		System.out.println("-----------------------------");
		System.out.print("> Enter Letter Choice: "   );
		choice_Payment = reader.readLine().toUpperCase().charAt(0);
		System.out.println("------------------------------");
		
		switch (choice_Payment) {
		case 'O' :
			System.out.println("-> You picked Online Payment <-");
			System.out.println("------------------------------");
			discount = bill * 0.05;
			totalBill = (int) (bill - discount);
			if (totalBill > 10000) {
				totalBill = (int) (totalBill * 0.02);
			}
			System.out.println("> Your Total Bill: ₱" + totalBill);
			break;
		case 'C' :
			System.out.println("-> You picked Cash Payment <-");
			System.out.println("------------------------------");
			if (bill > 10000) {
				bill = bill * 0.02;
			}
			System.out.println("> Your Total Bill: ₱" + bill);
			break;
		default :
			System.out.println("-------> Invalid Input <-------");
			System.out.println("-------------------------------");
			break;
		}
		
		System.out.println("=============================");
	}
}
