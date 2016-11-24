package chapter_05;

import java.util.Scanner;

/**
 * (Financial application: compound value) Suppose you save $100 each month
 * into a savings account with the annual interest rate 5%. So, the monthly interest
 * rate is 0.05 / 12 = 0.00417. After the first month, the value in the account
 * becomes
 *
 *      100 * (1 + 0.00417) = 100.417
 *
 * After the second month, the value in the account becomes
 *
 *      (100 + 100.417) * (1 + 0.00417) = 201.252
 *
 * After the third month, the value in the account becomes
 *
 *      (100 + 201.252) * (1 + 0.00417) = 302.507
 *
 * and so on.
 *
 * Write a program that prompts the user to enter an amount (e.g., 100), the annual
 * interest rate (e.g., 5), and the number of months (e.g., 6) and displays the amount
 * in the savings account after the given month.
 */
public class PE_05_30_Financial_application_compound_value {
    public static void main(String[] args) {
        // Declare Variables
        Scanner scanner = new Scanner(System.in);
        int months;
        double monthlyAmount;
        double totalAmount = 0;
        double annualInterestRate;
        double monthlyInterestRate;

        // Prompt user for input
        System.out.print("Enter an amount: ");
        monthlyAmount = scanner.nextDouble();
        System.out.print("Enter the anual interest rate: ");
        annualInterestRate = scanner.nextDouble();
        System.out.print("Enter the number of months: ");
        months = scanner.nextInt();

        // Calculate savings account
        monthlyInterestRate = annualInterestRate / 12;
        for (int i = 1; i <= months; i++) {
            totalAmount = (monthlyAmount + totalAmount) * (1 + monthlyInterestRate);
            System.out.printf("After " + i + " months the total is: %.3f%n", totalAmount);
        }
    }
}
