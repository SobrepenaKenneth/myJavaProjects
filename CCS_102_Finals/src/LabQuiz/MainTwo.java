package LabQuiz;

import java.util.Scanner;

/**
 * Campus Resource Tracker System Version: v29.2 -
 * 
 * This program tracks resource consumption across multiple buildings and
 * provides analytical insights for efficiency improvements.
 * 
 */
public class MainTwo {

	// Class variables and data structures
	static Scanner sc = new Scanner(System.in);
	public static String[][] buildings; // Daily building data matrix
	public static String[][] overview; // Overview data across all days
	public static int currentDay = 1; // Current tracking day
	public static int maxDays = 0; // Maximum days to track
	public static int averageRow; // Row index for column averages
	public static int totalsRow; // Row index for column totals
	public static int averageColumn; // Column index for row averages
	public static int totalsColumn; // Column index for row totals
	public static int overVAverageRow; // Overview average row index
	public static int overVTotalsRow; // Overview totals row index

	// Enhanced functionality variables
	public static String[] buildingNames; // Array of building names
	public static double[][] dailyEfficiency; // Efficiency metrics storage

	/**
	 * MAIN METHOD - Program entry point Orchestrates the overall program flow
	 * including setup, daily operations, and final summary reporting.
	 */
	public static void main(String[] args) {
	    setArraySize();
	    dailyEfficiency = new double[maxDays][2];
	    
	    // Process each day
	    for (int day = 1; day <= maxDays; day++) {
	        currentDay = day; // Set the current day
	        displayHeader();
	        OverviewTotalsAndAverages();
	        displayOverview();

	        if (day < maxDays) {
	            System.out.println("=========================================================");
	            System.out.println("[Enter] Compute Day " + (day + 1) + "/" + maxDays + " data sheet");
	            System.out.println("=========================================================");
	        } else {
	            System.out.println("=========================================================");
	            System.out.println("[Enter] Compute Final Day " + day + "/" + maxDays + " data sheet");
	            System.out.println("=========================================================");
	        }
	        
	        sc.nextLine();
	        computeDailies();
	    }
	    
	    // After all days are completed
	    displayHeader();
	    System.out.println("=========================================================");
	    System.out.println("[Enter] View Summary Report");
	    System.out.println("=========================================================");
	    sc.nextLine();
	    generateSummaryReport();
	}

	/**
	 * COMPUTE DAILIES - Manages daily data operations
	 */
	public static void computeDailies() {
		while (true) {
			computeBuildingTotals();
			computeResourceAverages();
			findMostEfficientBuilding();
			detectInefficiencies();
			calculateVariance();
			displayMatrix();

			System.out.println("=========================================================");
			System.out.println("DAILY OPERATIONS");
			System.out.println("=========================================================");
			System.out.println("[1] Input Data Individually");
			System.out.println("[2] Finalise Data and Save to Overview");
			System.out.println("=========================================================");
			System.out.print("Select option: ");
			String input = sc.nextLine();
			switch (input) {
			case "1":
				inputData();
				break;
			case "2":
				FinaliseAndSave();
				return;
			default:
				System.out.println("=========================================================");
				System.out.println("Invalid option! Please choose 1 or 2.");
				System.out.println("=========================================================");
				break;
			}
		}
	}

	// =========================================================================
	// REQUIRED USER-DEFINED METHODS (7 METHODS)
	// =========================================================================

	/**
	 * METHOD 1: inputData() - Accept and validate resource usage inputs
	 */
	public static void inputData() {
		String input = "";
		String exitKey = "exit";
		int columnQuery = 0;
		int rowQuery = 0;

		System.out.println("=========================================================");
		System.out.println("DATA INPUT");
		System.out.println("=========================================================");
		while (true) {
			rowQuery = askRow();
			columnQuery = askColumn();
			addData(rowQuery, columnQuery);

			System.out.println("=========================================================");
			System.out.println(buildingNames[rowQuery - 1] + "-" + buildings[0][columnQuery] + " successfully modified");
			System.out.println("=========================================================");
			System.out.println("Press [Enter] to input a new set");
			System.out.println("Type '" + exitKey + "' to return to list");
			System.out.println("=========================================================");
			input = sc.nextLine();
			if (input.equalsIgnoreCase(exitKey))
				break;
		}
	}

	/**
	 * METHOD 2: displayMatrix() - Display all data in formatted tabular form
	 */
	public static void displayMatrix() {
		System.out.println("\n=========================================================");
		System.out.println("DAILY RESOURCE CONSUMPTION - Day " + currentDay);
		System.out.println("=========================================================");

		// Print header row with resource type names
		System.out.printf("%-15s", "");
		for (int col = 1; col < buildings[0].length - 2; col++) {
			System.out.printf("%-12s", buildings[0][col]);
		}
		System.out.printf("%-15s%-15s\n", "Total", "Average");
		System.out.println("=========================================================");

		// Print data rows for each building
		for (int row = 1; row < buildings.length; row++) {
			if (buildings[row][0] != null) {
				System.out.printf("%-15s", buildings[row][0]);
				for (int col = 1; col < buildings[row].length; col++) {
					if (col < buildings[row].length - 2) {
						System.out.printf("%-12s", buildings[row][col]);
					} else {
						System.out.printf("%-15s", buildings[row][col]);
					}
				}
				System.out.println();
			}
		}
		System.out.println("=========================================================\n");
	}

	/**
	 * METHOD 3: computeBuildingTotals() - Calculate total and average usage per building
	 */
	public static void computeBuildingTotals() {
		double total = 0;
		int itemCount = 0;

		for (int row = 1; row < totalsRow; row++) {
			for (int column = 1; column < totalsColumn; column++) {
				try {
					total += Double.parseDouble(buildings[row][column]);
					itemCount++;
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}
			buildings[row][totalsColumn] = String.format("%.2f", total);
			if (itemCount != 0)
				buildings[row][averageColumn] = String.format("%.2f", total / itemCount);
			else
				buildings[row][averageColumn] = "0.00";
			total = 0;
			itemCount = 0;
		}

		System.out.println("=========================================================");
		System.out.println("BUILDING TOTALS COMPUTED");
		System.out.println("=========================================================");
	}

	/**
	 * METHOD 4: computeResourceAverages() - Calculate average usage per resource type
	 */
	public static void computeResourceAverages() {
		double total = 0;
		double currItem = 0;
		int itemCount = 0;

		for (int column = 1; column < buildings[0].length; column++) {
			for (int row = 1; row < totalsRow; row++) {
				try {
					currItem = Double.parseDouble(buildings[row][column]);
					total += currItem;
					if (currItem > 0)
						itemCount++;
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}
			if (column != averageColumn) {
				buildings[totalsRow][column] = String.format("%.2f", total);
				if (itemCount != 0)
					buildings[averageRow][column] = String.format("%.2f", total / itemCount);
				else
					buildings[averageRow][column] = "0.00";
			}
			total = 0;
			itemCount = 0;
		}

		System.out.println("=========================================================");
		System.out.println("RESOURCE AVERAGES COMPUTED");
		System.out.println("=========================================================");
	}

	/**
	 * METHOD 5: detectInefficiencies() - Identify buildings exceeding average resource consumption
	 */
	public static void detectInefficiencies() {
		System.out.println("=========================================================");
		System.out.println("EFFICIENCY ANALYSIS");
		System.out.println("=========================================================");

		try {
			double overallAverage = Double.parseDouble(buildings[averageRow][averageColumn]);
			System.out.printf("Overall Average Consumption: %.2f\n", overallAverage);
			System.out.println("=========================================================");
			System.out.println("Buildings exceeding average consumption:");
			boolean found = false;

			for (int row = 1; row < totalsRow; row++) {
				try {
					double buildingAvg = Double.parseDouble(buildings[row][averageColumn]);
					double percentDiff = ((buildingAvg - overallAverage) / overallAverage) * 100;

					if (percentDiff > 10) {
						System.out.printf("- %s: %.2f (%.1f%% above average)\n", buildings[row][0], buildingAvg, percentDiff);
						found = true;
					}
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}

			if (!found) {
				System.out.println("No buildings significantly exceed average consumption.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Insufficient data for efficiency analysis.");
		}
		System.out.println("=========================================================");
	}

	/**
	 * METHOD 6: findMostEfficientBuilding() - Determine building with lowest average consumption
	 */
	public static void findMostEfficientBuilding() {
		double minEfficiency = Double.MAX_VALUE;
		double maxEfficiency = Double.MIN_VALUE;
		int mostEffIndex = -1;
		int leastEffIndex = -1;

		for (int row = 1; row < totalsRow; row++) {
			try {
				double efficiency = Double.parseDouble(buildings[row][averageColumn]);
				if (efficiency < minEfficiency && efficiency > 0) {
					minEfficiency = efficiency;
					mostEffIndex = row;
				}
				if (efficiency > maxEfficiency) {
					maxEfficiency = efficiency;
					leastEffIndex = row;
				}
			} catch (NumberFormatException e) {
				// Skip invalid data
			}
		}

		System.out.println("=========================================================");
		System.out.println("EFFICIENCY RANKING");
		System.out.println("=========================================================");
		if (mostEffIndex != -1) {
			System.out.printf("Most Efficient: %s (Avg: %.2f)\n", buildings[mostEffIndex][0], minEfficiency);
			dailyEfficiency[currentDay - 1][0] = minEfficiency;
		} else {
			System.out.println("No efficient building found (insufficient data)");
		}
		if (leastEffIndex != -1) {
			System.out.printf("Least Efficient: %s (Avg: %.2f)\n", buildings[leastEffIndex][0], maxEfficiency);
			dailyEfficiency[currentDay - 1][1] = maxEfficiency;
		}
		System.out.println("=========================================================");
	}

	/**
	 * METHOD 7: generateSummaryReport() - Display and interpret all analytical findings
	 */
	public static void generateSummaryReport() {
		System.out.println("=========================================================");
		System.out.println("GLOBALTECH INSTITUTE");
		System.out.println("SMART CAMPUS RESOURCE MANAGEMENT");
		System.out.println("=========================================================");

		try {
			double overallAvg = Double.parseDouble(overview[overVAverageRow][averageColumn]);
			double overallTotal = Double.parseDouble(overview[overVTotalsRow][totalsColumn]);

			System.out.println("OVERALL STATISTICS");
			System.out.println("=========================================================");
			System.out.printf("Tracking Period: %d days\n", maxDays);
			System.out.printf("Total Resource Consumption: %.2f units\n", overallTotal);
			System.out.printf("Average Daily Consumption: %.2f units\n", overallAvg);

			System.out.println("=========================================================");
			System.out.println("EFFICIENCY ANALYSIS");
			System.out.println("=========================================================");
			analyzeOverallEfficiency();

		} catch (NumberFormatException e) {
			System.out.println("Insufficient data for overall statistics.");
		}

		System.out.println("=========================================================");
		System.out.println("RESOURCE EFFICIENCY ANALYSIS");
		System.out.println("=========================================================");
		analyzeResourceEfficiency();

		System.out.println("=========================================================");
		System.out.println("RECOMMENDATIONS");
		System.out.println("=========================================================");
		generateRecommendations();

		System.out.println("\n========================================================");
		System.out.println("           REPORT GENERATED BY SMART CAMPUS TRACKER           ");
		System.out.println("                 Driving Sustainability                       ");
		System.out.println("========================================================");
		
		System.out.println("\n>>> Thank you for using Smart Campus Resource Tracker!");
		System.out.println(">>> Making campuses smarter, one building at a time!");
	}

	// =========================================================================
	// HELPER METHODS
	// =========================================================================

	/**
	 * HELPER: displayHeader() - Display program header
	 */
	public static void displayHeader() {
	    System.out.println("\n=========================================================");
	    System.out.println("Campus Dashboard - Day " + currentDay + " of " + maxDays);
	    System.out.println("=========================================================");
	}

	/**
	 * HELPER: displayOverview() - Display cumulative overview data
	 */
	public static void displayOverview() {
		System.out.println("=========================================================");
		System.out.println("Campus Overview Dashboard");
		System.out.println("=========================================================");

		// Print header row
		System.out.printf("%-15s", "");
		for (int col = 1; col < overview[0].length - 2; col++) {
			System.out.printf("%-12s", overview[0][col]);
		}
		System.out.printf("%-15s%-15s\n", "Total", "Average");
		System.out.println("=========================================================");

		// Print data rows
		for (int row = 1; row < overview.length; row++) {
			if (overview[row][0] != null) {
				System.out.printf("%-15s", overview[row][0]);
				for (int col = 1; col < overview[row].length; col++) {
					if (col < overview[row].length - 2) {
						System.out.printf("%-12s", overview[row][col]);
					} else {
						System.out.printf("%-15s", overview[row][col]);
					}
				}
				System.out.println();
			}
		}
		System.out.println("=========================================================\n");
	}

	/**
	 * HELPER: askRow() - Get valid building selection from user
	 */
	public static int askRow() {
		int rowQuery = 0;
		String input;
		while (rowQuery <= 0 || rowQuery >= totalsRow) {
			System.out.print("Select building to modify (1-" + (buildings.length - 3) + "): ");
			input = sc.nextLine();
			try {
				rowQuery = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("=========================================================");
				System.out.println("Invalid Input: Not an Integer");
				System.out.println("=========================================================");
				rowQuery = 0;
			}
		}
		System.out.println("=========================================================");
		System.out.println("'" + buildingNames[rowQuery - 1] + "' selected.");
		System.out.println("=========================================================");
		return rowQuery;
	}

	/**
	 * HELPER: askColumn() - Get valid resource type selection from user
	 */
	public static int askColumn() {
		String input;
		int columnQuery = 0;

		while (columnQuery <= 0) {
			System.out.print("Select resource to modify: ");
			input = sc.nextLine();
			for (int column = 1; column < totalsColumn; column++) {
				if (input.equalsIgnoreCase(buildings[0][column])) {
					columnQuery = column;
					System.out.println("=========================================================");
					System.out.println("'" + buildings[0][column] + "' selected.");
					System.out.println("=========================================================");
					break;
				}
			}
			if (columnQuery == 0) {
				System.out.println("=========================================================");
				System.out.println("Resource not found. Available resources:");
				System.out.println("=========================================================");
				for (int column = 1; column < totalsColumn; column++) {
					System.out.println("- " + buildings[0][column]);
				}
				System.out.println("=========================================================");
			}
		}
		return columnQuery;
	}

	/**
	 * HELPER: addData() - Add validated data to the matrix
	 */
	public static void addData(int rowQuery, int columnQuery) {
		String input;
		Double finalData = null;
		while (finalData == null) {
			System.out.print("Input data for " + buildingNames[rowQuery - 1] + " - " + buildings[0][columnQuery] + " (Int/Double): ");
			input = sc.nextLine();
			try {
				finalData = Double.parseDouble(input);
				if (finalData < 0) {
					System.out.println("=========================================================");
					System.out.println("Error: Consumption cannot be negative!");
					System.out.println("=========================================================");
					finalData = null;
				} else {
					buildings[rowQuery][columnQuery] = input;
				}
			} catch (NumberFormatException e) {
				System.out.println("=========================================================");
				System.out.println("Invalid Input! Please enter a valid number.");
				System.out.println("=========================================================");
			}
		}
	}

	/**
	 * HELPER: setArraySize() - Initialize data structures
	 */
	public static void setArraySize() {
		int row = 0;
		int column = 0;

		System.out.println("=========================================================");
		System.out.println("SMART CAMPUS RESOURCE TRACKER");
		System.out.println("Optimizing Campus Resource Efficiency");
		System.out.println("=========================================================");

		// Get number of buildings
		System.out.print("How many buildings would you like to track?: ");
		while (row <= 0) {
			try {
				row = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				row = 0;
				System.out.println("=========================================================");
				System.out.println("Invalid Input! Please enter a positive number.");
				System.out.println("=========================================================");
				System.out.print("How many buildings would you like to track?: ");
			}
		}

		// Get number of resource types
		System.out.print("How many Resource types would you like to track?: ");
		while (column <= 0) {
			try {
				column = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				column = 0;
				System.out.println("=========================================================");
				System.out.println("Invalid Input! Please enter a positive number.");
				System.out.println("=========================================================");
				System.out.print("How many Resource types would you like to track?: ");
			}
		}

		// Get number of tracking days
		System.out.print("How many days would you like to track?: ");
		while (maxDays <= 0) {
			try {
				maxDays = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				maxDays = 0;
				System.out.println("=========================================================");
				System.out.println("Invalid Input! Please enter a positive number.");
				System.out.println("=========================================================");
				System.out.print("How many days would you like to track?: ");
			}
		}

		// Initialize building names array
		buildingNames = new String[row];
		System.out.println("=========================================================");
		System.out.println("BUILDING NAME SETUP");
		System.out.println("=========================================================");
		for (int i = 0; i < row; i++) {
			System.out.print("Building " + (i + 1) + " name: ");
			buildingNames[i] = sc.nextLine();
		}

		// Initialize daily buildings matrix
		buildings = new String[row + 3][column + 3];
		totalsRow = buildings.length - 2;
		averageRow = buildings.length - 1;
		totalsColumn = buildings[0].length - 2;
		averageColumn = buildings[0].length - 1;

		buildings[0][0] = "Name";
		buildings[totalsRow][0] = "Total";
		buildings[averageRow][0] = "Average";
		buildings[0][totalsColumn] = "Building Total";
		buildings[0][averageColumn] = "Building Avg.";

		// Initialize overview matrix
		overview = new String[maxDays + 3][column + 3];
		overVTotalsRow = overview.length - 2;
		overVAverageRow = overview.length - 1;
		overview[0][0] = "Name";
		overview[overVTotalsRow][0] = "Total";
		overview[overVAverageRow][0] = "Average";
		overview[0][totalsColumn] = "Building Total";
		overview[0][averageColumn] = "Building Avg.";

		// Get resource type names
		System.out.println("=========================================================");
		System.out.println("RESOURCE NAME SETUP");
		System.out.println("=========================================================");
		for (int name = 1; name < totalsColumn; name++) {
			while (buildings[0][name] == null || buildings[0][name].length() > 9) {
				System.out.print("Provide a name for resource " + name + "(Char. Limit: 10): ");
				String in = sc.nextLine();
				buildings[0][name] = in;
				overview[0][name] = in;
				if (buildings[0][name].length() > 9) {
					System.out.println("=========================================================");
					System.out.println("Length exceeds character limit.");
					System.out.println("=========================================================");
				}
			}
		}

		// Initialize building matrix with names and placeholders
		for (int r = 1; r < buildings.length; r++) {
			for (int c = 0; c < buildings[r].length; c++) {
				if (r >= totalsRow && c == 0)
					continue;
				if (c == 0 && r < totalsRow)
					buildings[r][c] = buildingNames[r - 1];
				else if (c == 0)
					buildings[r][c] = (r == totalsRow) ? "Total" : "Average";
				else
					buildings[r][c] = "-";
			}
		}

		// Initialize overview matrix with day labels
		for (int r = 1; r < overview.length; r++) {
			for (int c = 0; c < overview[r].length; c++) {
				if (r >= overVTotalsRow && c == 0)
					continue;
				if (c == 0)
					overview[r][c] = "Day " + r;
				else
					overview[r][c] = "-";
			}
		}

		System.out.println("=========================================================");
		System.out.println("Data Set with " + row + " buildings and " + column + " resource types created.");
		System.out.println("=========================================================");
	}

	/**
	 * HELPER: OverviewTotalsAndAverages() - Calculate overview totals and averages
	 */
	public static void OverviewTotalsAndAverages() {
		double total = 0;
		double currItem = 0;
		int itemCount = 0;

		for (int row = 1; row < overVTotalsRow; row++) {
			for (int column = 1; column < totalsColumn; column++) {
				try {
					total += Double.parseDouble(overview[row][column]);
					itemCount++;
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}
			overview[row][totalsColumn] = String.format("%.2f", total);
			if (itemCount != 0)
				overview[row][averageColumn] = String.format("%.2f", total / itemCount);
			total = 0;
			itemCount = 0;
		}

		for (int column = 1; column < overview[0].length; column++) {
			for (int row = 1; row < overVTotalsRow; row++) {
				try {
					currItem = Double.parseDouble(overview[row][column]);
					total += currItem;
					if (currItem > 0)
						itemCount++;
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}
			if (column != averageColumn) {
				overview[overVTotalsRow][column] = String.format("%.2f", total);
				if (itemCount != 0)
					overview[overVAverageRow][column] = String.format("%.2f", total / itemCount);
				else
					overview[overVAverageRow][column] = "0.00";
			}
			total = 0;
			itemCount = 0;
		}
	}

	/**
	 * HELPER: calculateVariance() - Calculate statistical variance
	 */
	public static void calculateVariance() {
		System.out.println("=========================================================");
		System.out.println("RESOURCE VARIANCE ANALYSIS");
		System.out.println("=========================================================");

		for (int col = 1; col < totalsColumn; col++) {
			double sum = 0;
			double sumSquared = 0;
			int count = 0;

			for (int row = 1; row < totalsRow; row++) {
				try {
					double value = Double.parseDouble(buildings[row][col]);
					sum += value;
					sumSquared += value * value;
					count++;
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}

			if (count > 1) {
				double mean = sum / count;
				double variance = (sumSquared - (sum * sum) / count) / count;
				System.out.printf("%s: Mean=%.2f, Variance=%.2f\n", buildings[0][col], mean, variance);
			}
		}
		System.out.println("=========================================================");
	}

	/**
	 * HELPER: FinaliseAndSave() - Save daily data and prepare for next day
	 */
	public static void FinaliseAndSave() {
		// Save current day's totals to overview
		for (int column = 1; column < buildings[0].length; column++) {
			overview[currentDay][column] = buildings[totalsRow][column];
			if (column == averageColumn)
				overview[currentDay][column] = buildings[averageRow][totalsColumn];
		}

		// Reset daily data for next day while preserving building names
		for (int r = 1; r < buildings.length; r++) {
			for (int c = 0; c < buildings[r].length; c++) {
				if (r >= totalsRow && c == 0)
					continue;
				if (c == 0 && r < totalsRow) {
					buildings[r][c] = buildingNames[r - 1];
				} else if (c == 0) {
					buildings[r][c] = (r == totalsRow) ? "Total" : "Average";
				} else {
					buildings[r][c] = "-";
				}
			}
		}
	}

	/**
	 * HELPER: analyzeOverallEfficiency() - Analyze overall efficiency trends
	 */
	public static void analyzeOverallEfficiency() {
		double bestEfficiency = Double.MAX_VALUE;
		double worstEfficiency = 0;

		for (int day = 0; day < maxDays; day++) {
			if (dailyEfficiency[day][0] < bestEfficiency && dailyEfficiency[day][0] > 0) {
				bestEfficiency = dailyEfficiency[day][0];
			}
			if (dailyEfficiency[day][1] > worstEfficiency) {
				worstEfficiency = dailyEfficiency[day][1];
			}
		}

		if (bestEfficiency < Double.MAX_VALUE) {
			System.out.printf("Best Daily Efficiency: %.2f units\n", bestEfficiency);
		}
		if (worstEfficiency > 0) {
			System.out.printf("Worst Daily Efficiency: %.2f units\n", worstEfficiency);
		}
	}

	/**
	 * HELPER: analyzeResourceEfficiency() - Analyze resource-specific efficiency
	 */
	public static void analyzeResourceEfficiency() {
		double minEfficiency = Double.MAX_VALUE;
		double maxEfficiency = Double.MIN_VALUE;
		String mostEfficientResource = "";
		String leastEfficientResource = "";

		for (int col = 1; col < totalsColumn; col++) {
			try {
				double resourceAvg = Double.parseDouble(overview[overVAverageRow][col]);

				if (resourceAvg < minEfficiency) {
					minEfficiency = resourceAvg;
					mostEfficientResource = overview[0][col];
				}
				if (resourceAvg > maxEfficiency) {
					maxEfficiency = resourceAvg;
					leastEfficientResource = overview[0][col];
				}
			} catch (NumberFormatException e) {
				// Skip invalid data
			}
		}

		if (!mostEfficientResource.isEmpty()) {
			System.out.printf("Most Efficient Resource: %s (Avg: %.2f)\n", mostEfficientResource, minEfficiency);
		}
		if (!leastEfficientResource.isEmpty()) {
			System.out.printf("Least Efficient Resource: %s (Avg: %.2f)\n", leastEfficientResource, maxEfficiency);
		}
	}

	/**
	 * HELPER: generateRecommendations() - Generate improvement recommendations
	 */
	public static void generateRecommendations() {
		System.out.println("1. Energy audit for buildings >10% above average");
		System.out.println("2. Implement resource usage benchmarks");
		System.out.println("3. Install smart meters for monitoring");
		System.out.println("4. Conservation initiatives for high consumption");
		System.out.println("5. Regular maintenance checks");
		System.out.println("6. Review and optimize high-consumption resources");
		System.out.println("7. Implement energy-saving best practices");
	}
}