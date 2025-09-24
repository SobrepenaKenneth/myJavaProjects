import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4 {

	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String username;
		int input;
		String admin = "admin";
		int password = 1234;

		System.out.println("=============================");
		System.out.println("*****> Company Hiraya <*****");
		System.out.println("-----------------------------");
		System.out.print("Enter valid username: ");
		username = reader.readLine();
		System.out.println("-----------------------------");
		System.out.print("Enter valid password: ");
		input = Integer.parseInt(reader.readLine());
		System.out.println("-----------------------------");
		if (username == "admin" && input == password) {
			System.out.println("Login Succesfull");
		} else {
			System.out.println("Login fail");
		}

		

		
		

	}

}
