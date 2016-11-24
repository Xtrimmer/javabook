package chapter_06;

import java.util.Scanner;

/**
 * (Financial application: compute the future investment value) Write a method that
 * computes future investment value at a given interest rate for a specified number
 * of years. The future investment is determined using the formula in Programming
 * Exercise 2.21.
 *
 * Use the following method header:
 *
 *      public static double futureInvestmentValue(
 *      double investmentAmount, double monthlyInterestRate, int years)
 *
 * For example, futureInvestmentValue(10000, 0.05/12, 5) returns
 * 12833.59.
 *
 * Write a test program that prompts the user to enter the investment amount (e.g.,
 * 1000) and the interest rate (e.g., 9%) and prints a table that displays future value
 * for the years from 1 to 30, as shown below:
 *
 *      The amount invested: 1000 (enter)
 *      Annual interest rate: 9 (enter)
 *      Years Future Value
 *      1         1093.80
 *      2         1196.41
 *      ...
 *      29       13467.25
 *      30       14730.57
 */
public class PE_06_07_Financial_application_compute_the_future_investment_value {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("The amount invested: ");
        double investmentAmount = scanner.nextDouble();
        System.out.print("Annual interest rate: ");
        double annualInterestRate = scanner.nextDouble() / 100;

        System.out.println("Years Future Value");
        for (int i = 1; i <= 30; i++) {
            System.out.printf("%-2d%15.2f%n", i, futureInvestmentValue(investmentAmount, annualInterestRate/12, i));
        }
    }

    public static double futureInvestmentValue(
            double investmentAmount, double monthlyInterestRate, int years){
        return investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12);
    }
}
