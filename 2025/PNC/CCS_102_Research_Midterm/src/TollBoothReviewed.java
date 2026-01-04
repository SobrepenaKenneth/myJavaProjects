import java.util.Scanner;

public class TollBoothReviewed {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);

		/*
		 * > Ken Comment < > variable names changed for readability 1.) instead of
		 * onDuty use isOnDuty 2.) instead of processingInput use isValidInput 3.)
		 * instead of turnCount use numberOfVehicles 4.) instead of vehicleName change
		 * it to vehicleType unless you are asking for the car brand like Honda etc, but
		 * you are asking for the type right! 5.) i will put some transition comments so
		 * it will be much readable
		 */

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
		// ============================================
		// PROGRAM START
		// ============================================
		// > i am changing this -ken
		// System.out.println("Toll Booth Simulator");
		// System.out.println("Press <<Enter>> to Begin Shift");
		System.out.println("=====================================================");
		System.out.println("             AUTOMATED TOLL BOOTH SIMULATOR          ");
		System.out.println("=====================================================");
		System.out.println("Press <Enter> to begin your shift...");
		reader.nextLine();

		// ============================================
		// MAIN PROGRAM LOOP (Do-While)
		// ============================================
		isOnDuty = true;
		do {
			// ====== ASK FOR NUMBER OF VEHICLES ======
			while (numberOfVehicles == 0) {
				System.out.println("-----------------------------------------------------");
				System.out.println("> How many vehicles would you like to process today? ");
				System.out.print("> ");
				try {
					numberOfVehicles = Integer.parseInt(reader.nextLine());
					System.out.println("-----------------------------------------------------");
				} catch (NumberFormatException e) {
					numberOfVehicles = 0;
				}
				if (numberOfVehicles <= 0) {
					numberOfVehicles = 0;
					// Prompt the user to enter a positive vehicle count
					System.out.println("\n> Invalid number. Please enter a positive value.");
					System.out.println("> Press <<Enter>> to continue <");
					reader.nextLine();
				}
			}

			// FIXME: there is no space after this add \n at the end!
			// System.out.println("> You will be processing " + numberOfVehicles + "
			// vehicle(s) today.");
			System.out.println("\n-----------------------------------------------------");
			System.out.println("> Processing " + numberOfVehicles + " vehicles this round.");
			System.out.println("-----------------------------------------------------\n");

			// ====== PROCESS VEHICLES (For loop) ======
			for (int i = 0; i < numberOfVehicles; i++) {
				isValidInput = true;

				// ====== INPUT VALIDATION (While) ======
				while (isValidInput) {

					// DECISION STRUCTURE:
					// Using if-else statements instead of a switch because this program accepts
					// both
					// numeric inputs (1–4) and text inputs ("car", "bus", etc.).
					// A switch would be cleaner if only numeric input was allowed.

					// NOTE: The term "Toll Fee" is used instead of "Ticket Price" because toll

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
					// FIXME: Prompt the user instead of just "> "
					System.out.print("> Enter vehicle type: ");
					vehicleType = reader.nextLine();

					// ====== DECISION STRUCTURE (If-Else) ======
					// Since it's a Toll Booth instead of "Charging ₱50" use "toll fee".
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
						System.out.println("[" + (i + 1) + "] Input did not match any item in data base.");
						// Do not unlock the while loop.
					}
					System.out.println();
					;
				}
			}

			// ====== DISPLAY SHIFT SUMMARY ======
			System.out.println("=====================================================");
			System.out.println("                SHIFT SUMMARY REPORT                 ");
			System.out.println("=====================================================");
			System.out.println("Vehicles Processed : " + numberOfVehicles);
			System.out.println("-----------------------------------------------------");
			System.out.println("Vehicle Type      ||      Amount Charged");
			System.out.println("-----------------------------------------------------");
			System.out.println("[•] Motorcycle	  " + "||		  	₱" + motor);
			System.out.println("[•] Car       	  " + "||		  	₱" + car);
			System.out.println("[•] Bus       	  " + "||		  	₱" + bus);
			System.out.println("[•] Truck     	  " + "||		  	₱" + truck);
			System.out.println("-----------------------------------------------------");
			System.out.println(">> > Press <<Enter>> to continue < <<");
			reader.nextLine();

			// ====== RESET VARIABLES FOR NEXT BATCH ======
			numberOfVehicles = 0; // Reset the turnCount for the new Batch check.
			motor = 0;
			car = 0;
			bus = 0;
			truck = 0;

			// EPILOGUE
			System.out.println("-----------------------------------------------------");
			System.out.println("> Congratulations.");
			System.out.println("> You've finished processing your quota for today.");
			System.out.println("-----------------------------------------------------");
			System.out.println("> Would you like to process another batch of vehicles?");
			System.out.println("-----------------------------------------------------");
			System.out.println(">> [1] Start New Batch");
			System.out.println(">> [2] Resign");
			System.out.println("-----------------------------------------------------");
			System.out.print("> Enter Choice: ");
			vehicleType = reader.nextLine();

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

	    // ============================================
        // PROGRAM END
        // ============================================
		System.out.println("-----------------------------------------------------");
		System.out.println("> Program Terminated. Goodbye!");
		System.out.println("-----------------------------------------------------");
		reader.close();
	}

}
