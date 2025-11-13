package Webinar;

public class ExerciseOne {

	public static void main(String[] args) {
		for (int row = 1; row <= 50; row++) {
			if (row % 2 == 0) {				
				System.out.print(row + " ");
			}
		}
		
		System.out.println();
		
		for (int row = 50; row >= 1; row--) {
			if (row % 2 == 0) {				
				System.out.print(row + " ");
			}
		}
		
		System.out.println();
		
		int row = 1;
		while (row <= 50) {
			if (row % 2 == 0) {
				System.out.print(row + " ");
			}
			row++;
		}
	}

}
