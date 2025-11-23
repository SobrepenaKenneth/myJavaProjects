package Practice;

import java.util.Scanner;
public class Felonia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int MOTORCYCLE_FEE = 50;
        final int CAR_FEE = 100;
        final int BUS_FEE = 150;
        final int TRUCK_FEE = 200;

        int totalCollected = 0;
        int round = 1;
        String continueSimulation;

        do {
            System.out.println("=== Toll Booth Round " + round + " ===");
            
            // Input validation for number of vehicles
            int numVehicles;
            while (true) {
                System.out.print("Enter the number of vehicles passing (1-10): ");
                numVehicles = scanner.nextInt();
                if (numVehicles > 0 && numVehicles <= 10) {
                    break;
                }
                System.out.println("Invalid input! Please enter a number between 1 and 10.");
            }

            for (int vehicleCount = 1; vehicleCount <= numVehicles; vehicleCount++) {
                System.out.println("\nVehicle " + vehicleCount + " - Select type:");
                System.out.println("1. Motorcycle (₱" + MOTORCYCLE_FEE + ")");
                System.out.println("2. Car (₱" + CAR_FEE + ")");
                System.out.println("3. Bus (₱" + BUS_FEE + ")");
                System.out.println("4. Truck (₱" + TRUCK_FEE + ")");
                
                // Input validation for vehicle choice
                int choice;
                while (true) {
                    System.out.print("Enter choice (1-4): ");
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        break;
                    }
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                }

                int toll = 0;
                switch (choice) {
                    case 1:
                        toll = MOTORCYCLE_FEE;
                        System.out.println("Motorcycle passed. Fee: " + toll);
                        break;
                    case 2:
                        toll = CAR_FEE;
                        System.out.println("Car passed. Fee: " + toll);
                        break;
                    case 3:
                        toll = BUS_FEE;
                        System.out.println("Bus passed. Fee: " + toll);
                        break;
                    case 4:
                        toll = TRUCK_FEE;
                        System.out.println("Truck passed. Fee: " + toll);
                        break;
                }
                totalCollected += toll;
            }

            System.out.println("\n--- End of Round " + round + " ---");
            System.out.println("Total Collected so far: " + totalCollected);

            // Input validation for continue prompt
            while (true) {
                System.out.print("Do you want to simulate another round? (yes/no): ");
                continueSimulation = scanner.next();
                if (continueSimulation.equalsIgnoreCase("yes") || 
                    continueSimulation.equalsIgnoreCase("no")) {
                    break;
                }
                System.out.println("Please enter only 'yes' or 'no'.");
            }
            round++;

        } while (continueSimulation.equalsIgnoreCase("yes"));

        System.out.println("\n=== Simulation Ended ===");
        System.out.println("Final Total Collected: " + totalCollected);
        scanner.close();
    }
}