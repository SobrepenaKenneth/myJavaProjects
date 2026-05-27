package OOP;

public class Main {
	public static void main(String[] args) {
		carStudy();
	}

	public static void carStudy() {
		// Create a single car instance as required
		Car car = new Car("Toyota Camry", 2022, 50.0, true);

		System.out.println("=== City Wheels Rental System ===");
		System.out.println("Car Model: " + car.getModel());
		System.out.println("Year: " + car.getYear());
		System.out.println("Price per day: ₱" + car.getRentalPricePerDay());
		System.out.println("Available: " + car.isAvailable());

		System.out.println("\n--- Attempting to rent car for 5 days ---");
		car.rentCar(5);

		System.out.println("\n--- After Rental Status ---");
		System.out.println("Available: " + car.isAvailable());

		// Demonstrate trying to rent again
		System.out.println("\n--- Attempting to rent again ---");
		car.rentCar(3);
	}
}