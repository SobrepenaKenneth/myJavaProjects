package OOP;

public class Car {
	// Private fields as required
	private String model;
	private int year;
	private double rentalPricePerDay;
	private boolean available;

	// Constructor
	public Car(String model, int year, double rentalPricePerDay, boolean available) {
		this.model = model;
		this.year = year;
		this.rentalPricePerDay = rentalPricePerDay;
		this.available = available;
	}

	// Getters and Setters
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRentalPricePerDay() {
		return rentalPricePerDay;
	}

	public void setRentalPricePerDay(double rentalPricePerDay) {
		this.rentalPricePerDay = rentalPricePerDay;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	// Method that RETURNS the total rental price (doesn't print)
	public double calculateRentalPrice(int days) {
		return rentalPricePerDay * days;
	}

	// Optional: Method to rent the car
	public void rentCar(int days) {
		if (available) {
			available = false;
			double total = calculateRentalPrice(days);
			System.out.println("Car rented successfully!");
			System.out.println("Model: " + model);
			System.out.println("Days: " + days);
			System.out.println("Total Rental Price: ₱" + total);
		} else {
			System.out.println("Sorry, this car is not available for rent.");
		}
	}
}