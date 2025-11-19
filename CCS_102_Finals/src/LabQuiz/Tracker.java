package LabQuiz;

import java.util.Scanner;

public class Tracker {

	// Code Version: v22.0 - Modified for Requirements

	static Scanner sc = new Scanner(System.in);
	public static String[][] buildings;
	public static String[][] overview;
	public static int currentDay = 1;
	public static int maxDays = 0;
	public static int averageRow; // The index that contains Per-Column Averages
	public static int totalsRow; // Per-Column Totals
	public static int averageColumn; // Per Row Avg
	public static int totalsColumn; // Per Row Totals
	public static int overVAverageRow; // For the overview sheet
	public static int overVTotalsRow;

	// Constants for resource categories as required
	private static final String[] DEFAULT_RESOURCES = { "Electricity", "Water", "Internet", "Lab Equipment" };
	private static final int NUM_DEFAULT_RESOURCES = 4;

	public static void main(String[] args) {
		setArraySize();
		while (true) {
			System.out.println("- Smart Campus Resource Utilization and Efficiency Tracker -");
			O_ComputeTotalsAndAverages();
			displayOverview();

			if (currentDay <= maxDays) {
				System.out.println("[Enter] Compute Day " + currentDay + "/" + maxDays + " data sheet");
				sc.nextLine();
				computeDailies();
			} else {
				System.out.println("[Enter] View Summary Report");
				sc.nextLine();
				generateSummaryReport();
				break; // Added to prevent infinite loop after report
			}
		}
		sc.close(); // Close scanner when done
	}

	public static void computeDailies() {
		while (true) {
			DailyComputeTotalsAndAverages();
			findMostEfficientBuilding();
			detectInefficiencies(); // Fixed method name to match requirements

			displayMatrix();
			System.out.println("[1] Input Data Individually");
			System.out.println("[2] Finalise Data and Save to Overview");
			String input = sc.nextLine();
			switch (input) {
			case "1":
				inputData(); // Fixed method name to match requirements
				break;
			case "2":
				FinaliseAndSave();
				return;
			default:
				break;
			}
		}
	}

	public static void setArraySize() {
		int row = 0;
		int column = 0;

		System.out.println("================= GlobalTech Institute =================");
        System.out.println("Smart Campus Resource Utilization and Efficiency Tracker");
        System.out.println("========================================================\n");
		System.out.print("> How many buildings would you like to track?: ");
		while (row <= 0) {
			try {
				row = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				row = 0;
				System.out.println("Invalid Input!");
			}
		}

		System.out.print("> How many Resource types would you like to track? (Recommended: 4 for default): ");
		while (column <= 0) {
			try {
				column = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				column = 0;
				System.out.println("Invalid Input!");
			}
		}

		System.out.print("> How many days would you like to track?: ");
		while (maxDays <= 0) {
			try {
				maxDays = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				maxDays = 0;
				System.out.println("Invalid Input!");
			}
		}

		// For Daily Data
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

		// For the Overview Data
		overview = new String[maxDays + 3][column + 3];
		overVTotalsRow = overview.length - 2;
		overVAverageRow = overview.length - 1;
		overview[0][0] = "Name";
		overview[overVTotalsRow][0] = "Total";
		overview[overVAverageRow][0] = "Average";
		overview[0][totalsColumn] = "Building Total";
		overview[0][averageColumn] = "Building Avg.";

		System.out.println("Please provide identifiers(names) for each resource type:");

		// Use default resource names if user chooses 4 resources, otherwise custom
		for (int name = 1; name < totalsColumn; name++) {
			while (buildings[0][name] == null || buildings[0][name].length() > 15) { // Increased character limit
				if (column == NUM_DEFAULT_RESOURCES && name <= DEFAULT_RESOURCES.length) {
					buildings[0][name] = DEFAULT_RESOURCES[name - 1];
					overview[0][name] = DEFAULT_RESOURCES[name - 1];
					System.out.println("Resource " + name + " set to: " + DEFAULT_RESOURCES[name - 1]);
					break;
				} else {
					System.out.print("Provide a name for resource " + name + "(Char. Limit: 15): ");
					String in = sc.nextLine();
					buildings[0][name] = in;
					overview[0][name] = in;
					if (buildings[0][name].length() > 15)
						System.out.println("> Length exceeds character limit.");
				}
			}
		}

		// Initialize buildings array
		for (int r = 1; r < buildings.length; r++) {
			for (int c = 0; c < buildings[r].length; c++) {
				if (r >= totalsRow && c == 0)
					continue;
				if (c == 0)
					buildings[r][c] = "Building " + r;
				else
					buildings[r][c] = "-";
			}
		}

		// Initialize overview array
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

		System.out.println("> Data Set with " + row + " buildings and " + column + " resource types has been created.");
	}

	// CHANGED: Renamed to match requirements exactly
	public static void inputData() {
		String input = "";
		String exitKey = "exit";
		int columnQuery = 0;
		int rowQuery = 0;

		System.out.println("== [ Data Input ] == ");
		while (true) {
			rowQuery = askRow();
			columnQuery = askColumn();
			addData(rowQuery, columnQuery);
			System.out.println("== " + rowQuery + "-" + columnQuery + " successfully modified == ");
			System.out.println("-- Press [Enter] to input a new set, Type " + exitKey + " to return to list -- ");
			input = sc.nextLine();
			if (input.equalsIgnoreCase(exitKey))
				break;
		}
	}

	public static int askRow() {
		int rowQuery = 0;
		String input;
		while (rowQuery <= 0 || rowQuery >= totalsRow) {
			System.out.print("> Select building to modify (1-" + (buildings.length - 3) + "): ");
			input = sc.nextLine();
			try {
				rowQuery = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(">Invalid Input: Not an Integer");
				rowQuery = 0;
			}
		}
		System.out.println("> Building '" + rowQuery + "' selected.");
		return rowQuery;
	}

	public static int askColumn() {
		String input;
		int columnQuery = 0;
		System.out.print("> Select resource to modify: ");
		input = sc.nextLine();
		while (columnQuery <= 0) {
			for (int column = 1; column < totalsColumn; column++) {
				if (input.equalsIgnoreCase(buildings[0][column])) {
					columnQuery = column;
					System.out.println("> '" + buildings[0][column] + "' selected.");
					break;
				}
			}
			if (columnQuery == 0) {
				System.out.println("Resource not found. Available resources:");
				for (int column = 1; column < totalsColumn; column++) {
					System.out.println("- " + buildings[0][column]);
				}
				System.out.print("> Select resource to modify: ");
				input = sc.nextLine();
			}
		}
		return columnQuery;
	}

	public static void addData(int rowQuery, int columnQuery) {
		String input;
		Double finalData = null;
		while (finalData == null) {
			System.out.print(
					"> Input consumption data for Building " + rowQuery + " - " + buildings[0][columnQuery] + ": ");
			input = sc.nextLine();
			try {
				finalData = Double.parseDouble(input);
				if (finalData < 0) {
					System.out.println("Error: Consumption cannot be negative.");
					finalData = null;
				} else {
					buildings[rowQuery][columnQuery] = String.format("%.2f", finalData);
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input! Please enter a valid number.");
			}
		}
	}

	// CHANGED: Enhanced display to show proper formatting
	public static void displayMatrix() {
		System.out.println("\n=== DAILY RESOURCE CONSUMPTION MATRIX ===");
		System.out.println("Day " + currentDay + " of " + maxDays);
		System.out.println("=".repeat(80));

		// Print header
		System.out.printf("%-15s", "");
		for (int col = 1; col < totalsColumn; col++) {
			System.out.printf("%-15s", buildings[0][col]);
		}
		System.out.printf("%-15s%-15s\n", buildings[0][totalsColumn], buildings[0][averageColumn]);
		System.out.println("-".repeat(80));

		// Print data rows
		for (int row = 1; row < buildings.length; row++) {
			if (buildings[row][0] != null) {
				System.out.printf("%-15s", buildings[row][0]);
				for (int col = 1; col < buildings[row].length; col++) {
					System.out.printf("%-15s", buildings[row][col]);
				}
				System.out.println();
			}
		}
		System.out.println("=".repeat(80) + "\n");
	}

	public static void displayOverview() {
		System.out.println("\n=== CAMPUS RESOURCE OVERVIEW ===");
		System.out.println("=".repeat(80));

		// Print header
		System.out.printf("%-15s", "");
		for (int col = 1; col < totalsColumn; col++) {
			System.out.printf("%-15s", overview[0][col]);
		}
		System.out.printf("%-15s%-15s\n", overview[0][totalsColumn], overview[0][averageColumn]);
		System.out.println("-".repeat(80));

		// Print data rows
		for (int row = 1; row < overview.length; row++) {
			if (overview[row][0] != null) {
				System.out.printf("%-15s", overview[row][0]);
				for (int col = 1; col < overview[row].length; col++) {
					System.out.printf("%-15s", overview[row][col]);
				}
				System.out.println();
			}
		}
		System.out.println("=".repeat(80) + "\n");
	}

	// CHANGED: Enhanced to handle doubles and provide better calculations
	public static void DailyComputeTotalsAndAverages() {
		double total = 0;
		double currItem = 0;
		int itemCount = 0;

		// Per row Totals and Average (Building-wise)
		for (int row = 1; row < totalsRow; row++) {
			for (int column = 1; column < totalsColumn; column++) {
				try {
					currItem = Double.parseDouble(buildings[row][column]);
					total += currItem;
					itemCount++;
				} catch (NumberFormatException e) {
					// Skip non-numeric values
				}
			}
			buildings[row][totalsColumn] = String.format("%.2f", total);
			if (itemCount != 0)
				buildings[row][averageColumn] = String.format("%.2f", total / itemCount);
			total = 0;
			itemCount = 0;
		}

		// Per column Totals and Average (Resource-wise)
		for (int column = 1; column < buildings[0].length; column++) {
			for (int row = 1; row < totalsRow; row++) {
				try {
					currItem = Double.parseDouble(buildings[row][column]);
					total += currItem;
					if (currItem > 0)
						itemCount++;
				} catch (NumberFormatException e) {
					// Skip non-numeric values
				}
			}
			if (column != averageColumn && column != totalsColumn) {
				buildings[totalsRow][column] = String.format("%.2f", total);
				if (itemCount != 0)
					buildings[averageRow][column] = String.format("%.2f", total / itemCount);
				else
					buildings[averageRow][column] = "0.00";
			}
			total = 0;
			itemCount = 0;
		}
	}

	public static void O_ComputeTotalsAndAverages() {
		double total = 0;
		double currItem = 0;
		int itemCount = 0;

		// Similar logic as DailyCompute but for overview
		for (int row = 1; row < overVTotalsRow; row++) {
			for (int column = 1; column < totalsColumn; column++) {
				try {
					currItem = Double.parseDouble(overview[row][column]);
					total += currItem;
					itemCount++;
				} catch (NumberFormatException e) {
					// Skip non-numeric values
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
					// Skip non-numeric values
				}
			}
			if (column != averageColumn && column != totalsColumn) {
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

	// CHANGED: Enhanced to meet requirements with threshold detection
	public static void detectInefficiencies() {
		System.out.println("=== INEFFICIENCY DETECTION ===");

		try {
			double campusAverage = Double.parseDouble(buildings[averageRow][averageColumn]);
			final double THRESHOLD_PERCENT = 10.0; // 10% threshold as required

			boolean foundInefficiencies = false;

			for (int row = 1; row < totalsRow; row++) {
				try {
					double buildingAvg = Double.parseDouble(buildings[row][averageColumn]);
					double percentAbove = ((buildingAvg - campusAverage) / campusAverage) * 100;

					if (percentAbove > THRESHOLD_PERCENT) {
						if (!foundInefficiencies) {
							System.out.println("Buildings exceeding " + THRESHOLD_PERCENT + "% above campus average:");
							foundInefficiencies = true;
						}
						System.out.printf("  • Building %s: %.2f (%.1f%% above average)\n", buildings[row][0],
								buildingAvg, percentAbove);
					}
				} catch (NumberFormatException e) {
					// Skip buildings with invalid data
				}
			}

			if (!foundInefficiencies) {
				System.out.println("No buildings significantly exceed consumption thresholds.");
			}
			System.out.println();

		} catch (NumberFormatException e) {
			System.out.println("Insufficient data for inefficiency analysis.");
		}
	}

	// CHANGED: Enhanced to provide better efficiency analysis
	public static void findMostEfficientBuilding() {
		System.out.println("=== EFFICIENCY ANALYSIS ===");

		int efficientIndex = -1;
		double mostEfficientValue = Double.MAX_VALUE;

		for (int row = 1; row < totalsRow; row++) {
			try {
				double candidate = Double.parseDouble(buildings[row][averageColumn]);
				if (candidate < mostEfficientValue && candidate > 0) {
					mostEfficientValue = candidate;
					efficientIndex = row;
				}
			} catch (NumberFormatException e) {
				// Skip buildings with invalid data
			}
		}

		if (efficientIndex != -1) {
			System.out.printf("Most Efficient Building: %s (Average Consumption: %.2f)\n", buildings[efficientIndex][0],
					mostEfficientValue);

			// Also find least efficient for comparison
			int leastEfficientIndex = -1;
			double leastEfficientValue = Double.MIN_VALUE;

			for (int row = 1; row < totalsRow; row++) {
				try {
					double candidate = Double.parseDouble(buildings[row][averageColumn]);
					if (candidate > leastEfficientValue) {
						leastEfficientValue = candidate;
						leastEfficientIndex = row;
					}
				} catch (NumberFormatException e) {
					// Skip buildings with invalid data
				}
			}

			if (leastEfficientIndex != -1) {
				System.out.printf("Least Efficient Building: %s (Average Consumption: %.2f)\n",
						buildings[leastEfficientIndex][0], leastEfficientValue);
			}
		} else {
			System.out.println("No valid data available for efficiency analysis.");
		}
		System.out.println();
	}

	public static void FinaliseAndSave() {
		// Save current day's totals to overview
		for (int column = 1; column < buildings[0].length; column++) {
			overview[currentDay][column] = buildings[totalsRow][column];
			if (column == averageColumn)
				overview[currentDay][column] = buildings[averageRow][totalsColumn];
		}

		// Reset the daily data for next day
		for (int r = 1; r < buildings.length; r++) {
			for (int c = 0; c < buildings[r].length; c++) {
				if (r >= totalsRow && c == 0)
					continue;
				if (c == 0) {
					buildings[r][c] = "Building " + r;
				} else {
					buildings[r][c] = "-";
				}
			}
		}
		currentDay++;
	}

	// CHANGED: Implemented comprehensive summary report as required
	public static void generateSummaryReport() {
		System.out.println("\n" + "=".repeat(80));
		System.out.println("           SMART CAMPUS RESOURCE UTILIZATION SUMMARY REPORT");
		System.out.println("=".repeat(80));

		// Overall Statistics
		System.out.println("\nOVERALL CAMPUS STATISTICS:");
		System.out.println("-".repeat(40));

		try {
			double totalCampusConsumption = 0;
			for (int row = 1; row < overVTotalsRow; row++) {
				try {
					totalCampusConsumption += Double.parseDouble(overview[row][totalsColumn]);
				} catch (NumberFormatException e) {
					// Skip invalid data
				}
			}

			System.out.printf("Total Tracking Period: %d days\n", maxDays);
			System.out.printf("Number of Buildings: %d\n", buildings.length - 3);
			System.out.printf("Total Campus Consumption: %.2f units\n", totalCampusConsumption);
			System.out.printf("Average Daily Consumption: %.2f units/day\n", totalCampusConsumption / maxDays);

		} catch (Exception e) {
			System.out.println("Unable to calculate overall statistics due to data issues.");
		}

		// Resource-wise Analysis
		System.out.println("\nRESOURCE CONSUMPTION BREAKDOWN:");
		System.out.println("-".repeat(40));

		for (int col = 1; col < totalsColumn; col++) {
			try {
				double resourceTotal = Double.parseDouble(overview[overVTotalsRow][col]);
				System.out.printf("%-15s: %10.2f units\n", overview[0][col], resourceTotal);
			} catch (NumberFormatException e) {
				System.out.printf("%-15s: %10s\n", overview[0][col], "No data");
			}
		}

		// Key Findings
		System.out.println("\nKEY FINDINGS AND RECOMMENDATIONS:");
		System.out.println("-".repeat(40));
		System.out.println("1. Review buildings identified in inefficiency analysis");
		System.out.println("2. Implement best practices from most efficient building");
		System.out.println("3. Consider resource-specific optimization strategies");
		System.out.println("4. Set reduction targets for high-consumption periods");
		System.out.println("5. Regular monitoring and reporting for continuous improvement");

		System.out.println("\n" + "=".repeat(80));
		System.out.println("Report generated by Smart Campus Resource Tracker v2.0");
		System.out.println("=".repeat(80));
	}
}