package LabQuiz;

/**
 * Smart Campus Resource Utilization and Efficiency Tracker
 * System for analyzing resource consumption patterns across campus buildings
 * 
 * @author GlobalTech Institute Development Team
 * @version 1.0
 * @date December 2024
 */
import java.util.Scanner;

public class SmartCampusTrackerTwo {
    // Constants for resource categories
    private static final int ELECTRICITY = 0;
    private static final int WATER = 1;
    private static final int INTERNET = 2;
    private static final int LAB_EQUIPMENT = 3;
    private static final int NUM_RESOURCES = 4;
    
    private static final String[] RESOURCE_NAMES = {
        "Electricity (kWh)", "Water (m³)", "Internet (GB)", "Lab Equipment (hrs)"
    };
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== GlobalTech Institute ===");
        System.out.println("Smart Campus Resource Utilization and Efficiency Tracker");
        System.out.println("========================================================\n");
        
        // Get system configuration
        int numBuildings = getNumberOfBuildings();
        int numDays = getNumberOfDays();
        
        // Initialize data structures
        double[][][] dailyData = new double[numBuildings][numDays][NUM_RESOURCES];
        double[][] buildingAverages = new double[numBuildings][NUM_RESOURCES];
        
        // Execute system functions
        inputData(dailyData, numBuildings, numDays);
        displayMatrix(dailyData, numBuildings, numDays);
        computeBuildingTotals(dailyData, numBuildings, numDays);
        computeResourceAverages(dailyData, buildingAverages, numBuildings, numDays);
        detectInefficiencies(dailyData, buildingAverages, numBuildings, numDays);
        findMostEfficientBuilding(buildingAverages, numBuildings);
        generateSummaryReport(dailyData, buildingAverages, numBuildings, numDays);
        
        scanner.close();
    }
    
    /**
     * Gets and validates number of buildings from user
     * @return Validated number of buildings
     */
    public static int getNumberOfBuildings() {
        int buildings = 0;
        while (buildings <= 0) {
            System.out.print("Enter number of campus buildings: ");
            if (scanner.hasNextInt()) {
                buildings = scanner.nextInt();
                if (buildings <= 0) {
                    System.out.println("Error: Please enter a positive number.");
                }
            } else {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next();
            }
        }
        return buildings;
    }
    
    /**
     * Gets and validates number of tracking days from user
     * @return Validated number of days
     */
    public static int getNumberOfDays() {
        int days = 0;
        while (days <= 0) {
            System.out.print("Enter number of tracking days: ");
            if (scanner.hasNextInt()) {
                days = scanner.nextInt();
                if (days <= 0) {
                    System.out.println("Error: Please enter a positive number.");
                }
            } else {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next();
            }
        }
        return days;
    }
    
    /**
     * Collects and validates daily resource consumption data
     * @param data 3D array to store daily resource data
     * @param numBuildings Number of campus buildings
     * @param numDays Number of tracking days
     */
    public static void inputData(double[][][] data, int numBuildings, int numDays) {
        System.out.println("\n=== DATA INPUT PHASE ===");
        System.out.println("Enter daily resource consumption data:\n");
        
        for (int building = 0; building < numBuildings; building++) {
            System.out.println("--- Building " + (building + 1) + " ---");
            for (int day = 0; day < numDays; day++) {
                System.out.println("Day " + (day + 1) + ":");
                for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                    double value = -1;
                    while (value < 0) {
                        System.out.print("  " + RESOURCE_NAMES[resource] + ": ");
                        if (scanner.hasNextDouble()) {
                            value = scanner.nextDouble();
                            if (value < 0) {
                                System.out.println("Error: Consumption cannot be negative.");
                            } else {
                                data[building][day][resource] = value;
                            }
                        } else {
                            System.out.println("Error: Please enter a valid number.");
                            scanner.next();
                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println("Data collection completed successfully!\n");
    }
    
    /**
     * Displays the complete resource consumption matrix in formatted tables
     * @param data 3D array containing daily resource data
     * @param numBuildings Number of campus buildings
     * @param numDays Number of tracking days
     */
    public static void displayMatrix(double[][][] data, int numBuildings, int numDays) {
        System.out.println("=== RESOURCE CONSUMPTION MATRIX ===");
        System.out.println("Complete daily consumption data for all buildings\n");
        
        for (int building = 0; building < numBuildings; building++) {
            System.out.println("BUILDING " + (building + 1) + ":");
            // Print header
            System.out.printf("%-8s", "Day");
            for (String resourceName : RESOURCE_NAMES) {
                System.out.printf("%-18s", resourceName);
            }
            System.out.println();
            System.out.println("-".repeat(8 + 18 * NUM_RESOURCES));
            
            // Print daily data
            for (int day = 0; day < numDays; day++) {
                System.out.printf("%-8d", day + 1);
                for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                    System.out.printf("%-18.2f", data[building][day][resource]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    /**
     * Computes and displays total consumption per building with analysis
     * @param data 3D array containing daily resource data
     * @param numBuildings Number of campus buildings
     * @param numDays Number of tracking days
     */
    public static void computeBuildingTotals(double[][][] data, int numBuildings, int numDays) {
        System.out.println("=== BUILDING CONSUMPTION TOTALS ===");
        System.out.println("Total resource consumption per building:\n");
        
        double[][] buildingTotals = new double[numBuildings][NUM_RESOURCES];
        double[] grandTotals = new double[NUM_RESOURCES];
        
        // Calculate totals
        for (int building = 0; building < numBuildings; building++) {
            for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                double total = 0;
                for (int day = 0; day < numDays; day++) {
                    total += data[building][day][resource];
                }
                buildingTotals[building][resource] = total;
                grandTotals[resource] += total;
            }
        }
        
        // Display building totals
        System.out.printf("%-12s", "Building");
        for (String resourceName : RESOURCE_NAMES) {
            System.out.printf("%-18s", "Total " + resourceName.split(" ")[0]);
        }
        System.out.printf("%-15s\n", "Overall Total");
        System.out.println("-".repeat(12 + 18 * NUM_RESOURCES + 15));
        
        for (int building = 0; building < numBuildings; building++) {
            System.out.printf("%-12s", "Bldg " + (building + 1));
            double buildingOverall = 0;
            for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                System.out.printf("%-18.2f", buildingTotals[building][resource]);
                buildingOverall += buildingTotals[building][resource];
            }
            System.out.printf("%-15.2f\n", buildingOverall);
        }
        
        // Display grand totals
        System.out.println("-".repeat(12 + 18 * NUM_RESOURCES + 15));
        System.out.printf("%-12s", "Campus Total");
        double campusGrandTotal = 0;
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            System.out.printf("%-18.2f", grandTotals[resource]);
            campusGrandTotal += grandTotals[resource];
        }
        System.out.printf("%-15.2f\n\n", campusGrandTotal);
    }
    
    /**
     * Computes average consumption per resource with percent difference analysis
     * @param data 3D array containing daily resource data
     * @param averages 2D array to store building averages
     * @param numBuildings Number of campus buildings
     * @param numDays Number of tracking days
     */
    public static void computeResourceAverages(double[][][] data, double[][] averages, 
                                             int numBuildings, int numDays) {
        System.out.println("=== RESOURCE AVERAGE ANALYSIS ===");
        System.out.println("Average daily consumption with efficiency analysis:\n");
        
        // Calculate overall campus averages
        double[] campusAverages = new double[NUM_RESOURCES];
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            double total = 0;
            for (int building = 0; building < numBuildings; building++) {
                for (int day = 0; day < numDays; day++) {
                    total += data[building][day][resource];
                }
            }
            campusAverages[resource] = total / (numBuildings * numDays);
        }
        
        // Calculate building averages and percent differences
        System.out.printf("%-12s", "Building");
        for (String resourceName : RESOURCE_NAMES) {
            System.out.printf("%-20s", resourceName + " (Avg)");
        }
        System.out.printf("%-15s\n", "Efficiency Index");
        System.out.println("-".repeat(12 + 20 * NUM_RESOURCES + 15));
        
        for (int building = 0; building < numBuildings; building++) {
            System.out.printf("%-12s", "Bldg " + (building + 1));
            double efficiencyIndex = 0;
            
            for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                double buildingTotal = 0;
                for (int day = 0; day < numDays; day++) {
                    buildingTotal += data[building][day][resource];
                }
                double buildingAvg = buildingTotal / numDays;
                averages[building][resource] = buildingAvg;
                
                // Calculate percent difference from campus average
                double percentDiff = ((campusAverages[resource] - buildingAvg) / campusAverages[resource]) * 100;
                efficiencyIndex += percentDiff;
                
                // Color code based on efficiency
                if (percentDiff > 5) {
                    System.out.printf("%-20.2f", buildingAvg); // Efficient (green)
                } else if (percentDiff < -5) {
                    System.out.printf("%-20.2f", buildingAvg); // Inefficient (red)
                } else {
                    System.out.printf("%-20.2f", buildingAvg); // Average (yellow)
                }
            }
            System.out.printf("%-15.2f\n", efficiencyIndex);
        }
        
        // Display campus averages
        System.out.println("-".repeat(12 + 20 * NUM_RESOURCES + 15));
        System.out.printf("%-12s", "Campus Avg");
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            System.out.printf("%-20.2f", campusAverages[resource]);
        }
        System.out.printf("%-15s\n\n", "Baseline");
    }
    
    /**
     * Identifies buildings exceeding efficiency thresholds with detailed analysis
     * @param data 3D array containing daily resource data
     * @param averages 2D array containing building averages
     * @param numBuildings Number of campus buildings
     * @param numDays Number of tracking days
     */
    public static void detectInefficiencies(double[][][] data, double[][] averages, 
                                          int numBuildings, int numDays) {
        System.out.println("=== INEFFICIENCY DETECTION ===");
        System.out.println("Buildings exceeding consumption thresholds:\n");
        
        // Calculate campus averages for threshold comparison
        double[] campusAverages = new double[NUM_RESOURCES];
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            double total = 0;
            for (int building = 0; building < numBuildings; building++) {
                total += averages[building][resource];
            }
            campusAverages[resource] = total / numBuildings;
        }
        
        final double THRESHOLD_PERCENT = 10.0;
        boolean inefficienciesFound = false;
        
        for (int building = 0; building < numBuildings; building++) {
            for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                double buildingAvg = averages[building][resource];
                double campusAvg = campusAverages[resource];
                double percentAbove = ((buildingAvg - campusAvg) / campusAvg) * 100;
                
                if (percentAbove > THRESHOLD_PERCENT) {
                    if (!inefficienciesFound) {
                        System.out.println("ALERT: Buildings exceeding " + THRESHOLD_PERCENT + "% above campus average:");
                        inefficienciesFound = true;
                    }
                    System.out.printf("  • Building %d - %s: %.2f (%.1f%% above average)\n",
                            building + 1, RESOURCE_NAMES[resource], buildingAvg, percentAbove);
                }
            }
        }
        
        if (!inefficienciesFound) {
            System.out.println("✓ No significant inefficiencies detected across campus.");
            System.out.println("  All buildings are operating within efficient consumption ranges.");
        }
        System.out.println();
    }
    
    /**
     * Identifies the most efficient building based on comprehensive scoring
     * @param averages 2D array containing building averages
     * @param numBuildings Number of campus buildings
     */
    public static void findMostEfficientBuilding(double[][] averages, int numBuildings) {
        System.out.println("=== EFFICIENCY RANKING ANALYSIS ===");
        System.out.println("Building efficiency ranking based on resource consumption:\n");
        
        double[] efficiencyScores = new double[numBuildings];
        double[] resourceMeans = new double[NUM_RESOURCES];
        
        // Calculate resource means across all buildings
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            double total = 0;
            for (int building = 0; building < numBuildings; building++) {
                total += averages[building][resource];
            }
            resourceMeans[resource] = total / numBuildings;
        }
        
        // Calculate efficiency scores (lower consumption = higher score)
        for (int building = 0; building < numBuildings; building++) {
            double score = 0;
            for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                double normalizedEfficiency = (resourceMeans[resource] - averages[building][resource]) 
                                           / resourceMeans[resource];
                score += normalizedEfficiency * 100; // Convert to percentage points
            }
            efficiencyScores[building] = score;
        }
        
        // Find most and least efficient
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
        
        System.out.printf("🏆 MOST EFFICIENT: Building %d (Efficiency Score: %.2f)\n", 
                         mostEfficient + 1, efficiencyScores[mostEfficient]);
        System.out.printf("📊 LEAST EFFICIENT: Building %d (Efficiency Score: %.2f)\n", 
                         leastEfficient + 1, efficiencyScores[leastEfficient]);
        
        // Display complete ranking
        System.out.println("\nComplete Efficiency Ranking:");
        System.out.println("-".repeat(40));
        for (int i = 0; i < numBuildings; i++) {
            String ranking = (i == mostEfficient) ? "🥇" : 
                           (i == leastEfficient) ? "🔴" : "📈";
            System.out.printf("%s Rank %d: Building %d (Score: %.2f)\n", 
                            ranking, i + 1, i + 1, efficiencyScores[i]);
        }
        System.out.println();
    }
    
    /**
     * Generates comprehensive analytical report with insights and recommendations
     * @param data 3D array containing daily resource data
     * @param averages 2D array containing building averages
     * @param numBuildings Number of campus buildings
     * @param numDays Number of tracking days
     */
    public static void generateSummaryReport(double[][][] data, double[][] averages, 
                                           int numBuildings, int numDays) {
        System.out.println("=== COMPREHENSIVE ANALYTICAL SUMMARY REPORT ===");
        System.out.println("GlobalTech Institute - Campus Resource Management");
        System.out.println("=" .repeat(60));
        
        // Executive Summary
        System.out.println("\n📈 EXECUTIVE SUMMARY");
        System.out.println("-".repeat(40));
        
        double totalCampusConsumption = 0;
        for (int building = 0; building < numBuildings; building++) {
            for (int day = 0; day < numDays; day++) {
                for (int resource = 0; resource < NUM_RESOURCES; resource++) {
                    totalCampusConsumption += data[building][day][resource];
                }
            }
        }
        
        System.out.printf("Total Campus Consumption: %.2f units\n", totalCampusConsumption);
        System.out.printf("Tracking Period: %d days\n", numDays);
        System.out.printf("Buildings Monitored: %d\n", numBuildings);
        System.out.printf("Average Daily Consumption: %.2f units/day\n", 
                         totalCampusConsumption / numDays);
        
        // Resource Distribution Analysis
        System.out.println("\n📊 RESOURCE DISTRIBUTION ANALYSIS");
        System.out.println("-".repeat(40));
        
        double[] resourceTotals = new double[NUM_RESOURCES];
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            for (int building = 0; building < numBuildings; building++) {
                for (int day = 0; day < numDays; day++) {
                    resourceTotals[resource] += data[building][day][resource];
                }
            }
        }
        
        System.out.println("Resource Consumption Breakdown:");
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            double percentage = (resourceTotals[resource] / totalCampusConsumption) * 100;
            System.out.printf("  %s: %.2f units (%.1f%%)\n", 
                            RESOURCE_NAMES[resource], resourceTotals[resource], percentage);
        }
        
        // Key Findings
        System.out.println("\n🔍 KEY FINDINGS & INSIGHTS");
        System.out.println("-".repeat(40));
        
        // Find most and least consumed resource
        int mostConsumed = 0;
        int leastConsumed = 0;
        for (int resource = 1; resource < NUM_RESOURCES; resource++) {
            if (resourceTotals[resource] > resourceTotals[mostConsumed]) {
                mostConsumed = resource;
            }
            if (resourceTotals[resource] < resourceTotals[leastConsumed]) {
                leastConsumed = resource;
            }
        }
        
        System.out.printf("• Highest Consumption: %s\n", RESOURCE_NAMES[mostConsumed]);
        System.out.printf("• Lowest Consumption: %s\n", RESOURCE_NAMES[leastConsumed]);
        
        // Calculate consumption variance
        double[] variances = new double[NUM_RESOURCES];
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            double mean = resourceTotals[resource] / (numBuildings * numDays);
            double variance = 0;
            for (int building = 0; building < numBuildings; building++) {
                for (int day = 0; day < numDays; day++) {
                    variance += Math.pow(data[building][day][resource] - mean, 2);
                }
            }
            variances[resource] = variance / (numBuildings * numDays);
        }
        
        System.out.println("\n• Consumption Variability Analysis:");
        for (int resource = 0; resource < NUM_RESOURCES; resource++) {
            String variability = (variances[resource] > 1000) ? "High" : 
                               (variances[resource] > 500) ? "Medium" : "Low";
            System.out.printf("  %s: %s variability\n", RESOURCE_NAMES[resource], variability);
        }
        
        // Recommendations
        System.out.println("\n💡 STRATEGIC RECOMMENDATIONS");
        System.out.println("-".repeat(40));
        System.out.println("1. Implement targeted efficiency programs for high-consumption resources");
        System.out.println("2. Establish best practice sharing from most efficient buildings");
        System.out.println("3. Set reduction targets for resources with high variability");
        System.out.println("4. Conduct energy audits for buildings exceeding consumption thresholds");
        System.out.println("5. Implement real-time monitoring dashboards for ongoing optimization");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Report generated by Smart Campus Resource Tracker");
        System.out.println("GlobalTech Institute - Driving Sustainable Campus Operations");
    }
}
