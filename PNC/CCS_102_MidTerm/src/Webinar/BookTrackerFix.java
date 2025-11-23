package Webinar;

import java.util.Scanner;

public class BookTrackerFix {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("== Reading Tracker ==");

		System.out.print("How many Chapters are in the book: ");
		int chapters = scan.nextInt();

		int totalPagesRead = 0; // Track across all chapters
		int totalPagesInBook = 0; // Track total pages in book

		for (int chapter = 1; chapter <= chapters; chapter++) {
			System.out.print("How many pages does Chapter " + chapter + " have: ");
			int pagesInChapter = scan.nextInt();
			totalPagesInBook += pagesInChapter;

			int pagesReadInChapter = 0;

			for (int page = 1; page <= pagesInChapter; page++) {
				int response;
				boolean validInput = false;

				// Input validation loop
				while (!validInput) {
					System.out.print("Have you read page " + page + "? [1] Yes [2] No: ");
					response = scan.nextInt();

					if (response == 1) {
						pagesReadInChapter++;
						validInput = true;
					} else if (response == 2) {
						validInput = true;
					} else {
						System.out.println("Invalid input. Please enter 1 for Yes or 2 for No.");
					}
				}
			}

			// Chapter progress
			System.out.println("\n=== Your Progress for Chapter " + chapter + " ===");
			System.out.println("Pages Read: " + pagesReadInChapter + "/" + pagesInChapter);

			// Correct completion check
			if (pagesReadInChapter == pagesInChapter) {
				System.out.println("✓ You have finished reading this chapter!");
			} else {
				System.out.println("✗ You have not finished reading this chapter.");
			}
			System.out.println();

			totalPagesRead += pagesReadInChapter;
		}

		// Final statistics
		System.out.println("=== OVERALL READING STATISTICS ===");
		System.out.println("Total Chapters: " + chapters);
		System.out.println("Total Pages in Book: " + totalPagesInBook);
		System.out.println("Total Pages Read: " + totalPagesRead);
		System.out.println("Overall Progress: " + totalPagesRead + "/" + totalPagesInBook + " pages");

		scan.close();
	}
}