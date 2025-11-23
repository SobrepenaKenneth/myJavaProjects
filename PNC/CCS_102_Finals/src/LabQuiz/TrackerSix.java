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
 * Technical Implementation: - Uses only arrays (no ArrayList or external data
 * structures) - Implements static methods for modularity - Consistent method
 * decomposition and parameter passing - Comprehensive input validation and
 * output formatting
 * 
 * @author GlobalTech Institute
 * @version 26.4
 */
public class TrackerSix {
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

	// ========== PROGRAM INITIALIZATION METHODS ==========

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
	 * Initializes the daily data array with proper dimensions and headers
	 * 
	 * @param buildingCount Number of buildings to track
	 * @param resourceCount Number of resource types to track
	 */
	public static void initializeDailyDataArray(int buildingCount, int resourceCount) {
		// +3 for header row, totals row, and averages row
		dailyData = new String[buildingCount + 3][resourceCount + 3];

		// Set row indices for summary rows
		dailyTotalsRow = dailyData.length - 2;
		dailyAveragesRow = dailyData.length - 1;

		// Set column indices for summary columns
		dailyTotalsCol = dailyData[0].length - 2;
		dailyAveragesCol = dailyData[0].length - 1;

		// Initialize header cells
		dailyData[0][0] = "Building";
		dailyData[dailyTotalsRow][0] = "Total";
		dailyData[dailyAveragesRow][0] = "Average";
		dailyData[0][dailyTotalsCol] = "Building Total";
		dailyData[0][dailyAveragesCol] = "Building Avg.";
	}

	/**
	 * Initializes the overview data array for multi-day tracking
	 * 
	 * @param resourceCount Number of resource types to track
	 */
	public static void initializeOverviewDataArray(int resourceCount) {
		// +3 for header row, totals row, and averages row
		overviewData = new String[totalDays + 3][resourceCount + 3];

		// Set row indices for summary rows
		overviewTotalsRow = overviewData.length - 2;
		overviewAveragesRow = overviewData.length - 1;

		// Initialize header cells
		overviewData[0][0] = "Day";
		overviewData[overviewTotalsRow][0] = "Total";
		overviewData[overviewAveragesRow][0] = "Average";
		overviewData[0][dailyTotalsCol] = "Building Total";
		overviewData[0][dailyAveragesCol] = "Building Avg.";
	}

	/**
	 * Sets up resource names using defaults or user input
	 * 
	 * @param resourceCount Number of resource types to name
	 */
	public static void setupResourceNames(int resourceCount) {
		System.out.println("\n>>> Please provide names for each resource type:");

		for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
			assignResourceName(resourceIndex, resourceCount);
		}
	}

	/**
	 * Assigns a name to a specific resource
	 * 
	 * @param resourceIndex  Index of the resource to name
	 * @param totalResources Total number of resources
	 */
	public static void assignResourceName(int resourceIndex, int totalResources) {
		if (totalResources == NUM_DEFAULT_RESOURCES && resourceIndex <= DEFAULT_RESOURCES.length) {
			// Use default resource names
			String defaultName = DEFAULT_RESOURCES[resourceIndex - 1];
			dailyData[0][resourceIndex] = defaultName;
			overviewData[0][resourceIndex] = defaultName;
			System.out.println(">>> Resource " + resourceIndex + " set to: " + defaultName);
		} else {
			// Get custom resource name from user
			promptUserForResourceName(resourceIndex);
		}
	}

	/**
	 * Prompts user to enter a name for a specific resource
	 * 
	 * @param resourceIndex Index of the resource to name
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
	 * Initializes all arrays with default placeholder values
	 */
	public static void initializeArraysWithDefaultValues() {
		initializeArrayWithPlaceholders(dailyData, dailyTotalsRow, "Building ");
		initializeArrayWithPlaceholders(overviewData, overviewTotalsRow, "Day ");
	}

	/**
	 * Fills an array with default placeholder values
	 * 
	 * @param targetArray Array to initialize
	 * @param summaryRow  Index of the first summary row
	 * @param rowPrefix   Prefix for row headers
	 */
	public static void initializeArrayWithPlaceholders(String[][] targetArray, int summaryRow, String rowPrefix) {
		for (int rowIndex = 1; rowIndex < targetArray.length; rowIndex++) {
			for (int colIndex = 0; colIndex < targetArray[rowIndex].length; colIndex++) {
				// Skip header cells in summary rows
				if (rowIndex >= summaryRow && colIndex == 0)
					continue;

				if (colIndex == 0 && rowIndex < summaryRow) {
					// Set row headers
					targetArray[rowIndex][colIndex] = rowPrefix + rowIndex;
				} else {
					// Set data cells to placeholder
					targetArray[rowIndex][colIndex] = "-";
				}
			}
		}
	}

	// ========== MAIN PROGRAM EXECUTION METHODS ==========

	/**
	 * Executes the main program loop for daily data processing
	 */
	public static void executeMainProgramLoop() {
		while (true) {
			displayProgramDashboard();

			if (currentDay <= totalDays) {
				processCurrentDayData();
			} else {
				generateFinalSummaryReport();
				break;
			}
		}
	}

	/**
	 * Displays the main program dashboard with overview data
	 */
	public static void displayProgramDashboard() {
		displayMainHeader();
		computeOverviewTotalsAndAverages();
		displayOverviewMatrix();
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
			computeDailyTotalsAndAverages();
			performDailyAnalyses();
			displayDailyDataMatrix();

			String userChoice = displayDataEntryMenu();
			if (userChoice.equals("1")) {
				collectIndividualDataEntries();
			} else if (userChoice.equals("2")) {
				saveCurrentDayData();
				return;
			}
		}
	}

	/**
	 * Displays the data entry menu and gets user selection
	 * 
	 * @return User's menu selection as String
	 */
	public static String displayDataEntryMenu() {
		displayMenuHeader("DATA ENTRY MENU");
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

	// ========== DATA COLLECTION METHODS ==========

	/**
	 * Collects individual data entries from the user
	 */
	public static void collectIndividualDataEntries() {
		displaySectionHeader("DATA INPUT");
		System.out.println(">>> Tip: Enter 'back' at any time to return to menu.");

		while (true) {
			int buildingIndex = promptForBuildingSelection();
			int resourceIndex = promptForResourceSelection();
			enterConsumptionValue(buildingIndex, resourceIndex);

			if (!promptForNextEntry())
				break;
		}
	}

	/**
	 * Prompts user to select a building
	 * 
	 * @return Selected building index
	 */
	public static int promptForBuildingSelection() {
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
	 * Prompts user to select a resource type
	 * 
	 * @return Selected resource index
	 */
	public static int promptForResourceSelection() {
		displayAvailableResources();

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
	 * Displays all available resource types to the user
	 */
	public static void displayAvailableResources() {
		System.out.println(">>> Available resources:");
		for (int i = 1; i < dailyTotalsCol; i++) {
			System.out.println("   " + i + ". " + dailyData[0][i]);
		}
	}

	/**
	 * Enters consumption value for a specific building and resource
	 * 
	 * @param buildingIndex Index of the building
	 * @param resourceIndex Index of the resource
	 */
	public static void enterConsumptionValue(int buildingIndex, int resourceIndex) {
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
	 * Prompts user whether to continue with next entry
	 * 
	 * @return true if user wants to continue, false otherwise
	 */
	public static boolean promptForNextEntry() {
		System.out.print(">>> Press [Enter] for next entry or type 'back' to finish: ");
		return !sc.nextLine().equalsIgnoreCase("back");
	}

	// ========== CALCULATION AND ANALYSIS METHODS ==========

	/**
	 * Computes all daily totals and averages
	 */
	public static void computeDailyTotalsAndAverages() {
		calculateBuildingWiseTotals();
		calculateResourceWiseTotals();
		calculateCampusWideAverage();
	}

	/**
	 * Calculates totals and averages for each building (row-wise)
	 */
	public static void calculateBuildingWiseTotals() {
		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			double buildingTotal = 0.0;
			int validDataCount = 0;

			for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
				Double consumption = parseDoubleSafely(dailyData[buildingIndex][resourceIndex]);
				if (consumption != null) {
					buildingTotal += consumption;
					validDataCount++;
				}
			}

			dailyData[buildingIndex][dailyTotalsCol] = String.format("%.2f", buildingTotal);
			dailyData[buildingIndex][dailyAveragesCol] = validDataCount > 0
					? String.format("%.2f", buildingTotal / validDataCount)
					: "0.00";
		}
	}

	/**
	 * Calculates totals and averages for each resource (column-wise)
	 */
	public static void calculateResourceWiseTotals() {
		for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
			double resourceTotal = 0.0;
			int validDataCount = 0;

			for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
				Double consumption = parseDoubleSafely(dailyData[buildingIndex][resourceIndex]);
				if (consumption != null) {
					resourceTotal += consumption;
					validDataCount++;
				}
			}

			dailyData[dailyTotalsRow][resourceIndex] = String.format("%.2f", resourceTotal);
			dailyData[dailyAveragesRow][resourceIndex] = validDataCount > 0
					? String.format("%.2f", resourceTotal / validDataCount)
					: "0.00";
		}
	}

	/**
	 * Calculates campus-wide average consumption
	 */
	public static void calculateCampusWideAverage() {
		double sumOfAverages = 0.0;
		int buildingCount = 0;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				sumOfAverages += buildingAverage;
				buildingCount++;
			}
		}

		dailyData[dailyAveragesRow][dailyAveragesCol] = buildingCount > 0
				? String.format("%.2f", sumOfAverages / buildingCount)
				: "0.00";
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
			// Skip summary columns
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

	/**
	 * Performs all daily analytical computations
	 */
	public static void performDailyAnalyses() {
		identifyInefficientBuildings();
		rankBuildingsByEfficiency();
		computePercentageDifferences();
	}

	/**
	 * Identifies buildings consuming significantly above average
	 */
	public static void identifyInefficientBuildings() {
		displaySectionHeader("EFFICIENCY ALERTS");
		final double INEFFICIENCY_THRESHOLD = 10.0; // 10% above average

		Double campusAverage = getCampusAverageConsumption();
		if (campusAverage == null) {
			displayErrorMessage("Insufficient data for inefficiency analysis");
			return;
		}

		boolean foundInefficientBuildings = false;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				double percentAboveAverage = calculatePercentageDifference(buildingAverage, campusAverage);

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
	 * Ranks buildings by efficiency and identifies top/bottom performers
	 */
	public static void rankBuildingsByEfficiency() {
		displaySectionHeader("EFFICIENCY RANKING");

		Integer mostEfficientIndex = findMostEfficientBuilding();
		Integer leastEfficientIndex = findLeastEfficientBuilding();

		if (mostEfficientIndex != null) {
			displayEfficiencyRanking(mostEfficientIndex, leastEfficientIndex);
		} else {
			displayErrorMessage("No valid data for efficiency analysis");
		}
		System.out.println();
	}

	/**
	 * Finds the most efficient building (lowest average consumption)
	 * 
	 * @return Index of most efficient building, or null if no data
	 */
	public static Integer findMostEfficientBuilding() {
		Double minConsumption = Double.MAX_VALUE;
		Integer bestBuildingIndex = null;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null && buildingAverage < minConsumption) {
				minConsumption = buildingAverage;
				bestBuildingIndex = buildingIndex;
			}
		}
		return bestBuildingIndex;
	}

	/**
	 * Finds the least efficient building (highest average consumption)
	 * 
	 * @return Index of least efficient building, or null if no data
	 */
	public static Integer findLeastEfficientBuilding() {
		Double maxConsumption = Double.NEGATIVE_INFINITY;
		Integer worstBuildingIndex = null;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null && buildingAverage > maxConsumption) {
				maxConsumption = buildingAverage;
				worstBuildingIndex = buildingIndex;
			}
		}
		return worstBuildingIndex;
	}

	/**
	 * Displays efficiency ranking results
	 * 
	 * @param bestBuilding  Index of most efficient building
	 * @param worstBuilding Index of least efficient building
	 */
	public static void displayEfficiencyRanking(Integer bestBuilding, Integer worstBuilding) {
		Double bestAverage = parseDoubleSafely(dailyData[bestBuilding][dailyAveragesCol]);
		System.out.println(
				"TOP PERFORMER: " + dailyData[bestBuilding][0] + " (Avg: " + String.format("%.2f", bestAverage) + ")");

		if (worstBuilding != null && !worstBuilding.equals(bestBuilding)) {
			Double worstAverage = parseDoubleSafely(dailyData[worstBuilding][dailyAveragesCol]);
			System.out.println("NEEDS IMPROVEMENT: " + dailyData[worstBuilding][0] + " (Avg: "
					+ String.format("%.2f", worstAverage) + ")");

			double efficiencyGap = worstAverage - bestAverage;
			System.out.println("Efficiency gap: " + String.format("%.2f", efficiencyGap) + " units");
		}
	}

	/**
	 * Computes and displays percentage differences from campus average
	 */
	public static void computePercentageDifferences() {
		Double campusAverage = getCampusAverageConsumption();
		if (campusAverage == null || campusAverage <= 0)
			return;

		System.out.println("PERCENT DIFFERENCES VS CAMPUS AVERAGE:");
		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAverage = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAverage != null) {
				double percentageDiff = calculatePercentageDifference(buildingAverage, campusAverage);
				System.out.printf("   %s: %.1f%% relative\n", dailyData[buildingIndex][0], percentageDiff);
			}
		}
		System.out.println();
	}

	/**
	 * Calculates percentage difference between two values
	 * 
	 * @param value The value to compare
	 * @param base  The base value for comparison
	 * @return Percentage difference
	 */
	public static double calculatePercentageDifference(double value, double base) {
		return ((value - base) / base) * 100.0;
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

		// Copy resource-wise totals
		for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
			String resourceTotal = dailyData[dailyTotalsRow][resourceIndex];
			overviewData[currentDay][resourceIndex] = resourceTotal;

			Double numericValue = parseDoubleSafely(resourceTotal);
			if (numericValue != null) {
				dayTotalConsumption += numericValue;
			}
		}

		// Calculate and store day totals and averages
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
				// Preserve header cells in summary rows
				if (rowIndex >= dailyTotalsRow && colIndex == 0)
					continue;

				if (colIndex == 0) {
					// Reset row headers
					dailyData[rowIndex][colIndex] = "Building " + rowIndex;
				} else {
					// Reset data cells to placeholder
					dailyData[rowIndex][colIndex] = "-";
				}
			}
		}
	}

	// ========== DISPLAY AND OUTPUT METHODS ==========

	/**
	 * Displays the daily data matrix
	 */
	public static void displayDailyDataMatrix() {
		System.out.println();
		displaySectionHeader("DAILY CONSUMPTION MATRIX - DAY " + currentDay);
		displayFormattedMatrix(dailyData);
	}

	/**
	 * Displays the overview data matrix
	 */
	public static void displayOverviewMatrix() {
		System.out.println();
		displaySectionHeader("CAMPUS OVERVIEW DASHBOARD");
		displayFormattedMatrix(overviewData);
	}

	/**
	 * Displays any matrix in a formatted table
	 * 
	 * @param matrix The matrix to display
	 */
	public static void displayFormattedMatrix(String[][] matrix) {
		// Print column headers
		System.out.printf("%-18s", matrix[0][0]);
		for (int colIndex = 1; colIndex < dailyTotalsCol; colIndex++) {
			System.out.printf("%-18s", matrix[0][colIndex]);
		}
		System.out.printf("%-18s%-18s\n", matrix[0][dailyTotalsCol], matrix[0][dailyAveragesCol]);

		// Print separator line
		System.out.println("=".repeat(18 * matrix[0].length));

		// Print data rows
		for (int rowIndex = 1; rowIndex < matrix.length; rowIndex++) {
			if (matrix[rowIndex][0] != null) {
				System.out.printf("%-18s", matrix[rowIndex][0]);

				for (int colIndex = 1; colIndex < matrix[rowIndex].length; colIndex++) {
					System.out.printf("%-18s", matrix[rowIndex][colIndex]);
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * Generates and displays the final summary report
	 */
	public static void generateFinalSummaryReport() {
		displaySectionHeader("COMPREHENSIVE SUMMARY REPORT");

		displayReportHeader();
		displayExecutiveSummary();
		displayStrategicRecommendations();
		displayReportFooter();

		System.out.println("\n>>> Thank you for using Smart Campus Resource Tracker!");
		System.out.println(">>> Making campuses smarter, one building at a time!");
	}

	/**
	 * Displays the executive summary section of the report
	 */
	public static void displayExecutiveSummary() {
		System.out.println("\nEXECUTIVE SUMMARY");
		System.out.println("--------------------------------------------------------------");

		try {
			ConsumptionSummary summary = calculateConsumptionSummary();

			System.out.println(">>> Tracking Period: " + totalDays + " days");
			System.out.println(">>> Buildings Monitored: " + (dailyData.length - 3));
			System.out.println(">>> Total Consumption: " + String.format("%.2f", summary.totalConsumption) + " units");

			if (summary.daysWithData > 0) {
				System.out.println(">>> Average Daily: "
						+ String.format("%.2f", summary.totalConsumption / summary.daysWithData) + " units/day");
			}
		} catch (Exception e) {
			displayErrorMessage("Error calculating summary statistics");
		}
	}

	/**
	 * Calculates consumption summary statistics
	 * 
	 * @return ConsumptionSummary object with calculated values
	 */
	public static ConsumptionSummary calculateConsumptionSummary() {
		double totalConsumption = 0.0;
		int daysWithData = 0;

		for (int dayIndex = 1; dayIndex < overviewTotalsRow; dayIndex++) {
			Double dayTotal = parseDoubleSafely(overviewData[dayIndex][dailyTotalsCol]);
			if (dayTotal != null) {
				totalConsumption += dayTotal;
				daysWithData++;
			}
		}

		return new ConsumptionSummary(totalConsumption, daysWithData);
	}

	/**
	 * Displays strategic recommendations
	 */
	public static void displayStrategicRecommendations() {
		System.out.println("\nSTRATEGIC RECOMMENDATIONS");
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. Implement targeted efficiency programs");
		System.out.println("2. Share best practices from top-performing buildings");
		System.out.println("3. Conduct energy audits for high-consumption areas");
		System.out.println("4. Develop real-time monitoring dashboard");
		System.out.println("5. Train staff on resource conservation practices");
	}

	// ========== HELPER METHODS ==========

	/**
	 * Safely parses a string to Double, returns null if invalid
	 * 
	 * @param value String value to parse
	 * @return Parsed Double value, or null if invalid
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
	 * Gets the campus average consumption
	 * 
	 * @return Campus average, or null if no data
	 */
	public static Double getCampusAverageConsumption() {
		// Try to get from pre-calculated value first
		Double campusAvg = parseDoubleSafely(dailyData[dailyAveragesRow][dailyAveragesCol]);
		if (campusAvg != null)
			return campusAvg;

		// Fallback calculation
		return calculateFallbackCampusAverage();
	}

	/**
	 * Calculates campus average as fallback
	 * 
	 * @return Campus average, or null if no data
	 */
	public static Double calculateFallbackCampusAverage() {
		double sum = 0.0;
		int count = 0;

		for (int buildingIndex = 1; buildingIndex < dailyTotalsRow; buildingIndex++) {
			Double buildingAvg = parseDoubleSafely(dailyData[buildingIndex][dailyAveragesCol]);
			if (buildingAvg != null) {
				sum += buildingAvg;
				count++;
			}
		}

		return count > 0 ? sum / count : null;
	}

	/**
	 * Gets validated integer input from user
	 * 
	 * @param prompt Message to display to user
	 * @param min    Minimum acceptable value
	 * @param max    Maximum acceptable value
	 * @return Validated integer input
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
	 * Prompts user to press Enter to continue
	 */
	public static void promptUserToContinue() {
		System.out.println(">>> Ready to compute Day " + currentDay + "/" + totalDays + " data");
		System.out.println(">>> Press [Enter] to continue...");
		sc.nextLine();
	}

	// ========== UI DISPLAY METHODS ==========

	/**
	 * Displays the welcome banner
	 */
	public static void displayWelcomeBanner() {
		System.out.println("==============================================================");
		System.out.println("               SMART CAMPUS RESOURCE TRACKER                  ");
		System.out.println("           Optimizing Campus Resource Efficiency              ");
		System.out.println("==============================================================");
		System.out.println();
	}

	/**
	 * Displays the main program header
	 */
	public static void displayMainHeader() {
		System.out.println("\n--------------------------------------------------------------");
		System.out.println(
				"                 CAMPUS DASHBOARD - DAY " + currentDay + "/" + totalDays + "                 ");
		System.out.println("--------------------------------------------------------------");
	}

	/**
	 * Displays a section header with centered title
	 * 
	 * @param title The section title to display
	 */
	public static void displaySectionHeader(String title) {
		int padding = (60 - title.length()) / 2;
		String leftPadding = " ".repeat(Math.max(0, padding));
		String rightPadding = " ".repeat(Math.max(0, 60 - title.length() - padding));

		System.out.println("\n==============================================================");
		System.out.println(leftPadding + title + rightPadding);
		System.out.println("==============================================================");
	}

	/**
	 * Displays a menu header
	 * 
	 * @param title The menu title
	 */
	public static void displayMenuHeader(String title) {
		System.out.println("==========================================");
		System.out.println("           " + title + "            ");
		System.out.println("==========================================");
	}

	/**
	 * Displays the report header
	 */
	public static void displayReportHeader() {
		System.out.println("==============================================================");
		System.out.println("           GLOBALTECH INSTITUTE                        ");
		System.out.println("           SMART CAMPUS RESOURCE MANAGEMENT                  ");
		System.out.println("==============================================================");
	}

	/**
	 * Displays the report footer
	 */
	public static void displayReportFooter() {
		System.out.println("\n==============================================================");
		System.out.println("           REPORT GENERATED BY SMART CAMPUS TRACKER           ");
		System.out.println("                 Driving Sustainability                ");
		System.out.println("==============================================================");
	}

	/**
	 * Displays a success message
	 * 
	 * @param message The success message to display
	 */
	public static void displaySuccessMessage(String message) {
		System.out.println(">>> SUCCESS: " + message);
	}

	/**
	 * Displays an error message
	 * 
	 * @param message The error message to display
	 */
	public static void displayErrorMessage(String message) {
		System.out.println(">>> ERROR: " + message);
	}

	// ========== REQUIREMENT WRAPPER METHODS ==========

	/**
	 * Wrapper method to satisfy "computeBuildingTotals" requirement
	 */
	public static void computeBuildingTotals() {
		computeDailyTotalsAndAverages();
	}

	/**
	 * Wrapper method to satisfy "computeResourceAverages" requirement
	 */
	public static void computeResourceAverages() {
		computeDailyTotalsAndAverages();
	}

	/**
	 * Helper class for consumption summary data
	 */
	static class ConsumptionSummary {
		public double totalConsumption;
		public int daysWithData;

		public ConsumptionSummary(double totalConsumption, int daysWithData) {
			this.totalConsumption = totalConsumption;
			this.daysWithData = daysWithData;
		}
	}
}