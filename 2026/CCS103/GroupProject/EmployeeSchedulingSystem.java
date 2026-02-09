package GroupProject;

import java.util.Scanner;

/**
 * Version 0.4 palitan tong version everytime na mag edit po kayo! Always
 * refresh!!
 * 
 * We will assist as much as we can!
 */
public class EmployeeSchedulingSystem {
	/**
	 * Properties: This are the variables that will be used throughout the software
	 */
	private static Scanner scan = new Scanner(System.in);
	private static final int row = 3;
	private static final int column = 6;
	// Arrays for Days [COLUMN]
	private static String daySlotHeader[] = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
	// Arrays for Shifts [ROW]
	private static String shiftSlotHeader[] = { "M", "A", "E" };
	// Arrays for Employees [LAYERS]
	private static String employees[] = { "Kenneth", "Paz", "Diamante" };
	// Arrays for Employees Roles
	private static String employeeRoles[] = { "Homeless", "Cashier", "Security" };
	// Arrays for Mall Schedule
	private static String mallSchedule[][][] = new String[employees.length][shiftSlotHeader.length][(daySlotHeader.length + 1)];

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
		// Menu test delete na lang
		System.out.println("===================================================");
		System.out.println("\t\t EMPLOYEE SCHEDULE");
		System.out.println("===================================================");
		// call the dummy
		assignDummy();
		
		// Test
		for (int cols = 1, assign = 0; assign < daySlotHeader.length; cols++, assign++) {
			System.out.print(mallSchedule[0][0][cols] + " ");
		}
		
	}
	
	// Test
	public static void assignDummy() {
		for (int cols = 1, assign = 0; assign < daySlotHeader.length; cols++, assign++) {
			mallSchedule[0][0][cols] = daySlotHeader[assign];
		}
		
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
