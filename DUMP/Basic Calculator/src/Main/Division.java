package Main;

public class Division {	
	// TODO: the return sum, remainder doesn't work
	/* FIXME: separate the division and remainder and make a constructor instead, 
	 * so that the parameter in the constructor will be the value for the two methods
	 * and function the same!
	 * YIPPE LMAO
	 * */
	public double divideNumbers(double num1, double num2) {
		double sum;
		sum = num1 / num2;
		return sum;
	}
	
	public double getRemainder(double num1, double num2) {
		double remainder;
		remainder = num1 % num2;
		return remainder;
	}
}
