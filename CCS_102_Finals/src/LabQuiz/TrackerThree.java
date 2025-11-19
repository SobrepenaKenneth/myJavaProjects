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
 * Revision Notes: - Revised code and added comments (by ken) - Fixed bugs and
 * added required methods/wrappers
 *
 * @author GlobalTech Institute
 * @version 26.1
 */
public class TrackerThree {
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

    public static void main(String[] args) {
        printWelcomeBanner();
        setArraySize();

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
                break;
            }
        }
        sc.close();
    }// End of Main()

    public static void computeDailyData() {
        while (true) {
            // Compute totals/averages for current daily matrix
            computeDailyTotalsAndAverages();

            // Additional analyses
            findMostEfficientBuilding();
            detectInefficiencies(); // uses computed per-building averages
            computePercentDifferences(); // percent diffs relative to campus mean

            // Show matrix
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
    }// End of computeDailyData()

    public static void setArraySize() {
        printSectionHeader("SYSTEM SETUP");

        int numberOfBuildings = 0;
        int numberOfResources = 0;

        System.out.print(">>> How many buildings would you like to track?: ");
        numberOfBuildings = getValidatedInteger(1, 50);

        System.out.print(">>> How many Resource types? (Recommended: 4): ");
        numberOfResources = getValidatedInteger(1, 10);

        System.out.print(">>> How many days would you like to track?: ");
        maxDays = getValidatedInteger(1, 30);

        // initialize daily data array: add 2 rows (totals, averages) + header row => +3
        buildings = new String[numberOfBuildings + 3][numberOfResources + 3];

        totalsRow = buildings.length - 2; // second last row
        averageRow = buildings.length - 1; // last row
        totalsColumn = buildings[0].length - 2; // second last column
        averageColumn = buildings[0].length - 1; // last column

        buildings[0][0] = "Building";
        buildings[totalsRow][0] = "Total";
        buildings[averageRow][0] = "Average";
        buildings[0][totalsColumn] = "Building Total";
        buildings[0][averageColumn] = "Building Avg.";

        // overview array (days as rows)
        overview = new String[maxDays + 3][numberOfResources + 3];
        overVTotalsRow = overview.length - 2;
        overVAverageRow = overview.length - 1;

        overview[0][0] = "Day";
        overview[overVTotalsRow][0] = "Total";
        overview[overVAverageRow][0] = "Average";
        overview[0][totalsColumn] = "Building Total";
        overview[0][averageColumn] = "Building Avg.";

        System.out.println("\n>>> Please provide names for each resource type:");

        for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
            while (buildings[0][resourceIndex] == null || buildings[0][resourceIndex].length() > 15) {
                if (numberOfResources == NUM_DEFAULT_RESOURCES && resourceIndex <= DEFAULT_RESOURCES.length) {
                    buildings[0][resourceIndex] = DEFAULT_RESOURCES[resourceIndex - 1];
                    overview[0][resourceIndex] = DEFAULT_RESOURCES[resourceIndex - 1];
                    System.out.println(">>> Resource " + resourceIndex + " set to: " + DEFAULT_RESOURCES[resourceIndex - 1]);
                    break;
                } else {
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

        initializeDataArrays();
        printSuccessMessage("System initialized with " + numberOfBuildings + " buildings, " + numberOfResources + " resources, " + maxDays + " days!");
    }// End of setArraySize()

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
    }// End of inputData()

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
    }// End of getBuildingSelection()

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
    }// End of getResourceSelection()

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
    }// End of addConsumptionData()

    public static void displayMatrix() {
        System.out.println();
        printSectionHeader("DAILY CONSUMPTION MATRIX - DAY " + currentDay);

        // Print column headers
        System.out.printf("%-18s", buildings[0][0]); // Building header
        for (int columnIndex = 1; columnIndex < totalsColumn; columnIndex++) {
            System.out.printf("%-18s", buildings[0][columnIndex]); // Resource headers
        }
        System.out.printf("%-18s%-18s\n", buildings[0][totalsColumn], buildings[0][averageColumn]);

        // Print separator line
        System.out.println("=".repeat(18 * (buildings[0].length)));

        // Print data rows for each building and summary rows
        for (int rowIndex = 1; rowIndex < buildings.length; rowIndex++) {
            if (buildings[rowIndex][0] != null) {
                // row header already contains "Building N" for data rows and "Total"/"Average" for summary rows
                String rowHeader = buildings[rowIndex][0];
                System.out.printf("%-18s", rowHeader);

                // Print data cells for this row
                for (int columnIndex = 1; columnIndex < buildings[rowIndex].length; columnIndex++) {
                    String cellValue = buildings[rowIndex][columnIndex];
                    System.out.printf("%-18s", cellValue);
                }
                System.out.println();
            }
        }
        System.out.println();
    }// End of displayMatrix()

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
                String rowHeader = overview[rowIndex][0];
                System.out.printf("%-18s", rowHeader);

                for (int columnIndex = 1; columnIndex < overview[rowIndex].length; columnIndex++) {
                    String cellValue = overview[rowIndex][columnIndex];
                    System.out.printf("%-18s", cellValue);
                }
                System.out.println();
            }
        }
        System.out.println();
    }// End of displayOverview()

    public static void detectInefficiencies() {
        printSectionHeader("EFFICIENCY ALERTS");

        final double INEFFICIENCY_THRESHOLD_PERCENT = 10.0; // 10% above average

        // compute campus average as mean of building averages
        double sumBuildingAvgs = 0.0;
        int countBuildingAvgs = 0;
        for (int b = 1; b < totalsRow; b++) {
            try {
                double bAvg = Double.parseDouble(buildings[b][averageColumn]);
                // only count valid averages
                sumBuildingAvgs += bAvg;
                countBuildingAvgs++;
            } catch (NumberFormatException e) {
                // skip
            }
        }

        if (countBuildingAvgs == 0) {
            printErrorMessage("Insufficient data for inefficiency analysis");
            System.out.println();
            return;
        }

        double campusAverageConsumption = sumBuildingAvgs / countBuildingAvgs;
        boolean foundInefficientBuildings = false;

        for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
            try {
                double buildingAverage = Double.parseDouble(buildings[buildingIndex][averageColumn]);
                double percentAboveAverage = ((buildingAverage - campusAverageConsumption) / campusAverageConsumption) * 100.0;

                if (percentAboveAverage > INEFFICIENCY_THRESHOLD_PERCENT) {
                    if (!foundInefficientBuildings) {
                        System.out.println("ALERT: BUILDINGS EXCEEDING " + INEFFICIENCY_THRESHOLD_PERCENT + "% ABOVE CAMPUS AVERAGE:");
                        foundInefficientBuildings = true;
                    }
                    System.out.printf("   [!] %s: Avg=%.2f (%.1f%% above campus avg)\n", buildings[buildingIndex][0], buildingAverage, percentAboveAverage);
                }
            } catch (NumberFormatException e) {
                // skip
            }
        }

        if (!foundInefficientBuildings) {
            System.out.println(">>> All buildings operating within efficient ranges!");
        }
        System.out.println();
    }// End of detectInefficiencies()

    public static void findMostEfficientBuilding() {
        printSectionHeader("EFFICIENCY RANKING");

        int mostEfficientBuildingIndex = -1;
        double mostEfficientConsumption = Double.MAX_VALUE;
        int leastEfficientBuildingIndex = -1;
        double leastEfficientConsumption = Double.NEGATIVE_INFINITY;

        for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
            try {
                double currentBuildingConsumption = Double.parseDouble(buildings[buildingIndex][averageColumn]);

                if (currentBuildingConsumption < mostEfficientConsumption && currentBuildingConsumption >= 0) {
                    mostEfficientConsumption = currentBuildingConsumption;
                    mostEfficientBuildingIndex = buildingIndex;
                }

                if (currentBuildingConsumption > leastEfficientConsumption) {
                    leastEfficientConsumption = currentBuildingConsumption;
                    leastEfficientBuildingIndex = buildingIndex;
                }
            } catch (NumberFormatException e) {
                // skip
            }
        }

        if (mostEfficientBuildingIndex != -1) {
            System.out.println("TOP PERFORMER: " + buildings[mostEfficientBuildingIndex][0] + " (Avg: " + String.format("%.2f", mostEfficientConsumption) + ")");
            if (leastEfficientBuildingIndex != -1 && leastEfficientBuildingIndex != mostEfficientBuildingIndex) {
                System.out.println("NEEDS IMPROVEMENT: " + buildings[leastEfficientBuildingIndex][0] + " (Avg: " + String.format("%.2f", leastEfficientConsumption) + ")");
            }
            double efficiencyGap = leastEfficientConsumption - mostEfficientConsumption;
            System.out.println("Efficiency gap: " + String.format("%.2f", efficiencyGap) + " units");
        } else {
            printErrorMessage("No valid data for efficiency analysis");
        }
        System.out.println();
    }// End of findMostEfficientBuilding()

    public static void generateSummaryReport() {
        System.out.println();
        printSectionHeader("COMPREHENSIVE SUMMARY REPORT");

        System.out.println("==============================================================");
        System.out.println("           GLOBALTECH INSTITUTE                        ");
        System.out.println("           SMART CAMPUS RESOURCE MANAGEMENT                  ");
        System.out.println("==============================================================");

        System.out.println("\nEXECUTIVE SUMMARY");
        System.out.println("--------------------------------------------------------------");

        try {
            double totalCampusConsumption = 0;
            int daysWithValidData = 0;

            for (int dayIndex = 1; dayIndex < overVTotalsRow; dayIndex++) {
                try {
                    totalCampusConsumption += Double.parseDouble(overview[dayIndex][totalsColumn]);
                    daysWithValidData++;
                } catch (NumberFormatException e) {
                    // skip
                }
            }

            System.out.println(">>> Tracking Period: " + maxDays + " days");
            System.out.println(">>> Buildings Monitored: " + (buildings.length - 3));
            System.out.println(">>> Total Consumption: " + String.format("%.2f", totalCampusConsumption) + " units");
            if (daysWithValidData > 0) {
                System.out.println(">>> Average Daily: " + String.format("%.2f", totalCampusConsumption / daysWithValidData) + " units/day");
            }
        } catch (Exception e) {
            printErrorMessage("Error calculating summary statistics");
        }

        System.out.println("\nSTRATEGIC RECOMMENDATIONS");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Implement targeted efficiency programs");
        System.out.println("2. Share best practices from top-performing buildings");
        System.out.println("3. Conduct energy audits for high-consumption areas");
        System.out.println("4. Develop real-time monitoring dashboard");
        System.out.println("5. Train staff on resource conservation practices");

        System.out.println("\n==============================================================");
        System.out.println("           REPORT GENERATED BY SMART CAMPUS TRACKER           ");
        System.out.println("                 Driving Sustainability                ");
        System.out.println("==============================================================");

        System.out.println("\n>>> Thank you for using Smart Campus Resource Tracker!");
        System.out.println(">>> Making campuses smarter, one building at a time!");
    }// End of generateSummaryReport()

    // ========== UI helpers ==========

    private static void printWelcomeBanner() {
        System.out.println("==============================================================");
        System.out.println("               SMART CAMPUS RESOURCE TRACKER               ");
        System.out.println("           Optimizing Campus Resource Efficiency            ");
        System.out.println("==============================================================");
        System.out.println();
    }

    private static void printMainHeader() {
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("                 CAMPUS DASHBOARD - DAY " + currentDay + "/" + maxDays + "                 ");
        System.out.println("--------------------------------------------------------------");
    }

    private static void printSectionHeader(String title) {
        int padding = (60 - title.length()) / 2;
        String leftPadding = " ".repeat(Math.max(0, padding));
        String rightPadding = " ".repeat(Math.max(0, 60 - title.length() - padding));

        System.out.println("\n==============================================================");
        System.out.println(leftPadding + title + rightPadding);
        System.out.println("==============================================================");
    }

    private static void printSuccessMessage(String message) {
        System.out.println(">>> SUCCESS: " + message);
    }

    private static void printErrorMessage(String message) {
        System.out.println(">>> ERROR: " + message);
    }

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
    }

    private static void initializeDataArrays() {
        // Initialize buildings array with default values
        for (int rowIndex = 1; rowIndex < buildings.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < buildings[rowIndex].length; columnIndex++) {
                if (rowIndex >= totalsRow && columnIndex == 0)
                    continue; // Skip header cells
                if (columnIndex == 0) {
                    if (rowIndex < totalsRow) {
                        buildings[rowIndex][columnIndex] = "Building " + rowIndex;
                    }
                } else {
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
                    if (rowIndex < overVTotalsRow) {
                        overview[rowIndex][columnIndex] = "Day " + rowIndex;
                    }
                } else {
                    overview[rowIndex][columnIndex] = "-";
                }
            }
        }
    }// End of initializeDataArrays()

    // ========== CORE COMPUTATION METHODS ==========

    /**
     * Computes totals and averages for the daily building data (row and column)
     */
    public static void computeDailyTotalsAndAverages() {
        double runningTotal = 0;
        double currentValue = 0;
        int validDataCount = 0;

        // Calculate building-wise totals and averages (rows)
        for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
            for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
                try {
                    currentValue = Double.parseDouble(buildings[buildingIndex][resourceIndex]);
                    runningTotal += currentValue;
                    validDataCount++;
                } catch (NumberFormatException e) {
                    // Skip
                }
            }
            buildings[buildingIndex][totalsColumn] = String.format("%.2f", runningTotal);
            if (validDataCount != 0)
                buildings[buildingIndex][averageColumn] = String.format("%.2f", runningTotal / validDataCount);
            else
                buildings[buildingIndex][averageColumn] = "0.00";

            runningTotal = 0;
            validDataCount = 0;
        }

        // Calculate resource-wise totals and averages (columns)
        for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
            for (int buildingIndex = 1; buildingIndex < totalsRow; buildingIndex++) {
                try {
                    currentValue = Double.parseDouble(buildings[buildingIndex][resourceIndex]);
                    runningTotal += currentValue;
                    validDataCount++;
                } catch (NumberFormatException e) {
                    // skip
                }
            }
            buildings[totalsRow][resourceIndex] = String.format("%.2f", runningTotal);
            if (validDataCount != 0)
                buildings[averageRow][resourceIndex] = String.format("%.2f", runningTotal / validDataCount);
            else
                buildings[averageRow][resourceIndex] = "0.00";

            runningTotal = 0;
            validDataCount = 0;
        }

        // Optionally compute a campus-wide average (stored in buildings[averageRow][averageColumn])
        double sumBuildingAvgs = 0;
        int countBuildingAvgs = 0;
        for (int b = 1; b < totalsRow; b++) {
            try {
                double bAvg = Double.parseDouble(buildings[b][averageColumn]);
                sumBuildingAvgs += bAvg;
                countBuildingAvgs++;
            } catch (NumberFormatException e) {
                // skip
            }
        }
        if (countBuildingAvgs != 0) {
            buildings[averageRow][averageColumn] = String.format("%.2f", (sumBuildingAvgs / countBuildingAvgs));
        } else {
            buildings[averageRow][averageColumn] = "0.00";
        }
    }// End of computeDailyTotalsAndAverages()

    /**
     * Computes totals and averages for the overview data across all days
     */
    public static void computeOverviewTotalsAndAverages() {
        double runningTotal = 0;
        double currentValue = 0;
        int validDataCount = 0;

        // day-wise totals and averages
        for (int dayIndex = 1; dayIndex < overVTotalsRow; dayIndex++) {
            for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
                try {
                    currentValue = Double.parseDouble(overview[dayIndex][resourceIndex]);
                    runningTotal += currentValue;
                    validDataCount++;
                } catch (NumberFormatException e) {
                    // skip
                }
            }
            overview[dayIndex][totalsColumn] = String.format("%.2f", runningTotal);
            if (validDataCount != 0)
                overview[dayIndex][averageColumn] = String.format("%.2f", runningTotal / validDataCount);
            else
                overview[dayIndex][averageColumn] = "0.00";

            runningTotal = 0;
            validDataCount = 0;
        }

        // resource-wise totals across all days
        for (int resourceIndex = 1; resourceIndex < overview[0].length; resourceIndex++) {
            for (int dayIndex = 1; dayIndex < overVTotalsRow; dayIndex++) {
                try {
                    currentValue = Double.parseDouble(overview[dayIndex][resourceIndex]);
                    runningTotal += currentValue;
                    validDataCount++;
                } catch (NumberFormatException e) {
                    // skip
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
    }// End of computeOverviewTotalsAndAverages()

    /**
     * Finalizes current day's data and saves resource totals to overview, then resets
     * daily array for next day.
     */
    public static void finalizeAndSaveDailyData() {
        // copy resource totals from buildings totalsRow to overview currentDay
        double dayTotal = 0.0;
        int resourceCols = totalsColumn - 1; // resource columns count

        for (int resourceIndex = 1; resourceIndex < totalsColumn; resourceIndex++) {
            String value = buildings[totalsRow][resourceIndex];
            overview[currentDay][resourceIndex] = value;
            try {
                dayTotal += Double.parseDouble(value);
            } catch (NumberFormatException e) {
                // treat missing as zero
            }
        }

        overview[currentDay][totalsColumn] = String.format("%.2f", dayTotal);
        if (resourceCols > 0) {
            overview[currentDay][averageColumn] = String.format("%.2f", dayTotal / resourceCols);
        } else {
            overview[currentDay][averageColumn] = "0.00";
        }

        // Reset daily data array for next day (preserve header cells)
        for (int rowIndex = 1; rowIndex < buildings.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < buildings[rowIndex].length; columnIndex++) {
                if (rowIndex >= totalsRow && columnIndex == 0)
                    continue; // Preserve header rows
                if (columnIndex == 0) {
                    buildings[rowIndex][columnIndex] = "Building " + rowIndex;
                } else {
                    buildings[rowIndex][columnIndex] = "-";
                }
            }
        }

        currentDay++;
    }// End of finalizeAndSaveDailyData()

    // ===== Additional helper methods required by assignment (names) =====

    // wrapper to satisfy "computeBuildingTotals()" requirement
    public static void computeBuildingTotals() {
        computeDailyTotalsAndAverages();
    }

    // wrapper to satisfy "computeResourceAverages()" requirement
    public static void computeResourceAverages() {
        computeDailyTotalsAndAverages();
    }

    /**
     * Compute percent difference between each building average and campus average,
     * display short summary (advanced computation requirement).
     */
    public static void computePercentDifferences() {
        // campus average from buildings[averageRow][averageColumn] (we set this in computeDailyTotalsAndAverages())
        double campusAvg = 0.0;
        try {
            campusAvg = Double.parseDouble(buildings[averageRow][averageColumn]);
        } catch (NumberFormatException e) {
            // fallback compute
            double sum = 0;
            int c = 0;
            for (int b = 1; b < totalsRow; b++) {
                try {
                    double val = Double.parseDouble(buildings[b][averageColumn]);
                    sum += val;
                    c++;
                } catch (NumberFormatException ex) {
                }
            }
            if (c > 0) campusAvg = sum / c;
        }

        if (campusAvg <= 0) {
            return;
        }

        System.out.println("PERCENT DIFFERENCES VS CAMPUS AVERAGE:");
        for (int b = 1; b < totalsRow; b++) {
            try {
                double bAvg = Double.parseDouble(buildings[b][averageColumn]);
                double pct = ((bAvg - campusAvg) / campusAvg) * 100.0;
                System.out.printf("   %s: %.1f%% relative\n", buildings[b][0], pct);
            } catch (NumberFormatException e) {
                // skip
            }
        }
        System.out.println();
    }

}// End of class
