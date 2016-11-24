package chapter_04;

import java.util.Scanner;

/**
 * Created by jtrimmer on 1/5/2016.
 */
public class PE_04_26_Financial_application_monetary_units {
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Receive the amount
        System.out.print("Enter an amount, for example 11.56: ");
        String amount = input.nextLine();
        int dollarAmount = Integer.parseInt(amount.substring(0, amount.indexOf('.')));
        int remainingAmount = Integer.parseInt(amount.substring(amount.indexOf('.') + 1));

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
                "\t" + dollarAmount + " dollars\n" +
                "\t" + numberOfQuarters + " quarters\n" +
                "\t" + numberOfDimes + " dimes\n" +
                "\t" + numberOfNickels + " nickels\n" +
                "\t" + numberOfPennies + " pennies";
        System.out.println(output);
    }
}
