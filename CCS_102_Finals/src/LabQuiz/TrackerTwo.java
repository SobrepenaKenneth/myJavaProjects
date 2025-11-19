package LabQuiz;

import java.util.Scanner;

/**
 * Smart Campus Resource Utilization and Efficiency Tracker
 * 
 * This system tracks and analyzes resource consumption across multiple campus
 * buildings using 2D arrays for data storage and processing. It provides
 * analytical insights into resource usage patterns and identifies
 * inefficiencies.
 * 
 * Revision Notes: - Revised code and added comments (by ken)
 * 
 * @author GlobalTech Institute
 * @version 26.0
 */
public class TrackerTwo {
	// Scanner for user input throughout the program
	static Scanner sc = new Scanner(System.in);

	// Data storage arrays
	public static String[][] buildings; // Daily building resource data
	public static String[][] overview; // Overview data across all days

	// Tracking variables
	public static int currentDay = 1; // Current day being processed
	public static int maxDays = 0; // Total number of days to track

	// Array index constants for daily data matrix
	public static int averageRow; // Row index for column averages
	public static int totalsRow; // Row index for column totals
	public static int averageColumn; // Column index for row averages
	public static int totalsColumn; // Column index for row totals

	// Array index constants for overview matrix
	public static int overVAverageRow; // Overview row for averages
	public static int overVTotalsRow; // Overview row for totals

	// Default resource categories as specified in requirements
	private static final String[] DEFAULT_RESOURCES = { "Electricity", "Water", "Internet", "Lab Equipment" };
	private static final int NUM_DEFAULT_RESOURCES = 4;

	/**
	 * Main program entry point Coordinates the overall system workflow
	 */
	public static void main(String[] args) {
		printWelcomeBanner();
		setArraySize();

		// Main program loop - continues until all days are processed
		while (true) {
			printMainHeader();
			computeOverviewTotalsAndAverages();
			displayOverview();

			if (currentDay <= maxDays) {
				// Data entry phase for current day
				printSectionHeader("DAY " + currentDay + " DATA ENTRY");
				System.out.println(">>> Ready to compute Day " + currentDay + "/" + maxDays + " data");
				System.out.println(">>> Press [Enter] to continue...");
				sc.nextLine();
				computeDailyData();
			} else {
				// All data collected - generate final report
				printSectionHeader("SUMMARY REPORT");
				System.out.println(">>> All data collected! Generating comprehensive report...");
				System.out.println(">>> Press [Enter] to view summary...");
				sc.nextLine();
				generateSummaryReport();
				break; // Exit program after report generation
			}
		}
		sc.close();
	}// End of Main() method

	/**
	 * Manages the daily data computation workflow Allows user to input data and
	 * perform analyses for current day
	 */
	public static void computeDailyData() {
		while (true) {
			// Perform daily computations
			computeDailyTotalsAndAverages();
			findMostEfficientBuilding();
			detectInefficiencies();

			// Display current state and get user input
			displayMatrix();

			System.out.println("==========================================");
			System.out.println("           DATA ENTRY MENU            ");
			System.out.println("==========================================");
			System.out.println(" [1] Input Data Individually          ");
			System.out.println(" [2] Finalise & Save to Overview      ");
			System.out.println("==========================================");
			System.out.print(">>> Select option (1-2): ");

			String userInput = sc.nextLine();
			switch (userInput) {
			case "1":
				inputData();
				break;
			case "2":
				finalizeAndSaveDailyData();
				printSuccessMessage("Day " + (currentDay - 1) + " data saved successfully!");
				return;
			default:
				printErrorMessage("Invalid option! Please choose 1 or 2.");
				break;
			}
		}
	}// End of computeDailyData() method

	/**
	 * Initializes the system by setting up array sizes and data structures Collects
	 * user input for system configuration
	 */
	public static void setArraySize() {
		printSectionHeader("SYSTEM SETUP");

		int numberOfBuildings = 0;
		int numberOfResources = 0;

		// Get number of buildings with validation
		System.out.print(">>> How many buildings would you like to track?: ");
		numberOfBuildings = getValidatedInteger(1, 50);

		// Get number of resource types with validation
		System.out.print(">>> How many Resource types? (Recommended: 4): ");
		numberOfResources = getValidatedInteger(1, 10);

		// Get tracking period with validation
		System.out.print(">>> How many days would you like to track?: ");
		maxDays = getValidatedInteger(1, 30);

		// ========== INITIALIZE DAILY DATA ARRAY ==========
		// Add extra rows for totals and averages, extra columns for building
		// totals/averages
		buildings = new String[numberOfBuildings + 3][numberOfResources + 3];

		// Calculate index positions for special rows and columns
		totalsRow = buildings.length - 2; // Second last row for totals
		averageRow = buildings.length - 1; // Last row for averages
		totalsColumn = buildings[0].length - 2; // Second last column for building totals
		averageColumn = buildings[0].length - 1; // Last column for building averages

		// Initialize header cells for daily data matrix
		buildings[0][0] = "Building";
		buildings[totalsRow][0] = "Total";
		buildings[averageRow][0] = "Average";
		buildings[0][totalsColumn] = "Building Total";
		buildings[0][averageColumn] = "Building Avg.";

		// ========== INITIALIZE OVERVIEW DATA ARRAY ==========
		// Add extra rows for totals and averages
		overview = new String[maxDays + 3][numberOfResources + 3];
		overVTotalsRow = overview.length - 2;
		overVAverageRow = overview.length - 1;

		// Initialize header cells for overview matrix
		overview[0][0] = "Day";
		overview[overVTotalsRow][0] = "Total";
		overview[overVAverageRow][0] = "Average";
		overview[0][totalsColumn] = "Building Total";
		overview[0][averageColumn] = "Building Avg.";

		// ========== COLLECT RESOURCE NAMES ==========
		System.out.println("\n>>> Please provide names for each resource type:");

		for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
			while (buildings[0][resourceIndex] == null || buildings[0][resourceIndex].length() > 15) {
				if (numberOfResources == NUM_DEFAULT_RESOURCES && resourceIndex <= DEFAULT_RESOURCES.length) {
					// Use default resource names if user selected 4 resources
					buildings[0][resourceIndex] = DEFAULT_RESOURCES[resourceIndex - 1];
					overview[0][resourceIndex] = DEFAULT_RESOURCES[resourceIndex - 1];
					System.out.println(
							">>> Resource " + resourceIndex + " set to: " + DEFAULT_RESOURCES[resourceIndex - 1]);
					break;
				} else {
					// Get custom resource names from user
					System.out.print(">>> Name for resource " + resourceIndex + " (15 chars max): ");
					String userInput = sc.nextLine();
					if (userInput.length() > 15) {
						printErrorMessage("Name too long! Maximum 15 characters.");
					} else if (userInput.trim().isEmpty()) {
						printErrorMessage("Name cannot be empty!");
					} else {
						buildings[0][resourceIndex] = userInput;
						overview[0][resourceIndex] = userInput;
					}
				}
			}
		}

		// Initialize all data cells with default values
		initializeDataArrays();
		printSuccessMessage("System initialized with " + numberOfBuildings + " buildings, " + numberOfResources
				+ " resources, " + maxDays + " days!");
	}// End of setArraySize() method

	/**
	 * Handles individual data input for building resources Allows user to input
	 * consumption values for specific building-resource combinations
	 */
	public static void inputData() {
		printSectionHeader("DATA INPUT");

		String exitCommand = "back";
		int selectedColumn = 0;
		int selectedRow = 0;

		System.out.println(">>> Tip: Enter '" + exitCommand + "' at any time to return to menu.");

		while (true) {
			selectedRow = getBuildingSelection();
			selectedColumn = getResourceSelection();
			addConsumptionData(selectedRow, selectedColumn);

			System.out.print(">>> Press [Enter] for next entry or type '" + exitCommand + "' to finish: ");
			String userInput = sc.nextLine();
			if (userInput.equalsIgnoreCase(exitCommand))
				break;
		}
	}// End of inputData() method

	/**
	 * Prompts user to select a building and validates input
	 * 
	 * @return validated building row index
	 */
	public static int getBuildingSelection() {
		int selectedBuilding = 0;
		String userInput;
		while (selectedBuilding <= 0 || selectedBuilding >= totalsRow) {
			System.out.print(">>> Select building (1-" + (buildings.length - 3) + "): ");
			userInput = sc.nextLine();
			try {
				selectedBuilding = Integer.parseInt(userInput);
				if (selectedBuilding <= 0 || selectedBuilding >= totalsRow) {
					printErrorMessage("Please enter a number between 1 and " + (buildings.length - 3));
				}
			} catch (NumberFormatException e) {
				printErrorMessage("Invalid input! Please enter a valid number.");
				selectedBuilding = 0;
			}
		}
		System.out.println(">>> Selected: Building " + selectedBuilding);
		return selectedBuilding;
	}// End of getBuildingSelection() method

	/**
	 * Prompts user to select a resource type and validates input
	 * 
	 * @return validated resource column index
	 */
	public static int getResourceSelection() {
		String userInput;
		int selectedResource = 0;

		System.out.println(">>> Available resources:");
		for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
			System.out.println("   " + (resourceIndex) + ". " + buildings[0][resourceIndex]);
		}

		while (selectedResource <= 0) {
			System.out.print(">>> Select resource (1-" + (totalsColumn - 1) + "): ");
			userInput = sc.nextLine();
			try {
				int userSelection = Integer.parseInt(userInput);
				if (userSelection >= 1 && userSelection < totalsColumn) {
					selectedResource = userSelection;
					System.out.println(">>> Selected: " + buildings[0][selectedResource]);
				} else {
					printErrorMessage("Please select a number between 1 and " + (totalsColumn - 1));
				}
			} catch (NumberFormatException e) {
				printErrorMessage("Invalid input! Please enter a number.");
			}
		}
		return selectedResource;
	}// End of getResourceSelection() method

	/**
	 * Adds consumption data for a specific building and resource
	 * 
	 * @param buildingIndex The building row index
	 * @param resourceIndex The resource column index
	 */
	public static void addConsumptionData(int buildingIndex, int resourceIndex) {
		String userInput;
		Double consumptionValue = null;

		System.out.println("\n>>> Input for Building " + buildingIndex + " - " + buildings[0][resourceIndex]);

		while (consumptionValue == null) {
			System.out.print(">>> Enter consumption value: ");
			userInput = sc.nextLine();
			try {
				consumptionValue = Double.parseDouble(userInput);
				if (consumptionValue < 0) {
					printErrorMessage("Consumption cannot be negative!");
					consumptionValue = null;
				} else {
					buildings[buildingIndex][resourceIndex] = String.format("%.2f", consumptionValue);
					printSuccessMessage("Data saved: " + buildings[buildingIndex][resourceIndex] + " units");
				}
			} catch (NumberFormatException e) {
				printErrorMessage("Invalid number! Please enter a valid numeric value.");
			}
		}
	}// End of addConsumptionData() method

	/**
	 * Displays the current daily consumption matrix in formatted table Shows all
	 * buildings with their resource consumption, totals, and averages
	 */
	public static void displayMatrix() {
		System.out.println();
		printSectionHeader("DAILY CONSUMPTION MATRIX - DAY " + currentDay);

		// Print column headers
		System.out.printf("%-18s", buildings[0][0]); // Building header
		for (int columnIndex = 1; columnIndex < totalsColumn; columnIndex++) {
			System.out.printf("%-18s", buildings[0][columnIndex]); // Resource headers
		}
		// Total and average column headers
		System.out.printf("%-18s%-18s\n", buildings[0][totalsColumn], buildings[0][averageColumn]);

		// Print separator line
		System.out.println("=".repeat(18 * (buildings[0].length)));

		// Print data rows for each building
		for (int rowIndex = 1; rowIndex < buildings.length; rowIndex++) {
			if (buildings[rowIndex][0] != null) {
				// Print row header (building name or summary label)
				String rowHeader = buildings[rowIndex][0];
				if (rowIndex < totalsRow) {
					System.out.printf("%-18s", "Building " + rowHeader);
				} else {
					System.out.printf("%-18s", rowHeader);
				}

				// Print data cells for this row
				for (int columnIndex = 1; columnIndex < buildings[rowIndex].length; columnIndex++) {
					String cellValue = buildings[rowIndex][columnIndex];
					System.out.printf("%-18s", cellValue);
				}
				System.out.println();
			}
		}
		System.out.println();
	}// End of displayMatrix() method

	/**
	 * Displays the overview matrix showing data across all tracked days
	 */
	public static void displayOverview() {
		System.out.println();
		printSectionHeader("CAMPUS OVERVIEW DASHBOARD");

		// Print column headers
		System.out.printf("%-18s", overview[0][0]);
		for (int columnIndex = 1; columnIndex < totalsColumn; columnIndex++) {
			System.out.printf("%-18s", overview[0][columnIndex]);
		}
		System.out.printf("%-18s%-18s\n", overview[0][totalsColumn], overview[0][averageColumn]);

		// Print separator line
		System.out.println("=".repeat(18 * (overview[0].length)));

		// Print data rows for each day
		for (int rowIndex = 1; rowIndex < overview.length; rowIndex++) {
			if (overview[rowIndex][0] != null) {
				// Print row header (day number or summary label)
				if (rowIndex < overVTotalsRow) {
					System.out.printf("%-18s", "Day " + overview[rowIndex][0]);
				} else {
					System.out.printf("%-18s", overview[rowIndex][0]);
				}

				// Print data cells for this row
				for (int columnIndex = 1; columnIndex < overview[rowIndex].length; columnIndex++) {
					String cellValue = overview[rowIndex][columnIndex];
					System.out.printf("%-18s", cellValue);
				}
				System.out.println();
			}
		}
		System.out.println();
	}// End of displayOverview() method

	/**
	 * Identifies buildings that exceed average consumption thresholds Implements
	 * requirement for threshold-based inefficiency detection
	 */
	public static void detectInefficiencies() {
		printSectionHeader("EFFICIENCY ALERTS");

		try {
			double campusAverageConsumption = Double.parseDouble(buildings[averageRow][averageColumn]);
			final double INEFFICIENCY_THRESHOLD_PERCENT = 10.0; // 10% above average as specified

			boolean foundInefficientBuildings = false;

			// Check each building against campus average
			for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
				try {
					double buildingAverage = Double.parseDouble(buildings[buildingIndex][averageColumn]);
					double percentAboveAverage = ((buildingAverage - campusAverageConsumption)
							/ campusAverageConsumption) * 100;

					// Flag buildings exceeding threshold
					if (percentAboveAverage > INEFFICIENCY_THRESHOLD_PERCENT) {
						if (!foundInefficientBuildings) {
							System.out.println("ALERT: BUILDINGS EXCEEDING " + INEFFICIENCY_THRESHOLD_PERCENT
									+ "% ABOVE AVERAGE:");
							foundInefficientBuildings = true;
						}
						System.out.printf("   [!] Building %s: %.2f (%.1f%% above average)\n",
								buildings[buildingIndex][0], buildingAverage, percentAboveAverage);
					}
				} catch (NumberFormatException e) {
					// Skip buildings with invalid or missing data
				}
			}

			if (!foundInefficientBuildings) {
				System.out.println(">>> All buildings operating within efficient ranges!");
			}
			System.out.println();

		} catch (NumberFormatException e) {
			printErrorMessage("Insufficient data for inefficiency analysis");
		}
	}// End of detectInefficiencies() method

	/**
	 * Identifies the most and least efficient buildings based on average
	 * consumption Implements requirement for efficiency ranking
	 */
	public static void findMostEfficientBuilding() {
		printSectionHeader("EFFICIENCY RANKING");

		int mostEfficientBuildingIndex = -1;
		double mostEfficientConsumption = Double.MAX_VALUE;
		int leastEfficientBuildingIndex = -1;
		double leastEfficientConsumption = Double.MIN_VALUE;

		// Analyze each building to find efficiency extremes
		for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
			try {
				double currentBuildingConsumption = Double.parseDouble(buildings[buildingIndex][averageColumn]);

				// Check for most efficient (lowest consumption)
				if (currentBuildingConsumption < mostEfficientConsumption && currentBuildingConsumption > 0) {
					mostEfficientConsumption = currentBuildingConsumption;
					mostEfficientBuildingIndex = buildingIndex;
				}

				// Check for least efficient (highest consumption)
				if (currentBuildingConsumption > leastEfficientConsumption) {
					leastEfficientConsumption = currentBuildingConsumption;
					leastEfficientBuildingIndex = buildingIndex;
				}
			} catch (NumberFormatException e) {
				// Skip buildings with invalid data
			}
		}

		// Display results if valid data found
		if (mostEfficientBuildingIndex != -1) {
			System.out.println("TOP PERFORMER: Building " + buildings[mostEfficientBuildingIndex][0] + " (Avg: "
					+ String.format("%.2f", mostEfficientConsumption) + ")");

			if (leastEfficientBuildingIndex != -1 && leastEfficientBuildingIndex != mostEfficientBuildingIndex) {
				System.out.println("NEEDS IMPROVEMENT: Building " + buildings[leastEfficientBuildingIndex][0]
						+ " (Avg: " + String.format("%.2f", leastEfficientConsumption) + ")");
			}

			// Calculate and display efficiency gap
			double efficiencyGap = leastEfficientConsumption - mostEfficientConsumption;
			System.out.println("Efficiency gap: " + String.format("%.2f", efficiencyGap) + " units");
		} else {
			printErrorMessage("No valid data for efficiency analysis");
		}
		System.out.println();
	}// End of findMostEfficientBuilding() method

	/**
	 * Generates comprehensive summary report as specified in requirements Provides
	 * executive summary and strategic recommendations
	 */
	public static void generateSummaryReport() {
		System.out.println();
		printSectionHeader("COMPREHENSIVE SUMMARY REPORT");

		System.out.println("==============================================================");
		System.out.println("           GLOBALTECH INSTITUTE                        ");
		System.out.println("           SMART CAMPUS RESOURCE MANAGEMENT                  ");
		System.out.println("==============================================================");

		// ========== EXECUTIVE SUMMARY SECTION ==========
		System.out.println("\nEXECUTIVE SUMMARY");
		System.out.println("--------------------------------------------------------------");

		try {
			double totalCampusConsumption = 0;
			int daysWithValidData = 0;

			// Calculate total consumption across all days
			for (int dayIndex = 1; dayIndex < overVTotalsRow; dayIndex++) {
				try {
					totalCampusConsumption += Double.parseDouble(overview[dayIndex][totalsColumn]);
					daysWithValidData++;
				} catch (NumberFormatException e) {
					// Skip days with invalid data
				}
			}

			// Display summary statistics
			System.out.println(">>> Tracking Period: " + maxDays + " days");
			System.out.println(">>> Buildings Monitored: " + (buildings.length - 3));
			System.out.println(">>> Total Consumption: " + String.format("%.2f", totalCampusConsumption) + " units");
			if (daysWithValidData > 0) {
				System.out.println(">>> Average Daily: "
						+ String.format("%.2f", totalCampusConsumption / daysWithValidData) + " units/day");
			}

		} catch (Exception e) {
			printErrorMessage("Error calculating summary statistics");
		}

		// ========== STRATEGIC RECOMMENDATIONS SECTION ==========
		System.out.println("\nSTRATEGIC RECOMMENDATIONS");
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. Implement targeted efficiency programs");
		System.out.println("2. Share best practices from top-performing buildings");
		System.out.println("3. Conduct energy audits for high-consumption areas");
		System.out.println("4. Develop real-time monitoring dashboard");
		System.out.println("5. Train staff on resource conservation practices");

		// ========== REPORT FOOTER ==========
		System.out.println("\n==============================================================");
		System.out.println("           REPORT GENERATED BY SMART CAMPUS TRACKER           ");
		System.out.println("                 Driving Sustainability                ");
		System.out.println("==============================================================");

		// Closing message
		System.out.println("\n>>> Thank you for using Smart Campus Resource Tracker!");
		System.out.println(">>> Making campuses smarter, one building at a time!");
	}// End of generateSummaryReport() method

	// ========== USER INTERFACE HELPER METHODS ==========

	/**
	 * Displays welcome banner at program start
	 */
	private static void printWelcomeBanner() {
		System.out.println("==============================================================");
		System.out.println("               SMART CAMPUS RESOURCE TRACKER               ");
		System.out.println("           Optimizing Campus Resource Efficiency            ");
		System.out.println("==============================================================");
		System.out.println();
	}// end of printWelcomeBanner() Method

	/**
	 * Displays main program header with current day information
	 */
	private static void printMainHeader() {
		System.out.println("\n--------------------------------------------------------------");
		System.out
				.println("                 CAMPUS DASHBOARD - DAY " + currentDay + "/" + maxDays + "                 ");
		System.out.println("--------------------------------------------------------------");
	}// end of printMainHeader() Method

	/**
	 * Displays a formatted section header
	 * 
	 * === The title to display in the header ===
	 */
	private static void printSectionHeader(String title) {
		int padding = (60 - title.length()) / 2;
		String leftPadding = " ".repeat(Math.max(0, padding));
		String rightPadding = " ".repeat(Math.max(0, 60 - title.length() - padding));

		System.out.println("\n==============================================================");
		System.out.println(leftPadding + title + rightPadding);
		System.out.println("==============================================================");
	}// End of printSectionHeader() Method

	/**
	 * Displays a success message to the user
	 * 
	 * === The success message to display ===	
	 */
	private static void printSuccessMessage(String message) {
		System.out.println(">>> SUCCESS: " + message);
	}// End of printSuccessMessage() Method

	/**
	 * Displays an error message to the user
	 * 
	 * === The error message to display ===
	 */
	private static void printErrorMessage(String message) {
		System.out.println(">>> ERROR: " + message);
	}// End of printErrorMessage

	/**
	 * Validates and returns an integer within specified range
	 * 
	 * @param min Minimum allowed value
	 * @param max Maximum allowed value
	 * @return Validated integer input from user
	 */
	private static int getValidatedInteger(int min, int max) {
		int userValue = 0;
		while (userValue < min || userValue > max) {
			try {
				userValue = Integer.parseInt(sc.nextLine());
				if (userValue < min || userValue > max) {
					printErrorMessage("Please enter a number between " + min + " and " + max);
				}
			} catch (NumberFormatException e) {
				printErrorMessage("Invalid input! Please enter a valid number.");
				userValue = 0;
			}
		}
		return userValue;
	}// End of getValidatedInteger() Method

	/**
	 * Initializes data arrays with default values Sets up building names and
	 * placeholder data cells
	 */
	private static void initializeDataArrays() {
		// Initialize buildings array with default values
		for (int rowIndex = 1; rowIndex < buildings.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < buildings[rowIndex].length; columnIndex++) {
				if (rowIndex >= totalsRow && columnIndex == 0)
					continue; // Skip header cells
				if (columnIndex == 0) {
					// Set building names for data rows
					if (rowIndex < totalsRow) {
						buildings[rowIndex][columnIndex] = "Building " + rowIndex;
					}
				} else {
					// Set placeholder for data cells
					buildings[rowIndex][columnIndex] = "-";
				}
			}
		}

		// Initialize overview array with default values
		for (int rowIndex = 1; rowIndex < overview.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < overview[rowIndex].length; columnIndex++) {
				if (rowIndex >= overVTotalsRow && columnIndex == 0)
					continue; // Skip header cells
				if (columnIndex == 0) {
					// Set day labels for data rows
					if (rowIndex < overVTotalsRow) {
						overview[rowIndex][columnIndex] = "Day " + rowIndex;
					}
				} else {
					// Set placeholder for data cells
					overview[rowIndex][columnIndex] = "-";
				}
			}
		}
	}// End of initializeDataArrays() Method

	// ========== CORE COMPUTATION METHODS ==========

	/**
	 * Computes totals and averages for the daily building data Calculates both
	 * building-wise and resource-wise summaries
	 */
	public static void computeDailyTotalsAndAverages() {
		double runningTotal = 0;
		double currentValue = 0;
		int validDataCount = 0;

		// Calculate building-wise totals and averages (row calculations)
		for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
			for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
				try {
					currentValue = Double.parseDouble(buildings[buildingIndex][resourceIndex]);
					runningTotal += currentValue;
					validDataCount++;
				} catch (NumberFormatException e) {
					// Skip non-numeric values during calculation
				}
			}
			// Store building total and average
			buildings[buildingIndex][totalsColumn] = String.format("%.2f", runningTotal);
			if (validDataCount != 0)
				buildings[buildingIndex][averageColumn] = String.format("%.2f", runningTotal / validDataCount);
			runningTotal = 0;
			validDataCount = 0;
		}

		// Calculate resource-wise totals and averages (column calculations)
		for (int resourceIndex = 1; resourceIndex < buildings[0].length; resourceIndex++) {
			for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
				try {
					currentValue = Double.parseDouble(buildings[buildingIndex][resourceIndex]);
					runningTotal += currentValue;
					if (currentValue > 0)
						validDataCount++;
				} catch (NumberFormatException e) {
					// Skip non-numeric values during calculation
				}
			}
			if (resourceIndex != averageColumn && resourceIndex != totalsColumn) {
				buildings[totalsRow][resourceIndex] = String.format("%.2f", runningTotal);
				if (validDataCount != 0)
					buildings[averageRow][resourceIndex] = String.format("%.2f", runningTotal / validDataCount);
				else
					buildings[averageRow][resourceIndex] = "0.00";
			}
			runningTotal = 0;
			validDataCount = 0;
		}
	}// End of computeDailyTotalsAndAverages() Method

	/**
	 * Computes totals and averages for the overview data across all days
	 */
	public static void computeOverviewTotalsAndAverages() {
		double runningTotal = 0;
		double currentValue = 0;
		int validDataCount = 0;

		// Calculate day-wise totals and averages
		for (int dayIndex = 1; dayIndex < overVTotalsRow; dayIndex++) {
			for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
				try {
					currentValue = Double.parseDouble(overview[dayIndex][resourceIndex]);
					runningTotal += currentValue;
					validDataCount++;
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}
			overview[dayIndex][totalsColumn] = String.format("%.2f", runningTotal);
			if (validDataCount != 0)
				overview[dayIndex][averageColumn] = String.format("%.2f", runningTotal / validDataCount);
			runningTotal = 0;
			validDataCount = 0;
		}

		// Calculate resource-wise totals across all days
		for (int resourceIndex = 1; resourceIndex < overview[0].length; resourceIndex++) {
			for (int dayIndex = 1; dayIndex < overVTotalsRow; dayIndex++) {
				try {
					currentValue = Double.parseDouble(overview[dayIndex][resourceIndex]);
					runningTotal += currentValue;
					if (currentValue > 0)
						validDataCount++;
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}
			if (resourceIndex != averageColumn && resourceIndex != totalsColumn) {
				overview[overVTotalsRow][resourceIndex] = String.format("%.2f", runningTotal);
				if (validDataCount != 0)
					overview[overVAverageRow][resourceIndex] = String.format("%.2f", runningTotal / validDataCount);
				else
					overview[overVAverageRow][resourceIndex] = "0.00";
			}
			runningTotal = 0;
			validDataCount = 0;
		}
	}// End of computeOverviewTotalsAndAverages() Method

	/**
	 * Finalizes current day's data and saves it to overview Resets daily data for
	 * next day's entry
	 */
	public static void finalizeAndSaveDailyData() {
		// Save current day's totals to overview array
		for (int columnIndex = 1; columnIndex < buildings[0].length; columnIndex++) {
			overview[currentDay][columnIndex] = buildings[totalsRow][columnIndex];
			if (columnIndex == averageColumn)
				overview[currentDay][columnIndex] = buildings[averageRow][totalsColumn];
		}

		// Reset daily data array for next day
		for (int rowIndex = 1; rowIndex < buildings.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < buildings[rowIndex].length; columnIndex++) {
				if (rowIndex >= totalsRow && columnIndex == 0)
					continue; // Preserve header rows
				if (columnIndex == 0) {
					buildings[rowIndex][columnIndex] = "Building " + rowIndex;
				} else {
					buildings[rowIndex][columnIndex] = "-"; // Reset data cells
				}
			}
		}
		currentDay++; // Move to next day
	}// End of finalizeAndSaveDailyData() Method
}