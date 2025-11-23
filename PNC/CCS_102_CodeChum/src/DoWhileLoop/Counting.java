package DoWhileLoop;


public class Counting {
	/*
	 * Write a program that takes an integer n. Use a do-while loop to count from 1
	 * to n (inclusive), and print each number on a new line.
	 */
	public static void main(String[] args) {int i = 1;
		while(i <= 5) {
			i++;
			if (i == 3) continue;
			System.out.print(i + " ");
		}
	}
}
