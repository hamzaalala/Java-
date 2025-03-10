package subjecet;

/*
 * COSC1200 Assignment 3: Bowling Scoring
 * Name: Hamza Alala
 * Date: 2025-03-09
 * Description: Two-player bowling game that records scores frame-by-frame.
 * Uses correct rules for strikes, spares, and open frames. Determines a winner to.
 */

import java.util.Scanner;

public class BowlingGGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to the Bowling Scoring Program!");

        while (playAgain) {
            // Get players' names
            System.out.print("Enter Player 1's name: ");
            String player1 = scanner.nextLine();
            System.out.print("Enter Player 2's name: ");
            String player2 = scanner.nextLine();

            // Arrays to hold scores (up to 21 rolls per game)
            int[] rolls1 = new int[21];
            int[] rolls2 = new int[21];

            int index1 = 0, index2 = 0; // Tracking rolls per player

            System.out.println("\nStarting game between " + player1 + " and " + player2 + "...\n");

            // Play through 10 frames
            for (int frame = 1; frame <= 10; frame++) {
                System.out.println("\nFrame " + frame);
                System.out.println(player1 + "'s turn:");
                index1 = recordFrame(scanner, frame, rolls1, index1);
                System.out.println(player2 + "'s turn:");
                index2 = recordFrame(scanner, frame, rolls2, index2);
            }

            // Compute total scores
            int[] scores1 = computeScores(rolls1);
            int[] scores2 = computeScores(rolls2);
            int total1 = scores1[9];
            int total2 = scores2[9];

            // Print out the scoreboard
            System.out.println("\nFinal Scores:");
            System.out.printf("%6s   %15s   %15s\n", "Frame", player1, player2);
            System.out.println("-----------------------------------------------------");
            for (int frame = 0; frame < 10; frame++) {
                System.out.printf(" %2d     %15d   %15d\n", frame + 1, scores1[frame], scores2[frame]);
            }
            System.out.println("-----------------------------------------------------");
            System.out.printf("Total:   %15d   %15d\n", total1, total2);

            // Determine and announce the winner
            if (total1 > total2) {
                System.out.println("\n" + player1 + " wins!");
            } else if (total2 > total1) {
                System.out.println("\n" + player2 + " wins!");
            } else {
                System.out.println("\nIt's a tie!");
            }

            // Ask if players want to start another game
            System.out.print("\nPlay another game? (yes/no): ");
            playAgain = scanner.nextLine().trim().equalsIgnoreCase("yes");

            System.out.println();
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // Get a valid roll input from the user
    public static int getRoll(Scanner scanner, String rollDesc, int maxPins) {
        int roll;
        while (true) {
            System.out.print("Enter " + rollDesc + " (0-" + maxPins + "): ");
            if (scanner.hasNextInt()) {
                roll = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (roll >= 0 && roll <= maxPins) {
                    return roll;
                }
            } else {
                scanner.nextLine(); // Clear invalid input
            }
            System.out.println("Invalid input. Enter a number between 0 and " + maxPins + ".");
        }
    }

    // Record scores for one frame
    public static int recordFrame(Scanner scanner, int frame, int[] rolls, int index) {
        int firstRoll = getRoll(scanner, "first roll", 10);
        rolls[index++] = firstRoll;

        if (firstRoll == 10) { // Strike
            System.out.println("Strike!");
            if (frame == 10) {
                rolls[index++] = getRoll(scanner, "second roll", 10);
                rolls[index++] = getRoll(scanner, "third roll", 10);
            }
        } else {
            int secondRoll = getRoll(scanner, "second roll", 10 - firstRoll);
            rolls[index++] = secondRoll;

            if (firstRoll + secondRoll == 10) { // Spare
                System.out.println("Spare!");
                if (frame == 10) {
                    rolls[index++] = getRoll(scanner, "bonus roll", 10);
                }
            }
        }
        return index;
    }

    // Compute cumulative frame scores
    public static int[] computeScores(int[] rolls) {
        int[] frameScores = new int[10];
        int total = 0;
        int i = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (rolls[i] == 10) {  // Strike case: 10 + next two rolls
                total += 10 + rolls[i + 1] + rolls[i + 2];
                frameScores[frame] = total;
                i++;
            } else if (rolls[i] + rolls[i + 1] == 10) {  // Spare case: 10 + next roll
                total += 10 + rolls[i + 2];
                frameScores[frame] = total;
                i += 2;
            } else {  // Open frame
                total += rolls[i] + rolls[i + 1];
                frameScores[frame] = total;
                i += 2;
            }
        }
        return frameScores;
    }
}