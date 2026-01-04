package OOP;

public class Puppy {
	private int puppyAge;
	public static String BREED = "Bulldog";
	
	public void setAge(int age) {
		puppyAge = age;
	}
	
	public int getAge() {
		return puppyAge;
	}
	
	public static void main(String[] args) {
		Puppy myPuppy = new Puppy();
		myPuppy.setAge(4);
		System.out.println("Puppy Age: " + myPuppy.getAge());
		System.out.println("Breed: " + Puppy.BREED);
	}

}
