import java.util.Scanner;

/**
 * Temperature Analyzer
 * Author: Hamza Alala
 * Date: 02/16/2025
/* This application collects temperature data over several days, identifies the highest
 and lowest recorded temperatures, and tells the daily and overall averages.*/



 public class TemperatureAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for number of days
        System.out.print("Enter the number of days, ranges from 2 to 366.\n");
        int numDays = scanner.nextInt();

        // Validate number of days

        while (numDays < 2 || numDays > 366) {
            System.out.print("Invalid input. Enter a number between 2 and 366: ");
            numDays = scanner.nextInt();
        }

        // Creating an arrays to store low and high temperatures
        double[] lowTemps = new double[numDays];
        double[] highTemps = new double[numDays];

        // Get temperature inputs
        for (int i = 0; i < numDays; i++) {
            System.out.print("Day " + (i + 1) + " - Enter the low temperature (between  -100 to 100): ");
            lowTemps[i] = scanner.nextDouble();

            // Validate low temperature
            while (lowTemps[i] < -100 || lowTemps[i] > 100) {
                System.out.print("The input is invalid. Set the temperature to be between -100 and 100.: ");
                lowTemps[i] = scanner.nextDouble();
            }

            System.out.print("Day " + (i + 1) + " - Enter the high temperature (-100 to 100): ");
            highTemps[i] = scanner.nextDouble();

            // Validate high temperature
            while (highTemps[i] < lowTemps[i] || highTemps[i] > 100) {
                System.out.print("Invalid input. High temperature must be ≥ " + lowTemps[i] + " and ≤ 100: ");
                highTemps[i] = scanner.nextDouble();
            }
        }

        // Calculate and display results
        double totalAvg = 0;
        double highestTemp = -100;
        double lowestTemp = 100;
        int highestDay = 0;
        int lowestDay = 0;

        System.out.println("\n--- Temperature Report ---");
        for (int i = 0; i < numDays; i++) {
            double dailyAvg = (lowTemps[i] + highTemps[i]) / 2;
            totalAvg += dailyAvg;

            if (highTemps[i] > highestTemp) {
                highestTemp = highTemps[i];
                highestDay = i + 1;
            }
            if (lowTemps[i] < lowestTemp) {
                lowestTemp = lowTemps[i];
                lowestDay = i + 1;
            }

            System.out.println("Day " + (i + 1) + ": Low = " + lowTemps[i] + "°C, High = "
                    + highTemps[i] + "°C, Average = " + dailyAvg + "°C");
        }

        // Printing out the results.
        System.out.println("\n--- Summary ---");
        System.out.println("Overall Average Temperature: " + (totalAvg / numDays) + "°C");
        System.out.println("Highest Temperature: " + highestTemp + "°C on Day " + highestDay);
        System.out.println("Lowest Temperature: " + lowestTemp + "°C on Day " + lowestDay);

    }
}
