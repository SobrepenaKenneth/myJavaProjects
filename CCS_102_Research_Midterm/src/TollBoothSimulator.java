import java.util.Scanner;

/*
 * ================================================
 * PROGRAM: Automated Toll Booth Simulator
 * AUTHOR: [Your Name]
 * DESCRIPTION:
 *   Simulates a toll booth operation using decision structures
 *   (if-else), loops (for, while, do-while), and input validation.
 *   Processes multiple vehicles, calculates toll fees by type,
 *   and displays a summary for each batch.
 *
 * NOTE:
 *   Arrays and advanced Java features are not used, in compliance
 *   with project requirements.
 * ================================================
 */

public class TollBoothSimulator {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        // ============================================
        // VARIABLE DECLARATION
        // ============================================
        boolean isOnDuty = false;     // Controls the main program loop
        boolean isValidInput = true;  // Controls the input validation loop
        int numberOfVehicles = 0;     // Number of vehicles to process in a batch
        String vehicleType;           // Stores the user input for vehicle type

        // Toll Fees (constant values)
        final int MOTORCYCLE_FEE = 50;
        final int CAR_FEE = 100;
        final int BUS_FEE = 150;
        final int TRUCK_FEE = 200;

        // Charge Tally (accumulates total fees per type)
        int motorcycleTotal = 0;
        int carTotal = 0;
        int busTotal = 0;
        int truckTotal = 0;

        // ============================================
        // PROGRAM START
        // ============================================
        System.out.println("===============================================");
        System.out.println("          AUTOMATED TOLL BOOTH SIMULATOR       ");
        System.out.println("===============================================");
        System.out.println("Press <Enter> to begin your shift...");
        reader.nextLine();

        // ============================================
        // MAIN PROGRAM LOOP (Do-While)
        // ============================================
        isOnDuty = true;
        do {
            // ====== ASK FOR NUMBER OF VEHICLES ======
            while (numberOfVehicles == 0) {
                System.out.print("> How many vehicles would you like to process today? ");
                try {
                    numberOfVehicles = Integer.parseInt(reader.nextLine());
                } catch (NumberFormatException e) {
                    numberOfVehicles = 0;
                }

                if (numberOfVehicles <= 0) {
                    numberOfVehicles = 0;
                    System.out.println("\n> Invalid number. Please enter a positive value.");
                    System.out.println("> Press <Enter> to continue...");
                    reader.nextLine();
                }
            }

            System.out.println("\n> Processing " + numberOfVehicles + 
                (numberOfVehicles == 1 ? " vehicle" : " vehicles") + " this round.\n");

            // ====== PROCESS VEHICLES (For Loop) ======
            for (int i = 0; i < numberOfVehicles; i++) {
                isValidInput = true;

                // ====== INPUT VALIDATION LOOP (While) ======
                while (isValidInput) {

                    // DECISION STRUCTURE:
                    // Using if-else statements instead of a switch because this program accepts both
                    // numeric inputs (1–4) and text inputs ("car", "bus", etc.).
                    // A switch would be cleaner if only numeric input was allowed.

                    System.out.println("-----------------------------------------------");
                    System.out.println("[" + (i + 1) + "] Vehicle has arrived — please select its type below:");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Vehicle Type      ||      Toll Fee");
                    System.out.println("-----------------------------------------------");
                    System.out.println("[1] Motorcycle    ||      ₱50.00");
                    System.out.println("[2] Car           ||      ₱100.00");
                    System.out.println("[3] Bus           ||      ₱150.00");
                    System.out.println("[4] Truck         ||      ₱200.00");
                    System.out.println("-----------------------------------------------");
                    System.out.print("> Enter vehicle type: ");
                    vehicleType = reader.nextLine().toLowerCase();

                    // ====== DECISION STRUCTURE (If–Else) ======
                    if (vehicleType.equals("motorcycle") || vehicleType.equals("motor") || vehicleType.equals("1")) {
                        motorcycleTotal += MOTORCYCLE_FEE;
                        System.out.println("[" + (i + 1) + "] Vehicle identified as a Motorcycle. Toll fee: ₱" + MOTORCYCLE_FEE);
                        isValidInput = false;

                    } else if (vehicleType.equals("car") || vehicleType.equals("2")) {
                        carTotal += CAR_FEE;
                        System.out.println("[" + (i + 1) + "] Vehicle identified as a Car. Toll fee: ₱" + CAR_FEE);
                        isValidInput = false;

                    } else if (vehicleType.equals("bus") || vehicleType.equals("3")) {
                        busTotal += BUS_FEE;
                        System.out.println("[" + (i + 1) + "] Vehicle identified as a Bus. Toll fee: ₱" + BUS_FEE);
                        isValidInput = false;

                    } else if (vehicleType.equals("truck") || vehicleType.equals("4")) {
                        truckTotal += TRUCK_FEE;
                        System.out.println("[" + (i + 1) + "] Vehicle identified as a Truck. Toll fee: ₱" + TRUCK_FEE);
                        isValidInput = false;

                    } else {
                        System.out.println("> Invalid input. Please enter 1–4 or the vehicle name.");
                    }

                    System.out.println("-----------------------------------------------");
                }
            }

            // ====== DISPLAY SHIFT SUMMARY ======
            System.out.println();
            System.out.println("===============================================");
            System.out.println("             SHIFT SUMMARY REPORT              ");
            System.out.println("===============================================");
            System.out.println("Vehicles Processed : " + numberOfVehicles);
            System.out.println("-----------------------------------------------");
            System.out.println("Vehicle Type      ||      Total Toll Collected");
            System.out.println("-----------------------------------------------");
            System.out.printf("%-15s || ₱%6d%n", "Motorcycle", motorcycleTotal);
            System.out.printf("%-15s || ₱%6d%n", "Car", carTotal);
            System.out.printf("%-15s || ₱%6d%n", "Bus", busTotal);
            System.out.printf("%-15s || ₱%6d%n", "Truck", truckTotal);
            System.out.println("-----------------------------------------------");

            System.out.println("\nPress <Enter> to continue...");
            reader.nextLine();

            // ====== RESET VARIABLES FOR NEXT BATCH ======
            numberOfVehicles = 0;
            motorcycleTotal = 0;
            carTotal = 0;
            busTotal = 0;
            truckTotal = 0;

            // ====== ASK USER TO CONTINUE OR EXIT ======
            System.out.println("-----------------------------------------------");
            System.out.println("> You've finished processing your quota for today.");
            System.out.println("> Would you like to process another batch?");
            System.out.println(">> [1] Start New Batch");
            System.out.println(">> [2] Resign");
            System.out.print("> Enter choice: ");
            vehicleType = reader.nextLine().toLowerCase();

            if (vehicleType.equals("1") || vehicleType.equals("yes") || vehicleType.equals("process new batch")) {
                System.out.println("\n> Preparing next batch...\n");
            } else if (vehicleType.equals("2") || vehicleType.equals("no") || vehicleType.equals("resign")) {
                System.out.println("\n> Ending your shift...");
                isOnDuty = false;
            }

        } while (isOnDuty); // end of main loop

        // ============================================
        // PROGRAM END
        // ============================================
        System.out.println();
        System.out.println("===============================================");
        System.out.println("         Program Terminated. Goodbye!          ");
        System.out.println("===============================================");
        System.out.println();

        reader.close();
    }
}
