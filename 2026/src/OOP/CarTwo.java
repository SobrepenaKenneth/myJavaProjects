package OOP;

public class CarTwo extends Vehicle {
	
	private String color;
	
	public CarTwo(String color) {
		this.color = color;
	}
	
	public void printDetails() {
		System.out.println("The car color is " + this.color);
	}

}
