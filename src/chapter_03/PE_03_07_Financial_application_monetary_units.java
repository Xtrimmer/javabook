package chapter_03;

import java.util.Scanner;

/**
 * (Financial application: monetary units) Modify Listing 2.10, ComputeChange.java,
 * to display the nonzero denominations only, using singular words for single units such
 * as 1 dollar and 1 penny, and plural words for more than one unit such as 2 dollars and
 * 3 pennies.
 */
public class PE_03_07_Financial_application_monetary_units {
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Receive the amount
        System.out.print(
                "Enter an amount in double, for example 11.56: ");
        double amount = input.nextDouble();

        int remainingAmount = (int) (amount * 100);

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

        String dollars = "dollar";
        String quarters = "quarter";
        String dimes = "dime";
        String nickels = "nickel";
        String pennies = "penny";

        if (numberOfOneDollars > 1) {
            dollars = "dollars";
        }
        if (numberOfQuarters > 1) {
            quarters = "quarters";
        }
        if (numberOfDimes > 1) {
            dimes = "dimes";
        }
        if (numberOfNickels > 1) {
            nickels = "nickels";
        }
        if (numberOfPennies > 1) {
            pennies = "pennies";
        }

        // Display results
        String output;
        output = "Your amount " + amount + " consists of \n";
        if (numberOfOneDollars > 0) {
            output += "\t" + numberOfOneDollars + " " + dollars + "\n";
        }
        if (numberOfQuarters > 0) {
            output += "\t" + numberOfQuarters + " " + quarters + "\n";
        }
        if (numberOfDimes > 0) {
            output += "\t" + numberOfDimes + " " + dimes + "\n";
        }
        if (numberOfNickels > 0) {
            output += "\t" + numberOfNickels + " " + nickels + "\n";
        }
        if (numberOfPennies > 0) {
            output += "\t" + numberOfPennies + " " + pennies;
        }
        System.out.println(output);
    }
}
