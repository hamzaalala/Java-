package subjecet;
import java.util.Scanner;

public class While {
    private static final double CONVERSION_FACTOR = 703;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double height = 0;
        double weight = 0;
        double bmi;

      //*  Verification of height input
        while (true) {
            System.out.print("Kindly provide your height in inches, ranging from 24 to 120: ");
            if (scanner.hasNextDouble()) {
                height = scanner.nextDouble();
                if (height >= 24 && height <= 120) {
                    break;
                } else {
                    System.out.println("Error: The height range has to be 24 to 120 inches.");
                }
            } else {
                System.out.println("Error: Please provide a real height in numbers.");
                scanner.next(); // Clear the invalid input
            }
        }

// Weight input validation

        while (true) {
            System.out.print("Kindly input your weight in pounds, with a minimum of 25 pounds. ");
            if (scanner.hasNextDouble()) {
                weight = scanner.nextDouble();
                if (weight >= 25) {
                    break;
                } else {
                    System.out.println("Error: A minimum weight of twenty-five pounds is required.");
                }
            } else {
                System.out.println("Error: Please provide a legitimate weight in numbers.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Calculate BMI using the formula
        bmi = (weight / (height * height)) * CONVERSION_FACTOR;

        // Determine BMI category
        String category;
        if (bmi < 16) {
            category = "Severely underweight";
        } else if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 25) {
            category = "Healthy";
        } else if (bmi < 30) {
            category = "Overweight";
        } else {
            category = "Obese";

        }

        // Output the results
        System.out.printf("\nHeight: %.1f inches\nWeight: %.1f pounds\nBMI: %.1f\nCategory: %s\n", height, weight, bmi, category);


    }
}

