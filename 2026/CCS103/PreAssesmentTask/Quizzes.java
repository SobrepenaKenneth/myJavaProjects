package PreAssesmentTask;

import java.util.Scanner;

/**
 * Problem 4: Problem Description Create a Java program that asks the user to
 * input the scores of 5 quizzes for a student. The program should: 1. Store the
 * quiz scores in an array 2. Compute the average score 3. Determine the
 * equivalent remark based on the average Grading Criteria • 90 – 100 -
 * Excellent • 80 – 89 - Very Good • 70 – 79 - Good • 60 – 69 - Passed • Below
 * 60 - Failed Program Requirements: • Use an array to store quiz scores • Use a
 * loop to accept user input • Use conditional statements to determine the
 * remark • Display: 1. All quiz scores 2. Average score (rounded to 2 decimal
 * places) 3. Final remark
 * 
 * 01/09/26
 */

public class Quizzes {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		double sum = 0, average = 0;
		double[] scores = new double[5];
		
		System.out.println("Enter quiz scores: ");
		for (int i = 0; i < scores.length; i++) {
			scores[i] = scan.nextDouble();
			sum += scores[i];
		}
		
		System.out.println("====:> All Quiz Scores <====");
		for (double showScores : scores) {
			System.out.print(showScores + ", ");
		}
		
		average = sum / 5;
		
		System.out.println("\n====:> Average Score <====");
		System.out.printf("Average score: %.2f", average);

		System.out.println("\n====:> Grade <====");
		if (average >= 90) {
			System.out.println("Final Remark: Excellent");
		} else if (average >= 80) {
			System.out.println("Final Remark: Very Good");
		} else if (average >= 70) {
			System.out.println("Final Remark: Good");
		} else if (average >= 60) {
			System.out.println("Final Remark: Passed");
		} else {
			System.out.println("Final Remark: Failed");
		}
		scan.close();
	}
}