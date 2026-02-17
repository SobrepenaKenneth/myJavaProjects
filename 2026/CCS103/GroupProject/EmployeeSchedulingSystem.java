package GroupProject;

import java.util.Scanner;

/**
 * MALL EMPLOYEE SCHEDULING SYSTEM Version: 3.0.2
 * 
 * A scheduling system for mall employees that allows: - Viewing employee
 * schedules - Assigning employees to shifts - Updating/removing employee
 * records - Searching for employees - Generating daily summary reports
 * 
 * This program strictly uses only primitive arrays and no advanced Java
 * libraries as per requirements (no ArrayList, Collections, etc.)
 */
public class EmployeeSchedulingSystem {
	// =====================================================================================
	// PROPERTIES / CLASS VARIABLES
	// =====================================================================================

	// Scanner object for reading user input
	private static Scanner scan = new Scanner(System.in);

	// Array of days names for display and indexing (6 days: MONDAY to SATURDAY)
	private static String daySlotHeader[] = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };

	/**
	 * Array of shift codes for display and indexing (M=Morning, A=Afternoon,
	 * E=Evening)
	 */
	private static String shiftSlotHeader[] = { "M", "A", "E" };

	// Changed this to jagged to meet requirements v3.0.2
	private static String mallSchedule[][][] = {
		    { new String[3], new String[2], new String[4] }, // Monday
		    { new String[2], new String[3], new String[3] }, // Tuesday
		    { new String[4], new String[2], new String[2] }, // Wednesday
		    { new String[3], new String[3], new String[3] }, // Thursday
		    { new String[2], new String[4], new String[3] }, // Friday
		    { new String[3], new String[2], new String[2] }, // Saturday
		};


	// User's menu choice input
	private static int input = 0;

	// =====================================================================================
	// MAIN METHOD - PROGRAM ENTRY POINT
	// =====================================================================================

	/**
	 * Main entry point of the program. Displays welcome banner, waits for user to
	 * press Enter, then enters infinite loop showing schedule and menu.
	 */
	public static void main(String[] args) {
		// Display program header
		System.out.println("=====================================================================================");
		System.out.println("                           MALL EMPLOYEE SCHEDULING SYSTEM                           ");
		System.out.println("=====================================================================================");
		System.out.println("\n                       <<Press Enter to Begin Setup Process>>");
		scan.nextLine(); // Wait for user to press Enter

		// Infinite loop - program runs until user chooses to exit
		while (true) {
			menuController(); // Show menu and handle user choice
		}
	}// main() method

	// =====================================================================================
	// DISPLAY METHODS
	// =====================================================================================

	/**
	 * DISPLAY SCHEDULE METHOD
	 * 
	 * This method Displays the complete employee schedule in a table. Also
	 * calculates and displays total employees per day.
	 */
	public static void displaySchedule() {
		int spaceLength = 0; // Controls spacing between columns

		// ===================== HEADER SECTION =====================
		System.out.println("=====================================================================================");
		System.out.println("                                 EMPLOYEE SCHEDULE                                   ");
		System.out.println("=====================================================================================");
		System.out.println("DAY        |   MORNING(M)               AFTERNOON(A)             EVENING(E)");
		System.out.println("-------------------------------------------------------------------------------------");

		// ===================== MAIN SCHEDULE DISPLAY =====================
		// Loop through each day (0 = MONDAY, 1 = TUESDAY, etc.)
		for (int day = 0; day < mallSchedule.length; day++) {

			int maxRows = 1; // Track how many rows needed for this day (stacked employees)

			// ------------------------------------------------------------
			// STEP 1: Calculate maximum employees in any shift for this day
			// ------------------------------------------------------------
			for (int shift = 0; shift < mallSchedule[day].length; shift++) {

				int count = 0; // Count employees in current shift

				// Count non-null employees in this shift
				for (int emp = 0; emp < mallSchedule[day][shift].length; emp++) {
					if (mallSchedule[day][shift][emp] == null)
						continue; // Skip empty slots
					count++;
				}

				// Update maxRows if this shift has more employees than previous shifts
				if (count > maxRows) {
					maxRows = count;
				}
			}

			// ------------------------------------------------------------
			// STEP 2: Print stacked rows for this day
			// Each row shows one employee per shift (or dash if no employee)
			// Spacing Fixed by: Paz
			// ------------------------------------------------------------
			for (int row = 0; row < maxRows; row++) {
				// Print day name only on first row, otherwise print blank space
				if (row == 0) {
					System.out.print(daySlotHeader[day]); // Print day name
					// Add padding to align columns (day names have different lengths)
					for (int space = 1; space <= 11 - daySlotHeader[day].length(); space++) {
						System.out.print(" ");
					}
					System.out.print("|   ");
				} else {
					System.out.print("           |   "); // 11 spaces + | + 3 spaces = 14 spaces total
				}

				// Loop through each shift (MORNING, AFTERNOON, EVENING)
				for (int shift = 0; shift < mallSchedule[day].length; shift++) {

					int index = 0;
					String output = " ";

					// Find which employee to display in this row
					for (int emp = 0; emp < mallSchedule[day][shift].length; emp++) {

						// If slot is empty, display dash (-)
						if (mallSchedule[day][shift][emp] == null) {
							output = "-";
							spaceLength = 25 - output.length();
							continue;
						}

						// If this employee index matches current row, display them
						if (index == row) {
							output = mallSchedule[day][shift][emp]; // Get assigned employee
							spaceLength = 25 - output.length(); // Calculate padding
							break;
						}
						index++;
					}

					// Print the employee (or dash) and add padding
					System.out.print(output);
					for (int space = 1; space <= spaceLength; space++) {
						System.out.print(" ");
					}
				}

				System.out.println(); // New line after each row
			}

			// Divider after each day
			System.out.println("-------------------------------------------------------------------------------------");
		}

		// ===================== TOTAL EMPLOYEES PER DAY =====================
		System.out.println("\nTOTAL EMPLOYEES PER DAY:");

		// Calculate and display total employees for each day
		for (int day = 0; day < mallSchedule.length; day++) {

			int total = 0;

			// Sum all non-null employees across all shifts for this day
			for (int shift = 0; shift < mallSchedule[day].length; shift++) {
				for (int emp = 0; emp < mallSchedule[day][shift].length; emp++) {
					if (mallSchedule[day][shift][emp] == null)
						continue;
					total++;
				}
			}

			// Display total 
			System.out.println("\u2022 " + daySlotHeader[day] + ": " + total);
		}
		System.out.println();
	}// > displaySchedule() method

	// =====================================================================================
	// MENU CONTROLLER METHODS
	// =====================================================================================

	/**
	 * MAIN MENU CONTROLLER METHOD
	 * Displays the main menu options and routes user to appropriate functionality
	 */
	public static void menuController() {
		// Display menu header
		System.out.println("=====================================================================================");
		System.out.println("                                    MAIN-MENU");
		System.out.println("=====================================================================================");
		System.out.println("[1] View Employee Schedule");
		System.out.println("[2] Assign Employee to a Shift");
		System.out.println("[3] Update / Remove Employee");
		System.out.println("[4] Search Employee");
		System.out.println("[5] Daily Summary Report");
		System.out.println("[6] Total Working Hours Per Employee");
		System.out.println("[7] Exit");
		System.out.print("\nEnter your Choice: ");

		// Get user input with error handling
		try {
			input = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			input = 0; // Invalid input will trigger default case
		}
		System.out.println();

		// Route user based on choice
		switch (input) {
		case 1:
		    displaySchedule();
		    System.out.println("Press Enter to return to Main Menu...");
		    scan.nextLine();
			break;
		case 2:
			assignEmployee(); // Assign new employee to shift
			break;
		case 3:
			subMenu(); // Show update/remove submenu
			break;
		case 4:
			searchEmployee(); // Search for employee
			break;
		case 5:
			dailySummaryReport(); // Show daily summary
			break;
		case 6:
			displayTotalHoursPerEmployee(); // Show total hours per employee v3.0.2
			break;
		case 7:
			exit(); // Exit program
			break;
		default:
			System.out.println("> Invalid Input!");
		}
	}// > menuController() method

	// =====================================================================================
	// ASSIGN EMPLOYEE METHODS
	// =====================================================================================

	/**
	 * ASSIGN EMPLOYEE METHOD
	 * 
	 * Handles the process of assigning an employee to a specific day and shift
	 */
	public static int assignEmployee() {

		int shiftIndex, dayIndex, assignIndex = 0;

		// Display header
		System.out.println("=====================================================================================");
		System.out.println("                                 ASSIGN EMPLOYEE");
		System.out.println("=====================================================================================");

		// --- STEP 1: Get Day from user ---
		dayIndex = dayValidation();

		// --- STEP 2: Get Shift from user ---
		shiftIndex = shiftValidation();

		// --- STEP 3: Get Employee Details ---
		System.out.print("Enter Employee ID: ");
		String empID = scan.nextLine();

		System.out.print("Enter Employee Name: ");
		String empName = scan.nextLine();

		System.out.print("Enter Role: ");
		String empRole = scan.nextLine();

		// Format: "ID - Name (Role)"
		String entry = empID + " - " + empName + " (" + empRole + ")";

		// Flag to control assignment loop
		boolean assignAgain = true;

		// Main assignment loop
		while (assignAgain) {

			boolean assigned = false;

			// --- STEP 4: Check for duplicate employee on same day ---
			String duplicateValue = dupeChecker(empID + " -", dayIndex);

			// If no duplicate found, proceed with assignment
			if (duplicateValue == null) {

				// Find first empty slot in the selected day/shift
				for (int i = 0; i < mallSchedule[dayIndex][shiftIndex].length; i++) {

					// Check if slot is empty (null or placeholder "-")
					if (mallSchedule[dayIndex][shiftIndex][i] == null
							|| mallSchedule[dayIndex][shiftIndex][i].equals("-")) {

						// Assign employee to this slot
						mallSchedule[dayIndex][shiftIndex][i] = entry;

						// Success message
						System.out.println(
								"-------------------------------------------------------------------------------------");
						System.out.println("> " + empID + " - " + empName + " was successfully assigned to "
								+ shiftSlotHeader[shiftIndex] + " shift on " + daySlotHeader[dayIndex] + ".");

						assigned = true;
						break;
					}
				}

				// If no empty slot found
				if (!assigned) {
					System.out.println("> ERROR: All " + shiftSlotHeader[shiftIndex] + " shift slots on "
							+ daySlotHeader[dayIndex] + " are already filled.");
				}

			} else {
				// Duplicate found - cannot assign same employee twice on same day
				System.out.println(
						"> ERROR: " + duplicateValue + " is already assigned on " + daySlotHeader[dayIndex] + ".");
			}

			// --- STEP 5: Ask if user wants to assign another employee ---
			while (true) {
				System.out.print("\nDo you want to assign again? Y or N: ");
				String choice = scan.nextLine().toUpperCase();

				if (choice.equals("Y")) {
					// Get new day and shift for next assignment
					System.out.println();
					return assignEmployee();

				} else if (choice.equals("N")) {
					System.out.println();
					return 0;
				} else {
					System.out.println("Invalid Input! Please enter Y or N.");
				}
			}
		}
		return assignIndex;
	}// assignEmployee() method

	// =====================================================================================
	// VALIDATION METHODS
	// =====================================================================================

	/**
	 * DAY VALIDATION METHOD
	 * 
	 * Prompts user to enter a day and validates input. Continues asking until valid
	 * day is entered.
	 * 
	 * @return int Index corresponding to selected day (0-5) (Monday(0) to Saturday(5))
	 */
	private static int dayValidation() {
		int dayIndex = -1; // Initialize with invalid value

		while (dayIndex == -1) {
			System.out.print("Enter Day (MON, TUE, WED, THU, FRI, SAT): ");
			String dayInput = scan.nextLine().toUpperCase();

			// Convert 3-letter code to array index
			switch (dayInput) {
			case "MON":
				dayIndex = 0;
				break;
			case "TUE":
				dayIndex = 1;
				break;
			case "WED":
				dayIndex = 2;
				break;
			case "THU":
				dayIndex = 3;
				break;
			case "FRI":
				dayIndex = 4;
				break;
			case "SAT":
				dayIndex = 5;
				break;
			default:
				dayIndex = -1; // Invalid input
			}

			if (dayIndex == -1) {
				System.out.println("Invalid Day! Please enter MON, TUE, WED, THU, FRI, or SAT.");
			}
		}
		return dayIndex;
	}// dayValidation() method

	/**
	 * SHIFT VALIDATION METHOD
	 * 
	 * Prompts user to enter a shift and validates input. Continues asking until
	 * valid shift is entered.
	 * 
	 * @return int Index corresponding to selected shift (0-2) (Morning(0) to Evening(2))
	 */
	private static int shiftValidation() {
		int shiftIndex = -1; // Initialize with invalid value

		while (shiftIndex == -1) {
			System.out.print("Enter Shift:");
			System.out.println("\n    \u2022 [M] Morning");
			System.out.println("    \u2022 [A] Afternoon");
			System.out.println("    \u2022 [E] Evening");
			System.out.print("\nEnter your Choice: ");

			String shiftInput = scan.nextLine().toUpperCase();

			// Convert shift letter to array index
			switch (shiftInput) {
			case "M":
				shiftIndex = 0; // Morning
				break;
			case "A":
				shiftIndex = 1; // Afternoon
				break;
			case "E":
				shiftIndex = 2; // Evening
				break;
			default:
				shiftIndex = -1; // Invalid input
			}

			if (shiftIndex == -1) {
				System.out.println("Invalid Shift! Please enter M, A, or E.");
			}
		}
		return shiftIndex;
	}// shiftValidation() method

	// =====================================================================================
	// EMPLOYEE SEARCH METHOD
	// =====================================================================================

	/**
	 * SEARCH EMPLOYEE METHOD
	 * 
	 * Searches for an employee by ID or name and displays:
	 */
	public static void searchEmployee() {
		// Local arrays for display (full names instead of codes)
		String daySlotHeader[] = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
		String shiftSlotHeader[] = { "MORNING", "AFTERNOON", "EVENING" };

		boolean hasSchedule = false; // track if employee has any assignments

		// Display header
		System.out.println("=====================================================================================");
		System.out.println("                                  SEARCH EMPLOYEE");
		System.out.println("=====================================================================================");

		System.out.print("Enter Employee ID to Search: ");
		String searchTerm = scan.nextLine();

		// Check if employee exists in system
		String matchingUser = dupeChecker(searchTerm);

		if (matchingUser != null) {
			// Employee found - display details
			System.out.println("\n--- Employee Found! ---");

			// Extract and display name 
			System.out.println("Name: "
					+ matchingUser.substring(matchingUser.lastIndexOf("-") + 2, matchingUser.lastIndexOf("(") - 1));

			// Extract and display role 
			System.out.println(
					"Role: " + matchingUser.substring(matchingUser.lastIndexOf("(") + 1, matchingUser.length() - 1));

			System.out.println("-----------------------");
			System.out.println("Current Schedule:");

			int totalHours = 0;

			// Search through entire schedule for this employee
			for (int day = 0; day < mallSchedule.length; day++) {
				for (int shift = 0; shift < mallSchedule[day].length; shift++) {
					for (int slot = 0; slot < mallSchedule[day][shift].length; slot++) {

						// Check if this slot contains our search term
						if (mallSchedule[day][shift][slot] != null
								&& mallSchedule[day][shift][slot].contains(searchTerm)) {

							// Display shift assignment
							System.out.println("> " + daySlotHeader[day] + ", " + shiftSlotHeader[shift] + " shift.");

							// Add hours based on shift type
							if (shift == 0) // Morning: 4 hours
								totalHours += 4;
							else if (shift == 1) // Afternoon: 2 hours
								totalHours += 2;
							else if (shift == 2) // Evening: 6 hours
								totalHours += 6;

							hasSchedule = true;
						}
					}
				}
			}

			if (!hasSchedule) {
				System.out.println("No shifts assigned yet.");
			} else {
				System.out.println("-----------------------");
				System.out.println("> Total Working Hours: " + totalHours);
			}

		} else {
			// Employee not found
			System.out.println("\n Invalid: Employee \"" + searchTerm + "\" not found in the database.");
		}

		// Pause before returning to menu
		System.out.println("\nPress Enter to return to Main Menu...");
		scan.nextLine();
	}// searchEmployee() method

	// =====================================================================================
	// UPDATE/REMOVE SUBMENU
	// =====================================================================================

	/**
	 * UPDATE/REMOVE SUBMENU
	 * 
	 * Displays submenu for update and remove operations
	 */
	public static void subMenu() {
		System.out.println("=====================================================================================");
		System.out.println("                                    UPDATE OR REMOVE EMPLOYEE");
		System.out.println("=====================================================================================");
		System.out.println("[1] Update Employee");
		System.out.println("[2] Remove Employee");
		System.out.print("\nEnter your Choice: ");

		// Get user input with error handling
		try {
			input = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e) {
			input = 0;
		}
		System.out.println();

		// Route to appropriate method
		switch (input) {
		case 1:
			updateEmployee();
			System.out.println("\nPress Enter to continue...");
			scan.nextLine();
			break;
		case 2:
			removeEmployee();
			break;
		default:
			System.out.println("> Invalid Input!");
		}
	}

	// =====================================================================================
	// REMOVE EMPLOYEE METHOD
	// =====================================================================================

	/**
	 * REMOVE EMPLOYEE METHOD
	 * 
	 * Removes employee(s) from schedule based on ID or name search. Searches
	 * through entire schedule and removes ALL matching entries. Provides feedback
	 * on what was removed.
	 */
	public static void removeEmployee() {
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("Enter Name or ID: ");
		String nameForRemoval = scan.nextLine();

		boolean found = false; // Track if any employees were found/removed

		// Search through entire 3D array
		for (int day = 0; day < mallSchedule.length; day++) {
			for (int shift = 0; shift < mallSchedule[day].length; shift++) {
				for (int slot = 0; slot < mallSchedule[day][shift].length; slot++) {

					// Check if this slot contains the search term
					if (mallSchedule[day][shift][slot] != null
							&& mallSchedule[day][shift][slot].toLowerCase().contains(nameForRemoval.toLowerCase())) {

						// Display what's being removed
						System.out.println("Removing: " + mallSchedule[day][shift][slot]);

						// Remove by setting to null
						mallSchedule[day][shift][slot] = null;
						found = true;
					}
				}
			}
		}

		// Provide feedback
		if (found) {
			System.out.println("> Employee(s) removed successfully!");
		} else {
			System.out.println("> No employee found matching '" + nameForRemoval + "'");
		}

		System.out.println("\nPress Enter to continue...");
		scan.nextLine();
	}

	// =====================================================================================
	// UPDATE EMPLOYEE METHOD
	// =====================================================================================

	/**
	 * UPDATE EMPLOYEE METHOD
	 * 
	 * Updates employee's name and/or role throughout the entire schedule. Employee
	 * ID remains the same (used as key). Updates ALL occurrences of the employee
	 * across all days and shifts.
	 */
	public static void updateEmployee() {
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("Enter Employee ID to Update: ");
		String empID = scan.nextLine();

		// Check if employee exists using dupeChecker
		String existing = dupeChecker(empID + " -");

		if (existing == null) {
			System.out.println("> ERROR: Employee not found!");
			return;
		}

		// Display current record
		System.out.println("\nCurrent Record: " + existing);

		// Get new information
		System.out.print("Enter New Employee Name: ");
		String newName = scan.nextLine();

		System.out.print("Enter New Role: ");
		String newRole = scan.nextLine();

		System.out.println();

		// Create updated entry string
		String updatedEntry = empID + " - " + newName + " (" + newRole + ")";

		// Flag to track if update occurred
		boolean updated = false;

		// Search through entire schedule and update ALL occurrences
		for (int day = 0; day < mallSchedule.length; day++) {
			for (int shift = 0; shift < mallSchedule[day].length; shift++) {
				for (int slot = 0; slot < mallSchedule[day][shift].length; slot++) {

					String value = mallSchedule[day][shift][slot];

					if (value != null) {

						// Check if this entry matches our target ID
						boolean match = true;
						String target = empID + " -";

						// If value is shorter than target, it can't match
						if (value.length() < target.length()) {
							match = false;
						} else {
							// Compare character by character (no String.startsWith() allowed)
							for (int x = 0; x < target.length(); x++) {
								if (value.charAt(x) != target.charAt(x)) {
									match = false;
									break;
								}
							}
						}

						// If match found, update the entry
						if (match) {
							mallSchedule[day][shift][slot] = updatedEntry;
							updated = true;
						}
					}
				}
			}
		}

		// Provide feedback
		if (updated) {
			System.out.println("\n> Employee successfully updated!");
		}
	}

	// =====================================================================================
	// DUPLICATE CHECKER METHODS (OVERLOADED)
	// =====================================================================================

	/**
	 * DUPLICATE CHECKER - Full Schedule Search
	 * 
	 * Searches entire schedule for an employee matching the target. Used by
	 * searchEmployee() to find employees.
	 * 
	 * @param target The search string (usually ID or name)
	 * @return The first matching employee entry, or null if not found
	 */
	public static String dupeChecker(String target) {
		// Search through all days
		for (int day = 0; day < mallSchedule.length; day++) {
			// Search through all shifts
			for (int shift = 0; shift < mallSchedule[day].length; shift++) {
				// Search through all slots in this shift
				for (int slot = 0; slot < mallSchedule[day][shift].length; slot++) {

					// Check if slot contains target
					if (mallSchedule[day][shift][slot] != null && mallSchedule[day][shift][slot].startsWith(target)) {

						return mallSchedule[day][shift][slot]; // Return first match found
					}
				}
			}
		}
		return null; // No match found
	}

	/**
	 * DUPLICATE CHECKER - Day-Specific Search
	 * 
	 * Searches only a specific day for an employee matching the target. Used by
	 * assignEmployee() to check for duplicates on same day.
	 * 
	 * @param target   The search string (usually ID)
	 * @param dayIndex The specific day to search
	 * @return The first matching employee entry on that day, or null if not found
	 */
	public static String dupeChecker(String target, int dayIndex) {

		// Search through all shifts on the specified day
		for (int shift = 0; shift < mallSchedule[dayIndex].length; shift++) {
			// Search through all slots in this shift
			for (int slot = 0; slot < mallSchedule[dayIndex][shift].length; slot++) {

				// Check if slot contains target
				if (mallSchedule[dayIndex][shift][slot] != null
						&& mallSchedule[dayIndex][shift][slot].startsWith(target)) {

					return mallSchedule[dayIndex][shift][slot]; // Return first match found
				}
			}
		}
		return null; // No match found on this day
	}

	// =====================================================================================
	// DAILY SUMMARY REPORT METHOD
	// =====================================================================================

	/**
	 * DAILY SUMMARY REPORT METHOD
	 * 
	 * Generates a summary report showing employee counts per shift for each day.
	 */
	public static void dailySummaryReport() {
		System.out.println("=====================================================================================");
		System.out.println("                              DAILY SUMMARY REPORT");
		System.out.println("=====================================================================================");

		// Loop day
		for (int day = 0; day < mallSchedule.length; day++) {
			System.out.println("\n" + daySlotHeader[day] + ":");

			// Loop shift
			for (int shift = 0; shift < mallSchedule[day].length; shift++) {
				int count = 0;
				System.out.print("  " + shiftSlotHeader[shift] + ": ");

				// Count employees in this shift
				for (int emp = 0; emp < mallSchedule[day][shift].length; emp++) {
					if (mallSchedule[day][shift][emp] != null) {
						count++;
					}
				}
				System.out.println(count + " employees");
			}
		}

		// Pause before returning to menu
		System.out.println("\nPress Enter to return to Main Menu...");
		scan.nextLine();
	}

	public static void displayTotalHoursPerEmployee() {

		System.out.println("=====================================================================================");
		System.out.println("                    TOTAL WORKING HOURS PER EMPLOYEE");
		System.out.println("=====================================================================================");

		// Temporary arrays to store employees and hours
		String employees[] = new String[100]; // max possible entries
		int hours[] = new int[100];

		int count = 0;

		// Loop entire schedule
		for (int day = 0; day < mallSchedule.length; day++) {
			for (int shift = 0; shift < mallSchedule[day].length; shift++) {
				for (int slot = 0; slot < mallSchedule[day][shift].length; slot++) {

					String value = mallSchedule[day][shift][slot];

					if (value != null) {

						// Check if employee already recorded
						int existingIndex = -1;

						for (int i = 0; i < count; i++) {
							if (employees[i].equals(value)) {
								existingIndex = i;
								break;
							}
						}

						// Determine shift hours
						int shiftHours = 0;
						if (shift == 0)
							shiftHours = 4;
						else if (shift == 1)
							shiftHours = 2;
						else if (shift == 2)
							shiftHours = 6;

						if (existingIndex == -1) {
							// New employee
							employees[count] = value;
							hours[count] = shiftHours;
							count++;
						} else {
							// Existing employee
							hours[existingIndex] += shiftHours;
						}
					}
				}
			}
		}

		// Display result
		for (int i = 0; i < count; i++) {
			System.out.println("\u2022 " + employees[i] + " \u279C " + hours[i] + " hours");
		}

		if (count == 0) {
			System.out.println("No employees assigned yet.");
		}

		System.out.println("\nPress Enter to return...");
		scan.nextLine();
	}// End of displayTotalHoursPerEmployee

	// =====================================================================================
	// EXIT METHOD
	// =====================================================================================

	/**
	 * EXIT METHOD
	 * Asks user to confirm exit
	 * choice (Y/N). Uses System.exit(0) to terminate the program.
	 */
	public static void exit() {

		while (true) {
			System.out.print("Are you sure you want to exit? Y or N: ");
			String exitInput = scan.nextLine().toUpperCase();

			switch (exitInput) {
			case "Y":
				System.out.println("\nThank you for using the Mall Employee Scheduling System!");
				System.out.println("Program Terminated");
				System.exit(0); // Terminate JVM
				break; 

			case "N":
				System.out.println();
				return;

			default:
				System.out.println("Invalid Input! Please enter Y or N.");
			}
		}
	} // exit() method

} // End of Final class