package OOP;

// base class for all students
class Person {
	String name;

	Person(String name) {
		this.name = name;
	}
}

// This is a student class
public class Student {
	// Declaring Attributes
	String name;
	int rollNo;
	String section;

	// initializing Attributes
	Student(String name, int rollNo, String section) {
		this.name = name;
		this.rollNo = rollNo;
		this.section = section;
	}

	// print details
	public void printDetails() {
		System.out.println("Student Details:");
		System.out.print(this.name + ", " + this.rollNo + ", " + section);
	}

	public static void main(String[] args) {
		Student student1 = new Student("Felonia", 2, "1CS-A");
		Student student2 = new Student("Kenneth", 1, "1CS-A");

		student1.printDetails();
		student2.printDetails();
	}
}
