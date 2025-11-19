package LabQuiz;

import java.util.Scanner;

/**
 * Smart Campus Resource Utilization and Efficiency Tracker This system
 * optimizes energy and resource usage across campus buildings using 2D arrays
 * for data modeling and processing.
 * 
 * @author Smart Campus Team
 * @version 1.0
 */
public class SmartCampusTracker {
	// Constants for resource categories
	private static final int ELECTRICITY = 0;
	private static final int WATER = 1;
	private static final int INTERNET = 2;
	private static final int LAB_EQUIPMENT = 3;

	// Resource category names
	private static final String[] RESOURCE_NAMES = { "Electricity (kWh)", "Water (m³)", "Internet (GB)",
			"Lab Equipment (hrs)" };

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Main method - program entry point
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("=== Smart Campus Resource Utilization and Efficiency Tracker ===");
		System.out.println();

		// Get campus configuration from user
		System.out.print("Enter number of campus buildings: ");
		int numBuildings = scanner.nextInt();

		System.out.print("Enter number of days to track: ");
		int numDays = scanner.nextInt();

		// Create 3D array: buildings[days][resources]
		double[][][] resourceData = inputData(numBuildings, numDays);

		// Display the collected data
		displayMatrix(resourceData);

		// Perform analytical computations
		computeBuildingTotals(resourceData);
		computeResourceAverages(resourceData);
		detectInefficiencies(resourceData);
		findMostEfficientBuilding(resourceData);
		generateSummaryReport(resourceData);

		scanner.close();
	}

	/**
	 * Collects daily resource utilization data for all buildings
	 * 
	 * @param numBuildings Number of campus buildings
	 * @param numDays      Number of tracking days
	 * @return 3D array containing resource data [building][day][resource]
	 */
	public static double[][][] inputData(int numBuildings, int numDays) {
		double[][][] data = new double[numBuildings][numDays][4];

		System.out.println("\n=== DATA COLLECTION ===");
		for (int building = 0; building < numBuildings; building++) {
			System.out.println("\n--- Building " + (building + 1) + " ---");
			for (int day = 0; day < numDays; day++) {
				System.out.println("Day " + (day + 1) + ":");
				for (int resource = 0; resource < 4; resource++) {
					System.out.print("  " + RESOURCE_NAMES[resource] + ": ");
					data[building][day][resource] = scanner.nextDouble();
				}
			}
		}
		return data;
	}

	/**
	 * Displays the resource utilization matrix in a formatted table
	 * 
	 * @param data 3D array containing resource data
	 */
	public static void displayMatrix(double[][][] data) {
		int numBuildings = data.length;
		int numDays = data[0].length;

		System.out.println("\n=== RESOURCE UTILIZATION MATRIX ===");

		for (int building = 0; building < numBuildings; building++) {
			System.out.println("\nBuilding " + (building + 1) + ":");
			System.out.printf("%-8s", "Day");
			for (String resource : RESOURCE_NAMES) {
				System.out.printf("%-20s", resource);
			}
			System.out.println();

			for (int day = 0; day < numDays; day++) {
				System.out.printf("%-8d", (day + 1));
				for (int resource = 0; resource < 4; resource++) {
					System.out.printf("%-20.2f", data[building][day][resource]);
				}
				System.out.println();
			}
		}
	}

	/**
	 * Computes and displays total resource consumption per building
	 * 
	 * @param data 3D array containing resource data
	 */
	public static void computeBuildingTotals(double[][][] data) {
		int numBuildings = data.length;
		int numDays = data[0].length;

		System.out.println("\n=== BUILDING TOTALS ANALYSIS ===");

		double[][] buildingTotals = new double[numBuildings][4];

		// Calculate totals for each building and resource
		for (int building = 0; building < numBuildings; building++) {
			for (int resource = 0; resource < 4; resource++) {
				double total = 0;
				for (int day = 0; day < numDays; day++) {
					total += data[building][day][resource];
				}
				buildingTotals[building][resource] = total;
			}
		}

		// Display building totals
		System.out.printf("%-15s", "Building");
		for (String resource : RESOURCE_NAMES) {
			System.out.printf("%-20s", "Total " + resource.split(" ")[0]);
		}
		System.out.println();

		for (int building = 0; building < numBuildings; building++) {
			System.out.printf("%-15s", "Building " + (building + 1));
			for (int resource = 0; resource < 4; resource++) {
				System.out.printf("%-20.2f", buildingTotals[building][resource]);
			}
			System.out.println();
		}
	}

	/**
	 * Computes average consumption for each resource across all buildings
	 * 
	 * @param data 3D array containing resource data
	 */
	public static void computeResourceAverages(double[][][] data) {
		int numBuildings = data.length;
		int numDays = data[0].length;

		System.out.println("\n=== RESOURCE AVERAGES ANALYSIS ===");

		double[] resourceAverages = new double[4];
		double[] overallMeans = new double[4];

		// Calculate overall means for each resource
		for (int resource = 0; resource < 4; resource++) {
			double total = 0;
			int count = 0;
			for (int building = 0; building < numBuildings; building++) {
				for (int day = 0; day < numDays; day++) {
					total += data[building][day][resource];
					count++;
				}
			}
			overallMeans[resource] = total / count;
		}

		// Calculate and display building averages with percent difference
		System.out.printf("%-15s", "Building");
		for (String resource : RESOURCE_NAMES) {
			System.out.printf("%-25s", resource + " (Avg)");
		}
		System.out.printf("%-20s\n", "Efficiency Score");

		for (int building = 0; building < numBuildings; building++) {
			System.out.printf("%-15s", "Building " + (building + 1));
			double efficiencyScore = 0;

			for (int resource = 0; resource < 4; resource++) {
				double buildingTotal = 0;
				for (int day = 0; day < numDays; day++) {
					buildingTotal += data[building][day][resource];
				}
				double buildingAvg = buildingTotal / numDays;
				resourceAverages[resource] = buildingAvg;

				// Calculate percent difference from overall mean
				double percentDiff = ((overallMeans[resource] - buildingAvg) / overallMeans[resource]) * 100;
				efficiencyScore += percentDiff;

				System.out.printf("%-25.2f", buildingAvg);
			}
			System.out.printf("%-20.2f\n", efficiencyScore);
		}

		// Display overall means
		System.out.printf("\n%-15s", "Overall Mean");
		for (int resource = 0; resource < 4; resource++) {
			System.out.printf("%-25.2f", overallMeans[resource]);
		}
		System.out.println();
	}

	/**
	 * Identifies buildings with inefficient resource usage patterns
	 * 
	 * @param data 3D array containing resource data
	 */
	public static void detectInefficiencies(double[][][] data) {
		int numBuildings = data.length;
		int numDays = data[0].length;

		System.out.println("\n=== INEFFICIENCY DETECTION ===");

		// Calculate overall averages for each resource
		double[] overallAverages = new double[4];
		for (int resource = 0; resource < 4; resource++) {
			double total = 0;
			int count = 0;
			for (int building = 0; building < numBuildings; building++) {
				for (int day = 0; day < numDays; day++) {
					total += data[building][day][resource];
					count++;
				}
			}
			overallAverages[resource] = total / count;
		}

		boolean foundInefficiencies = false;

		// Check each building for inefficiencies
		for (int building = 0; building < numBuildings; building++) {
			for (int resource = 0; resource < 4; resource++) {
				double buildingTotal = 0;
				for (int day = 0; day < numDays; day++) {
					buildingTotal += data[building][day][resource];
				}
				double buildingAvg = buildingTotal / numDays;

				// Check if usage exceeds threshold (10% above average)
				double threshold = overallAverages[resource] * 1.10;
				if (buildingAvg > threshold) {
					if (!foundInefficiencies) {
						System.out.println("Buildings with usage >10% above average:");
						foundInefficiencies = true;
					}
					double excessPercent = ((buildingAvg - overallAverages[resource]) / overallAverages[resource])
							* 100;
					System.out.printf("  Building %d - %s: %.2f (%.1f%% above average)\n", building + 1,
							RESOURCE_NAMES[resource], buildingAvg, excessPercent);
				}
			}
		}

		if (!foundInefficiencies) {
			System.out.println("No significant inefficiencies detected.");
		}
	}

	/**
	 * Identifies the most efficient building based on resource utilization
	 * 
	 * @param data 3D array containing resource data
	 */
	public static void findMostEfficientBuilding(double[][][] data) {
		int numBuildings = data.length;
		int numDays = data[0].length;

		System.out.println("\n=== EFFICIENCY RANKING ===");

		double[] efficiencyScores = new double[numBuildings];
		double[] overallMeans = new double[4];

		// Calculate overall means
		for (int resource = 0; resource < 4; resource++) {
			double total = 0;
			int count = 0;
			for (int building = 0; building < numBuildings; building++) {
				for (int day = 0; day < numDays; day++) {
					total += data[building][day][resource];
					count++;
				}
			}
			overallMeans[resource] = total / count;
		}

		// Calculate efficiency scores for each building
		for (int building = 0; building < numBuildings; building++) {
			double score = 0;
			for (int resource = 0; resource < 4; resource++) {
				double buildingTotal = 0;
				for (int day = 0; day < numDays; day++) {
					buildingTotal += data[building][day][resource];
				}
				double buildingAvg = buildingTotal / numDays;

				// Lower usage than average = positive efficiency
				double efficiency = ((overallMeans[resource] - buildingAvg) / overallMeans[resource]) * 100;
				score += efficiency;
			}
			efficiencyScores[building] = score;
		}

		// Find most and least efficient buildings
		int mostEfficient = 0;
		int leastEfficient = 0;
		for (int i = 1; i < numBuildings; i++) {
			if (efficiencyScores[i] > efficiencyScores[mostEfficient]) {
				mostEfficient = i;
			}
			if (efficiencyScores[i] < efficiencyScores[leastEfficient]) {
				leastEfficient = i;
			}
		}

		System.out.printf("Most Efficient: Building %d (Score: %.2f)\n", mostEfficient + 1,
				efficiencyScores[mostEfficient]);
		System.out.printf("Least Efficient: Building %d (Score: %.2f)\n", leastEfficient + 1,
				efficiencyScores[leastEfficient]);

		// Display ranking
		System.out.println("\nBuilding Efficiency Ranking:");
		for (int i = 0; i < numBuildings; i++) {
			System.out.printf("  %d. Building %d: %.2f\n", i + 1, i + 1, efficiencyScores[i]);
		}
	}

	/**
	 * Generates a comprehensive summary report of campus resource utilization
	 * 
	 * @param data 3D array containing resource data
	 */
	public static void generateSummaryReport(double[][][] data) {
		int numBuildings = data.length;
		int numDays = data[0].length;

		System.out.println("\n=== COMPREHENSIVE SUMMARY REPORT ===");
		System.out.println("Campus Resource Utilization Analysis");
		System.out.println("=====================================");

		// Calculate key statistics
		double[][] resourceStats = new double[4][3]; // [resource][min, max, avg]

		for (int resource = 0; resource < 4; resource++) {
			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;
			double total = 0;
			int count = 0;

			for (int building = 0; building < numBuildings; building++) {
				for (int day = 0; day < numDays; day++) {
					double value = data[building][day][resource];
					if (value < min)
						min = value;
					if (value > max)
						max = value;
					total += value;
					count++;
				}
			}

			resourceStats[resource][0] = min;
			resourceStats[resource][1] = max;
			resourceStats[resource][2] = total / count;
		}

		// Display resource statistics
		System.out.println("\nResource Consumption Statistics:");
		System.out.printf("%-20s %-10s %-10s %-10s\n", "Resource", "Min", "Max", "Average");
		for (int resource = 0; resource < 4; resource++) {
			System.out.printf("%-20s %-10.2f %-10.2f %-10.2f\n", RESOURCE_NAMES[resource], resourceStats[resource][0],
					resourceStats[resource][1], resourceStats[resource][2]);
		}

		// Calculate total campus consumption
		double totalCampusConsumption = 0;
		for (int building = 0; building < numBuildings; building++) {
			for (int day = 0; day < numDays; day++) {
				for (int resource = 0; resource < 4; resource++) {
					totalCampusConsumption += data[building][day][resource];
				}
			}
		}

		System.out.printf("\nTotal Campus Consumption (all resources): %.2f units\n", totalCampusConsumption);
		System.out.printf("Average Daily Consumption per Building: %.2f units\n",
				totalCampusConsumption / (numBuildings * numDays));

		// Recommendations
		System.out.println("\nRecommendations:");
		System.out.println("1. Review buildings with usage >10% above average");
		System.out.println("2. Implement best practices from most efficient building");
		System.out.println("3. Consider resource-specific optimization strategies");
		System.out.println("4. Monitor trends and set reduction targets");
	}
}