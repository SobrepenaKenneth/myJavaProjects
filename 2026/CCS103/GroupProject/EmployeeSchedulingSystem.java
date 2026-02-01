package GroupProject;

import java.util.Scanner;

/**
 * Version 0.3 palitan tong version everytime na mag edit po kayo! 
 * Always refresh!!
 * 
 * We will assist as much as we can!
 */
public class EmployeeSchedulingSystem {
	/**
	 * Properties: This are the variables that will be used throughout the
	 * software
	 */
	private static Scanner scan = new Scanner(System.in);

	// user input used in Menu
	private static int input = 0;

	public static void main(String[] args) {
		System.out.println("===================================================");
		System.out.println("          MALL EMPLOYEE SCHEDULING SYSTEM          ");
		System.out.println("===================================================");
		System.out.println("Legend: M = Morning | A = Afternoon | E - Evening");

		System.out.println("\n      <<Press Enter to Begin Setup Process>>");
		scan.nextLine();

		menuController();

	}

	/*
	 * REQUIRED METHOD Assigned to : Abo-Abo ver - 0
	 */
	public static void displaySchedule() {
		// Menu test delete nalang
		System.out.println("Display Schedule test");
	}

	/*
	 * REQUIRED METHOD Assigned to : Paz ver - 0
	 */
	public static void assignEmployee() {
		// Menu test delete nalang
		System.out.println("Assign Employee test");
	}

	/*
	 * REQUIRED METHOD Assigned to : Gabriel ver - 0
	 */
	public static void validateInput() {

	}

	/*
	 * REQUIRED METHOD Assigned to : Cairo ver - 0
	 */
	public static void checkDuplicateEmployee() {

	}

	/*
	 * REQUIRED METHOD Assigned to : ? ver - 0
	 */
	public static void updateOrRemoveEmployee() {
		// Menu test delete nalang
		System.out.println("Update / Remove Employee test");
	}

	/*
	 * REQUIRED METHOD Assigned to : Galasao ver - 0
	 */
	public static void searchEmployee() {
		// Menu test delete nalang
		System.out.println("Search Employee test");
	}

	/*
	 * REQUIRED METHOD Assigned to : Granada ver - 0
	 */
	public static void calculateWorkingHours() {

	}

	/*
	 * REQUIRED METHOD Assigned to : Ken ver - 1
	 */
	public static void menuController() {
		System.out.println("===================================================");
		System.out.println("\t\t     MAIN-MENU");
		System.out.println("===================================================");
		System.out.println("[1] View Employee Schedule");
		System.out.println("[2] Assign Employee to a Shift");
		System.out.println("[3] Update / Remove Employee");
		System.out.println("[4] Search Employee");
		System.out.println("[5] Exit");

		System.out.print("\nEnter your Choice: ");
		input = scan.nextInt();
		
		System.out.println();

		switch (input) {
		case 1:
			displaySchedule();
			break;
		case 2:
			assignEmployee();
			break;
		case 3:
			updateOrRemoveEmployee();
			break;
		case 4:
			searchEmployee();
			break;
		case 5:
			exit();
			break;

		}
	}// menuController() method

	/*
	 * REQUIRED METHOD Assigned to : ? ver - 0
	 */
	public static void dailySummaryReport() {

	}

	public static void exit() {
		// Menu test delete nalang
		System.out.println("Thank you for using the Mall Employee Scheduling System!");
		System.out.println("Program Terminated");
	}// exit() method
}
