package preLim;

public class UnaryOperators {
	public static void main(String[] args) {
		// =========================================
		//         - INCREMENTS OPERATORS -
		// =========================================
		
		// PostFix++: First operation and then increment
		// ++PreFix : First increment and then perform operation
		
		int age = 10;
		
		System.out.println(age++); // 10 First operation
		// 11 then increment
		System.out.println(++age); // 12 increment
		// then perform operation 12
		
		System.err.println(age++ + ++age);
		// prints 12
		// increments 13
		// prints 14
		// perform the operation 12 + 14
		// prints 26
		// -ken
		
		// =========================================
		//         - ASSIGNMENT OPERATORS -
		// =========================================
		
		// Associativity (Right to Left) then assign
		// Instead of (x = x + y) do this (x += y)	
		
		int salary = 20000;
		int promo = 5000;
		
		// salary = salary + promo;
		// Instead of writing this ^
		// the output of salary + promo will be stored
		// in salary
		salary += promo;
		// It's the same as this
		System.err.println(salary);
		// - ken
		
		// =========================================
		//         - RELATIONAL OPERATORS -
		// =========================================
		// returns boolean results
		/*== , Equal to.
		  != , Not equal to.
		  < , Less than.
		  <= , Less than or equal to.
		  > , Greater than.
		  >= , Greater than or equal to.
		 */
		// Comparison operators
        int a = 10;
        int b = 3;
        int c = 5;

        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));
        System.out.println("a == c: " + (a == c));
        System.out.println("a != c: " + (a != c));
        // - ken
	}
}
