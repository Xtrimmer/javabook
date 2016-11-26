package chapter_02;

import java.util.Scanner;

/**
 * (Financial application: monetary units) Rewrite Listing 2.10,
 * ComputeChange.java, to fix the possible loss of accuracy when converting a
 * double value to an int value. Enter the input as an integer whose last two digits
 * represent the cents. For example, the input 1156 represents 11 dollars and 56 cents.
 */
public class PE_02_22_Financial_application_monetary_units {
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Receive the amount
        System.out.print("Enter an integer amount, for example $11.56 would be 1156: ");
        int remainingAmount = input.nextInt();
        double amount = remainingAmount / 100d;

        // Find the number of one dollars
        int numberOfOneDollars = remainingAmount / 100;
        remainingAmount = remainingAmount % 100;

        // Find the number of quarters in the remaining amount
        int numberOfQuarters = remainingAmount / 25;
        remainingAmount = remainingAmount % 25;

        // Find the number of dimes in the remaining amount
        int numberOfDimes = remainingAmount / 10;
        remainingAmount = remainingAmount % 10;

        // Find the number of nickels in the remaining amount
        int numberOfNickels = remainingAmount / 5;
        remainingAmount = remainingAmount % 5;

        // Find the number of pennies in the remaining amount
        int numberOfPennies = remainingAmount;

        // Display results
        String output = "Your amount " + amount + " consists of \n" +
                "\t" + numberOfOneDollars + " dollars\n" +
                "\t" + numberOfQuarters + " quarters\n" +
                "\t" + numberOfDimes + " dimes\n" +
                "\t" + numberOfNickels + " nickels\n" +
                "\t" + numberOfPennies + " pennies";
        System.out.println(output);
    }
}
