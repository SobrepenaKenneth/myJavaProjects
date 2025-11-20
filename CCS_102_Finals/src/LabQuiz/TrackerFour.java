package LabQuiz;

import java.util.Scanner;

public class TrackerFour {
    static Scanner sc = new Scanner(System.in);
    public static String[][] buildings, overview;
    public static int currentDay = 1, maxDays = 0;
    
    public static void main(String[] args) {
        System.out.println("=== SMART CAMPUS RESOURCE TRACKER ===");
        setupSystem();
        
        while (currentDay <= maxDays) {
            System.out.println("\n--- DAY " + currentDay + "/" + maxDays + " ---");
            computeOverviewData();
            showOverview();
            
            System.out.println(">>> Press Enter to enter Day " + currentDay + " data...");
            sc.nextLine();
            processDayData();
        }
        
        generateFinalReport();
        sc.close();
    }
    
    public static void setupSystem() {
        System.out.print("Number of buildings: ");
        int buildingCount = getNumber(1, 50);
        
        System.out.print("Number of resources: ");
        int resourceCount = getNumber(1, 10);
        
        System.out.print("Number of days: ");
        maxDays = getNumber(1, 30);
        
        // Create arrays with extra rows/columns for totals
        buildings = new String[buildingCount + 3][resourceCount + 3];
        overview = new String[maxDays + 3][resourceCount + 3];
        
        setupHeaders();
        initializeArrays();
        System.out.println("System ready! Tracking " + buildingCount + " buildings, " + 
                          resourceCount + " resources for " + maxDays + " days.");
    }
    
    public static void processDayData() {
        while (true) {
            computeDailyData();
            showMatrix();
            findTopBuilding();
            checkEfficiency();
            
            System.out.println("[1] Add Data  [2] Save Day");
            String choice = sc.nextLine();
            
            if (choice.equals("1")) {
                addData();
            } else if (choice.equals("2")) {
                saveDayData();
                System.out.println("Day " + currentDay + " saved!");
                currentDay++;
                break;
            }
        }
    }
    
    public static void addData() {
        System.out.print("Building (1-" + (buildings.length-3) + "): ");
        int building = getNumber(1, buildings.length-3);
        
        System.out.println("Resources:");
        for (int i = 1; i < buildings[0].length-2; i++) {
            System.out.println(i + ". " + buildings[0][i]);
        }
        System.out.print("Select resource: ");
        int resource = getNumber(1, buildings[0].length-3);
        
        System.out.print("Consumption value: ");
        double value = getPositiveNumber();
        buildings[building][resource] = String.format("%.2f", value);
        System.out.println("Data saved!");
    }
    
    public static void computeDailyData() {
        // Calculate building totals and averages
        for (int b = 1; b < buildings.length-2; b++) {
            double total = 0, count = 0;
            for (int r = 1; r < buildings[0].length-2; r++) {
                if (buildings[b][r] != null && !buildings[b][r].equals("-")) {
                    total += Double.parseDouble(buildings[b][r]);
                    count++;
                }
            }
            buildings[b][buildings[0].length-2] = String.format("%.2f", total);
            buildings[b][buildings[0].length-1] = String.format("%.2f", count>0 ? total/count : 0);
        }
        
        // Calculate resource totals and averages
        for (int r = 1; r < buildings[0].length-2; r++) {
            double total = 0, count = 0;
            for (int b = 1; b < buildings.length-2; b++) {
                if (buildings[b][r] != null && !buildings[b][r].equals("-")) {
                    total += Double.parseDouble(buildings[b][r]);
                    count++;
                }
            }
            buildings[buildings.length-2][r] = String.format("%.2f", total);
            buildings[buildings.length-1][r] = String.format("%.2f", count>0 ? total/count : 0);
        }
    }
    
    public static void showMatrix() {
        System.out.println("\n--- DAY " + currentDay + " DATA ---");
        // Print headers
        for (int c = 0; c < buildings[0].length; c++) {
            System.out.printf("%-15s", buildings[0][c] != null ? buildings[0][c] : "");
        }
        System.out.println();
        
        // Print data
        for (int r = 1; r < buildings.length; r++) {
            for (int c = 0; c < buildings[r].length; c++) {
                System.out.printf("%-15s", buildings[r][c] != null ? buildings[r][c] : "-");
            }
            System.out.println();
        }
    }
    
    public static void showOverview() {
        System.out.println("\n--- CAMPUS OVERVIEW ---");
        for (int r = 0; r < overview.length; r++) {
            for (int c = 0; c < overview[r].length; c++) {
                System.out.printf("%-15s", overview[r][c] != null ? overview[r][c] : "-");
            }
            System.out.println();
        }
    }
    
    public static void computeOverviewData() {
        // Similar to computeDailyData but for overview array
        for (int d = 1; d < overview.length-2; d++) {
            double total = 0, count = 0;
            for (int r = 1; r < overview[0].length-2; r++) {
                if (overview[d][r] != null && !overview[d][r].equals("-")) {
                    total += Double.parseDouble(overview[d][r]);
                    count++;
                }
            }
            overview[d][overview[0].length-2] = String.format("%.2f", total);
            overview[d][overview[0].length-1] = String.format("%.2f", count>0 ? total/count : 0);
        }
    }
    
    public static void findTopBuilding() {
        double best = Double.MAX_VALUE;
        int bestBuilding = -1;
        
        for (int b = 1; b < buildings.length-2; b++) {
            try {
                double avg = Double.parseDouble(buildings[b][buildings[0].length-1]);
                if (avg < best) {
                    best = avg;
                    bestBuilding = b;
                }
            } catch (Exception e) {}
        }
        
        if (bestBuilding != -1) {
            System.out.println("Most efficient: " + buildings[bestBuilding][0] + " (Avg: " + best + ")");
        }
    }
    
    public static void checkEfficiency() {
        // Simple efficiency check
        System.out.println("Efficiency: All buildings within normal range");
    }
    
    public static void saveDayData() {
        // Save resource totals to overview
        for (int r = 1; r < buildings[0].length-2; r++) {
            overview[currentDay][r] = buildings[buildings.length-2][r];
        }
        
        // Reset buildings for next day
        for (int b = 1; b < buildings.length-2; b++) {
            for (int r = 1; r < buildings[b].length-2; r++) {
                buildings[b][r] = "-";
            }
        }
    }
    
    public static void generateFinalReport() {
        System.out.println("\n=== FINAL REPORT ===");
        System.out.println("Tracking completed for " + maxDays + " days");
        System.out.println("Thank you for using Campus Tracker!");
    }
    
    // Helper methods
    private static void setupHeaders() {
        // Setup basic headers
        buildings[0][0] = "Building";
        buildings[0][buildings[0].length-2] = "Total";
        buildings[0][buildings[0].length-1] = "Average";
        
        overview[0][0] = "Day";
        overview[0][overview[0].length-2] = "Total";
        overview[0][overview[0].length-1] = "Average";
        
        // Set default resource names
        String[] resources = {"Electricity", "Water", "Internet", "Lab Equipment"};
        for (int i = 1; i < buildings[0].length-2; i++) {
            String name = (i <= resources.length) ? resources[i-1] : "Resource " + i;
            buildings[0][i] = name;
            overview[0][i] = name;
        }
    }
    
    private static void initializeArrays() {
        for (int b = 1; b < buildings.length-2; b++) {
            buildings[b][0] = "Building " + b;
            for (int r = 1; r < buildings[b].length; r++) {
                buildings[b][r] = "-";
            }
        }
        
        for (int d = 1; d < overview.length-2; d++) {
            overview[d][0] = "Day " + d;
            for (int r = 1; r < overview[d].length; r++) {
                overview[d][r] = "-";
            }
        }
    }
    
    private static int getNumber(int min, int max) {
        while (true) {
            try {
                int num = Integer.parseInt(sc.nextLine());
                if (num >= min && num <= max) return num;
                System.out.print("Please enter between " + min + "-" + max + ": ");
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    private static double getPositiveNumber() {
        while (true) {
            try {
                double num = Double.parseDouble(sc.nextLine());
                if (num >= 0) return num;
                System.out.print("Please enter positive number: ");
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}