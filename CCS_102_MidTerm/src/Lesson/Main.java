package Lesson;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input;

        // Input validation
        do {
            System.out.print("Enter the height of each tree tier: ");
            input = sc.nextInt();

            if (input <= 0) {
                System.out.println("----------------------------------");
                System.out.println("Enter a positive integer only!");
                System.out.println("----------------------------------");
            }
        } while (input <= 0);
        
        // ===== TREE BUILDING =====
        int tiers = 3;
        int maxWidth = (input + tiers - 1) * 2 - 1; // width of the last (bottom) tier

        // Loop for each tier
        for (int t = 0; t < tiers; t++) {
            for (int row = 1; row <= input; row++) {
                int stars = row * 2 - 1 + t * 2; // widen each tier slightly
                int spaces = (maxWidth - stars) / 2;

                // print spaces
                for (int s = 0; s < spaces; s++) {
                    System.out.print(" ");
                }

                // print ornaments and stars in repeating pattern
                for (int c = 0; c < stars; c++) {
                    if (c % 5 == 2)
                        System.out.print("@");
                    else if (c % 3 == 1)
                        System.out.print("O");
                    else
                        System.out.print("*");
                }

                System.out.println();
            }
        }

        // ===== TREE TRUNK =====
        int trunkHeight = input;
        int trunkWidth = input / 2 + 1; // keep trunk proportional
        if (trunkWidth % 2 == 0) trunkWidth++; // make sure trunk width is odd for centering

        for (int i = 0; i < trunkHeight; i++) {
            int spaces = (maxWidth - trunkWidth) / 2;

            // spaces before trunk
            for (int s = 0; s < spaces; s++) {
                System.out.print(" ");
            }

            // trunk itself
            for (int j = 0; j < trunkWidth; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        sc.close();
    }
}