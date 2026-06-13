package OOP;

// SubClass
// Create a student class
public class Student extends Person{
	
	// Declaring Attributes
	String section;
	int rollNum;
	
	// Initialize attributes
	/**
	 * @param name - name of the student
	 * @param section - section of the student
	 * @param rollNum - student id number
	 */
	Student(String name, String section, int rollNum) {
		super(name);
		this.section = section;
		this.rollNum = rollNum;
	}
	
	public void printDetails() {
		System.out.println("> Student Details <");
		System.out.println(this.name + ", " + this.section + ", " + this.rollNum + ".");
		System.out.println();
	}
	
	// POLYMORPHISM
	public void printDetails(boolean hideSection) {
		System.out.println("> Student Details <");
		System.out.println(this.name + ", " + (hideSection ? " " : this.section)  + ", " + this.rollNum + ".");
		System.out.println();
	}

}
