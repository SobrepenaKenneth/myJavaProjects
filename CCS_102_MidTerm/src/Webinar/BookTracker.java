package Webinar;

import java.util.Scanner;

public class BookTracker {

	public static void main(String[] args) {
		/*
		 * Problem: The Book Reading Tracker
		 * NOTE: this is my attempt / The checked version is in the webinar.BookTrackerFix/
		 **
		 * Situation:** You are creating a program to help a student track their reading
		 * progress. The student is reading a long book and wants to maintain a
		 * consistent reading habit.
		 ** 
		 * Your Task:** Write a program that: \
		 * 1. Asks the user how many chapters are in the book. 
		 * 2. For each chapter, asks how many pages that chapter contains. 
		 * 3.For each page in the chapter, the program should check if the student read that page. 
		 * 4. If the student read the page, mark it as completed. 
		 * 5. At the end of each chapter, show the reading progress for that chapter. 
		 * 6. Keep track of total pages read across all chapters. 
		 * 7. After all chapters, display the overall reading statistics.
		 * 
		 * Constraints:** - Use nested loops (outer loop for chapters, inner loop for
		 * pages) - Use if-else statements to track reading status - No arrays or
		 * methods
		 ** 
		 * Now write your code for this problem.**
		 */
		Scanner scan = new Scanner(System.in);
		System.out.println("== Reading Tracker ==");
		int chapters, pages = 0, doneReading;
		boolean checker = false;
		
		System.out.print("How many Chapters are in the book: ");
		chapters = scan.nextInt();
		
		for (int i = 1; i <= chapters; i++) {
			System.out.print("How many pages does Chapter " + i + " Have: ");
			pages = scan.nextInt();
			int yes = 0, no = 0;
			
			System.out.println("[1] Yes [2] No");
			
			for (int pageChecker = 1; pageChecker <= pages; pageChecker++) {
				
				do {
					System.out.print("Have you read page " + pageChecker + ": ");
					doneReading = scan.nextInt();
					
					switch (doneReading) {	
					case 1 : 
						yes++;
						break;
					case 2 :
						no++;
						break;
					default :
						System.out.println("Invalid input.");
						checker = true;
						break;
					}
				} while (checker);
			}
			
			System.out.println("=== Your Progress for Chapter " + i + " ===");
			System.out.println("Pages Read: " + yes);
			System.out.println("Pages Not Read: " + no);
			if (yes == no) {
				System.out.println("You have finished reading chapter " + i);
			} else {
				System.out.println("You have not finished reading chapter " + i);
			}
			System.out.println();
		}
		scan.close();
	}
}
