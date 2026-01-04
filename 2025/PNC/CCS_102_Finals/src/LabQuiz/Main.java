package LabQuiz;

import java.util.Scanner;

public class Main {
	
	//Code Version: v23.0
	
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
	/*
	 
	XXX [0][column] are used as headers.
	XXX [row][0] are reserved for building names.
	
	*/
	
	public static void main(String[] args) {
		setArraySize();
		while(true) {
		
		System.out.println("- Very Smart Campus Tracker v23.0 -");
		OverviewTotalsAndAverages();
		displayOverview();
		
		if(currentDay <= maxDays) {
			System.out.println("[Enter] Compute Day " + currentDay + "/" + maxDays + " data sheet");
		
		sc.nextLine();
		computeDailies();
			}
		else {
			System.out.println("[Enter] View Summary Reoprt");
			
			sc.nextLine();
			generateSummaryReport();
			
			
		}
		}
	}
	
	public static void computeDailies() {
		while(true) {
		DailyTotalsAndAverages();
		findMostEfficientBuilding();
		DetectInefficiencies();
		
		displayMatrix();
		System.out.println("[1] Input Data Individually");
		System.out.println("[2] Finalise Data and Save to Overview");
		String input = sc.nextLine();
		switch (input) {
		case "1":
			I_inputData();
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
		System.out.print("> How many buildings would you like to track?: ");
		while(row <= 0) { // Row Input Validation
		try {
			row = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			row = 0;
			System.out.println("Invalid Input!");
			}
		}
		
		System.out.print("> How many Resource types would you like to track?: ");
		while(column <= 0) { // Column Input Validation
		try {
			column = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			column = 0;
			System.out.println("Invalid Input!");
			}
		}
		
		System.out.print("> How many days would you like to track?: ");
		while(maxDays <= 0) { // Column Input Validation
		try {
			maxDays = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			maxDays = 0;
			System.out.println("Invalid Input!");
			}
		}
		
		//For Daily Data
		buildings = new String[row + 3][column + 3];
		totalsRow = buildings.length - 2;
		averageRow = buildings.length - 1;
		totalsColumn = buildings[0].length - 2;
		averageColumn = buildings[0].length - 1;
		System.out.println(buildings[0].length - 2);
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
		
		for(int name = 1; name < totalsColumn; name++) {
			while(buildings[0][name] == null || buildings[0][name].length() > 9) {
				
				System.out.print("Provide a name for resource " + name + "(Char. Limit: 10): ");
				String in = sc.nextLine();
				buildings[0][name] = in;
				overview[0][name] = in;
				if(buildings[0][name].length() > 9) System.out.println("> Length exceeds character limit.");
			}
		}
		
		for(int r = 1; r < buildings.length; r++) { 
			for(int c = 0; c < buildings[r].length; c++) {
			if(r >= totalsRow && c == 0) continue;
			if(c == 0)  buildings[r][c] = Integer.toString(r);
			else buildings[r][c] = "-"; 
				
			}
		}
		
		for(int r = 1; r < overview.length; r++) { 
			for(int c = 0; c < overview[r].length; c++) {
			if(r >= overVTotalsRow && c == 0) continue;
			if(c == 0) overview[r][c] = Integer.toString(r);
			else overview[r][c] = "-"; 
				
			}
		}
		
		System.out.println("> An Empty Data Set with " + row + " rows and " + column +
				" columns has been created."
				+ "\n Totals Row: " + totalsRow
				+ "\n Average Row: " + averageRow);
	}
	
	public static void I_inputData() {
			String input = "";
			String exitKey = "exit";
			int columnQuery = 0;
			int rowQuery = 0;
			
			System.out.println("== [ Data Input ] == ");
			while(true) {
			// Ask for Row Number
			rowQuery = askRow();
			// Ask for Column name/number
			columnQuery = askColumn();
			// Modify Data
			addData(rowQuery, columnQuery);
			System.out.println("== " + rowQuery + "-" + columnQuery +" successfully modified == ");
			System.out.println("-- Press [Enter] to input a new set, Type " + exitKey + " to return to list -- ");
			input = sc.nextLine();
			if(input.equalsIgnoreCase(exitKey)) break;
			}	
		}
		
	public static int askRow() {
		int rowQuery = 0;
		String input;
		while(rowQuery <= 0 || rowQuery >= totalsRow) {
			System.out.print("> Select row to modify(1-" + (buildings.length-3) + "): ");
			input = sc.nextLine();
		try {
			rowQuery =	Integer.parseInt(input);
			} catch(NumberFormatException e) {
				System.out.println(">Invalid Input: Not an Integer");
				rowQuery = 0;
			}
		}
		System.out.println("> '"+ rowQuery + "' selected.");
		return rowQuery;
	}
	
	public static int askColumn() {
		String input;
		int columnQuery = 0;
		
		while(columnQuery <= 0) {
			System.out.print("> Select column to modify: ");
			input = sc.nextLine();
			for (int column = 1; column < totalsColumn; column++) {
			if(input.equals(buildings[0][column])) {
				columnQuery = column;
				System.out.println("> '"+ buildings[0][column] + "' selected.");
				break;
				}
			}
		}
		return columnQuery;
	}
	
	public static void addData(int rowQuery, int columnQuery) {
		String input;
		Double finalData = null;
		while(finalData == null) {
		System.out.print("> Input data for "+ rowQuery + "-" + buildings[0][columnQuery] +  " (Int/Double): ");
		input = sc.nextLine();
		try {
			finalData = Double.parseDouble(input);
			buildings[rowQuery][columnQuery] = input;
			} catch(NumberFormatException e) {
				
			System.out.println("Invalid Input!");
			
			}
		}
		
	}
	
	public static void displayMatrix() {
			System.out.println("");
			System.out.println("----------------------------------------------------------------");
			System.out.println("ITEM LIST");
			for (int i = 0; i < buildings.length; i++) {
				
				String index = buildings[i][0];
				
					 System.out.print("         ");
					
					if (index != null) {
					for (int column = 0; column < buildings[i].length; column++) {
						String item = (column != 0) ? buildings[i][column - 1] : ""; //Take the last column's string length
						int spaceLength = 20 - item.length();
						if(column != 0) {
							
						for (int space = 1; space <= spaceLength; space++) { 
							System.out.print(" ");
						} 
						}
						
						System.out.print(buildings[i][column]);

					} // column loop end
					
				}
					System.out.println("\n");
			}
			System.out.println("----------------------------------------------------------------");
			System.out.println("");
	}
	
	public static void displayOverview() {
		System.out.println("");
		System.out.println("----------------------------------------------------------------");
		System.out.println("OVERVIEW");
		for (int i = 0; i < overview.length; i++) {
			
			String index = overview[i][0];
			
				 System.out.print("         ");
				
				if (index != null) {
				for (int column = 0; column < overview[i].length; column++) {
					String item = (column != 0) ? overview[i][column - 1] : ""; //Take the last column's string length
					int spaceLength = 20 - item.length();
					if(column != 0) {
						
					for (int space = 1; space <= spaceLength; space++) {
						System.out.print(" ");
					} 
					}
					
					System.out.print(overview[i][column]);

				} 
				
			}
				System.out.println("\n");
		}
		System.out.println("----------------------------------------------------------------");
		System.out.println("");
}
	
	public static void DailyTotalsAndAverages() {
		double total = 0;
		double currItem = 0;
		int itemCount = 0;
		
		
		// This handles per row Totals and Average
		for (int row = 1; row < totalsRow; row++) {
			
			for (int column = 1; column < totalsColumn; column++) {
				try {
				total += Double.parseDouble(buildings[row][column]);
				itemCount++;
				} catch (NumberFormatException e) {
					
				}
			}
			
			buildings[row][totalsColumn] = Double.toString(total);
			if(itemCount != 0)buildings[row][averageColumn] = Double.toString(total / itemCount);
			total = 0;
			itemCount = 0;
		} 
		
		// This handles per column Totals and Average
				for (int column = 1; column < buildings[0].length; column++) {
					
					for (int row = 1; row < totalsRow; row++) {
						try {
						currItem = Double.parseDouble(buildings[row][column]);
						total += currItem;
						if(!(currItem <= 0))itemCount++;
						} catch (NumberFormatException e) {
							
						}
					}
					if(column != averageColumn) {
					buildings[totalsRow][column] = Double.toString(total);
					if(itemCount != 0)buildings[averageRow][column] = Double.toString(total / itemCount);
					else buildings[averageRow][column] = Double.toString(0);
					}
					total = 0;
					itemCount = 0;
				} 
				
		
	}

	public static void OverviewTotalsAndAverages() {
		double total = 0;
		double currItem = 0;
		int itemCount = 0;
		
		
		// This handles per row Totals and Average
		for (int row = 1; row < overVTotalsRow; row++) {
			
			for (int column = 1; column < totalsColumn; column++) {
				try {
				total += Double.parseDouble(overview[row][column]);
				itemCount++;
				} catch (NumberFormatException e) {
					
				}
			}
			
			overview[row][totalsColumn] = Double.toString(total);
			if(itemCount != 0)overview[row][averageColumn] = Double.toString(total / itemCount);
			total = 0;
			itemCount = 0;
		} 
		
		// This handles per column Totals and Average
				for (int column = 1; column < overview[0].length; column++) {
					
					for (int row = 1; row < overVTotalsRow; row++) {
						try {
						currItem = Double.parseDouble(overview[row][column]);
						total += currItem;
						if(!(currItem <= 0))itemCount++;
						} catch (NumberFormatException e) {
							
						}
					}
					if(column != averageColumn) {
						overview[overVTotalsRow][column] = Double.toString(total);
					if(itemCount != 0)overview[overVAverageRow][column] = Double.toString(total / itemCount);
					else overview[overVAverageRow][column] = Double.toString(0);
					}
					total = 0;
					itemCount = 0;
				} 
				
		
	}

	
	public static void DetectInefficiencies() { // TODO: REQUIRES REVISION
		int columnAverage;
		int currentItem;
		System.out.println("Buildings Exceeding Average Resource Consumption: ");
		
		try {
		columnAverage = Integer.parseInt(buildings[averageRow][averageColumn]);
		} catch (NumberFormatException e) {
		columnAverage = 0;
		}
		for (int row = 1; row < totalsRow; row++) {
				try {
					currentItem = Integer.parseInt(buildings[row][averageColumn]);
					} catch (NumberFormatException e) {
					currentItem = 0;
					}
				if(currentItem > columnAverage) System.out.print(buildings[row][0] + " ");
				
			}
			System.out.println();
		}
		
	
	
	public static void findMostEfficientBuilding() { // TODO: REQUIRES REVISION
		int efficientBuilding;
		int efficientIndex = 1;
		int candidate;
		try {
		 efficientBuilding = Integer.parseInt(buildings[1][averageColumn]);
		 
		} catch(NumberFormatException e) {
			efficientBuilding = 0;
		}
		for (int row = 1; row < totalsRow; row++) {
			try {
			candidate = Integer.parseInt(buildings[row][averageColumn]);
			
			} catch(NumberFormatException e) {
				candidate = 0;
			}
			if(candidate < efficientBuilding) {
				efficientBuilding = candidate;
				efficientIndex = row;
			}
		}
		
		System.out.print("Most Efficient Building: ");
		if(efficientIndex != 0) System.out.println(buildings[efficientIndex][0]);
		else System.out.println();
		
	}
	
	public static void FinaliseAndSave() {
		// Takes the Totals Column for the current set and records it in the overview set.
		for(int column = 1; column < buildings[0].length; column++) {
			overview[currentDay][column] = buildings[totalsRow][column];
			if(column == averageColumn) overview[currentDay][column] = buildings[averageRow][totalsColumn];
		}
		// Resets the current set.
		for(int r = 1; r < buildings.length; r++) { 
			for(int c = 0; c < buildings[r].length; c++) {
			if(r >= totalsRow && c == 0) continue;
			if(c == 0) { buildings[r][c] = Integer.toString(r);
			}
			else { buildings[r][c] = "-";
				}
			}
		}
		
		currentDay++;
	}
	
	public static void generateSummaryReport() {
			//buildings[averageRow][averageColumn];
			
		
		
	}
	
}
