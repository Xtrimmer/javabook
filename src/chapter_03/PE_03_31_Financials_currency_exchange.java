package chapter_03;

import java.util.Scanner;

/**
 * (Financials: currency exchange) Write a program that prompts the user to enter
 * the exchange rate from currency in U.S. dollars to Chinese RMB. Prompt the user
 * to enter 0 to convert from U.S. dollars to Chinese RMB and 1 to convert from
 * Chinese RMB and U.S. dollars. Prompt the user to enter the amount in U.S. dollars
 * or Chinese RMB to convert it to Chinese RMB or U.S. dollars, respectively.
 */
public class PE_03_31_Financials_currency_exchange {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter the exchange rate from dollars to RMB: ");
        double rate = SCANNER.nextDouble();
        System.out.print("Enter 0 to convert dollars to RMB and 1 vice versa: ");
        double conversion = SCANNER.nextDouble();

        if (conversion == 0) {
            System.out.print("Enter the dollar amount: ");
            double dollars = SCANNER.nextDouble();
            double rmb = Math.round(dollars * rate * 100) / 100.0;
            System.out.println("$" + dollars + " is " + rmb + " yuan");
        } else if (conversion == 1) {
            System.out.print("Enter the RMB amount: ");
            double rmb = SCANNER.nextDouble();
            double dollars = Math.round(rmb / rate * 100) / 100.0;
            System.out.println(rmb + " yuan is $" + dollars);
        } else {
            System.out.println("Incorrect input");
        }
    }
}
