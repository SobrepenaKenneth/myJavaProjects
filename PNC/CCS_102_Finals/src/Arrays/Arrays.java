package Arrays;

import java.util.Scanner;

public class Arrays {
	static Scanner scan = new Scanner(System.in);
	private static int row, column;
	
	// Multi-Dimensional
	public static void main(String[] args) {
		System.out.println("==========================");
		System.out.println("  XX | TIC TAC TOE | OO ");
		System.out.println("==========================");
		char[][] board = {
				{' ', ' ', ' '},
				{' ', ' ', ' '},
				{' ', ' ', ' '},
		};
		
		for (int turn = 0; turn <= 9; turn++) {
			playerOneTurn(board);
			printBoard(board);
			playerTwoTurn(board);
			printBoard(board);
		}
		
	}// main method
	
	public static void printBoard(char[][] board) {
		// board
		System.out.println("-----------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " |");
			}
			System.out.println();
			System.out.println("-----------");
		}
	}// printBoard method
	
	public static void playerOneTurn(char[][] board) {
		do {
			// ask the user
			System.out.print("Player 1, enter row (0-2): ");
			row = scan.nextInt();
			System.out.print("Player 1, enter column (0-2): ");
			column = scan.nextInt();
			System.out.println("==========================");
		} while (row > 2 || column > 2 || row < 0 || column < 0);
		
		board[row][column] = 'X';
	}// printBoard method
	
	public static void playerTwoTurn(char[][] board) {
		do {
			// ask the user
			System.out.print("Player 2, enter row (0-2): ");
			row = scan.nextInt();
			System.out.print("Player 2, enter column (0-2): ");
			column = scan.nextInt();
			System.out.println("==========================");
		} while (row > 2 || column > 2 || row < 0 || column < 0);
		
		board[row][column] = 'O';
	}// printBoard method
}
