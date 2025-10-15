import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		// ============================================
		// VARIABLE DECLARATION
		// ============================================
		boolean isOnDuty = false; // Determines if the Game is active
		boolean isValidInput = true; // Locks and Unlocks the processing loop
		int numberOfVehicles = 0;
		String vehicleType;

		// Toll Ticket Prices
		final int motorCost = 50;
		final int carCost = 100;
		final int busCost = 150;
		final int truckCost = 200;

		// Charge Tally
		int motor = 0;
		int car = 0;
		int bus = 0;
		int truck = 0;

		// > STEP 2
		// ============================================
		// PROGRAM START
		// ============================================
		System.out.println("=====================================================");
		System.out.println("             AUTOMATED TOLL BOOTH SIMULATOR          ");
		System.out.println("=====================================================");
		System.out.println("Press <Enter> to begin your shift...");
		reader.nextLine();

		// > STEP 3
		// ============================================
		// MAIN PROGRAM LOOP (Do-While)
		// ============================================
		// > STEP 4
		isOnDuty = true;
		do {
			// > STEP 5
			// ====== ASK FOR NUMBER OF VEHICLES ======
			while (numberOfVehicles == 0) {
				System.out.println("-----------------------------------------------------");
				System.out.println("> How many vehicles would you like to process today?");
				System.out.print("> ");
				try {
					// > STEP 6
					numberOfVehicles = Integer.parseInt(reader.nextLine());
					System.out.println("-----------------------------------------------------");
				} catch (NumberFormatException e) {
					numberOfVehicles = 0;
				}
				// > STEP 7
				if (numberOfVehicles <= 0) {
					numberOfVehicles = 0;
					// Prompt the user to enter a positive vehicle count
					System.out.println("\n> Invalid number. Please enter a positive value.");
					System.out.println("> Press <<Enter>> to continue <");
					reader.nextLine();
				}
			}// END OF WHILE LOOP
			
			// > STEP 8
			System.out.println("\n-----------------------------------------------------");
			System.out.println("> Processing " + numberOfVehicles + " vehicles this round.");
			System.out.println("-----------------------------------------------------\n");
			
			// > STEP 9
			// ====== PROCESS VEHICLES (For loop) ======
			for (int i = 0; i < numberOfVehicles; i++) {
				isValidInput = true;
				// > STEP 10
				// ====== INPUT VALIDATION (While) ======
				while (isValidInput) {
					// ====== Input Validation Loop (For loop) ======
					System.out.println("=====================================================");
					System.out.println("Vehicle " + (i + 1) + " has arrived. Please select its type below.");
					System.out.println("=====================================================");
					System.out.println("Vehicle Type          ||		Toll Fee");
					System.out.println("-----------------------------------------------------");
					System.out.println("[1] Motorcycle        ||		₱50.00");
					System.out.println("[2] Car               ||		₱100.00");
					System.out.println("[3] Bus               ||		₱150.00");
					System.out.println("[4] Truck             ||		₱200.00");
					System.out.println("-----------------------------------------------------");
					// > STEP 11
					System.out.print("> Enter vehicle type: ");
					// > STEP 12
					vehicleType = reader.nextLine();

					// > STEP 13
					// ====== DECISION STRUCTURE (If-Else) ======
					if (vehicleType.toLowerCase().equals("motorcycle") || vehicleType.toLowerCase().equals("motor")
							|| vehicleType.equals("1")) {
						motor += motorCost;
						System.out.println("-----------------------------------------------------");
						System.out.println("[" + (i + 1) + "] Vehicle identified as a Motorcycle. Toll Fee ₱" + motorCost);
						System.out.println("-----------------------------------------------------");
						isValidInput = false; // Unlocks the while loop
					}
					// CHECK FOR : CARS
					else if (vehicleType.toLowerCase().equals("car") || vehicleType.equals("2")) {
						car += carCost;
						System.out.println("-----------------------------------------------------");
						System.out.println("[" + (i + 1) + "] Vehicle identified as a Car. Toll Fee ₱" + carCost);
						System.out.println("-----------------------------------------------------");
						isValidInput = false; // Unlocks the while loop
					}
					// CHECK FOR : BUSES
					else if (vehicleType.toLowerCase().equals("bus") || vehicleType.equals("3")) {
						bus += busCost;
						System.out.println("-----------------------------------------------------");
						System.out.println("[" + (i + 1) + "] Vehicle identified as a Bus. Toll Fee ₱" + busCost);
						System.out.println("-----------------------------------------------------");
						isValidInput = false; // Unlocks the while loop
					}
					// CHECK FOR : TRUCKS
					else if (vehicleType.toLowerCase().equals("truck") || vehicleType.equals("4")) {
						truck += truckCost;
						System.out.println("-----------------------------------------------------");
						System.out.println("[" + (i + 1) + "] Vehicle identified as a Truck. Toll Fee ₱" + truckCost);
						System.out.println("-----------------------------------------------------");
						isValidInput = false; // Unlocks the while loop
					}
					// INPUT DOESN'T MATCH ANY VALID INTAKES.
					else {
						System.out.println("-----------------------------------------------------");
						System.out.println("[" + (vehicleType) + "] No Match Found. Please Try again.");
						System.out.println("-----------------------------------------------------");
						// Do not unlock the while loop.
					}
					System.out.println();
				}// END OF WHILE LOOP
			}// END OF FOR LOOP
			
			// > STEP 13
			// ====== DISPLAY SHIFT SUMMARY ======
			System.out.println("=====================================================");
			System.out.println("                SHIFT SUMMARY REPORT                 ");
			System.out.println("=====================================================");
			// > STEP 14
			System.out.println("Vehicles Processed : " + numberOfVehicles);
			// > STEP 15
			System.out.println("-----------------------------------------------------");
			System.out.println("Vehicle Type      ||      Amount Charged");
			System.out.println("-----------------------------------------------------");
			System.out.println("[•] Motorcycle	  " + "||		  	₱" + motor);
			System.out.println("[•] Car       	  " + "||		  	₱" + car);
			System.out.println("[•] Bus       	  " + "||		  	₱" + bus);
			System.out.println("[•] Truck     	  " + "||		  	₱" + truck);
			System.out.println("-----------------------------------------------------");
			// > STEP 16
			System.out.println(">> > Press <<Enter>> to continue < <<");
			reader.nextLine();

			// > STEP 17
			// ====== RESET VARIABLES FOR NEXT BATCH ======
			numberOfVehicles = 0; // Reset the turnCount for the new Batch check.
			motor = 0;
			car = 0;
			bus = 0;
			truck = 0;
			
			// > STEP 18
			// EPILOGUE
			System.out.println("-----------------------------------------------------");
			System.out.println("> Congratulations.");
			System.out.println("> You've finished processing your quota for today.");
			System.out.println("-----------------------------------------------------");
			// > STEP 19
			System.out.println("> Would you like to process another batch of vehicles?");
			System.out.println("-----------------------------------------------------");
			System.out.println(">> [1] Start New Batch");
			System.out.println(">> [2] Resign");
			System.out.println("-----------------------------------------------------");
			System.out.print("> Enter Choice: ");
			vehicleType = reader.nextLine();

			// > STEP 20
			if (vehicleType.equals("1") || vehicleType.toLowerCase().equals("yes")
					|| vehicleType.toLowerCase().equals("process new batch")) {
				System.out.println("-----------------------------------------------------");
				System.out.println("> Preparing next batch...");
				System.out.println("-----------------------------------------------------\n");
				// Just let the loop trigger again.
			} else if (vehicleType.equals("2") || vehicleType.toLowerCase().equals("no")
					|| vehicleType.toLowerCase().equals("resign")) {
				System.out.println("-----------------------------------------------------");
				System.out.println("> Terminating Programm...");
				isOnDuty = false; // Unlock the main-loop
			}
		} while (isOnDuty); // end of main loop
		
		// STEP 21
	    // ============================================
        // PROGRAM END
        // ============================================
		System.out.println("-----------------------------------------------------");
		System.out.println("> Program Terminated. Goodbye!");
		System.out.println("-----------------------------------------------------");
		reader.close();
	}

}
