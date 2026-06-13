package OOP;

public class Main {
	public static void main(String[] args) {
		//carStudy();
		studentStudy();
	}

	static void studentStudy() {
		Student student1 = new Student("Kenneth", "Mahogany", 2501047);
		Student student2 = new Student("Felonia", "Faraday", 827644);
		
		student1.printDetails();
		student2.printDetails(true);
	}
	
	static void carStudy() {
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
	
	static void carStudyTwo() {
		CarTwo car = new CarTwo("Blue");
		
		car.printDetails();
		car.startEngine();
	}
	
	
}