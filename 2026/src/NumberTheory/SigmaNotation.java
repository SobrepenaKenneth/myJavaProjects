package NumberTheory;

public class SigmaNotation {

	// For this study i will use methods to separate each expression problem
	public static void main(String[] args) {
		//problemOne();
		problemTwo();

	}
	
	private static void problemOne() {
		
		int n = 5; // upper limit
		int sum = 0;
		
		for (int i = 1; i <= n; i++) {
			sum += (i * i);
			System.out.println(sum);
		}
		
		System.out.println("Total : " + sum);
	}
	
	private static void problemTwo() {
		int n = 6;
		int term = 1;
		int sum = 0;
		
		for (int i = 1; i <= n; i++) {
			term = term * 2;
			sum = sum + term;
			System.out.println(sum);
			
		}
		
		System.out.println("Total : " + sum);
	}

}
