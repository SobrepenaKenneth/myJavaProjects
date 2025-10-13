package For_Loops;

public class NestedFor {

	public static void main(String[] args) {
		for (int i = 1; i <= 2; i++) { // OUTER LOOP
			for (int j = 1; j <= 3; j++) { // INNER LOOP
				System.out.println(i + " * " + j + " = " + (i*j));
			}
		}

	}

}
