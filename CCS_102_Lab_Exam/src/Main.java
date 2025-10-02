import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		// > Local Variables
		int months;
		int capitalShare;
		int totalToBePaid;
		int monthlyPayment;
		int loanValidity;
		float interestPerMonth; //I forgor how to use printf
		float loan;
		final float interestRate = 0.01f;
		
		// > INPUT
		System.out.println("---------------------------------------");
		System.out.println("> San Isidro Multipurpose Cooperative <");
		System.out.println("---------------------------------------");
		System.out.print("Input Capital Share: ");
		capitalShare = Integer.parseInt(reader.readLine());
		System.out.println("---------------------------------------");
		System.out.print("Input loan amount: ");
		loan = Integer.parseInt(reader.readLine());
		System.out.println("---------------------------------------");
		System.out.print("Input Months to Pay: ");
		months = Integer.parseInt(reader.readLine());
		System.out.println("---------------------------------------");
		
		// > PROCESS
		// > Checks if the loan amount is valid <
		loanValidity = (capitalShare * 2);
		interestPerMonth = ((interestRate * months) / loan);
		totalToBePaid = (int) (interestPerMonth + loan);
		monthlyPayment = (totalToBePaid / months);
		
		// > OUTPUT
		System.out.println("========================================");
		System.out.println((loan < loanValidity) ? "> Loan is Valid." : "> Amount is not allowed");
		System.out.println("> Loan Requested: " + loan);
		System.out.println("> Months To Pay: " + months);
		System.out.println("> Interest To Be Paid: " + interestPerMonth);
		System.out.println("> Total Amount to be Paid: " + totalToBePaid);
		System.out.println("> Monthly Payment: " + monthlyPayment);
		System.out.println("========================================");
		
		
	}

}
