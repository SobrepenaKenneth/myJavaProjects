package LabQuiz;

import java.util.Scanner;

/**
 * Smart Campus Resource Utilization and Efficiency Tracker
 * Tracks and analyzes resource consumption across campus buildings
 * 
 * @author GlobalTech Institute
 * @version 27.1
 */
public class TrackerFive {
    // Scanner for user input
    static Scanner sc = new Scanner(System.in);
    
    // Data storage
    public static String[][] dailyData;      // Daily building resource data
    public static String[][] overviewData;   // Overview across all days
    
    // Tracking variables
    public static int currentDay = 1;
    public static int totalDays = 0;
    
    // Array index constants for daily data
    public static int dailyTotalsRow;
    public static int dailyAveragesRow;
    public static int dailyTotalsCol;
    public static int dailyAveragesCol;
    
    // Array index constants for overview data
    public static int overviewTotalsRow;
    public static int overviewAveragesRow;
    
    // Default resources
    private static final String[] DEFAULT_RESOURCES = {"Electricity", "Water", "Internet", "Lab Equipment"};
    private static final int NUM_DEFAULT_RESOURCES = 4;

    public static void main(String[] args) {
        initializeSystem();
        runMainProgram();
        sc.close();
    }

    // ========== MAIN PROGRAM FLOW ==========
    
    private static void initializeSystem() {
        printWelcomeBanner();
        setupDataStructures();
    }
    
    private static void runMainProgram() {
        while (true) {
            displayDashboard();
            
            if (currentDay <= totalDays) {
                processCurrentDay();
            } else {
                generateSummaryReport();
                break;
            }
        }
    }
    
    private static void processCurrentDay() {
        printSectionHeader("DAY " + currentDay + " DATA ENTRY");
        promptToContinue();
        handleDailyDataEntry();
    }
    
    // ========== SETUP & INITIALIZATION ==========
    
    public static void setupDataStructures() {
        printSectionHeader("SYSTEM SETUP");
        
        int buildingCount = getInputWithValidation("How many buildings would you like to track?: ", 1, 50);
        int resourceCount = getInputWithValidation("How many Resource types? (Recommended: 4): ", 1, 10);
        totalDays = getInputWithValidation("How many days would you like to track?: ", 1, 30);
        
        initializeDailyDataArray(buildingCount, resourceCount);
        initializeOverviewArray(resourceCount);
        setupResourceNames(resourceCount);
        
        initializeWithDefaultValues();
        printSuccessMessage("System initialized with " + buildingCount + " buildings, " + 
                           resourceCount + " resources, " + totalDays + " days!");
    }
    
    private static void initializeDailyDataArray(int buildingCount, int resourceCount) {
        // +3 for header, totals, and averages rows
        dailyData = new String[buildingCount + 3][resourceCount + 3];
        
        // Set row indices for totals and averages
        dailyTotalsRow = dailyData.length - 2;
        dailyAveragesRow = dailyData.length - 1;
        dailyTotalsCol = dailyData[0].length - 2;
        dailyAveragesCol = dailyData[0].length - 1;
        
        // Initialize headers
        dailyData[0][0] = "Building";
        dailyData[dailyTotalsRow][0] = "Total";
        dailyData[dailyAveragesRow][0] = "Average";
        dailyData[0][dailyTotalsCol] = "Building Total";
        dailyData[0][dailyAveragesCol] = "Building Avg.";
    }
    
    private static void initializeOverviewArray(int resourceCount) {
        overviewData = new String[totalDays + 3][resourceCount + 3];
        overviewTotalsRow = overviewData.length - 2;
        overviewAveragesRow = overviewData.length - 1;
        
        overviewData[0][0] = "Day";
        overviewData[overviewTotalsRow][0] = "Total";
        overviewData[overviewAveragesRow][0] = "Average";
        overviewData[0][dailyTotalsCol] = "Building Total";
        overviewData[0][dailyAveragesCol] = "Building Avg.";
    }
    
    private static void setupResourceNames(int resourceCount) {
        System.out.println("\n>>> Please provide names for each resource type:");
        
        for (int resourceIndex = 1; resourceIndex < dailyTotalsCol; resourceIndex++) {
            setResourceName(resourceIndex, resourceCount);
        }
    }
    
    private static void setResourceName(int resourceIndex, int totalResources) {
        if (totalResources == NUM_DEFAULT_RESOURCES && resourceIndex <= DEFAULT_RESOURCES.length) {
            String defaultName = DEFAULT_RESOURCES[resourceIndex - 1];
            dailyData[0][resourceIndex] = defaultName;
            overviewData[0][resourceIndex] = defaultName;
            System.out.println(">>> Resource " + resourceIndex + " set to: " + defaultName);
        } else {
            promptForResourceName(resourceIndex);
        }
    }
    
    private static void promptForResourceName(int resourceIndex) {
        while (dailyData[0][resourceIndex] == null) {
            System.out.print(">>> Name for resource " + resourceIndex + " (15 chars max): ");
            String userInput = sc.nextLine();
            
            if (userInput.length() > 15) {
                printErrorMessage("Name too long! Maximum 15 characters.");
            } else if (userInput.trim().isEmpty()) {
                printErrorMessage("Name cannot be empty!");
            } else {
                dailyData[0][resourceIndex] = userInput;
                overviewData[0][resourceIndex] = userInput;
            }
        }
    }
    
    // ========== DAILY DATA PROCESSING ==========
    
    public static void handleDailyDataEntry() {
        while (true) {
            computeDailyCalculations();
            performDailyAnalysis();
            displayDailyMatrix();
            
            String userChoice = showDataEntryMenu();
            if (userChoice.equals("1")) {
                collectIndividualData();
            } else if (userChoice.equals("2")) {
                saveDailyDataAndContinue();
                return;
            }
        }
    }
    
    private static String showDataEntryMenu() {
        printMenuHeader("DATA ENTRY MENU");
        System.out.println(" [1] Input Data Individually");
        System.out.println(" [2] Finalise & Save to Overview");
        System.out.println("==========================================");
        
        while (true) {
            System.out.print(">>> Select option (1-2): ");
            String input = sc.nextLine();
            if (input.equals("1") || input.equals("2")) {
                return input;
            }
            printErrorMessage("Invalid option! Please choose 1 or 2.");
        }
    }
    
    private static void collectIndividualData() {
        printSectionHeader("DATA INPUT");
        System.out.println(">>> Tip: Enter 'back' at any time to return to menu.");
        
        while (true) {
            int building = selectBuilding();
            int resource = selectResource();
            enterConsumptionData(building, resource);
            
            if (!promptForContinue()) break;
        }
    }
    
    private static boolean promptForContinue() {
        System.out.print(">>> Press [Enter] for next entry or type 'back' to finish: ");
        return !sc.nextLine().equalsIgnoreCase("back");
    }
    
    private static void saveDailyDataAndContinue() {
        saveDailyDataToOverview();
        resetDailyDataForNextDay();
        printSuccessMessage("Day " + (currentDay - 1) + " data saved successfully!");
    }
    
    // ========== DATA INPUT METHODS ==========
    
    public static int selectBuilding() {
        int maxBuilding = dailyData.length - 3;
        
        while (true) {
            System.out.print(">>> Select building (1-" + maxBuilding + "): ");
            String input = sc.nextLine();
            
            try {
                int selection = Integer.parseInt(input);
                if (selection >= 1 && selection <= maxBuilding) {
                    System.out.println(">>> Selected: Building " + selection);
                    return selection;
                }
                printErrorMessage("Please enter a number between 1 and " + maxBuilding);
            } catch (NumberFormatException e) {
                printErrorMessage("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    public static int selectResource() {
        System.out.println(">>> Available resources:");
        for (int i = 1; i < dailyTotalsCol; i++) {
            System.out.println("   " + i + ". " + dailyData[0][i]);
        }
        
        while (true) {
            System.out.print(">>> Select resource (1-" + (dailyTotalsCol - 1) + "): ");
            String input = sc.nextLine();
            
            try {
                int selection = Integer.parseInt(input);
                if (selection >= 1 && selection < dailyTotalsCol) {
                    System.out.println(">>> Selected: " + dailyData[0][selection]);
                    return selection;
                }
                printErrorMessage("Please select a number between 1 and " + (dailyTotalsCol - 1));
            } catch (NumberFormatException e) {
                printErrorMessage("Invalid input! Please enter a number.");
            }
        }
    }
    
    public static void enterConsumptionData(int building, int resource) {
        System.out.println("\n>>> Input for Building " + building + " - " + dailyData[0][resource]);
        
        while (true) {
            System.out.print(">>> Enter consumption value: ");
            String input = sc.nextLine();
            
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    printErrorMessage("Consumption cannot be negative!");
                } else {
                    dailyData[building][resource] = String.format("%.2f", value);
                    printSuccessMessage("Data saved: " + dailyData[building][resource] + " units");
                    return;
                }
            } catch (NumberFormatException e) {
                printErrorMessage("Invalid number! Please enter a valid numeric value.");
            }
        }
    }
    
    // ========== CALCULATION METHODS ==========
    
    public static void computeDailyCalculations() {
        calculateBuildingTotalsAndAverages();
        calculateResourceTotalsAndAverages();
        calculateCampusWideAverage();
    }
    
    private static void calculateBuildingTotalsAndAverages() {
        for (int building = 1; building < dailyTotalsRow; building++) {
            double total = 0;
            int count = 0;
            
            for (int resource = 1; resource < dailyTotalsCol; resource++) {
                Double value = parseDoubleSafely(dailyData[building][resource]);
                if (value != null) {
                    total += value;
                    count++;
                }
            }
            
            dailyData[building][dailyTotalsCol] = String.format("%.2f", total);
            dailyData[building][dailyAveragesCol] = count > 0 ? 
                String.format("%.2f", total / count) : "0.00";
        }
    }
    
    private static void calculateResourceTotalsAndAverages() {
        for (int resource = 1; resource < dailyTotalsCol; resource++) {
            double total = 0;
            int count = 0;
            
            for (int building = 1; building < dailyTotalsRow; building++) {
                Double value = parseDoubleSafely(dailyData[building][resource]);
                if (value != null) {
                    total += value;
                    count++;
                }
            }
            
            dailyData[dailyTotalsRow][resource] = String.format("%.2f", total);
            dailyData[dailyAveragesRow][resource] = count > 0 ? 
                String.format("%.2f", total / count) : "0.00";
        }
    }
    
    private static void calculateCampusWideAverage() {
        double sum = 0;
        int count = 0;
        
        for (int building = 1; building < dailyTotalsRow; building++) {
            Double avg = parseDoubleSafely(dailyData[building][dailyAveragesCol]);
            if (avg != null) {
                sum += avg;
                count++;
            }
        }
        
        dailyData[dailyAveragesRow][dailyAveragesCol] = count > 0 ? 
            String.format("%.2f", sum / count) : "0.00";
    }
    
    public static void computeOverviewTotalsAndAverages() {
        calculateDayWiseTotals();
        calculateResourceWiseTotals();
    }
    
    private static void calculateDayWiseTotals() {
        for (int day = 1; day < overviewTotalsRow; day++) {
            double total = 0;
            int count = 0;
            
            for (int resource = 1; resource < dailyTotalsCol; resource++) {
                Double value = parseDoubleSafely(overviewData[day][resource]);
                if (value != null) {
                    total += value;
                    count++;
                }
            }
            
            overviewData[day][dailyTotalsCol] = String.format("%.2f", total);
            overviewData[day][dailyAveragesCol] = count > 0 ? 
                String.format("%.2f", total / count) : "0.00";
        }
    }
    
    private static void calculateResourceWiseTotals() {
        for (int resource = 1; resource < overviewData[0].length; resource++) {
            if (resource == dailyAveragesCol || resource == dailyTotalsCol) continue;
            
            double total = 0;
            int count = 0;
            
            for (int day = 1; day < overviewTotalsRow; day++) {
                Double value = parseDoubleSafely(overviewData[day][resource]);
                if (value != null) {
                    total += value;
                    count++;
                }
            }
            
            overviewData[overviewTotalsRow][resource] = String.format("%.2f", total);
            overviewData[overviewAveragesRow][resource] = count > 0 ? 
                String.format("%.2f", total / count) : "0.00";
        }
    }
    
    // ========== ANALYSIS METHODS ==========
    
    private static void performDailyAnalysis() {
        detectInefficientBuildings();
        rankBuildingEfficiency();
        computePercentDifferences();
    }
    
    public static void detectInefficientBuildings() {
        printSectionHeader("EFFICIENCY ALERTS");
        final double THRESHOLD = 10.0; // 10% above average
        
        Double campusAvg = getCampusAverage();
        if (campusAvg == null) {
            printErrorMessage("Insufficient data for inefficiency analysis");
            return;
        }
        
        boolean foundInefficient = false;
        
        for (int building = 1; building < dailyTotalsRow; building++) {
            Double buildingAvg = parseDoubleSafely(dailyData[building][dailyAveragesCol]);
            if (buildingAvg != null) {
                double percentAbove = ((buildingAvg - campusAvg) / campusAvg) * 100.0;
                
                if (percentAbove > THRESHOLD) {
                    if (!foundInefficient) {
                        System.out.println("ALERT: BUILDINGS EXCEEDING " + THRESHOLD + "% ABOVE CAMPUS AVERAGE:");
                        foundInefficient = true;
                    }
                    System.out.printf("   [!] %s: Avg=%.2f (%.1f%% above campus avg)\n", 
                                    dailyData[building][0], buildingAvg, percentAbove);
                }
            }
        }
        
        if (!foundInefficient) {
            System.out.println(">>> All buildings operating within efficient ranges!");
        }
        System.out.println();
    }
    
    public static void rankBuildingEfficiency() {
        printSectionHeader("EFFICIENCY RANKING");
        
        Integer mostEfficient = findMostEfficientBuilding();
        Integer leastEfficient = findLeastEfficientBuilding();
        
        if (mostEfficient != null) {
            double bestAvg = parseDoubleSafely(dailyData[mostEfficient][dailyAveragesCol]);
            System.out.println("TOP PERFORMER: " + dailyData[mostEfficient][0] + 
                             " (Avg: " + String.format("%.2f", bestAvg) + ")");
            
            if (leastEfficient != null && !leastEfficient.equals(mostEfficient)) {
                double worstAvg = parseDoubleSafely(dailyData[leastEfficient][dailyAveragesCol]);
                System.out.println("NEEDS IMPROVEMENT: " + dailyData[leastEfficient][0] + 
                                 " (Avg: " + String.format("%.2f", worstAvg) + ")");
                
                double gap = worstAvg - bestAvg;
                System.out.println("Efficiency gap: " + String.format("%.2f", gap) + " units");
            }
        } else {
            printErrorMessage("No valid data for efficiency analysis");
        }
        System.out.println();
    }
    
    private static Integer findMostEfficientBuilding() {
        Double minAvg = Double.MAX_VALUE;
        Integer bestBuilding = null;
        
        for (int building = 1; building < dailyTotalsRow; building++) {
            Double currentAvg = parseDoubleSafely(dailyData[building][dailyAveragesCol]);
            if (currentAvg != null && currentAvg < minAvg) {
                minAvg = currentAvg;
                bestBuilding = building;
            }
        }
        return bestBuilding;
    }
    
    private static Integer findLeastEfficientBuilding() {
        Double maxAvg = Double.NEGATIVE_INFINITY;
        Integer worstBuilding = null;
        
        for (int building = 1; building < dailyTotalsRow; building++) {
            Double currentAvg = parseDoubleSafely(dailyData[building][dailyAveragesCol]);
            if (currentAvg != null && currentAvg > maxAvg) {
                maxAvg = currentAvg;
                worstBuilding = building;
            }
        }
        return worstBuilding;
    }
    
    public static void computePercentDifferences() {
        Double campusAvg = getCampusAverage();
        if (campusAvg == null || campusAvg <= 0) return;
        
        System.out.println("PERCENT DIFFERENCES VS CAMPUS AVERAGE:");
        for (int building = 1; building < dailyTotalsRow; building++) {
            Double buildingAvg = parseDoubleSafely(dailyData[building][dailyAveragesCol]);
            if (buildingAvg != null) {
                double percent = ((buildingAvg - campusAvg) / campusAvg) * 100.0;
                System.out.printf("   %s: %.1f%% relative\n", dailyData[building][0], percent);
            }
        }
        System.out.println();
    }
    
    // ========== DATA MANAGEMENT ==========
    
    public static void saveDailyDataToOverview() {
        double dayTotal = 0.0;
        
        for (int resource = 1; resource < dailyTotalsCol; resource++) {
            String value = dailyData[dailyTotalsRow][resource];
            overviewData[currentDay][resource] = value;
            
            Double numValue = parseDoubleSafely(value);
            if (numValue != null) {
                dayTotal += numValue;
            }
        }
        
        overviewData[currentDay][dailyTotalsCol] = String.format("%.2f", dayTotal);
        int resourceCount = dailyTotalsCol - 1;
        overviewData[currentDay][dailyAveragesCol] = resourceCount > 0 ? 
            String.format("%.2f", dayTotal / resourceCount) : "0.00";
        
        currentDay++;
    }
    
    private static void resetDailyDataForNextDay() {
        for (int row = 1; row < dailyData.length; row++) {
            for (int col = 0; col < dailyData[row].length; col++) {
                if (row >= dailyTotalsRow && col == 0) continue;
                
                if (col == 0) {
                    dailyData[row][col] = "Building " + row;
                } else {
                    dailyData[row][col] = "-";
                }
            }
        }
    }
    
    // ========== DISPLAY METHODS ==========
    
    private static void displayDashboard() {
        printMainHeader();
        computeOverviewTotalsAndAverages();
        displayOverviewData();
    }
    
    public static void displayDailyMatrix() {
        System.out.println();
        printSectionHeader("DAILY CONSUMPTION MATRIX - DAY " + currentDay);
        printMatrix(dailyData);
    }
    
    public static void displayOverviewData() {
        System.out.println();
        printSectionHeader("CAMPUS OVERVIEW DASHBOARD");
        printMatrix(overviewData);
    }
    
    private static void printMatrix(String[][] matrix) {
        // Print headers
        System.out.printf("%-18s", matrix[0][0]);
        for (int col = 1; col < dailyTotalsCol; col++) {
            System.out.printf("%-18s", matrix[0][col]);
        }
        System.out.printf("%-18s%-18s\n", matrix[0][dailyTotalsCol], matrix[0][dailyAveragesCol]);
        
        // Separator
        System.out.println("=".repeat(18 * matrix[0].length));
        
        // Data rows
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] != null) {
                System.out.printf("%-18s", matrix[row][0]);
                
                for (int col = 1; col < matrix[row].length; col++) {
                    System.out.printf("%-18s", matrix[row][col]);
                }
                System.out.println();
            }
        }
        System.out.println();
    }
    
    public static void generateSummaryReport() {
        printSectionHeader("COMPREHENSIVE SUMMARY REPORT");
        
        printReportHeader();
        printExecutiveSummary();
        printStrategicRecommendations();
        printReportFooter();
    }
    
    private static void printExecutiveSummary() {
        System.out.println("\nEXECUTIVE SUMMARY");
        System.out.println("--------------------------------------------------------------");
        
        try {
            double totalConsumption = 0;
            int daysWithData = 0;
            
            for (int day = 1; day < overviewTotalsRow; day++) {
                Double dayTotal = parseDoubleSafely(overviewData[day][dailyTotalsCol]);
                if (dayTotal != null) {
                    totalConsumption += dayTotal;
                    daysWithData++;
                }
            }
            
            System.out.println(">>> Tracking Period: " + totalDays + " days");
            System.out.println(">>> Buildings Monitored: " + (dailyData.length - 3));
            System.out.println(">>> Total Consumption: " + String.format("%.2f", totalConsumption) + " units");
            
            if (daysWithData > 0) {
                System.out.println(">>> Average Daily: " + 
                    String.format("%.2f", totalConsumption / daysWithData) + " units/day");
            }
        } catch (Exception e) {
            printErrorMessage("Error calculating summary statistics");
        }
    }
    
    private static void printStrategicRecommendations() {
        System.out.println("\nSTRATEGIC RECOMMENDATIONS");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Implement targeted efficiency programs");
        System.out.println("2. Share best practices from top-performing buildings");
        System.out.println("3. Conduct energy audits for high-consumption areas");
        System.out.println("4. Develop real-time monitoring dashboard");
        System.out.println("5. Train staff on resource conservation practices");
    }
    
    // ========== HELPER METHODS ==========
    
    private static Double parseDoubleSafely(String value) {
        if (value == null || value.equals("-")) return null;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private static Double getCampusAverage() {
        Double campusAvg = parseDoubleSafely(dailyData[dailyAveragesRow][dailyAveragesCol]);
        if (campusAvg != null) return campusAvg;
        
        // Fallback calculation
        double sum = 0;
        int count = 0;
        for (int building = 1; building < dailyTotalsRow; building++) {
            Double avg = parseDoubleSafely(dailyData[building][dailyAveragesCol]);
            if (avg != null) {
                sum += avg;
                count++;
            }
        }
        return count > 0 ? sum / count : null;
    }
    
    private static int getInputWithValidation(String prompt, int min, int max) {
        System.out.print(">>> " + prompt);
        return getValidatedInteger(min, max);
    }
    
    private static void initializeWithDefaultValues() {
        initializeArrayWithDefaults(dailyData, dailyTotalsRow, "Building ");
        initializeArrayWithDefaults(overviewData, overviewTotalsRow, "Day ");
    }
    
    private static void initializeArrayWithDefaults(String[][] array, int summaryRow, String prefix) {
        for (int row = 1; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                if (row >= summaryRow && col == 0) continue;
                
                if (col == 0 && row < summaryRow) {
                    array[row][col] = prefix + row;
                } else {
                    array[row][col] = "-";
                }
            }
        }
    }
    
    // ========== UI METHODS ==========
    
    private static void printWelcomeBanner() {
        System.out.println("==============================================================");
        System.out.println("               SMART CAMPUS RESOURCE TRACKER                  ");
        System.out.println("           Optimizing Campus Resource Efficiency              ");
        System.out.println("==============================================================");
        System.out.println();
    }
    
    private static void printMainHeader() {
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("                 CAMPUS DASHBOARD - DAY " + currentDay + "/" + totalDays + "                 ");
        System.out.println("--------------------------------------------------------------");
    }
    
    private static void printSectionHeader(String title) {
        int padding = (60 - title.length()) / 2;
        String leftPad = " ".repeat(Math.max(0, padding));
        String rightPad = " ".repeat(Math.max(0, 60 - title.length() - padding));
        
        System.out.println("\n==============================================================");
        System.out.println(leftPad + title + rightPad);
        System.out.println("==============================================================");
    }
    
    private static void printMenuHeader(String title) {
        System.out.println("==========================================");
        System.out.println("           " + title + "            ");
        System.out.println("==========================================");
    }
    
    private static void printReportHeader() {
        System.out.println("==============================================================");
        System.out.println("           GLOBALTECH INSTITUTE                        ");
        System.out.println("           SMART CAMPUS RESOURCE MANAGEMENT                  ");
        System.out.println("==============================================================");
    }
    
    private static void printReportFooter() {
        System.out.println("\n==============================================================");
        System.out.println("           REPORT GENERATED BY SMART CAMPUS TRACKER           ");
        System.out.println("                 Driving Sustainability                ");
        System.out.println("==============================================================");
        
        System.out.println("\n>>> Thank you for using Smart Campus Resource Tracker!");
        System.out.println(">>> Making campuses smarter, one building at a time!");
    }
    
    private static void printSuccessMessage(String message) {
        System.out.println(">>> SUCCESS: " + message);
    }
    
    private static void printErrorMessage(String message) {
        System.out.println(">>> ERROR: " + message);
    }
    
    private static void promptToContinue() {
        System.out.println(">>> Ready to compute Day " + currentDay + "/" + totalDays + " data");
        System.out.println(">>> Press [Enter] to continue...");
        sc.nextLine();
    }
    
    private static int getValidatedInteger(int min, int max) {
        int value = 0;
        while (value < min || value > max) {
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value < min || value > max) {
                    printErrorMessage("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                printErrorMessage("Invalid input! Please enter a valid number.");
                value = 0;
            }
        }
        return value;
    }
    
    // Wrapper methods for assignment requirements
    public static void computeBuildingTotals() {
        computeDailyCalculations();
    }
    
    public static void computeResourceAverages() {
        computeDailyCalculations();
    }
}