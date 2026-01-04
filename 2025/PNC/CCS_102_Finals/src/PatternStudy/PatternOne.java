package PatternStudy;

public class PatternOne {
	public static void main(String[] args) {
		int number = 8;
		
		for (int column = 1; column <= number; column++) {
			for (int row = column; row <= number; row++) {
				System.out.print(" ");
			}
			for (int row = 1; row < column; row++) {
				System.out.print("*");
			}
			for (int row = 1; row <= column; row++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		for (int i = 1; i <= number; i++) {
			for (int a = 1; a < number - 1; a++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= number; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
