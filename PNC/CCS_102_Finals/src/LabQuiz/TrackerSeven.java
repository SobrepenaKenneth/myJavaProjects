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
 * @author GlobalTech Institute
 * @version 27.2
 */
public class TrackerSeven {
	// Scanner object for user input throughout the program
	static Scanner sc = new Scanner(System.in);

	// Data storage arrays
	public static String[][] dailyData; // Daily building resource data
	public static String[][] overviewData; // Overview data across all days

	// Tracking variables
	public static int currentDay = 1; // Current day being processed
	public static int totalDays = 0; // Total number of days to track

	// Array index constants for daily data matrix
	public static int dailyTotalsRow; // Row index for column totals
	public static int dailyAveragesRow; // Row index for column averages
	public static int dailyTotalsCol; // Column index for row totals
	public static int dailyAveragesCol; // Column index for row averages

	// Array index constants for overview matrix
	public static int overviewTotalsRow; // Overview row for totals
	public static int overviewAveragesRow; // Overview row for averages

	// Default resource categories as specified in requirements
	private static final String[] DEFAULT_RESOURCES = { "Electricity", "Water", "Internet", "Lab Equipment" };
	private static final int NUM_DEFAULT_RESOURCES = 4;

	/**
	 * Main method - program entry point Controls the overall program flow and
	 * execution sequence
	 */
	public static void main(String[] args) {
		initializeProgram();
		executeMainProgramLoop();
		sc.close();
	}

	// ========== REQUIRED USER-DEFINED METHODS ==========

	/**
	 * REQUIRED METHOD 1: inputData() Accept and validate resource usage inputs from
	 * the user Allows individual data entry for specific building and resource
	 * combinations
	 */
	public static void inputData() {
		displaySectionHeader("DATA INPUT");
//        System.out.println(">>> Tip: Enter 'back' at any time to return to menu.");

		while (true) {
			int buildingIndex = getBuildingSelectionFromUser();
			int resourceIndex = getResourceSelectionFromUser();
			enterConsumptionValueForCell(buildingIndex, resourceIndex);

			if (!shouldContinueDataEntry())
				break;
		}
	}

	/**
	 * REQUIRED METHOD 2: displayMatrix() Display all data in formatted tabular form
	 * Shows the current daily consumption matrix with proper formatting
	 */
	public static void displayMatrix() {
		System.out.println();
		displaySectionHeader("DAILY CONSUMPTION MATRIX - DAY " + currentDay);

		// Print column headers
		System.out.printf("%-18s", dailyData[0][0]);
		for (int colIndex = 1; colIndex < dailyTotalsCol; colIndex++) {
			System.out.printf("%-18s", dailyData[0][colIndex]);
		}
		System.out.printf("%-18s%-18s\n", dailyData[0][dailyTotalsCol], dailyData[0][dailyAveragesCol]);

		// Print separator line
		System.out.println("=".repeat(18 * dailyData[0].length));

		// Print data rows
		for (int rowIndex = 1; rowIndex < dailyData.length; rowIndex++) {
			if (dailyData[rowIndex][0] != null) {
				System.out.printf("%-18s", dailyData[rowIndex][0]);

				for (int colIndex = 1; colIndex < dailyData[rowIndex].length; colIndex++) {
					System.out.printf("%-18s", dailyData[rowIndex][colIndex]);
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * REQUIRED METHOD 3: computeBuildingTotals() Calculate total and average usage
	 * per building Computes row-wise totals and averages for each building
	 */
	public static void computeBuildingTotals() {
		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			double buildingTotal = 0.0;
			int validDataCount = 0;

			// Calculate total consumption for this building
			for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
				Double consumption = parseDoubleSafely(dailyData[buildingIndex][resourceIndex]);
				if (consumption != null) {
					buildingTotal += consumption;
					validDataCount++;
				}
			}

			// Store results in the matrix
			dailyData[buildingIndex][dailyTotalsCol] = String.format("%.2f", buildingTotal);

			// Calculate and store average
			if (validDataCount > 0) {
				double buildingAverage = buildingTotal / validDataCount;
				dailyData[buildingIndex][dailyAveragesCol] = String.format("%.2f", buildingAverage);
			} else {
				dailyData[buildingIndex][dailyAveragesCol] = "0.00";
			}
		}
	}

	/**
	 * REQUIRED METHOD 4: computeResourceAverages() Calculate average usage per
	 * resource type Computes column-wise averages for each resource across all
	 * buildings
	 */
	public static void computeResourceAverages() {
		for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
			double resourceTotal = 0.0;
			int validDataCount = 0;

			// Calculate total consumption for this resource
			for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
				Double consumption = parseDoubleSafely(dailyData[buildingIndex][resourceIndex]);
				if (consumption != null) {
					resourceTotal += consumption;
					validDataCount++;
				}
			}

			// Calculate and store average
			if (validDataCount > 0) {
				double resourceAverage = resourceTotal / validDataCount;
				dailyData[dailyAveragesRow][resourceIndex] = String.format("%.2f", resourceAverage);
			} else {
				dailyData[dailyAveragesRow][resourceIndex] = "0.00";
			}
		}
	}

	/**
	 * REQUIRED METHOD 5: detectInefficiencies() Identify any building exceeding
	 * average resource consumption Flags buildings consuming more than 10% above
	 * campus average
	 */
	public static void detectInefficiencies() {
		displaySectionHeader("EFFICIENCY ALERTS");
		final double INEFFICIENCY_THRESHOLD = 10.0; // 10% above average

		// Calculate campus-wide average consumption
		Double campusAverage = calculateCampusAverageConsumption();
		if (campusAverage == null) {
			displayErrorMessage("Insufficient data for inefficiency analysis");
			System.out.println();
			return;
		}

		boolean foundInefficientBuildings = false;

		// Check each building against campus average
		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				double percentAboveAverage = calculatePercentageAboveAverage(buildingAverage, campusAverage);

				// Flag buildings exceeding threshold
				if (percentAboveAverage > INEFFICIENCY_THRESHOLD) {
					if (!foundInefficientBuildings) {
						System.out.println(
								"ALERT: BUILDINGS EXCEEDING " + INEFFICIENCY_THRESHOLD + "% ABOVE CAMPUS AVERAGE:");
						foundInefficientBuildings = true;
					}
					System.out.printf("   [!] %s: Avg=%.2f (%.1f%% above campus avg)\n", dailyData[buildingIndex][0],
							buildingAverage, percentAboveAverage);
				}
			}
		}

		if (!foundInefficientBuildings) {
			System.out.println(">>> All buildings operating within efficient ranges!");
		}
		System.out.println();
	}

	/**
	 * REQUIRED METHOD 6: findMostEfficientBuilding() Determine which building has
	 * the lowest average resource consumption Identifies top performer and
	 * optionally the least efficient building
	 */
	public static void findMostEfficientBuilding() {
		displaySectionHeader("EFFICIENCY RANKING");

		// Find most efficient building
		int mostEfficientIndex = -1;
		double lowestConsumption = Double.MAX_VALUE;

		// Find least efficient building
		int leastEfficientIndex = -1;
		double highestConsumption = Double.NEGATIVE_INFINITY;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				// Check for most efficient
				if (buildingAverage < lowestConsumption) {
					lowestConsumption = buildingAverage;
					mostEfficientIndex = buildingIndex;
				}

				// Check for least efficient
				if (buildingAverage > highestConsumption) {
					highestConsumption = buildingAverage;
					leastEfficientIndex = buildingIndex;
				}
			}
		}

		// Display results
		if (mostEfficientIndex != -1) {
			System.out.println("TOP PERFORMER: " + dailyData[mostEfficientIndex][0] + " (Avg: "
					+ String.format("%.2f", lowestConsumption) + ")");

			if (leastEfficientIndex != -1 && leastEfficientIndex != mostEfficientIndex) {
				System.out.println("NEEDS IMPROVEMENT: " + dailyData[leastEfficientIndex][0] + " (Avg: "
						+ String.format("%.2f", highestConsumption) + ")");

				double efficiencyGap = highestConsumption - lowestConsumption;
				System.out.println("Efficiency gap: " + String.format("%.2f", efficiencyGap) + " units");
			}
		} else {
			displayErrorMessage("No valid data for efficiency analysis");
		}
		System.out.println();
	}

	/**
	 * REQUIRED METHOD 7: generateSummaryReport() Display and interpret all
	 * analytical findings Provides comprehensive summary with strategic
	 * recommendations
	 */
	public static void generateSummaryReport() {
		System.out.println();
		displaySectionHeader("COMPREHENSIVE SUMMARY REPORT");

		// Report Header
		System.out.println("==============================================================");
		System.out.println("                 GLOBALTECH INSTITUTE                        ");
		System.out.println("           SMART CAMPUS RESOURCE MANAGEMENT                  ");
		System.out.println("==============================================================");

		// Executive Summary
		displayExecutiveSummary();

		// Analytical Findings
		displayAnalyticalFindings();

		// Strategic Recommendations
		displayStrategicRecommendations();

		// Report Footer
		System.out.println("\n==============================================================");
		System.out.println("           REPORT GENERATED BY SMART CAMPUS TRACKER           ");
		System.out.println("                 Driving Sustainability                ");
		System.out.println("==============================================================");

		System.out.println("\n>>> Thank you for using Smart Campus Resource Tracker!");
		System.out.println(">>> Making campuses smarter, one building at a time!");
	}

	// ========== SUPPORTING METHODS FOR REQUIRED FUNCTIONS ==========

	/**
	 * Supports inputData() - Gets building selection from user
	 */
	private static int getBuildingSelectionFromUser() {
		int maxBuilding = dailyData.length - 3;

		while (true) {
			System.out.print(">>> Select building (1-" + maxBuilding + "): ");
			String userInput = sc.nextLine();

			try {
				int selection = Integer.parseInt(userInput);
				if (selection >= 1 && selection <= maxBuilding) {
					System.out.println(">>> Selected: Building " + selection);
					return selection;
				}
				displayErrorMessage("Please enter a number between 1 and " + maxBuilding);
			} catch (NumberFormatException e) {
				displayErrorMessage("Invalid input! Please enter a valid number.");
			}
		}
	}

	/**
	 * Supports inputData() - Gets resource selection from user
	 */
	private static int getResourceSelectionFromUser() {
		System.out.println(">>> Available resources:");
		for (int i = 1; i < dailyTotalsCol; i++) {
			System.out.println("   " + i + ". " + dailyData[0][i]);
		}

		while (true) {
			System.out.print(">>> Select resource (1-" + (dailyTotalsCol - 1) + "): ");
			String userInput = sc.nextLine();

			try {
				int selection = Integer.parseInt(userInput);
				if (selection >= 1 && selection < dailyTotalsCol) {
					System.out.println(">>> Selected: " + dailyData[0][selection]);
					return selection;
				}
				displayErrorMessage("Please select a number between 1 and " + (dailyTotalsCol - 1));
			} catch (NumberFormatException e) {
				displayErrorMessage("Invalid input! Please enter a number.");
			}
		}
	}

	/**
	 * Supports inputData() - Enters consumption value for specific cell
	 */
	private static void enterConsumptionValueForCell(int buildingIndex, int resourceIndex) {
		System.out.println("\n>>> Input for Building " + buildingIndex + " - " + dailyData[0][resourceIndex]);

		while (true) {
			System.out.print(">>> Enter consumption value: ");
			String userInput = sc.nextLine();

			try {
				double consumption = Double.parseDouble(userInput);
				if (consumption < 0) {
					displayErrorMessage("Consumption cannot be negative!");
				} else {
					dailyData[buildingIndex][resourceIndex] = String.format("%.2f", consumption);
					displaySuccessMessage("Data saved: " + dailyData[buildingIndex][resourceIndex] + " units");
					return;
				}
			} catch (NumberFormatException e) {
				displayErrorMessage("Invalid number! Please enter a valid numeric value.");
			}
		}
	}

	/**
	 * Supports inputData() - Checks if user wants to continue data entry
	 */
	private static boolean shouldContinueDataEntry() {
		System.out.print(">>> Press [Enter] for next entry or type 'back' to finish: ");
		return !sc.nextLine().equalsIgnoreCase("back");
	}

	/**
	 * Supports detectInefficiencies() - Calculates campus average consumption
	 */
	private static Double calculateCampusAverageConsumption() {
		double sum = 0.0;
		int count = 0;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				sum += buildingAverage;
				count++;
			}
		}

		return count > 0 ? sum / count : null;
	}

	/**
	 * Supports detectInefficiencies() - Calculates percentage above average
	 */
	private static double calculatePercentageAboveAverage(double value, double average) {
		return ((value - average) / average) * 100.0;
	}

	/**
	 * Supports generateSummaryReport() - Displays executive summary
	 */
	private static void displayExecutiveSummary() {
		System.out.println("\nEXECUTIVE SUMMARY");
		System.out.println("--------------------------------------------------------------");

		double totalConsumption = 0.0;
		int daysWithData = 0;

		// Calculate total consumption across all days
		for (int dayIndex = 1; dayIndex < overviewTotalsRow; dayIndex++) {
			Double dayTotal = parseDoubleSafely(overviewData[dayIndex][dailyTotalsCol]);
			if (dayTotal != null) {
				totalConsumption += dayTotal;
				daysWithData++;
			}
		}

		System.out.println(">>> Tracking Period: " + totalDays + " days");
		System.out.println(">>> Buildings Monitored: " + (dailyData.length - 3));
		System.out.println(">>> Total Consumption: " + String.format("%.2f", totalConsumption) + " units");

		if (daysWithData > 0) {
			System.out.println(
					">>> Average Daily: " + String.format("%.2f", totalConsumption / daysWithData) + " units/day");
		}
	}

	/**
	 * Supports generateSummaryReport() - Displays analytical findings
	 */
	private static void displayAnalyticalFindings() {
		System.out.println("\nANALYTICAL FINDINGS");
		System.out.println("--------------------------------------------------------------");

		// Find most and least efficient buildings for summary
		int mostEfficient = -1;
		int leastEfficient = -1;
		double minConsumption = Double.MAX_VALUE;
		double maxConsumption = Double.NEGATIVE_INFINITY;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAvg = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAvg != null) {
				if (buildingAvg < minConsumption) {
					minConsumption = buildingAvg;
					mostEfficient = buildingIndex;
				}
				if (buildingAvg > maxConsumption) {
					maxConsumption = buildingAvg;
					leastEfficient = buildingIndex;
				}
			}
		}

		if (mostEfficient != -1) {
			System.out.println(">>> Most Efficient: " + dailyData[mostEfficient][0] + " ("
					+ String.format("%.2f", minConsumption) + " avg)");
			System.out.println(">>> Least Efficient: " + dailyData[leastEfficient][0] + " ("
					+ String.format("%.2f", maxConsumption) + " avg)");
			System.out.println(
					">>> Performance Gap: " + String.format("%.2f", (maxConsumption - minConsumption)) + " units");
		}
	}

	/**
	 * Supports generateSummaryReport() - Displays strategic recommendations
	 */
	private static void displayStrategicRecommendations() {
		System.out.println("\nSTRATEGIC RECOMMENDATIONS");
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. Implement targeted efficiency programs for high-consumption buildings");
		System.out.println("2. Share best practices from top-performing buildings");
		System.out.println("3. Conduct energy audits for identified inefficiency areas");
		System.out.println("4. Develop real-time monitoring dashboard for continuous tracking");
		System.out.println("5. Train staff on resource conservation practices and technologies");
	}

	// ========== PROGRAM INITIALIZATION AND CORE LOGIC ==========

	/**
	 * Initializes the program by setting up welcome banner and data structures
	 */
	public static void initializeProgram() {
		displayWelcomeBanner();
		setupDataStructures();
	}

	/**
	 * Sets up the data structures by getting user input and initializing arrays
	 */
	public static void setupDataStructures() {
		displaySectionHeader("SYSTEM SETUP");

		int buildingCount = getValidatedInput("How many buildings would you like to track?: ", 1, 50);
		int resourceCount = getValidatedInput("How many Resource types? (Recommended: 4): ", 1, 10);
		totalDays = getValidatedInput("How many days would you like to track?: ", 1, 30);

		initializeDailyDataArray(buildingCount, resourceCount);
		initializeOverviewDataArray(resourceCount);
		setupResourceNames(resourceCount);
		initializeArraysWithDefaultValues();

		displaySuccessMessage("System initialized with " + buildingCount + " buildings, " + resourceCount
				+ " resources, " + totalDays + " days!");
	}

	/**
	 * Executes the main program loop for daily data processing
	 */
	public static void executeMainProgramLoop() {
		while (true) {
			displayProgramDashboard();

			if (currentDay <= totalDays) {
				processCurrentDayData();
			} else {
				generateSummaryReport(); // Use the required method
				break;
			}
		}
	}

	/**
	 * Processes data entry and calculations for the current day
	 */
	public static void processCurrentDayData() {
		displaySectionHeader("DAY " + currentDay + " DATA ENTRY");
		promptUserToContinue();
		executeDailyDataProcessing();
	}

	/**
	 * Executes the main daily data processing workflow
	 */
	public static void executeDailyDataProcessing() {
		while (true) {
			// Use required methods for calculations
			computeBuildingTotals();
			computeResourceAverages();
			calculateCampusWideSummary();

			// Use required methods for analysis and display
			performDailyAnalyses();
			displayMatrix();

			String userChoice = displayDataEntryMenu();
			if (userChoice.equals("1")) {
				inputData(); // Use the required method
			} else if (userChoice.equals("2")) {
				saveCurrentDayData();
				return;
			}
		}
	}

	/**
	 * Performs daily analytical computations using required methods
	 */
	public static void performDailyAnalyses() {
		detectInefficiencies(); // Required method
		findMostEfficientBuilding(); // Required method
		computePercentageDifferences();
	}

	// ========== ARRAY MANAGEMENT METHODS ==========

	/**
	 * Initializes the daily data array with proper dimensions and headers
	 */
	public static void initializeDailyDataArray(int buildingCount, int resourceCount) {
		dailyData = new String[buildingCount + 3][resourceCount + 3];

		dailyTotalsRow = dailyData.length - 2;
		dailyAveragesRow = dailyData.length - 1;
		dailyTotalsCol = dailyData[0].length - 2;
		dailyAveragesCol = dailyData[0].length - 1;

		dailyData[0][0] = "Building";
		dailyData[dailyTotalsRow][0] = "Total";
		dailyData[dailyAveragesRow][0] = "Average";
		dailyData[0][dailyTotalsCol] = "Building Total";
		dailyData[0][dailyAveragesCol] = "Building Avg.";
	}

	/**
	 * Initializes the overview data array for multi-day tracking
	 */
	public static void initializeOverviewDataArray(int resourceCount) {
		overviewData = new String[totalDays + 3][resourceCount + 3];

		overviewTotalsRow = overviewData.length - 2;
		overviewAveragesRow = overviewData.length - 1;

		overviewData[0][0] = "Day";
		overviewData[overviewTotalsRow][0] = "Total";
		overviewData[overviewAveragesRow][0] = "Average";
		overviewData[0][dailyTotalsCol] = "Building Total";
		overviewData[0][dailyAveragesCol] = "Building Avg.";
	}

	/**
	 * Sets up resource names using defaults or user input
	 */
	public static void setupResourceNames(int resourceCount) {
		System.out.println("\n>>> Please provide names for each resource type:");

		for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
			if (resourceCount == NUM_DEFAULT_RESOURCES && resourceIndex <= DEFAULT_RESOURCES.length) {
				String defaultName = DEFAULT_RESOURCES[resourceIndex - 1];
				dailyData[0][resourceIndex] = defaultName;
				overviewData[0][resourceIndex] = defaultName;
				System.out.println(">>> Resource " + resourceIndex + " set to: " + defaultName);
			} else {
				promptUserForResourceName(resourceIndex);
			}
		}
	}

	/**
	 * Initializes all arrays with default placeholder values
	 */
	public static void initializeArraysWithDefaultValues() {
		initializeArrayWithPlaceholders(dailyData, dailyTotalsRow, "Building ");
		initializeArrayWithPlaceholders(overviewData, overviewTotalsRow, "Day ");
	}

	// ========== CALCULATION METHODS ==========

	/**
	 * Calculates campus-wide summary statistics
	 */
	public static void calculateCampusWideSummary() {
		double sumOfAverages = 0.0;
		int buildingCount = 0;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				sumOfAverages += buildingAverage;
				buildingCount++;
			}
		}

		if (buildingCount > 0) {
			double rawAvg = sumOfAverages / buildingCount;
			double rounded = ((int) (rawAvg * 100)) / 100.0; // manual rounding
			dailyData[dailyAveragesRow][dailyAveragesCol] = "" + rounded;
		} else {
			dailyData[dailyAveragesRow][dailyAveragesCol] = "0.00";
		}
	}

	/**
	 * Computes percentage differences from campus average (additional analysis)
	 */
	public static void computePercentageDifferences() {
		Double campusAverage = calculateCampusAverageConsumption();
		if (campusAverage == null || campusAverage <= 0)
			return;

		System.out.println("PERCENT DIFFERENCES VS CAMPUS AVERAGE:");
		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				double percentageDiff = calculatePercentageAboveAverage(buildingAverage, campusAverage);
				System.out.printf("   %s: %.1f%% relative\n", dailyData[buildingIndex][0], percentageDiff);
			}
		}
		System.out.println();
	}

	// ========== DATA MANAGEMENT METHODS ==========

	/**
	 * Saves current day's data to overview and prepares for next day
	 */
	public static void saveCurrentDayData() {
		copyDailyDataToOverview();
		resetDailyDataForNextDay();
		displaySuccessMessage("Day " + (currentDay - 1) + " data saved successfully!");
	}

	/**
	 * Copies current day's resource totals to overview data
	 */
	public static void copyDailyDataToOverview() {
		double dayTotalConsumption = 0.0;

		for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
			String resourceTotal = dailyData[dailyTotalsRow][resourceIndex];
			overviewData[currentDay][resourceIndex] = resourceTotal;

			Double numericValue = parseDoubleSafely(resourceTotal);
			if (numericValue != null) {
				dayTotalConsumption += numericValue;
			}
		}

		overviewData[currentDay][dailyTotalsCol] = String.format("%.2f", dayTotalConsumption);

		int resourceCount = dailyTotalsCol - 1;
		overviewData[currentDay][dailyAveragesCol] = resourceCount > 0
				? String.format("%.2f", dayTotalConsumption / resourceCount)
				: "0.00";

		currentDay++;
	}

	/**
	 * Resets daily data array for the next day's entries
	 */
	public static void resetDailyDataForNextDay() {
		for (int rowIndex = 1; rowIndex < dailyData.length; rowIndex++) {
			for (int colIndex = 0; colIndex < dailyData[rowIndex].length; colIndex++) {
				if (rowIndex >= dailyTotalsRow && colIndex == 0)
					continue;

				if (colIndex == 0) {
					dailyData[rowIndex][colIndex] = "Building " + rowIndex;
				} else {
					dailyData[rowIndex][colIndex] = "-";
				}
			}
		}
	}

	// ========== HELPER METHODS ==========

	/**
	 * Safely parses a string to Double, returns null if invalid
	 */
	public static Double parseDoubleSafely(String value) {
		if (value == null || value.equals("-") || value.trim().isEmpty()) {
			return null;
		}
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Gets validated integer input from user
	 */
	public static int getValidatedInput(String prompt, int min, int max) {
		System.out.print(">>> " + prompt);

		int value = 0;
		while (value < min || value > max) {
			try {
				value = Integer.parseInt(sc.nextLine());
				if (value < min || value > max) {
					displayErrorMessage("Please enter a number between " + min + " and " + max);
				}
			} catch (NumberFormatException e) {
				displayErrorMessage("Invalid input! Please enter a valid number.");
				value = 0;
			}
		}
		return value;
	}

	/**
	 * Prompts user for resource name with validation
	 */
	public static void promptUserForResourceName(int resourceIndex) {
		while (dailyData[0][resourceIndex] == null) {
			System.out.print(">>> Name for resource " + resourceIndex + " (15 chars max): ");
			String userInput = sc.nextLine();

			if (userInput.length() > 15) {
				displayErrorMessage("Name too long! Maximum 15 characters.");
			} else if (userInput.trim().isEmpty()) {
				displayErrorMessage("Name cannot be empty!");
			} else {
				dailyData[0][resourceIndex] = userInput;
				overviewData[0][resourceIndex] = userInput;
			}
		}
	}

	/**
	 * Fills an array with default placeholder values
	 */
	public static void initializeArrayWithPlaceholders(String[][] targetArray, int summaryRow, String rowPrefix) {
		for (int rowIndex = 1; rowIndex < targetArray.length; rowIndex++) {
			for (int colIndex = 0; colIndex < targetArray[rowIndex].length; colIndex++) {
				if (rowIndex >= summaryRow && colIndex == 0)
					continue;

				if (colIndex == 0 && rowIndex < summaryRow) {
					targetArray[rowIndex][colIndex] = rowPrefix + rowIndex;
				} else {
					targetArray[rowIndex][colIndex] = "-";
				}
			}
		}
	}

	/**
	 * Displays the data entry menu and gets user selection
	 */
	public static String displayDataEntryMenu() {
		System.out.println("==========================================");
		System.out.println("           DATA ENTRY MENU            ");
		System.out.println("==========================================");
		System.out.println(" [1] Input Data Individually");
		System.out.println(" [2] Finalise & Save to Overview");
		System.out.println("==========================================");

		while (true) {
			System.out.print(">>> Select option (1-2): ");
			String userInput = sc.nextLine();

			if (userInput.equals("1") || userInput.equals("2")) {
				return userInput;
			}
			displayErrorMessage("Invalid option! Please choose 1 or 2.");
		}
	}

	// ========== UI DISPLAY METHODS ==========

	public static void displayWelcomeBanner() {
		System.out.println("==============================================================");
		System.out.println("               SMART CAMPUS RESOURCE TRACKER                  ");
		System.out.println("           Optimizing Campus Resource Efficiency              ");
		System.out.println("==============================================================");
		System.out.println();
	}

	public static void displayProgramDashboard() {
		displayMainHeader();
		computeOverviewTotalsAndAverages();
		displayOverviewMatrix();
	}

	public static void displayMainHeader() {
		System.out.println("\n--------------------------------------------------------------");
		System.out.println(
				"                 CAMPUS DASHBOARD - DAY " + currentDay + "/" + totalDays + "                 ");
		System.out.println("--------------------------------------------------------------");
	}

	public static void displaySectionHeader(String title) {
	    int padding = (60 - title.length()) / 2;
	    if (padding < 0) padding = 0;

	    int rightPad = 60 - title.length() - padding;
	    if (rightPad < 0) rightPad = 0;

	    // Build padding manually (no .repeat)
	    String leftPadding = "";
	    for (int i = 0; i < padding; i++) {
	        leftPadding += " ";
	    }

	    String rightPadding = "";
	    for (int i = 0; i < rightPad; i++) {
	        rightPadding += " ";
	    }

	    System.out.println("\n==============================================================");
	    System.out.println(leftPadding + title + rightPadding);
	    System.out.println("==============================================================");
	}

	public static void displayOverviewMatrix() {
		System.out.println();
		displaySectionHeader("CAMPUS OVERVIEW DASHBOARD");

		System.out.printf("%-18s", overviewData[0][0]);
		for (int colIndex = 1; colIndex < dailyTotalsCol; colIndex++) {
			System.out.printf("%-18s", overviewData[0][colIndex]);
		}
		System.out.printf("%-18s%-18s\n", overviewData[0][dailyTotalsCol], overviewData[0][dailyAveragesCol]);

		System.out.println("=".repeat(18 * overviewData[0].length));

		for (int rowIndex = 1; rowIndex < overviewData.length; rowIndex++) {
			if (overviewData[rowIndex][0] != null) {
				System.out.printf("%-18s", overviewData[rowIndex][0]);

				for (int colIndex = 1; colIndex < overviewData[rowIndex].length; colIndex++) {
					System.out.printf("%-18s", overviewData[rowIndex][colIndex]);
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	public static void promptUserToContinue() {
		System.out.println(">>> Ready to compute Day " + currentDay + "/" + totalDays + " data");
		System.out.println(">>> Press [Enter] to continue...");
		sc.nextLine();
	}

	public static void displaySuccessMessage(String message) {
		System.out.println(">>> SUCCESS: " + message);
	}

	public static void displayErrorMessage(String message) {
		System.out.println(">>> ERROR: " + message);
	}

	/**
	 * Computes overview totals and averages across all days
	 */
	public static void computeOverviewTotalsAndAverages() {
		calculateDayWiseOverviewTotals();
		calculateResourceWiseOverviewTotals();
	}

	/**
	 * Calculates day-wise totals and averages for overview
	 */
	public static void calculateDayWiseOverviewTotals() {
		for (int dayIndex = 1; dayIndex < overviewTotalsRow; dayIndex++) {
			double dayTotal = 0.0;
			int validDataCount = 0;

			for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
				Double consumption = parseDoubleSafely(overviewData[dayIndex][resourceIndex]);
				if (consumption != null) {
					dayTotal += consumption;
					validDataCount++;
				}
			}

			overviewData[dayIndex][dailyTotalsCol] = String.format("%.2f", dayTotal);
			overviewData[dayIndex][dailyAveragesCol] = validDataCount > 0
					? String.format("%.2f", dayTotal / validDataCount)
					: "0.00";
		}
	}

	/**
	 * Calculates resource-wise totals and averages for overview
	 */
	public static void calculateResourceWiseOverviewTotals() {
		for (int resourceIndex = 1; resourceIndex < overviewData[0].length; resourceIndex++) {
			if (resourceIndex == dailyAveragesCol || resourceIndex == dailyTotalsCol)
				continue;

			double resourceTotal = 0.0;
			int validDataCount = 0;

			for (int dayIndex = 1; dayIndex < overviewTotalsRow; dayIndex++) {
				Double consumption = parseDoubleSafely(overviewData[dayIndex][resourceIndex]);
				if (consumption != null) {
					resourceTotal += consumption;
					validDataCount++;
				}
			}

			overviewData[overviewTotalsRow][resourceIndex] = String.format("%.2f", resourceTotal);
			overviewData[overviewAveragesRow][resourceIndex] = validDataCount > 0
					? String.format("%.2f", resourceTotal / validDataCount)
					: "0.00";
		}
	}
}