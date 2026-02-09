package ArrayStudy;

import java.util.Scanner;

public class AirplaneSeating {
	private static Scanner scan = new Scanner(System.in);
	private static String[][] airplaneSched = new String[14][7];
	private static String ticket;
	
	public static void main(String[] args) {
		askTicket();
		
	}
	
	public static void askTicket() {
		String ticket;
		
		System.out.println("===== AIRPLANE SEAT =====");
		System.out.println("Ticket :");
		System.out.println("    \u2022  First Class");
		System.out.println("    \u2022  Business Class");
		System.out.println("    \u2022  Economy Class");
		System.out.print("Pick your Ticket: ");
		ticket = scan.nextLine();
		
		switch (ticket) {
		case "First Class" :
			System.out.println();
			break;
		case "Business Class" :
			System.out.println();
			break;
		case "Economy Class" :
			System.out.println();
			break;
		default :
			System.out.println("Error");
			break;
		
		}
	}
	
	public static void airplaneSeats() {
		
	}
}
