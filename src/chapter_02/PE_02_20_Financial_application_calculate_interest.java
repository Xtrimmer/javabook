package chapter_02;

import java.util.Scanner;

/**
 * (Financial application: calculate interest) If you know the balance and the annual
 * percentage interest rate, you can compute the interest on the next monthly payment
 * using the following formula:
 *
 *      interest = balance * (annualInterestRate / 1200)
 *
 * Write a program that reads the balance and the annual percentage interest rate and
 * displays the interest for the next month.
 */
public class PE_02_20_Financial_application_calculate_interest {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter balance and interest rate (e.g., 3 for 3%): ");
        double balance = SCANNER.nextDouble();
        double annualInterestRate = SCANNER.nextDouble();

        double interest = balance * (annualInterestRate / 1200);

        System.out.println("The interest is " + interest);
    }
}
