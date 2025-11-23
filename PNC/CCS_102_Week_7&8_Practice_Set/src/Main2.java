import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		double gradePointAverage;
		double inputValue;
		
		System.out.println("==============================");
		System.out.println("**> University Scholarship <**");
		System.out.println("------------------------------");
		
		try {
			System.out.print("Enter your GPA: ");
			gradePointAverage = Double.parseDouble(reader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("------------------------------");
			System.out.println(">> - Invalid Input! - <<");
			System.out.println("------------------------------");
			System.out.print("Enter your GPA: ");
			gradePointAverage = Double.parseDouble(reader.readLine());
		}
		
		if (gradePointAverage >= 85.) {
			System.out.println("------------------------------");
		    try {
		    	System.out.println("Enter student attendance percentage!");
		    	System.out.print("Decimal format: ");
		    	inputValue = Double.parseDouble(reader.readLine());
		    	
		    	if (inputValue >= 0.75) {
		    		System.out.println("You are qualified for scholarship!");
		    	} else {
		    		System.out.println("You are not qualified for scholarship!");
		    	}

		    } catch (NumberFormatException e) {
		        System.out.println("Input is not a valid number.");
		    }
		} else {
			System.out.println("------------------------------");
			System.out.println("You are not qualified for scholarship!");
		}

	}

}
