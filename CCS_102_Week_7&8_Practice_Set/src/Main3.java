import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		double purchased;
		double discountedPrice;
		char registered;

		System.out.print("Enter purchase amount: ");
		purchased = Double.parseDouble(reader.readLine());

		if (purchased >= 1000) {
			System.out.println("-----------------------------");
			System.out.println("You are eligible for a discount!");

			discountedPrice = purchased - 10.0;

			System.out.println("-----------------------------");
			System.out.println("Enter (Y) yes or (N) no <");
			System.out.println("-----------------------------");
			System.out.println("Are you a registered member?");
			System.out.println("-----------------------------");
			System.out.print("Enter character only: ");
			registered = (char) reader.read();
			System.out.println("-----------------------------");

			// > If Yes (Y) <
			if (registered == 'Y') {
				System.out.println("<<>> Congratulations <<>>");
				System.out.println("You received 20%");

				discountedPrice = purchased - 20.0;
				System.out.println("Total: " + discountedPrice);
				// > If No (N) <
			} else if (registered == 'N') {
				System.out.println("Total: " + discountedPrice);

				// > Else wrong input <
			} else {
				System.out.println("Enter (Y) yes or (N) no only!");
				System.out.println("-----------------------------");
				System.out.println("     > Please Try Again <");
				System.out.println("-----------------------------");

			}
		} else {
			System.out.println("-----------------------------");
			System.out.println("You are not eligible for discount");
			System.out.println("Total: " + purchased);
			System.out.println("-----------------------------");

		}
	}

}
